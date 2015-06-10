package de.cpdfjarrtm.pdfborx.export;

import de.cpdfjarrtm.pdfborx.export.bibtex.FileExportBibTex;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.io.File;
import java.util.Collection;


/**
 * Handles bibtex export to text file.
 * 
 * @author  Philipp Kleinhenz
 */
public class ExportController {
    private final FileExportBibTex exportBibTex;
    
    public ExportController() {
        exportBibTex = new FileExportBibTex();
    }
    
    /**
     * Export document information as BibTeX in a new bib file.
     * Creates a new file.
     * 
     * @param   filename
     *          The filename of the new file.
     * @param   documents
     *          The documents to get the information from.
     * @return  {@code true} if successful, {@code false} if not.
     */
    public boolean exportBibTex(String filename, DocumentPdf[] documents) {
        exportBibTex.createBibFile(filename, documents);
        return checkFile(filename);
    }
    
    /**
     * Export document information as BibTeX.
     * Creates a new file.
     * 
     * @param   filename
     *          The filename of the new file.
     * @param   document
     *          The document to get the information from.
     * @return  {@code true} if successful, {@code false} if not.
     */
    public boolean exportBibTex(String filename, DocumentPdf document) {
        exportBibTex.createBibFile(filename, new DocumentPdf[] {document});
        return checkFile(filename);
    }
    
    /**
     * Export document information as BibTeX.
     * Creates a new file.
     * 
     * @param   filename
     *          The filename of the new file.
     * @param   documents
     *          A collection of documents to get the information from.
     * @return  
     */
    public boolean exportBibTex(String filename, Collection<DocumentPdf> documents) {
        exportBibTex.createBibFile(filename, documents
                .toArray(new DocumentPdf[documents.size()]));
        return checkFile(filename);
    }
    
    /**
     * Check if a file exists.
     * @param   filename
     *          The file to check.
     * @return  {@code true} if exists, {@code false} if not.
     */
    static private boolean checkFile(String filename) {
        return (new File(filename)).exists();
    }
    
}
