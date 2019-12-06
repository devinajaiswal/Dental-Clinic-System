/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.WorkQueue.InquiryWorkRequest;
import Business.WorkQueue.Message;
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
public class InquiryWorkRequestDAO {

    private static int generateId() {
        try {
            String sql = "SELECT max(message_id) from Message";
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

    public static int createMessage(int workRequestId, Message message) {
        int id = -1;
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO Message VALUES (?, ?, ?, ?, ?, ? )";
            PreparedStatement stmt = conn.prepareStatement(sql);
            id = generateId();
            stmt.setInt(1, id);
            stmt.setInt(2, workRequestId);
            stmt.setString(3, message.getFromUsername());
            stmt.setString(4, message.getToUsername());
            stmt.setString(5, message.getMessage());
            stmt.setTimestamp(6, Timestamp.valueOf(message.getSentTime()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public static ArrayList<Message> searchMessagesById(int workRequestId) {
        ArrayList<Message> result = new ArrayList<>();
        try {
            String sql = "SELECT * from Message where request_id = ? order by sent_time asc";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, workRequestId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setMessage(rs.getString("message"));
                message.setFromUsername(rs.getString("from_username"));
                message.setToUsername(rs.getString("to_username"));
                message.setSentTime(rs.getTimestamp("sent_time").toLocalDateTime());
                result.add(message);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ArrayList<InquiryWorkRequest> searchByToUser(String toUsername) {
        ArrayList<InquiryWorkRequest> result = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM WorkRequest WHERE receiver_username = ? And \n"
                + "exists (SELECT * FROM Message where Message.request_id = WorkRequest.request_id)";
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
            String sql = "SELECT * FROM WorkRequest WHERE sender_username = ? And \n"
                + "exists (SELECT * FROM Message where Message.request_id = WorkRequest.request_id)";
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
            String sql = "SELECT * FROM WorkRequest WHERE receiver_org_id = ? and receiver_username is null And \n"
                + "exists (SELECT * FROM Message where Message.request_id = WorkRequest.request_id);";
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
}
