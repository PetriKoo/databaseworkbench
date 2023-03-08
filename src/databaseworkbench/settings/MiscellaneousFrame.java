package databaseworkbench.settings;

import databaseworkbench.Configs;
import databaseworkbench.DatabaseWorkbench;
import databaseworkbench.FileUtility;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class MiscellaneousFrame extends javax.swing.JInternalFrame implements InternalFrameListener {

    private static MiscellaneousFrame INSTANCE = null;
    
    /**
     * Creates new form MiscellaneousFrame
     */
    private MiscellaneousFrame() {
        initComponents();
        this.addInternalFrameListener( this );
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {                
            this.comboLookAndFeel.addItem( info.getName() );
        }          
    }
    
    public static MiscellaneousFrame getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MiscellaneousFrame();
        }
        return INSTANCE;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtSave = new javax.swing.JButton();
        jtbCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtfCharset = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfNotNull = new javax.swing.JTextField();
        jtfUnique = new javax.swing.JTextField();
        jtfAutoIncrement = new javax.swing.JTextField();
        jtfPrimaryKey = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfOutputPath = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtfTemplatePath = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtfWorkingDir = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        comboLookAndFeel = new javax.swing.JComboBox<>();

        setClosable(true);
        setTitle("Miscellaneous settings");

        jbtSave.setText("Save");
        jbtSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSaveActionPerformed(evt);
            }
        });

        jtbCancel.setText("Cancel");
        jtbCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbCancelActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Working directory");

        jtfCharset.setText("jtfCharset");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Not null");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Unique");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Auto Increment");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Primary Key");

        jtfNotNull.setText("jtfNotNull");

        jtfUnique.setText("jTextField3");

        jtfAutoIncrement.setText("jTextField2");

        jtfPrimaryKey.setText("jTextField2");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Output path");

        jtfOutputPath.setText("jTextField2");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Template path");

        jtfTemplatePath.setText("jTextField2");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Charset");

        jtfWorkingDir.setText("jTextField1");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Look & Feel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jtbCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtSave))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCharset))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNotNull))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfUnique, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfPrimaryKey))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfAutoIncrement))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfOutputPath))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfTemplatePath))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfWorkingDir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboLookAndFeel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfWorkingDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCharset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfNotNull, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfUnique, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfAutoIncrement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfPrimaryKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfOutputPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtfTemplatePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(comboLookAndFeel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSave)
                    .addComponent(jtbCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSaveActionPerformed
        this.saveSettings();
        this.dispose();
    }//GEN-LAST:event_jbtSaveActionPerformed

    private void jtbCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jtbCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboLookAndFeel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jbtSave;
    private javax.swing.JButton jtbCancel;
    private javax.swing.JTextField jtfAutoIncrement;
    private javax.swing.JTextField jtfCharset;
    private javax.swing.JTextField jtfNotNull;
    private javax.swing.JTextField jtfOutputPath;
    private javax.swing.JTextField jtfPrimaryKey;
    private javax.swing.JTextField jtfTemplatePath;
    private javax.swing.JTextField jtfUnique;
    private javax.swing.JTextField jtfWorkingDir;
    // End of variables declaration//GEN-END:variables

    private void loadSettings() {
        this.jtfCharset.setText( Configs.getInstance().get("charset") );
        this.jtfWorkingDir.setText( Configs.getInstance().get("working_dir") );
        this.jtfNotNull.setText( Configs.getInstance().get("not_null") );
        this.jtfUnique.setText( Configs.getInstance().get("unique") );
        this.jtfAutoIncrement.setText( Configs.getInstance().get("auto_increment") );        
        this.jtfPrimaryKey.setText( Configs.getInstance().get("primary_key") );
        this.jtfOutputPath.setText( Configs.getInstance().get("output_path") );
        this.jtfTemplatePath.setText( Configs.getInstance().get("template_path") );
        this.comboLookAndFeel.setSelectedItem( Configs.getInstance().get("lookandfeel") );
    }
    
    private void saveSettings() {
        Configs.getInstance().set("charset", this.jtfCharset.getText() );
        Configs.getInstance().set("not_null", this.jtfNotNull.getText() );
        Configs.getInstance().set("unique", this.jtfUnique.getText() );
        Configs.getInstance().set("auto_increment", this.jtfAutoIncrement.getText() );
        Configs.getInstance().set("primary_key", this.jtfPrimaryKey.getText() );
        Configs.getInstance().set("output_path", FileUtility.fixPath(this.jtfOutputPath.getText()) );
        Configs.getInstance().set("template_path", FileUtility.fixPath(this.jtfTemplatePath.getText()) );
        Configs.getInstance().set("working_dir", FileUtility.fixPath(this.jtfWorkingDir.getText()) );
        DatabaseWorkbench.makeWorkFolders();
        
        if (!Configs.getInstance().get("lookandfeel").equals(this.comboLookAndFeel.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Look and Feel changes next you start program");
        }
        Configs.getInstance().set("lookandfeel", this.comboLookAndFeel.getSelectedItem().toString() );
    }
    
    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        this.loadSettings();
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
    public void internalFrameActivated(InternalFrameEvent e) {
        this.loadSettings();
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) { }
}
