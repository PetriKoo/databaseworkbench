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

    public static final int LocalTableFieldColumn = 1;
    public static final int ForeignTableColumn = 2;
    public static final int ForeignTableFieldColumn = 3;
    
    String[] labels = {"Name", "Field", "Foreign Table", "and Field"};
    Class[] classes = {String.class, TableFieldBean.class, TableBean.class, TableFieldBean.class};
    ForeignKeyBean newKeyBean = null;
    
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
        if (bean.getForeignkeys().size() == rowIndex) {
            System.out.println("Getting new Value...");
            if (newKeyBean == null) newKeyBean = new ForeignKeyBean();
            switch (columnIndex) {
                case 0:
                    return newKeyBean.getName();
                case 1:
                    return newKeyBean.getField();
                case 2:
                    return newKeyBean.getForeigntable();
                case 3:
                    return newKeyBean.getForeignfield();
            }
        } else {            
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
        }
        return "";
    }

    @Override
    public void setNewTempRowValue(Object oValue, BeanInterface bean, int columnIndex) {
        if (this.newKeyBean == null) this.newKeyBean = new ForeignKeyBean();
        System.out.println( "Setting new Value: " + oValue);
        switch (columnIndex) {
            case 0:
                    System.out.println("Set name value");
                    this.newKeyBean.setName((String) oValue);
                break;
            case 1:
                System.out.println("Set table value");
                    this.newKeyBean.setField((TableFieldBean) oValue);                    
                break;
            case 2:
                System.out.println("Set ForeignTable value");
                    this.newKeyBean.setForeigntable((TableBean) oValue);
                break;
            case 3:
                    System.out.println("Set ForeignField value");
                    this.newKeyBean.setForeignfield((TableFieldBean) oValue);
                break;
        }
    }

    @Override
    public boolean addNewTempRow(BeanInterface beanIF) {
        if (this.isNewDataOK()) {
            TableBean bean = (TableBean) beanIF;
            bean.getForeignkeys().add( newKeyBean );
            this.deleteNewTempRow();
            return true;
        }
        return false;
    }

    @Override
    public void deleteNewTempRow() {
        newKeyBean = null;
    }

    private boolean isNewDataOK() {
        boolean bOK = true;
        if (newKeyBean.getName() == null) bOK = false;
        if (newKeyBean.getField() == null) bOK = false;
        if (newKeyBean.getForeigntable() == null) bOK = false;
        if (newKeyBean.getForeignfield() == null) {
            System.out.println("ForeignField error!");
            bOK = false;
        }
        return bOK;
    }
    
}
