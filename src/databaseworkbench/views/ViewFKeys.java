package databaseworkbench.views;

import databaseworkbench.BeanInterface;
import databaseworkbench.beans.ForeignKeyBean;
import databaseworkbench.beans.TableBean;
import databaseworkbench.beans.TableFieldBean;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class ViewFKeys implements ViewInterface {

    public static final int LocalTableColumn = 1;
    public static final int ForeignTableColumn = 2;
    public static final int ForeignTableFieldColumn = 3;
    
    String[] labels = {"Name", "Field", "Foreign Table", "and Field"};
    Class[] classes = {String.class, TableFieldBean.class, TableBean.class, TableFieldBean.class};        
    
    @Override
    public String getLabelText(int c) {
        return labels[c];
    }

    @Override
    public int getCount() {
        return labels.length;
    }

    @Override
    public Class<?> getClass(int c) {
        return classes[c];
    }

    @Override
    public Object getValue(BeanInterface beanIF, int rowIndex, int columnIndex) {
        TableBean bean = (TableBean) beanIF;
        ForeignKeyBean keyBean = bean.getForeignkeys().get( rowIndex );
        switch (columnIndex) {
            case 0:
                return keyBean.getName();
            case 1:
                return keyBean.getField();
            case 2:
                return keyBean.getForeigntable();
            case 3:
                return keyBean.getForeignfield();
        }
        return "";
    }
    
}
