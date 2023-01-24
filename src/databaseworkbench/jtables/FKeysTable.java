package databaseworkbench.jtables;

import databaseworkbench.beans.TableBean;
import databaseworkbench.beans.TableFieldBean;
import databaseworkbench.renderers.TableBeanRenderer;
import databaseworkbench.renderers.TableFieldBeanRenderer;
import javax.swing.JTable;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class FKeysTable extends JTable {

    public FKeysTable(javax.swing.table.TableModel m) {
        super(m);
        this.setAutoResizeMode( JTable.AUTO_RESIZE_NEXT_COLUMN );
        this.setDefaultRenderer( TableBean.class, new TableBeanRenderer() );
        this.setDefaultRenderer( TableFieldBean.class, new TableFieldBeanRenderer() );
    }
    
}
