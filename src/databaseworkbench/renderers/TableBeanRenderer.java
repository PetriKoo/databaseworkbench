package databaseworkbench.renderers;

import databaseworkbench.MainWindow;
import databaseworkbench.TableModel;
import databaseworkbench.beans.TableBean;
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
public class TableBeanRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TableCellRenderer rend = table.getDefaultRenderer(String.class);        
        DefaultTableCellRenderer comp = (DefaultTableCellRenderer) rend.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        TableModel tableModel = (TableModel) table.getModel();
        if (isSelected) {
            JComboBox box = new JComboBox();
            CellComboBoxModel model = new CellComboBoxModel();
            box.setModel( model );
            switch (column) {
                case ViewFKeys.LocalTableColumn:
                    
                    break;
                case ViewFKeys.ForeignTableColumn:
                    ArrayList<TableBean> tables = MainWindow.getInstance().getTableBeans();
                    tables.remove( MainWindow.getInstance().getTableBean(tableModel.getTableName()) );
                    model.removeAllElements();
                    model.addAll( tables );
                    box.updateUI();
                    break;
                    
            }
            return box;
        } else {
            
        }
        return comp;
    }
    
}
