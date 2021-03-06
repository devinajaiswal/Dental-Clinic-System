/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.SystemAdminRole;

import Business.Network.Network;
import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raunak
 */
public class ManageNetworkJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private String currentAction;
    private final String ACTION_ADD = "ADD";
    private final String ACTION_EDIT = "EDIT";

    /**
     *
     * Creates new form ManageNetworkJPanel
     */
    public ManageNetworkJPanel(JPanel userProcessContainer) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        populateNetworkTable();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) jInternalFrame1.getUI();
        Container north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
        jInternalFrame1.setVisible(false);
        buttonEdit.setVisible(false);

        comboNetworkName.removeAllItems();
        ArrayList<String> states = data.Data.getAllStates();
        for (String state : states) {
            comboNetworkName.addItem(state);
        }
    }

    private void populateNetworkTable() {
        DefaultTableModel model = (DefaultTableModel) tableNetwork.getModel();

        model.setRowCount(0);
        ArrayList<Network> networkList = data.NetworkDAO.getAll();
        for (Network network : networkList) {
            Object[] row = new Object[1];
            row[0] = network;
            model.addRow(row);
        }
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
        tableNetwork = new javax.swing.JTable();
        buttonEdit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        buttonAddNew = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        labAvailable = new javax.swing.JLabel();
        labUnavailable = new javax.swing.JLabel();
        buttonConfirm = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        comboNetworkName = new javax.swing.JComboBox<>();

        tableNetwork.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Network Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableNetwork.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tableNetworkFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(tableNetwork);
        if (tableNetwork.getColumnModel().getColumnCount() > 0) {
            tableNetwork.getColumnModel().getColumn(0).setResizable(false);
        }

        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setText("Network Management");

        buttonAddNew.setText("Add New");
        buttonAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddNewActionPerformed(evt);
            }
        });

        jInternalFrame1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jInternalFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jInternalFrame1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jInternalFrame1.setEnabled(false);
        jInternalFrame1.setVisible(true);

        buttonConfirm.setText("Confirm");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        jLabel3.setText("Network Name");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboNetworkName, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonConfirm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labUnavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(labUnavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(buttonConfirm)
                        .addComponent(buttonCancel)
                        .addComponent(comboNetworkName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEdit)
                    .addComponent(buttonAddNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resetInternalFrame() {
        comboNetworkName.setBorder(new LineBorder(new Color(128, 128, 128)));
        comboNetworkName.setSelectedIndex(1);
        jInternalFrame1.setVisible(false);
        currentAction = null;
    }

    private void setButtonsEnabled(boolean enable) {
        buttonAddNew.setEnabled(enable);
        buttonEdit.setEnabled(enable);
    }

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        if (tableNetwork.getSelectedRow() >= 0) {
            Network network = (Network) tableNetwork.getValueAt(tableNetwork.getSelectedRow(), 0);
//            txtName.setText(network.getName());
            currentAction = ACTION_EDIT;
            jInternalFrame1.setVisible(true);
            setButtonsEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }
    }//GEN-LAST:event_buttonEditActionPerformed

    private void tableNetworkFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tableNetworkFocusGained

    }//GEN-LAST:event_tableNetworkFocusGained

    private void buttonAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddNewActionPerformed
        jInternalFrame1.setVisible(true);
        setButtonsEnabled(false);
        currentAction = ACTION_ADD;
    }//GEN-LAST:event_buttonAddNewActionPerformed

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        comboNetworkName.setBorder(new LineBorder(new Color(128, 128, 128)));
        String name = (String) comboNetworkName.getSelectedItem();

        if (data.NetworkDAO.isNetworkNameExist(name)) {
            comboNetworkName.setBorder(new LineBorder(Color.RED));
            JOptionPane.showMessageDialog(this, "This network alread exists!");
            return;
        }

        if (currentAction.equals(ACTION_ADD)) {
            Network network = new Network();
            network.setName(name);
            data.NetworkDAO.create(network);
            tableNetwork.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Add successfully!");
        } else if (currentAction.equals(ACTION_EDIT)) {
            Network network = (Network) tableNetwork.getValueAt(tableNetwork.getSelectedRow(), 0);
//            if (network.getName().equals(txtName.getText())) {
//                resetInternalFrame();
//                tableNetwork.setEnabled(true);
//                return;
//            }
//            network.setName(txtName.getText());
            data.NetworkDAO.update(network);
            tableNetwork.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Updated successfully!");

        }

        populateNetworkTable();
        SystemAdminWorkAreaJPanel parent = (SystemAdminWorkAreaJPanel) userProcessContainer;
        parent.populateTree();
        resetInternalFrame();
        jInternalFrame1.setVisible(false);
        setButtonsEnabled(true);
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        jInternalFrame1.setVisible(false);
        resetInternalFrame();
        setButtonsEnabled(true);
    }//GEN-LAST:event_buttonCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddNew;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JComboBox<String> comboNetworkName;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labAvailable;
    private javax.swing.JLabel labUnavailable;
    private javax.swing.JTable tableNetwork;
    // End of variables declaration//GEN-END:variables
}
