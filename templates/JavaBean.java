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
/*
    {[table.description]}
*/
@XmlRootElement
public class {[table.name]}Bean implements Serializable {

    {foreach field}
    private {[field.lang.Java]} {[field.name]};{/fieldforeach}

    public {[table.name]}Bean() {}

    {foreach field}
    @XmlElement
    public void set{[field.name.capitalisation]} ({[field.lang.Java]} value) { this.{[field.name]} = value; }
    public {[field.lang.Java]} get{[field.name.capitalisation]}() { return this.{[field.name]}; }
    {/fieldforeach}

    public static void saveXml({[table.name]}Bean bean, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance({[table.name]}Bean.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(bean, file);
            
        } catch (MarshalException ex1) {
            Logger.getLogger({[table.name]}Bean.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (JAXBException ex2) {
            Logger.getLogger({[table.name]}Bean.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }
    
    public static {[table.name]}Bean loadXml(File file) {
        {[table.name]}Bean bean = null;
        try {
            JAXBContext context = JAXBContext.newInstance({[table.name]}Bean.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            bean = ({[table.name]}Bean) unmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            Logger.getLogger({[table.name]}Bean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
}
