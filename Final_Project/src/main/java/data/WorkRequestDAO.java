/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.Customer.CustomerPersonalInfo;
import Business.Network.Network;
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
            stmt.setString(3, workRequest.getSender().getUsername());
            stmt.setString(4, workRequest.getReceiver() == null ? null : workRequest.getReceiver().getUsername());
            stmt.setInt(5, workRequest.getReceiverOrganization().getOrganizationID());
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

}