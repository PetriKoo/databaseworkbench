package databaseworkbench;

import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JInternalFrame;

/**
 *
 * @author petri
 */
public class Tools {
    
    public static boolean contains(JInternalFrame isThisFrame, JInternalFrame[] allFrames) {
        for(JInternalFrame frame : allFrames) {
            if (frame.equals(isThisFrame)) return true;
        }
        return false;
    }
    
    public static FilenameFilter dbFileFilter() {
        FilenameFilter dbFilter = new FilenameFilter() {
            @Override
            public boolean accept(File directory, String filename) {
                if (filename.endsWith(".obj")) {
                    return true;
                } else return false;
            }
            
        };
        return dbFilter;
    }
    
    public static void createFolder(File folder) {
        if (folder.exists()) {
            if (!folder.isDirectory()) {
                folder.mkdir();
            }
        } else {
            folder.mkdir();
        }
    }
}
