package databaseworkbench.codes;

import databaseworkbench.File;
import databaseworkbench.Tools;
import databaseworkbench.beans.CodeTypeBean;
import databaseworkbench.beans.ForeignKeyBean;
import databaseworkbench.beans.TableBean;
import databaseworkbench.beans.TableFieldBean;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class ManyTablesOneFileFrameThread extends Thread {

    private ManyTablesOneFileFrame frame;
    
    private final String startFieldTag = "{foreach field}";
    private final String endFieldTag = "{/fieldforeach}";
    
    private final String startTableTag = "{foreach table}";
    private final String endTableTag = "{/tableforeach}";
    
    private final String startForeignKeyTag = "{foreach foreignkey}";
    private final String endForeignKeyTag = "{/foreignkeyforeach}";
    
    private final String tableTag = "{[table]}";
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
        if (sPath.charAt(sPath.length() -1) != java.io.File.separatorChar) sPath = sPath + java.io.File.separatorChar;
        
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
        int size;
        StringBuffer betweenTableDataTemplate;
        String betweenFieldData;
        String betweenForeignKeyData;
        sbDataToWorkWith = new StringBuffer( sTheTemplate );
        sNewDatafilename = sFileNameTemplate;
        workLocation = 0;
        size = tableTag.length();
        StringBuffer allTables = new StringBuffer();
        StringBuffer oneTableWork;
        while ((startTableLocation = sbDataToWorkWith.indexOf(startTableTag, workLocation)) > -1) {
            endTableLocation = sbDataToWorkWith.indexOf(endTableTag, startTableLocation);
            if (endTableLocation > -1) { // found both, start and end, lets do replacing work
                
                betweenTableDataTemplate = new StringBuffer( sbDataToWorkWith.substring(startTableLocation + startTableTag.length(), endTableLocation) ); // template, do not modify, because needed multiple times
                for(TableBean table: selectedTables) {
                
                    oneTableWork = new StringBuffer(betweenTableDataTemplate.toString() );
                    oneTableWork = this.doTabletags(oneTableWork, table); // replace {[table]} with table's names
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
                    while ((startForeignkeyLocation = sbDataToWorkWith.indexOf(startForeignKeyTag, workLocation)) > -1) {
            
                        if (startForeignkeyLocation > -1) {
                            endForeignkeyLocation = sbDataToWorkWith.indexOf(endForeignKeyTag, startForeignkeyLocation);
                            if (endForeignkeyLocation > -1) { // found both, start and end, lets do replacing work
                                betweenForeignKeyData = sbDataToWorkWith.substring(startForeignkeyLocation + startForeignKeyTag.length(), endForeignkeyLocation);
                                sbDataToWorkWith.replace(startForeignkeyLocation, endForeignkeyLocation + endForeignKeyTag.length(), this.replaceForeignKeyTags(table, betweenForeignKeyData));
                            }
                        }
                    }
                    
                    allTables.append( oneTableWork );
                }
                
                sbDataToWorkWith = sbDataToWorkWith.replace(startTableLocation, endTableLocation + endTableTag.length(), allTables.toString());
                workLocation = workLocation + allTables.toString().length();
            }
        }
        File.save(sbDataToWorkWith, sPath + sNewDatafilename);
        frame.jobHasBeenDone();
    }
    
    private StringBuffer doTabletags(StringBuffer sbData, TableBean table ) {
        int workLocation = 0;
        int size = tableTag.length();        
        int startLocation;
        while((startLocation = sbData.indexOf(tableTag,workLocation)) > -1) {
            sbData = sbData.replace(startLocation, startLocation+size, table.getName());
            workLocation = startLocation + table.getName().length();
        }
        return sbData;
    }
    
     private String replaceFieldTags(TableBean table, String betweenData) {
        StringBuffer sbReturnData = new StringBuffer();
        
        Pattern pattern = Pattern.compile(this.PatternCurlybrackets); // find language
        Matcher matcher = pattern.matcher(betweenData);
        
        ArrayList<String> sLangs = new ArrayList<>();
        String sFound;
        String sOneLine;
        String[] theWords;
        
        String sCode;
        
        CodeTypeBean CTB;
        for (TableFieldBean field : table.getFields()) {
            sOneLine = String.copyValueOf( betweenData.toCharArray() );
            matcher = pattern.matcher(sOneLine);
             while(matcher.find()) {
                 sOneLine = sOneLine.replaceAll(Pattern.quote(matcher.group(0)), CodeTools.getFieldText(field, Tools.splitDot(matcher.group(1))));
             }
        
            sbReturnData.append(sOneLine);
        }
        
        return sbReturnData.toString();
    }
     
    private String replaceForeignKeyTags(TableBean table, String betweenData) {
        StringBuffer sbReturnData = new StringBuffer();
        
        Pattern pattern = Pattern.compile(this.PatternCurlybrackets); // find language
        Matcher matcher;
        
        String sOneLine;
        for (ForeignKeyBean key : table.getForeignkeys()) {
            sOneLine = String.copyValueOf( betweenData.toCharArray() );
            matcher = pattern.matcher(sOneLine);
            while(matcher.find()) {
                sOneLine = sOneLine.replaceAll(Pattern.quote(matcher.group(0)), CodeTools.getForeignKeyText(key, Tools.splitDot(matcher.group(1))));
            }
            sbReturnData.append(sOneLine);
        }
        
        return sbReturnData.toString();
     }

    void setFrame(ManyTablesOneFileFrame aThis) {
        this.frame = aThis;
    }
}
