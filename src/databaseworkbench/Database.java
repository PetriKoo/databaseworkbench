package databaseworkbench;

import databaseworkbench.beans.TableBean;
import java.util.ArrayList;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class Database {

    private static Database INSTANCE = null;
    public static String DEFAULT_NAME = "New Database";
    
    private String databaseName = Database.DEFAULT_NAME;
    private ArrayList<TableBean> tableBeans = new ArrayList<>();

    private Database() { }
    
    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }
    
    public String getDatabaseName() { return databaseName; }
    public void setDatabaseName(String databaseName) { this.databaseName = databaseName; }
    
    

    public ArrayList<TableBean> getTableBeans() { return tableBeans; }
    
    public TableBean getTable(String sTableName) {
        
        for (TableBean bean : tableBeans) {        
            if (bean.getName().equalsIgnoreCase( sTableName)) return bean;
        }
        return null;
    }

    public void replaceTable(String tableName, TableBean newTable) {
        TableBean removeThis = getTable( tableName );
        this.tableBeans.remove( removeThis );
        this.tableBeans.add( newTable );
    }
        
    
}
