/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.CustomerRole;

import Business.UserAccount.UserAccount;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author z9737
 */
public class CustomerPersonalInfoJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CustomerPersonalInfoJPanel
     */
    private UserAccount account;

    public CustomerPersonalInfoJPanel(UserAccount account) {
        initComponents();
        this.account = account;
    }

    /**
     * This method is called state within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        labelHead = new javax.swing.JLabel();
        lblRoutingNo = new javax.swing.JLabel();
        lblAccNo = new javax.swing.JLabel();
        txtStreet = new javax.swing.JTextField();
        txtPostcode = new javax.swing.JTextField();
        lblBalance4 = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        lblBalance3 = new javax.swing.JLabel();
        lblRoutingNo1 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        lblRoutingNo2 = new javax.swing.JLabel();
        labelFirstName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        labelLastName = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        labelDriversLiscence = new javax.swing.JLabel();
        txtDriverLiscence = new javax.swing.JTextField();
        buttonPhoneCode = new javax.swing.JButton();
        txtPhoneCode = new javax.swing.JTextField();
        lblBalance5 = new javax.swing.JLabel();
        buttonEmailCode = new javax.swing.JButton();
        txtPhoneCode1 = new javax.swing.JTextField();
        lblBalance6 = new javax.swing.JLabel();
        comboState = new javax.swing.JComboBox<>();

        labelHead.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelHead.setText("Personal Information");

        lblRoutingNo.setText("Street *");

        lblAccNo.setText("Post Code *");

        txtStreet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStreetActionPerformed(evt);
            }
        });

        lblBalance4.setText("Email *");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        txtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneActionPerformed(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        lblBalance3.setText("Phone *");

        lblRoutingNo1.setText("City *");

        txtCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCityActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblRoutingNo2.setText("State *");

        labelFirstName.setText("First Name *");

        labelLastName.setText("Last Name *");

        labelDriversLiscence.setText("SSN *");

        buttonPhoneCode.setText("Send Phone Verification Code");

        txtPhoneCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneCodeActionPerformed(evt);
            }
        });

        lblBalance5.setText("Phone Code *");

        buttonEmailCode.setText("Send Email Verification Code");

        txtPhoneCode1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneCode1ActionPerformed(evt);
            }
        });

        lblBalance6.setText("Email Code *");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelLastName)
                            .addComponent(labelDriversLiscence))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(txtDriverLiscence)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblAccNo)
                                    .addComponent(lblRoutingNo)
                                    .addComponent(lblRoutingNo1)
                                    .addComponent(lblRoutingNo2)
                                    .addComponent(lblBalance3)
                                    .addComponent(lblBalance4)
                                    .addComponent(lblBalance5)
                                    .addComponent(lblBalance6))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelFirstName)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCreate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancel))
                            .addComponent(txtStreet)
                            .addComponent(txtCity)
                            .addComponent(txtPostcode)
                            .addComponent(txtPhone)
                            .addComponent(txtPhoneCode)
                            .addComponent(buttonPhoneCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail)
                            .addComponent(labelHead)
                            .addComponent(txtFirstName)
                            .addComponent(buttonEmailCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPhoneCode1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboState, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelHead)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFirstName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(labelLastName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDriverLiscence, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(labelDriversLiscence))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStreet, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lblRoutingNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCity, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lblRoutingNo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboState, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lblRoutingNo2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPostcode, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lblAccNo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lblBalance3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPhoneCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhoneCode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBalance5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBalance4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEmailCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhoneCode1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(lblBalance6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnCreate))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void txtStreetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStreetActionPerformed
    }//GEN-LAST:event_txtStreetActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        txtStreet.setBorder(new LineBorder(new Color(128, 128, 128)));
        txtCity.setBorder(new LineBorder(new Color(128, 128, 128)));
        comboState.setBorder(new LineBorder(new Color(128, 128, 128)));
        txtPostcode.setBorder(new LineBorder(new Color(128, 128, 128)));
        txtPhone.setBorder(new LineBorder(new Color(128, 128, 128)));
        txtEmail.setBorder(new LineBorder(new Color(128, 128, 128)));
        txtFirstName.setBorder(new LineBorder(new Color(128, 128, 128)));
        txtLastName.setBorder(new LineBorder(new Color(128, 128, 128)));
        txtDriverLiscence.setBorder(new LineBorder(new Color(128, 128, 128)));

    
        JOptionPane.showMessageDialog(this, "Create customer successfully.");
    }//GEN-LAST:event_btnCreateActionPerformed

    private void txtCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCityActionPerformed
    }//GEN-LAST:event_txtCityActionPerformed

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneActionPerformed
    }//GEN-LAST:event_txtPhoneActionPerformed

    private void txtPhoneCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneCodeActionPerformed
    }//GEN-LAST:event_txtPhoneCodeActionPerformed

    private void txtPhoneCode1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneCode1ActionPerformed
    }//GEN-LAST:event_txtPhoneCode1ActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton buttonEmailCode;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonPhoneCode;
    private javax.swing.JComboBox<String> comboState;
    private javax.swing.JLabel labelDriversLiscence;
    private javax.swing.JLabel labelFirstName;
    private javax.swing.JLabel labelHead;
    private javax.swing.JLabel labelLastName;
    private javax.swing.JLabel lblAccNo;
    private javax.swing.JLabel lblBalance3;
    private javax.swing.JLabel lblBalance4;
    private javax.swing.JLabel lblBalance5;
    private javax.swing.JLabel lblBalance6;
    private javax.swing.JLabel lblRoutingNo;
    private javax.swing.JLabel lblRoutingNo1;
    private javax.swing.JLabel lblRoutingNo2;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtDriverLiscence;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPhoneCode;
    private javax.swing.JTextField txtPhoneCode1;
    private javax.swing.JTextField txtPostcode;
    private javax.swing.JTextField txtStreet;
    // End of variables declaration//GEN-END:variables
}
