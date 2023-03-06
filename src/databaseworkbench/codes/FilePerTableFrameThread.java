package databaseworkbench.codes;

import databaseworkbench.File;
import databaseworkbench.Tools;
import databaseworkbench.beans.TableBean;
import java.util.List;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class FilePerTableFrameThread extends AbstractFrameThread {

    private FilePerTableFrame frame;
    

    public FilePerTableFrameThread() {
        
    }
    
    @Override
    public void run() {
        String sTheTemplate = frame.getTheTemplate();
        String sFileNameTemplate = frame.getFilename();
        String sPath = frame.getOutputPath();
        sPath = Tools.fixPath(sPath);
        
        List<TableBean> selectedTables = this.frame.getSelectedTables();
        
        String sNewDatafilename;
        int startLocation;
        int endLocation;
        int workLocation = 0;
        int size;
        String sBetweenData;
        
        for(TableBean table: selectedTables) {
            workLocation = 0;
            
            oneTableWork = new StringBuffer( sTheTemplate );
            
            sNewDatafilename = sFileNameTemplate;
            sNewDatafilename = sNewDatafilename.replace("{table}", table.getName() );             
            
            size = tableTag.length();
            while((startLocation = oneTableWork.indexOf(tableTag,workLocation)) > -1) {
                oneTableWork = oneTableWork.replace(startLocation, startLocation+size, table.getName());
                workLocation = startLocation + table.getName().length();
            }
            workLocation = 0;
            // working on the file
            
            while ((startLocation = oneTableWork.indexOf(startFieldTag, workLocation)) > -1) {
            
                if (startLocation > -1) {
                    endLocation = oneTableWork.indexOf(endFieldTag, startLocation);
                    if (endLocation > -1) { // found both, start and end, lets do replacing work
                        sBetweenData = oneTableWork.substring(startLocation + startFieldTag.length(), endLocation);
                        oneTableWork = oneTableWork.replace(startLocation, endLocation + endFieldTag.length(), this.replaceFieldTags(table, sBetweenData));
                    }
                }
            }
            workLocation = 0;
            while ((startLocation = oneTableWork.indexOf(startForeignKeyTag, workLocation)) > -1) {
            
                if (startLocation > -1) {
                    endLocation = oneTableWork.indexOf(endForeignKeyTag, startLocation);
                    if (endLocation > -1) { // found both, start and end, lets do replacing work
                        sBetweenData = oneTableWork.substring(startLocation + startForeignKeyTag.length(), endLocation);
                        oneTableWork = oneTableWork.replace(startLocation, endLocation + endForeignKeyTag.length(), this.replaceForeignKeyTags(table, sBetweenData));
                        if (this.doChange) this.doChange( this.doChangeData );
                    }
                }
            }
            this.clearIf();
            File.save(oneTableWork.toString(), sPath + sNewDatafilename);
            
        }
        frame.jobHasBeenDone();
    }

    void setFrame(FilePerTableFrame aThis) {
        this.frame = aThis;
    }

}
