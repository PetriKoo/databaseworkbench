package databaseworkbench.beans;

import databaseworkbench.FileUtility;
import java.io.File;
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

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
@XmlRootElement(name = "languagecodes")
public class LanguageCodesBean implements Serializable {
    
    public static File createFile(LanguageBean newBean) {
        File newFile = new File(LanguageCodesBean.folderName + File.separatorChar + newBean.getName() + FileUtility.FileExtension);
        return newFile;
    }
    
    public File getMyFile() {
        File file = new File(LanguageCodesBean.folderName + File.separatorChar + this.getName() + FileUtility.FileExtension);
        return file;
    }
    
    private String name;
    public final static String folderName = "languagecodes";
    private CodeTypeBean[] codes;
    
    public LanguageCodesBean() {}
    public LanguageCodesBean(String name) { this.name = name; }
    
    public String getName() { return name; }

    @XmlElement(name = "name")
    public void setName(String name) { this.name = name; }

    public CodeTypeBean[] getCodes() { return codes; }

    @XmlElement(name = "codes")
    public void setCodes(CodeTypeBean[] codes) { this.codes = codes; }
    
    public static void saveXml(LanguageCodesBean bean, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(LanguageCodesBean.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(bean, file);
            
        } catch (MarshalException ex1) {
            Logger.getLogger(LanguageCodesBean.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (JAXBException ex2) {
            Logger.getLogger(LanguageCodesBean.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }
    
    public static LanguageCodesBean loadXml(File file) {
        LanguageCodesBean bean = new LanguageCodesBean();
        try {
            JAXBContext context = JAXBContext.newInstance(LanguageCodesBean.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            bean = (LanguageCodesBean) unmarshaller.unmarshal(file);
        } catch (IllegalArgumentException | JAXBException ex) {
            Logger.getLogger(LanguageCodesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
}
