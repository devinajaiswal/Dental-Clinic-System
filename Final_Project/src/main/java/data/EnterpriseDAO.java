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
        String sql = "SELECT * FROM (Enterprise NATURAL JOIN NetworkEnterprise) NATURAL JOIN Network ORDER BY network_name";

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
        String sql = "SELECT * FROM (Enterprise NATURAL JOIN NetworkEnterprise) NATURAL JOIN Network WHERE network_name = ?";

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

    public static void create(Enterprise enterprise) {
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO Enterprise VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int id = generateId();
            stmt.setInt(1, id);
            stmt.setString(2, enterprise.getEnterpriseName());
            stmt.setString(3, enterprise.getEnterpriseType().getValue());
            stmt.executeUpdate();

            sql = "INSERT INTO NetworkEnterprise VALUES (?, ?)";
            stmt = conn.prepareCall(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, enterprise.getNetwork().getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void update(Enterprise enterprise) {
        try {
            Connection conn = getConnection();
            String sql = "UPDATE Enterprise SET enterprise_name = ? WHERE network_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, enterprise.getEnterpriseName());
            stmt.setInt(2, enterprise.getEnterpriseId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}