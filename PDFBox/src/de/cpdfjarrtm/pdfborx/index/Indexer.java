package de.cpdfjarrtm.pdfborx.index;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentFieldType;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentLucene;
import de.cpdfjarrtm.pdfborx.util.Language;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.util.Version;

/**
 * Indexes documents in an search index.
 * Must be commited everytime new documents have been added.
 * 
 * @author  Philipp Kleinhenz.
 */
public class Indexer {
    private IndexWriter indexWriter;
    private final Index index;
        
    public Indexer(Index index) {
        this.index = index;
        IndexWriterConfig config;

        indexWriter = null;
        try {
            config = new IndexWriterConfig(Version.LUCENE_46, new StandardAnalyzer(Version.LUCENE_46));
            indexWriter = new IndexWriter(index.getDirectory(), config);
        } catch (IOException ex) {
            Logger.getLogger(Indexer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Add a docuemnt to the search index.
     * 
     * @param   doc 
     *          The document to add.
     */
    public void addDocument(DocumentLucene doc) {
        Document luc = doc.getLuceneDocument();
        try {
            indexWriter.addDocument(luc, index.getAnalyzer(doc.getLanguage()));
        } catch (IOException ex) {
            Logger.getLogger(Indexer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateDocument(DocumentLucene doc) {
        Document luc = doc.getLuceneDocument();
        Term term = new Term(DocumentFieldType.HASH_TYPE.name(), String.valueOf(doc.hashCode()));
        try {
            indexWriter.updateDocument(term, luc, index.getAnalyzer(doc.getLanguage()));
        } catch (IOException ex) {
            Logger.getLogger(Indexer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Write uncommited data to the search index. Always call this after adding
     * new documents to the index.
     */
    public void commit() {
        try {
            indexWriter.commit();
        } catch (IOException ex) {
            Logger.getLogger(Indexer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clear() {
        commit();
        try {
            indexWriter.deleteAll();
        } catch (IOException ex) {
            Logger.getLogger(Indexer.class.getName()).log(Level.SEVERE, null, ex);
        }
        commit();
    }
    
}
