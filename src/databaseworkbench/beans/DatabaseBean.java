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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
@XmlRootElement(name = "Database")
@XmlType(propOrder = { "databaseName", "tables" })
public class DatabaseBean  implements Serializable, BeanInterface {
    
    
    private String databaseName = "";
    private TablesBean tables = new TablesBean();

    
    public String getDatabaseName() { return databaseName; }
    
    @XmlElement(name = "Name")
    public void setDatabaseName(String databaseName) { this.databaseName = databaseName; }
        
    
    public TablesBean getTables() { return tables; }
    
    @XmlElement(name = "Tables")
    public void setTables( TablesBean list) { this.tables = list; }
    
    public static void saveXml(DatabaseBean bean, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(DatabaseBean.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(bean, file);
            
        } catch (MarshalException ex1) {
            Logger.getLogger(DatabaseBean.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (JAXBException ex2) {
            Logger.getLogger(DatabaseBean.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }
    
    public static DatabaseBean loadXml(File file) {
        DatabaseBean bean = null;
        try {
            JAXBContext context = JAXBContext.newInstance(DatabaseBean.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            bean = (DatabaseBean) unmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            Logger.getLogger(DatabaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
    
    @Override
    public String toString() {
        return this.getDatabaseName();
    }

    @Override
    public String getName() { return this.databaseName; }
    
}
