package databaseworkbench.beans;

import databaseworkbench.BeanInterface;
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
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
@XmlRootElement(name = "ForeignKey")
public class ForeignKeyBean implements Serializable, BeanInterface {
    
    private String name = null;
    private TableFieldBean field = null;
    private TableBean foreigntable = null;
    private TableFieldBean foreignfield = null;

    public ForeignKeyBean() {
        
    }
    
    @Override
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
      
}
