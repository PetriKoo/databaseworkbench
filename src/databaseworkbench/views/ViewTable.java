package databaseworkbench.views;

import databaseworkbench.BeanInterface;
import databaseworkbench.FieldTypeEnum;
import databaseworkbench.beans.TableBean;

/**
 *
 * @author petri
 */
public class ViewTable implements ViewInterface {

    
    String[] labels = {"Key", "Name", "Type"};
    Class[] classes = {Boolean.class, String.class, FieldTypeEnum.class};
    
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
        switch (columnIndex) {
            case 0:
                return bean.getFields().get( rowIndex ).isPrimarykey();
            case 1:
                return bean.getFields().get( rowIndex ).getName();
            case 2:
                return bean.getFields().get( rowIndex ).getType();
        }
        return "";
    }
    
}
