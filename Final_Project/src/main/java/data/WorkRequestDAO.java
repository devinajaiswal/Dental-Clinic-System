/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.Customer.CustomerPersonalInfo;
import Business.Network.Network;
import Business.WorkQueue.InquiryWorkRequest;
import Business.WorkQueue.WorkRequest;
import static data.Data.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zhangchuanqi
 */
public class WorkRequestDAO {

    private static int generateId() {
        try {
            String sql = "SELECT max(request_id) from WorkRequest";
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int max = rs.getInt(1);
            return max + 1;
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public static int create(WorkRequest workRequest) {
        int id = -1;
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO WorkRequest VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            id = generateId();
            stmt.setInt(1, id);
            stmt.setString(2, workRequest.getMessage());
            stmt.setString(3, workRequest.getSenderUsername());
            stmt.setString(4, workRequest.getReceiverUsername());
            stmt.setInt(5, workRequest.getReceiverOrganizationId());
            stmt.setString(6, WorkRequest.Status.SENT.getValue());
            stmt.setTimestamp(7, Timestamp.valueOf(workRequest.getRequestTime()));
            stmt.setString(8, null);
            stmt.setString(9, null);
            stmt.setString(10, null);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public static void update(WorkRequest workRequest) {
        try {
            Connection conn = getConnection();
            String sql = "Update WorkRequest set message = ?, sender_username = ?,"
                + " receiver_username = ?, receiver_org_id = ?, request_status = ?, "
                + " request_time = ?, assign_time = ?, finish_time = ?, confirm_time = ?"
                + " where request_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, workRequest.getMessage());
            stmt.setString(2, workRequest.getSenderUsername());
            stmt.setString(3, workRequest.getReceiverUsername());
            stmt.setInt(4, workRequest.getReceiverOrganizationId());
            stmt.setString(5, workRequest.getStatus());
            if (workRequest.getRequestTime() != null) {
                stmt.setTimestamp(6, Timestamp.valueOf(workRequest.getRequestTime()));
            } else {
                stmt.setTimestamp(6, null);
            }
            if (workRequest.getAssignTime() != null) {
                stmt.setTimestamp(7, Timestamp.valueOf(workRequest.getAssignTime()));
            } else {
                stmt.setTimestamp(7, null);
            }
            if (workRequest.getFinishTime() != null) {
                stmt.setTimestamp(8, Timestamp.valueOf(workRequest.getFinishTime()));
            } else {
                stmt.setTimestamp(8, null);
            }
            if (workRequest.getConfirmTime() != null) {
                stmt.setTimestamp(9, Timestamp.valueOf(workRequest.getConfirmTime()));
            } else {
                stmt.setTimestamp(9, null);
            }
            stmt.setInt(10, workRequest.getRequestId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
