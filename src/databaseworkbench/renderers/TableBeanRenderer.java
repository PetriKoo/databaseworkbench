package databaseworkbench.renderers;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author petri
 */
public class TableBeanRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TableCellRenderer rend = table.getDefaultRenderer(String.class);        
        DefaultTableCellRenderer comp = (DefaultTableCellRenderer) rend.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (isSelected) {
            
        } else {
            
        }
        return comp;
    }
    
}
