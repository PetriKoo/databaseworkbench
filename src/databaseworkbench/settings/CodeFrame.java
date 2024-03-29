package databaseworkbench.settings;
import databaseworkbench.FileUtility;
import databaseworkbench.beans.LanguageBean;
import databaseworkbench.beans.LanguageCodesBean;
import databaseworkbench.settings.code.CodesModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JMenuItem;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class CodeFrame extends javax.swing.JInternalFrame implements InternalFrameListener, ActionListener {

    private static CodeFrame INSTANCE = null;
    
    
    
    /**
     * Creates new form CodeFrame
     */
    private CodeFrame() {
        initComponents();
    }
    
    public static synchronized CodeFrame getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CodeFrame();
            INSTANCE.addInternalFrameListener( INSTANCE );           
        }
        return INSTANCE;
    }
    
    
    public void setLanguage(String sLang) {
       setTitle( "Codes for " + sLang );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableCodes = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        menuLanguages = new javax.swing.JMenu();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Codes");
        setToolTipText("");

        tableCodes.setModel(CodesModel.getInstance());
        jScrollPane1.setViewportView(tableCodes);

        menuLanguages.setText("Languages");
        menuBar.add(menuLanguages);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuLanguages;
    private javax.swing.JTable tableCodes;
    // End of variables declaration//GEN-END:variables

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        updateMenu();
        CodeFrame.getInstance().setLanguage( CodesModel.getInstance().getSelectedLanguage().getName() );
    }
    
    private void updateMenu() {
        menuLanguages.removeAll();
        LanguageBean[] languages = LanguageBean.values();
        JMenuItem langMenuItem;
        for (LanguageBean lang : languages) {
            langMenuItem = new JMenuItem( lang.getName() );
            langMenuItem.setActionCommand("change " + lang.getName());            
            langMenuItem.addActionListener( this );
            this.menuLanguages.add( langMenuItem );
        }
        this.menuLanguages.addSeparator();
        JMenuItem updateItem = new JMenuItem("Update");
        updateItem.setActionCommand("update");
        updateItem.addActionListener( this );
        this.menuLanguages.add( updateItem );
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("update")) {
            this.updateMenu();
            return;
        }
        if (e.getActionCommand().startsWith("change ")) {
            String[] stuff = e.getActionCommand().split(" ");
            String language = stuff[1];
            for(LanguageBean lang: LanguageBean.values()) {
                if (language.equals(lang.getName())) {
                    CodesModel.getInstance().setLanguage(lang);
                    this.setLanguage(language);
                }
            }
        }
    }
}
