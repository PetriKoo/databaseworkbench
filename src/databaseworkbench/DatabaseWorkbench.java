package databaseworkbench;

import java.io.File;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author petri koskelainen
 */
public class DatabaseWorkbench {

    static DatabaseWorkbench INSTANCE;
    public static final String DATABASE_FOLDER = "databases";
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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {}
        File folder = new File( DatabaseWorkbench.DATABASE_FOLDER );
        if (folder.exists()) {
            if (!folder.isDirectory()) {
                folder.mkdir();
            }
        } else {
            folder.mkdir();
        }
        INSTANCE = new DatabaseWorkbench();
        
        
    }
    
}
