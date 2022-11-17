package databaseworkbench;

/**
 *
 * @author petri
 */
public class TableFieldBean {
        
    private String name = "";
    private FieldType type = FieldType.INTEGER;
    private boolean primarykey = false;
    private boolean auto_increment = false;
    private boolean notnull = false;
    private boolean unique = false;
    private String default_value = "";
    private String description = "";

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public FieldType getType() { return type; }
    public void setType(FieldType type) { this.type = type; }

    public boolean isPrimarykey() { return primarykey; }
    public void setPrimarykey(boolean primarykey) { this.primarykey = primarykey; }

    public boolean isAuto_increment() { return auto_increment; }
    public void setAuto_increment(boolean auto_increment) { this.auto_increment = auto_increment; }

    public boolean isNotnull() { return notnull; }
    public void setNotnull(boolean notnull) { this.notnull = notnull; }

    public boolean isUnique() { return unique; }
    public void setUnique(boolean unique) { this.unique = unique; }

    public String getDefault_value() { return default_value; }
    public void setDefault_value(String default_value) { this.default_value = default_value; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
        
}
