package databaseworkbench.beans;

import databaseworkbench.BeanInterface;
import databaseworkbench.FileUtility;
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
@XmlRootElement(name = "Language")
public class LanguageBean implements Serializable, BeanInterface {
    
    public static File createFile(LanguageBean newBean) {
        File newFile = new File(LanguageBean.folderName + File.separatorChar + newBean.getName() + FileUtility.XmlFileExtension);
        return newFile;
    }
    
    public File getMyFile() {
        File file = new File(LanguageBean.folderName + File.separatorChar + this.getName() + FileUtility.XmlFileExtension);
        return file;
    }
    
    private String name;
    private final static String folderName = "languages";

    public LanguageBean() {}
    
    public LanguageBean(String name) { this.name = name; }
    
    @Override
    public String getName() { return name; }
    
    @XmlElement(name = "Name")
    public void setName(String name) { this.name = name; }
    
    @Override
    public String toString() { return this.name; }
    
    public static LanguageBean[] values() {        
        ArrayList<LanguageBean> alReturn = new ArrayList<>();
        LanguageBean one;
        
        for (File file : listFiles()) {
            one = LanguageBean.loadXml(file);
            if (one != null) alReturn.add(one);
        }
        LanguageBean[] oReturn = new LanguageBean[alReturn.size()];
        int i = 0;
        for (LanguageBean bean : alReturn) {
            oReturn[i] = bean;
            i++;
        }
        return oReturn;
    }
    
    private static File[] listFiles() {
        File typeFolder = new File(LanguageBean.folderName);
        
        if (typeFolder.exists()) {
            if (typeFolder.isDirectory()) {
                
            } else {
                typeFolder.mkdir();
            }
        } else {
            typeFolder.mkdir();
        }
        File[] files = typeFolder.listFiles(FileUtility.dbFileFilterXml());
        return files;
    }
    
    public static LanguageBean loadXml(File file) {
        LanguageBean bean = null;
        try {
            JAXBContext context = JAXBContext.newInstance(LanguageBean.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            bean = (LanguageBean) unmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            Logger.getLogger(LanguageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
    
    public static void saveXml(LanguageBean bean, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(LanguageBean.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(bean, file);
            
        } catch (MarshalException ex1) {
            Logger.getLogger(LanguageBean.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (JAXBException ex2) {
            Logger.getLogger(LanguageBean.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }    
}
