package databaseworkbench.renderers;

import databaseworkbench.beans.TableBean;
import databaseworkbench.beans.TableFieldBean;
import databaseworkbench.views.ViewFKeys;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JComboBox;
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
        if (isSelected) {
            JComboBox box = new JComboBox();
            CellComboBoxModel model = new CellComboBoxModel();
            box.setModel( model );
            TableBean foreignTable;
            switch (column) {
                case ViewFKeys.ForeignTableFieldColumn:
                    foreignTable = (TableBean) table.getModel().getValueAt(row, ViewFKeys.ForeignTableColumn);                    
                    ArrayList<TableFieldBean> list = foreignTable.getFields();
                    model.removeAllElements();
                    model.addAll(list);
                    box.updateUI();
                    break;
                    
            }
            return box;
        } else {
            
        }
        return comp;
    }
    
}
