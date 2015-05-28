package de.cpdfjarrtm.pdfborx.data;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 * DataController.
 * 
 * @author  Philipp Kleinhenz
 */
public interface DataController {
    /**
     * Stores a document for later retrieval.
     * 
     * @param   document 
     *          The document to store.
     * @return 
     */
    long storeDocument(DocumentPdf document);
    
    /**
     * Stores multiple documents for later retrieval.
     * 
     * @param   documents
     *          A Map of documents to store, with file descriptors as key.
     * @return 
     */
    Map<DocumentPdf, Long> storeDocuments(Map<File, DocumentPdf> documents);
    
    /**
     * 
     * @param document
     * @return 
     */
    long getId(DocumentPdf document);
    
    /**
     * Gets a stored document.
     * 
     * @param id
     * @return  The document or null if not found.
     */
    DocumentPdf getDocument(long id);
    
    /**
     * Gets all stored documents.
     * 
     * @return  A map containing all documents with filenames as keys.
     */
    Map<Long, DocumentPdf> getAllDocuments();
    
    /**
     * Gets multiple documents as Collection.
     * 
     * @param   ids
     *          A collection of ids to get the documents for.
     * @return  The documents as collection.
     * @deprecated 
     */
    @Deprecated
    public Collection<DocumentPdf> getDocuments(Collection<Long> ids);
    
    /**
     * Check if a document is available.
     * 
     * @param   id
     *          The documents id.
     * @return  {@code true} if the document is available, {@code false} if not.
     */
    boolean hasDocument(long id);
    
    /**
     * Check if a document is available.
     * 
     * @param   doc
     *          The document object.
     * @return  {@code true} if the document is available, {@code false} if not.
     */
    boolean hasDocument(DocumentPdf doc);
    
    /**
     * Get the amount of stored documents.
     * 
     * @return  The amount of stored documents.
     */
    long getAmount();    

}
