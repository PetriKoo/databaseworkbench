package databaseworkbench.settings.code;

import databaseworkbench.beans.CodeTypeBean;
import databaseworkbench.beans.FieldtypeBean;
import databaseworkbench.beans.LanguageBean;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class CodesModel extends AbstractTableModel {
    
    private static CodesModel INSTANCE = null;
    
    private final String[] columnNames = {"Field", "Code"};
    private FieldtypeBean[] fieldtypes;
    private LanguageBean[] languages;
    private LanguageBean selectedLanguage;
    private CodeTypeBean[] codes;
            
    private CodesModel() {
        this.update();
        this.setLanguage( languages[0] );
        this.buildEmptyIfNotExist();
    }
    
    public static CodesModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CodesModel();            
        }
        return INSTANCE;
    }
    
    public void setLanguage(LanguageBean language) { 
        this.selectedLanguage = language; 
        buildEmptyIfNotExist();
    }
    public LanguageBean getSelectedLanguage() { return this.selectedLanguage; }
    
    public void buildEmptyIfNotExist() {
        update();
        codes = new CodeTypeBean[fieldtypes.length];
        CodeTypeBean codetype;
        int i = 0;
        for(FieldtypeBean fieldtype : fieldtypes) {
            codetype = new CodeTypeBean();
            codetype.setLanguage( selectedLanguage );
            codetype.setType( fieldtype );
            codetype.setInCodeText( "" );
            codes[i] = codetype;
            i++;
        }
        this.fireTableDataChanged();
    }
    
    public void update() {
        fieldtypes = FieldtypeBean.values();
        languages = LanguageBean.values();
    }

    @Override
    public int getRowCount() {
        return fieldtypes.length;
    }
    
    @Override
    public Class<?> getColumnClass( int column) {
        return super.getColumnClass(column);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override 
    public boolean isCellEditable(int row, int column) {
        return column != 0;
    }
    
    @Override
    public String getColumnName( int column) { return columnNames[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return fieldtypes[rowIndex].getName();                
            case 1:
                return codes[rowIndex].getInCodeText();
        }
        return "";
    }
    
    @Override
    public void setValueAt(Object oValue, int row, int column) {
        codes[row].setInCodeText( oValue.toString() );
    }
    
    
}
