/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.cpdfjarrtm.pdfborx;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zweistecken
 */
public class PdfBorxTest {
    
    public PdfBorxTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testStart() {
        System.out.println("start");
        PdfBorx instance = PdfBorx.getInstance();
        assertNotNull(instance.getDataController());
        assertNotNull(instance.getLoadController());
        assertNotNull(instance.getGuiController());
        assertNotNull(instance.getExportController());
        instance.start();
        assertNotNull(instance.getDataController());
        assertNotNull(instance.getLoadController());
        assertNotNull(instance.getGuiController());
        assertNotNull(instance.getExportController());
    }    
}
