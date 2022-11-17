package databaseworkbench;

import javax.swing.JTable;

/**
 *
 * @author petri
 */
public class TableTable extends JTable {

    public TableTable(javax.swing.table.TableModel m) {
        super(m);
        this.setAutoResizeMode( JTable.AUTO_RESIZE_NEXT_COLUMN );
        this.setDefaultRenderer( Boolean.class, new BooleanRenderer() );
        this.getColumnModel().getColumn(0).setMaxWidth( 30 );
    }
    
}
