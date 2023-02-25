package databaseworkbench.beans;

import databaseworkbench.FileUtility;
import databaseworkbench.Tools;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
@XmlRootElement(name = "Fieldtype")
public class FieldtypeBean implements Serializable {

    public static File createFile(FieldtypeBean newBean) {
        File newFile = new File(FieldtypeBean.folderName + File.separatorChar + newBean.getName() + FileUtility.FileExtension);
        return newFile;
    }
    
    public File getMyFile() {
        File file = new File(FieldtypeBean.folderName + File.separatorChar + this.getName() + FileUtility.FileExtension);
        return file;
    }
    
    private String name;
    private final static String folderName = "fieldtypes";

    public FieldtypeBean() {}
    
    public FieldtypeBean(String name) { this.name = name; }
    
    public String getName() { return name; }

    @XmlElement(name = "Name")
    public void setName(String name) { this.name = name; }
    
    @Override
    public String toString() { return name; }
    
    public static FieldtypeBean[] values() {        
        ArrayList<FieldtypeBean> alReturn = new ArrayList<>();
        FieldtypeBean one;
        
        for (File file : listFiles()) {
            one = FieldtypeBean.loadXml(file);
            if (one != null) alReturn.add(one);
        }
        FieldtypeBean[] oReturn = new FieldtypeBean[alReturn.size()];
        int i = 0;
        for (FieldtypeBean bean : alReturn) {
            oReturn[i] = bean;
            i++;
        }
        return oReturn;
    }
    
    private static File[] listFiles() {
        File typeFolder = new File(FieldtypeBean.folderName);
        
        if (typeFolder.exists()) {
            if (typeFolder.isDirectory()) {
                
            } else {
                typeFolder.mkdir();
            }
        } else {
            typeFolder.mkdir();
        }
        File[] files = typeFolder.listFiles(Tools.dbFileFilterXml());
        return files;
    }
    
    public static FieldtypeBean loadXml(File file) {
        FieldtypeBean bean = null;
        try {
            JAXBContext context = JAXBContext.newInstance(FieldtypeBean.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            bean = (FieldtypeBean) unmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            Logger.getLogger(FieldtypeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
    
    public static void saveXml(FieldtypeBean bean, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(FieldtypeBean.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(bean, file);
            
        } catch (MarshalException ex1) {
            Logger.getLogger(FieldtypeBean.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (JAXBException ex2) {
            Logger.getLogger(FieldtypeBean.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }
}
