package databaseworkbench;

import databaseworkbench.frames.FieldFormFrame;
import databaseworkbench.frames.TableListFrame;
import databaseworkbench.frames.TableFrame;
import databaseworkbench.frames.DatabaseChooserFrame;
import databaseworkbench.beans.DatabaseBean;
import databaseworkbench.beans.TableBean;
import databaseworkbench.beans.TableFieldBean;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class MainWindow extends JFrame implements KeyEventDispatcher, ActionListener {

    ArrayList<DatabaseBean> databases = new ArrayList<>();
    DatabaseWorkbench workbench;
    JDesktopPane desktop;
    JMenuBar menubar;
    JMenu frameMenu;
    JMenu tableMenu;
    JMenu databaseMenu;
    JMenu codesMenu;    
    TableListFrame listFrame;
    DatabaseChooserFrame chooser = null;
    
    ArrayList<TableFrame> tableFrames = new ArrayList<>();
    ArrayList<TableBean> tableBeans = new ArrayList<>();
    
    private static MainWindow INSTANCE = null;
    
    private final String title = "Workbench";
    private final String version = "0.2";
    private String databaseName = "New";
    
    static final String FileExtension = ".xml";
    
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
    
    public void addFrame(TableFrame tableF) {
        tableFrames.add(tableF);
        this.listFrame.updateList(tableFrames);
        desktop.add( tableF);
        try {
            tableF.setSelected( true );
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dropFrameAndBean(TableFrame tableF) {
        this.tableBeans.remove( tableF.getBean() );
        tableFrames.remove( tableF );
        this.listFrame.updateList(tableFrames);
        desktop.remove( tableF );
    }
    
    public void showFrame(TableFrame frame) {
        if (!Tools.contains(frame, this.desktop.getAllFrames())) {
            this.desktop.add(frame);
            frame.setLocation( 50, 50);
            frame.setVisible( true );
            frame.toFront();
        } else {
            frame.toFront();
            frame.setVisible( true );
            try {
                frame.setSelected( true );
            } catch (PropertyVetoException ex) { }
            
            
        }
    }
    
    public boolean containsFrame(TableFrame tableF) {
        return (tableFrames.contains(tableF));
    }

    public void openFieldEditor(TableFrame frame, TableBean bean, int selectedRow) {
        FieldFormFrame formEditor = new FieldFormFrame( frame, bean, selectedRow);        
        desktop.add( formEditor );
        formEditor.toFront();
        formEditor.requestFocus();
    }
    
    public void addNewField(TableFrame frame, TableBean bean) {
        FieldFormFrame formEditor = new FieldFormFrame( frame, bean, -1);
        desktop.add( formEditor );
        formEditor.toFront();
        try {
            formEditor.setSelected( true );
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<TableBean> getTableBeans() { return this.tableBeans; }
    
    public TableBean getTableBean(String sTableName) {
        for(TableBean table: this.tableBeans) {
            if (sTableName != null)
                if (sTableName.equalsIgnoreCase( table.getName() )) return table;
        }
        return null;
    }
    
    public ArrayList<TableFieldBean> getFieldBeans(String sTableName) {
        for(TableBean table: this.tableBeans) {
            if (sTableName.equalsIgnoreCase( table.getName() )) return table.getFields();
        }
        return new ArrayList<TableFieldBean>();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) this.keyPressed(e);
        return false;
    }

    private void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_N && e.isControlDown() && !e.isShiftDown()) newTable();
        if (e.getKeyCode() == KeyEvent.VK_X && e.isControlDown()) closeProgram();
    }

    private void closeProgram() {
        System.exit(0);
    }

    private void doMenuBarShit() {
        databaseMenu = new JMenu("Database");
        
        JMenuItem newDatabase = new JMenuItem("New");
        newDatabase.setActionCommand("newDatabase");
        newDatabase.addActionListener( this );
        databaseMenu.add( newDatabase );
        
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
        newTable.setAccelerator( KeyStroke.getKeyStroke("ctrl N") );
        tableMenu.add( newTable );           
        
        this.menubar.add(tableMenu);
        
        codesMenu = new JMenu("Codes");
        
        
        this.menubar.add(codesMenu);
                
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "newTable":
                newTable();
                break;
            
            case "newDatabase":
                newDatabase();
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
            this.tableBeans.add( bean );
            TableFrame frame = new TableFrame(bean);
            tableFrames.add( frame );
            desktop.add( frame );
            this.listFrame.updateList(tableFrames);
            frame.toFront();
            try {
                frame.setSelected( true );
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   

   

    public void updateListFrame() {
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
    
    private void saveDatabaseXml() {
        DatabaseBean databasebean = new DatabaseBean();
        databasebean.setDatabaseName( this.databaseName );
        for(TableFrame frame : this.tableFrames) {
            databasebean.getTables().getTables().add( frame.getBean() );
        }
        File file = new File(DatabaseWorkbench.DATABASE_FOLDER + File.separator + databasebean.getDatabaseName() + MainWindow.FileExtension);
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
            File file = new File(DatabaseWorkbench.DATABASE_FOLDER + File.separator + this.databaseName + MainWindow.FileExtension);
            if (file.exists()) {
                if (file.delete()) {
                    JOptionPane.showInternalMessageDialog(this.desktop, "Database deleted!", "Title", JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    JOptionPane.showInternalMessageDialog(this.desktop, "Database was NOT deleted!", "Title", JOptionPane.INFORMATION_MESSAGE);
                }
                    
            } else {
                JOptionPane.showInternalMessageDialog(this.desktop, "File " + file.getName() + " not found.\nDatabase was NOT deleted!", "Title", JOptionPane.INFORMATION_MESSAGE);
            }
            this.databaseName = "New Database";
            this.updateTitle();
            this.tableFrames.clear();
            this.tableBeans.clear();
            this.updateListFrame();
        }
    }

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
    public void getDatabase(DatabaseBean databaseBean) {
        this.databaseName = databaseBean.getDatabaseName();
        this.tableBeans.clear();
        this.updateTitle();
        for (TableBean table : databaseBean.getTables().getTables()) {
            this.tableFrames.add( new TableFrame(table) );
            this.tableBeans.add( table );
        }
        this.chooser.setVisible( false );
        this.updateListFrame();
        this.listFrame.setVisible( true );
        try {
            this.listFrame.setSelected( true );
        } catch (PropertyVetoException ex) { }
    }

    private void newDatabase() {
        this.tableBeans.clear();
        this.databaseName = "New";
        this.updateTitle();
        this.updateListFrame();
    }
    
}
