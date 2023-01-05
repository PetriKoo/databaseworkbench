package databaseworkbench.renderers;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author petri
 */
public class BooleanRenderer extends JCheckBox implements TableCellRenderer {   
    
    public BooleanRenderer() {
        this.setOpaque( true );
        this.setHorizontalAlignment( SwingConstants.CENTER );
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        TableCellRenderer rend = table.getDefaultRenderer(String.class);        
        DefaultTableCellRenderer comp = (DefaultTableCellRenderer) rend.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (value.equals(Boolean.TRUE)) {
            this.setSelected( true );
        } else
            this.setSelected( false );
        
        this.setBackground( comp.getBackground() );
        this.setBorder( comp.getBorder() );
        
        return this;
    }
    
}
