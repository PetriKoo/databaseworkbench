/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseworkbench;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author petri
 */
public class File {
    
    public static void save(StringBuffer sb, String sFilename) {
        BufferedWriter bfilew = null;
        try {
            bfilew = new BufferedWriter( new FileWriter(sFilename) );
            bfilew.write(sb.toString());
            bfilew.flush();
            bfilew.close();
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bfilew != null) bfilew.close();
            } catch (IOException ex) {
                Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    
    public static String load(String sFilename) {
        Path filePath = Path.of(sFilename);
        String sData = null;
        try {
            sData = Files.readString(filePath);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bfilew != null) bfilew.close();
            } catch (IOException ex) {
                Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    
    public static void saveConfiguration(Properties props, String sFilename) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(sFilename);
            props.store(fos, null);
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (IOException ex) {
                Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException ex) {
                Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return props;
    }
    
}
