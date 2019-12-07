/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.Enterprise.InsurancePlan;
import Business.WorkQueue.AppointmentWorkRequest;
import Business.WorkQueue.PolicyWorkRequest;
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
public class PolicyDAO {

    private static int generateId() {
        try {
            String sql = "SELECT max(policy_id) from Policy";
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

    public static int create(int requestId, PolicyWorkRequest policy) {
        int id = -1;
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO Policy VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            id = generateId();
            stmt.setInt(1, id);
            stmt.setInt(2, requestId);
            stmt.setInt(3, policy.getPlanId());
            stmt.setString(4, policy.getUsername());
            stmt.setTimestamp(5, Timestamp.valueOf(policy.getStartDate()));
            stmt.setDouble(6, policy.getPremium());
            stmt.setString(7, PolicyWorkRequest.Status.APPLIED.getValue());
            stmt.executeUpdate();
            Logger.getLogger(Data.class.getName()).info("policy request created, id = " + id); 
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

    }

    public static void updateStatusAndPremium(PolicyWorkRequest policy) {
        try {
            Connection conn = getConnection();
            String sql = "Update Policy set status = ?, premium = ? where policy_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, policy.getStatus());
            stmt.setDouble(2, policy.getPremium());
            stmt.setInt(3, policy.getPolicyId());
            stmt.executeUpdate();
            Logger.getLogger(Data.class.getName()).info("policy request updated, id = " + policy.getPolicyId()); 
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean isUserCoveredAtDate(LocalDateTime date) {
        try {
            String sql = "select * from Policy where status = 'APPROVED' and \n"
                + "timestampdiff(day, ADDDATE(start_date, INTERVAL 365 day), ?) < 0";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static PolicyWorkRequest findActivePolicyByUser(String username) {
        try {
            String sql = "select * from Policy where username = ? and status = 'APPROVED' and \n"
                + "timestampdiff(day, ADDDATE(start_date, INTERVAL 365 day), ?) < 0";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                PolicyWorkRequest policy = new PolicyWorkRequest();
                policy.setPolicyId(rs.getInt("policy_id"));
                policy.setRequestId(rs.getInt("request_id"));
                policy.setPlanId(rs.getInt("plan_id"));
                policy.setUsername(rs.getString("username"));
                policy.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
                policy.setPremium(rs.getDouble("premium"));
                policy.setStatus(rs.getString("status"));
                return policy;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<PolicyWorkRequest> findPolicyByOrgAndStatus(int orgId, String status) {
        ArrayList<PolicyWorkRequest> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM WorkRequest natural join Policy\n"
                + "where receiver_org_id = ? and status = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orgId);
            stmt.setString(2, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PolicyWorkRequest policy = new PolicyWorkRequest();
                policy.setPolicyId(rs.getInt("policy_id"));
                policy.setRequestId(rs.getInt("request_id"));
                policy.setPlanId(rs.getInt("plan_id"));
                policy.setUsername(rs.getString("username"));
                policy.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
                policy.setPremium(rs.getDouble("premium"));
                policy.setStatus(rs.getString("status"));
                policy.setMessage(rs.getString("message"));
                policy.setSenderUsername(rs.getString("sender_username"));
                policy.setReceiverUsername(rs.getString("receiver_username"));
                policy.setReceiverOrganizationId(rs.getInt("receiver_org_id"));
                if (rs.getTimestamp("request_time") != null) {
                    policy.setRequestTime(rs.getTimestamp("request_time").toLocalDateTime());
                }
                if (rs.getTimestamp("finish_time") != null) {
                    policy.setFinishTime(rs.getTimestamp("finish_time").toLocalDateTime());
                }
                result.add(policy);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ArrayList<PolicyWorkRequest> findPolicyByOrg(int orgId) {
        ArrayList<PolicyWorkRequest> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM WorkRequest natural join Policy\n"
                + "where receiver_org_id = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orgId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PolicyWorkRequest policy = new PolicyWorkRequest();
                policy.setPolicyId(rs.getInt("policy_id"));
                policy.setRequestId(rs.getInt("request_id"));
                policy.setPlanId(rs.getInt("plan_id"));
                policy.setUsername(rs.getString("username"));
                policy.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
                policy.setPremium(rs.getDouble("premium"));
                policy.setStatus(rs.getString("status"));
                policy.setMessage(rs.getString("message"));
                policy.setSenderUsername(rs.getString("sender_username"));
                policy.setReceiverUsername(rs.getString("receiver_username"));
                policy.setReceiverOrganizationId(rs.getInt("receiver_org_id"));
                if (rs.getTimestamp("request_time") != null) {
                    policy.setRequestTime(rs.getTimestamp("request_time").toLocalDateTime());
                }
                if (rs.getTimestamp("finish_time") != null) {
                    policy.setFinishTime(rs.getTimestamp("finish_time").toLocalDateTime());
                }
                result.add(policy);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ArrayList<PolicyWorkRequest> findPolicyByUser(String username) {
        ArrayList<PolicyWorkRequest> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM WorkRequest natural join Policy\n"
                + "where username = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PolicyWorkRequest policy = new PolicyWorkRequest();
                policy.setPolicyId(rs.getInt("policy_id"));
                policy.setRequestId(rs.getInt("request_id"));
                policy.setPlanId(rs.getInt("plan_id"));
                policy.setUsername(rs.getString("username"));
                policy.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
                policy.setPremium(rs.getDouble("premium"));
                policy.setStatus(rs.getString("status"));
                policy.setMessage(rs.getString("message"));
                policy.setSenderUsername(rs.getString("sender_username"));
                policy.setReceiverUsername(rs.getString("receiver_username"));
                policy.setReceiverOrganizationId(rs.getInt("receiver_org_id"));
                if (rs.getTimestamp("request_time") != null) {
                    policy.setRequestTime(rs.getTimestamp("request_time").toLocalDateTime());
                }
                if (rs.getTimestamp("finish_time") != null) {
                    policy.setFinishTime(rs.getTimestamp("finish_time").toLocalDateTime());
                }
                result.add(policy);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
