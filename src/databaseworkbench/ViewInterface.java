package databaseworkbench;

import databaseworkbench.beans.TableBean;

/**
 *
 * @author petri
 */
public interface ViewInterface {

    public String getLabelText(int c);

    public int getCount();

    public Class<?> getClass(int c);

    public Object getValue(TableBean bean, int rowIndex, int columnIndex);
    
}
