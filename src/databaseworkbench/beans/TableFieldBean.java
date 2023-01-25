package databaseworkbench.beans;

import databaseworkbench.BeanInterface;
import databaseworkbench.FieldTypeEnum;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
@XmlRootElement(name = "Field")
@XmlType(propOrder = { "table", "name", "type", "primarykey", "auto_increment" ,"notnull", "unique" ,"default_value", "description" })
public class TableFieldBean implements Serializable, BeanInterface {
        
    private String name = "";
    private FieldTypeEnum type = FieldTypeEnum.INTEGER;
    private boolean primarykey = false;
    private boolean auto_increment = false;
    private boolean notnull = false;
    private boolean unique = false;
    private String default_value = "";
    private String description = "";

    private String table = "";
    
    @Override
    public String getName() { return name; }
    @XmlElement(name = "Name")
    public void setName(String name) { this.name = name; }

    public String getTable() { return table; }
    @XmlElement(name = "Table")
    public void setTable(String table) { this.table = table; }

    
    
    public FieldTypeEnum getType() { return type; }
    @XmlElement(name = "Type")
    public void setType(FieldTypeEnum type) { this.type = type; }

    
    public boolean isPrimarykey() { return primarykey; }
    @XmlElement(name = "PrimaryKey")
    public void setPrimarykey(boolean primarykey) { this.primarykey = primarykey; }

    
    public boolean isAuto_increment() { return auto_increment; }
    @XmlElement(name = "Auto_increment")
    public void setAuto_increment(boolean auto_increment) { this.auto_increment = auto_increment; }

    
    public boolean isNotnull() { return notnull; }
    @XmlElement(name = "Notnull")
    public void setNotnull(boolean notnull) { this.notnull = notnull; }

    
    public boolean isUnique() { return unique; }
    @XmlElement(name = "Unique")
    public void setUnique(boolean unique) { this.unique = unique; }

    
    public String getDefault_value() { return default_value; }
    @XmlElement(name = "Default_value")
    public void setDefault_value(String default_value) { this.default_value = default_value; }

    
    public String getDescription() { return description; }
    @XmlElement(name = "Description")
    public void setDescription(String description) { this.description = description; }
    
   public static void saveObject(TableFieldBean bean, File file) {
        try {
            FileOutputStream fileStream = new FileOutputStream( file );
            ObjectOutputStream objectStream = new ObjectOutputStream( fileStream );
            objectStream.writeObject( bean );
            objectStream.close();
            fileStream.close();
        } catch (IOException ex) {
            Logger.getLogger(TableFieldBean.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TableFieldBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
    
    @Override
    public String toString() { return this.getName(); }
    
}
