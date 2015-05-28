package de.cpdfjarrtm.pdfborx.gui.controller;

import de.cpdfjarrtm.pdfborx.PdfBorx;
import de.cpdfjarrtm.pdfborx.export.bibtex.BibTexSingleDocumentEntry;
import de.cpdfjarrtm.pdfborx.gui.data.Exportpath;
import de.cpdfjarrtm.pdfborx.gui.data.Pdflist;
import de.cpdfjarrtm.pdfborx.gui.model.PdfComboBoxModel;
import de.cpdfjarrtm.pdfborx.gui.model.ShowPdfModel;
import de.cpdfjarrtm.pdfborx.gui.model.Metamodel;
import de.cpdfjarrtm.pdfborx.gui.model.ShowSearchedPdfModel;
import de.cpdfjarrtm.pdfborx.gui.model.Tableresizer;
import de.cpdfjarrtm.pdfborx.index.SearchResult;
import de.cpdfjarrtm.pdfborx.onlinefetcher.FetcherImpl;
import de.cpdfjarrtm.pdfborx.parser.biblio.Citation;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexFieldType;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentFieldType;
import de.cpdfjarrtm.pdfborx.util.Language;
import gui.GUI;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kog.suche.KogSucheConnector;

import org.swixml.SwingEngine;

/**
 * @author Alexander, Philipp, Patrick, Lorenz
 *
 * Controls the Actions of the GUI.
 *
 */
public class GuiController extends WindowAdapter {

    private static final String DESCRIPTOR = "xml/Mainframe2.xml";

    private PdfBorx app;

    private PdfComboBoxModel comboBox;
    private ShowPdfModel foundModel;
    private ShowSearchedPdfModel searchModel;
    private Pdflist fileChoose;
    private SwingEngine swix;
    private JComboBox<String> kategorie;
    private JTable tempList;
    private JList metaList;
    private JScrollPane foundPdf;
    private JScrollPane infoPdf;
    private JFrame main;
    private Exportpath exportDirChoose;
    private JTable metaview;
    private JTextArea bibview;
    private Metamodel metaviewModel;
    private ListSelectionListener mauslistener;
    private KeywordGuiController keyWordView;
    private JList keywordList;
    private JTextField searchField;
    private ConfirmMetadataChangeController confirmChange;
    private JTextArea abstractTextArea;
    private HandbuchController handbuch;
    private JTextArea litrefTextArea;
    
    private ImageIcon icon;

    /**
     *
     * @param app
     */
    public GuiController() {
        this.app = PdfBorx.getInstance();
        comboBox = new PdfComboBoxModel();
        kategorie = new JComboBox<>();
        swix = new SwingEngine(this);
        fileChoose = new Pdflist();
        foundModel = new ShowPdfModel(app.getDataController());
        searchModel = new ShowSearchedPdfModel();
        tempList = new JTable(foundModel);
        metaList = new JList();
        foundPdf = new JScrollPane(tempList);
        metaviewModel = new Metamodel();
        metaview = new JTable();
        bibview = new JTextArea();
        infoPdf = new JScrollPane(metaview);
        infoPdf.getViewport().add(metaview);
        keyWordView = new KeywordGuiController();
        keywordList = new JList();
        searchField = new JTextField();
        confirmChange = new ConfirmMetadataChangeController();
        abstractTextArea = new JTextArea();
        handbuch = new HandbuchController();
        litrefTextArea = new JTextArea();

        mauslistener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!tempList.getSelectionModel().isSelectionEmpty()){
                    metaviewModel.loadDocument(
                            app.getDataController().getDocument(
                                    (long) tempList.getValueAt(tempList.getSelectedRow(), 0))
                    );
                }
                metaview = new Tableresizer().resize(metaview);
                infoPdf.revalidate();
                //TODO
                //BibTexsingleBookEntry tempbib = new BibTexsingleBookEntry();
                BibTexSingleDocumentEntry tempbib = new BibTexSingleDocumentEntry();
                bibview.setEditable(false);
                //bibview.setText(tempbib.getAttributes(Integer.parseInt(tempList.getValueAt(tempList.getSelectedRow(), 0).toString()), app.getDataController().getDocument(
                //        (long) tempList.getValueAt(tempList.getSelectedRow(), 0))));
                if(!tempList.getSelectionModel().isSelectionEmpty()) {
                    bibview.setText(tempbib.getcompleteEntry(app.getDataController().getDocument(
                            (long) tempList.getValueAt(tempList.getSelectedRow(), 0))));
                    infoPdf.revalidate();
                }
                try {
                    keywordList.setModel(keyWordView.startShowKeyword());
                } catch (Exception ex) {
                    Logger.getLogger(GuiController.class.getName()).log(Level.SEVERE, null, ex);
                }
                keywordList.revalidate();
                if(!tempList.getSelectionModel().isSelectionEmpty()){
                    abstractTextArea.setText(app.getDataController().getDocument((long) tempList.getValueAt(tempList.getSelectedRow(), 0)).getAbstract());
                    abstractTextArea.revalidate();
                }
                
