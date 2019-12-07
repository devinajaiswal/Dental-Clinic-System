/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.Customer.CustomerMedicalInfo;
import Business.Customer.CustomerPersonalInfo;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
                enterprise.setEnterpriseId(rs.getInt("enterprise_id"));
                Network network = new Network();
                network.setId(rs.getInt("network_id"));
                network.setName(rs.getString("network_name"));
                Employee employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
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

    public static ArrayList<UserAccount> getAllUsersByEnterpriseId(int enpterpriseId) {
        ArrayList<UserAccount> result = new ArrayList<>();

        String sql = "SELECT * FROM ((((((Enterprise natural join Enterprise_User) natural join Employee_User)\n"
            + "            natural join User) natural join Role_User) natural join Employee) \n"
            + "            natural join Organization_User) natural join Organization\n"
            + "            where enterprise_id = ?";
        try {
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enpterpriseId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserAccount user = new UserAccount();
                user.setUsername(rs.getString("username"));
                Enterprise enterprise = Enterprise.createEnterprise(rs.getString("enterprise_name"), rs.getString("enterprise_type"));
                enterprise.setEnterpriseId(rs.getInt("enterprise_id"));
                Employee employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
                employee.setName(rs.getString("employee_name"));
                employee.setEmail(rs.getString("email"));
                Organization org = Organization.createOrganization(rs.getString("organization_type"));
                org.setOrganizationID(rs.getInt("organization_id"));
                org.setName(rs.getString("organization_name"));
                Role role = Role.createRole(rs.getString("role_name"));
                user.setEnterprise(enterprise);
                user.setEmployee(employee);
                user.setOrganization(org);
                user.setRole(role);
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
             Logger.getLogger(Data.class.getName()).info("user created, id = " + user.getUsername()); 

            sql = "INSERT INTO Employee values (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            int employeeId = generateEmployeeId();
            stmt.setInt(1, employeeId);
            stmt.setString(2, user.getEmployee().getName());
            stmt.setString(3, user.getEmployee().getEmail());
            stmt.executeLargeUpdate();
             Logger.getLogger(Data.class.getName()).info("employee created, id = " + employeeId); 

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
             Logger.getLogger(Data.class.getName()).info("customer created, id = " + user.getUsername()); 

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

    public static void updateEmailCode(String username, String emailCode) {
        try {
            String sql = "SELECT username from User_VerficationCodes where username = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                sql = "UPDATE User_VerficationCodes SET email_code = ? WHERE username = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, emailCode);
                stmt.setString(2, username);
                stmt.executeUpdate();
            } else {
                sql = "INSERT INTO User_VerficationCodes VALUES (?, ?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, null);
                stmt.setString(3, emailCode);
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updatePhoneCode(String username, String phoneCode) {
        try {
            String sql = "SELECT username from User_VerficationCodes where username = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                sql = "UPDATE User_VerficationCodes SET phone_code = ? WHERE username = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, phoneCode);
                stmt.setString(2, username);
                stmt.executeUpdate();
            } else {
                sql = "INSERT INTO User_VerficationCodes VALUES (?, ?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, phoneCode);
                stmt.setString(3, null);
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean checkEmailCode(String username, String emailCode) {
        boolean result = false;
        try {
            String sql = "SELECT email_code from User_VerficationCodes where username = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (emailCode.equals(rs.getString("email_code"))) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static boolean checkPhoneCode(String username, String phoneCode) {
        boolean result = false;
        try {
            String sql = "SELECT phone_code from User_VerficationCodes where username = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (phoneCode.equals(rs.getString("phone_code"))) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static boolean isSSNExist(String ssn) {
        try {
            String sql = "SELECT ssn from User_PersonalInfo where ssn = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ssn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean isEmailExist(String email) {
        try {
            String sql = "SELECT email from User_PersonalInfo where email = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean isPhoneExist(String phone) {
        try {
            String sql = "SELECT phone from User_PersonalInfo where phone = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void createPersonalInfo(String username, CustomerPersonalInfo personalInfo) {
        try {
            Connection conn = data.Data.getConnection();
            String sql = "INSERT INTO User_PersonalInfo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, personalInfo.getFirstName());
            stmt.setString(3, personalInfo.getLastName());
            stmt.setString(4, personalInfo.getSsn());
            stmt.setString(5, personalInfo.getStreet());
            stmt.setString(6, personalInfo.getCity());
            stmt.setString(7, personalInfo.getState());
            stmt.setString(8, personalInfo.getPostcode());
            stmt.setString(9, personalInfo.getPhone());
            stmt.setString(10, personalInfo.getEmail());
            stmt.executeUpdate();

            sql = "DELETE FROM User_VerficationCodes WHERE username = ?";
            stmt = conn.prepareCall(sql);
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean isPersonalInfoComplete(String username) {
        try {
            String sql = "SELECT ssn from User_PersonalInfo where username = ?";
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

    public static boolean isMedicalInfoComplete(String username) {
        try {
            String sql = "SELECT gender from User_MedicalInfo where username = ?";
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

    public static CustomerPersonalInfo searchPersonalInfo(String username) {
        CustomerPersonalInfo result = null;
        try {
            String sql = "SELECT * from User_PersonalInfo where username = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new CustomerPersonalInfo();
                result.setFirstName(rs.getString("first_name"));
                result.setLastName(rs.getString("last_name"));
                result.setSsn(rs.getString("ssn"));
                result.setStreet(rs.getString("street"));
                result.setCity(rs.getString("city"));
                result.setState(rs.getString("state"));
                result.setPostcode(rs.getString("postcode"));
                result.setPhone(rs.getString("phone"));
                result.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void updatePersonalInfo(String username, CustomerPersonalInfo personalInfo) {
        try {
            Connection conn = data.Data.getConnection();
            String sql = "Update User_PersonalInfo SET street = ?, city = ?, state = ?, postcode = ?, phone = ? where username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, personalInfo.getStreet());
            stmt.setString(2, personalInfo.getCity());
            stmt.setString(3, personalInfo.getState());
            stmt.setString(4, personalInfo.getPostcode());
            stmt.setString(5, personalInfo.getPhone());
            stmt.setString(6, username);
            stmt.executeUpdate();

            sql = "DELETE FROM User_VerficationCodes WHERE username = ?";
            stmt = conn.prepareCall(sql);
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<String> searchUsernamesByOrganizationId(int organizationId) {
        ArrayList<String> result = new ArrayList<>();

        try {
            String sql = "SELECT * from Organization_User natural join User  where organization_id = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, organizationId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("username"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

        public static ArrayList<String> searchUsernamesByEnterpriseIdAndRole(int enterpriseid, String role) {
        ArrayList<String> result = new ArrayList<>();

        try {
            String sql = "SELECT * from (Enterprise_User natural join User) natural join Role_User \n"
                + "where enterprise_id = ? AND role_name = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enterpriseid);
            stmt.setString(2, role);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void updateNameAndPassword(int employeeId, String name, String username, String password) {
        try {
            Connection conn = data.Data.getConnection();
            conn.setAutoCommit(false);
            String sql = "Update Employee SET employee_name = ?  where employee_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, employeeId);
            stmt.executeUpdate();

            sql = "UPDATE User SET password = ? WHERE username = ?";
            stmt = conn.prepareCall(sql);
            stmt.setString(1, password);
            stmt.setString(2, username);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateNameAndPasswordAndEmail(int employeeId, String name, String email,
        String username, String password) {
        try {
            Connection conn = data.Data.getConnection();
            conn.setAutoCommit(false);
            String sql = "Update Employee SET employee_name = ?, email = ? where employee_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, employeeId);
            stmt.executeUpdate();

            sql = "UPDATE User SET password = ? WHERE username = ?";
            stmt = conn.prepareCall(sql);
            stmt.setString(1, password);
            stmt.setString(2, username);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createMedicalInfo(String username, CustomerMedicalInfo info) {
        try {
            Connection conn = data.Data.getConnection();
            String sql = "INSERT INTO User_MedicalInfo VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, info.getGender());
            stmt.setTimestamp(3, Timestamp.valueOf(info.getDob()));
            stmt.setBoolean(4, info.isSmoking());
            stmt.setBoolean(5, info.isSweet());
            stmt.setBoolean(6, info.isDiabetes());
            stmt.setBoolean(7, info.isCardio());
            stmt.setBoolean(8, info.isImmune());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateMedicalInfo(String username, CustomerMedicalInfo info) {
        try {
            Connection conn = data.Data.getConnection();
            String sql = "Update User_MedicalInfo SET gender = ?, smoking = ?, sweet = ?, diabetes = ?, "
                + "cardio = ?, immune = ?, dob = ? where username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, info.getGender());
            stmt.setBoolean(2, info.isSmoking());
            stmt.setBoolean(3, info.isSweet());
            stmt.setBoolean(4, info.isDiabetes());
            stmt.setBoolean(5, info.isCardio());
            stmt.setBoolean(6, info.isImmune());
            stmt.setTimestamp(7, Timestamp.valueOf(info.getDob()));
            stmt.setString(8, username);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static CustomerMedicalInfo searchMedicalInfo(String username) {
        CustomerMedicalInfo result = null;
        try {
            String sql = "SELECT * from User_MedicalInfo where username = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new CustomerMedicalInfo();
                result.setGender(rs.getString("gender"));
                result.setDob(rs.getTimestamp("dob").toLocalDateTime());
                result.setSmoking(rs.getBoolean("smoking"));
                result.setSweet(rs.getBoolean("sweet"));
                result.setDiabetes(rs.getBoolean("diabetes"));
                result.setCardio(rs.getBoolean("cardio"));
                result.setImmune(rs.getBoolean("immune"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
