package databaseworkbench;

import javax.swing.JTable;

/**
 *
 * @author petri
 */
public class FKeysTable extends JTable {

    public FKeysTable(javax.swing.table.TableModel m) {
        super(m);
        this.setAutoResizeMode( JTable.AUTO_RESIZE_NEXT_COLUMN );
        
    }
    
}
