package de.cpdfjarrtm.pdfborx.gui.model;

import de.cpdfjarrtm.pdfborx.data.DataController;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *  Model for the JList to show imported PDF
 * 
 * @author Philipp
 * 
 */
public class ShowPdfModel extends DefaultTableModel {
    private final DataController documents;
    
    public ShowPdfModel(DataController documents) {
        this.documents = documents;
        this.addColumn("ID");
        this.addColumn("Filename");
    }

    /**
     * updates List of imported PDFs
     */
    public void updateList() {
        for (int i = 0; i < this.getRowCount(); i++) {
            this.removeRow(i);
        }
        Map<Long, DocumentPdf> files = documents.getAllDocuments();
        for (Map.Entry<Long, DocumentPdf> e : files.entrySet()) {
            this.addRow(new Object[] {e.getKey(), e.getValue().getFilename()});
        }
    }

}
