package databaseworkbench.beans;

import databaseworkbench.FieldTypeEnum;
import databaseworkbench.LanguageEnum;
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
@XmlType(propOrder = { "language", "type", "incodetext" })
public class CodeTypeBean implements Serializable {
    
    private FieldTypeEnum type;
    private LanguageEnum language;
    private String inCodeText = "";

    public FieldTypeEnum getType() { return type; }

    @XmlElement(name = "Type")
    public void setType(FieldTypeEnum type) { this.type = type; }

    public LanguageEnum getLanguage() { return language; }

    @XmlElement(name = "Lang")
    public void setLanguage(LanguageEnum language) { this.language = language; }

    public String getInCodeText() { return inCodeText; }

    @XmlElement(name = "Text")
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
    
    public static void saveObject(CodeTypeBean bean, File file) {
        try {
            FileOutputStream fileStream = new FileOutputStream( file );
            ObjectOutputStream objectStream = new ObjectOutputStream( fileStream );
            objectStream.writeObject( bean );
            objectStream.close();
            fileStream.close();
        } catch (IOException ex) {
            Logger.getLogger(CodeTypeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static CodeTypeBean loadObject(File file) {
        CodeTypeBean bean = null;
        try {
            FileInputStream fileStream = new FileInputStream( file );
            ObjectInputStream objectStream = new ObjectInputStream( fileStream );
            bean = (CodeTypeBean) objectStream.readObject();
            objectStream.close();
            fileStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CodeTypeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
    
    @Override
    public String toString() { return this.inCodeText; }
    
}
