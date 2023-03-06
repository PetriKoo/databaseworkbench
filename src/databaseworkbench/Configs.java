package databaseworkbench;

import java.util.Properties;

/**
 *
 * @author petri
 */
public class Configs {
    
    private String filename = "settings.cfg";
    private static final Configs INSTANCE = new Configs();
    Properties props = new Properties();
    
    private Configs() {
        props = File.loadConfiguration(filename);
    }
    
    public static Configs getInstance() {
        return INSTANCE;
    }
    
    public String get(String sKey) {
        if (props.getProperty(sKey) == null && sKey.equals("charset")) {           
            props.setProperty("charset", "UTF-8");
            File.saveConfiguration(props, filename);
        }
        if (props.getProperty(sKey) == null && sKey.equals("not_null")) {           
            props.setProperty("not_null", "NOT NULL");
            File.saveConfiguration(props, filename);
        }
        if (props.getProperty(sKey) == null && sKey.equals("unique")) {           
            props.setProperty("unique", "UNIQUE");
            File.saveConfiguration(props, filename);
        }
        if (props.getProperty(sKey) == null && sKey.equals("auto_increment")) {           
            props.setProperty("auto_increment", "AUTO_INCREMENT");
            File.saveConfiguration(props, filename);
        }
        if (props.getProperty(sKey) == null && sKey.equals("primary_key")) {           
            props.setProperty("primary_key", "PRIMARY KEY");
            File.saveConfiguration(props, filename);
        }
        if (props.getProperty(sKey) == null && sKey.equals("output_path")) {           
            props.setProperty("output_path", System.getProperty("user.home"));
        }
        if (props.getProperty(sKey) == null && sKey.equals("template_path")) {           
            java.io.File folder = new java.io.File(FileUtility.CODETEMPLATE_FOLDER);
            props.setProperty("template_path", folder.getAbsolutePath());
        }
        return props.getProperty(sKey);
    }
    
    public void set(String skey, String sValue) {
        props.setProperty(skey, sValue);
        File.saveConfiguration(props, filename);
    }
}
