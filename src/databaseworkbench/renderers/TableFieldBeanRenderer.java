package databaseworkbench.renderers;

import databaseworkbench.beans.TableFieldBean;
import databaseworkbench.views.ViewFKeys;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class TableFieldBeanRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TableCellRenderer rend = table.getDefaultRenderer(String.class);        
        DefaultTableCellRenderer comp = (DefaultTableCellRenderer) rend.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        switch (column) {
            case ViewFKeys.LocalTableFieldColumn:
                if (value != null) {
                TableFieldBean tfb = (TableFieldBean) value;
                    if (tfb.getName() != null) comp.setText( tfb.getName() );
                }
                break;
            case ViewFKeys.ForeignTableFieldColumn:
                if (value != null) {
                TableFieldBean tfb = (TableFieldBean) value;
                    if (tfb.getName() != null) comp.setText( tfb.getName() );
                }                
                break;
        }
        
        return comp;
    }
    
}
