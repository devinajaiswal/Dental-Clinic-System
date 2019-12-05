/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import Business.UserAccount.UserAccount;
import Business.WorkQueue.Message;
import Business.WorkQueue.WorkRequest;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zhangchuanqi
 */
public class ConversationJPanel extends javax.swing.JPanel {

    private UserAccount account;
    private WorkRequest request;

    /**
     * Creates new form ConversationJPanel
     */
    public ConversationJPanel(UserAccount account, WorkRequest request) {
        initComponents();
        this.account = account;
        this.request = request;
        populateTable();
    }

    public void populateTable() {
        DefaultTableModel model = (DefaultTableModel) tableConversation.getModel();
        tableConversation.getColumnModel().getColumn(1).setHeaderValue(account.getUsername());
        tableConversation.getColumnModel().getColumn(2).setHeaderValue(
            request.getSenderUsername().equals(account.getUsername())
            ? request.getReceiverUsername() : request.getSenderUsername());
        for (Message message : data.InquiryWorkRequestDAO.searchMessagesById(request.getRequestId())) {
            Object[] row = new Object[3];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            row[0] = formatter.format(message.getSentTime());
            if (message.getFromUsername().equals(account.getUsername())) {
                row[1] = message.getMessage();
            } else {
                row[2] = message.getMessage();
            }
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
        tableConversation = new javax.swing.JTable();

        tableConversation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(tableConversation);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableConversation;
    // End of variables declaration//GEN-END:variables
}