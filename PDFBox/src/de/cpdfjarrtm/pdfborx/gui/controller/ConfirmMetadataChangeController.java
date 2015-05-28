/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.cpdfjarrtm.pdfborx.gui.controller;

import de.cpdfjarrtm.pdfborx.PdfBorx;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexFieldType;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import org.swixml.SwingEngine;

/**
 *
 * @author Patrick Jeckel
 */
public class ConfirmMetadataChangeController extends WindowAdapter {
        private static final String CONFMETACHANGE = "xml/ConfirmMetaChangeGui.xml";
        private final PdfBorx app;
        
        private SwingEngine swixMetaChange;
        private JFrame confirmMetadata;
        
        public ConfirmMetadataChangeController(){
            this.app = PdfBorx.getInstance();
            swixMetaChange = new SwingEngine(this);
            confirmMetadata = new JFrame();

        }
        
        public void startGUI(){
            try {
                swixMetaChange.render(CONFMETACHANGE).setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(ConfirmMetadataChangeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            confirmMetadata.setAlwaysOnTop(true);
            confirmMetadata.setLocation((app.getGuiController().getPositionOfMainWindow()));
            confirmMetadata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        
        public Action discardMetadataChange = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                app.getGuiController().getMetaviewModel().loadDocument(
                            app.getDataController().getDocument(
                                    (long)app.getGuiController().getTempList().getValueAt(app.getGuiController().getTempList().getSelectedRow(), 0)));
                app.getGuiController().getMetaview().revalidate();
                app.getGuiController().getMetaview().repaint();
                app.getGuiController().getInfoPdf().revalidate();
                app.getGuiController().getInfoPdf().repaint();
                confirmMetadata.dispose();
            }
        };
        
        public Action acceptMetadataChange = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {                
                for(int rowCount=0; rowCount<(app.getGuiController().getMetaview().getRowCount()); rowCount++){
                    
                    //System.out.println("Row" + rowCount);
                    
                    String fieldType = app.getGuiController().getMetaview().getValueAt(rowCount, 0).toString();
                    String selectedMetaRowString = app.getGuiController().getMetaview().getValueAt(rowCount, 1).toString();
                    
                    for (BibtexFieldType t : BibtexFieldType.values()) {
                        if (t.name().equalsIgnoreCase(fieldType)) {
                            app.getGuiController().getSelectedDocument().setField(BibtexFieldType.valueOf(fieldType.toUpperCase()), selectedMetaRowString);
                            break;
                        }
                    }

                }   
                app.getGuiController().getMetaview().revalidate();
                app.getGuiController().getInfoPdf().revalidate();

                confirmMetadata.dispose();
            }
        };
}
