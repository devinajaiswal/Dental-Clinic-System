/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author zhangchuanqi
 */
public class Util {

    public static void setBorderBlack(JComponent... components) {
        for (JComponent component : components) {
            component.setBorder(new LineBorder(new Color(128, 128, 128)));
        }
    }

    public static void setBorderRed(JComponent component) {
        component.setBorder(new LineBorder(Color.RED));
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

    public static boolean requireSeletedItemNotNull(Component parentComponent, JComboBox... combos) {
        for (JComboBox combo : combos) {
            if (combo.getSelectedItem() == null) {
                combo.setBorder(new LineBorder(Color.RED));
                JOptionPane.showMessageDialog(parentComponent, "This text field can't be empty!");
                return false;
            }
        }
        return true;
    }

    public static boolean requirePhoneNumber(Component parentComponent, JTextField... txtFields) {
        for (JTextField txtField : txtFields) {
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

//        AuthMethod auth = new TokenAuthMethod("ba7ca931", "lzxn76ucVe521SoF");
//        NexmoClient client = new NexmoClient(auth);
//        TextMessage message = new TextMessage("17866403564", to, text);
//        SmsSubmissionResult[] responses;
//        try {
//            responses = client.getSmsClient().submitMessage(message);
//            for (SmsSubmissionResult response : responses) {
//                System.out.println(response);
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NexmoClientException ex) {
//            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public static String getRandomString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of n bytes
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            // generate a random number between 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
