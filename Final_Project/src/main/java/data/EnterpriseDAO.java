/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.Enterprise.Enterprise;
import Business.Enterprise.DentalClinicEnterprise;
import Business.Enterprise.InsuranceEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
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
public class EnterpriseDAO {

    public static ArrayList<Enterprise> getAll() {
        ArrayList<Enterprise> result = new ArrayList<>();
        String sql = "SELECT * FROM (Enterprise NATURAL JOIN Network_Enterprise) NATURAL JOIN Network ORDER BY network_name";

        try {
            Connection conn = getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                Enterprise enterprise;
                String type = rs.getString("enterprise_type");
                if (type.equals(Enterprise.EnterpriseType.DENTAL_CLINIC.getValue())) {
                    enterprise = new DentalClinicEnterprise(rs.getString("enterprise_name"));
                } else {
                    enterprise = new InsuranceEnterprise(rs.getString("enterprise_name"));
                }
                enterprise.setEnterpriseId(rs.getInt("enterprise_id"));
                Network network = new Network();
                network.setId(rs.getInt("network_id"));
                network.setName(rs.getString("network_name"));
                enterprise.setNetwork(network);
                result.add(enterprise);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ArrayList<Enterprise> getAllbyNetworkName(String networkName) {
        ArrayList<Enterprise> result = new ArrayList<>();
        String sql = "SELECT * FROM (Enterprise NATURAL JOIN Network_Enterprise) NATURAL JOIN Network WHERE network_name = ?";

        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, networkName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Enterprise enterprise;
                String type = rs.getString("enterprise_type");
                if (type.equals(Enterprise.EnterpriseType.DENTAL_CLINIC.getValue())) {
                    enterprise = new DentalClinicEnterprise(rs.getString("enterprise_name"));
                } else {
                    enterprise = new InsuranceEnterprise(rs.getString("enterprise_name"));
                }
                enterprise.setEnterpriseId(rs.getInt("enterprise_id"));
                Network network = new Network();
                network.setId(rs.getInt("network_id"));
                network.setName(rs.getString("network_name"));
                enterprise.setNetwork(network);
                result.add(enterprise);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Enterprise searchByUsername(String Username) {
        Enterprise enterprise = null; 
        String sql = "SELECT * FROM ((Enterprise NATURAL JOIN Enterprise_User) NATURAL JOIN "
            + "Network_Enterprise) NATURAL JOIN Network WHERE username = ?";

        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("enterprise_type");
                enterprise = Enterprise.createEnterprise(rs.getString("enterprise_name"), type);
                enterprise.setEnterpriseId(rs.getInt("enterprise_id"));
                Network network = new Network();
                network.setId(rs.getInt("network_id"));
                network.setName(rs.getString("network_name"));
                enterprise.setNetwork(network);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enterprise;
    }

    private static int generateId() {
        try {
            String sql = "SELECT max(enterprise_id) from Enterprise";
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

    public static void createWithAdminOrganization(String enterpriseName, Enterprise.EnterpriseType type, int netwokdId) {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            String sql = "INSERT INTO Enterprise VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int id = generateId();
            stmt.setInt(1, id);
            stmt.setString(2, enterpriseName);
            stmt.setString(3, type.getValue());
            stmt.executeUpdate();

            sql = "INSERT INTO Network_Enterprise VALUES (?, ?)";
            stmt = conn.prepareCall(sql);
            stmt.setInt(1, netwokdId);
            stmt.setInt(2, id);
            stmt.executeUpdate();

            Organization organization = null;
            if (type.getValue().equals(Enterprise.EnterpriseType.DENTAL_CLINIC.getValue())) {
                organization = Organization.createOrganization(Organization.Type.DentalAdmin.getValue());
            } else if (type.getValue().equals(Enterprise.EnterpriseType.INSURACE.getValue())) {
                organization = Organization.createOrganization(Organization.Type.InsuranceAdmin.getValue());
            }
            organization.setName("Default Admin Organization");

            sql = "INSERT INTO Organization VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            int organizationId = data.OrganizationDAO.generateId();
            stmt.setInt(1, organizationId);
            stmt.setString(2, organization.getType().getValue());
            stmt.setString(3, organization.getName());
            stmt.setInt(4, id);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException ex1) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public static void updateEnterpriseName(String enterpriseName, int enterpriseId) {
        try {
            Connection conn = getConnection();
            String sql = "UPDATE Enterprise SET enterprise_name = ? WHERE enterprise_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, enterpriseName);
            stmt.setInt(2, enterpriseId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
