package databaseworkbench;

import databaseworkbench.beans.TableBean;
import databaseworkbench.beans.TableFieldBean;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JInternalFrame;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class Tools {
    
    public static boolean contains(JInternalFrame isThisFrame, JInternalFrame[] allFrames) {
        for(JInternalFrame frame : allFrames) {
            if (frame.equals(isThisFrame)) return true;
        }
        return false;
    }
    
    public static TableBean copyFields(TableBean from, TableBean to) {        
        for(TableFieldBean field : from.getFields()) {
            to.getFields().add( field );
        }
        return to;
    }
    
    public static String[] splitDot(String sData ) {
        return sData.split("\\.");
    }
    
    public static String Capitalisation(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return name;
    }
}
