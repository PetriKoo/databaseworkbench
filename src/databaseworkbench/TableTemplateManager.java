package databaseworkbench;

import databaseworkbench.beans.TableBean;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class TableTemplateManager {
    
    private static TableTemplateManager INSTANCE;
    
    private TableTemplateManager() {}
    
    public static TableTemplateManager getInstance() {
        if (INSTANCE == null) INSTANCE = new TableTemplateManager();
        return INSTANCE;
    }

    public void add(TableBean table) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
