/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.EnterpriseAdminRole;

import userinterface.SystemAdminRole.*;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.EnterpriseAdminRole;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import java.awt.Container;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raunak
 */
public class ManageUserAccountJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private String currentAction = null;
    private final String ACTION_ADD = "ADD";
    private final String ACTION_EDIT = "EDIT";
    private UserAccount account;
    private Organization organization;
    private Enterprise enterprise;

    /**
     * Creates new form ManageEnterpriseJPane¡l
     */
    public ManageUserAccountJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.organization = organization;
        this.enterprise = enterprise;

        populateTable();
        populateOrganizationComboBox();
        Organization org = (Organization) comboOrganization.getSelectedItem();
        populateRoleComboBox(org);

        BasicInternalFrameUI ui = (BasicInternalFrameUI) jInternalFrame1.getUI();
        Container north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
        jInternalFrame1.setVisible(false);
    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) tableUsers.getModel();

        model.setRowCount(0);
        for (UserAccount userAccount : data.UserDAO.getAllUsersByEnterpriseId(enterprise.getEnterpriseId())) {
            Object[] row = new Object[3];
            row[0] = userAccount.getOrganization().getName();
            row[1] = userAccount.getRole();
            row[2] = userAccount;
            model.addRow(row);
        }
    }

    private void populateOrganizationComboBox() {
        comboOrganization.removeAllItems();
        for (Organization org : enterprise.getSupportedOrganizations()) {
            comboOrganization.addItem(org);
        }
    }

    private void populateRoleComboBox(Organization org) {
        comboRole.removeAllItems();

        for (Role role : org.getSupportedRole()) {
            comboRole.addItem(role);
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
        tableUsers = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        labAvailable = new javax.swing.JLabel();
        labUnavailable = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboOrganization = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        comboRole = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        buttonConfirm = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtRepassword = new javax.swing.JPasswordField();
        buttonCancel = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        buttonAddNew = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();

        tableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Organization Name", "Role Type", "Username"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableUsers);

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel6.setText("User Account Management");

        jInternalFrame1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jInternalFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jInternalFrame1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jInternalFrame1.setEnabled(false);
        jInternalFrame1.setVisible(true);

        jLabel3.setText("Organization *");

        comboOrganization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOrganizationActionPerformed(evt);
            }
        });

        jLabel1.setText("Role *");

        comboRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRoleActionPerformed(evt);
            }
        });

        jLabel2.setText("Username *");

        jLabel4.setText("Password *");

        jLabel5.setText("Name *");

        buttonConfirm.setText("Confirm");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        jLabel7.setText("Confrim Password *");

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        jLabel8.setText("Email *");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(50, 50, 50)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPassword)
                            .addComponent(comboRole, 0, 200, Short.MAX_VALUE)
                            .addComponent(txtRepassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(labUnavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labUnavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(comboOrganization, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(comboRole, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtRepassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel)
                    .addComponent(buttonConfirm))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonAddNew.setText("Add New");
        buttonAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddNewActionPerformed(evt);
            }
        });

        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 719, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jInternalFrame1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEdit)
                    .addComponent(buttonAddNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboOrganizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOrganizationActionPerformed
        Organization org = (Organization) comboOrganization.getSelectedItem();
        if (org != null) {
            populateRoleComboBox(org);
        }
    }//GEN-LAST:event_comboOrganizationActionPerformed

    private void resetInternalFrame() {
        setComponentsBorderBlack();
        txtUsername.setText("");
        txtName.setText("");
        txtPassword.setText("");
        txtRepassword.setText("");
        comboRole.setSelectedIndex(0);
        comboOrganization.setSelectedIndex(0);
        jInternalFrame1.setVisible(false);
        currentAction = null;
        comboRole.setEnabled(true);
        comboRole.setEnabled(true);
        tableUsers.setEnabled(true);
    }

    private void setButtonsEnabled(boolean enable) {
        buttonAddNew.setEnabled(enable);
        buttonEdit.setEnabled(enable);
    }

    private void setEditFieldsEnabled(boolean enable) {
        txtUsername.setEnabled(enable);
        comboRole.setEnabled(enable);
        comboOrganization.setEnabled(enable);
    }

    private void setComponentsBorderBlack() {
        userinterface.Util.setBorderBlack(txtName, txtPassword, txtRepassword, txtUsername, comboOrganization, comboRole);
    }


    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        setComponentsBorderBlack();
        if (currentAction.equals(ACTION_ADD)) {
            if (!userinterface.Util.requireSeletedItemNotNull(this, comboRole, comboOrganization)) {
                return;
            }
            Enterprise enterprise = (Enterprise) comboOrganization.getSelectedItem();

            if (!userinterface.Util.requireNotEmpty(this, txtName, txtPassword, txtRepassword)) {
                return;
            }

            String password = String.valueOf(txtPassword.getPassword());
            String rePassword = String.valueOf(txtRepassword.getPassword());
            if (!password.equals(rePassword)) {
                JOptionPane.showMessageDialog(this, "Two passwords don't mathch!");
                return;
            }

            if (data.UserDAO.isUsernameExist(txtUsername.getText())) {
                JOptionPane.showMessageDialog(this, "Username already exists, please choose another one!");
                return;
            }

            UserAccount user = new UserAccount();
            user.setEnterprise(enterprise);
            user.setUsername(txtUsername.getText());
            user.setPassword(password);
            user.setRole(new EnterpriseAdminRole());
            Employee employee = new Employee();
            employee.setName(txtName.getText());
            user.setEmployee(employee);
            Organization.Type organizationType = null;
            if (enterprise.getEnterpriseType().getValue().equals(Enterprise.EnterpriseType.DENTAL_CLINIC.getValue())) {
                organizationType = Organization.Type.DentalAdmin;
            } else if (enterprise.getEnterpriseType().getValue().equals(Enterprise.EnterpriseType.INSURACE.getValue())) {
                organizationType = Organization.Type.InsuranceAdmin;
            }
            Organization organization = data.OrganizationDAO.searchByTypeAndEnterprise(
                organizationType.getValue(), enterprise.getEnterpriseId());
            data.UserDAO.createWithEmployee(user, organization.getOrganizationID());
            JOptionPane.showMessageDialog(this, "Created successfully!");
        } else if (currentAction.equals(ACTION_EDIT)) {
            if (!userinterface.Util.requireNotEmpty(this, txtName, txtPassword, txtRepassword)) {
                return;
            }

            String password = String.valueOf(txtPassword.getPassword());
            String rePassword = String.valueOf(txtRepassword.getPassword());
            if (!password.equals(rePassword)) {
                JOptionPane.showMessageDialog(this, "Two passwords don't mathch!");
                return;
            }
            UserAccount user = (UserAccount) tableUsers.getValueAt(tableUsers.getSelectedRow(), 2);
            data.UserDAO.updateNameAndPassword(user.getEmployee().getId(), txtName.getText(),
                user.getUsername(), password);
            JOptionPane.showMessageDialog(this, "Updated successfully!");
        }
        populateTable();
        SystemAdminWorkAreaJPanel parent = (SystemAdminWorkAreaJPanel) userProcessContainer;
        parent.populateTree();
        resetInternalFrame();
        jInternalFrame1.setVisible(false);
        setButtonsEnabled(true);
        setEditFieldsEnabled(true);
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        resetInternalFrame();
        jInternalFrame1.setVisible(false);
        setButtonsEnabled(true);
        setEditFieldsEnabled(true);
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddNewActionPerformed
        jInternalFrame1.setVisible(true);
        currentAction = ACTION_ADD;
        setButtonsEnabled(false);
    }//GEN-LAST:event_buttonAddNewActionPerformed

    private void comboRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRoleActionPerformed
    }//GEN-LAST:event_comboRoleActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        if (tableUsers.getSelectedRow() >= 0) {
            UserAccount user = (UserAccount) tableUsers.getValueAt(tableUsers.getSelectedRow(), 2);
            txtName.setText(user.getEmployee().getName());
            txtUsername.setText(user.getUsername());
            comboRole.setSelectedItem(user.getEnterprise().getNetwork());
            comboOrganization.setSelectedItem(user.getEnterprise());
            currentAction = ACTION_EDIT;
            jInternalFrame1.setVisible(true);
            setButtonsEnabled(false);
            setEditFieldsEnabled(false);
            tableUsers.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }
    }//GEN-LAST:event_buttonEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddNew;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JComboBox comboOrganization;
    private javax.swing.JComboBox comboRole;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labAvailable;
    private javax.swing.JLabel labUnavailable;
    private javax.swing.JTable tableUsers;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtRepassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
