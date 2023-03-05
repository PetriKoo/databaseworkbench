package databaseworkbench.views;

import databaseworkbench.BeanInterface;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public interface ViewInterface {

    public String getLabelText(int c);

    public int getCount();

    public Class<?> getClass(int c);

    public Object getValue(BeanInterface bean, int rowIndex, int columnIndex);
    
    public void setNewTempRowValue(Object oValue, BeanInterface bean, int columnIndex);
    
    public boolean addNewTempRow(BeanInterface bean);
    
    public void deleteNewTempRow();   
    
}
