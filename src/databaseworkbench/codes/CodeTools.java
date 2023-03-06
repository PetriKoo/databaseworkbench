package databaseworkbench.codes;

import databaseworkbench.Configs;
import databaseworkbench.Database;
import databaseworkbench.Tools;
import databaseworkbench.beans.ForeignKeyBean;
import databaseworkbench.beans.TableFieldBean;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class CodeTools {
    public static String getFieldText(TableFieldBean field, String[] sData) {
        if (sData[0].equals("field")){
            switch (sData[1]) {
                case "name":
                    if (sData.length > 2) {
                        if(sData[2].equalsIgnoreCase("capitalisation")) {
                            return Tools.Capitalisation(field.getName());
                        }
                    }
                    return field.getName();                    
                case "label":
                    return field.getLabel();
                case "description":
                    return field.getDescription();
                case "default_value":
                    return field.getDefault_value();
                case "lang":
                    if (field.getType().getCodeTypeBean(sData[2]) != null) return field.getType().getCodeTypeBean(sData[2]).getInCodeText();
                    else return "";
                case "auto_increment":
                    if (field.isAuto_increment()) return Configs.getInstance().get("auto_increment");
                    else return "";
                case "not_null":
                    if (field.isNotnull()) return Configs.getInstance().get("not_null");
                    else return "";
                case "unique":
                    if (field.isUnique()) return Configs.getInstance().get("unique");
                    else return "";
                case "primary_key":
                    if (field.isPrimarykey()) return Configs.getInstance().get("primary_key");
                    else return "";    
                default:
                    return "";
            }
        }
        if (sData[0].equalsIgnoreCase("not_last")) {
            if (Database.getInstance().getTable(field.getTable()).getFields().indexOf(field) != Database.getInstance().getTable(field.getTable()).getFields().size() -1)
            switch (sData[1]) {
                case "comma":
                    return ",";                    
                
                case "dot":
                    return ".";
                
                case "semicolon":
                    return ";";
                
                default:
                    return "";
            }
            
        }
        return "";
    }
    
    public static String getForeignKeyText(ForeignKeyBean key, String[] sData) {
        if (sData[0].equals("key")){
            switch (sData[1]) {
                case "name":
                    if (sData.length > 2) {
                        if(sData[2].equalsIgnoreCase("capitalisation")) {
                            return Tools.Capitalisation(key.getName());
                        }
                    }
                    return key.getName();
                    
                case "field":
                    switch (sData[2]) {
                        case "name":
                            if (sData.length > 3) {
                                if(sData[3].equalsIgnoreCase("capitalisation")) {
                                    return Tools.Capitalisation(key.getField().getName());
                                }
                            }
                            return key.getField().getName();
                        case "lang":
                            return key.getField().getType().getCodeTypeBean(sData[3]).getInCodeText();
                        default:
                            return "";
                    }
                    
                case "foreign":
                    switch (sData[2]) {
                        case "table":
                            if (sData.length > 3) {
                                if(sData[3].equalsIgnoreCase("capitalisation")) {
                                    return Tools.Capitalisation(key.getForeigntable().getName());
                                }
                            }
                            return key.getForeigntable().getName();
                        case "field":
                            if (sData.length > 3) {
                                if(sData[3].equalsIgnoreCase("capitalisation")) {
                                    return Tools.Capitalisation(key.getForeignfield().getName());
                                }
                            }
                            return key.getForeignfield().getName();
                        default:
                            return "";
                    }
                    
                default:
                    return "";
            }
        }
        if (sData[0].equalsIgnoreCase("not_last")) {
            if (Database.getInstance().getTable(key.getTable()).getForeignkeys().indexOf(key) != Database.getInstance().getTable(key.getTable()).getForeignkeys().size() -1)
            switch (sData[1]) {
                case "comma":
                    return ",";                    
                
                case "dot":
                    return ".";
                
                case "semicolon":
                    return ";";
                
                default:
                    return "";
            }
            
        }
   
        return "";
    }


}
