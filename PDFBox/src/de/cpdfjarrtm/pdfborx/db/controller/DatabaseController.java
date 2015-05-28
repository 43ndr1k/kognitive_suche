package de.cpdfjarrtm.pdfborx.db.controller;

import de.cpdfjarrtm.pdfborx.PdfBorx;
import de.cpdfjarrtm.pdfborx.db.model.DatabaseLink;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to control the database.
 * 
 * @author Julian Goetz
 */
public class DatabaseController implements DatabaseInterface {
    
    private PdfBorx app;
    private DatabaseLink link;
    private boolean connectionClosed;
    private Connection connection;
    
    public DatabaseController(PdfBorx app) {
        this.app = app;
        link  = new DatabaseLink();
        start();
    }

    @Override
    public boolean establishConnection() {
        connection = link.establishConnection();
        if (connection != null){
            System.out.println("Conntected to database.");
            return true;
        }
        else{
            System.out.println("Not connected.");
            return false;
        }
    }

    @Override
    public boolean closeConnection() {  
        return link.closeConnection();
    }

    @Override
    public void saveDocumentData(DocumentPdf documents[]) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DocumentPdf readDocumentData() {
        return null;
    }

    @Override
    public void setPath(String path) {
        link.setPath(path);
    }

    @Override
    public String getPath() {
        return link.getPath();
    }


    @Override
    public Connection getConnection() {
        return connection;
    }

    private void start() {
        link.setPath("./database/maindatabase");
        this.establishConnection();
        checkoutTable();
        
    }
    
    /**
     * controlls if the tables PDF_TABLE and CUSTOM_FIELDS are excisting (they wont if you start the program for the first time) if needed the tables will be created
     * 
     * Table PDF_TABLE contains the ID, the Document, the Text of the Document and all BibTex-Fields
     * CUSTOM_FIELDS contains all non-BibTex-Fields (ID | FIELDNAME | VALUE)
     */
    private void checkoutTable() {
        try {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(null, null, "PDF_TABLE", null);
            if (resultSet.next()) {
                System.out.println("table exsists");
            } else {
                           
                String creationStatementQuery = 
                        "CREATE TABLE PDF_TABLE "  +
                        "(PDF_ID BIGINT not NULL AUTO_INCREMENT, " +
                        "ADRESS VARCHAR(255), " +
                        "ANNOTE VARCHAR(255), " +
                        "AUTHOR VARCHAR(255), " +
                        "BOOKTITLE VARCHAR(255), " +
                        "CHAPTER VARCHAR(255), " +
                        "CROSSREF VARCHAR(255), " +
                        "EDITION VARCHAR(255), " +
                        "EDITOR VARCHAR(255), " +
                        "HOWPUBLISHED VARCHAR(255), " +
                        "INSTITUTION VARCHAR(255), " +
                        "JOURNAL VARCHAR(255), " +
                        "KEY VARCHAR(255), " +
                        "MONTH VARCHAR(255), " +
                        "NOTE VARCHAR(255), " +
                        "NUMBER VARCHAR(255), " +
                        "ORGANIZATION VARCHAR(255), " +
                        "PAGES VARCHAR(255), " +
                        "PUBLISHER VARCHAR(255), " +
                        "SCHOOL VARCHAR(255), " +
                        "SERIES VARCHAR(255), " +
                        "TITLE VARCHAR(255), " +
                        "TYPE VARCHAR(255), " +
                        "VOLUME VARCHAR(255), " +
                        "YEAR VARCHAR(255), " +
                        "FILENAME VARCHAR(255), " +
                        "VERSION VARCHAR (255), " +
                        "ENCRYPTED BOOLEAN, " +
                        "PRIMARY KEY (PDF_ID));";
            
            Statement executableStatement = connection.createStatement();
            executableStatement.execute(creationStatementQuery);
            System.out.println("table created");
        }
            ResultSet resultSet_Table2 = databaseMetadata.getTables(null, null, "CUSTOM_FIELDS", null);
            if (resultSet_Table2.next()) {
                System.out.println("table2 exsists");
            } else {
                                    String creationStatementQuery_Table2 = 
                        "CREATE TABLE CUSTOM_FIELDS "  +
                        "(PDF_ID BIGINT not NULL, " +
                        "FIELD_NAME VARCHAR(255), " +
                        "VALUE VARCHAR(255), " +
                        "PRIMARY KEY (PDF_ID, FIELD_NAME))";
                Statement executableStatement_Table2 = connection.createStatement();
                
                executableStatement_Table2.execute(creationStatementQuery_Table2);
                System.out.println("table2 created");
                
            }
            
            ResultSet resultSet_Table3 = databaseMetadata.getTables(null, null, "PDF_TEXT", null);
            if (resultSet_Table3.next()) {
                System.out.println("table3 exsists");
            } else {
                    String creationStatementQuery_Table3 = 
                        "CREATE TABLE PDF_TEXT "  +
                        "(PDF_ID INTEGER NOT NULL, " +
                        "PAGENUMBER BIGINT NOT NULL, " +
                        "TEXT LONGVARCHAR, " +
                        "PRIMARY KEY (PDF_ID, PAGENUMBER))";
                Statement executableStatement_Table3 = connection.createStatement();
                
                executableStatement_Table3.execute(creationStatementQuery_Table3);
                System.out.println("table3 created");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }  
}

