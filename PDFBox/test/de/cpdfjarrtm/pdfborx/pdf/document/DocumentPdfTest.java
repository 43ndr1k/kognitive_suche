package de.cpdfjarrtm.pdfborx.pdf.document;

import de.cpdfjarrtm.pdfborx.data.DocumentModelImpl;
import de.cpdfjarrtm.pdfborx.pdf.load.LoadController;
import java.io.File;
import java.nio.file.FileSystems;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * DocumentPdfTest.
 * 
 * @author  Philipp Kleinhenz
 * @TODO wieder brauchbar machen
 */
public class DocumentPdfTest {
//    public DocumentPdf doc;
//    public Map<File, DocumentPdf> docs;
//    public LoadController load;
//    public Set<String> metaKeys;
//    public String page1;
//    
//    public static final String sep = FileSystems.getDefault().getSeparator();
//    
//    public String path;
//    
//    public DocumentPdfTest() {
//        doc = null;
//        load = null;
//        path = System.getProperty("user.dir") + sep + ".." + sep + "pdfborx"
//                + sep + "code" + sep + "test" + sep + "testdata"
//                + sep + "arnold2003otes.pdf";
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//        load = new LoadController(new DocumentModelImpl());
//        metaKeys = new HashSet<>();
//        metaKeys.add("ModDate");
//        metaKeys.add("Producer");
//        metaKeys.add("CreationDate");
//        metaKeys.add("Creator");
//        metaKeys.add("Title");
//        page1 = "Optimum Tracking with Evolution Strategies\n" +
//"Dirk V. Arnold dirk@cs.dal.ca\n" +
//"Faculty of Computer Science, Dalhousie University, Halifax, Nova Scotia, Canada\n" +
//"B3H 1W5\n" +
//"Hans-Georg Beyer hans-georg.beyer@fh-vorarlberg.ac.at\n" +
//"Department of Computer Science, VorarlbergUniversity of Applied Sciences, Achstr. 1,\n" +
//"A-6850 Dornbirn, Austria\n" +
//"Abstract\n" +
//"Evolutionary algorithms are frequently applied to dynamic optimization problems in\n" +
//"which the objective varies with time. It is desirable to gain an improved understanding\n" +
//"of the influence of different genetic operators and of the parameters of a strategy on its\n" +
//"tracking performance. An approach that has proven useful in the past is to mathemat-\n" +
//"ically analyze the strategy’s behavior in simple, idealized environments. The present\n" +
//"paper investigates the performance of a multiparent evolution strategy that employs\n" +
//"cumulative step length adaptation for an optimization task in which the target moves\n" +
//"linearly with uniform speed. Scaling laws that quite accurately describe the behavior\n" +
//"of the strategy and that greatly contribute to its understanding are derived. It is shown\n" +
//"that in contrast to previously obtained results for a randomly moving target, cumula-\n" +
//"tive step length adaptation fails to achieve optimal step lengths if the target moves in a\n" +
//"linear fashion. Implications for the choice of population size parameters are discussed.\n" +
//"Keywords\n" +
//"Genetic and evolutionary computation, evolution strategies, cumulative step length\n" +
//"adaptation, tracking problem, dynamic optimization.\n" +
//"1 Introduction\n" +
//"Evolutionary algorithms are nature inspired heuristics for search and optimization that\n" +
//"model the iterated interplay of variation and selection in a population of candidate\n" +
//"solutions. Comprehensive treatments of the subject area can be found in (Ba¨ck, 1996;\n" +
//"Goldberg, 1989; Mitchell, 1996; Rechenberg, 1994). Reasons for the widespread use of\n" +
//"evolutionary algorithms include their often observed robustness, the ease with which\n" +
//"the underlying paradigm is understood and implemented, and their wide applicability.\n" +
//"Areas of application today include management, control, design, scheduling, pattern\n" +
//"recognition, and decision making.\n" +
//"In recent years, a fair amount of theoretical investigation has contributed substan-\n" +
//"tially to our understanding of the dynamics of evolutionary search strategies on a va-\n" +
//"riety of problem classes. For an overview, see (Beyer et al., 2002). However, most of\n" +
//"the problem classes that have been considered are of a static nature. In contrast, many\n" +
//"problems encountered in the computational, engineering, and biological sciences are\n" +
//"dynamic in that the objective is not constant but varies with time. Instances of dynamic\n" +
//"optimization problems arise for example in the context of online job scheduling, where\n" +
//"new jobs arrive in the course of the optimization. In the engineering sciences, many\n" +
//"control problems are of an inherently dynamic nature. An extensive list of references\n" +
//"c©200X by the Massachusetts Institute of Technology Evolutionary Computation x(x): xxx-xxx\n";
//    }
//    
//    @After
//    public void tearDown() {
//        doc = null;
//    }
//
//    @Test
//    public void testLoad() {
//        System.out.println("load");
//        doc = load.loadDocument(new File(path));
//        assertFalse(load.switchModel(LoadController.MODEL.DOCUMENTPDFBOX));
//        assertTrue(load.switchModel(LoadController.MODEL.DOCUMENTDATA));
//        File file1 = new File(path);
//        File file2 = new File(path);
//        docs = load.loadDocument(new File[] {file1, file2});
//        assertEquals(doc.getFilename(), docs.get(file1).getFilename());
//    }
//    
//    @Test
//    public void testDocumentData() {
//        doc = load.loadDocument(new File(path));
//        assertFalse(load.switchModel(LoadController.MODEL.DOCUMENTPDFBOX));
//        assertTrue(load.switchModel(LoadController.MODEL.DOCUMENTDATA));
//        testData();
//        File file1 = new File(path);
//        File file2 = new File(path);
//        docs = load.loadDocument(new File[] {file1, file2});
//        assertEquals(doc.getFilename(), docs.get(file1).getFilename());
//        doc = (DocumentData)docs.get(file2);
//        testData();
//    }
//    
//    @Test
//    public void testDocumentLucene() {
//        assertTrue(load.switchModel(LoadController.MODEL.DOCUMENTLUCENE));
//        doc = load.loadDocument(new File(path));
//        testData();
//    }
//    
//    public void testData() {
//        System.out.println("getTitle()");
//        assertEquals("main.dvi", doc.getTitle());
//        System.out.println("getAuthor()");
//        assertEquals(null, doc.getAuthor());
//        System.out.println("getCreator()");
//        assertEquals("dvips(k) 5.92b Copyright 2002 Radical Eye Software", doc.getCreator());
//        System.out.println("getFilename()");
//        assertEquals(path, doc.getFilename());
//        System.out.println("getKeywords()");
//        assertEquals(null, doc.getKeywords());
//        System.out.println("getLanguage()");
//        assertEquals(null, doc.getLanguage());
//        System.out.println("getNumberOfPages()");
//        assertEquals(18, doc.getNumberOfPages());
//        System.out.println("getProducer()");
//        assertEquals("ESP Ghostscript 8.15", doc.getProducer());
//        System.out.println("getSubject()");
//        assertEquals(null, doc.getSubject());
//        System.out.println("getTitle()");
//        assertEquals("main.dvi", doc.getTitle());
//        System.out.println("getVersion()");
//        assertEquals(1.2, doc.getVersion(), 0.04);
//        System.out.println("isEncrypted()");
//        assertEquals(false, doc.isEncrypted());
//        System.out.println("getMetadataKeys()");
//        for (String e : metaKeys) {
//            if (!e.startsWith("FILE_")) {
//                assertTrue(doc.getMetadataKeys().contains(e));
//            }
//        }
//        System.out.println("getText(1)");
//        assertEquals(page1, doc.getText(1));
//        assertEquals(page1, doc.getText()[0]);
//        assertNull(doc.getText(-1));
//        assertNull(doc.getText(doc.getNumberOfPages()+1));
//        System.out.println("getCustomMetadataValue(\"ModDate\")");
//        assertEquals("D:20051115144437", doc.getCustomMetadataValue("ModDate"));
//        System.out.println("getCustomMetadataValue(\"CreationDate\")");
//        assertEquals("D:20051115144437", doc.getCustomMetadataValue("CreationDate"));
//    }

}
