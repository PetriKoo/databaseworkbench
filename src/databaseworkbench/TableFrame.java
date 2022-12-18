package databaseworkbench;

import databaseworkbench.beans.TableBean;
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

/**
 *
 * @author petri
 */
public class TableFrame extends JInternalFrame implements ActionListener, MouseListener, KeyListener {
    
    TableBean bean;
    JScrollPane jsp;
    TableTable table;
    TableModel model;
    ViewTable viewTable = new ViewTable();
    JMenuBar menuBar;
    
    public TableFrame(TableBean bean) {
        this.bean = bean;
        this.setTitle( bean.getName() );
        this.setSize(400,200);
        this.setLocation(20, 20);
        model = new TableModel(this, viewTable );
        table = new TableTable( model );
        table.addMouseListener( this );
        table.addKeyListener( this );
        jsp = new JScrollPane( table );
        this.getContentPane().add( jsp );
        menuBar = new JMenuBar();
        this.setMenuThings();
        this.setJMenuBar( menuBar );
        this.setClosable( true );
        this.setIconifiable( true );
        this.setVisible( true );
        
    }
    
    public String toString() {
        return this.bean.getName();
    }
    
    public TableBean getBean() { return this.bean; }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
            if (table.getSelectedRow() > -1) {
                editSelectedRow();
            }
        }
    }
    
    private void editSelectedRow() {
        JDesktopPane desktopPane = (JDesktopPane) this.getParent();
        JPanel panel  = (JPanel) desktopPane.getParent();
        JLayeredPane layeredPane = (JLayeredPane) panel.getParent();
        JRootPane rootPane = (JRootPane) layeredPane.getParent();
        MainWindow mainWindow = (MainWindow) rootPane.getParent();                
        mainWindow.openFieldEditor( this, this.bean, table.getSelectedRow() );
    }
            

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
     
    }

    @Override
    public void mouseExited(MouseEvent e) {
     
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && table.getSelectedRow() > -1) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                int row = table.getSelectedRow();
                bean.moveUp( row );
                table.getSelectionModel().setSelectionInterval( row - 1, row - 1);
            }
            
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                int row = table.getSelectedRow();
                bean.moveDown( table.getSelectedRow() );
                table.getSelectionModel().setSelectionInterval( row + 1, row + 1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER && table.getSelectedRow() > -1) {
            editSelectedRow();
        }
        if (e.getKeyCode() == KeyEvent.VK_DELETE && table.getSelectedRow() > -1) {
            deleteSelectedRow();
        }
        if (e.getKeyCode() == KeyEvent.VK_INSERT) {
            addNewRow();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    private void deleteSelectedRow() {
        this.bean.getFields().remove( table.getSelectedRow() );
        this.model.fireTableDataChanged();
    }

    private void addNewRow() {
        JDesktopPane desktopPane = (JDesktopPane) this.getParent();
        JPanel panel  = (JPanel) desktopPane.getParent();
        JLayeredPane layeredPane = (JLayeredPane) panel.getParent();
        JRootPane rootPane = (JRootPane) layeredPane.getParent();
        MainWindow mainWindow = (MainWindow) rootPane.getParent();             
        mainWindow.addNewField( this, this.bean );
    }

    private void setMenuThings() {
        JMenu tableMenu = new JMenu("Table");
        
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
        newField.addActionListener( this );
        fieldMenu.add( newField );
        
        JMenuItem editField = new JMenuItem("Edit");
        editField.setActionCommand( "editField" );
        editField.addActionListener( this );
        fieldMenu.add( editField );
        
        JMenuItem deleteField = new JMenuItem("Delete");
        deleteField.setActionCommand( "deleteField" );
        deleteField.addActionListener( this );
        fieldMenu.add( deleteField );
        
        this.menuBar.add( fieldMenu );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
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
            
        }
    }

    void newFieldInBean(TableBean bean) {
        this.bean = bean;
        this.model.fireTableDataChanged();
    }

    private void renameThisTable() {
        String newName = JOptionPane.showInputDialog("Name for this table?", this.bean.getName());
        if (!newName.trim().equals("")) {
            newName = newName.trim();
            this.bean.setName(newName);
            this.setTitle( newName );
            MainWindow.getInstance().updateListFrame();
        }
    }

    private void deleteThisTable() {
        this.bean = null;
        this.dispose();
        MainWindow.getInstance().dropFrame( this );
    }

    private void closeThisTable() {
        this.dispose();
    }
    
}
