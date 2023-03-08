package databaseworkbench;

import databaseworkbench.beans.TableBean;
import databaseworkbench.beans.TablesBean;
import databaseworkbench.frames.TemplateListFrame;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public final class TableTemplateManager {
    
    private static TableTemplateManager INSTANCE;
    private ArrayList<TableBean> templates = new ArrayList<>();    
    
    private TableTemplateManager() {
        this.load();
    }
    
    public static TableTemplateManager getInstance() {
        if (INSTANCE == null) INSTANCE = new TableTemplateManager();
        return INSTANCE;
    }
    
    public TableBean getTable(String sTableName) {
        for(TableBean bean: this.templates) {
            if (bean.getName().equalsIgnoreCase(sTableName)) return bean;
        }
        return null;
    }
    
    public void putTable(TableBean bean) {
        this.templates.add( bean );
        this.save();
    }
    
    public void removeTable(String sTableName) {
        for(TableBean bean: this.templates) {
            if (bean.getName().equalsIgnoreCase(sTableName)) { 
                this.templates.remove(bean);
                this.save();
            }
        }
    }
    
    public void save() {
        TablesBean temps = new TablesBean();
        temps.setTables( this.templates );
        FileUtility.saveXml(temps, new File(Configs.getInstance().get("working_dir") + FileUtility.TEMPLATE_FILE), TablesBean.class);
    }
    
    public void load() {
        Object o = FileUtility.loadXml( new File(Configs.getInstance().get("working_dir") + FileUtility.TEMPLATE_FILE) , TablesBean.class);
        if (o != null) {
            TablesBean temps = (TablesBean) o;
            this.templates = temps.getTables();
        }
    }
    
    public ArrayList<TableBean> getTemplates() { return this.templates; }

    public void update() {
        this.save();
        TemplateListFrame.getInstance().putData( templates );
    }
    
}
