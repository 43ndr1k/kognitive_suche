package de.cpdfjarrtm.pdfborx.gui.controller;

import java.awt.event.WindowAdapter;
import de.cpdfjarrtm.pdfborx.PdfBorx;
import de.cpdfjarrtm.pdfborx.keyword.Keyword;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 * KeywordGuiController.
 * 
 * @author  Patrik Jeckel
 */
public class KeywordGuiController extends WindowAdapter {
    
    private final PdfBorx app;
    private DefaultListModel<String> list;
    
    /**
     * KeywordGuiController.
     */
    public KeywordGuiController(){
        this.app = PdfBorx.getInstance();
        list = new DefaultListModel<>();
    }
    
    /**
     * Method to start and render KeywordGUI
     * @throws Exception
     * @return keyWordList
     */
    public DefaultListModel startShowKeyword() throws Exception{
        list.clear();
        DocumentPdf pdfs = app.getGuiController().getSelectedDocument();
        if (pdfs != null) {
            List<Keyword> keys = pdfs.getTags();
            for (Keyword e : keys) {
                list.addElement(e.getTerm());
            }
        }
        return list;
    } 
}
