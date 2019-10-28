/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import bean.Bank;
import bean.Account;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
 * @author HK
 */
// Implementing the remote interface 
// Exporting the object of implementation class  
// (here we are exporting the remote object to the stub)
public class BannImpl extends UnicastRemoteObject implements Bank {

    //ArrayList<Account> account;
    private server.JwtApp jwt;
    private static int count = 0;

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        BannImpl.count = count;
    }

    public BannImpl() throws RemoteException {
        jwt = new server.JwtApp();
    }

    // Implementing the interface method
    private String getValue(String cardNo) {
        try {
            if (jwt.validateJWT(cardNo)) {
                String getValue = jwt.getValue(cardNo);
                return getValue;
            }
        } catch (Exception ex) {
            Logger.getLogger(BannImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public ArrayList<String> login(String cardNo, String PIN) throws RemoteException, SQLException {
        ArrayList<String> userInfo = new ArrayList<>();
        if (getCount() < 3) {
            String sql = "select card_no from Account where card_no=?";
            PreparedStatement ps;
            ps = server.MyConnection.getConnection().prepareStatement(sql);
            ps.setString(1, cardNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (checkpass(PIN, cardNo)) {
                    try {
                        String sqlcheck = "select status from Account where card_no=?";
                        ps = server.MyConnection.getConnection().prepareStatement(sqlcheck);
                        ps.setString(1, cardNo);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            int statuscheck = rs.getInt("status");
                            //System.out.println(statuscheck);
                            if (statuscheck == 0) {
                                userInfo.add(jwt.generateJWT(cardNo));
                                String sqlLogin = "update Account set status =? where card_no =?";
                                ps = server.MyConnection.getConnection().prepareStatement(sqlLogin);
                                ps.setInt(1, 1);// logined
                                ps.setString(2, cardNo);
                                ps.executeUpdate();

                            } else if (statuscheck == 1) {
                                userInfo.add("logined");
                            } else {
                                userInfo.add("locked");
                            }

                            return userInfo;
                        }

                    } catch (Exception ex) {
                        Logger.getLogger(BannImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    count = count + 1;
                    setCount(count);
                    //System.out.println(getCount());
                }
            }
        } else {
            String sqlLock = "update Account set status =? where card_no =?";
            PreparedStatement ps;
            ps = server.MyConnection.getConnection().prepareStatement(sqlLock);
            ps.setInt(1, 2);// logined
            ps.setString(2, cardNo);
            ps.executeUpdate();
            userInfo.add("locked");
            return userInfo;

        }
        return null;
    }

    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

    @Override
    public Account getAccount(String cardNo) throws RemoteException, SQLException {

        if (isNumeric(cardNo)) {
            String sql = "select * from Account where card_no=?";
            PreparedStatement ps;
            ps = server.MyConnection.getConnection().prepareStatement(sql);
            ps.setString(1, cardNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getBigDecimal(7));
            }
        } else {
            try {
                String value = getValue(cardNo);
                if (value != null) {
                    String sql = "select * from Account where card_no=?";
                    PreparedStatement ps;
                    ps = server.MyConnection.getConnection().prepareStatement(sql);
                    ps.setString(1, value);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        return new Account(rs.getString(2), rs.getString(3),
                                rs.getString(4), rs.getString(5), rs.getString(6), rs.getBigDecimal(7));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(BannImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }

    @Override
    public ArrayList<String> inquiry(String cardNo) throws RemoteException, SQLException {
        ArrayList<String> accountInquiry = new ArrayList<>();
        String value = getValue(cardNo);

        if (value != null) {
            try {
                String sql = "select full_name,card_no from Account where card_no=?";
                PreparedStatement ps;
                ps = server.MyConnection.getConnection().prepareStatement(sql);
                ps.setString(1, value);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    accountInquiry.add(rs.getString(1));
                    accountInquiry.add(rs.getString(2));
                    // accountInquiry.add(rs.getString(3));
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return accountInquiry;
    }

    @Override
    public ArrayList<String> transfer(String cardNo1, String cardNo2, BigDecimal amount) throws RemoteException, SQLException {
        String value = getValue(cardNo1);
        if (value != null) {
            ArrayList<String> code = new ArrayList<>();
            String store = "Chuyển khoản thành công!";
            Account acc1 = getAccount(value);
            Account acc2 = getAccount(cardNo2);
            if (acc2 != null) {
                if (acc2.getCard_no().equals(acc1.getCard_no())) {
                    store = "Bạn không thể chuyển khoản cho chính mình!";
                    code.add(store);
                } else {
                    if (amount.compareTo(new BigDecimal("50000")) == 0 || amount.compareTo(new BigDecimal("50000")) == 1) {

                        if (amount.compareTo(acc1.getBalance()) == 1) {
                            code.add("Lỗi,Số tiền lớn hơn số dư của bạn!");
                            // store = "Error,Amount is greater than your balance!";
                        } else {
                            if (!amount.remainder(new BigDecimal("5000")).equals(BigDecimal.ZERO)) {
                                code.add("Lỗi,số tiền phải là bội của 5");
                            } else {
                                BigDecimal balance1 = acc1.getBalance().subtract(amount);
                                String sql = "update Account set balance =? where card_no =?";
                                PreparedStatement ps;
                                ps = server.MyConnection.getConnection().prepareStatement(sql);
                                ps.setBigDecimal(1, balance1);
                                ps.setString(2, value);
                                ps.executeUpdate();

                                BigDecimal balance2 = acc2.getBalance().add(amount);
                                String sql2 = "update Account set balance =? where card_no =?";
                                PreparedStatement ps2;
                                ps = server.MyConnection.getConnection().prepareStatement(sql2);
                                ps.setBigDecimal(1, balance2);
                                ps.setString(2, cardNo2);
                                ps.executeUpdate();
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
                }
            } else {
                store = "Không tồn tại loại tài khoản này!";
                code.add(store);
            }
            //store history
            PreparedStatement ps;
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp curent_Date = new java.sql.Timestamp(new java.util.Date().getTime());
            String sqlhistory = "INSERT INTO History(card_no1,card_no2,type,money_no,tran_date,code) VALUES(?,?,?,?,?,?)";
            ps = server.MyConnection.getConnection().prepareStatement(sqlhistory);
            ps.setString(1, value);
            ps.setString(2, cardNo2);
            ps.setInt(3, 2);
            ps.setBigDecimal(4, amount);
            ps.setTimestamp(5, curent_Date, cal);
            ps.setString(6, store);
            ps.executeUpdate();
            return code;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<String> withdraw(String cardNo, BigDecimal amount) throws RemoteException, SQLException {
        String value = getValue(cardNo);
        if (value != null) {
            //store code
            ArrayList<String> code = new ArrayList<>();
            String store = "Rút tiền thành công!";
            Account acc = getAccount(cardNo);
            if (amount.compareTo(new BigDecimal("50000")) == 0 || amount.compareTo(new BigDecimal("50000")) == 1) {

                if (amount.compareTo(acc.getBalance()) == 1) {
                    code.add("Lỗi,Số tiền lớn hơn số dư của bạn!");
                    // store = "Error,Amount is greater than your balance!";
                } else {
                    if (!amount.remainder(new BigDecimal("5000")).equals(BigDecimal.ZERO)) {
                        code.add("Lỗi,số tiền phải là bội của 5");
                    } else {
                        BigDecimal balance = acc.getBalance().subtract(amount);
                        String sql = "update Account set balance =? where card_no =?";
                        PreparedStatement ps;
                        ps = server.MyConnection.getConnection().prepareStatement(sql);
                        ps.setBigDecimal(1, balance);
                        ps.setString(2, value);
                        ps.executeUpdate();
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
            ps.setString(1, acc.getCard_no());
            ps.setString(2, acc.getCard_no());
            ps.setInt(3, 1);
            ps.setBigDecimal(4, amount);
            ps.setTimestamp(5, curent_Date, cal);
            ps.setString(6, store);
            ps.executeUpdate();
            return code;
        }
        return null;
    }

    //check password
    public Boolean checkpass(String plaintext, String card_no) throws SQLException {
        String hashed = "";
        String sql = "select pin from Account where card_no =?";
        PreparedStatement ps;
        ps = server.MyConnection.getConnection().prepareStatement(sql);
        ps.setString(1, card_no);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            hashed = rs.getString(1);
        }
        if (BCrypt.checkpw(plaintext, hashed)) {
            return true;
        } else {
            return false;
        }

    }
    //change pass

    private void changepassSQL(String card_no, String newpin) {
        String hashed = BCrypt.hashpw(newpin, BCrypt.gensalt(12));
        try {
            String sql = "update Account set pin =? where card_no =?";
            PreparedStatement ps;
            ps = server.MyConnection.getConnection().prepareStatement(sql);
            ps.setString(1, hashed);
            ps.setString(2, card_no);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BannImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<String> changepass(String cardNo, String oldpass, String newpass) throws RemoteException, SQLException {
        String value = getValue(cardNo);
        if (value != null) {
            ArrayList<String> code = new ArrayList<>();
            if (checkpass(oldpass, value)) {
                if (!newpass.equals("old")) {
                    changepassSQL(value, newpass);
                }
            } else {
                code.add("Mật khẩu cũ không đúng!");
            }
            return code;
        }
        return null;
    }

    @Override
    public void changeStatus(String cardNo) throws RemoteException {
        String value = getValue(cardNo);
        if (value != null) {
            try {
                String sqlLogin = "update Account set status =? where card_no =?";
                PreparedStatement ps;
                ps = server.MyConnection.getConnection().prepareStatement(sqlLogin);
                ps.setInt(1, 0);// Not login
                ps.setString(2, value);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(BannImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
