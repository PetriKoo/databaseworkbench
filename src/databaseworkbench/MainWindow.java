package databaseworkbench;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
    JMenu databaseMenu;
    TableFormFrame tableFormFrame = null;
    TableListFrame listFrame;
    
    ArrayList<TableFrame> tableFrames = new ArrayList<>();
    private static MainWindow INSTANCE = null;
    MainWindow(DatabaseWorkbench aThis) {
        MainWindow.INSTANCE = this;
        workbench = aThis;
        
        this.setSize(800,600);
        this.setTitle("Workbench");        
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        desktop = new JDesktopPane();
        this.getContentPane().add( desktop );
        
        
        menubar = new JMenuBar();
        this.doMenuBarShit();
        this.setJMenuBar( menubar );
     
        listFrame = new TableListFrame();
        
        listFrame.setVisible( true );
        listFrame.setLocation( 20, 20);
        this.desktop.add( listFrame );
        listFrame.updateList( tableFrames );
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher( this );
    }
    
    public static MainWindow getInstance() { return INSTANCE; }
    
    void addFrame(TableFrame tableF) {
        tableFrames.add(tableF);
        this.listFrame.updateList(tableFrames);
        desktop.add( tableF);
    }
    
    void dropFrame(TableFrame tableF) {
        tableFrames.remove( tableF );
        this.listFrame.updateList(tableFrames);
        desktop.remove( tableF );
    }
    
    void showFrame(TableFrame frame) {
        if (!Tools.contains(frame, this.desktop.getAllFrames())) {
            this.desktop.add(frame);
            frame.setLocation( 50, 50);
            frame.setVisible( true );
            frame.toFront();
        } else {
            frame.setVisible( true );
            try {
                frame.setSelected( true );
            } catch (PropertyVetoException ex) { }
            frame.toFront();
        }
    }
    
    boolean containsFrame(TableFrame tableF) {
        return (tableFrames.contains(tableF));
    }

    void openFieldEditor(TableFrame frame, TableBean bean, int selectedRow) {
        FieldFormFrame formEditor = new FieldFormFrame( frame, bean, selectedRow);        
        desktop.add( formEditor );
        formEditor.toFront();
    }
    
    void addNewField(TableFrame frame, TableBean bean) {
        FieldFormFrame formEditor = new FieldFormFrame( frame, bean, -1);
        desktop.add( formEditor );
        formEditor.toFront();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) this.keyPressed(e);
        return false;
    }

    private void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_N && e.isControlDown()) newTable();
        if (e.getKeyCode() == KeyEvent.VK_X && e.isControlDown()) closeProgram();
    }

    private void closeProgram() {
        System.exit(0);
    }

    private void doMenuBarShit() {
        databaseMenu = new JMenu("Database");
        
        JMenuItem saveDatabase = new JMenuItem("Save");
        saveDatabase.setActionCommand("saveDatabase");
        saveDatabase.addActionListener( this );
        databaseMenu.add( saveDatabase );
        
        JMenuItem loadDatabase = new JMenuItem("Load");
        loadDatabase.setActionCommand("loadDatabase");
        loadDatabase.addActionListener( this );
        databaseMenu.add( loadDatabase );
        
        JMenuItem deleteDatabase = new JMenuItem("Delete");
        deleteDatabase.setActionCommand("deleteDatabase");
        deleteDatabase.addActionListener( this );
        databaseMenu.add( deleteDatabase );
        
        this.menubar.add( databaseMenu );
        
        tableMenu = new JMenu("Table");
        
        JMenuItem newTable = new JMenuItem("New");
        newTable.setActionCommand("newTable");
        newTable.addActionListener( this );
        tableMenu.add( newTable );           
        
        this.menubar.add(tableMenu);
                
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "newTable":
                newTable();
                break;
                
        }
    }

    private void newTable() {
        String newName = JOptionPane.showInputDialog("A new table, Name for this table?", "");
        if (newName != null && !newName.trim().equals("")) {
            newName = newName.trim();
            TableBean bean = new TableBean();
            bean.setName( newName );
            TableFrame frame = new TableFrame(bean);
            tableFrames.add( frame );
            desktop.add( frame );
            this.listFrame.updateList(tableFrames);
            frame.toFront();
        }
    }   

   

    void updateListFrame() {
        this.listFrame.updateList(tableFrames);
    }

    
}
