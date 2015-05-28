package de.cpdfjarrtm.pdfborx.parser.biblio;

import java.util.ArrayList;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;

/**
 * This class controls the parsing of bibliographies.
 * 
 * @author Julian Goetz
 *
 */
public class BiblioParserController {

    private BibliographyParser bibparser;
    private String bibliography;
    
    public BiblioParserController(){
        bibparser = new BibliographyParser();
    }
    
    /**
     * This method will parse a bibliography and return its list of citations.
     * 
     * @param doc The bibliography and citations of this document will be parsed.
     * @return The parsed citations.
     */
    public ArrayList<Citation> getCitations(DocumentPdf doc){
        bibliography = bibparser.getBibliography(doc);
        ArrayList<Citation> citations = bibparser.splitBibliography(bibliography);
        citations = bibparser.parseCitations(citations);
        return citations;
    }
    
    /**
     * Gets the bibliography of a document in one String.
     * 
     * @param doc The bibliography of this document will be parsed.
     * @return The parsed bibliography.
     */
    public String getBibliography(DocumentPdf doc){
        return bibliography = bibparser.getBibliography(doc);
    }
}
