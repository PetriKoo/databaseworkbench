package databaseworkbench;

import java.io.File;
import java.util.Properties;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class DatabaseWorkbench {

    private static DatabaseWorkbench INSTANCE;
    
    MainWindow mainWindow;
    
    DatabaseWorkbench() {
        SwingUtilities.invokeLater(() -> {
            mainWindow = new MainWindow(DatabaseWorkbench.INSTANCE);
            mainWindow.setVisible( true );
        });
    }
            
    
    public static DatabaseWorkbench getInstance() { 
        return INSTANCE;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (Configs.getInstance().get("lookandfeel").equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }   
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {}
        System.setProperty("user.dir", Configs.getInstance().get("working_dir"));
        /*
        Properties props = System.getProperties();
        
        for (Object key : props.keySet()) {
            System.out.println(key.toString() + " => " + props.getProperty((String) key));
        }
        */
        makeWorkFolders();
        
        INSTANCE = new DatabaseWorkbench();
        
        
    }
    
    public static void makeWorkFolders() {
        File folder;
        
        folder = new File( Configs.getInstance().get("working_dir") );
        FileUtility.createFolder(folder);
        
        folder = new File( Configs.getInstance().get("working_dir") + FileUtility.DATABASE_FOLDER );
        FileUtility.createFolder(folder);
        
        folder = new File( Configs.getInstance().get("working_dir") + FileUtility.CODETEMPLATE_FOLDER );
        FileUtility.createFolder(folder);
        
        folder = new File( Configs.getInstance().get("working_dir") + FileUtility.LANGUAGECODES_FOLDER );
        FileUtility.createFolder(folder);
        
    }
    
}
