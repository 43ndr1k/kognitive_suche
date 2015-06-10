package de.cpdfjarrtm.pdfborx.index;

import de.cpdfjarrtm.pdfborx.util.Language;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LetterTokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.de.GermanNormalizationFilter;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.hunspell.HunspellDictionary;
import org.apache.lucene.analysis.hunspell.HunspellStemFilter;
import org.apache.lucene.analysis.miscellaneous.SetKeywordMarkerFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;
import org.apache.lucene.util.Version;


/**
 * HunspellAnalyzer is a Analyzer that uses an Hunspell dictionary
 * and affix directory for stemming and a stop word list for filtering.
 * 
 * @author  Philipp Kleinhenz
 */
public class HunspellAnalyzer extends StopwordAnalyzerBase {
    private final HunspellDictionary dic;
    private final CharArraySet stemExclusionSet;
    private final Language lang;
    
    /**
     * Creates a new Hunspell analyzer.
     * 
     * @param   lang
     *          The dictionary language.
     * @param   matchVersion
     *          The lucene version using this analyzer.
     * @param   dic
     *          Path to the hunspell dictionary file.
     * @param   aff
     *          Path to the hunspell affix file.
     * @param   stopset
     *          A CharArraySet containing all stopwords.
     * @throws  IOException
     *          Can be thrown while reading dictionary and affix files
     * @throws  ParseException
     *          Can be thrown if affix or dictionary file are of a wrong format
     */
    public HunspellAnalyzer(Language lang, Version matchVersion, String dic, String aff, CharArraySet stopset)
            throws IOException, ParseException {
        super(matchVersion, stopset);
        this.dic = new HunspellDictionary(
                this.getClass().getResourceAsStream(aff),   // Dictionary
                this.getClass().getResourceAsStream(dic),   // Affixes
                matchVersion,                          // Lucene Version
                false);                                     // ignore case
        stemExclusionSet = CharArraySet.EMPTY_SET;
        this.lang = lang;
    }
    
    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {        
        //final Tokenizer source = new StandardTokenizer(matchVersion, reader);
        final Tokenizer source = new LetterTokenizer(matchVersion, reader);
        TokenStream result = new StandardFilter(matchVersion, source);
        result = new StopFilter(matchVersion, result, stopwords);
        if (lang == Language.EN && matchVersion.onOrAfter(Version.LUCENE_31)) {
            result = new EnglishPossessiveFilter(matchVersion, result);    
        }
        if(!stemExclusionSet.isEmpty()) {
            result = new SetKeywordMarkerFilter(result, stemExclusionSet);
        }
        if (matchVersion.onOrAfter(Version.LUCENE_35)) {
            result = new HunspellStemFilter(result, dic);
        }
        result = new LowerCaseFilter(matchVersion, result);
        if (lang == Language.DE && matchVersion.onOrAfter(Version.LUCENE_36)) {
            result = new GermanNormalizationFilter(result);
        }
        return new TokenStreamComponents(source, result);   
    }
    
}
