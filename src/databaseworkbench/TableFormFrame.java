package databaseworkbench;

import javax.swing.JInternalFrame;

/**
 *
 * @author petri
 */
public class TableFormFrame extends JInternalFrame {
    
    public TableFormFrame() {
        this.setTitle("Table Editor");
        this.setSize( 640,480);
        this.setLocation( 40, 40);
        this.setClosable( true );
        this.setVisible( true );
    }
}
