/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.Organization.Organization;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zhangchuanqi
 */
public class OrganizationDAO {

    public static void create(Organization organization) {
        Connection conn = null;
        try {
            conn = data.Data.getConnection();
            conn.setAutoCommit(false);
            String sql = "INSERT INTO Organization VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int id = generateId();
            stmt.setInt(1, id);
            stmt.setString(2, organization.getType().getValue());
            stmt.setString(3, organization.getName());
            stmt.setInt(4, organization.getEnterprise().getEnterpriseId());
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

    public static int generateId() {
        try {
            String sql = "SELECT max(organization_id) from Organization";
            Connection conn = data.Data.getConnection();
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

    public static Organization searchByTypeAndEnterprise(String organizationType, int enterpriseId) {
        Organization organization = null;

        try {
            String sql = "SELECT * from Organization where organization_type = ? and enterprise_id = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, organizationType);
            stmt.setInt(2, enterpriseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                organization = Organization.createOrganization(organizationType); 
                organization.setOrganizationID(rs.getInt("organization_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return organization;
    }
}
