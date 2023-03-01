package databaseworkbench.codes;

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
                    return field.getType().getCodeTypeBean(sData[2]).getInCodeText();
                default:
                    return "";
            }
        } 
        return "";
    }
}
