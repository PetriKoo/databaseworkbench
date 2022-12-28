package databaseworkbench;

import databaseworkbench.beans.TableBean;
import databaseworkbench.beans.TableFieldBean;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author petri
 */
public class FieldForm extends javax.swing.JPanel {

    TableFieldBean data = null;
    private int fieldIndex;
    private TableBean bean;
    private boolean bNewMode = false;
    void setNewMode(boolean b) { this.bNewMode = b; }
    /**
     * Creates new form TableForm
     */
    public FieldForm() {
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

        checkboxPrimKey = new javax.swing.JCheckBox();
        checkboxNotNull = new javax.swing.JCheckBox();
        checkboxAutoInc = new javax.swing.JCheckBox();
        checkboxUnique = new javax.swing.JCheckBox();
        comboboxType = new javax.swing.JComboBox<>();
        nameTextfield = new javax.swing.JTextField();
        labelName = new javax.swing.JLabel();
        labelDescription = new javax.swing.JLabel();
        labelType = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textareaDescription = new javax.swing.JTextArea();
        labelDefaultValue = new javax.swing.JLabel();
        textfieldDefaultValue = new javax.swing.JTextField();

        checkboxPrimKey.setText("Primary Key");
        checkboxPrimKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxPrimKeyActionPerformed(evt);
            }
        });

        checkboxNotNull.setLabel("Not Null");
        checkboxNotNull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxNotNullActionPerformed(evt);
            }
        });

        checkboxAutoInc.setText("Auto Increment");

        checkboxUnique.setText("Unique");

        comboboxType.setModel(new DefaultComboBoxModel<>(FieldTypeEnum.values()));
        comboboxType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxTypeActionPerformed(evt);
            }
        });

        nameTextfield.setText("namefield");
        nameTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextfieldActionPerformed(evt);
            }
        });

        labelName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelName.setText("Name");

        labelDescription.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelDescription.setText("Description");

        labelType.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelType.setText("Type");

        textareaDescription.setColumns(20);
        textareaDescription.setRows(5);
        jScrollPane1.setViewportView(textareaDescription);

        labelDefaultValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelDefaultValue.setText("Default Value");

        textfieldDefaultValue.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelDefaultValue, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(labelDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkboxPrimKey)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkboxNotNull)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkboxAutoInc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkboxUnique))
                    .addComponent(jScrollPane1)
                    .addComponent(textfieldDefaultValue)
                    .addComponent(comboboxType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameTextfield))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(nameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(checkboxNotNull)
                        .addComponent(checkboxAutoInc)
                        .addComponent(checkboxUnique))
                    .addComponent(checkboxPrimKey, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboboxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDefaultValue)
                    .addComponent(textfieldDefaultValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkboxPrimKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxPrimKeyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxPrimKeyActionPerformed

    private void checkboxNotNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxNotNullActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxNotNullActionPerformed

    private void nameTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextfieldActionPerformed

    private void comboboxTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboboxTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkboxAutoInc;
    private javax.swing.JCheckBox checkboxNotNull;
    private javax.swing.JCheckBox checkboxPrimKey;
    private javax.swing.JCheckBox checkboxUnique;
    private javax.swing.JComboBox<FieldTypeEnum> comboboxType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDefaultValue;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelType;
    public javax.swing.JTextField nameTextfield;
    private javax.swing.JTextArea textareaDescription;
    private javax.swing.JTextField textfieldDefaultValue;
    // End of variables declaration//GEN-END:variables

    void putData(TableBean bean, int fieldIndex) {
        this.fieldIndex = fieldIndex;
        this.bean = bean;
        data = bean.getFields().get(fieldIndex);
        checkboxPrimKey.setSelected( data.isPrimarykey() );
        checkboxAutoInc.setSelected( data.isAuto_increment() );
        checkboxNotNull.setSelected( data.isNotnull() );
        checkboxUnique.setSelected( data.isUnique() );
        comboboxType.setSelectedItem( data.getType() );
        nameTextfield.setText( data.getName() );
        textareaDescription.setText( data.getDescription() );
        textfieldDefaultValue.setText( data.getDefault_value() );
    }
    
    TableBean getData() {
        TableFieldBean data = this.gatherData();
        if (this.bNewMode) {
            
        } else {
            this.bean.getFields().remove( this.fieldIndex );
            this.bean.getFields().add( this.fieldIndex, data);
        }
        return this.bean;
    }
    
    TableFieldBean getNewData() {
        return this.gatherData();
    }
    
    
    private TableFieldBean gatherData() {
        TableFieldBean data = new TableFieldBean();
        data.setAuto_increment( checkboxAutoInc.isSelected() );
        data.setPrimarykey( checkboxPrimKey.isSelected() );
        data.setNotnull( checkboxNotNull.isSelected() );
        data.setUnique( checkboxUnique.isSelected() );
        data.setType( (FieldTypeEnum) comboboxType.getSelectedItem() );
        data.setName( nameTextfield.getText() );
        data.setDefault_value( textfieldDefaultValue.getText() );
        data.setDescription( textfieldDefaultValue.getText() );
        return data;
    }

    void empty() {
        checkboxPrimKey.setSelected( false );
        checkboxAutoInc.setSelected( false );
        checkboxNotNull.setSelected( false );
        checkboxUnique.setSelected( false );
        comboboxType.setSelectedItem( FieldTypeEnum.INTEGER );
        nameTextfield.setText( "" );
        textareaDescription.setText( "" );
        textfieldDefaultValue.setText( "" );
    }
       
}
