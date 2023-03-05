package databaseworkbench.codes;

import databaseworkbench.Configs;
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
        return "";
    }
    
    public static String getForeignKeyText(ForeignKeyBean key, String[] sData) {
        if (sData[0].equals("key")){
            switch (sData[1]) {
                case "name":
                    return key.getName();
                    
                case "field":
                    switch (sData[2]) {
                        case "name":
                            return key.getField().getName();
                        case "lang":
                            return key.getField().getType().getCodeTypeBean(sData[3]).getInCodeText();
                        default:
                            return "";
                    }
                    
                case "foreign":
                    switch (sData[2]) {
                        case "table":
                            return key.getForeigntable().getName();
                        case "field":
                            return key.getForeignfield().getName();
                        default:
                            return "";
                    }
                    
                default:
                    return "";
            }
        }
        return "";
    }
}
