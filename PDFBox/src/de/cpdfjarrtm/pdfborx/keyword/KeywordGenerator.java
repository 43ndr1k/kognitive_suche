package de.cpdfjarrtm.pdfborx.keyword;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.util.List;

/**
 * Generate Keywords from any given document. (As long as there is plain text
 * to analyze available)
 * 
 * @author  Philipp Kleinhenz
 */
public interface KeywordGenerator {
    /**
     * Generate Keywords from an Document.
     * 
     * Keywords are mapped to the occurence count.
     * The map is sorted decending by count.
     * 
     * @param   doc The pdf to generate keywords from.
     * @return  Keywords mapped to count.
     */
    List<Keyword> getKeywords(final DocumentPdf doc);
}
