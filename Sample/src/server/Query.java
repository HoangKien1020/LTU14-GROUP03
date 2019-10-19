/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import bean.Account;
import bean.Transaction;
import com.mysql.cj.util.StringUtils;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Admin
 */
public class Query {

    public static ArrayList<Transaction> getTransactionHistory() {
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        try {
            String getAccountQuery = "select * from History";
            PreparedStatement ps;
            MyConnection connection = new MyConnection();
            ps = connection.getConnection().prepareStatement(getAccountQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String cardNo1 = rs.getString("card_no1");
                String cardNo2 = rs.getString("card_no2");
                String type = "";
                if (rs.getInt("type") == 1) {
                    type = "Rút tiền";
                }
                if (rs.getInt("type") == 2) {
                    type = "Chuyển khoản";
                }
                int moneyNo = rs.getInt("money_no");
                String tranDate = new Date(rs.getDate("tran_date").getTime()).toString();
                String code = rs.getString("code");
                Transaction transc = new Transaction(cardNo1, cardNo2, type, moneyNo, tranDate, code);
                transactionList.add(transc);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return transactionList;
    }

    public static void createAccount(String fullName, String address, String phone, String pin) {
        String hashed = BCrypt.hashpw(pin, BCrypt.gensalt(12));
        String cardNo = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 10);
        try {
            PreparedStatement ps;
            Calendar cal = Calendar.getInstance();
            String sqlhistory = "INSERT INTO Account VALUES (default, ?, ?, ?, ?, ?, default)";
            ps = server.MyConnection.getConnection().prepareStatement(sqlhistory);
            ps.setString(1, fullName);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setString(4, cardNo);
            ps.setString(5, hashed);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Account getAccountInfo(String cardNo) {
        Account accountInfo = null;
        try {
            String getAccountQuery = "select * from Account where card_no=?";
            PreparedStatement ps;
            MyConnection connection = new MyConnection();
            ps = connection.getConnection().prepareStatement(getAccountQuery);
            ps.setString(1, cardNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                accountInfo = new Account(rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getBigDecimal(7));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return accountInfo;
    }

    public void changePin(String card_no, String newpin) {
        String hashed = BCrypt.hashpw(newpin, BCrypt.gensalt(12));
        try {
            String sql = "update Account set pin =? where card_no =?";
            PreparedStatement ps;
            MyConnection connection = new MyConnection();
            ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, hashed);
            ps.setString(2, card_no);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void updateAccountBalance(String cardNo, int balance) {
        try {
            String sql = "update Account set balance =? where card_no =?";
            PreparedStatement ps;
            MyConnection connection = new MyConnection();
            ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, balance);
            ps.setString(2, cardNo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void insertTopUpTransactionHistory(String cardNo, int moneyNo) {

        try {
            PreparedStatement ps;
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp currentDate = new java.sql.Timestamp(new java.util.Date().getTime());
            String sqlhistory = "INSERT INTO History VALUES (?, ?, ?, ?, ?, ?)";
            ps = server.MyConnection.getConnection().prepareStatement(sqlhistory);
            ps.setString(1, cardNo);
            ps.setString(2, "");
            ps.setInt(3, 1);
            ps.setInt(4, moneyNo);
            ps.setTimestamp(5, currentDate);
            ps.setString(6, "Nạp tiền thành công");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
