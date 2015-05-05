package de.cpdfjarrtm.pdfborx.index;

import de.cpdfjarrtm.pdfborx.keyword.KeywordGeneratorEnDe;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentLucene;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentFieldType;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentListener;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.cpdfjarrtm.pdfborx.util.Language;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.de.GermanAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.Version;

/**
 * Index.
 * 
 * @author  Philipp Kleinhenz
 */
public class Index implements DocumentListener {
    private Directory directory;
    private final Indexer indexer;
    private final Searcher searcher;
    private final IndexSettings settings;
    private final Map<String, Analyzer> analMap_de;
    private final Map<String, Analyzer> analMap_en;
    private final PerFieldAnalyzerWrapper anal_de;
    private final PerFieldAnalyzerWrapper anal_en;
    private final GermanAnalyzer default_de;
    private final EnglishAnalyzer default_en;
    
    public Index(IndexSettings settings) {
        this.settings = settings;
        
        directory = null;
        
        try {    
            directory = new MMapDirectory(new File(settings.getIndexPath()));
        } catch (IOException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }

        HunspellAnalyzer hun_de = null;
        HunspellAnalyzer hun_en = null;

        try {
            hun_de = new HunspellAnalyzer(Language.DE, Version.LUCENE_46,
                    settings.getDictDe(), settings.getAffDe(),  settings.getStopsetDe());
            hun_en = new HunspellAnalyzer(Language.EN, Version.LUCENE_46,
                    settings.getDictEn(), settings.getAffEn(),  settings.getStopsetEn());
        } catch (IOException | ParseException ex) {
            Logger.getLogger(KeywordGeneratorEnDe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        analMap_de = new HashMap<>();
        analMap_en = new HashMap<>();
        
        analMap_de.put(DocumentFieldType.USER_TAG_TYPE.name(), hun_de);
        analMap_en.put(DocumentFieldType.USER_TAG_TYPE.name(), hun_en);
        
        analMap_de.put(DocumentFieldType.FULL_TEXT_TYPE.name(), new GermanAnalyzer(Version.LUCENE_46, settings.getStopsetDe()));
        analMap_en.put(DocumentFieldType.FULL_TEXT_TYPE.name(), new EnglishAnalyzer(Version.LUCENE_46, settings.getStopsetEn()));
        
        default_de = new GermanAnalyzer(Version.LUCENE_46);
        default_en = new EnglishAnalyzer(Version.LUCENE_46);
        
        anal_de = new PerFieldAnalyzerWrapper(default_de, analMap_de);
        anal_en = new PerFieldAnalyzerWrapper(default_en, analMap_en);

        indexer = new Indexer(this);
        indexer.commit();
        searcher = new Searcher(this);
        
    }
    
    public Directory getDirectory() {
        return directory;
    }
    
    public void addDocument(DocumentLucene doc) {
        doc.addListener(this);
        indexer.addDocument(doc);
    }
    
    public void commit() {
        indexer.commit();
    }
    
    public Analyzer getAnalyzer(Language lang) {
        switch (lang) {
            case DE:
                return anal_de;
            case EN:
                return anal_en;
            default:
                return anal_en;
        }
    }
    
    public Analyzer getAnalyzer(String field, Language lang) {
        switch (lang) {
            case DE:
                if (analMap_de.containsKey(field))
                    return analMap_de.get(field);
                return default_de;
            case EN:
                if (analMap_en.containsKey(field))
                    return analMap_en.get(field);
                return default_en;
            default:
                return null;
        }
    }
    
    public IndexSettings getSettings() {
        return settings;
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
        commit();
        return searcher.search(field, value, lang, resultsize);
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
        commit();
        return searcher.similar(doc, field, resultsize);
    }

    @Override
    public void update(DocumentPdf document) {
        if (document instanceof DocumentLucene) {
            System.out.println("updating: " + document.getFilename());
            commit();
            indexer.updateDocument((DocumentLucene)document);
            commit();
        }
            
    }
    
    public void clear() {
        indexer.clear();
    }
    
}
