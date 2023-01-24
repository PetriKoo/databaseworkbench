package databaseworkbench.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
@XmlRootElement(name = "Tables")
public class TablesBean implements Serializable {
    
    private ArrayList<TableBean> tables = new ArrayList<>();
    
    @XmlElement(name = "Table")
    public ArrayList<TableBean> getTables() { return tables; }
    
    public void setTables( ArrayList<TableBean> list) { this.tables = list; }
    
}
