package de.cpdfjarrtm.pdfborx.index;

import org.apache.lucene.analysis.util.CharArraySet;

/**
 * Stores all settings required for creating an indexer and searcher
 * over an document search index.
 * 
 * @author  Philipp Kleinhenz
 */
public class IndexSettings {
    private String indexPath;
    private CharArraySet stopsetDe;
    private CharArraySet stopsetEn;
    private String dictEn;
    private String dictDe;
    private String affEn;
    private String affDe;
    
    public String getIndexPath() {
        return indexPath;
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }

    public CharArraySet getStopsetDe() {
        return stopsetDe;
    }

    public void setStopsetDe(CharArraySet stopsetDe) {
        this.stopsetDe = stopsetDe;
    }

    public CharArraySet getStopsetEn() {
        return stopsetEn;
    }

    public void setStopsetEn(CharArraySet stopsetEn) {
        this.stopsetEn = stopsetEn;
    }

    public String getDictEn() {
        return dictEn;
    }

    public void setDictEn(String dictEn) {
        this.dictEn = dictEn;
    }

    public String getDictDe() {
        return dictDe;
    }

    public void setDictDe(String dictDe) {
        this.dictDe = dictDe;
    }

    public String getAffEn() {
        return affEn;
    }

    public void setAffEn(String affEn) {
        this.affEn = affEn;
    }

    public String getAffDe() {
        return affDe;
    }

    public void setAffDe(String affDe) {
        this.affDe = affDe;
    }
    
}
