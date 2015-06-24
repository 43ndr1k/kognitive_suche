/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.cpdfjarrtm.pdfborx.data;

import de.cpdfjarrtm.pdfborx.PdfBorx;
import de.cpdfjarrtm.pdfborx.gui.model.ShowPdfModel;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexFieldType;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentLucene;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TimGeorg
 */
public class DocumentModelDatabase implements DocumentModel {

    private final Connection connection;
    private final PdfBorx app;
    private long id;
    private final HashMap<Long, DocumentPdf> documents;
    
    public DocumentModelDatabase (PdfBorx app) {
        this.app = app;
        documents = new HashMap<>();
        connection = app.getDatabaseController().getConnection();
    }
    
    @Override
    public long add(DocumentPdf document) {
        
        if (!has(document)) {
        
            PreparedStatement statement;
            String addQuery = "INSERT INTO PDF_TABLE (";
            for (BibtexFieldType field : document.getFields()) {        //jedes vorhandene bibtex feld
                addQuery += (field.toString()+",");                     //namentlich in tabelle eintragen
            }
            addQuery += "FILENAME,PATH,VERSION,ENCRYPTED";                   //sowie filename, version und encrypted, benötigt, um document lucene zu erzeugen
            addQuery += ") VALUES (";
            for (BibtexFieldType field : document.getFields()) {        //enstsprechende werte dazu eintragen
                addQuery += ("?,");    
            }
            
            
            //"'" + document.getField(field) +
            
            addQuery += ("?,?,?,?);"); //sowie die extra-felder
            
            try {
                ResultSet generatedKeys = null;
                statement = connection.prepareStatement(addQuery);
                int counter = 1;
                for(BibtexFieldType field : document.getFields()) {
                    statement.setString(counter, document.getField(field));
                    counter++;
                }
                statement.setString(counter, document.getFilename());
                statement.setString(counter +1, document.getPath());
                statement.setString(counter + 2, document.getVersion());
                statement.setBoolean(counter + 3, document.isEncrypted());
                
                statement.execute();
                //statement.executeUpdate(statement.toString(), Statement.RETURN_GENERATED_KEYS);
                generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                } else {
                     throw new SQLException("Insert did not work");
                }

            } catch (SQLException ex) {
                Logger.getLogger(DocumentModelDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(!document.getCustomFields().isEmpty()) {
                for (String cSF : document.getCustomFields()) {
                    try {
                        PreparedStatement cFQstatement = connection.prepareStatement("INSERT INTO CUSTOM_FIELDS (PDF_ID, FIELD_NAME, VALUE) VALUES (?,?,?);");
                        cFQstatement.setLong(1, id);
                        cFQstatement.setString(2, cSF);
                        cFQstatement.setString(3, document.getCustomField(cSF));
                        cFQstatement.execute();
                    } catch (SQLException ex) {
                        Logger.getLogger(DocumentModelDatabase.class.getName()).log(Level.SEVERE, null, ex);
                    }    
                }                              
            }
            
            for (int i = 1; i <= document.getNumberOfPages(); i++) {
            
                try {
                    PreparedStatement textStatement = connection.prepareStatement("INSERT INTO PDF_TEXT (PDF_ID, PAGENUMBER, TEXT) VALUES ( ?, ?, ?);");
                    textStatement.setLong(1, id);
                    textStatement.setLong(2, i);
                    textStatement.setString(3, document.getText(i));
                    
                    textStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(DocumentModelDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            document.setId(id);
            documents.put(id, document);
            return id;
        } else return getId(document);       
        
    }

    @Override
    public Map<Long, DocumentPdf> getAll() {
        return documents; 
    }

    @Override
    public DocumentPdf get(long id) {
        return documents.get(id);
    }

    @Override
    public long getId(DocumentPdf document) {
        return 0; 
    }

    @Override
    public boolean has(long id) {
         return documents.containsKey(id);
    }

    @Override
    public boolean has(DocumentPdf document) {        
        return documents.containsValue(document);
    }

    @Override
    public long size() {
        return documents.size();
    }
    
    private static Map<BibtexFieldType, String> columnNames = new HashMap<>();
        
    static {
        columnNames.put(BibtexFieldType.ANNOTE, "ANNOTE");
        columnNames.put(BibtexFieldType.AUTHOR, "AUTHOR");
        columnNames.put(BibtexFieldType.BOOKTITLE, "BOOKTITLE");
        columnNames.put(BibtexFieldType.CHAPTER, "CHAPTER");
        columnNames.put(BibtexFieldType.CROSSREF, "CROSSREF");
        columnNames.put(BibtexFieldType.EDITION, "EDITION");
        columnNames.put(BibtexFieldType.EDITOR, "EDITOR");
        columnNames.put(BibtexFieldType.HOWPUBLISHED, "HOWPUBLISHED");
        columnNames.put(BibtexFieldType.INSTITUTION, "INSTITUTION");
        columnNames.put(BibtexFieldType.JOURNAL, "JOURNAL");
        columnNames.put(BibtexFieldType.KEY, "KEY");
        columnNames.put(BibtexFieldType.MONTH, "MONTH");
        columnNames.put(BibtexFieldType.NOTE, "NOTE");
        columnNames.put(BibtexFieldType.NUMBER, "NUMBER");
        columnNames.put(BibtexFieldType.ORGANIZATION, "ORGANIZATION");
        columnNames.put(BibtexFieldType.PAGES, "PAGES");
        columnNames.put(BibtexFieldType.PUBLISHER, "PUBLISHER");
        columnNames.put(BibtexFieldType.SCHOOL, "SCHOOL");
        columnNames.put(BibtexFieldType.SERIES, "SERIES");
        columnNames.put(BibtexFieldType.TITLE, "TITLE");
        columnNames.put(BibtexFieldType.TYPE, "TYPE");
        columnNames.put(BibtexFieldType.VOLUME, "VOLUME");
        columnNames.put(BibtexFieldType.YEAR, "YEAR");
        
    }

    public void fill() {
        
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PDF_TABLE;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                long id = rs.getLong("PDF_ID");
                Map<BibtexFieldType, String> tempMap = new HashMap<>();
                for(Map.Entry<BibtexFieldType, String> column : columnNames.entrySet()) {
       
                    String val = rs.getString(column.getValue());
                    if(val!= null){
                        tempMap.put(column.getKey(), val);
                    }
                }
                PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM PDF_TEXT WHERE PDF_ID = " + id);
                ResultSet rs2 = ps2.executeQuery();
                List<String> tempText = new ArrayList<>();
                while(rs2.next()) {
                    tempText.add(rs2.getString("TEXT"));
                }
                              
                String filename = rs.getString("FILENAME"), version = rs.getString("VERSION");
                String path = rs.getString("PATH");
                Boolean encrypted = rs.getBoolean("ENCRYPTED");
                
                DocumentPdf document = new DocumentLucene(tempMap, id ,filename, path,tempText.toArray(new String[0]), version ,encrypted);
                PreparedStatement ps3 = connection.prepareStatement("SELECT * FROM CUSTOM_FIELDS WHERE PDF_ID = " + id + ";");
                ResultSet rs3 = ps3.getResultSet();
                if(rs3 != null) {
                    while (rs3.next()) {
                        document.setCustomField(rs3.getString("FIELD_NAME"), rs3.getString("VALUE"));
                    }
                }
                document.setId(id);
                documents.put(id, document);
                
                ((ShowPdfModel) app.getGuiController().getTempList().getModel()).updateList();
                app.getGuiController().getTempList().repaint();
                app.getGuiController().getTempList().revalidate();  
            }
            
        } catch (SQLException ex) {
            System.out.println("Loading from Database failed!");
            Logger.getLogger(DocumentModelDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

