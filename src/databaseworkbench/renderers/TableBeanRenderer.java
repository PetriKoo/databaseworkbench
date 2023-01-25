package databaseworkbench.renderers;

import databaseworkbench.BeanInterface;
import databaseworkbench.MainWindow;
import databaseworkbench.TableModel;
import databaseworkbench.beans.TableBean;
import databaseworkbench.views.ViewFKeys;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class TableBeanRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TableCellRenderer rend = table.getDefaultRenderer(String.class);        
        DefaultTableCellRenderer comp = (DefaultTableCellRenderer) rend.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value != null) {
            TableBean tb = (TableBean) value;
            if (tb.getName() != null) comp.setText( tb.getName() );
        }
        
        return comp;
    }
    
}
