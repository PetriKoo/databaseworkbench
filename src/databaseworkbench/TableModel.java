package databaseworkbench;

import databaseworkbench.beans.TableBean;
import databaseworkbench.frames.TableFrame;
import databaseworkbench.views.ViewInterface;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class TableModel extends AbstractTableModel {

    public static final int EDIT_MODE_NONE = 0;
    public static final int EDIT_MODE_NEW_ROW = 1;
    public static final int EDIT_MODE_ALL = 2;
    
    private final TableFrame frame;
    private final ViewInterface view;
    private final String sTableName;
        
    private boolean bNewRowMode = false;    
    private int edit_mode = TableModel.EDIT_MODE_NONE;
    private String subTableName;

    public TableModel(TableFrame frame, String subtable, ViewInterface view) {
        this.frame = frame;
        this.view = view;
        sTableName = frame.getBean().getName();
        this.subTableName = subtable;
    }
    
    public String getTableName() { return this.sTableName; }
    
    public String getSubtableName() { return this.subTableName; }
    
    public TableBean getTableBean() { return this.frame.getBean(); }
    
    @Override
    public String getColumnName(int c) {
        return view.getLabelText( c );
    }

    public int getEditMode() { return edit_mode; }
    public void setEditMode(int edit_mode) {        
        this.edit_mode = edit_mode;         
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
        return view.getClass(c);
    }
    
    @Override
    public int getRowCount() {
        int iRowMax = 0;
        if (this.subTableName.equalsIgnoreCase("fields")) {
            iRowMax = frame.getBean().getFields().size();
        } else if (this.subTableName.equalsIgnoreCase("foreignkeys")) {
            if (frame.getBean() != null) iRowMax = frame.getBean().getForeignkeys().size();
        }
        if (this.bNewRowMode) {
            iRowMax = iRowMax + 1;            
        }        
        return iRowMax;        
    }

    @Override
    public int getColumnCount() {
        return view.getCount();
    }
    
    public void setNewRowMode( boolean b) { this.bNewRowMode = b; this.fireTableDataChanged(); }    
    public boolean isNewRowMode() { return this.bNewRowMode; }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        switch (this.edit_mode) {
            case TableModel.EDIT_MODE_NONE:
                return false;               
            case TableModel.EDIT_MODE_ALL:
                return true;                
            case TableModel.EDIT_MODE_NEW_ROW:
                if (this.bNewRowMode) {
                    int iRowMax = 0;
                    if (this.subTableName.equalsIgnoreCase("foreignkeys")) {
                        iRowMax = frame.getBean().getForeignkeys().size();
                        return (rowIndex == iRowMax);
                    }    
                }
                break;
        }
        return false;
    }
    
    @Override
    public void setValueAt(Object oValue, int rowIndex, int columnIndex) {
        if (this.bNewRowMode) {
            if (this.subTableName.equalsIgnoreCase("fields")) {
                if (rowIndex == frame.getBean().getFields().size());
                    
            } else if (this.subTableName.equalsIgnoreCase("foreignkeys")) {
                if (rowIndex == frame.getBean().getForeignkeys().size())
                    this.view.setNewTempRowValue(oValue, frame.getBean(), columnIndex);
            }
            
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        return view.getValue(frame.getBean(), rowIndex, columnIndex);        
    }
    
}
