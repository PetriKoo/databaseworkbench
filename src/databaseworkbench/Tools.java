package databaseworkbench;

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
    
}
