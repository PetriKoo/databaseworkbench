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
    public static final String TABLE_FOLDER = "tables";
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
        
        File folder;
        folder = new File( DatabaseWorkbench.DATABASE_FOLDER );
        Tools.createFolder(folder);
        
        folder = new File( DatabaseWorkbench.TABLE_FOLDER );
        Tools.createFolder(folder);
        
        INSTANCE = new DatabaseWorkbench();
        
        
    }        
    
}
