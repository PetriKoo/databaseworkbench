package databaseworkbench;

import databaseworkbench.beans.DatabaseBean;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class FileUtility {
    
    public static final String FileExtension = ".xml";
    public static final String DATABASE_FOLDER = "databases";
    public static final String TABLE_FOLDER = "tables";
    public static final String TEMPLATE_FILE = "templates" + FileUtility.FileExtension;
    
    public static Object loadXml(File file, Class c) {
        Object oReturn = null;
        try {
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(c);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                oReturn = unmarshaller.unmarshal(file);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(DatabaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oReturn;
    }
        
    public static void saveXml(Object o, File file, Class c) {
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(o, file);            
        } catch (MarshalException ex1) {
            Logger.getLogger(DatabaseBean.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (JAXBException ex2) {
            Logger.getLogger(DatabaseBean.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }
    
    public static void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }
}
