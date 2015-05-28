package de.cpdfjarrtm.pdfborx.gui.model;

import java.util.Map;

import javax.swing.table.DefaultTableModel;

import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.cpdfjarrtm.pdfborx.pdf.document.BibtexFieldType;

public class Metamodel extends DefaultTableModel {
	public Map<BibtexFieldType, String> penis;
	private DocumentPdf doc = null;
	
	public Metamodel(){
            this.addColumn("Feld");
            this.addColumn("Inhalt");
	}
	
        public void loadDocument(DocumentPdf doc) {
            if (doc != this.doc) {
                clear();
                this.doc = doc;
                for (BibtexFieldType t : doc.getFields()) {
                        this.addRow(new String[] {t.toString(), doc.getField(t)});
                }
            }
        }	
        
        public void clear() {
            for (int i = this.getRowCount()-1; i >= 0; i--) {
                this.removeRow(i);
            }
        }
}
