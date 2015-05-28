package de.cpdfjarrtm.pdfborx.keyword;

import de.cpdfjarrtm.pdfborx.util.Settings;
import de.cpdfjarrtm.pdfborx.index.HunspellAnalyzer;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.cpdfjarrtm.pdfborx.util.Language;
import de.cpdfjarrtm.pdfborx.util.Loader;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;
import org.apache.lucene.util.Version;

/**
 * A simple Keyword generator using HunspellAnalyzers to extrakt user tag
 * (keyword) candidates from a given document.
 * 
 * @author  Philipp Kleinhenz
 */
public class KeywordGeneratorEnDe implements KeywordGenerator {  
    private HunspellAnalyzer hunspell_de;
    private HunspellAnalyzer hunspell_en;
    private CharArraySet stopset_de;
    private CharArraySet stopset_en;
    
    /**
     * A simple Keyword generator using HunspellAnalyzers to extrakt user tag
     * (keyword) candidates from a given document.
     */
    public KeywordGeneratorEnDe() {
        stopset_de = Loader.loadAsCharArraySet(Settings.germanStopList);
        stopset_en = Loader.loadAsCharArraySet(Settings.englishStopList);
        hunspell_de = null;
        hunspell_en = null;
        try {
            hunspell_de = new HunspellAnalyzer(Language.DE, Version.LUCENE_46,
                    Settings.germanDic, Settings.germanAff, stopset_de);
            hunspell_en = new HunspellAnalyzer(Language.EN, Version.LUCENE_46,
                    Settings.englishDic, Settings.englishAff, stopset_en);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(KeywordGeneratorEnDe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Keyword> getKeywords(DocumentPdf doc) {
        TokenStream ts = null;
        List<Keyword> keywords = new ArrayList<>();
        Map<String, Integer> keymap = new HashMap<>();
        StopwordAnalyzerBase analyzer;
        
        switch (doc.getLanguage()) {
            case DE:
                analyzer = hunspell_de;
                break;
            case EN:
                analyzer = hunspell_en;
                break;
            default:
                analyzer = hunspell_en;
                break;
        }
        
        int total = 0;
        for (int i = 1; i <= doc.getNumberOfPages(); i++) {
            try {
                ts = analyzer.tokenStream("text", doc.getText(i));
                if (ts != null) {
                    CharTermAttribute cattr = ts.addAttribute(CharTermAttribute.class);
                    
                    ts.reset();
                    while (ts.incrementToken()) {
                        String term = cattr.toString();
                        if (keymap.containsKey(term)) {
                            keymap.put(term, keymap.get(term)+1);
                        } else {
                            keymap.put(term, 1);
                        }
                        total++;
                    }
                    ts.end();
                    ts.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(KeywordGeneratorEnDe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
       
        for (Map.Entry<String, Integer> e : keymap.entrySet()) {
            keywords.add(new SimpleKeyword(e.getKey(), e.getValue()/(float)total));
        }
        
        Collections.sort(keywords);
        Collections.reverse(keywords);
        
        System.out.println("STARTKEY:"+doc.getFilename());
        
        for(int i = 0; i < keywords.size(); i++)
        {
        	System.out.println(keywords.get(i).getTerm()+":"+keywords.get(i).getWeight());
        }
        
        System.out.println("ENDKEY:"+doc.getFilename());
        
        return keywords;
    }
       
}
