package databaseworkbench.codes;

import databaseworkbench.Tools;
import databaseworkbench.beans.CodeTypeBean;
import databaseworkbench.beans.ForeignKeyBean;
import databaseworkbench.beans.TableBean;
import databaseworkbench.beans.TableFieldBean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
abstract public class AbstractFrameThread extends Thread {
    
    protected final String startFieldTag = "{foreach field}";
    protected final String endFieldTag = "{/fieldforeach}";
    // protected final String tableTag = "{[table]}";
    
    protected final String startForeignKeyTag = "{foreach foreignkey}";
    protected final String endForeignKeyTag = "{/foreignkeyforeach}";           
    
    //private final String patternFieldLabel = "\\{\\[field.label\\]\\}";
    // private final String patternForeachField = "\\{foreach field\\}(.*?)\\{/fieldforeach\\}";
    protected final String PatternCurlybrackets = "\\{\\[(.*?)\\]\\}";
    protected final String PatternCurlyTablebrackets = "\\{\\[(\\btable\\.(.*?))\\]\\}";
    
    protected StringBuffer oneTableWork;
    protected boolean doChange = false;
    protected String[] doChangeData =  null;
    
    @Override
    abstract public void run();

    protected String replaceTableTags(TableBean table, String betweenData) {
        
        Pattern pattern = Pattern.compile(this.PatternCurlyTablebrackets); // find language
        Matcher matcher = pattern.matcher(betweenData);
        String[] sData;
        String sReplaceThis;
        while(matcher.find()) {
                 sData = Tools.splitDot(matcher.group(1));
                 sReplaceThis = Pattern.quote(matcher.group(0));
                 System.out.println(sReplaceThis);
                 betweenData = betweenData.replaceAll( sReplaceThis, CodeTools.getTableText(table, sData));
        }
        return betweenData;
    }
    
    protected String replaceFieldTags(TableBean table, String betweenData) {
        StringBuffer sbReturnData = new StringBuffer();
        
        Pattern pattern = Pattern.compile(this.PatternCurlybrackets); // find language
        Matcher matcher;                
        
        String sOneLine;
        
        String[] sData;
        String sCode;
        
        
        CodeTypeBean CTB;
        for (TableFieldBean field : table.getFields()) {
            sOneLine = String.copyValueOf( betweenData.toCharArray() );
            matcher = pattern.matcher(sOneLine);
            while(matcher.find()) {
                 sData = Tools.splitDot(matcher.group(1));
                 
                 sOneLine = sOneLine.replaceAll(Pattern.quote(matcher.group(0)), CodeTools.getFieldText(field, sData));
                 this.doChangeChecker( sData );
             }
        
            sbReturnData.append(sOneLine);
        }
        
        return sbReturnData.toString();
    }
     
    protected String replaceForeignKeyTags(TableBean table, String betweenData) {
        StringBuffer sbReturnData = new StringBuffer();
        
        Pattern pattern = Pattern.compile(this.PatternCurlybrackets); // find language
        Matcher matcher;
        String[] sData;
        String sOneLine;
        for (ForeignKeyBean key : table.getForeignkeys()) {
            sOneLine = String.copyValueOf( betweenData.toCharArray() );
            matcher = pattern.matcher(sOneLine);
            while(matcher.find()) {
                sData = Tools.splitDot(matcher.group(1));
                sOneLine = sOneLine.replaceAll(Pattern.quote(matcher.group(0)), CodeTools.getForeignKeyText(key, sData));
                this.doChangeChecker( sData );
            }
            sbReturnData.append(sOneLine);
        }
        
        return sbReturnData.toString();
     }
    
    protected void doChange(String[] sData) {
        if (sData[0].equalsIgnoreCase("change")) {
            switch (sData[1]) {
                case "if":
                    this.doIf(sData);
                    break;
            }
            this.doChange = false;
        }
    }
    
    protected void doChangeChecker(String[] sData) {
        
        if (sData[0].equalsIgnoreCase("change")) {
            
            this.doChange = true;
            this.doChangeData = sData;
        }
    }

    protected void doIf(String[] sData) {
        
        String sPattern = "";
        switch (sData[2]) {
            case "comma":
                sPattern = "\\{\\[\\bif\\.\\bcomma\\.\\b" + sData[3] + "\\]\\}";
                oneTableWork = new StringBuffer( oneTableWork.toString().replaceAll( sPattern, ",") );
                break;
            case "dot":
                sPattern = "\\{\\[\\bif\\.\\bdot\\.\\b" + sData[3] + "\\]\\}";
                oneTableWork = new StringBuffer( oneTableWork.toString().replaceAll(sPattern, ".") );
                break;
            case "semicolon":
                sPattern = "\\{\\[\\bif\\.\\bsemicolon\\.\\b" + sData[3] + "\\]\\}";
                oneTableWork = new StringBuffer( oneTableWork.toString().replaceAll(sPattern, ";") );
                break;
        }
        
    }
    
    protected void clearIf() {
        System.out.println("clearif");
        oneTableWork =  new StringBuffer( oneTableWork.toString().replaceAll("\\{\\[\\bif\\.(.*?)\\.(.*?)\\]\\}", "*if*") );
        oneTableWork =  new StringBuffer( oneTableWork.toString().replaceAll("\\{\\[\\bchange\\.\\bif\\.(.*?)\\.(.*?)\\]\\}", "*change*") );
    }
}
