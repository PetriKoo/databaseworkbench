package databaseworkbench;

/**
 *
 * @author petri
 */
public class ForeignKey {
    
    private String field;
    private String foreigntable;
    private String foreignfield;

    public String getField() { return field; }
    public void setField(String field) { this.field = field; }

    public String getForeigntable() { return foreigntable; }
    public void setForeigntable(String foreigntable) { this.foreigntable = foreigntable; }

    public String getForeignfield() { return foreignfield; }
    public void setForeignfield(String foreignfield) { this.foreignfield = foreignfield; }
        
}
