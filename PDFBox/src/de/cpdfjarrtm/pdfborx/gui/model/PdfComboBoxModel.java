package de.cpdfjarrtm.pdfborx.gui.model;


import de.cpdfjarrtm.pdfborx.pdf.document.BibtexFieldType;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentFieldType;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author Patrick
 * 
 * Model of drop down menu for category of different PDF
 */

public class PdfComboBoxModel extends DefaultComboBoxModel<String> {
    
    public PdfComboBoxModel(){
        super();
        for (BibtexFieldType t : BibtexFieldType.values()) {
            this.addElement(t.name());
        }
        this.addElement(DocumentFieldType.CITATION_TYPE.name());
        this.addElement(DocumentFieldType.ABSTRACT_TYPE.name());
        this.addElement(DocumentFieldType.FULL_TEXT_TYPE.name());
        this.addElement(DocumentFieldType.USER_TAG_TYPE.name());
        this.setSelectedItem(DocumentFieldType.FULL_TEXT_TYPE.name());
    }
}
