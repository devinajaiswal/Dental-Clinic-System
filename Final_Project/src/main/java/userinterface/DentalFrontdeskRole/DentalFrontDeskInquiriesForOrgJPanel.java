/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.DentalFrontdeskRole;

import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.InquiryWorkRequest;
import Business.WorkQueue.Message;
import Business.WorkQueue.WorkRequest;
import java.awt.Container;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author raunak
 */
public class DentalFrontDeskInquiriesForOrgJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount account;
    private Organization organization;
    private Enterprise enterprise;

    /**
     * Creates new form ManageEnterpriseJPanel
     */
    public DentalFrontDeskInquiriesForOrgJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.organization = organization;
        this.enterprise = enterprise;

        populateTable();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) frameReply.getUI();
        Container north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
        frameReply.setVisible(false);
    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) tableToOrg.getModel();

        model.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");
        for (WorkRequest request : data.InquiryWorkRequestDAO.searchByOrgId(organization.getOrganizationID())) {
            Object[] row = new Object[3];
            row[0] = request.getSenderUsername();
            row[1] = formatter.format(request.getRequestTime());
            row[2] = request;
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
        tableToOrg = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        frameReply = new javax.swing.JInternalFrame();
        labAvailable = new javax.swing.JLabel();
        labUnavailable = new javax.swing.JLabel();
        buttonConfirm = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtReply = new javax.swing.JTextArea();
        buttonReply = new javax.swing.JButton();

        tableToOrg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "From", "Sent Time", "Message"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableToOrg);
        if (tableToOrg.getColumnModel().getColumnCount() > 0) {
            tableToOrg.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableToOrg.getColumnModel().getColumn(1).setPreferredWidth(20);
            tableToOrg.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel4.setText("Patients Inquries");

        frameReply.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        frameReply.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        frameReply.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        frameReply.setEnabled(false);
        frameReply.setVisible(true);

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

        txtReply.setColumns(20);
        txtReply.setRows(5);
        jScrollPane2.setViewportView(txtReply);

        javax.swing.GroupLayout frameReplyLayout = new javax.swing.GroupLayout(frameReply.getContentPane());
        frameReply.getContentPane().setLayout(frameReplyLayout);
        frameReplyLayout.setHorizontalGroup(
            frameReplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameReplyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameReplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameReplyLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(labUnavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameReplyLayout.createSequentialGroup()
                        .addComponent(buttonConfirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        frameReplyLayout.setVerticalGroup(
            frameReplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameReplyLayout.createSequentialGroup()
                .addGroup(frameReplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labUnavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameReplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        buttonReply.setText("Make An Reply");
        buttonReply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReplyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(frameReply, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonReply)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReply, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frameReply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resetFrameReply() {
        frameReply.setVisible(false);
        tableToOrg.setEnabled(true);
        txtReply.setText("");
    }

    private void setButtonsEnabled(boolean enable) {
        buttonReply.setEnabled(enable);
    }

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        if (!userinterface.Util.requireNotEmpty(this, txtReply)) {
            return;
        }
        String messageText = txtReply.getText();
        InquiryWorkRequest request = (InquiryWorkRequest) tableToOrg.getValueAt(tableToOrg.getSelectedRow(), 2);
        request.setMessage(messageText);
        request.setRequestTime(LocalDateTime.now());
        request.setReceiverUsername(request.getSenderUsername());
        request.setSenderUsername(account.getUsername());
        data.WorkRequestDAO.update(request);

        Message message;
        message = new Message();
        message.setFromUsername(account.getUsername());
        message.setToUsername(request.getReceiverUsername());
        message.setMessage(messageText);
        message.setSentTime(LocalDateTime.now());
        data.InquiryWorkRequestDAO.createMessage(request.getRequestId(), message);

        JOptionPane.showMessageDialog(this, "Messasge Sent!");

        resetFrameReply();
        setButtonsEnabled(true);
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        frameReply.setVisible(false);
        resetFrameReply();
        setButtonsEnabled(true);
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonReplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReplyActionPerformed
        if (tableToOrg.getSelectedRow() >= 0) {
            frameReply.setVisible(true);
            setButtonsEnabled(false);
            tableToOrg.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }
    }//GEN-LAST:event_buttonReplyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonReply;
    private javax.swing.JInternalFrame frameReply;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labAvailable;
    private javax.swing.JLabel labUnavailable;
    private javax.swing.JTable tableToOrg;
    private javax.swing.JTextArea txtReply;
    // End of variables declaration//GEN-END:variables
}
