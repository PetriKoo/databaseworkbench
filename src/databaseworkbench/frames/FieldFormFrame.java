package databaseworkbench.frames;

import databaseworkbench.Database;
import databaseworkbench.forms.FieldForm;
import databaseworkbench.beans.TableFieldBean;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class FieldFormFrame extends JInternalFrame implements ActionListener, InternalFrameListener {
    
    JButton buttonSave;
    JButton buttonCancel;
    FieldForm tableForm;    
    private final int fieldIndex;
    private final TableFrame tableFrame;
    private final String tableName;
    
    
    public FieldFormFrame(TableFrame tFrame, String sTableName, int fieldIndex) {
        this.tableName = sTableName;
        this.tableFrame = tFrame;
        this.fieldIndex = fieldIndex;
        this.setTitle("Field Editor - table " + this.tableName);
        this.setSize( 640,320);
        this.setLocation( 40, 40);
        this.setClosable( true );
        tableForm = new FieldForm();
        putStuff();
        this.setVisible( true );
        doSomeShit();
        
    }
    
    
    private void putStuff() {
        JPanel panel = (JPanel) this.getContentPane();
        JPanel buttonBar = new JPanel();        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout( layout );
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        panel.add( tableForm ,c );
        buttonSave = new JButton("Save");
        buttonCancel = new JButton("Cancel");
        c.gridy = 1;        
        c.gridx = 0;
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.NONE;
        panel.add( buttonBar, c );
        buttonBar.add(buttonCancel,c);
        
        buttonBar.add(buttonSave,c);
        buttonCancel.setActionCommand("cancel");
        buttonCancel.addActionListener( this );
        buttonSave.setActionCommand("save");
        buttonSave.addActionListener( this );
    }

    private void doSomeShit() {
        if (fieldIndex == -1) {
            this.tableForm.setNewMode( true );
            this.tableForm.setBean( Database.getInstance().getTable(tableName) );
                   
            this.tableForm.empty();
        } else {
            this.tableForm.setNewMode( false );
            this.tableForm.putData( Database.getInstance().getTable(tableName), fieldIndex );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            if (fieldIndex == -1) {
                TableFieldBean newData = this.tableForm.getNewData();
                Database.getInstance().getTable(tableName).getFields().add(newData);
                this.tableFrame.newFieldInBean();
            } else {                
                Database.getInstance().replaceTable(tableName,this.tableForm.getData());
            }
            this.dispose();
        }
        if (e.getActionCommand().equals("cancel")) {
            this.setVisible( false );
            this.dispose();
        }
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) { 
        this.requestFocus();
        tableForm.requestNameFieldFocus();
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) { }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) { }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) { }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) { }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        this.requestFocus();
        tableForm.requestNameFieldFocus();
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) { }
}
