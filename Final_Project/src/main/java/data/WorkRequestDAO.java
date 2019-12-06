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

    public static ArrayList<InquiryWorkRequest> searchByToUser(String toUsername) {
        ArrayList<InquiryWorkRequest> result = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM WorkRequest WHERE receiver_username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, toUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                InquiryWorkRequest request = new InquiryWorkRequest();
                request.setMessage(rs.getString("message"));
                request.setRequestId(rs.getInt("request_id"));
                request.setSenderUsername(rs.getString("sender_username"));
                request.setReceiverUsername(rs.getString("receiver_username"));
                request.setReceiverOrganizationId(rs.getInt("receiver_org_id"));
                request.setStatus(rs.getString("request_status"));
                if (rs.getTimestamp("request_time") != null) {
                    request.setRequestTime(rs.getTimestamp("request_time").toLocalDateTime());
                }
                if (rs.getTimestamp("assign_time") != null) {
                    request.setAssignTime(rs.getTimestamp("assign_time").toLocalDateTime());
                }
                if (rs.getTimestamp("finish_time") != null) {
                    request.setFinishTime(rs.getTimestamp("finish_time").toLocalDateTime());
                }
                if (rs.getTimestamp("confirm_time") != null) {
                    request.setConfirmTime(rs.getTimestamp("confirm_time").toLocalDateTime());
                }
                result.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ArrayList<InquiryWorkRequest> searchByFromUser(String fromUsername) {
        ArrayList<InquiryWorkRequest> result = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM WorkRequest WHERE sender_username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fromUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                InquiryWorkRequest request = new InquiryWorkRequest();
                request.setMessage(rs.getString("message"));
                request.setRequestId(rs.getInt("request_id"));
                request.setSenderUsername(rs.getString("sender_username"));
                request.setReceiverUsername(rs.getString("receiver_username"));
                request.setReceiverOrganizationId(rs.getInt("receiver_org_id"));
                request.setStatus(rs.getString("request_status"));
                if (rs.getTimestamp("request_time") != null) {
                    request.setRequestTime(rs.getTimestamp("request_time").toLocalDateTime());
                }
                if (rs.getTimestamp("assign_time") != null) {
                    request.setAssignTime(rs.getTimestamp("assign_time").toLocalDateTime());
                }
                if (rs.getTimestamp("finish_time") != null) {
                    request.setFinishTime(rs.getTimestamp("finish_time").toLocalDateTime());
                }
                if (rs.getTimestamp("confirm_time") != null) {
                    request.setConfirmTime(rs.getTimestamp("confirm_time").toLocalDateTime());
                }
                result.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ArrayList<InquiryWorkRequest> searchByOrgId(int orgId) {
        ArrayList<InquiryWorkRequest> result = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM WorkRequest WHERE receiver_org_id = ? and receiver_username is null;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orgId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                InquiryWorkRequest request = new InquiryWorkRequest();
                request.setMessage(rs.getString("message"));
                request.setRequestId(rs.getInt("request_id"));
                request.setSenderUsername(rs.getString("sender_username"));
                request.setReceiverUsername(rs.getString("receiver_username"));
                request.setReceiverOrganizationId(rs.getInt("receiver_org_id"));
                request.setStatus(rs.getString("request_status"));
                if (rs.getTimestamp("request_time") != null) {
                    request.setRequestTime(rs.getTimestamp("request_time").toLocalDateTime());
                }
                if (rs.getTimestamp("assign_time") != null) {
                    request.setAssignTime(rs.getTimestamp("assign_time").toLocalDateTime());
                }
                if (rs.getTimestamp("finish_time") != null) {
                    request.setFinishTime(rs.getTimestamp("finish_time").toLocalDateTime());
                }
                if (rs.getTimestamp("confirm_time") != null) {
                    request.setConfirmTime(rs.getTimestamp("confirm_time").toLocalDateTime());
                }
                result.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void update(WorkRequest workRequest) {
        try {
            Connection conn = getConnection();
            String sql = "Update WorkRequest set message = ?, sender_username = ?,"
                + " receiver_username = ?, request_time = ? where request_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, workRequest.getMessage());
            stmt.setString(2, workRequest.getSenderUsername());
            stmt.setString(3, workRequest.getReceiverUsername());
            stmt.setTimestamp(4, Timestamp.valueOf(workRequest.getRequestTime()));
            stmt.setInt(5, workRequest.getRequestId() == -1 ? null : workRequest.getRequestId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
