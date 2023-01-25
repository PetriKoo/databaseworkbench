package databaseworkbench;

import java.io.Serializable;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public enum LanguageEnum implements Serializable {
    
    JAVA("Java"),
    CPP("C++"),
    CSHARP("C#"),
    SCALA("Scala"),
    JSON("JSON"),
    JAVASCRIPT("JavaScript"),
    VISUALBASIC("VisualBasic"),
    PHP("Php"),
    XML("XML");
    
    private final String s;        
    
    LanguageEnum(String s) {
        this.s = s;        
    }
    
    public String getName() { return this.s; }
    
    
}