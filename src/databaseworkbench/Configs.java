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
        return props.getProperty(sKey);
    }
    
    public void set(String skey, String sValue) {
        props.setProperty(skey, sValue);
        File.saveConfiguration(props, filename);
    }
}
