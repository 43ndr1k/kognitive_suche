package de.cpdfjarrtm.pdfborx.db.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatabaseControllerTest {
/*
    @Test
    public void testDatabaseController() {
        DatabaseController testControl = new DatabaseController();
        assertNotNull(testControl);
    }

    @Test
    public void testEstablishConnection() {
        String path = "/home/j/Desktop/Dropbox/";      //Change to an appropriate path on your system.
        DatabaseController testControl = new DatabaseController();  //
        testControl.setPath(path);
        assertTrue(testControl.establishConnection());
        assertTrue(testControl.closeConnection());
        assertEquals("/home/j/Desktop/Dropbox/",testControl.getPath());
    }
    
    @Test
    public void testEstablishConnectionIncorrectPath(){                   
        String path = null;
        DatabaseController testControl = new DatabaseController();
        testControl.setPath(path);
        assertFalse(testControl.establishConnection());
        assertFalse(testControl.closeConnection());
    }

    /*
     * Both methods are not implemented in DatabaseController yet.
     * @Test
    public void testSaveDocumentData() {
        fail("Not yet implemented");
    }

    @Test
    public void testReadDocumentData() {
        fail("Not yet implemented");
    }*/

}