                if(!tempList.getSelectionModel().isSelectionEmpty()){
                    Collection<Citation> cits = app.getDataController().getDocument(
                            (long) tempList.getValueAt(tempList.getSelectedRow(), 0))
                            .getCitations(); 
                    String cittext = "";
                    int i = 1;
                    for (Citation c : cits) {
                        cittext += "["+i+"]"+c.getcompleteCit()+"\n";
                        i++;
                    }
                    litrefTextArea.setText(cittext);
                    litrefTextArea.revalidate();
                }
                
            }
        };

        main = new JFrame();
        exportDirChoose = new Exportpath();

    }

    public JTable getMetaview() {
        return metaview;
    }

    public JTable getTempList() {
        return tempList;
    }

    public Metamodel getMetaviewModel() {
        return metaviewModel;
    }

    public JScrollPane getInfoPdf() {
        return infoPdf;
    }
    
    /**
     * Method to start and render the GUI
     *
     * @throws java.lang.Exception
     *
     */
    public void start() throws Exception {
        swix.render(DESCRIPTOR).setVisible(true);
        kategorie.setModel(comboBox);
        tempList.setModel(foundModel);
        tempList.getSelectionModel().addListSelectionListener(mauslistener);
        metaview.setModel(metaviewModel);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
        main.setIconImage(icon.getImage());
    }

    public Action confirmMetadataChange = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (metaview.getCellEditor() == null) return;
            metaview.getCellEditor().stopCellEditing();
            confirmChange.startGUI();
        }    
    };
    
    public Action onlineMetadata = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                // DocumentPdf doc = app.getGuiController().getSelectedDocument();
                FetcherImpl fetcher;
                List<Long> pdfs = app.getGuiController().getSelectedDocumentList();

                if (pdfs.size() != 1) {
                    System.out.println("Kein Dokument ausgewählt");
                    return;
                }

                DocumentPdf doc = app.getDataController().getDocument(pdfs.get(0));
                String page1 = doc.getText()[0];
                String title = doc.getField(BibtexFieldType.TITLE);
                String abstractFull = null,author = null ,year = null,conference = null;

                if (title!=null && title.contains(" ")) {
                    fetcher = new FetcherImpl(title, NAME);
                } else { 
                    fetcher = new FetcherImpl(page1);
               }

                HashMap metaData = fetcher.getMetaData();
                
                if (metaData.containsKey("Abstract")) {                
                abstractFull = metaData.get("Abstract").toString();
                }
                if (metaData.containsKey("Author")) {     
                author = metaData.get("Author").toString();
                }
                if (metaData.containsKey("Year")) { 
                year = metaData.get("Year").toString();
                }
                if (metaData.containsKey("Conference")) { 
                conference = metaData.get("Conference").toString();
                }
                title = metaData.get("Title").toString();

                if (abstractFull != null) {
                    doc.setAbstract(abstractFull);
                    abstractTextArea.setWrapStyleWord(true);
                    abstractTextArea.setText(abstractFull.replace(".", ".\n"));
                }
                if (author != null) {
                    doc.setField(BibtexFieldType.AUTHOR, author);                    
                }
                                   
                    doc.setField(BibtexFieldType.TITLE, title);
                
                if (year != null) {
                    doc.setField(BibtexFieldType.YEAR, year);
                }
                //doc.setField(BibtexFieldType.CONFERENCE, conference );
                
                System.out.println(doc.getField(BibtexFieldType.AUTHOR));

            } catch (IOException ex) {
                Logger.getLogger(GuiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    /**
     * @author Patrick Method to get Dimensions of the Screen
     * @return Parent
     */
    public Point getPositionOfMainWindow() {
        return main.getLocationOnScreen();
    }

    /**
     * Opens Frame to display keywords
     */
    public Action showKeywords = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            app.startKeywordGUI();
        }
    };
    
    public Action importbibtex = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            app.getImportController().importBibTex(fileChoose.getbib(), getSelectedDocument());
        }
    };

    /**
     * Opens Frame to edit and display Metadata
     */
    public Action showEditMetadata = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            app.startMetadataGUI();
        }
    };

    /**
     * Opens file name dialogue and imports selected PDFs
     */
    public Action pdfImport = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            app.getLoadController().loadDocument(fileChoose.getPDF());
            foundModel.updateList();
            foundPdf.revalidate();
        }
    };

    /**
     * Opens file name dialogue and imports all PDFs of selected Folder
     */
    public Action importDir = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<File> files = fileChoose.getDir();
            if (importDir != null) {
                app.getLoadController().loadDocument(files);
                foundModel.updateList();
                foundPdf.revalidate();
            }
        }
    };

    /**
     * Opens file name dialogue and exports selected PDF from JList
     */
    public Action pdfExport = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("PDF-Export TODO");
        }
    };

    /**
     * Opens file name dialogue and exports Bibtex from selected PDF of JList
     */
    public Action bibexport = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String exportDir = exportDirChoose.getpath("bib");
            if (exportDir != null) {
                List<Long> content = getSelectedDocumentList();
                app.getExportController().exportBibTex(
                        exportDir,
                        app.getDataController().getDocuments(content));
            }
        }
    };

    /**
     * Search for terms in the search box.
     */
    public Action searchWord = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchModel.clear();
            String query = searchField.getText();
            String field = (String)comboBox.getSelectedItem();
            if (query == null || query.isEmpty() || field == null || field.isEmpty()) {
                return;
            }
            SearchResult res = app.getIndex().search(field, searchField.getText(), Language.EN, 20);
            res.sort();
            Collections.reverse(res);
            for (SearchResult.ResultEntry t : res) {
                if (t.getId() == -1) continue;
                DocumentPdf doc = app.getDataController().getDocument(t.getId());
                if (doc == null) continue;
                System.out.println(
                        t.getId() + " :: " +
                        t.getScore() + " :: " +
                        app.getDataController().getDocument(t.getId()).getField(BibtexFieldType.TITLE));
                searchModel.addRow(new Object[] { t.getId(), app.getDataController().getDocument(t.getId()).getFilename(), t.getScore()});
            }
            tempList.setModel(searchModel);
            tempList.repaint();
            tempList.revalidate();
        }
    };
    
    public Action newSearch = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            searchModel.clear();
            tempList.setModel(foundModel);
            tempList.repaint();
            tempList.revalidate();
        }
    };
    
    public Action showAllPdfs = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchModel.clear();
            tempList.setModel(foundModel);
            tempList.repaint();
            tempList.revalidate();
        }
    };

    /**
     * Get documents similar to the currently selected one.
     */
    public Action similarDocuments = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("SIMILARDOCUMENTS");
            DocumentPdf doc = getSelectedDocument();
            if (doc == null) return;
            searchModel.clear();
            SearchResult res = app.getIndex().similar(doc, DocumentFieldType.FULL_TEXT_TYPE.name(), 20);
            res.sort();
            Collections.reverse(res);
            for (SearchResult.ResultEntry t : res) {
                System.out.println(
                        t.getId() + " :: " +
                        t.getScore() + " :: " +
                        app.getDataController().getDocument(t.getId()).getField(BibtexFieldType.TITLE));
                searchModel.addRow(new Object[] { t.getId(), app.getDataController().getDocument(t.getId()).getFilename(), t.getScore()});
            }
            tempList.setModel(searchModel);
            tempList.repaint();
            tempList.revalidate();
        }
    };
    
    /**
     * Get documents similar to the currently selected one, similar by bibliography.
     */
    public Action similarDocumentsCitation = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DocumentPdf doc = getSelectedDocument();
            if (doc == null) return;
            searchModel.clear();
            SearchResult res = app.getIndex().similar(doc, DocumentFieldType.CITATION_TYPE.name(), 20);
            res.sort();
            Collections.reverse(res);
            for (SearchResult.ResultEntry t : res) {
                System.out.println(
                        t.getId() + " :: " +
                        t.getScore() + " :: " +
                        app.getDataController().getDocument(t.getId()).getField(BibtexFieldType.TITLE));
                searchModel.addRow(new Object[] { t.getId(), app.getDataController().getDocument(t.getId()).getFilename(), t.getScore()});
            }
            tempList.setModel(searchModel);
            tempList.repaint();
            tempList.revalidate();
        }
    };

    public Action options = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    };

    public Action about = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(main,
                    "PdfBorx\n2014\nDingsbums",
                    "Über PdfBorx",
                    JOptionPane.INFORMATION_MESSAGE,
                    icon);
        }
    };

    public Action manual = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            handbuch.startGUI();
        }
    };

    public Action exit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            app.exit();
        }
    };
    
    public Action kogSearchStart = new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
    	  KogSucheConnector kogSuche = new KogSucheConnector();
    	  kogSuche.setSendPDFs(app.getDataController().getAllDocuments());
    	  kogSuche.sendPDFs();
    	  kogSuche.startKogSuche();
      }
    };


    public List<Long> getSelectedDocumentList() {
        int[] rows = tempList.getSelectedRows();
        List<Long> res = new ArrayList<>();
        for (int i = 0; i < rows.length; i++) {
            res.add((long) (tempList.getValueAt(rows[i], 0)));
        }

        return res;
    }

    public DocumentPdf getSelectedDocument() {
        List<Long> docs = getSelectedDocumentList();
        if (docs.size() == 1) {
            return app.getDataController().getDocument((long) (tempList.getValueAt(tempList.getSelectedRow(), 0)));
        }
        return null;
    }

}
