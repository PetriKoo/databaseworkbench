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
@XmlRootElement(name = "CodeType")
@XmlType(propOrder = { "language", "type", "inCodeText" })
public class CodeTypeBean implements Serializable {
    
    private FieldtypeBean type;
    private LanguageBean language;
    private String inCodeText = "";

    public FieldtypeBean getType() { return type; }

    @XmlElement(name = "Type")
    public void setType(FieldtypeBean type) { this.type = type; }

    public LanguageBean getLanguage() { return language; }

    @XmlElement(name = "Lang")
    public void setLanguage(LanguageBean language) { this.language = language; }

    public String getInCodeText() { return inCodeText; }

    @XmlElement(name = "inCodeText")
    public void setInCodeText(String inCodeText) { this.inCodeText = inCodeText; }
    
    public static void saveXml(CodeTypeBean bean, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(CodeTypeBean.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(bean, file);
            
        } catch (MarshalException ex1) {
            Logger.getLogger(CodeTypeBean.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (JAXBException ex2) {
            Logger.getLogger(CodeTypeBean.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }
    
    public static CodeTypeBean loadXml(File file) {
        CodeTypeBean bean = null;
        try {
            JAXBContext context = JAXBContext.newInstance(CodeTypeBean.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            bean = (CodeTypeBean) unmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            Logger.getLogger(CodeTypeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
    
    @Override
    public String toString() { return this.inCodeText; }
    
}
