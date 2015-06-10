package de.cpdfjarrtm.pdfborx.db.controller;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.sql.Connection;

/**
 * Interface for database usage.
 * 
 * @author Julian Goetz
 * 
 */
public interface DatabaseInterface {
    
    /**
     * Establishes the connection. The database will be created if it doesn't exist yet. Do not forget to use setPath(String path) first.
     * @return True, if connected. False, if not.
     */
    public boolean establishConnection();
    
    /**
     * Closes the connection to the database.
     * @return True, if the connection was closed successfully. False, if not.
     */
    public boolean closeConnection();
    
    /**
     * Saves the data of each documents[] element.
     * 
     * @param documents    These documents will be saved.
     */
    public void saveDocumentData(DocumentPdf documents[]);
    
    /**
     * Returns the saved data of a document as a DocumentPdf object.
     * 
     * @return The saved data.
     */
    public DocumentPdf readDocumentData();
    
    /**
     * Sets the path where the database file will be saved.
     * 
     * @param path The path of the database file. Must end either on '/' (Linux) or '\' (Windows).
     */
    public void setPath(String path);

    /**
     * Returns the path of the database file.
     * 
     * @return The path of the database file.
     */
    public String getPath();
    
    public Connection getConnection();
}