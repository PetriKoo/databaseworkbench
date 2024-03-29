package databaseworkbench.codes;


import javax.swing.DefaultListSelectionModel;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class TableBeanListSelectionModel extends DefaultListSelectionModel {
    
    @Override
    public void setSelectionInterval(int index0, int index1) {
        if(super.isSelectedIndex(index0)) {
            super.removeSelectionInterval(index0, index1);
        }
        else {
            super.addSelectionInterval(index0, index1);
        }
    }

}
