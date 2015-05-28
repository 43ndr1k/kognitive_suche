package de.cpdfjarrtm.pdfborx.gui.controller;


import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;

import org.swixml.SwingEngine;

import de.cpdfjarrtm.pdfborx.PdfBorx;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexFieldType;

import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;

import javax.swing.JFrame;
import javax.swing.JTextField;



/**
 * MetadataGuiController.
 * 
 * @author Patrick Jeckel
 */
public class MetadataGuiController extends WindowAdapter {
    
    private static final String DESCRIPTOR = "xml/ShowEditMetadata.xml";
    
    private final PdfBorx app;
    
    private SwingEngine swixMetadata;
    private JFrame showEditMetadata;
    private JTextField pdfautor;
    private JTextField pdftitel;
    private JTextField pdfversion;
    
    /**
     * 
     */
    public MetadataGuiController(){
        this.app = PdfBorx.getInstance();
        swixMetadata = new SwingEngine(this);
        showEditMetadata = new JFrame();
        pdfautor = new JTextField();
        pdftitel = new JTextField();
        pdfversion = new JTextField();
    }
    
    /**
     * Renders the GUI to display Metadata
     * @throws Exception 
     */
    public void startShowEditMetadata() throws Exception {
        swixMetadata.render(DESCRIPTOR).setVisible(true);
        showEditMetadata.setAlwaysOnTop(true);
        showEditMetadata.setLocation((app.getGuiController().getPositionOfMainWindow()));
        showEditMetadata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        List<Long> pdfs = app.getGuiController().getSelectedDocumentList();
        if (pdfs.size() != 1){
            return;
        }
        DocumentPdf doc = app.getDataController().getDocument(pdfs.get(0));
        pdfautor.setText(doc.getField(BibtexFieldType.AUTHOR));
        pdftitel.setText(doc.getField(BibtexFieldType.TITLE));
        pdfversion.setText(doc.getVersion());
    }

    public Action discardMetadata = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showEditMetadata.dispose();
        }
    };
    
    
}
