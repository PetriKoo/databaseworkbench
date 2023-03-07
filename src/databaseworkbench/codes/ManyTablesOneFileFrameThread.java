package databaseworkbench.codes;

import databaseworkbench.File;
import databaseworkbench.Tools;
import databaseworkbench.beans.TableBean;
import java.util.List;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class ManyTablesOneFileFrameThread extends AbstractFrameThread {

    private ManyTablesOneFileFrame frame;
    
    private final String startFieldTag = "{foreach field}";
    private final String endFieldTag = "{/fieldforeach}";
    
    private final String startTableTag = "{foreach table}";
    private final String endTableTag = "{/tableforeach}";
    
    private final String startForeignKeyTag = "{foreach foreignkey}";
    private final String endForeignKeyTag = "{/foreignkeyforeach}";
        
    //private String patternForeachTable = "\\{foreach table\\}(.*?)\\{/tableforeach\\}";
    // private String patternForeachField = "\\{foreach field\\}(.*?)\\{/fieldforeach\\}";
    private String PatternCurlybrackets = "\\{\\[(.*?)\\]\\}";
    
    public ManyTablesOneFileFrameThread() {
        
    }
    
    @Override
    public void run() {
        String sTheTemplate = frame.getTheTemplate();
        String sFileNameTemplate = frame.getFilename();
        String sPath = frame.getOutputPath();
        sPath = Tools.fixPath(sPath);
        
        List<TableBean> selectedTables = this.frame.getSelectedTables();
        StringBuffer sbDataToWorkWith;
        String sNewDatafilename;
        int startTableLocation;
        int endTableLocation;
        
        int startFieldLocation;
        int endFieldLocation;
        
        int startForeignkeyLocation;
        int endForeignkeyLocation;
        
        int workLocation = 0;
        // int size;
        
        String betweenFieldData;
        String betweenForeignKeyData;
        StringBuffer betweenTableDataTemplate;
        sbDataToWorkWith = new StringBuffer( sTheTemplate );
        sNewDatafilename = sFileNameTemplate;
        workLocation = 0;
        
        StringBuffer allTables = new StringBuffer();
        
        while ((startTableLocation = sbDataToWorkWith.indexOf(startTableTag, workLocation)) > -1) {
            endTableLocation = sbDataToWorkWith.indexOf(endTableTag, startTableLocation);
            if (endTableLocation > -1) { // found both, start and end, lets do replacing work
                
                betweenTableDataTemplate = new StringBuffer( sbDataToWorkWith.substring(startTableLocation + startTableTag.length(), endTableLocation) ); // template, do not modify, because needed multiple times
                for(TableBean table: selectedTables) {
                
                    oneTableWork = new StringBuffer(betweenTableDataTemplate.toString() );
                    oneTableWork = new StringBuffer( this.replaceTableTags(table, oneTableWork.toString()) ); // changing all {[table.*]]}
                    workLocation = 0;
                    while ((startFieldLocation = oneTableWork.indexOf(startFieldTag, workLocation)) > -1) {
            
                        if (startFieldLocation > -1) {
                            endFieldLocation = oneTableWork.indexOf(endFieldTag, startFieldLocation);
                            if (endFieldLocation > -1) { // found both, start and end, lets do replacing work
                                betweenFieldData = oneTableWork.substring(startFieldLocation + startFieldTag.length(), endFieldLocation);
                                oneTableWork = oneTableWork.replace(startFieldLocation, endFieldLocation + endFieldTag.length(), this.replaceFieldTags(table, betweenFieldData));
                            }
                        }
                    }
                    
                    workLocation = 0;
                    while ((startForeignkeyLocation = oneTableWork.indexOf(startForeignKeyTag, workLocation)) > -1) {
            
                        if (startForeignkeyLocation > -1) {
                            endForeignkeyLocation = oneTableWork.indexOf(endForeignKeyTag, startForeignkeyLocation);
                            if (endForeignkeyLocation > -1) { // found both, start and end, lets do replacing work
                                betweenForeignKeyData = oneTableWork.substring(startForeignkeyLocation + startForeignKeyTag.length(), endForeignkeyLocation);
                                oneTableWork = oneTableWork.replace(startForeignkeyLocation, endForeignkeyLocation + endForeignKeyTag.length(), this.replaceForeignKeyTags(table, betweenForeignKeyData));
                                if (this.doChange) this.doChange( this.doChangeData );
                            }
                        }
                    }
                    this.clearIf();
                    allTables.append( oneTableWork );
                }
                
                sbDataToWorkWith = sbDataToWorkWith.replace(startTableLocation, endTableLocation + endTableTag.length(), allTables.toString());
                workLocation = workLocation + allTables.toString().length();
            }
        }
        File.save(sbDataToWorkWith, sPath + sNewDatafilename);
        frame.jobHasBeenDone();
    }       

    void setFrame(ManyTablesOneFileFrame aThis) {
        this.frame = aThis;
    }
   
}
