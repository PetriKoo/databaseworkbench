package databaseworkbench;

/**
 *
 * @author petri
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
