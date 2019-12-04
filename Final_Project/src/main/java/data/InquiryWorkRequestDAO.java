/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.WorkQueue.InquiryWorkRequest;
import Business.WorkQueue.Message;
import Business.WorkQueue.WorkRequest;
import static data.Data.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zhangchuanqi
 */
public class InquiryWorkRequestDAO {

    public static void create(int workRequestId, Message message) {
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO Message VALUES (?, ?, ?, ?, ? )";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, workRequestId);
            stmt.setString(2, message.getFrom().getUsername());
            stmt.setString(3, message.getTo() == null ? null : message.getTo().getUsername());
            stmt.setString(4, message.getMessage());
            stmt.setTimestamp(5, Timestamp.valueOf(message.getSentTime()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        public static InquiryWorkRequest searchById(int workRequestId) {
        InquiryWorkRequest result = null;
        try {
            String sql = "SELECT * from WorkRequest where request_id = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, workRequestId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new InquiryWorkRequest();
                result.setRequestTime(rs.getTimestamp("request_time").toLocalDateTime());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
