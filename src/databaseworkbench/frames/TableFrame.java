package databaseworkbench.frames;

import databaseworkbench.Database;
import databaseworkbench.MainWindow;
import databaseworkbench.TableModel;
import databaseworkbench.TableTemplateManager;
import databaseworkbench.views.ViewFKeys;
import databaseworkbench.views.ViewTable;
import databaseworkbench.beans.TableBean;
import databaseworkbench.jtables.FKeysTable;
import databaseworkbench.jtables.TableTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class TableFrame extends JInternalFrame implements ActionListener, MouseListener, KeyListener {
        
    JSplitPane splitter;
    
    
    JScrollPane jspUpper;
    TableTable table;
    TableModel model;
    ViewTable viewTable = new ViewTable();
    
    JScrollPane jspLower;
    FKeysTable keysTable;
    TableModel keysModel;
    ViewFKeys viewFKeys = new ViewFKeys();
    
    JMenuBar menuBar;
    
    JMenuItem saveNewKey;
    JMenuItem newKey;
    JMenuItem deleteKey;
    
    private String tableName;
    
    public TableFrame(String sTableName) {
        this.tableName = sTableName;
        this.setTitle( sTableName );
        this.setSize(400,300);
        // this.setLocation(20, 20);
        
        
        
        // Upper
        model = new TableModel(this, "fields",viewTable );
        table = new TableTable( model );
        table.addMouseListener( this );
        table.addKeyListener( this );
        jspUpper = new JScrollPane( table );
        
        // Lower
        keysModel = new TableModel(this, "foreignkeys",viewFKeys );
        keysModel.setEditMode( TableModel.EDIT_MODE_NEW_ROW );        
        keysTable = new FKeysTable( keysModel );
        
        jspLower = new JScrollPane( keysTable );
        keysTable.addMouseListener( this );
        
        
        splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jspUpper, jspLower);
        splitter.setDividerLocation(150);
        
        
        this.getContentPane().add( splitter );
        menuBar = new JMenuBar();
        this.setMenuThings();
        this.setJMenuBar( menuBar );
        this.setClosable( true );
        this.setIconifiable( true );
        this.setResizable( true );
        this.setVisible( true );
        
    }
    
    @Override
    public String toString() {
        return this.tableName;
    }
    
    public synchronized TableBean getBean() {
        TableBean bean = Database.getInstance().getTable(tableName);
        if (bean == null) {
            System.out.println("Table " + tableName + " cannot be found!");
            System.exit(0);
            return null;
        }
        else return bean;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(table))
        if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
            if (table.getSelectedRow() > -1) {
                editSelectedRow();
            }
        }
        if (e.getSource().equals(keysTable)) {
            if (keysTable.getSelectedRow() > -1) {
                this.deleteKey.setEnabled( true );
            } else this.deleteKey.setEnabled( false );
        }
    }
    
    private void editSelectedRow() {
        JDesktopPane desktopPane = (JDesktopPane) this.getParent();
        JPanel panel  = (JPanel) desktopPane.getParent();
        JLayeredPane layeredPane = (JLayeredPane) panel.getParent();
        JRootPane rootPane = (JRootPane) layeredPane.getParent();
        MainWindow mainWindow = (MainWindow) rootPane.getParent();                
        mainWindow.openFieldEditor( this, this.getBean(), table.getSelectedRow() );
    }
            

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource().equals(table))
        if (e.isControlDown() && table.getSelectedRow() > -1) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                int row = table.getSelectedRow();
                this.getBean().moveUp( row );
                table.getSelectionModel().setSelectionInterval( row - 1, row - 1);
            }
            if (e.getSource().equals(table))
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                int row = table.getSelectedRow();
                this.getBean().moveDown( table.getSelectedRow() );
                table.getSelectionModel().setSelectionInterval( row + 1, row + 1);
            }
        }
        if (e.getSource().equals(table))
        if (e.getKeyCode() == KeyEvent.VK_ENTER && table.getSelectedRow() > -1) {
            editSelectedRow();
        }
        if (e.getSource().equals(table))
        if (e.getKeyCode() == KeyEvent.VK_DELETE && table.getSelectedRow() > -1) {
            deleteSelectedRow();
        }
        if (e.getSource().equals(table)) {
            if (e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_N) {
                addNewRow();
            }
        }
        if (e.getSource().equals(table))
        if (e.getKeyCode() == KeyEvent.VK_INSERT) {
            addNewRow();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    private void deleteSelectedRow() {
        this.getBean().getFields().remove( table.getSelectedRow() );
        this.model.fireTableDataChanged();
    }

    private void addNewRow() {
        JDesktopPane desktopPane = (JDesktopPane) this.getParent();
        JPanel panel  = (JPanel) desktopPane.getParent();
        JLayeredPane layeredPane = (JLayeredPane) panel.getParent();
        JRootPane rootPane = (JRootPane) layeredPane.getParent();
        MainWindow mainWindow = (MainWindow) rootPane.getParent();             
        mainWindow.addNewField( this, this.getBean() );
    }

    private void setMenuThings() {
        JMenu tableMenu = new JMenu("Table");
        
        JMenuItem saveAsTemplate = new JMenuItem("Save as template");
        saveAsTemplate.setActionCommand( "saveAsTemplate" );
        saveAsTemplate.addActionListener( this );
        tableMenu.add( saveAsTemplate );
        
        JMenuItem deleteTable = new JMenuItem("Delete");
        deleteTable.setActionCommand( "deleteTable" );
        deleteTable.addActionListener( this );
        tableMenu.add( deleteTable );
        
        JMenuItem closeTable = new JMenuItem("Close");
        closeTable.setActionCommand( "closeTable" );
        closeTable.addActionListener( this );
        tableMenu.add( closeTable );
        
        JMenuItem renameTable = new JMenuItem("Rename");
        renameTable.setActionCommand( "renameTable" );
        renameTable.addActionListener( this );        
        tableMenu.add( renameTable );
        
        this.menuBar.add( tableMenu );
        
        JMenu fieldMenu = new JMenu("Field");
        
        JMenuItem newField = new JMenuItem("New");
        newField.setActionCommand("newField");
        newField.setAccelerator( KeyStroke.getKeyStroke("pressed INSERT") );
        newField.addActionListener( this );
        fieldMenu.add( newField );
        
        JMenuItem editField = new JMenuItem("Edit");
        editField.setActionCommand( "editField" );
        editField.setAccelerator( KeyStroke.getKeyStroke("pressed ENTER") );
        editField.addActionListener( this );
        fieldMenu.add( editField );
        
        JMenuItem deleteField = new JMenuItem("Delete");
        deleteField.setActionCommand( "deleteField" );
        deleteField.setAccelerator( KeyStroke.getKeyStroke("pressed DELETE"));
        deleteField.addActionListener( this );
        fieldMenu.add( deleteField );
        
        this.menuBar.add( fieldMenu );
        
        JMenu foreignKeysMenu = new JMenu("Foreign Keys");
        
        newKey = new JMenuItem("New");
        newKey.setActionCommand("newKey");
        newKey.addActionListener( this );
        foreignKeysMenu.add( newKey );
        
        saveNewKey = new JMenuItem("Save new key");
        saveNewKey.setActionCommand("saveNewKey");
        saveNewKey.addActionListener( this );
        saveNewKey.setEnabled( false );
        foreignKeysMenu.add( saveNewKey );
        
        deleteKey = new JMenuItem("Delete");
        deleteKey.setActionCommand("deleteKey");
        deleteKey.addActionListener( this );
        deleteKey.setEnabled( false );
        foreignKeysMenu.add( deleteKey );
        
        this.menuBar.add( foreignKeysMenu );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        switch(e.getActionCommand()) {
            case "saveAsTemplate":
                this.saveAsTemplate();
                break;
                
            case "deleteTable":
                this.deleteThisTable();
                break;
                
            case "renameTable":
                renameThisTable();
                break;
                
            case "closeTable":
                closeThisTable();
                break;
                
            case "newField":
                addNewRow();
                break;
                
            case "editField":
                if (table.getSelectedRow() > -1) editSelectedRow();
                break;
                
            case "deleteField":
                if (table.getSelectedRow() > -1) deleteSelectedRow();
                break;
            
            case "newKey":
                this.keysModel.setNewRowMode( true );
                menuItem.setText("Cancel new key");
                menuItem.setActionCommand("cancelNewKey");
                saveNewKey.setEnabled( true );
                break;
                
            case "cancelNewKey":
                this.keysModel.setNewRowMode( false );
                this.viewFKeys.deleteNewTempRow();
                menuItem.setText("New");
                menuItem.setActionCommand("newKey");
                saveNewKey.setEnabled( false );
                break;
                
            case "saveNewKey":
                saveNewKey();
                break;
                
            case "deleteKey":
                deleteKey();
                break;
        }
    }

    void newFieldInBean() {
        
        this.model.fireTableDataChanged();
    }

    private void renameThisTable() {
        String newName = JOptionPane.showInputDialog("Name for this table?", this.getBean().getName());
        if (!newName.trim().equals("")) {
            newName = newName.trim();
            this.getBean().setName( newName );
            this.setTitle( newName );
            MainWindow.getInstance().updateListFrame();
        }
    }

    private void deleteThisTable() {        
        this.dispose();
        MainWindow.getInstance().dropFrameAndBean( this );
    }

    private void closeThisTable() {
        this.dispose();
    }

    private void saveNewKey() {
        if (this.viewFKeys.addNewTempRow( this.getBean() )) {
            this.keysModel.setNewRowMode( false );
            this.saveNewKey.setEnabled( false );
            this.newKey.setActionCommand("newKey");
            this.newKey.setText( "New" );
            this.keysModel.fireTableDataChanged();
            JOptionPane.showInternalMessageDialog(this, "New key saved!");
        } else {
            JOptionPane.showInternalMessageDialog(this, "Error on saving new key!");
        }
        
    }

    private void deleteKey() {
        if(this.keysTable.getSelectedRow() > -1) {
            int iIndex = this.keysTable.getSelectedRow();
            this.getBean().getForeignkeys().remove( iIndex );
            this.keysModel.fireTableDataChanged();
        }
    }

    private void saveAsTemplate() {
        TableTemplateManager.getInstance().getTemplates().add( Database.getInstance().getTable( tableName ) );
        TableTemplateManager.getInstance().update();
    }
    
}
