/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.cpdfjarrtm.pdfborx.gui.controller;

import de.cpdfjarrtm.pdfborx.PdfBorx;
import java.awt.event.WindowAdapter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.xml.soap.Text;
import org.swixml.SwingEngine;

/**
 *
 * @author lipingu
 */
public class HandbuchController extends WindowAdapter {
    
    private static final String HANDBUCH = "xml/Handbuch.xml";
    private final PdfBorx app;
    private SwingEngine swixHandbuch;
    private JFrame handbuch;
    private JTextPane html;
    
    
    public HandbuchController() {
        this.app = PdfBorx.getInstance();
        swixHandbuch = new SwingEngine(this);
        handbuch = new JFrame();
        html = new JTextPane();
    }
    
    public void startGUI(){
        try {
            swixHandbuch.render(HANDBUCH).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(HandbuchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        handbuch.setAlwaysOnTop(true);
        handbuch.setLocation(app.getGuiController().getPositionOfMainWindow());
        handbuch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
