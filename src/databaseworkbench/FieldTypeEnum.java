package databaseworkbench;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public enum FieldTypeEnum {    
    ID ("ID number"),     
    INTEGER ("Int"),     
    DOUBLE("Double"),
    CURRENCY("Currency"),
    NAME ("Name"),
    TEXT ("Short text"),
    LONGTEXT ("Long text");
    
    private final String text;
    
    FieldTypeEnum (String s) {
        this.text = s;
    }
    
    @Override
    public String toString() {
        return text;
    }
}