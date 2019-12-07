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
import data.PricesDAO;
import java.awt.Container;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raunak
 */
public class InsurancePolicyManagerPoliciesJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount account;
    private Organization organization;
    private Enterprise enterprise;

    /**
     * Creates new form ManageEnterpriseJPanel
     */
    public InsurancePolicyManagerPoliciesJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.organization = organization;
        this.enterprise = enterprise;

        populateTable();

        comboStatus.removeAll();
        comboStatus.addItem("");
        comboStatus.addItem(PolicyWorkRequest.Status.APPLIED.getValue());
        comboStatus.addItem(PolicyWorkRequest.Status.APPROVED.getValue());
        comboStatus.addItem(PolicyWorkRequest.Status.REJECTED.getValue());

    }

    private void populateTable(ArrayList<PolicyWorkRequest> list) {
        DefaultTableModel model = (DefaultTableModel) tableTreatment.getModel();

        model.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for (PolicyWorkRequest request : list) {
            Object[] row = new Object[6];
            row[0] = request.getUsername();
            row[1] = request.getStatus();
            row[2] = request;
            InsurancePlan plan = PricesDAO.searchInsurancePlan(request.getPlanId());
            row[3] = plan.getPlanName();
            row[4] = request.getPremium();
            row[5] = formatter.format(request.getStartDate());
            model.addRow(row);
        }

    }

    private void populateTable() {
        populateTable(data.PolicyDAO.findPolicyByOrg(organization.getOrganizationID()));
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
        jLabel6 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        comboStatus = new javax.swing.JComboBox<>();
        buttonSearch = new javax.swing.JButton();

        tableTreatment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Applicant Username", "Status", "Message", "Plan Name", "Premium", "Start Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableTreatment);
        if (tableTreatment.getColumnModel().getColumnCount() > 0) {
            tableTreatment.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel4.setText("Policy Management");

        jLabel6.setText("Keyword");

        comboStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboStatusActionPerformed(evt);
            }
        });

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSearch)
                        .addGap(0, 201, Short.MAX_VALUE)))
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
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    private void comboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStatusActionPerformed

    }//GEN-LAST:event_comboStatusActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        String keyword = txtSearch.getText();
        String status = (String) comboStatus.getSelectedItem();
        ArrayList<PolicyWorkRequest> list;
        if (status.equals("")) {
            list = data.PolicyDAO.findPolicyByOrg(organization.getOrganizationID());
        } else {
            list = data.PolicyDAO.findPolicyByOrgAndStatus(organization.getOrganizationID(), status);
        }
        ArrayList<PolicyWorkRequest> result = new ArrayList<>();

        for (PolicyWorkRequest request : list) {
            InsurancePlan plan = PricesDAO.searchInsurancePlan(request.getPlanId());
            if (request.getMessage().contains(keyword) || request.getSenderUsername().contains(keyword)
                || plan.getPlanName().contains(keyword)) {
                result.add(request);
            }
        }
        populateTable(result);
    }//GEN-LAST:event_buttonSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSearch;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableTreatment;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
