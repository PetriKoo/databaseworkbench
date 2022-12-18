package databaseworkbench.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author petri
 */
public class ForeignKeyBean implements Serializable {
    
    private String field;
    private String foreigntable;
    private String foreignfield;

    public String getField() { return field; }
    public void setField(String field) { this.field = field; }

    public String getForeigntable() { return foreigntable; }
    public void setForeigntable(String foreigntable) { this.foreigntable = foreigntable; }

    public String getForeignfield() { return foreignfield; }
    public void setForeignfield(String foreignfield) { this.foreignfield = foreignfield; }
    
    public static void saveObject(ForeignKeyBean bean, File file) {
        try {
            FileOutputStream fileStream = new FileOutputStream( file );
            ObjectOutputStream objectStream = new ObjectOutputStream( fileStream );
            objectStream.writeObject( bean );
            objectStream.close();
            fileStream.close();
        } catch (IOException exp1) {
            
        }
    }
    
    public static ForeignKeyBean loadObject(File file) {
        ForeignKeyBean bean = null;
        try {
            FileInputStream fileStream = new FileInputStream( file );
            ObjectInputStream objectStream = new ObjectInputStream( fileStream );
            bean = (ForeignKeyBean) objectStream.readObject();
            objectStream.close();
            fileStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TableBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }    
}
