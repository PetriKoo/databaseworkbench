package databaseworkbench.beans;

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
    
    private String name;
    private CodeTypeBean[] codes;
    
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
        LanguageCodesBean bean = null;
        try {
            JAXBContext context = JAXBContext.newInstance(LanguageCodesBean.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            bean = (LanguageCodesBean) unmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            Logger.getLogger(LanguageCodesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
}
