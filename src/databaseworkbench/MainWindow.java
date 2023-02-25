package databaseworkbench;

import databaseworkbench.frames.FieldFormFrame;
import databaseworkbench.frames.TableListFrame;
import databaseworkbench.frames.TableFrame;
import databaseworkbench.frames.DatabaseChooserFrame;
import databaseworkbench.beans.DatabaseBean;
import databaseworkbench.beans.TableBean;
import databaseworkbench.beans.TableFieldBean;
import databaseworkbench.codes.FilePerTableFrame;
import databaseworkbench.frames.TemplateListFrame;
import databaseworkbench.settings.CodeFrame;
import databaseworkbench.settings.FieldtypeFrame;
import databaseworkbench.settings.LanguageFrame;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class MainWindow extends JFrame implements KeyEventDispatcher, ActionListener, WindowListener, WindowStateListener {

    ArrayList<DatabaseBean> databases = new ArrayList<>();
    DatabaseWorkbench workbench;
    JDesktopPane desktop;
    JMenuBar menubar;
    JMenu frameMenu;
    JMenu tableMenu;
    JMenu databaseMenu;
    JMenu codesMenu;   
    JMenu settingsMenu;
    TableListFrame listFrame;
    TemplateListFrame templateFrame;
    DatabaseChooserFrame chooser = null;
    
    ArrayList<TableFrame> tableFrames = new ArrayList<>();
    
    
    private static MainWindow INSTANCE = null;
    
    private final String title = "Workbench";
    private final String version = "0.4";
    
    private Database database = Database.getInstance();        
    private boolean framesInit = true;
    
    MainWindow(DatabaseWorkbench aThis) {
        MainWindow.INSTANCE = this;
        workbench = aThis;
        
        // this.setSize(1024, 768);
        this.setExtendedState( JFrame.MAXIMIZED_BOTH );
        this.setSize(1024,768);
        this.updateTitle();
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        desktop = new JDesktopPane();
        this.addWindowStateListener( this );
        this.addWindowListener( this );
        
        this.getContentPane().add( desktop );
        
        
        menubar = new JMenuBar();
        this.doMenuBarShit();
        this.setJMenuBar( menubar );
     
        listFrame = new TableListFrame();
        
        listFrame.setVisible( true );
        listFrame.setLocation( 20, 20);
        this.desktop.add( listFrame );
        listFrame.updateList( tableFrames );
        
        
        templateFrame = new TemplateListFrame();
        templateFrame.setVisible( true );
        int X = this.getSize().width - 20 - templateFrame.getSize().width;
        
        templateFrame.setLocation(20, 20);
        this.desktop.add( templateFrame );
        
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher( this );
    }
    
    void updateTitle() {
        this.setTitle(title + " " + version + " - " + this.database.getDatabaseName());
    }
    
    public static MainWindow getInstance() { return INSTANCE; }
    
    public JDesktopPane getJDesktopPane() { return this.desktop; }
    
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
        this.database.getTableBeans().remove( tableF.getBean() );
        tableFrames.remove( tableF );
        this.listFrame.updateList(tableFrames);
        desktop.remove( tableF );
    }
    
    public void showFrame(TableFrame frame) {
        if (!Tools.contains(frame, this.desktop.getAllFrames())) {
            this.desktop.add(frame);
            // frame.setLocation( 50, 50);
            this.centerJInternalFrame( frame );
            frame.setVisible( true );
            frame.toFront();
        } else {
            this.centerJInternalFrame( frame );
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
        FieldFormFrame formEditor = new FieldFormFrame( frame, bean.getName(), selectedRow);        
        desktop.add( formEditor );
        formEditor.toFront();
        formEditor.requestFocus();
        this.centerJInternalFrame( formEditor );
    }
    
    public void addNewField(TableFrame frame, TableBean bean) {
        FieldFormFrame formEditor = new FieldFormFrame( frame, bean.getName(), -1);
        desktop.add( formEditor );
        this.centerJInternalFrame( formEditor );
        formEditor.toFront();
        try {
            formEditor.setSelected( true );
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<TableBean> getTableBeans() { return this.database.getTableBeans(); }
    
    public TableBean getTableBean(String sTableName) {
        for(TableBean table: this.database.getTableBeans()) {
            if (sTableName != null)
                if (sTableName.equalsIgnoreCase( table.getName() )) return table;
        }
        return null;
    }
    
    public ArrayList<TableFieldBean> getFieldBeans(String sTableName) {
        for(TableBean table: this.database.getTableBeans()) {
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
        if (e.getKeyCode() == KeyEvent.VK_X && e.isControlDown() && !e.isShiftDown()) closeProgram();
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
        
        JMenuItem filepertable = new JMenuItem("File per Table");
        filepertable.setActionCommand("filepertable");
        filepertable.addActionListener( this );
        codesMenu.add( filepertable );
        
        this.menubar.add(codesMenu);
        
        settingsMenu = new JMenu("Settings");
        
        JMenuItem fieldtypes = new JMenuItem("Field types");
        fieldtypes.setActionCommand("fieldtypes");
        fieldtypes.addActionListener( this );
        settingsMenu.add( fieldtypes );
        
        JMenuItem languages = new JMenuItem("Languages");
        languages.setActionCommand("languages");
        languages.addActionListener( this );
        settingsMenu.add( languages );
        
        JMenuItem codes = new JMenuItem("Codes");
        codes.setActionCommand("codes");
        codes.addActionListener( this );
        settingsMenu.add( codes );
        
        this.menubar.add(settingsMenu);
        
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
                
            case "filepertable":
                this.desktop.add( FilePerTableFrame.getInstance() );
                this.centerJInternalFrame( FilePerTableFrame.getInstance() );
                FilePerTableFrame.getInstance().setVisible( true );
                break;
                
            case "fieldtypes":
                this.desktop.add( FieldtypeFrame.getInstance() );
                this.centerJInternalFrame( FieldtypeFrame.getInstance() );
                FieldtypeFrame.getInstance().setVisible( true );
                break;
                
            case "languages":
                this.desktop.add( LanguageFrame.getInstance() );
                this.centerJInternalFrame( LanguageFrame.getInstance() );
                LanguageFrame.getInstance().setVisible( true );
                break;
                
            case "codes":
                this.desktop.add( CodeFrame.getInstance() );
                this.centerJInternalFrame( CodeFrame.getInstance() );
                CodeFrame.getInstance().setVisible( true );
                break;
        }
    }

    private void newTable() {
        String newName = JOptionPane.showInputDialog("A new table, Name for this table?", "");
        if (newName != null && !newName.trim().equals("")) {
            newName = newName.trim();
            TableBean bean = new TableBean();
            bean.setName( newName );
            
            this.addTableBean( bean );
        }
    }
    
    public void addTableBean(TableBean bean ) {
        this.database.getTableBeans().add( bean );
        TableFrame frame = new TableFrame( bean.getName() );
        tableFrames.add( frame );
        desktop.add( frame );
        this.centerJInternalFrame( frame );
        this.listFrame.updateList(tableFrames);
        frame.toFront();
        try {
            frame.setSelected( true );
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    public void updateListFrame() {
        this.listFrame.updateList(tableFrames);
    }

    private void renameDatabase() {
        String newName = JOptionPane.showInputDialog(this.desktop, "Name for this database?", this.database.getDatabaseName());        
        
        if (newName != null && !newName.trim().equals("")) {
            newName = newName.trim();
            this.database.setDatabaseName(newName);
            this.updateTitle();
        }
    }
    
    private void saveDatabaseXml() {
        DatabaseBean databasebean = new DatabaseBean();
        databasebean.setDatabaseName( this.database.getDatabaseName() );
        for(TableFrame frame : this.tableFrames) {
            databasebean.getTables().getTables().add( frame.getBean() );
        }
        File file = new File(FileUtility.DATABASE_FOLDER + File.separator + databasebean.getDatabaseName() + FileUtility.FileExtension);
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
            File file = new File(FileUtility.DATABASE_FOLDER + File.separator + this.database.getDatabaseName() + FileUtility.FileExtension);
            if (file.exists()) {
                if (file.delete()) {
                    JOptionPane.showInternalMessageDialog(this.desktop, "Database deleted!", "Title", JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    JOptionPane.showInternalMessageDialog(this.desktop, "Database was NOT deleted!", "Title", JOptionPane.INFORMATION_MESSAGE);
                }
                    
            } else {
                JOptionPane.showInternalMessageDialog(this.desktop, "File " + file.getName() + " not found.\nDatabase was NOT deleted!", "Title", JOptionPane.INFORMATION_MESSAGE);
            }
            this.database.setDatabaseName( Database.DEFAULT_NAME );
            this.updateTitle();
            this.tableFrames.clear();
            this.database.getTableBeans().clear();
            this.updateListFrame();
        }
    }

    private void listDatabase() {
        this.databases.clear();
        File dbFolder = new File( FileUtility.DATABASE_FOLDER );
        File[] files = dbFolder.listFiles( Tools.dbFileFilterXml() );
        DatabaseBean dbBean;
        for (File file : files) {
            dbBean = DatabaseBean.loadXml(file);
            this.databases.add(dbBean);
        }
    }
    public void getDatabase(DatabaseBean databaseBean) {
        this.database.setDatabaseName( databaseBean.getDatabaseName() );
        this.database.getTableBeans().clear();
        this.updateTitle();
        for (TableBean bean : databaseBean.getTables().getTables()) {
            this.database.getTableBeans().add( bean );
            this.tableFrames.add( new TableFrame( bean.getName()) );
            
        }
        this.chooser.setVisible( false );
        this.updateListFrame();
        this.listFrame.setVisible( true );
        try {
            this.listFrame.setSelected( true );
        } catch (PropertyVetoException ex) { }
    }

    private void newDatabase() {
        this.database.getTableBeans().clear();
        this.database.setDatabaseName( Database.DEFAULT_NAME );
        this.updateTitle();
        this.updateListFrame();
    }
    
    private void centerJInternalFrame( JInternalFrame frame ) {
        int maxWidth = this.getWidth();
        int maxHeight = this.getHeight();
        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();
        int X = maxWidth / 2 - windowWidth / 2;
        int Y = maxHeight / 2 - windowHeight / 2;
        frame.setLocation(X, Y);
    }

    @Override
    public void windowOpened(WindowEvent e) {  }        

    @Override
    public void windowClosing(WindowEvent e) { }

    @Override
    public void windowClosed(WindowEvent e) { }

    @Override
    public void windowIconified(WindowEvent e) { }

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) {
        int maxWidth = this.getWidth();
        int marginRight = 20;
        int marginTop = 20;
        // System.out.println("Change state, maxWidth " + maxWidth);        
        int X = maxWidth - marginRight - templateFrame.getSize().width;        
        templateFrame.setLocation(X, marginTop);
    }        

    @Override
    public void windowDeactivated(WindowEvent e) { }

    @Override
    public void windowStateChanged(WindowEvent e) { 
        
    }
    
}
