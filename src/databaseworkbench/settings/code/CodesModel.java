package databaseworkbench.settings.code;

import databaseworkbench.beans.CodeTypeBean;
import databaseworkbench.beans.FieldtypeBean;
import databaseworkbench.beans.LanguageBean;
import databaseworkbench.beans.LanguageCodesBean;
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
        LanguageCodesBean LCB = new LanguageCodesBean( this.selectedLanguage.getName() );
        if (!LCB.getMyFile().exists()) {
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
            LCB.setCodes( codes );
            LanguageCodesBean.saveXml(LCB, LCB.getMyFile() );
        } else {
            LCB = LanguageCodesBean.loadXml( LCB.getMyFile() );
            codes = LCB.getCodes();
            if (codes.length != fieldtypes.length) {
                CodeTypeBean[] newCodes = new CodeTypeBean[fieldtypes.length];
                CodeTypeBean codetype;
                int i = 0;
                for (CodeTypeBean oldCode : codes) {
                    newCodes[i] = oldCode;
                    i++;
                }
                for (FieldtypeBean fieldtype : this.fieldtypes) {
                    if (!this.contains(fieldtype, newCodes)) {
                        codetype = new CodeTypeBean();
                        codetype.setLanguage( selectedLanguage );
                        codetype.setType( fieldtype );
                        codetype.setInCodeText( "" );
                        codes[i] = codetype;
                        i++;
                    }
                }
                this.codes = newCodes;
                LCB.setCodes( codes );
                LanguageCodesBean.saveXml(LCB, LCB.getMyFile() );
            }
        }
        this.fireTableDataChanged();
    }
    
    private boolean contains(FieldtypeBean bean, CodeTypeBean[] theArray) {
        for (CodeTypeBean oldCode : codes) {
            if (bean.getName().equals(oldCode.getType().getName())) return true;
        }
        return false;
    }
    
    public void update() {
        fieldtypes = FieldtypeBean.values();
        languages = LanguageBean.values();
    }
    
    private void save() {
        LanguageCodesBean LCB = new LanguageCodesBean( this.selectedLanguage.getName() );
        LCB.setCodes( codes );
        LanguageCodesBean.saveXml(LCB, LCB.getMyFile() );
    }

    @Override
    public int getRowCount() {
        return codes.length;
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
                return codes[rowIndex].getType().getName();                           
            case 1:
                if (rowIndex < codes.length) return codes[rowIndex].getInCodeText();
                else return "";
        }
        return "";
    }
    
    @Override
    public void setValueAt(Object oValue, int row, int column) {
        
        if (row >= codes.length) {
            CodeTypeBean[] codesCopy = new CodeTypeBean[row +1];
            System.arraycopy(codes, 0, codesCopy, 0, codes.length);
            this.codes = codesCopy;
            int i = 0;
            CodeTypeBean newShit;
            
            
            for (CodeTypeBean ctb : codes) {
                if (ctb == null) {
                    newShit = new CodeTypeBean();
                    newShit.setLanguage(selectedLanguage);
                    newShit.setType(fieldtypes[i]);
                    this.codes[i] = newShit;
                }
                i++;
            }
        }
        codes[row].setInCodeText( oValue.toString() );
        save();        
    }
    
    
}

class ModelRow {
    
}
