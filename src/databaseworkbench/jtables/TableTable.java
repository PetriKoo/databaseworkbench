package databaseworkbench.jtables;

import databaseworkbench.renderers.BooleanRenderer;
import javax.swing.JTable;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class TableTable extends JTable {

    public TableTable(javax.swing.table.TableModel m) {
        super(m);
        this.setAutoResizeMode( JTable.AUTO_RESIZE_NEXT_COLUMN );
        this.setDefaultRenderer( Boolean.class, new BooleanRenderer() );
        this.getColumnModel().getColumn(0).setMaxWidth( 40 );
    }
    
}
