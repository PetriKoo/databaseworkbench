package databaseworkbench.celleditors;

import databaseworkbench.MainWindow;
import databaseworkbench.TableModel;
import databaseworkbench.beans.TableBean;
import databaseworkbench.views.ViewFKeys;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class TableBeanCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private TableBean selectedTableBean = null;
    
    @Override
    public Object getCellEditorValue() {
        return selectedTableBean;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        TableModel tableModel = (TableModel) table.getModel();
        JComboBox<TableBean> comboBox = new JComboBox<>();
        DefaultComboBoxModel<TableBean> model = new DefaultComboBoxModel<>();
        comboBox.setModel( model );            
        ArrayList<TableBean> list;
        list = new ArrayList(MainWindow.getInstance().getTableBeans());
        if (column == ViewFKeys.ForeignTableColumn) {
            
            TableBean notThisTable = MainWindow.getInstance().getTableBean( tableModel.getTableName() );
            list.remove( notThisTable );
            
        }
        model.addAll(list);
    
                    
        
        
        if (selectedTableBean != null) comboBox.setSelectedItem(selectedTableBean);
        comboBox.addActionListener( this );
        if (isSelected) {
            comboBox.setBackground( table.getSelectionBackground() );
        } else {
            comboBox.setBackground( table.getSelectionForeground() );
        }
        return comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<TableBean> comboBox = (JComboBox<TableBean>) e.getSource();
        this.selectedTableBean = (TableBean) comboBox.getSelectedItem();
    }
    
}
