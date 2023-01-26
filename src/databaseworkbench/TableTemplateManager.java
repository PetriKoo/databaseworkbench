package databaseworkbench;

import databaseworkbench.beans.TableBean;
import java.util.ArrayList;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class TableTemplateManager {
    
    private static TableTemplateManager INSTANCE;
    private ArrayList<TableBean> templates = new ArrayList<>();
    
    private TableTemplateManager() {}
    
    public static TableTemplateManager getInstance() {
        if (INSTANCE == null) INSTANCE = new TableTemplateManager();
        return INSTANCE;
    }

    public void add(TableBean table) {
        templates.add( table );
    }
    
    public ArrayList<TableBean> getTemplates() { return this.templates; }
    
}
