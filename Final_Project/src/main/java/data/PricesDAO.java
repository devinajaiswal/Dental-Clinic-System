/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Business.Customer.CustomerMedicalInfo;
import Business.Enterprise.InsurancePlan;
import Business.Enterprise.TreatmentPrices;
import Business.WorkQueue.TreatmentWorkRequest;
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
public class PricesDAO {

    public static void createTreatmentPrices(int enterprise_id, double fillingPrice, double rootPrice, double srpPrice) {
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO TreatmentPrice VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enterprise_id);
            stmt.setDouble(2, fillingPrice);
            stmt.setDouble(3, rootPrice);
            stmt.setDouble(4, srpPrice);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateTreatmentPrices(int enterprise_id, double fillingPrice, double rootPrice, double srpPrice) {
        try {
            Connection conn = getConnection();
            String sql = "update TreatmentPrice set filling_price = ?, rootcanal_price = ?, "
                + " srp_price = ? where enterprise_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(4, enterprise_id);
            stmt.setDouble(1, fillingPrice);
            stmt.setDouble(2, rootPrice);
            stmt.setDouble(3, srpPrice);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean isTeatmentPricesExist(int enterpriseId) {
        try {
            String sql = "SELECT * from TreatmentPrice where enterprise_id = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enterpriseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static TreatmentPrices searchTreatmentPrice(int enterpriseId) {
        TreatmentPrices result = null;
        try {
            String sql = "SELECT * from TreatmentPrice where enterprise_id = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enterpriseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new TreatmentPrices();
                result.setFillingPrice(rs.getDouble("filling_price"));
                result.setRootPrice(rs.getDouble("rootcanal_price"));
                result.setSrpPrice(rs.getDouble("srp_price"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int generatePlanId() {
        try {
            String sql = "SELECT max(plan_id) from InsurancePlan";
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

    public static void createPlan(int enterprise_id, InsurancePlan plan) {
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO InsurancePlan VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, generatePlanId());
            stmt.setString(2, plan.getPlanName());
            stmt.setDouble(3, plan.getPrice());
            stmt.setInt(4, enterprise_id);
            stmt.setDouble(5, plan.getFillingCoverage());
            stmt.setDouble(6, plan.getRootCoverage());
            stmt.setDouble(7, plan.getSrpCoverage());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updatePlan(InsurancePlan plan) {
        try {
            Connection conn = getConnection();
            String sql = "update InsurancePlan set plan_name = ?, price = ?, filling_coverage = ?, "
                + " root_coverage = ?, srp_coverage = ? where plan_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, plan.getPlanName());
            stmt.setDouble(2, plan.getPrice());
            stmt.setDouble(3, plan.getFillingCoverage());
            stmt.setDouble(4, plan.getRootCoverage());
            stmt.setDouble(5, plan.getSrpCoverage());
            stmt.setInt(6, plan.getPlanId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<InsurancePlan> searchInsurancePlans(int enterpriseId) {
        ArrayList<InsurancePlan> result = new ArrayList<>();
        try {
            String sql = "SELECT * from InsurancePlan where enterprise_id = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enterpriseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                InsurancePlan plan = new InsurancePlan();
                plan.setPlanId(rs.getInt("plan_id"));
                plan.setEnterpriseId(rs.getInt("enterprise_id"));
                plan.setPlanName(rs.getString("plan_name"));
                plan.setPrice(rs.getDouble("price"));
                plan.setFillingCoverage(rs.getDouble("filling_coverage"));
                plan.setRootCoverage(rs.getDouble("root_coverage"));
                plan.setSrpCoverage(rs.getDouble("srp_coverage"));
                result.add(plan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static InsurancePlan searchInsurancePlan(int planId) {
        try {
            String sql = "SELECT * from InsurancePlan where plan_id = ?";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, planId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                InsurancePlan plan = new InsurancePlan();
                plan.setPlanId(rs.getInt("plan_id"));
                plan.setEnterpriseId(rs.getInt("enterprise_id"));
                plan.setPlanName(rs.getString("plan_name"));
                plan.setPrice(rs.getDouble("price"));
                plan.setFillingCoverage(rs.getDouble("filling_coverage"));
                plan.setRootCoverage(rs.getDouble("root_coverage"));
                plan.setSrpCoverage(rs.getDouble("srp_coverage"));
                return plan;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<InsurancePlan> getAllInsurancePlans() {
        ArrayList<InsurancePlan> result = new ArrayList<>();
        try {
            String sql = "SELECT * from InsurancePlan";
            Connection conn = data.Data.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                InsurancePlan plan = new InsurancePlan();
                plan.setPlanId(rs.getInt("plan_id"));
                plan.setEnterpriseId(rs.getInt("enterprise_id"));
                plan.setPlanName(rs.getString("plan_name"));
                plan.setPrice(rs.getDouble("price"));
                plan.setFillingCoverage(rs.getDouble("filling_coverage"));
                plan.setRootCoverage(rs.getDouble("root_coverage"));
                plan.setSrpCoverage(rs.getDouble("srp_coverage"));
                result.add(plan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
