package de.cpdfjarrtm.pdfborx.imports;
import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.cpdfjarrtm.pdfborx.data.DataController;
import de.cpdfjarrtm.pdfborx.data.DataControllerImpl;
import de.cpdfjarrtm.pdfborx.data.DocumentModelImpl;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexEntryType;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexFieldType;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.cpdfjarrtm.pdfborx.pdf.load.LoadController;

public class ImportControllerTest {

    public ImportController importControl;
    public HashMap <BibtexFieldType, String> fieldTypeMap;
    public HashMap <String, String> customFieldMap;
    public BibtexEntryType entryType;
    public DocumentPdf document;
    public LoadController load;

    @Before
    public void setUp() {
        document = null;
        load = null;
        File documentFile = new File("code/test/testdata/arnold2003otes.pdf");
        
        DataController data = new DataControllerImpl(new DocumentModelImpl());
        load = new LoadController(data);
        document = load.loadDocument(documentFile);
    }
    
    
    
    @Test
    public void testimportBibTex(){
        File file = new File("code/test/testdata/importBibTexTestFile.bib");
        importControl = new ImportController();
        assertTrue(importControl.importBibTex(file, document));
        fieldTypeMap = importControl.getfieldTypeMap();
        entryType = importControl.getentryType();
        customFieldMap = importControl.getcustomFieldMap();
        
        System.out.println(entryType);
        
        for (Map.Entry< BibtexFieldType,String > entry : fieldTypeMap.entrySet()) {
            System.out.println("Key: "+entry.getKey() + "--- Value: " + entry.getValue());
            switch (entry.getKey().toString()){
                case "author":
                    assertEquals(entry.getValue(), "Dirk V. Arnold and Hans-georg Beyer");
                    assertEquals(document.getField(entry.getKey()), entry.getValue());
                    break;
                case "year":
                    assertEquals(entry.getValue(), "2006");
                    assertEquals(document.getField(entry.getKey()), entry.getValue());
                    break;
                case "journal":
                    assertEquals(entry.getValue(), "Comput");
                    assertEquals(document.getField(entry.getKey()), entry.getValue());
                    break;
                case "volume":
                    assertEquals(entry.getValue(), "14");
                    assertEquals(document.getField(entry.getKey()), entry.getValue());
                    break;
                case "pages":
                    assertEquals(entry.getValue(), "291--308");
                    assertEquals(document.getField(entry.getKey()), entry.getValue());
                    break;
                case "title":
                    assertEquals(entry.getValue(), "Optimum Tracking with Evolution Strategies");
                    assertEquals(document.getField(entry.getKey()), entry.getValue());
                    break;
                case "key":
                    assertEquals(entry.getValue(), "Arnold06optimumtracking");
                    assertEquals(document.getField(entry.getKey()), entry.getValue());
                    break;
                default:
                    fail("unknown key");
            }
        }

    }
}
