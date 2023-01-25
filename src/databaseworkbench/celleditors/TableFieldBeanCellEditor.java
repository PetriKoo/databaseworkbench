package databaseworkbench.celleditors;

import databaseworkbench.TableModel;
import databaseworkbench.beans.TableBean;
import databaseworkbench.beans.TableFieldBean;
import databaseworkbench.views.ViewFKeys;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
public class TableFieldBeanCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private TableFieldBean selectedFieldBean = null;
    
    @Override
    public Object getCellEditorValue() {
        
        return selectedFieldBean;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        TableModel tableModel = (TableModel) table.getModel();
        JComboBox<TableFieldBean> comboBox = new JComboBox<>();
        DefaultComboBoxModel<TableFieldBean> model = new DefaultComboBoxModel<>();
        comboBox.setModel( model );            
        ArrayList<TableFieldBean> list = new ArrayList<>();
        System.out.println("local table fields: " + tableModel.getTableBean().getName());
        if (column == ViewFKeys.LocalTableFieldColumn) {
            list = tableModel.getTableBean().getFields();
        }
        if (column == ViewFKeys.ForeignTableFieldColumn) {
            TableBean foreignTable = (TableBean) tableModel.getValueAt(row, ViewFKeys.ForeignTableColumn);
            if (foreignTable != null ) list = foreignTable.getFields();
        }
        System.out.println("list size: " + list.size() );                    
    
                    
        model.addAll(list);
        System.out.println("model size: " + model.getSize() );
        if (selectedFieldBean != null) comboBox.setSelectedItem(selectedFieldBean);
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
        
        JComboBox<TableFieldBean> comboBox = (JComboBox<TableFieldBean>) e.getSource();
        
        
        this.selectedFieldBean = (TableFieldBean) comboBox.getSelectedItem();
        this.stopCellEditing();
    }    
}
