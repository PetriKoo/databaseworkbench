package databaseworkbench;

import databaseworkbench.frames.TableFrame;
import databaseworkbench.views.ViewInterface;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class TableModel extends AbstractTableModel {

    private final TableFrame frame;
    private final ViewInterface view;
    private String sTableName;
        
    private boolean bNewRowMode = false;

    public TableModel(TableFrame frame, ViewInterface view) {
        this.frame = frame;
        this.view = view;

    }
    
    public String getTableName() { return this.sTableName; }
    
    @Override
    public String getColumnName(int c) {
        return view.getLabelText( c );
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
        return view.getClass(c);
    }
    
    @Override
    public int getRowCount() {
        int iRowMax = frame.getBean().getFields().size();
        if (this.bNewRowMode) iRowMax = iRowMax + 1;
        return iRowMax;        
    }

    @Override
    public int getColumnCount() {
        return view.getCount();
    }
    
    public void setNewRowMode( boolean b) { this.bNewRowMode = b; }    
    public boolean isNewRowMode() { return this.bNewRowMode; }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public void setValueAt(Object oValue, int rowIndex, int columnIndex) {
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        this.setValueAt(frame, rowIndex, columnIndex);
        return view.getValue(frame.getBean(), rowIndex, columnIndex);
        
    }
    
}
