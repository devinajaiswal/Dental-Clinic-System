/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
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
public class UserDAO {

    public static ArrayList<UserAccount> getAllEnterpriseAdmin() {
        ArrayList<UserAccount> result = new ArrayList<>();

        String sql = "SELECT * FROM (((((Role_User natural join Enterprise_User) natural join Network_Enterprise)\n"
            + "natural join Enterprise) natural join network) natural join Employee_User) natural join Employee\n"
            + "where role_name = ?";
        try {
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Role.RoleType.EnterpriseAdmin.getValue());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserAccount user = new UserAccount();
                user.setUsername(rs.getString("username"));
                Enterprise enterprise = Enterprise.createEnterprise(rs.getString("enterprise_name"), rs.getString("enterprise_type"));
                System.out.println(rs.getString("enterprise_name"));
                System.out.println(rs.getString("enterprise_type"));
                enterprise.setEnterpriseId(rs.getInt("enterprise_id"));
                Network network = new Network();
                network.setId(rs.getInt("network_id"));
                network.setName(rs.getString("network_name"));
                Employee employee = new Employee();
                employee.setName(rs.getString("employee_name"));
                employee.setEmail(rs.getString("email"));
                enterprise.setNetwork(network);
                user.setEnterprise(enterprise);
                user.setEmployee(employee);
                result.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static int generateEmployeeId() {
        try {
            String sql = "SELECT max(employee_id) from Employee";
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

    public static boolean isUsernameExist(String username) {
        try {
            String sql = "SELECT username from User where username = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void createWithEmployee(UserAccount user, int organizationId) {
        Connection conn = null;
        try {
            conn = data.Data.getConnection();
            conn.setAutoCommit(false);
            String sql = "INSERT INTO User values (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();

            sql = "INSERT INTO Employee values (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            int employeeId = generateEmployeeId();
            stmt.setInt(1, employeeId);
            stmt.setString(2, user.getEmployee().getName());
            stmt.setString(3, user.getEmployee().getEmail());
            stmt.executeLargeUpdate();

            sql = "INSERT INTO Employee_User values (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employeeId);
            stmt.setString(2, user.getUsername());
            stmt.executeLargeUpdate();

            sql = "INSERT INTO Role_User values (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getRole().getRoleType().getValue());
            stmt.executeLargeUpdate();

            sql = "INSERT INTO Organization_User values (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, organizationId);
            stmt.setString(2, user.getUsername());
            stmt.executeLargeUpdate();
            
            sql = "INSERT INTO Enterprise_User values (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getEnterprise().getEnterpriseId());
            stmt.setString(2, user.getUsername());
            stmt.executeLargeUpdate();

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

    public static void createCustomer(UserAccount user) {
        Connection conn = null;
        try {
            conn = data.Data.getConnection();
            conn.setAutoCommit(false);
            String sql = "INSERT INTO User values (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();

            sql = "INSERT INTO Role_User values (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getRole().getRoleType().getValue());
            stmt.executeLargeUpdate();

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

}
