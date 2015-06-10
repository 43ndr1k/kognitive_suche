package de.cpdfjarrtm.pdfborx.gui.model;

import de.cpdfjarrtm.pdfborx.PdfBorx;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Patrick Jeckel
 * 
 */
public class ShowSearchedPdfModel extends DefaultTableModel {
    private final PdfBorx app;
    private DocumentPdf doc = null;
    
    public ShowSearchedPdfModel(){
        this.app = PdfBorx.getInstance();
        this.addColumn("ID");
        this.addColumn("Filename");
        this.addColumn("Score");
    }
    
    public void clear() {
        for (int i = this.getRowCount()-1; i >= 0; i--) {
            this.removeRow(i);
        }
    }
}
