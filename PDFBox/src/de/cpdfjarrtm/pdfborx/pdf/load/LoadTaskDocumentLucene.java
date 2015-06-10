package de.cpdfjarrtm.pdfborx.pdf.load;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentLucene;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexFieldType;
import de.cpdfjarrtm.pdfborx.pdf.document.ConvertPdfBibtex;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;


/**
 * Loads a single PDF file as DocumentLucene.
 * 
 * @author  Philipp Kleinhenz
 */
class LoadTaskDocumentLucene implements Runnable {    
    /**
     * The file to load.
     */
    private final File file;
    /**
     * Results from {@code run()} will be placed here as file-document
     * pairs.
     */
    private final ConcurrentMap<File, DocumentPdf> result;
    /**
     * The stripperpool. {@code run()} takes one stripper to extract text
     * and puts it back after it is done.
     */
    private final BlockingQueue<BorxTextStripper> strippers;

    /**
     * Creates a new document loader. Parameters given here will be used in the
     * {@code run()} method for multithreading.
     * 
     * @param   file
     *          The file to load.
     * @param   result
     *          The map to place the generated object in.
     * @param   strippers
     *          The stripperpool to take and return strippers from.
     */
    LoadTaskDocumentLucene(
            File file,
            ConcurrentMap<File, DocumentPdf> result,
            BlockingQueue<BorxTextStripper> strippers) {
        this.file = file;
        this.result = result;
        this.strippers = strippers;
    }

    /**
     * Loads the file specified in the constructor.
     * Places the result in the map, takes a stripper from the stripper pool
     * and returns it afterwards. Both map and stripperpool are specified
     * in the constructor.
     */
    @Override
    public void run() {
        try {
            // take a stripper from the pool
            BorxTextStripper s = strippers.take();
            // load the file and store its result
            result.put(file, load(file, s));
            // put the stripper back in the pool
            s.resetEngine();
            strippers.offer(s);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoadModelDocumentLucene.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Loads a PDF file.
     * 
     * @param   file
     *          The file to load.
     * @param   stripper
     *          The stripper to use for text extraction.
     * @return  An object containing the metadata and plain text of the PDF.
     */
    static DocumentPdf load(File file, BorxTextStripper stripper) {        
        PDDocument pddoc = null;

        // open the PDDocument
        try {
            pddoc =  PDDocument.load(file);
        } catch (IOException ex) {
            Logger.getLogger(LoadTaskDocumentLucene.class.getName())
                    .log(Level.SEVERE, "load " + file, ex);
        }

        // if the PDDocument was opened successfully
        // proceed with metadata extraction
        if (pddoc != null) {
            
            int numPages;
            
            String[] text;
            String fullText;
            float version;
            boolean encrypted;
            PDDocumentInformation info;

            Map<BibtexFieldType, String> bibtexfields;
                    
            numPages = pddoc.getNumberOfPages();
            encrypted = pddoc.isEncrypted();
            version = pddoc.getDocument().getVersion();
            
            info = pddoc.getDocumentInformation();
            
            // get all metadata from the document
            bibtexfields = new HashMap<>();
            for (String key : info.getMetadataKeys()) {
                if (ConvertPdfBibtex.isMapped(key)) {
                    bibtexfields.put(ConvertPdfBibtex.fieldPdfToBibtex(key), info.getCustomMetadataValue(key));
                }
            }

            // Extract the plain text from the document page by page.
            text = new String[numPages];
            fullText = "";
            try {
                for (int i = 1; i <= numPages; i++) {
                    stripper.setStartPage(i);
                    stripper.setEndPage(i);
                    text[i-1] = stripper.getText(pddoc);
                    fullText += text[i-1];
                    
                    // get title from document by guessing
                    if (i == 1) {
                        String titleGuess = stripper.getTitle();
                        if (titleGuess != null) {
                            bibtexfields.put(BibtexFieldType.TITLE, titleGuess);
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(LoadModelDocumentLucene.class.getName())
                        .log(Level.SEVERE, "text stripper " + file, ex);
            }
                        
            try {
                pddoc.close();
            } catch (IOException ex) {
                Logger.getLogger(LoadModelDocumentLucene.class.getName())
                        .log(Level.SEVERE, "close " + file, ex);
            }
            
            DocumentLucene doc;
            doc = new DocumentLucene(
                    bibtexfields,
                    -1,
                    file.getName(),
                    text,
                    Float.toString(version),
                    encrypted
            );

            return doc;
        } else {
            return null;
        }
    }

}
