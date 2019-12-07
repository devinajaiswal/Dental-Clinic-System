/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.CustomerRole;

import userinterface.DentalFrontdeskRole.*;
import userinterface.ConversationJPanel;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.InquiryWorkRequest;
import Business.WorkQueue.Message;
import Business.WorkQueue.WorkRequest;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import userinterface.DetailJFrame;

/**
 *
 * @author raunak
 */
public class CustomerInquiriesJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount account;
    private Organization organization;
    private Enterprise enterprise;

    /**
     * Creates new form ManageEnterpriseJPanel
     */
    public CustomerInquiriesJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.organization = organization;
        this.enterprise = enterprise;

        populate();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) frameReply.getUI();
        Container north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
        frameReply.setVisible(false);
    }

    private void populate() {
        populateInquiryTable(data.InquiryWorkRequestDAO.searchByUserAndStatus(account.getUsername(), false));
        populateHistoryTable(data.InquiryWorkRequestDAO.searchByUserAndStatus(account.getUsername(), true));

        tableActive.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                InquiryWorkRequest request = (InquiryWorkRequest) table.getModel().getValueAt(row, 2);
                if (request.getReceiverUsername() != null && request.getReceiverUsername().equals(account.getUsername())) {
                    setBackground(Color.PINK);
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
                return this;
            }
        });
    }

    private void populateInquiryTable(ArrayList<InquiryWorkRequest> list) {
        DefaultTableModel model = (DefaultTableModel) tableActive.getModel();
        model.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        for (WorkRequest request : list) {
            Object[] row = new Object[3];
            String from = request.getSenderUsername().equals(account.getUsername())
                ? request.getReceiverUsername() : request.getSenderUsername();
            row[0] = from;
            row[1] = formatter.format(request.getRequestTime());
            row[2] = request;
            model.addRow(row);
        }
    }

    private void populateHistoryTable(ArrayList<InquiryWorkRequest> list) {
        DefaultTableModel model = (DefaultTableModel) tableHistory.getModel();
        model.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        for (WorkRequest request : list) {
            Object[] row = new Object[3];
            String from = request.getSenderUsername().equals(account.getUsername())
                ? request.getReceiverUsername() : request.getSenderUsername();
            row[0] = from;
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

        jLabel4 = new javax.swing.JLabel();
        frameReply = new javax.swing.JInternalFrame();
        labAvailable = new javax.swing.JLabel();
        labUnavailable = new javax.swing.JLabel();
        buttonConfirm = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtReply = new javax.swing.JTextArea();
        buttonReply = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableActive = new javax.swing.JTable();
        buttonHistory = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableHistory = new javax.swing.JTable();
        buttonHistoryHistory = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonFinish = new javax.swing.JButton();

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel4.setText("Inquries");

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        jLabel6.setText("Keyword");

        tableActive.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tableActive);
        if (tableActive.getColumnModel().getColumnCount() > 0) {
            tableActive.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableActive.getColumnModel().getColumn(1).setPreferredWidth(20);
            tableActive.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        buttonHistory.setText("View Conversation History");
        buttonHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHistoryActionPerformed(evt);
            }
        });

        tableHistory.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tableHistory);
        if (tableHistory.getColumnModel().getColumnCount() > 0) {
            tableHistory.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableHistory.getColumnModel().getColumn(1).setPreferredWidth(20);
            tableHistory.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        buttonHistoryHistory.setText("View Conversation History");
        buttonHistoryHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHistoryHistoryActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel1.setText("Active Inquries");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("History Inquries");

        buttonFinish.setText("Mark As Finished");
        buttonFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFinishActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonReply)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonHistory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonFinish)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonHistoryHistory)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonReply, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frameReply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonHistoryHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resetFrameReply() {
        frameReply.setVisible(false);
        tableActive.setEnabled(true);
        txtReply.setText("");
    }

    private void setButtonsEnabled(boolean enable) {
        buttonReply.setEnabled(enable);
        buttonHistory.setEnabled(enable);
    }

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        if (!userinterface.Util.requireNotEmpty(this, txtReply)) {
            return;
        }
        String messageText = txtReply.getText();
        InquiryWorkRequest request = (InquiryWorkRequest) tableActive.getValueAt(tableActive.getSelectedRow(), 2);
        request.setMessage(messageText);
        request.setRequestTime(LocalDateTime.now());
        if (!request.getSenderUsername().equals(account.getUsername())) {
            request.setReceiverUsername(request.getSenderUsername());
            request.setSenderUsername(account.getUsername());
        }
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
        populate();
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        frameReply.setVisible(false);
        resetFrameReply();
        setButtonsEnabled(true);
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonReplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReplyActionPerformed
        if (tableActive.getSelectedRow() >= 0) {
            frameReply.setVisible(true);
            setButtonsEnabled(false);
            tableActive.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }
    }//GEN-LAST:event_buttonReplyActionPerformed

    private void buttonHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHistoryActionPerformed
        if (tableActive.getSelectedRow() >= 0) {
            InquiryWorkRequest request = (InquiryWorkRequest) tableActive.getValueAt(tableActive.getSelectedRow(), 2);
            DetailJFrame customerJFrame = new DetailJFrame();
            customerJFrame.setSize(600, 400);
            customerJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            customerJFrame.setLocationRelativeTo(this);
            customerJFrame.setContentPane(new ConversationJPanel(account, request));
            customerJFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }

    }//GEN-LAST:event_buttonHistoryActionPerformed

    private void buttonHistoryHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHistoryHistoryActionPerformed
        if (tableHistory.getSelectedRow() >= 0) {
            InquiryWorkRequest request = (InquiryWorkRequest) tableHistory.getValueAt(tableHistory.getSelectedRow(), 2);
            DetailJFrame customerJFrame = new DetailJFrame();
            customerJFrame.setSize(600, 400);
            customerJFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            customerJFrame.setLocationRelativeTo(this);
            customerJFrame.setContentPane(new ConversationJPanel(account, request));
            customerJFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }
    }//GEN-LAST:event_buttonHistoryHistoryActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        userinterface.Util.setBorderBlack(txtSearch);
        if (!userinterface.Util.requireNotEmpty(this, txtSearch)) {
            return;
        }
        String keyword = txtSearch.getText();
        ArrayList<InquiryWorkRequest> list = data.InquiryWorkRequestDAO.searchByUserAndStatus(account.getUsername(), false);
        ArrayList<InquiryWorkRequest> result = new ArrayList<>();
        for (InquiryWorkRequest request : list) {
            if (request.getMessage().contains(keyword) || request.getSenderUsername().contains(keyword)
                || request.getReceiverUsername().contains(keyword)) {
                result.add(request);
            }
        }
        populateInquiryTable(result);

        list = data.InquiryWorkRequestDAO.searchByUserAndStatus(account.getUsername(), true);
        result = new ArrayList<>();
        for (InquiryWorkRequest request : list) {
            if (request.getMessage().contains(keyword) || request.getSenderUsername().contains(keyword)
                || request.getReceiverUsername().contains(keyword)) {
                result.add(request);
            }
        }
        populateHistoryTable(result);
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void buttonFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFinishActionPerformed
        if (tableActive.getSelectedRow() >= 0) {
            InquiryWorkRequest request = (InquiryWorkRequest) tableActive.getValueAt(tableActive.getSelectedRow(), 2);
            request.setFinishTime(LocalDateTime.now());
            data.WorkRequestDAO.update(request);
            populate();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }
    }//GEN-LAST:event_buttonFinishActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.JButton buttonFinish;
    private javax.swing.JButton buttonHistory;
    private javax.swing.JButton buttonHistoryHistory;
    private javax.swing.JButton buttonReply;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JInternalFrame frameReply;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labAvailable;
    private javax.swing.JLabel labUnavailable;
    private javax.swing.JTable tableActive;
    private javax.swing.JTable tableHistory;
    private javax.swing.JTextArea txtReply;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
