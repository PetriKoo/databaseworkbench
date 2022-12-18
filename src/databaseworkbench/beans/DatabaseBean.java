package databaseworkbench.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author petri
 */
public class DatabaseBean implements Serializable {
    
    private String databaseName = "";
    private ArrayList<TableBean> tables = new ArrayList<>();

    public String getDatabaseName() { return databaseName; }
    public void setDatabaseName(String databaseName) { this.databaseName = databaseName; }
    
    public ArrayList<TableBean> getTables() { return tables; }
    
    public static void saveObject(DatabaseBean bean, File file) {
        try {
            FileOutputStream fileStream = new FileOutputStream( file );
            ObjectOutputStream objectStream = new ObjectOutputStream( fileStream );
            objectStream.writeObject( bean );
            objectStream.close();
            fileStream.close();
        } catch (IOException ex) {
            Logger.getLogger(TableBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DatabaseBean loadObject(File file) {
        DatabaseBean bean = null;
        try {
            FileInputStream fileStream = new FileInputStream( file );
            ObjectInputStream objectStream = new ObjectInputStream( fileStream );
            bean = (DatabaseBean) objectStream.readObject();
            objectStream.close();
            fileStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TableBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
    
    @Override
    public String toString() {
        return this.getDatabaseName();
    }
}
