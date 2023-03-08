package databaseworkbench;

import databaseworkbench.beans.DatabaseBean;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
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
    
    public static final String XmlFileExtension = ".xml";
    
    public static final String DATABASE_FOLDER = "databases";    
    public static final String LANGUAGECODES_FOLDER = "languagecodes";
    public static final String CODETEMPLATE_FOLDER = "templates";
    
    public static final String TEMPLATE_FILE = "tabletemplates" + FileUtility.XmlFileExtension;
    
       public static void save(StringBuffer sb, String sFilename) {
        BufferedWriter bfilew = null;
        try {
            bfilew = new BufferedWriter( new FileWriter(sFilename) );
            bfilew.write(sb.toString());
            bfilew.flush();
            bfilew.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bfilew != null) bfilew.close();
            } catch (IOException ex) {
                Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    
    public static String load(String sFilename) {
        Path filePath = Path.of(sFilename);
        String sData = null;
        try {
            sData = Files.readString(filePath);
        } catch (IOException ex) {
            Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sData;
    }
    
    public static void save(String sData, String sFilename) {
        BufferedWriter bfilew = null;
        try {
            bfilew = new BufferedWriter( new FileWriter(sFilename) );
            bfilew.write(sData);
            bfilew.flush();            
        } catch (IOException ex) {
            Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bfilew != null) bfilew.close();
            } catch (IOException ex) {
                Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    
    public static void saveConfiguration(Properties props, String sFilename) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(sFilename);
            props.store(fos, null);
        } catch (IOException ex) {
            Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException ex) {
                Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public static Properties loadConfiguration(String sFilename) {
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            
            java.io.File fFile = new java.io.File(sFilename);
            if (fFile.exists()) {
                fis = new FileInputStream(sFilename);
                props.load(fis);
            } else {
                saveConfiguration(props, sFilename);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException ex) {
                Logger.getLogger(FileUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return props;
    }
    
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
    
    public static void createFolder(File folder) {
        if (folder.exists()) {
            if (!folder.isDirectory()) {
                folder.mkdir();
            }
        } else {
            folder.mkdir();
        }
    }
    
    public static void deleteFile(File file) {
        if (file.exists()) {
            file.delete();
        }
    }
    
    public static String fixPath (String sPath) {
        if (sPath.charAt(sPath.length() -1) != java.io.File.separatorChar) sPath = sPath + java.io.File.separatorChar;
        return sPath;
    }
    
    public static FilenameFilter dbFileFilterXml() {
        FilenameFilter dbFilter = new FilenameFilter() {
            @Override
            public boolean accept(File directory, String filename) {
                if (filename.endsWith(".xml")) {
                    return true;
                } else return false;
            }
            
        };
        return dbFilter;
    }
}
