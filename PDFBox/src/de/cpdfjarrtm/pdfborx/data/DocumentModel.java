package de.cpdfjarrtm.pdfborx.data;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.util.Map;

/**
 * DocumentModel organizes storage and retrieval of documents and assigns
 * global IDs to documnets.
 * 
 * @author  Philipp Kleinhenz
 */
public interface DocumentModel {
    /**
     * Stores a document for later retrieval.
     * @param   document 
     *          The document to add.
     * @return  The document's id.
     */
    long add(DocumentPdf document);
        
    /**
     * Gets all stored documents.
     * 
     * @return  A map containing all documents with ids as keys.
     */
    Map<Long, DocumentPdf> getAll();
    
    /**
     * Gets a stored document.
     * 
     * @param   id
     *          The id of the document to get.
     * @return  The document or null if not found.
     */
    DocumentPdf get(long id);

    /**
     * Get the documents current id. If the returned id differs from
     * the id stored in the document, the returned id is to be prioritised.
     * 
     * @param   document
     *          The document to get the id for.
     * @return  id
     *          The documents id.
     */
    long getId(DocumentPdf document);
    
    /**
     * Check if a document is available.
     * 
     * @param   id
     *          The document's id.
     * @return  {@code true} if the document is available, {@code false} if not.
     */
    boolean has(long id);
    
    /**
     * Check if a document is available.
     * 
     * @param   document
     *          The document.
     * @return  {@code true} if the document is available, {@code false} if not.
     */
    boolean has(DocumentPdf document);
    
    /**
     * Get the amount of stored documents.
     * 
     * @return  The amount of stored documents.
     */
    long size();
}
