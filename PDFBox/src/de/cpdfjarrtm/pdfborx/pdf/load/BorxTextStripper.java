package de.cpdfjarrtm.pdfborx.pdf.load;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;


/**
 * A text stripper used to extract plain text from a pdf that also tries to
 * guess the title of the document during processing.
 * 
 * @author  Philipp Kleinhenz
 * @see org.apache.pdfbox.util.PDFTextStripper
 */
public class BorxTextStripper extends PDFTextStripper {
    
    public BorxTextStripper() throws IOException {
        super();
    }
    
    /**
     * This method will attempt to guess the title of the document using
     * either the document properties or the first lines of text.
     * (Taken from org.apache.pdfbox.util.PDFText2HTML.java)
     * @return The title.
     * @see org.apache.pdfbox.util.PDFText2HTML#getTitle()
     */
    public String getTitle() {
        Iterator<List<TextPosition>> textIter = getCharactersByArticle().iterator();
        String titleGuess = null;
        float lastFontSize = -1.0f;
        boolean done = false;

        StringBuilder titleText = new StringBuilder();
        while (!done && textIter.hasNext()) {
            Iterator<TextPosition> textByArticle = textIter.next().iterator();
            while (!done && textByArticle.hasNext()) {
                TextPosition position = textByArticle.next();

                float currentFontSize = position.getFontSize();
                //If we're past 64 chars we will assume that we're past the title
                //64 is arbitrary
                if (currentFontSize != lastFontSize || titleText.length() > 64) {
                    if (titleText.length() > 0) {
                        done = true;
                        titleGuess = titleText.toString();
                    }
                    lastFontSize = currentFontSize;
                }
                if (currentFontSize > 12.5f) { // most body text is 12pt
                    titleText.append(position.getCharacter());
                }
            }
        }
        
        if (titleGuess == null || titleGuess.trim().isEmpty()) {
            titleGuess = document.getDocumentInformation().getTitle();
            if(titleGuess == null || titleGuess.trim().isEmpty()) {
                titleGuess = null;
            }
        }
        
        return titleGuess;
    }
    
}
