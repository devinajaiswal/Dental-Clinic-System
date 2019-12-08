/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.DentalManagerRole;

import userinterface.CustomerRole.*;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author zhangchuanqi
 */
public class DentalManagerWorkAreaJPanel extends javax.swing.JPanel {

    JPanel userProcessContainer;
    UserAccount account;
    Organization organization;
    Enterprise enterprise;

    /**
     * Creates new form CustomerWorkAreaJPanel
     */
    public DentalManagerWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.organization = organization;
        this.enterprise = enterprise;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        buttonPersonalInfo = new javax.swing.JButton();
        btnPrices = new javax.swing.JButton();
        customerContainer = new javax.swing.JPanel();

        jSplitPane1.setDividerLocation(200);

        buttonPersonalInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/address.png"))); // NOI18N
        buttonPersonalInfo.setText("Clinic Information");
        buttonPersonalInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPersonalInfoActionPerformed(evt);
            }
        });

        btnPrices.setIcon(new javax.swing.ImageIcon(getClass().getResource("/money.png"))); // NOI18N
        btnPrices.setText("Treatment Prices");
        btnPrices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPricesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnPrices, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPersonalInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(buttonPersonalInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrices)
                .addGap(489, 489, 489))
        );

        jSplitPane1.setLeftComponent(jPanel3);

        customerContainer.setLayout(new java.awt.CardLayout());
        jSplitPane1.setRightComponent(customerContainer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPersonalInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPersonalInfoActionPerformed
        customerContainer.removeAll();
        DentalManagerCliniclInfoJPanel jpanel = new DentalManagerCliniclInfoJPanel(userProcessContainer, account, organization, enterprise);
        customerContainer.add(jpanel);
        CardLayout layout = (CardLayout) customerContainer.getLayout();
        layout.next(customerContainer);
    }//GEN-LAST:event_buttonPersonalInfoActionPerformed

    private void btnPricesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPricesActionPerformed
        customerContainer.removeAll();
        DentalManagerPriceJPanel jpanel = new DentalManagerPriceJPanel(userProcessContainer, account, organization, enterprise);
        customerContainer.add(jpanel);
        CardLayout layout = (CardLayout) customerContainer.getLayout();
        layout.next(customerContainer);
    }//GEN-LAST:event_btnPricesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrices;
    private javax.swing.JButton buttonPersonalInfo;
    private javax.swing.JPanel customerContainer;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
}
