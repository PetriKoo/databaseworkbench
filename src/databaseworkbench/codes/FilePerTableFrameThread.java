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
public class FilePerTableFrameThread extends Thread {

    private FilePerTableFrame frame;
    
    private final String startFieldTag = "{foreach field}";
    private final String endFieldTag = "{/fieldforeach}";
    private final String tableTag = "{[table]}";
    
    private final String startForeignKeyTag = "{foreach foreignkey}";
    private final String endForeignKeyTag = "{/foreignkeyforeach}";
   
    
    private final String patternField = "\\{\\[field\\]\\}";
    
    //private final String patternFieldLabel = "\\{\\[field.label\\]\\}";
    // private final String patternForeachField = "\\{foreach field\\}(.*?)\\{/fieldforeach\\}";
    private final String PatternCurlybrackets = "\\{\\[(.*?)\\]\\}";
    
    public FilePerTableFrameThread() {
        
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
        int startLocation;
        int endLocation;
        int workLocation = 0;
        int size;
        String betweenData;
        for(TableBean table: selectedTables) {
            workLocation = 0;
            sbDataToWorkWith = new StringBuffer( sTheTemplate );
            sNewDatafilename = sFileNameTemplate;
            sNewDatafilename = sNewDatafilename.replace("{table}", table.getName() );             
            
            size = tableTag.length();
            while((startLocation = sbDataToWorkWith.indexOf(tableTag,workLocation)) > -1) {
                sbDataToWorkWith = sbDataToWorkWith.replace(startLocation, startLocation+size, table.getName());
                workLocation = startLocation + table.getName().length();
            }
            workLocation = 0;
            // working on the file
            
            while ((startLocation = sbDataToWorkWith.indexOf(startForeignKeyTag, workLocation)) > -1) {
            
                if (startLocation > -1) {
                    endLocation = sbDataToWorkWith.indexOf(endFieldTag, startLocation);
                    if (endLocation > -1) { // found both, start and end, lets do replacing work
                        betweenData = sbDataToWorkWith.substring(startLocation + startFieldTag.length(), endLocation);
                        sbDataToWorkWith.replace(startLocation, endLocation + endFieldTag.length(), this.replaceFieldTags(table, betweenData));
                    }
                }
            }
            
             while ((startLocation = sbDataToWorkWith.indexOf(startFieldTag, workLocation)) > -1) {
            
                if (startLocation > -1) {
                    endLocation = sbDataToWorkWith.indexOf(endForeignKeyTag, startLocation);
                    if (endLocation > -1) { // found both, start and end, lets do replacing work
                        betweenData = sbDataToWorkWith.substring(startLocation + startFieldTag.length(), endLocation);
                        sbDataToWorkWith.replace(startLocation, endLocation + endForeignKeyTag.length(), this.replaceForeignKeyTags(table, betweenData));
                    }
                }
            }
            
            File.save(sbDataToWorkWith, sPath + sNewDatafilename);
        }
        frame.jobHasBeenDone();
    }
    
    private String replaceFieldTags(TableBean table, String betweenData) {
        StringBuffer sbReturnData = new StringBuffer();
        
        Pattern pattern = Pattern.compile(this.PatternCurlybrackets); // find language
        Matcher matcher = pattern.matcher(betweenData);
        
        String sOneLine;                
                
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
        Matcher matcher = pattern.matcher(betweenData);
        
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

    void setFrame(FilePerTableFrame aThis) {
        this.frame = aThis;
    }
}
