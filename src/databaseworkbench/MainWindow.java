package databaseworkbench;

import databaseworkbench.beans.DatabaseBean;
import databaseworkbench.beans.TableBean;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
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

    ArrayList<DatabaseBean> databases = new ArrayList<>();
    DatabaseWorkbench workbench;
    JDesktopPane desktop;
    JMenuBar menubar;
    JMenu frameMenu;
    JMenu tableMenu;
    JMenu databaseMenu;
    TableFormFrame tableFormFrame = null;
    TableListFrame listFrame;
    DatabaseChooserFrame chooser = null;
    
    ArrayList<TableFrame> tableFrames = new ArrayList<>();
    
    private static MainWindow INSTANCE = null;
    
    private final String title = "Workbench";
    private final String version = "0.2";
    private String databaseName = "New";
    
    MainWindow(DatabaseWorkbench aThis) {
        MainWindow.INSTANCE = this;
        workbench = aThis;
        
        this.setSize(800,600);
        this.updateTitle();
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
    
    void updateTitle() {
        this.setTitle(title + " " + version + " - " + databaseName);
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
        
        JMenuItem renameDatabase = new JMenuItem("Rename");
        renameDatabase.setActionCommand("renameDatabase");
        renameDatabase.addActionListener( this );
        databaseMenu.add( renameDatabase );
        
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
            
            case "renameDatabase":
                renameDatabase();
                break;
            
            case "saveDatabase":
                saveDatabaseXml();
                break;
                
            case "loadDatabase":
                loadDatabase();
                break;
                
            case "deleteDatabase":
                deleteDatabase();
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

    private void renameDatabase() {
        String newName = JOptionPane.showInputDialog("Name for this database?", this.databaseName);
        if (newName != null && !newName.trim().equals("")) {
            newName = newName.trim();
            this.databaseName = newName;
            this.updateTitle();
        }
    }

    private void saveDatabaseObj() {
        DatabaseBean databasebean = new DatabaseBean();
        databasebean.setDatabaseName( this.databaseName );
        for(TableFrame frame : this.tableFrames) {
            databasebean.getTables().getTables().add( frame.getBean() );
        }
        File file = new File(DatabaseWorkbench.DATABASE_FOLDER + File.separator + databasebean.getDatabaseName() + ".obj");
        DatabaseBean.saveObject(databasebean, file);
    }
    
    private void saveDatabaseXml() {
        DatabaseBean databasebean = new DatabaseBean();
        databasebean.setDatabaseName( this.databaseName );
        for(TableFrame frame : this.tableFrames) {
            databasebean.getTables().getTables().add( frame.getBean() );
        }
        File file = new File(DatabaseWorkbench.DATABASE_FOLDER + File.separator + databasebean.getDatabaseName() + ".xml");
        DatabaseBean.saveXml(databasebean, file);
    }

    private void loadDatabase() {
        listDatabase();
        if (chooser == null) {
            chooser = new DatabaseChooserFrame( this );
        }
        if (!Tools.contains(chooser, this.desktop.getAllFrames())) {
            this.desktop.add( chooser );
        }
        if (!chooser.isVisible()) chooser.setVisible( true );
        chooser.toFront();
        if (!chooser.isSelected()) try {
            chooser.setSelected( true );
        } catch (PropertyVetoException ex) {}
        this.chooser.updateList( this.databases );
    }

    private void deleteDatabase() {
        if (JOptionPane.showInternalConfirmDialog(this.desktop, "Are sure to delete THIS database?", "Deleting database", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            File file = new File(DatabaseWorkbench.DATABASE_FOLDER + File.separator + this.databaseName + ".obj");
            if (file.exists()) {
                file.delete();
            }
            this.databaseName = "New Database";
            this.updateTitle();
            this.tableFrames.clear();
            this.updateListFrame();
        }
    }
/*
    private void listDatabase() {
        this.databases.clear();
        File dbFolder = new File(DatabaseWorkbench.DATABASE_FOLDER);
        File[] files = dbFolder.listFiles( Tools.dbFileFilterObj() );
        DatabaseBean dbBean;
        for (File file : files) {
            dbBean = DatabaseBean.loadObject(file);
            this.databases.add(dbBean);
        }
    }
*/
    private void listDatabase() {
        this.databases.clear();
        File dbFolder = new File(DatabaseWorkbench.DATABASE_FOLDER);
        File[] files = dbFolder.listFiles( Tools.dbFileFilterXml() );
        DatabaseBean dbBean;
        for (File file : files) {
            dbBean = DatabaseBean.loadXml(file);
            this.databases.add(dbBean);
        }
    }
    void getDatabase(DatabaseBean databaseBean) {
        this.databaseName = databaseBean.getDatabaseName();
        this.updateTitle();
        for (TableBean table : databaseBean.getTables().getTables()) {
            this.tableFrames.add( new TableFrame(table) );
        }
        this.chooser.setVisible( false );
        this.updateListFrame();
        this.listFrame.setVisible( true );
        try {
            this.listFrame.setSelected( true );
        } catch (PropertyVetoException ex) { }
    }
    
}
