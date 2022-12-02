package databaseworkbench;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
    TableFormFrame tableFormFrame = null;
    
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
     
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher( this );
    }
    
    public static MainWindow getInstance() { return INSTANCE; }
    
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
        
        if (e.getKeyCode() == KeyEvent.VK_N && e.isControlDown()) newTable();
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
        }
    }   

    
}
