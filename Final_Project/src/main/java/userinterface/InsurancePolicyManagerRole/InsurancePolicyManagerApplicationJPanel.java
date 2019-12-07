/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.InsurancePolicyManagerRole;

import Business.Enterprise.Enterprise;
import Business.Enterprise.InsurancePlan;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.PolicyWorkRequest;
import Business.WorkQueue.TreatmentWorkRequest;
import data.PricesDAO;
import java.awt.Container;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import userinterface.DetailJFrame;
import userinterface.MedicalInfoJPanel;

/**
 *
 * @author raunak
 */
public class InsurancePolicyManagerApplicationJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount account;
    private Organization organization;
    private Enterprise enterprise;

    /**
     * Creates new form ManageEnterpriseJPanel
     */
    public InsurancePolicyManagerApplicationJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.organization = organization;
        this.enterprise = enterprise;

        populateTable();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) frameTreatment.getUI();
        Container north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
        frameTreatment.setVisible(false);

        comboDecision.removeAll();
        comboDecision.addItem(PolicyWorkRequest.Status.APPROVED.getValue());
        comboDecision.addItem(PolicyWorkRequest.Status.REJECTED.getValue());

        comboDiscount.removeAll();
        for (int i = 0; i <= 30; i++) {
            comboDiscount.addItem(i + "");
        }
    }

    private void populateTable(ArrayList<PolicyWorkRequest> list) {
        DefaultTableModel model = (DefaultTableModel) tableTreatment.getModel();

        model.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for (PolicyWorkRequest request : list) {
            Object[] row = new Object[6];
            row[0] = request.getUsername();
            row[1] = formatter.format(request.getRequestTime());
            row[2] = request;
            InsurancePlan plan = PricesDAO.searchInsurancePlan(request.getPlanId());
            row[3] = plan.getPlanName();
            row[4] = plan.getPrice();
            row[5] = formatter2.format(request.getStartDate());
            model.addRow(row);
        }

    }

    private void populateTable() {
        populateTable(data.PolicyDAO.findPolicyByOrgAndStatus(
            organization.getOrganizationID(), PolicyWorkRequest.Status.APPLIED.getValue()));
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
        tableTreatment = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        frameTreatment = new javax.swing.JInternalFrame();
        labAvailable = new javax.swing.JLabel();
        labUnavailable = new javax.swing.JLabel();
        buttonConfirm = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        comboDecision = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboDiscount = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        buttonDecision = new javax.swing.JButton();
        buttonMedical = new javax.swing.JButton();
        buttonHistory = new javax.swing.JButton();

        tableTreatment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Applicant Username", "Application Time", "Message", "Plan Name", "Base Premium", "Start Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableTreatment);
        if (tableTreatment.getColumnModel().getColumnCount() > 0) {
            tableTreatment.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableTreatment.getColumnModel().getColumn(1).setPreferredWidth(20);
        }

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel4.setText("Policy Application");

        frameTreatment.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        frameTreatment.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        frameTreatment.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        frameTreatment.setEnabled(false);
        frameTreatment.setVisible(true);

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

        jLabel1.setText("Decision");

        jLabel2.setText("Discount");

        jLabel3.setText("Off");

        javax.swing.GroupLayout frameTreatmentLayout = new javax.swing.GroupLayout(frameTreatment.getContentPane());
        frameTreatment.getContentPane().setLayout(frameTreatmentLayout);
        frameTreatmentLayout.setHorizontalGroup(
            frameTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameTreatmentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameTreatmentLayout.createSequentialGroup()
                        .addComponent(buttonConfirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancel))
                    .addGroup(frameTreatmentLayout.createSequentialGroup()
                        .addGroup(frameTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(frameTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboDecision, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboDiscount, 0, 200, Short.MAX_VALUE))
                        .addGroup(frameTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(frameTreatmentLayout.createSequentialGroup()
                                .addGap(590, 590, 590)
                                .addComponent(labAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(labUnavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(frameTreatmentLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        frameTreatmentLayout.setVerticalGroup(
            frameTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameTreatmentLayout.createSequentialGroup()
                .addGroup(frameTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labUnavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameTreatmentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(frameTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboDecision, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameTreatmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        buttonDecision.setText("Give A Decision");
        buttonDecision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDecisionActionPerformed(evt);
            }
        });

        buttonMedical.setText("View Patient Medical Info");
        buttonMedical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMedicalActionPerformed(evt);
            }
        });

        buttonHistory.setText("View Patient Treatment History");
        buttonHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(frameTreatment, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonDecision)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonMedical)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonHistory)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDecision, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonMedical, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frameTreatment))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resetFrame() {
        frameTreatment.setVisible(false);
        tableTreatment.setEnabled(true);
        comboDecision.setSelectedIndex(0);
        comboDiscount.setSelectedIndex(0);
    }

    private void setButtonsEnabled(boolean enable) {
        buttonDecision.setEnabled(enable);
    }

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        if (!userinterface.Util.requireSeletedItemNotNull(this, comboDiscount, comboDecision)) {
            return;
        }

        PolicyWorkRequest request = (PolicyWorkRequest) tableTreatment.getValueAt(tableTreatment.getSelectedRow(), 2);
        request.setMessage("Policy application decision made");
        request.setFinishTime(LocalDateTime.now());
        data.WorkRequestDAO.update(request);

        request.setStatus((String) comboDecision.getSelectedItem());
        int discount = Integer.parseInt((String) comboDiscount.getSelectedItem());
        double basePrice = (double) tableTreatment.getValueAt(tableTreatment.getSelectedRow(), 4);
        request.setPremium(basePrice * (1 - discount / 100));
        data.PolicyDAO.updateStatusAndPremium(request);

        JOptionPane.showMessageDialog(this, "Policy applicatio complete!");

        resetFrame();
        setButtonsEnabled(true);
        populateTable();
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        frameTreatment.setVisible(false);
        resetFrame();
        setButtonsEnabled(true);
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonDecisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDecisionActionPerformed
        if (tableTreatment.getSelectedRow() >= 0) {
            frameTreatment.setVisible(true);
            setButtonsEnabled(false);
            tableTreatment.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }
    }//GEN-LAST:event_buttonDecisionActionPerformed

    private void buttonMedicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMedicalActionPerformed
        if (tableTreatment.getSelectedRow() >= 0) {
            TreatmentWorkRequest request = (TreatmentWorkRequest) tableTreatment.getValueAt(tableTreatment.getSelectedRow(), 2);
            DetailJFrame customerJFrame = new DetailJFrame();
            customerJFrame.setSize(600, 400);
            customerJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            customerJFrame.setLocationRelativeTo(this);
            customerJFrame.setContentPane(new MedicalInfoJPanel(request.getPatientUsername()));
            customerJFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }

    }//GEN-LAST:event_buttonMedicalActionPerformed

    private void buttonHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHistoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonHistoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonDecision;
    private javax.swing.JButton buttonHistory;
    private javax.swing.JButton buttonMedical;
    private javax.swing.JComboBox<String> comboDecision;
    private javax.swing.JComboBox<String> comboDiscount;
    private javax.swing.JInternalFrame frameTreatment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labAvailable;
    private javax.swing.JLabel labUnavailable;
    private javax.swing.JTable tableTreatment;
    // End of variables declaration//GEN-END:variables
}
