package databaseworkbench;

import databaseworkbench.frames.TableFrame;
import databaseworkbench.views.ViewInterface;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author petri
 */
public class TableModel extends AbstractTableModel {

    private final TableFrame frame;
    private final ViewInterface view;
    

    public TableModel(TableFrame frame, ViewInterface view) {
        this.frame = frame;
        this.view = view;

    }
    
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
        return frame.getBean().getFields().size();        
    }

    @Override
    public int getColumnCount() {
        return view.getCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        return view.getValue(frame.getBean(), rowIndex, columnIndex);
        
    }
    
}
