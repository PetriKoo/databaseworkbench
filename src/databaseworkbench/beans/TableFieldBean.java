package databaseworkbench.beans;

import databaseworkbench.FieldType;
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
public class TableFieldBean implements Serializable {
        
    private String name = "";
    private FieldType type = FieldType.INTEGER;
    private boolean primarykey = false;
    private boolean auto_increment = false;
    private boolean notnull = false;
    private boolean unique = false;
    private String default_value = "";
    private String description = "";

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public FieldType getType() { return type; }
    public void setType(FieldType type) { this.type = type; }

    public boolean isPrimarykey() { return primarykey; }
    public void setPrimarykey(boolean primarykey) { this.primarykey = primarykey; }

    public boolean isAuto_increment() { return auto_increment; }
    public void setAuto_increment(boolean auto_increment) { this.auto_increment = auto_increment; }

    public boolean isNotnull() { return notnull; }
    public void setNotnull(boolean notnull) { this.notnull = notnull; }

    public boolean isUnique() { return unique; }
    public void setUnique(boolean unique) { this.unique = unique; }

    public String getDefault_value() { return default_value; }
    public void setDefault_value(String default_value) { this.default_value = default_value; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
   public static void saveObject(TableFieldBean bean, File file) {
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
    
    public static TableFieldBean loadObject(File file) {
        TableFieldBean bean = null;
        try {
            FileInputStream fileStream = new FileInputStream( file );
            ObjectInputStream objectStream = new ObjectInputStream( fileStream );
            bean = (TableFieldBean) objectStream.readObject();
            objectStream.close();
            fileStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TableBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
    
}
