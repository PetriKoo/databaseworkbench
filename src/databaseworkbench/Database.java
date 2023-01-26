package databaseworkbench;

import databaseworkbench.beans.TableBean;
import java.util.ArrayList;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class Database {

    public static String DEFAULT_NAME = "New Database";
    
    private String databaseName = Database.DEFAULT_NAME;
    private ArrayList<TableBean> tableBeans = new ArrayList<>();

    public Database() { }
    
    public String getDatabaseName() { return databaseName; }
    public void setDatabaseName(String databaseName) { this.databaseName = databaseName; }
    
    

    public ArrayList<TableBean> getTableBeans() { return tableBeans; }
    
        
    
}
