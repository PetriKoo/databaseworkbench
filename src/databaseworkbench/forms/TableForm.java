package databaseworkbench.forms;

/**
 *
 * @author Petri Koskelainen <pete.software.industries@gmail.com>
 */
public class TableForm extends javax.swing.JPanel {

    /**
     * Creates new form TableForm
     */
    public TableForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelName = new javax.swing.JLabel();
        textfieldName = new javax.swing.JTextField();
        labelDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textareaDescription = new javax.swing.JTextArea();

        labelName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelName.setText("Name");

        textfieldName.setText("jTextField1");

        labelDescription.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelDescription.setText("Description");

        textareaDescription.setColumns(20);
        textareaDescription.setRows(5);
        jScrollPane1.setViewportView(textareaDescription);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(labelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textfieldName)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(textfieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void clearData() {
        textfieldName.setText( "" );
        textareaDescription.setText( "" );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelName;
    private javax.swing.JTextArea textareaDescription;
    private javax.swing.JTextField textfieldName;
    // End of variables declaration//GEN-END:variables
}