package databaseworkbench;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public enum FieldTypeEnum {    
    INTEGER ("Int"),     
    DOUBLE("Double"),
    CURRENCY("Currency"),
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