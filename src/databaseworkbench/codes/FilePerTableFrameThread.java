package databaseworkbench.codes;

import databaseworkbench.beans.TableBean;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class FilePerTableFrameThread extends Thread {

    private FilePerTableFrame frame;
    
    public FilePerTableFrameThread() {
        
    }
    
    @Override
    public void run() {
        String sTheTemplate = frame.getTheTemplate();
        String sFileNameTemplate = frame.getFilename();
        List<TableBean> selectedTables = this.frame.getSelectedTables();
        String sDataToWorkWith;
        String sNewDatafilename;
        for(TableBean table: selectedTables) {
            sDataToWorkWith = sTheTemplate;
            sNewDatafilename = sFileNameTemplate;
            sNewDatafilename = sNewDatafilename.replace("{table}", table.getName() );
            sDataToWorkWith = sDataToWorkWith.replace("{table}", table.getName() );
            
        }
        frame.jobHasBeenDone();
    }

    void setFrame(FilePerTableFrame aThis) {
        this.frame = aThis;
    }
}
