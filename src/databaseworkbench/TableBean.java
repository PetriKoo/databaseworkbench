package databaseworkbench;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author petri
 */
public class TableBean {
    
    private String name;
    private ArrayList<TableFieldBean> fields = new ArrayList<>();
    private final ArrayList<ForeignKey> foreignkeys = new ArrayList<>();
    private String description;        

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ArrayList<TableFieldBean> getFields() { return fields; }

    public ArrayList<ForeignKey> getForeignkeys() { return foreignkeys; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    void moveUp(int selectedRow) {
        try {    
            Collections.swap(fields, selectedRow, selectedRow - 1);
        } catch (IndexOutOfBoundsException ex1) {}
    }

    void moveDown(int selectedRow) {
        try {    
            Collections.swap(fields, selectedRow, selectedRow + 1);
        } catch (IndexOutOfBoundsException ex1) {}
    }

}
