/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.Network.Network;
import Business.Role.Role;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccount;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author zhangchuanqi
 */
public class Data {

    public static Connection getConnection() throws SQLException {
        // final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/Final_Project?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        final String USERNAME = "user1";
        final String PASSWORD = "user1";

        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    public static UserAccount login(String userName, String passWord) {
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM User WHERE username = ?";
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, userName);
            ResultSet rs = stat.executeQuery();
            if (rs.next()) {
                if (rs.getString("password").equals(passWord)) {
                    UserAccount account = new UserAccount();
                    account.setUsername(userName);

                    query = "SELECT * FROM Roles WHERE username = ?";
                    stat = conn.prepareStatement(query);
                    stat.setString(1, userName);
                    rs = stat.executeQuery();
                    rs.next();
                    String roleName = rs.getString("role_name");
                    account.setRole(Role.createRole(roleName));
                    return account;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean requireNotEmpty(Component parentComponent, JTextField txtField) {
        if (txtField.getText() == null || txtField.getText().equals("")) {
            txtField.setBorder(new LineBorder(Color.RED));
            JOptionPane.showMessageDialog(parentComponent, "This text field can't be empty!");
            return false;
        }
        return true;
    }

}
