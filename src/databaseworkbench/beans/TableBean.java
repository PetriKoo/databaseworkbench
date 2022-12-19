package databaseworkbench.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author petri
 */
@XmlRootElement(name = "Table")
public class TableBean implements Serializable {
    
    private String name;
    private ArrayList<TableFieldBean> fields = new ArrayList<>();
    private ArrayList<ForeignKeyBean> foreignkeys = new ArrayList<>();
    private String description;        

    
    public String getName() { return name; }
    @XmlElement(name = "Name")
    public void setName(String name) { this.name = name; }

    
    public ArrayList<TableFieldBean> getFields() { return fields; }
    @XmlElement(name = "Fields")
    public void setFields(ArrayList<TableFieldBean> list) { this.fields = list; }
    
    
    public ArrayList<ForeignKeyBean> getForeignkeys() { return foreignkeys; }
    @XmlElement(name = "Foreignkeys")
    public void setForeignkeys(ArrayList<ForeignKeyBean> list) { this.foreignkeys = list; }
    
    
    public String getDescription() { return description; }
    @XmlElement(name = "Description")
    public void setDescription(String description) { this.description = description; }

    
    public void moveUp(int selectedRow) {
        try {    
            Collections.swap(fields, selectedRow, selectedRow - 1);
        } catch (IndexOutOfBoundsException ex1) {}
    }

    
    public void moveDown(int selectedRow) {
        try {    
            Collections.swap(fields, selectedRow, selectedRow + 1);
        } catch (IndexOutOfBoundsException ex1) {}
    }
    
    public static void saveObject(TableBean bean, File file) {
        try {
            FileOutputStream fileStream = new FileOutputStream( file );
            ObjectOutputStream objectStream = new ObjectOutputStream( fileStream );
            objectStream.writeObject( bean );
            objectStream.close();
            fileStream.close();
        } catch (IOException exp1) {
            
        }
    }
    
    public static TableBean loadObject(File file) {
        TableBean bean = null;
        try {
            FileInputStream fileStream = new FileInputStream( file );
            ObjectInputStream objectStream = new ObjectInputStream( fileStream );
            bean = (TableBean) objectStream.readObject();
            objectStream.close();
            fileStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TableBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }

}
