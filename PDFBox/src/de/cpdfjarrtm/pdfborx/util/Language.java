package de.cpdfjarrtm.pdfborx.util;

import org.apache.lucene.analysis.util.CharArraySet;

/**
 * Languages available to pdfborx, and helper functions to detect document languages.
 * 
 * @author  Philipp Kleinhenz
 */
public enum Language {
    /** German. */
    DE,
    /** English. */
    EN,
    /** No language known. */
    NONE;
    
    private static final CharArraySet stopset_de = Loader.loadAsCharArraySet(Settings.germanStopList);
    private static final CharArraySet stopset_en = Loader.loadAsCharArraySet(Settings.englishStopList);
    
    /**
     * detectLanguage.
     * @param text
     * @return 
     */
    public static Language detectLanguage(String text) {
        int en = 0;
        int de = 0;
        
        for (String s : text.split("\\s+")) {
            if (stopset_de.contains(s))
                de++;
            if (stopset_en.contains(s))
                en++;
        }

        if (en >= de)
            return Language.EN;
        else
            return Language.DE;
    }
    
}
