package de.cpdfjarrtm.pdfborx.pdf.load;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.io.File;
import java.util.Collection;
import java.util.Map;


/**
 * Loads documents from files as DocumentPdf objects.
 * 
 * @author  Philipp Kleinhenz
 */
interface LoadModel {
    /**
     * Loads a PDF file.
     * 
     * @param   file
     *          The file to load.
     * @return  An object containing the metadata and plain text of the PDF.
     */
    DocumentPdf load(File file);
    /**
     * Loads multiple PDF files.
     * 
     * @param   files
     *          A {@link Collection} of filenames of PDF files to load.
     * @return  A map containing the data objects with metadata and plain text
     *          of the PDF files with the filenames as keys.
     */
    Map<File, DocumentPdf> load(Collection<File> files);
}
