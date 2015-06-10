package de.cpdfjarrtm.pdfborx.gui.model;

import java.awt.Component;
import java.awt.FontMetrics;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author alf
 */
public class Tableresizer {
   
    public JTable resize(JTable table){
        final TableColumnModel columnModel = table.getColumnModel();
        int width=93; // Min width
        for (int column = 0; column < table.getColumnCount(); column++) {
            width = 93; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                 Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width, width);
            }
        columnModel.getColumn(column).setPreferredWidth(width+10);
        }
        table.setColumnModel(columnModel);
        if (width>93)
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        else
            table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            
            
        return table;
    }
    
    
}
