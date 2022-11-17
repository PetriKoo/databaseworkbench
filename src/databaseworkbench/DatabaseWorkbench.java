package databaseworkbench;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author petri koskelainen
 */
public class DatabaseWorkbench {

    static DatabaseWorkbench INSTANCE;
    
    MainWindow mainWindow;
    
    DatabaseWorkbench() {
        SwingUtilities.invokeLater(() -> {
            mainWindow = new MainWindow(DatabaseWorkbench.INSTANCE);
            mainWindow.setVisible( true );
        });
    }
            
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }   
        } catch (Exception e) {}
        
        INSTANCE = new DatabaseWorkbench();        
    }
    
}
