/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zhangchuanqi
 */
public class UserDAO {

    public static ArrayList<UserAccount> getAllEnterpriseAdmin() {
        ArrayList<UserAccount> result = new ArrayList<>();

        String sql = "SELECT * FROM (((Roles natural join EnterpriseUser) natural join NetworkEnterprise)\n"
            + "natural join Enterprise) natural join network\n"
            + "where role_name = ?;";
        try {
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Role.RoleType.EnterpriseAdmin.getValue());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserAccount user = new UserAccount();
                user.setUsername(rs.getString("username"));
                Enterprise enterprise = Enterprise.createEnterprise(rs.getString("enterprise_name"), rs.getString("enterprise_type"));
                enterprise.setEnterpriseId(rs.getInt("enterprise_id"));
                Network network = new Network();
                network.setId(rs.getInt("network_id"));
                network.setName(rs.getString("network_name"));
                enterprise.setNetwork(network);
                user.setEnterprise(enterprise);
                result.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
