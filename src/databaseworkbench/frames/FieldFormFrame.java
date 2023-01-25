package databaseworkbench.frames;

import databaseworkbench.forms.FieldForm;
import databaseworkbench.beans.TableBean;
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
    private TableBean bean;
    private final int fieldIndex;
    private final TableFrame tableFrame;
    
    
    public FieldFormFrame(TableFrame tFrame, TableBean bean, int fieldIndex) {
        this.bean = bean;
        this.tableFrame = tFrame;
        this.fieldIndex = fieldIndex;
        this.setTitle("Field Editor - table " + bean.getName());
        this.setSize( 640,280);
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
            this.tableForm.setBean( bean );
                   
            this.tableForm.empty();
        } else {
            this.tableForm.setNewMode( false );
            this.tableForm.putData( bean, fieldIndex );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            if (fieldIndex == -1) {
                TableFieldBean newData = this.tableForm.getNewData();
                this.bean.getFields().add(newData);
                this.tableFrame.newFieldInBean( this.bean );
            } else {
                this.bean = this.tableForm.getData();
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
    public void internalFrameActivated(InternalFrameEvent e) { }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) { }
}
