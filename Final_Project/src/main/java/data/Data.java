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

    public static boolean requireNotEmpty(Component parentComponent, JTextField... txtFields) {
        for (JTextField txtField : txtFields) {
            if (txtField.getText() == null || txtField.getText().equals("")) {
                txtField.setBorder(new LineBorder(Color.RED));
                JOptionPane.showMessageDialog(parentComponent, "This text field can't be empty!");
                return false;
            }
        }
        return true;
    }

    public static boolean requirePhoneNumber(Component parentComponent, JTextField... txtFields) {
        for (JTextField txtField : txtFields) {
            if (txtField.getText() == null || txtField.getText().equals("")) {
                txtField.setBorder(new LineBorder(Color.RED));
                JOptionPane.showMessageDialog(parentComponent, "This text field can't be empty!");
                return false;
            }
            if (!phonePatternCorrect(txtField.getText())) {
                txtField.setBorder(new LineBorder(Color.RED));
                JOptionPane.showMessageDialog(parentComponent, "Phone number pattern doesn't match!");
                return false;
            }
        }
        return true;
    }

    public static boolean requirePostcode(Component parentComponent, JTextField... txtFields) {
        for (JTextField txtField : txtFields) {
            if (txtField.getText() == null || txtField.getText().equals("")) {
                txtField.setBorder(new LineBorder(Color.RED));
                JOptionPane.showMessageDialog(parentComponent, "This text field can't be empty!");
                return false;
            }
            if (!postcodePatternCorrect(txtField.getText())) {
                txtField.setBorder(new LineBorder(Color.RED));
                JOptionPane.showMessageDialog(parentComponent, "Postcode pattern doesn't match!");
                return false;
            }
        }
        return true;
    }

    public static boolean requireEmail(Component parentComponent, JTextField... txtFields) {
        for (JTextField txtField : txtFields) {
            if (txtField.getText() == null || txtField.getText().equals("")) {
                txtField.setBorder(new LineBorder(Color.RED));
                JOptionPane.showMessageDialog(parentComponent, "This text field can't be empty!");
                return false;
            }
            if (!emailPatternCorrect(txtField.getText())) {
                txtField.setBorder(new LineBorder(Color.RED));
                JOptionPane.showMessageDialog(parentComponent, "Email address pattern doesn't match!");
                return false;
            }
        }
        return true;
    }

    private static boolean phonePatternCorrect(String phone) {
        Pattern p = Pattern.compile("^(1\\s?)?(\\(\\d{3}\\)|\\d{3})\\s?-?\\d{3}-?\\s?\\d{4}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    private static boolean postcodePatternCorrect(String postcode) {
        Pattern p = Pattern.compile("^\\d{5}$");
        Matcher m = p.matcher(postcode);
        return m.matches();
    }

    private static boolean emailPatternCorrect(String email) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static void sendEmail(String to, String title, String content) throws AddressException, MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("final.project.javabean", "finalbean");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("final.project.javabean@gmail.com"));
        message.setRecipients(
            Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);

        String msg = content;

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

    }

    public static void sendSMS(String to, String text) {
        String ACCOUNT_SID = "AC9f02539e506ffa7dc7247257a995c6b2";
        String AUTH_TOKEN = "7c6387a3d3aebb356dbf89f38c16f8fa";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message
            .creator(new PhoneNumber("+1" + to), // to
                new PhoneNumber("+12564149094"), // from
                text)
            .create();
    }

}
