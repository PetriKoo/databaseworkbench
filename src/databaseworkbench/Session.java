package databaseworkbench;

import java.util.Properties;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class Session {
    
    private static Session INSTANCE = null;
    Properties props = new Properties();
    
    public static final String TEMPLATESPATH_KEY = "templates_path";
    
    private Session() {
        
    }
    
    public static Session getInstance() {
        if (INSTANCE == null) INSTANCE = new Session();
        return INSTANCE;
    }
    
    public String getValue(String sKey) { return props.getProperty( sKey ); }
    
    public void setValue(String sKey, String sValue) { props.setProperty(sKey, sValue); }
    
    public boolean isSet(String sKey) { return props.containsKey(sKey); }
}
