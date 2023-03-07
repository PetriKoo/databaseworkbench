package databaseworkbench.beans;

import databaseworkbench.BeanInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
@XmlRootElement(name = "Table")
@XmlType(propOrder = { "name", "description","fields","foreignkeys" })
public class TableBean implements Serializable, BeanInterface {
    
    private String name;
    private ArrayList<TableFieldBean> fields = new ArrayList<>();
    private ArrayList<ForeignKeyBean> foreignkeys = new ArrayList<>();
    private String description;        

    
    @Override
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
    
    @Override
    public String toString() { return this.getName(); }

}
