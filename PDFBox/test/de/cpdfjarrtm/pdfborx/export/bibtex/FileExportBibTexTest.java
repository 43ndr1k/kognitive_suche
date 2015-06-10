package de.cpdfjarrtm.pdfborx.export.bibtex;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import de.cpdfjarrtm.pdfborx.data.DataController;
import de.cpdfjarrtm.pdfborx.data.DataControllerImpl;
import de.cpdfjarrtm.pdfborx.data.DocumentModelImpl;
import de.cpdfjarrtm.pdfborx.imports.ImportController;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.cpdfjarrtm.pdfborx.pdf.load.LoadController;

public class FileExportBibTexTest {

    public DocumentPdf document;
    public LoadController load;
    public ImportController importControl;
    public DocumentPdf[] docs = new DocumentPdf[3];
    public BufferedReader bufferedReader;
    public File exportedFile;
    
    @Before
    public void setUp() throws Exception {
        document = null;
        load = null;
        File documentFile = new File("code/test/testdata/arnold2003otes.pdf");
        DataController data = new DataControllerImpl(new DocumentModelImpl());
        load = new LoadController(data);
        document = load.loadDocument(documentFile);
        
        File bibtexImportfile = new File("code/test/testdata/importBibTexTestFile.bib");
        importControl = new ImportController();
        importControl.importBibTex(bibtexImportfile, document);
        
        exportedFile = new File("Bibjunittesttwo.bib");
        docs[0] = null;
        docs[1]  = document;
        docs[2] = document;
    }
    
    @Test
    public void testFileExportBibTex() {
        @SuppressWarnings("unused")
        FileExportBibTex test = new FileExportBibTex();
        
    }

    @Test
    public void testCreateBibFile() {
        FileExportBibTex export = new FileExportBibTex();
        String directory = "Bibjunittestone.bib";
        DocumentPdf[] documentsNull = null;
        assertFalse(export.createBibFile(directory, documentsNull));
        File file = new File(directory);
        assertFalse(file.exists());
    }
    
    @Test
    public void testCreateBibFile2() {
        String expectedContent = "@ARTICLE{Arnold06optimumtracking,"
                + "\tauthor = {Dirk V. Arnold and Hans-georg Beyer},"
                + "\tjournal = {Comput},"
                + "\tpages = {291--308},"
                + "\ttitle = {Optimum Tracking with Evolution Strategies},"
                + "\tvolume = {14},"
                + "\tyear = {2006}"
                + "}"
                + "@ARTICLE{Arnold06optimumtracking,"
                + "\tauthor = {Dirk V. Arnold and Hans-georg Beyer},"
                + "\tjournal = {Comput},"
                + "\tpages = {291--308},"
                + "\ttitle = {Optimum Tracking with Evolution Strategies},"
                + "\tvolume = {14},"
                + "\tyear = {2006}"
                + "}";

        FileExportBibTex export = new FileExportBibTex();
        String directory = "Bibjunittesttwo.bib";
        
        assertTrue(export.createBibFile(directory, docs));
        File file = new File(directory);
        assertTrue(file.exists());
        String readContent = "";
        String line;
        try {
            bufferedReader = new BufferedReader(new FileReader(exportedFile));
            while ((line = bufferedReader.readLine()) != null){
                readContent = readContent + line;
            }
        } catch (IOException e) {
            fail("file was not created");
        }
        assertEquals(expectedContent, readContent);
    }

}
