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
public class FilePerTableFrameThread extends Thread {

    private FilePerTableFrame frame;
    
    private final String startTag = "{foreach field}";
    private final String endTag = "{/foreach}";
    private final String tableTag = "{[table]}";
    private String patternForeach = "\\{foreach field\\}(.*?)\\{/foreach\\}";
    private String PatternCurlybrackets = "\\{\\[(.*?)\\]\\}";
    
    public FilePerTableFrameThread() {
        
    }
    
    @Override
    public void run() {
        String sTheTemplate = frame.getTheTemplate();
        String sFileNameTemplate = frame.getFilename();
        String sPath = frame.getOutputPath();
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
            
            while ((startLocation = sbDataToWorkWith.indexOf(startTag, workLocation)) > -1) {
            
                if (startLocation > -1) {
                    endLocation = sbDataToWorkWith.indexOf(endTag, startLocation);
                    if (endLocation > -1) { // found both, start and end, lets do replacing work
                        betweenData = sbDataToWorkWith.substring(startLocation + startTag.length(), endLocation);
                        sbDataToWorkWith.replace(startLocation, endLocation + endTag.length(), this.replaceFieldTags(table, betweenData));
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
        System.out.println("------------");
        CodeTypeBean CTB;
        for (TableFieldBean field : table.getFields()) {
            
            System.out.println("* " + field.getName());
            sOneLine = betweenData;
            
            sOneLine = sOneLine.replaceAll("\\{\\[field\\]\\}", field.getName() );
            for (String sLanguage : sLangs) {
                System.out.println("- " + sLanguage);
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

    void setFrame(FilePerTableFrame aThis) {
        this.frame = aThis;
    }
}
