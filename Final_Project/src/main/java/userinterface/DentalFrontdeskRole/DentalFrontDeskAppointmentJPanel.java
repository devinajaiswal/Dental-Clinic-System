/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.DentalFrontdeskRole;

import Business.Customer.CustomerPersonalInfo;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.AppointmentWorkRequest;
import Business.WorkQueue.InquiryWorkRequest;
import Business.WorkQueue.TreatmentWorkRequest;
import data.AppointmentWorkRequestDAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import userinterface.CustomerRole.CustomerMedicalInfoJPanel;

/**
 *
 * @author raunak
 */
public class DentalFrontDeskAppointmentJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private UserAccount account;
    private Organization organization;
    private Enterprise enterprise;

    /**
     * Creates new form ManageEnterpriseJPanel
     */
    public DentalFrontDeskAppointmentJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.organization = organization;
        this.enterprise = enterprise;

        populate();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) frameCancel.getUI();
        Container north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
        frameCancel.setVisible(false);

        ui = (BasicInternalFrameUI) frameReschedule.getUI();
        north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
        frameReschedule.setVisible(false);

        ui = (BasicInternalFrameUI) frameCheckin.getUI();
        north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
        frameCheckin.setVisible(false);

        comboDentist.removeAll();
        ArrayList<String> dentists = data.UserDAO.searchUsernamesByEnterpriseIdAndRole(
            enterprise.getEnterpriseId(), Role.RoleType.DentalDentist.getValue());
        for (String username : dentists) {
            comboDentist.addItem(username);
        }

        comboStatus.removeAll();
        comboStatus.addItem("");
        comboStatus.addItem(AppointmentWorkRequest.Status.NEED_CONFIRMATION.getValue());
        comboStatus.addItem(AppointmentWorkRequest.Status.CONFIRMED.getValue());
        comboStatus.addItem(AppointmentWorkRequest.Status.CANCELLED.getValue());
        comboStatus.addItem(AppointmentWorkRequest.Status.CHECKED_IN.getValue());

    }

    public void populate() {
        populateAppointmentTable(data.AppointmentWorkRequestDAO.searchByOrgId(organization.getOrganizationID()));

        tableAppointment.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                AppointmentWorkRequest request = (AppointmentWorkRequest) table.getModel().getValueAt(row, 3);
                if (request.getStatus().equals(AppointmentWorkRequest.Status.NEED_CONFIRMATION.getValue())) {
                    setBackground(Color.PINK);
                } else if (request.getStatus().equals(AppointmentWorkRequest.Status.CONFIRMED.getValue())) {
                    setBackground(new Color(152, 251, 152));
                } else if (request.getStatus().equals(AppointmentWorkRequest.Status.CANCELLED.getValue())) {
                    setBackground(new Color(205, 133, 63));
                } else if (request.getStatus().equals(AppointmentWorkRequest.Status.CHECKED_IN.getValue())) {
                    setBackground(new Color(192, 192, 192));
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
                return this;
            }
        });
    }

    private void populateAppointmentTable(ArrayList<AppointmentWorkRequest> list) {
        DefaultTableModel model = (DefaultTableModel) tableAppointment.getModel();
        model.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        for (AppointmentWorkRequest request : list) {
            Object[] row = new Object[4];
            row[0] = request.getSenderUsername();
            row[1] = formatter.format(request.getAppointmentTime());
            row[2] = request.getStatus();
            row[3] = request;
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
        frameCancel = new javax.swing.JInternalFrame();
        labAvailable = new javax.swing.JLabel();
        labUnavailable = new javax.swing.JLabel();
        buttonCancelConfirm = new javax.swing.JButton();
        buttonCancelCancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCancelReason = new javax.swing.JTextArea();
        buttonCancelAppointment = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAppointment = new javax.swing.JTable();
        buttonReschedule = new javax.swing.JButton();
        buttonCheckin = new javax.swing.JButton();
        buttonUpComing = new javax.swing.JButton();
        frameReschedule = new javax.swing.JInternalFrame();
        labAvailable1 = new javax.swing.JLabel();
        labUnavailable1 = new javax.swing.JLabel();
        buttonRescheduleConfirm = new javax.swing.JButton();
        buttonRescheduleCancel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboTime = new javax.swing.JComboBox();
        dateAppointment = new com.toedter.calendar.JDateChooser();
        buttonConfirmAppointment = new javax.swing.JButton();
        comboStatus = new javax.swing.JComboBox<>();
        frameCheckin = new javax.swing.JInternalFrame();
        labAvailable2 = new javax.swing.JLabel();
        labUnavailable2 = new javax.swing.JLabel();
        buttonCheckinConfirm = new javax.swing.JButton();
        buttonCheckinCancel = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        comboDentist = new javax.swing.JComboBox();

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel4.setText("Patients Appointments");

        frameCancel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        frameCancel.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        frameCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        frameCancel.setEnabled(false);
        frameCancel.setVisible(true);

        buttonCancelConfirm.setText("Confirm");
        buttonCancelConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelConfirmActionPerformed(evt);
            }
        });

        buttonCancelCancel.setText("Cancel");
        buttonCancelCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelCancelActionPerformed(evt);
            }
        });

        txtCancelReason.setColumns(20);
        txtCancelReason.setRows(5);
        jScrollPane2.setViewportView(txtCancelReason);

        javax.swing.GroupLayout frameCancelLayout = new javax.swing.GroupLayout(frameCancel.getContentPane());
        frameCancel.getContentPane().setLayout(frameCancelLayout);
        frameCancelLayout.setHorizontalGroup(
            frameCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameCancelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameCancelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(labUnavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameCancelLayout.createSequentialGroup()
                        .addComponent(buttonCancelConfirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCancelCancel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        frameCancelLayout.setVerticalGroup(
            frameCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameCancelLayout.createSequentialGroup()
                .addGroup(frameCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labUnavailable, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameCancelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancelConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancelCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        buttonCancelAppointment.setText("Cancel The Appointment");
        buttonCancelAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelAppointmentActionPerformed(evt);
            }
        });

        buttonSearch.setText("Search");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        jLabel6.setText("Keyword");

        tableAppointment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient Username", "Appointment Time", "Status", "Message"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableAppointment);
        if (tableAppointment.getColumnModel().getColumnCount() > 0) {
            tableAppointment.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableAppointment.getColumnModel().getColumn(1).setPreferredWidth(20);
            tableAppointment.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        buttonReschedule.setText("Reschedule");
        buttonReschedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRescheduleActionPerformed(evt);
            }
        });

        buttonCheckin.setText("Check In");
        buttonCheckin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCheckinActionPerformed(evt);
            }
        });

        buttonUpComing.setText("Coming Today");
        buttonUpComing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpComingActionPerformed(evt);
            }
        });

        frameReschedule.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        frameReschedule.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        frameReschedule.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        frameReschedule.setEnabled(false);
        frameReschedule.setVisible(true);

        buttonRescheduleConfirm.setText("Confirm");
        buttonRescheduleConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRescheduleConfirmActionPerformed(evt);
            }
        });

        buttonRescheduleCancel.setText("Cancel");
        buttonRescheduleCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRescheduleCancelActionPerformed(evt);
            }
        });

        jLabel2.setText("Date");

        jLabel10.setText("Time");

        comboTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" }));

        dateAppointment.setDateFormatString("MM/dd/yyyy");

        javax.swing.GroupLayout frameRescheduleLayout = new javax.swing.GroupLayout(frameReschedule.getContentPane());
        frameReschedule.getContentPane().setLayout(frameRescheduleLayout);
        frameRescheduleLayout.setHorizontalGroup(
            frameRescheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRescheduleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameRescheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameRescheduleLayout.createSequentialGroup()
                        .addGap(496, 496, 496)
                        .addComponent(labAvailable1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(labUnavailable1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameRescheduleLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(frameRescheduleLayout.createSequentialGroup()
                .addComponent(buttonRescheduleConfirm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRescheduleCancel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        frameRescheduleLayout.setVerticalGroup(
            frameRescheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRescheduleLayout.createSequentialGroup()
                .addGroup(frameRescheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labAvailable1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labUnavailable1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(frameRescheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateAppointment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameRescheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel10)
                        .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameRescheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRescheduleConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRescheduleCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        buttonConfirmAppointment.setText("Confirm The Appointment");
        buttonConfirmAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmAppointmentActionPerformed(evt);
            }
        });

        comboStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboStatusActionPerformed(evt);
            }
        });

        frameCheckin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        frameCheckin.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        frameCheckin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        frameCheckin.setEnabled(false);
        frameCheckin.setVisible(true);

        buttonCheckinConfirm.setText("Confirm");
        buttonCheckinConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCheckinConfirmActionPerformed(evt);
            }
        });

        buttonCheckinCancel.setText("Cancel");
        buttonCheckinCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCheckinCancelActionPerformed(evt);
            }
        });

        jLabel11.setText("Dentist");

        javax.swing.GroupLayout frameCheckinLayout = new javax.swing.GroupLayout(frameCheckin.getContentPane());
        frameCheckin.getContentPane().setLayout(frameCheckinLayout);
        frameCheckinLayout.setHorizontalGroup(
            frameCheckinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameCheckinLayout.createSequentialGroup()
                .addGroup(frameCheckinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameCheckinLayout.createSequentialGroup()
                        .addGap(502, 502, 502)
                        .addComponent(labAvailable2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(labUnavailable2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameCheckinLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboDentist, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(frameCheckinLayout.createSequentialGroup()
                .addComponent(buttonCheckinConfirm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCheckinCancel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        frameCheckinLayout.setVerticalGroup(
            frameCheckinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameCheckinLayout.createSequentialGroup()
                .addGroup(frameCheckinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labAvailable2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labUnavailable2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(frameCheckinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(comboDentist, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameCheckinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCheckinConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCheckinCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(frameCancel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonUpComing))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonCancelAppointment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonReschedule)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonConfirmAppointment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCheckin)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(frameReschedule, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(frameCheckin))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancelAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonReschedule, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonConfirmAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frameCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frameReschedule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frameCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(buttonUpComing, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resetFrameReply() {
        frameCancel.setVisible(false);
        tableAppointment.setEnabled(true);
        txtCancelReason.setText("");
    }

    private void resetFrameAppointment() {
        frameReschedule.setVisible(false);
        tableAppointment.setEnabled(true);
        dateAppointment.setDate(null);
        comboTime.setSelectedIndex(0);
    }

    private void resetFrameCheckin() {
        frameCheckin.setVisible(false);
        tableAppointment.setEnabled(true);
        comboDentist.setSelectedItem(0);
    }

    private void setButtonsEnabled(boolean enable) {
        buttonCancelAppointment.setEnabled(enable);
        buttonReschedule.setEnabled(enable);
        buttonCheckin.setEnabled(enable);
        buttonConfirmAppointment.setEnabled(enable);
    }

    private void buttonCancelConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelConfirmActionPerformed
        if (!userinterface.Util.requireNotEmpty(this, txtCancelReason)) {
            return;
        }
        String messageText = txtCancelReason.getText();
        AppointmentWorkRequest request = (AppointmentWorkRequest) tableAppointment.getValueAt(tableAppointment.getSelectedRow(), 3);
        request.setMessage(messageText);
        request.setFinishTime(LocalDateTime.now());
        data.WorkRequestDAO.update(request);
        data.AppointmentWorkRequestDAO.updateAppointmentStatus(request.getAppointmentId(), AppointmentWorkRequest.Status.CANCELLED.getValue());

        JOptionPane.showMessageDialog(this, "Appointment cancelled!");

        CustomerPersonalInfo info = data.UserDAO.searchPersonalInfo(request.getSenderUsername());
        String message = "Your appointment with "
            + enterprise.getEnterpriseName() + " at " + request.getAppointmentTime() + " is cancelled!";
        userinterface.Util.sendSMS(info.getPhone(), message);
        try {
            userinterface.Util.sendEmail(info.getEmail(), "Appointment cancelled", message);
        } catch (MessagingException ex) {
            Logger.getLogger(DentalFrontDeskAppointmentJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        resetFrameReply();
        setButtonsEnabled(true);
        populate();
    }//GEN-LAST:event_buttonCancelConfirmActionPerformed

    private void buttonCancelCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelCancelActionPerformed
        frameCancel.setVisible(false);
        resetFrameReply();
        setButtonsEnabled(true);
    }//GEN-LAST:event_buttonCancelCancelActionPerformed

    private void buttonCancelAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelAppointmentActionPerformed
        if (tableAppointment.getSelectedRow() >= 0) {
            AppointmentWorkRequest request = (AppointmentWorkRequest) tableAppointment.getValueAt(tableAppointment.getSelectedRow(), 3);
            if (request.getStatus().equals(AppointmentWorkRequest.Status.CANCELLED.getValue())
                || request.getStatus().equals(AppointmentWorkRequest.Status.CHECKED_IN.getValue())) {
                JOptionPane.showMessageDialog(this, "The appointment is already cancelled or checked in!");
                return;
            }
            frameCancel.setVisible(true);
            setButtonsEnabled(false);
            tableAppointment.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }
    }//GEN-LAST:event_buttonCancelAppointmentActionPerformed

    private void buttonRescheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRescheduleActionPerformed
        userinterface.Util.setBorderBlack(dateAppointment, comboTime);
        if (tableAppointment.getSelectedRow() >= 0) {
            AppointmentWorkRequest request = (AppointmentWorkRequest) tableAppointment.getValueAt(tableAppointment.getSelectedRow(), 3);
            if (request.getStatus().equals(AppointmentWorkRequest.Status.CANCELLED.getValue())
                || request.getStatus().equals(AppointmentWorkRequest.Status.CHECKED_IN.getValue())) {
                JOptionPane.showMessageDialog(this, "The appointment is already cancelled or checked in!");
                return;
            }
            frameReschedule.setVisible(true);
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
            String dateString = request.getAppointmentTime().format(formatter);
            try {
                Date date;
                date = df.parse(dateString);
                dateAppointment.setDate(date);
            } catch (ParseException ex) {
                Logger.getLogger(CustomerMedicalInfoJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            comboTime.setSelectedItem(request.getAppointmentTime().format(formatterTime));
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }

    }//GEN-LAST:event_buttonRescheduleActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        String keyword = txtSearch.getText();
        String status = (String) comboStatus.getSelectedItem();
        ArrayList<AppointmentWorkRequest> list = data.AppointmentWorkRequestDAO.
            searchByOrgId(organization.getOrganizationID());
        ArrayList<AppointmentWorkRequest> result = new ArrayList<>();
        ArrayList<AppointmentWorkRequest> temp = new ArrayList<>();

        for (AppointmentWorkRequest request : list) {
            if (request.getMessage().contains(keyword) || request.getSenderUsername().contains(keyword)
                || request.getStatus().contains(keyword)) {
                result.add(request);
                temp.add(request);
            }
        }

        System.out.println(status);
        if (status != null && !status.equals("")) {
            for (AppointmentWorkRequest request : temp) {
                System.out.println(request.getStatus());
                if (!request.getStatus().equals(status)) {
                    result.remove(request);
                }
            }
        }
        populateAppointmentTable(result);
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void buttonRescheduleConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRescheduleConfirmActionPerformed
        userinterface.Util.setBorderBlack(comboTime, dateAppointment);
        if (dateAppointment.getDate() == null || dateAppointment.getDate().compareTo(java.sql.Date.valueOf(LocalDate.now())) <= 0) {
            dateAppointment.setBorder(new LineBorder(Color.RED));
            JOptionPane.showMessageDialog(this, "We can only make appointment as early as tomorrow!");
            return;
        }
        if (!userinterface.Util.requireSeletedItemNotNull(this, comboTime)) {
            return;
        }

        AppointmentWorkRequest request = (AppointmentWorkRequest) tableAppointment.getValueAt(tableAppointment.getSelectedRow(), 3);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy ");
        String dateString = df.format(dateAppointment.getDate());
        String timeString = (String) comboTime.getSelectedItem();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        LocalDateTime appointmentTime = LocalDateTime.parse(dateString + timeString, formatter);
        request.setAppointmentTime(appointmentTime);
        request.setMessage("Appointment Rescheduled");
        data.WorkRequestDAO.update(request);
        data.AppointmentWorkRequestDAO.rescheduleAppointment(request.getAppointmentId(), appointmentTime);

        JOptionPane.showMessageDialog(this, "Appointment Rescheduled!");
        CustomerPersonalInfo info = data.UserDAO.searchPersonalInfo(request.getSenderUsername());
        String message = "Your appointment with "
            + enterprise.getEnterpriseName() + " at " + request.getAppointmentTime() + " is confirmed!";
        userinterface.Util.sendSMS(info.getPhone(), message);
        try {
            userinterface.Util.sendEmail(info.getEmail(), "Appointment confirmed", message);
        } catch (MessagingException ex) {
            Logger.getLogger(DentalFrontDeskAppointmentJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        resetFrameAppointment();
        setButtonsEnabled(true);
        populate();
    }//GEN-LAST:event_buttonRescheduleConfirmActionPerformed

    private void buttonRescheduleCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRescheduleCancelActionPerformed
        frameReschedule.setVisible(false);
        resetFrameAppointment();
        setButtonsEnabled(true);
    }//GEN-LAST:event_buttonRescheduleCancelActionPerformed

    private void buttonConfirmAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmAppointmentActionPerformed
        if (tableAppointment.getSelectedRow() >= 0) {
            AppointmentWorkRequest request = (AppointmentWorkRequest) tableAppointment.getValueAt(tableAppointment.getSelectedRow(), 3);
            if (!request.getStatus().equals(AppointmentWorkRequest.Status.NEED_CONFIRMATION.getValue())) {
                JOptionPane.showMessageDialog(this, "The appointment is already confirmed or cancelled or checked in!");
                return;
            }
            request.setMessage("Appointment Confirmed");
            AppointmentWorkRequestDAO.updateAppointmentStatus(request.getAppointmentId(),
                AppointmentWorkRequest.Status.CONFIRMED.getValue());
            data.WorkRequestDAO.update(request);
            JOptionPane.showMessageDialog(this, "Appointment Confirmed!");
            CustomerPersonalInfo info = data.UserDAO.searchPersonalInfo(request.getSenderUsername());
            String message = "Your appointment with "
                + enterprise.getEnterpriseName() + " at " + request.getAppointmentTime() + " is confirmed!";
            userinterface.Util.sendSMS(info.getPhone(), message);
            try {
                userinterface.Util.sendEmail(info.getEmail(), "Appointment confirmed", message);
            } catch (MessagingException ex) {
                Logger.getLogger(DentalFrontDeskAppointmentJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            resetFrameAppointment();
            populate();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }

    }//GEN-LAST:event_buttonConfirmAppointmentActionPerformed

    private void comboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStatusActionPerformed
    }//GEN-LAST:event_comboStatusActionPerformed

    private void buttonCheckinConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCheckinConfirmActionPerformed
        if (!userinterface.Util.requireSeletedItemNotNull(this, comboDentist)) {
            return;
        }
        String dentist = (String) comboDentist.getSelectedItem();
        if (dentist.equals("")) {
            JOptionPane.showMessageDialog(this, "You must choose a dentist!");
            return;
        }
        AppointmentWorkRequest request = (AppointmentWorkRequest) tableAppointment.getValueAt(tableAppointment.getSelectedRow(), 3);
        request.setMessage("Checked In");
        request.setFinishTime(LocalDateTime.now());
        AppointmentWorkRequestDAO.updateAppointmentStatus(request.getAppointmentId(),
            AppointmentWorkRequest.Status.CHECKED_IN.getValue());
        data.WorkRequestDAO.update(request);

        TreatmentWorkRequest treatRequest = new TreatmentWorkRequest();
        treatRequest.setPatientUsername(request.getSenderUsername());
        treatRequest.setSenderUsername(account.getUsername());
        treatRequest.setReceiverUsername(dentist);
        treatRequest.setMessage("Treatment request");
        treatRequest.setRequestTime(LocalDateTime.now());
        int requestId = data.WorkRequestDAO.create(treatRequest);
        data.TreatmentWorkRequestDAO.createTreatment(requestId, treatRequest);

        JOptionPane.showMessageDialog(this, "Checked In");
        resetFrameCheckin();
        setButtonsEnabled(true);
        populate();
    }//GEN-LAST:event_buttonCheckinConfirmActionPerformed

    private void buttonCheckinCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCheckinCancelActionPerformed
        resetFrameCheckin();
        setButtonsEnabled(true);
        tableAppointment.setEnabled(true);
    }//GEN-LAST:event_buttonCheckinCancelActionPerformed

    private void buttonCheckinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCheckinActionPerformed
        if (tableAppointment.getSelectedRow() >= 0) {
            AppointmentWorkRequest request = (AppointmentWorkRequest) tableAppointment.getValueAt(tableAppointment.getSelectedRow(), 3);
            if (!request.getStatus().equals(AppointmentWorkRequest.Status.CONFIRMED.getValue())) {
                JOptionPane.showMessageDialog(this, "Only confirmed appointment can be checked in!");
                return;
            }
            if (request.getAppointmentTime().toLocalDate().compareTo(LocalDate.now()) != 0) {
                JOptionPane.showMessageDialog(this, "Only today's appointment can be checked in!");
                return;
            }
            frameCheckin.setVisible(true);
            setButtonsEnabled(false);
            tableAppointment.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a record first");
            return;
        }
    }//GEN-LAST:event_buttonCheckinActionPerformed

    private void buttonUpComingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpComingActionPerformed
        ArrayList<AppointmentWorkRequest> list = data.AppointmentWorkRequestDAO.
            searchByOrgId(organization.getOrganizationID());
        ArrayList<AppointmentWorkRequest> result = new ArrayList<>();

        for (AppointmentWorkRequest request : list) {
            if (request.getAppointmentTime().toLocalDate().compareTo(LocalDate.now()) == 0) {
                result.add(request);
            }
        }
        populateAppointmentTable(result);
    }//GEN-LAST:event_buttonUpComingActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancelAppointment;
    private javax.swing.JButton buttonCancelCancel;
    private javax.swing.JButton buttonCancelConfirm;
    private javax.swing.JButton buttonCheckin;
    private javax.swing.JButton buttonCheckinCancel;
    private javax.swing.JButton buttonCheckinConfirm;
    private javax.swing.JButton buttonConfirmAppointment;
    private javax.swing.JButton buttonReschedule;
    private javax.swing.JButton buttonRescheduleCancel;
    private javax.swing.JButton buttonRescheduleConfirm;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton buttonUpComing;
    private javax.swing.JComboBox comboDentist;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JComboBox comboTime;
    private com.toedter.calendar.JDateChooser dateAppointment;
    private javax.swing.JInternalFrame frameCancel;
    private javax.swing.JInternalFrame frameCheckin;
    private javax.swing.JInternalFrame frameReschedule;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labAvailable;
    private javax.swing.JLabel labAvailable1;
    private javax.swing.JLabel labAvailable2;
    private javax.swing.JLabel labUnavailable;
    private javax.swing.JLabel labUnavailable1;
    private javax.swing.JLabel labUnavailable2;
    private javax.swing.JTable tableAppointment;
    private javax.swing.JTextArea txtCancelReason;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
