/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DecimalFormat; 
/**
 *
 * @author rc
 */
public class sql {
    public static boolean checkID(String id){
        String sqlCheck = "select * from  Account  where  Account.id  = ?";
        PreparedStatement ps;
        try {
                ps = MyConnection.getConnection().prepareStatement(sqlCheck);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
		rs.beforeFirst();
		boolean sa = rs.next();
		rs.close();
		ps.close();
		return sa;
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        
    }
    
     public static boolean deleteID(String id){
        //String sqlDelete = "DELETE FROm Account WHERE Account.id=?;DELETE FROM History WHERE History.id1=? or History.id2=?;";
        String sqlDelete = "DELETE FROm Account WHERE Account.id=?";
        
        PreparedStatement ps;
        try {
                ps = MyConnection.getConnection().prepareStatement(sqlDelete);
                ps.setString(1, id);
                boolean rs = ps.execute();
		return true;
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        
    }
     
    public static String getuserInfo(String id){
        String sql = "select * FROm Account WHERE Account.id=?;"; 
        PreparedStatement ps;
        try {
            ps = MyConnection.getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int id1 = rs.getInt("id");
            String fullName = rs.getString("full_name");
            String phone = rs.getString("phone");
            String ret = "ID: " +id1 +"\nTen: "+ fullName +"\nDien thoai: "+ phone;
            return ret;
        } catch (SQLException ex) {
            Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "fail";
    }
    
    public static BigDecimal getBalance(String id){
        
        String sql = "select * FROm Account WHERE Account.id=?;"; 
        PreparedStatement ps;
        BigDecimal balance = null;
        try {
            ps = MyConnection.getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int id1 = rs.getInt("id");
            balance = rs.getBigDecimal("balance");
            
            return balance;
        } catch (SQLException ex) {
            Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance;
    }
    
    public static boolean updateBalance(String id, BigDecimal money){
        String sql = "update Account set balance = ? where Account.id = ?";;
        PreparedStatement ps;
        try {
                ps = MyConnection.getConnection().prepareStatement(sql);
                ps.setString(2, id);
                ps.setBigDecimal(1, money);             
                int rs = ps.executeUpdate();      
		return true ;
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        
    }
    public static boolean updatePin(String id, String pin){
        String sql = "update Account set pin = ? where Account.id = ?";;
        PreparedStatement ps;
        try {
                ps = MyConnection.getConnection().prepareStatement(sql);
                ps.setString(2, id);
                ps.setString(1, pin);             
                int rs = ps.executeUpdate();      
		return true ;
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        
    }
}
