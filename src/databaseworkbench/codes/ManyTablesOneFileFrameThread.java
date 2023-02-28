package databaseworkbench.codes;

import databaseworkbench.File;
import databaseworkbench.beans.CodeTypeBean;
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
    private final String startTableTag = "{foreach table}";
    private final String endFieldTag = "{/fieldforeach}";
    private final String endTableTag = "{/tableforeach}";
    private final String tableTag = "{[table]}";
    private String patternForeachTable = "\\{foreach table\\}(.*?)\\{/tableforeach\\}";
    private String patternForeachField = "\\{foreach field\\}(.*?)\\{/fieldforeach\\}";
    private String PatternCurlybrackets = "\\{\\[(.*?)\\]\\}";
    
    public ManyTablesOneFileFrameThread() {
        
    }
    
    @Override
    public void run() {
        String sTheTemplate = frame.getTheTemplate();
        String sFileNameTemplate = frame.getFilename();
        String sPath = frame.getOutputPath();
        List<TableBean> selectedTables = this.frame.getSelectedTables();
        StringBuffer sbDataToWorkWith;
        String sNewDatafilename;
        int startTableLocation;
        int endTableLocation;
        int startFieldLocation;
        int endFieldLocation;
        int workLocation = 0;
        int size;
        StringBuffer betweenTableDataTemplate;
        String betweenFieldData;
        sbDataToWorkWith = new StringBuffer( sTheTemplate );
        sNewDatafilename = sFileNameTemplate;
        workLocation = 0;
        size = tableTag.length();
        StringBuffer allTables = new StringBuffer();
        StringBuffer oneTableWork;
        while ((startTableLocation = sbDataToWorkWith.indexOf(startTableTag, workLocation)) > -1) {
            endTableLocation = sbDataToWorkWith.indexOf(endTableTag, startTableLocation);
            if (endTableLocation > -1) { // found both, start and end, lets do replacing work
                System.out.println("Start & End foreach table found!");
                betweenTableDataTemplate = new StringBuffer( sbDataToWorkWith.substring(startTableLocation + startTableTag.length(), endTableLocation) ); // template, do not modify, because needed multiple times
                
                for(TableBean table: selectedTables) {
                    System.out.println("Working on table " + table.getName() );
                    oneTableWork = new StringBuffer(betweenTableDataTemplate.toString() );
                    oneTableWork = this.doTabletags(oneTableWork, table); // replace {[table]} with table's names
                    workLocation = 0;
                    while ((startFieldLocation = oneTableWork.indexOf(startFieldTag, workLocation)) > -1) {
            
                        if (startFieldLocation > -1) {
                            endFieldLocation = oneTableWork.indexOf(endFieldTag, startFieldLocation);
                            if (endFieldLocation > -1) { // found both, start and end, lets do replacing work
                                System.out.println("Going thru fields of table " + table.getName());
                                betweenFieldData = oneTableWork.substring(startFieldLocation + startFieldTag.length(), endFieldLocation);
                                oneTableWork = oneTableWork.replace(startFieldLocation, endFieldLocation + endFieldTag.length(), this.replaceFieldTags(table, betweenFieldData));
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
    
    private String replaceFieldTags(TableBean doThisTable, String betweenData) {
        StringBuffer sbReturnData = new StringBuffer();
        
        Pattern pattern = Pattern.compile(this.PatternCurlybrackets); // find language
        Matcher matcher = pattern.matcher(betweenData);
        
        ArrayList<String> sLangs = new ArrayList<>();
        String sFound;
        String sOneLine;
        while(matcher.find()) {
            sFound = matcher.group();
            if (!sFound.equalsIgnoreCase("{[field]}")) {
                sLangs.add( sFound.replace("{[", "").replace("]}", "") );
                
            }
        }
        String sCode;
        
        CodeTypeBean CTB;
        for (TableFieldBean field : doThisTable.getFields()) {
            
        
            sOneLine = betweenData;
            
            sOneLine = sOneLine.replaceAll("\\{\\[field\\]\\}", field.getName() );
            for (String sLanguage : sLangs) {
        
                CTB = field.getType().getCodeTypeBean(sLanguage);
                if (CTB != null) {
                    sCode = field.getType().getCodeTypeBean(sLanguage).getInCodeText();
                } else sCode = "???";
                sOneLine = sOneLine.replaceAll("\\{\\[" + sLanguage + "\\]\\}", sCode);
            }
            sbReturnData.append(sOneLine);
        }
        
        return sbReturnData.toString();
    }

    void setFrame(ManyTablesOneFileFrame aThis) {
        this.frame = aThis;
    }
}
