/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.Role.Role;
import Business.UserAccount.UserAccount;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.type.PhoneNumberCapabilities;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import userinterface.MainJFrame;

/**
 *
 * @author zhangchuanqi
 */
public class Data {

    static Connection conn;

    private static Connection createConnection() throws SQLException {
        // final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/Final_Project?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        final String USERNAME = "user1";
        final String PASSWORD = "user1";
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = createConnection();
        }
        return conn;
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

                    query = "SELECT * FROM Role_User WHERE username = ?";
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

    public static ArrayList<String> getAllStates() {
        ArrayList<String> result = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT state_name FROM States";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString("state_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }


}
