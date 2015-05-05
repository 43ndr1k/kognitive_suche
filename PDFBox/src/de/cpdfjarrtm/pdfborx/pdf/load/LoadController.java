package de.cpdfjarrtm.pdfborx.pdf.load;

import de.cpdfjarrtm.pdfborx.PdfBorx;
import de.cpdfjarrtm.pdfborx.data.DataController;
import de.cpdfjarrtm.pdfborx.parser.biblio.Citation;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentLucene;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * Controls loading of PDF documents.
 * 
 * Use to load PDF files into DocumentPdf data objects.
 * 
 * @author  Philipp Kleinhenz
 * 
 * @version 0.2.0
 */
public class LoadController {
    private final LoadModel loadModel;
    private final DataController data;
    private final PdfBorx app;

    /**
     * Create a new LoadController. 
     * @param   data
     *          Controller to save loaded documents in memory or database.
     * @TODO callback funcs an documents hängen, listener/observer
     */
    public LoadController (DataController data) {
        loadModel = new LoadModelDocumentLucene();
        this.data = data;
        this.app = PdfBorx.getInstance();
    }
    
    /**
     * Loads a single PDF file.
     * 
     * @param   file
     *          The file of the PDF to load.
     * @return  An object containing the metadata and plain text of the PDF.
     */
    public DocumentPdf loadDocument(File file) {
        DocumentPdf doc = loadModel.load(file);
        processDoc(doc);
        app.getIndex().commit();
        return doc;
    }
    
    private void indexDoc(DocumentPdf doc) {
        if (doc instanceof DocumentLucene) {
            app.getIndex().addDocument((DocumentLucene)doc);
        }
    }

    
    private void processDoc(DocumentPdf doc) {
        List<Citation> cits = app.getBiblioParser().getCitations(doc);
        for (Citation c : cits) {
            doc.addCitation(c);
        }
        doc.getDirtyFields(true);
        
        data.storeDocument(doc);
        // @TODO besseren flow finden, DataController vllt fuer indexen? listener?
        indexDoc(doc);
    }
    
    /**
     * Loads multiple PDF files.
     * 
     * @param   files
     *          A {@link Collection} of files of PDFs to load.
     * @return  A map containing the data objects, using the id as key.
     */
    public Map<File, DocumentPdf> loadDocument(Collection<File> files) {
        Map<File, DocumentPdf> docs = loadModel.load(files);
        for (DocumentPdf doc : docs.values()) {
            processDoc(doc);
        }
        app.getIndex().commit();
        return docs;
    }
    
    /**
     * Loads multiple PDF files.
     * 
     * @param   files
     *          An array of file names of PDFs to load.
     * @return  A map containing the data objects, using the file as key.
     */
    public Map<File, DocumentPdf> loadDocument(File[] files) {
        return loadDocument(Arrays.asList(files));
    }
    
}
