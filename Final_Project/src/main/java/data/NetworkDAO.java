/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

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
public class NetworkDAO {

    public static ArrayList<Network> getAll() {
        ArrayList<Network> result = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM Network";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                Network network = new Network();
                network.setName(rs.getString("network_name"));
                network.setId(rs.getInt("network_id"));
                result.add(network);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void update(Network network) {
        try {
            Connection conn = getConnection();
            String sql = "UPDATE Network SET network_name = ? WHERE network_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, network.getName());
            stmt.setInt(2, network.getId());
            stmt.executeUpdate();
            Logger.getLogger(Data.class.getName()).info("network updated, id = " + network.getId()); 
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static int generateId() {
        try {
            String sql = "SELECT max(network_id) from Network";
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

    public static void create(Network network) {
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO Network VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int id = generateId();
            stmt.setInt(1, id);
            stmt.setString(2, network.getName());
            stmt.executeUpdate();
            Logger.getLogger(Data.class.getName()).info("network created , id = " + id); 
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        public static boolean isNetworkNameExist(String networkName) {
        String sql = "SELECT * FROM Network WHERE network_name = ?";

        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, networkName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class
                .getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
