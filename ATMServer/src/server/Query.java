/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import bean.Account;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
            Calendar cal = Calendar.getInstance();
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
                if (rs.getInt("type") == 3) {
                    type = "Nạp tiền";
                }
                BigDecimal moneyNo = rs.getBigDecimal("money_no");
                String tranDate = rs.getTimestamp("tran_date", cal).toString();
                String code = rs.getString("code");
                Transaction transc = new Transaction(cardNo1, cardNo2, type, moneyNo, tranDate, code);
                transactionList.add(transc);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return transactionList;
    }

    public static boolean check(String cardNo) {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkcard = false;
        String query = "select * from Account where card_no=?";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, cardNo);
            rs = ps.executeQuery();
            if (rs.next()) {
                checkcard = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return checkcard;
    }

    public static void createAccount(String fullName, String address, String phone, String cardNo, String pin) {
        String hashed = BCrypt.hashpw(pin, BCrypt.gensalt(12));
        try {
            PreparedStatement ps;
            String sqlcreate = "INSERT INTO Account(full_name,address,phone,card_no,pin,balance,status) VALUES (?, ?, ?, ?, ?, ?,?)";
            ps = server.MyConnection.getConnection().prepareStatement(sqlcreate);
            ps.setString(1, fullName);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setString(4, cardNo);
            ps.setString(5, hashed);
            ps.setBigDecimal(6, new BigDecimal("50000"));
            ps.setInt(7, 0);
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

    public ArrayList<String> insertTopUpTransactionHistory(String cardNo, BigDecimal amount) {
        ArrayList<String> code = new ArrayList<>();
        try {
            //store code

            String store = "Nạp tiền thành công!";
            Account accountInfo = getAccountInfo(cardNo);
            if (amount.compareTo(new BigDecimal("50000")) == 0 || amount.compareTo(new BigDecimal("50000")) == 1) {

                if (!amount.remainder(new BigDecimal("5000")).equals(BigDecimal.ZERO)) {
                    code.add("Lỗi,số tiền phải là bội của 5");
                } else {
                    try {
                        BigDecimal balance = accountInfo.getBalance().add(amount);
                        String sql = "update Account set balance =? where card_no =?";
                        PreparedStatement ps;
                        ps = server.MyConnection.getConnection().prepareStatement(sql);
                        ps.setBigDecimal(1, balance);
                        ps.setString(2, cardNo);
                        ps.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                code.add("Lỗi,số tiền phải từ đủ 50.000VND!");
            }

            if (code.size() > 0) {

                for (int i = 0; i < code.size(); i++) {
                    store = code.get(i);
                }
            }
            //store history
            PreparedStatement ps;
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp curent_Date = new java.sql.Timestamp(new java.util.Date().getTime());
            String sqlhistory = "INSERT INTO History(card_no1,card_no2,type,money_no,tran_date,code) VALUES(?,?,?,?,?,?)";
            ps = server.MyConnection.getConnection().prepareStatement(sqlhistory);
            ps.setString(1, accountInfo.getCard_no());
            ps.setString(2, accountInfo.getCard_no());
            ps.setInt(3, 3);
            ps.setBigDecimal(4, amount);
            ps.setTimestamp(5, curent_Date, cal);
            ps.setString(6, store);
            ps.executeUpdate();
            return code;
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return code;
    }
    
}
