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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author petri
 */
@XmlRootElement(name = "ForeignKey")
public class ForeignKeyBean implements Serializable {
    
    private String name;
    private TableFieldBean field;
    private TableBean foreigntable;
    private TableFieldBean foreignfield;

    
    public String getName() { return name; }
    @XmlElement(name = "Name")
    public void setName(String name) { this.name = name; }
        
    
    public TableFieldBean getField() { return field; }
    @XmlElement(name = "Field")
    public void setField(TableFieldBean field) { this.field = field; }

    
    public TableBean getForeigntable() { return foreigntable; }
    @XmlElement(name = "Foreigntable")
    public void setForeigntable(TableBean foreigntable) { this.foreigntable = foreigntable; }

    
    public TableFieldBean getForeignfield() { return foreignfield; }
    @XmlElement(name = "Foreignfield")
    public void setForeignfield(TableFieldBean foreignfield) { this.foreignfield = foreignfield; }
    
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
            Logger.getLogger(ForeignKeyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }    
}
