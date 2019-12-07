/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.WorkQueue.TreatmentWorkRequest;
import static data.Data.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zhangchuanqi
 */
public class TreatmentWorkRequestDAO {

    private static int generateId() {
        try {
            String sql = "SELECT max(treatment_id) from Treatment";
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

    public static int createTreatment(int workRequestId, TreatmentWorkRequest treatment) {
        int id = -1;
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO Treatment VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            id = generateId();
            stmt.setInt(1, id);
            stmt.setInt(2, workRequestId);
            stmt.setString(3, treatment.getPatientUsername());
            stmt.setInt(4, treatment.getHygieneScore());
            stmt.setString(5, treatment.getType());
            stmt.setString(6, treatment.getNote());
            stmt.executeUpdate();
             Logger.getLogger(Data.class.getName()).info("treatment created, id = " + id); 
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

    }

    public static void updateTreatment(TreatmentWorkRequest request) {
        try {
            Connection conn = getConnection();
            String sql = "Update Treatment set hygiene_score = ?, type = ?, note = ? where treatment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, request.getHygieneScore());
            stmt.setString(2, request.getType());
            stmt.setString(3, request.getNote());
            stmt.setInt(4, request.getTreatmentId());
            stmt.executeUpdate();
             Logger.getLogger(Data.class.getName()).info("treatment updated, id = " + request.getTreatmentId()); 
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<TreatmentWorkRequest> searchByUsernameAndStatus(String username, boolean finished) {
        ArrayList<TreatmentWorkRequest> result = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql;
            if (!finished) {
                sql = "SELECT * FROM WorkRequest WHERE receiver_username = ? And \n"
                    + "exists (SELECT * FROM Treatment where Treatment.request_id = WorkRequest.request_id)"
                    + " And finish_time is null";
            } else {
                sql = "SELECT * FROM WorkRequest WHERE receiver_username = ? And \n"
                    + "exists (SELECT * FROM Treatment where Treatment.request_id = WorkRequest.request_id)"
                    + " And finish_time is not null";
            }
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TreatmentWorkRequest request = new TreatmentWorkRequest();
                request.setMessage(rs.getString("message"));
                request.setRequestId(rs.getInt("request_id"));
                request.setSenderUsername(rs.getString("sender_username"));
                request.setReceiverUsername(rs.getString("receiver_username"));
                request.setReceiverOrganizationId(rs.getInt("receiver_org_id"));
                if (rs.getTimestamp("request_time") != null) {
                    request.setRequestTime(rs.getTimestamp("request_time").toLocalDateTime());
                }
                if (rs.getTimestamp("finish_time") != null) {
                    request.setFinishTime(rs.getTimestamp("finish_time").toLocalDateTime());
                }

                sql = "SELECT * FROM Treatment WHERE request_id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, request.getRequestId());
                ResultSet rs2 = stmt.executeQuery();
                rs2.next();
                request.setTreatmentId(rs2.getInt("treatment_id"));
                request.setPatientUsername(rs2.getString("patient_username"));
                request.setHygieneScore(rs2.getInt("hygiene_score"));
                request.setType(rs2.getString("type"));
                request.setNote(rs2.getString("note"));
                result.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ArrayList<String> getAllTreatmentType() {
        ArrayList<String> result = new ArrayList<>();
        try {
            String sql = "SELECT * from TreatmentType";
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString("treatment_type"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ArrayList<TreatmentWorkRequest> searchByPatient(String patientUsername) {
        ArrayList<TreatmentWorkRequest> result = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM Treatment natural join WorkRequest where patient_username = ?"
                + " and finish_time is not null order by finish_time desc";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientUsername);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TreatmentWorkRequest request = new TreatmentWorkRequest();
                request.setMessage(rs.getString("message"));
                request.setRequestId(rs.getInt("request_id"));
                request.setSenderUsername(rs.getString("sender_username"));
                request.setReceiverUsername(rs.getString("receiver_username"));
                request.setReceiverOrganizationId(rs.getInt("receiver_org_id"));
                if (rs.getTimestamp("request_time") != null) {
                    request.setRequestTime(rs.getTimestamp("request_time").toLocalDateTime());
                }
                if (rs.getTimestamp("finish_time") != null) {
                    request.setFinishTime(rs.getTimestamp("finish_time").toLocalDateTime());
                }
                request.setTreatmentId(rs.getInt("treatment_id"));
                request.setPatientUsername(rs.getString("patient_username"));
                request.setHygieneScore(rs.getInt("hygiene_score"));
                request.setType(rs.getString("type"));
                request.setNote(rs.getString("note"));
                result.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
