package databaseworkbench;

import javax.swing.JInternalFrame;

/**
 *
 * @author petri
 */
public class Tools {
    
    public static boolean contains(TableFormFrame formFrame, JInternalFrame[] allFrames) {
        for(JInternalFrame frame : allFrames) {
            if (frame.equals(formFrame)) return true;
        }
        return false;
    }
    
}
