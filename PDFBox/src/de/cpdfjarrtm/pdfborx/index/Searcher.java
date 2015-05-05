package de.cpdfjarrtm.pdfborx.index;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentFieldType;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.cpdfjarrtm.pdfborx.util.Language;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.mlt.MoreLikeThis;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.QueryBuilder;


/**
 * Use this to search for documents in the index.
 * 
 * @author  Philipp Kleinhenz
 */
public class Searcher {
    private IndexReader indexReader;
    private IndexSearcher indexSearcher;
    private final Index index;
    
    public Searcher(Index index) {
        this.index = index;
    }
    
    /**
     * Search for a term in any document field.
     * 
     * @param   field
     *          The document field to search in (BibtexFieldType or DocumentFieldType)
     * @param   value
     *          The term to search for.
     * @param   lang
     *          The language the query is written in.
     * @param   resultsize
     *          The amount of search results to get.
     * @return  A list of documents containing the given term or similar terms.
     */
    public SearchResult search(String field, String value, Language lang, int resultsize) {
        openReader();

        
        QueryBuilder qb = new QueryBuilder(index.getAnalyzer(lang));
        Query q = qb.createBooleanQuery(field, value);
        
        if (q == null) {
            return new SearchResult();
        }
        
        TopDocs td = new TopDocs(0, null, 0);
        
        try {
            td = indexSearcher.search(q, resultsize);
        } catch (IOException ex) {
            Logger.getLogger(Searcher.class.getName()).log(Level.SEVERE, null, ex);
        }        
        SearchResult ret = getResult(td);
        closeReader();
        return ret;
    }
    
    private SearchResult getResult(TopDocs td) {
        SearchResult ret = new SearchResult();
        if (td == null) return ret;
        
        for (ScoreDoc scoreDoc : td.scoreDocs) {
            try {
                Document doc = indexSearcher.doc(scoreDoc.doc);
                ret.addResult(doc.getField(DocumentFieldType.ID_TYPE.name()).numericValue().longValue(), scoreDoc.score);
            } catch (IOException ex) {
                Logger.getLogger(Searcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }
    
    /**
     * Get the internal id used by lucene to reference documents.
     * Not to be confused with DocumentPdf#getId()
     * @param   doc
     *          The document to get the lucene id for.
     * @return  The lucene id or -1 if not in index.
     */
    public int getLuceneId(DocumentPdf doc) {
        int ret = -1;
        openReader();
        QueryBuilder qb = new QueryBuilder(index.getAnalyzer(doc.getLanguage()));
        Query q = qb.createPhraseQuery(DocumentFieldType.HASH_TYPE.name(), String.valueOf(doc.hashCode()));
        try {
            TopDocs td = indexSearcher.search(q, 1);
            if (td.scoreDocs.length > 0)
                ret = td.scoreDocs[0].doc;
        } catch (IOException ex) {
            Logger.getLogger(Searcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeReader();
        return ret;
    }
    
    /**
     * Find the top-n most similar documents to a given document.
     * 
     * @param   doc
     *          The document to check similarities in the index for.
     * @param   field
     *          The field to look for similarities by (full text, author, ...)
     * @param   resultsize
     *          The amount of results to get.
     * @return  The most similar documents.
     */
    public SearchResult similar(DocumentPdf doc, String field, int resultsize) {
        openReader();
        int id = -1;
        Query idq = new TermQuery(new Term(DocumentFieldType.HASH_TYPE.name(), String.valueOf(doc.hashCode())));
        try {
            TopDocs td = indexSearcher.search(idq, 1);
            if (td.scoreDocs.length > 0)
                id = td.scoreDocs[0].doc;
        } catch (IOException ex) {
            Logger.getLogger(Searcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (id == -1) {
            closeReader();
            return new SearchResult();
        }
        // @TODO FIDFSimilarity mit token payload gewichte einbringen PhiK
        MoreLikeThis mlt = new MoreLikeThis(indexReader);
        mlt.setFieldNames(new String[]{field});
        mlt.setAnalyzer(index.getAnalyzer(field, doc.getLanguage()));
        mlt.setMinTermFreq(0);
        mlt.setMinDocFreq(0);
        Query mltq = null;

        try {
            mltq = mlt.like(id);
        } catch (IOException ex) {
            Logger.getLogger(Searcher.class.getName()).log(Level.SEVERE, null, ex);
        }

        TopDocs td = null;
        
        try {
            td = indexSearcher.search(mltq, 10);
        } catch (IOException ex) {
            Logger.getLogger(Searcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SearchResult ret = getResult(td);
        
        closeReader();
        return ret;
    }
    
    private void openReader() {
        try {
            indexReader = DirectoryReader.open(index.getDirectory());
            indexSearcher = new IndexSearcher(indexReader);
        } catch (IOException ex) {
            Logger.getLogger(Searcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void closeReader() {
        try {
            indexSearcher = null;
            indexReader.close();
        } catch (IOException ex) {
            Logger.getLogger(Searcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
