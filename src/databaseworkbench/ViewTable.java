package databaseworkbench;

import databaseworkbench.beans.TableBean;

/**
 *
 * @author petri
 */
public class ViewTable implements ViewInterface {

    
    String[] labels = {"Key", "Name", "Type"};
    Class[] classes = {Boolean.class, String.class, FieldType.class};
    
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
    public Object getValue(TableBean bean, int rowIndex, int columnIndex) {
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
