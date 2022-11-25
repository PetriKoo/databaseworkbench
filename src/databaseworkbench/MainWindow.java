package databaseworkbench;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author petri
 */
public class MainWindow extends JFrame implements KeyEventDispatcher, ActionListener {

    DatabaseWorkbench workbench;
    JDesktopPane desktop;
    JMenuBar menubar;
    JMenu frameMenu;
    JMenu tableMenu;
    TableFormFrame tableFormFrame = null;
    TableFrame userframe;
    ArrayList<TableFrame> tableFrames = new ArrayList<>();
    
    MainWindow(DatabaseWorkbench aThis) {
        workbench = aThis;
        
        this.setSize(800,600);
        this.setTitle("Workbench");        
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        desktop = new JDesktopPane();
        this.getContentPane().add( desktop );
        
        
        menubar = new JMenuBar();
        this.doMenuBarShit();
        this.setJMenuBar( menubar );
        TableBean bean = this.userTable();
        
        userframe = new TableFrame(bean);
        desktop.add( userframe );
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher( this );
    }
    
    void addFrame(TableFrame tableF) {
        tableFrames.add(tableF);
        desktop.add( tableF);
    }
    
    void dropFrame(TableFrame tableF) {
        tableFrames.remove( tableF );
        desktop.remove( tableF );
    }  
    
    boolean containsFrame(TableFrame tableF) {
        return (tableFrames.contains(tableF));
    }

    void openFieldEditor(TableBean bean, int selectedRow) {
        FieldFormFrame formEditor = new FieldFormFrame( userframe, bean, selectedRow);        
        desktop.add( formEditor );
        formEditor.toFront();
    }
    private TableBean userTable() {
        TableBean bean = new TableBean();
        bean.setName("Users");
       
        TableFieldBean idfield = new TableFieldBean();
        idfield.setPrimarykey( true );
        idfield.setName("user_id");
        idfield.setType( FieldType.INTEGER );
        bean.getFields().add(idfield);
        
        TableFieldBean fnameField = new TableFieldBean();
        fnameField.setName("user_fname");
        fnameField.setType(FieldType.TEXT);
        bean.getFields().add(fnameField);
        
        TableFieldBean lnameField = new TableFieldBean();
        lnameField.setName("user_lname");
        lnameField.setType(FieldType.TEXT);
        bean.getFields().add(lnameField);
        
        return bean;
    }

    void addNewField(TableBean bean) {
        FieldFormFrame formEditor = new FieldFormFrame( userframe, bean, -1);
        desktop.add( formEditor );
        formEditor.toFront();
    }

  

    private void addNewTable() {
        if (tableFormFrame == null) {
            tableFormFrame = new TableFormFrame();
        } else {
            tableFormFrame.setVisible( true );
        }
        if (!Tools.contains(tableFormFrame, desktop.getAllFrames())) {
            desktop.add( tableFormFrame );
        }
        
        tableFormFrame.toFront();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) this.keyPressed(e);
        return false;
    }

    private void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_N && e.isControlDown()) addNewTable();
        if (e.getKeyCode() == KeyEvent.VK_X && e.isControlDown()) closeProgram();
    }

    private void closeProgram() {
        System.exit(0);
    }

    private void doMenuBarShit() {
        tableMenu = new JMenu("Table");
        
        JMenuItem newTable = new JMenuItem("New");
        newTable.setActionCommand("newTable");
        newTable.addActionListener( this );
        tableMenu.add( newTable );
        
        JMenuItem removeTable = new JMenuItem("Remove");
        removeTable.setActionCommand("removeTable");
        removeTable.addActionListener( this );
        tableMenu.add( removeTable );
        
        JMenuItem listTable = new JMenuItem("List");
        listTable.setActionCommand("listTable");
        listTable.addActionListener( this );
        tableMenu.add( listTable );
        
        this.menubar.add(tableMenu);
                
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "newTable":
                break;
        }
    }

    private static class Tools {

        private static boolean contains(TableFormFrame formFrame, JInternalFrame[] allFrames) {
            for(JInternalFrame frame : allFrames) {
                if (frame.equals(formFrame)) return true;
            }
            return false;
        }

        public Tools() {
        }
    }
    
}
