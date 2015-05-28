package de.cpdfjarrtm.pdfborx.export.bibtex;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import de.cpdfjarrtm.pdfborx.data.DataController;
import de.cpdfjarrtm.pdfborx.data.DataControllerImpl;
import de.cpdfjarrtm.pdfborx.data.DocumentModelImpl;
import de.cpdfjarrtm.pdfborx.imports.ImportController;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.cpdfjarrtm.pdfborx.pdf.load.LoadController;

public class BibTexSingleDocumentEntryTest {

    public BibTexSingleDocumentEntry entryGenerator;
    public DocumentPdf document;
    public LoadController load;
    public ImportController importControl;

    @Before
    public void setUp() throws Exception {
        entryGenerator = new BibTexSingleDocumentEntry();
        document = null;
        load = null;
        File documentFile = new File("code/test/testdata/arnold2003otes.pdf");
        DataController data = new DataControllerImpl(new DocumentModelImpl());
        load = new LoadController(data);
        document = load.loadDocument(documentFile);
        
        File bibtexImportfile = new File("code/test/testdata/importBibTexTestFile.bib");
        importControl = new ImportController();
        importControl.importBibTex(bibtexImportfile, document);
    }

    @Test
    public void testgetcompleteEntry() {
        assertNull(entryGenerator.getcompleteEntry(null));
        String expectedEntry = "@ARTICLE{Arnold06optimumtracking,\n"
                + "\tauthor = {Dirk V. Arnold and Hans-georg Beyer},\n"
                + "\tjournal = {Comput},\n"
                + "\tpages = {291--308},\n"
                + "\ttitle = {Optimum Tracking with Evolution Strategies},\n"
                + "\tvolume = {14},\n"
                + "\tyear = {2006}\n"
                + "}\n\n";
        String completeEntry = entryGenerator.getcompleteEntry(document);
        assertEquals(expectedEntry, completeEntry);
        System.out.println(completeEntry);
    }

}
