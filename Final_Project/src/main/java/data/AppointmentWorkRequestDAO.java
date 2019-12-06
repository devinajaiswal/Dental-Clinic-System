/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.WorkQueue.AppointmentWorkRequest;
import Business.WorkQueue.InquiryWorkRequest;
import Business.WorkQueue.Message;
import static data.Data.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zhangchuanqi
 */
public class AppointmentWorkRequestDAO {

    private static int generateId() {
        try {
            String sql = "SELECT max(appointment_id) from Appointment";
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

    public static int createAppointment(int workRequestId, LocalDateTime time) {
        int id = -1;
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO Appointment VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            id = generateId();
            stmt.setInt(1, id);
            stmt.setInt(2, workRequestId);
            stmt.setTimestamp(3, Timestamp.valueOf(time));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

    }

    
    public static int updateAppointment(int appointmentId, LocalDateTime time) {
        int id = -1;
        try {
            Connection conn = getConnection();
            String sql = "Update Appointment set appointment_time = ? where appointment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(time));
            stmt.setInt(2, appointmentId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

    }

    

    public static ArrayList<AppointmentWorkRequest> searchByOrgId(int orgId) {
        ArrayList<AppointmentWorkRequest> result = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM WorkRequest WHERE receiver_org_id = ? and receiver_username is null And \n"
                + "exists (SELECT * FROM Appointment where Appointment.request_id = WorkRequest.request_id)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orgId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AppointmentWorkRequest request = new AppointmentWorkRequest();
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

                sql = "SELECT * FROM Appointment WHERE request_id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, request.getRequestId());
                ResultSet rs2 = stmt.executeQuery();
                rs2.next();
                request.setAppointmentId(rs2.getInt("appointment_id"));
                request.setAppointmentTime(rs2.getTimestamp("appointment_time").toLocalDateTime());
                result.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
