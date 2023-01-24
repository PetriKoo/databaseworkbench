package databaseworkbench;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class TableManager {
    
    private static TableManager INSTANCE = null;
    
    private TableManager() {
        
    }
    
    public static TableManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TableManager();
        }
        return INSTANCE;
    }
}
