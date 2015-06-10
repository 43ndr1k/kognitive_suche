package de.cpdfjarrtm.pdfborx;

import de.cpdfjarrtm.pdfborx.data.DataController;
import de.cpdfjarrtm.pdfborx.data.DataControllerImpl;
import de.cpdfjarrtm.pdfborx.data.DocumentModel;
import de.cpdfjarrtm.pdfborx.data.DocumentModelDatabase;
import de.cpdfjarrtm.pdfborx.data.DocumentModelImpl;
import de.cpdfjarrtm.pdfborx.db.controller.DatabaseController;
import de.cpdfjarrtm.pdfborx.export.ExportController;
import de.cpdfjarrtm.pdfborx.gui.controller.GuiController;
import de.cpdfjarrtm.pdfborx.gui.controller.KeywordGuiController;
import de.cpdfjarrtm.pdfborx.gui.controller.MetadataGuiController;
import de.cpdfjarrtm.pdfborx.imports.ImportController;
import de.cpdfjarrtm.pdfborx.index.Index;
import de.cpdfjarrtm.pdfborx.index.IndexSettings;
import de.cpdfjarrtm.pdfborx.keyword.KeywordGenerator;
import de.cpdfjarrtm.pdfborx.keyword.KeywordGeneratorEnDe;
import de.cpdfjarrtm.pdfborx.parser.biblio.BiblioParserController;
import de.cpdfjarrtm.pdfborx.pdf.load.LoadController;
import de.cpdfjarrtm.pdfborx.util.Loader;
import de.cpdfjarrtm.pdfborx.util.Settings;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * PdfBorx.
 * 
 * Main controller. Creates controllers, models and views.
 * 
 * @author  Philipp Kleinhenz, Patrick Jeckel
 */
public class PdfBorx {
    private GuiController gui;
    private KeywordGuiController keywordGUI;
    private MetadataGuiController metadataGUI;
    private LoadController load;
    private ExportController export;
    private KeywordGenerator keyGen;
    private DataController data;
    private Index index;
    private BiblioParserController biblio;
    private ImportController importcontrol;
    private DatabaseController dbcontroller;
    
    private static final PdfBorx instance = new PdfBorx();
    
    static {
        instance.init();
    }
    
    private PdfBorx() {}
    
    public static PdfBorx getInstance() {
        return instance;
    }

    private void init() {
        dbcontroller = new DatabaseController(this);
        DocumentModel model = new DocumentModelDatabase(this);
        data = new DataControllerImpl(model);
        load = new LoadController(data);
        export = new ExportController();
        gui = new GuiController();
        keywordGUI = new KeywordGuiController();
        metadataGUI = new MetadataGuiController();
        keyGen = new KeywordGeneratorEnDe();        
        index = new Index(setUpIndex());
        biblio = new BiblioParserController();
        ((DocumentModelDatabase) model).fill();
        
        importcontrol = new ImportController();

    }
    
    public KeywordGenerator getKeywordGenerator() {
        return keyGen;
    }
    
    /**
     * Start the program and show the gui.
     */
    public void start() {
        try {
            gui.start();
        } catch (Exception ex) {
            Logger.getLogger(PdfBorx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Start the KeywordGUI
     */
    public void startKeywordGUI() {
        try {
            keywordGUI.startShowKeyword();
        } catch (Exception ex) {
            Logger.getLogger(PdfBorx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Start the MetadataGUI
     */
    public void startMetadataGUI() {
        try {
            metadataGUI.startShowEditMetadata();
        } catch (Exception ex) {
            Logger.getLogger(PdfBorx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Gets the GUI controller.
     * 
     * @return  The GUI controller.
     */
    public GuiController getGuiController() {
        return gui;
    }
    
    /**
     * Gets the controller for importing documents.
     * 
     * @return  The load controller.
     */
    public LoadController getLoadController() {
        return load;
    }
    
    /**
     * Gets the controller for exporting documents.
     * 
     * @return  The export controller.
     */
    public ExportController getExportController() {
        return export;
    }
    
    /**
     * Gets the document storage.
     * 
     * @return  The data controller.
     */
    public DataController getDataController() {
        return data;
    }
    
    /**
     * Get the search index.
     * 
     * @return  The search index.
     */
    public Index getIndex() {
        return index;
    }
    
    public BiblioParserController getBiblioParser() {
        return biblio;
    }
    
    /**
     * Gets the controller for importing BibTex documents.
     * 
     * @return  The BibTex import controller.
     */
    public ImportController getImportController() {
        return importcontrol;
    }
    
    /**
     * Stop the program.
     */
    public void exit() {
        // datenbank committen etc...
        System.exit(0);
    }

    /**
     * Set up settings required for index operations.
     * 
     * @return  Index settings.
     */
    private IndexSettings setUpIndex() {
        IndexSettings set = new IndexSettings();
        set.setIndexPath(Settings.indexDirectory);
        set.setStopsetDe(Loader.loadAsCharArraySet(Settings.germanStopList));
        set.setDictDe(Settings.germanDic);
        set.setAffDe(Settings.germanAff);
        
        set.setStopsetEn(Loader.loadAsCharArraySet(Settings.englishStopList));
        set.setDictEn(Settings.englishDic);
        set.setAffEn(Settings.englishAff);

        return set;
    }

    public DatabaseController getDatabaseController() {
        return dbcontroller;
    }
}
