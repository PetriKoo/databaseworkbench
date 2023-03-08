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
        props = FileUtility.loadConfiguration(filename);
    }
    
    public static Configs getInstance() {
        return INSTANCE;
    }
    
    public String get(String sKey) {
        if (props.getProperty(sKey) == null && sKey.equals("working_dir")) {           
            props.setProperty(sKey, System.getProperty("user.home") + java.io.File.separatorChar + "databaseworkbench" + java.io.File.separatorChar);
            FileUtility.saveConfiguration(props, filename);
        }
        if (props.getProperty(sKey) == null && sKey.equals("lookandfeel")) {           
            props.setProperty(sKey, "Nimbus");
            FileUtility.saveConfiguration(props, filename);
        }
        if (props.getProperty(sKey) == null && sKey.equals("charset")) {           
            props.setProperty(sKey, "UTF-8");
            FileUtility.saveConfiguration(props, filename);
        }
        if (props.getProperty(sKey) == null && sKey.equals("not_null")) {           
            props.setProperty(sKey, "NOT NULL");
            FileUtility.saveConfiguration(props, filename);
        }
        if (props.getProperty(sKey) == null && sKey.equals("unique")) {           
            props.setProperty(sKey, "UNIQUE");
            FileUtility.saveConfiguration(props, filename);
        }
        if (props.getProperty(sKey) == null && sKey.equals("auto_increment")) {           
            props.setProperty(sKey, "AUTO_INCREMENT");
            FileUtility.saveConfiguration(props, filename);
        }
        if (props.getProperty(sKey) == null && sKey.equals("primary_key")) {           
            props.setProperty(sKey, "PRIMARY KEY");
            FileUtility.saveConfiguration(props, filename);
        }
        if (props.getProperty(sKey) == null && sKey.equals("output_path")) {           
            props.setProperty(sKey, FileUtility.fixPath( System.getProperty("user.home") ) );
        }
        if (props.getProperty(sKey) == null && sKey.equals("template_path")) {           
            java.io.File folder = new java.io.File(FileUtility.CODETEMPLATE_FOLDER);
            props.setProperty(sKey, folder.getAbsolutePath());
        }
        return props.getProperty(sKey);
    }
    
    public void set(String skey, String sValue) {
        props.setProperty(skey, sValue);
        FileUtility.saveConfiguration(props, filename);
    }
}
