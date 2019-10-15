/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import bean.Bank;
import bean.Account;
import bean.User;
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
    public BannImpl() throws RemoteException {

    }
    // Implementing the interface method 

    @Override
    public String logintest(String cardNo, String PIN, int count) throws RemoteException {
        /*
        if (!cardNo.equals(NoCard)) {
            return "Khong co ma the";
        } else if (!PIN.equals(Passwd)) {
            return "Nhap sai mat khau";
        } else if (count <= this.count) {
            return "Khong phat lai thong diep";
        } else {
            return "Dang nhap thanh cong";
        }
        
         */
        return null;
    }

    @Override
    public ArrayList<String> login(String cardNo, String PIN, int count) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public Account getAccount(int id, int count) throws RemoteException, SQLException {
        String sql = "select * from Account where id=?";
        PreparedStatement ps;
        ps = server.MyConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Account(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getBigDecimal(4));
        }
        return null;
    }

    @Override
    public User getUser(int id, int count) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public ArrayList<String> inquiry(int id, int count) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public ArrayList<String> transfer(int id1, int id2, BigDecimal amount, int count) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public ArrayList<String> withdraw(int id, BigDecimal amount, int count) throws RemoteException, SQLException {
        //store code
        ArrayList<String> code = new ArrayList<>();
        String store = "Rút tiền thành công!";
        Account acc = getAccount(id, count);
        if (amount.compareTo(new BigDecimal("50000")) == 1) {

            if (amount.compareTo(acc.getBalance()) == 1) {
                code.add("Lỗi,Số tiền lớn hơn số dư của bạn!");
                // store = "Error,Amount is greater than your balance!";
            } else {
                if (!amount.remainder(new BigDecimal("5000")).equals(BigDecimal.ZERO)) {
                    code.add("Lỗi,số tiền phải là bội của 5");
                } else {
                    BigDecimal balance = acc.getBalance().subtract(amount);
                    String sql = "update Account set balance =? where id =?";
                    PreparedStatement ps;
                    ps = server.MyConnection.getConnection().prepareStatement(sql);
                    ps.setBigDecimal(1, balance);
                    ps.setInt(2, id);
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
        String sqlhistory = "INSERT INTO History(id1,id2,type,money_no,tran_date,code) VALUES(?,?,?,?,?,?)";
        ps = server.MyConnection.getConnection().prepareStatement(sqlhistory);
        ps.setInt(1, id);
        ps.setInt(2, id);
        ps.setInt(3, 1);
        ps.setBigDecimal(4, amount);
        ps.setTimestamp(5, curent_Date, cal);
        ps.setString(6, store);
        ps.executeUpdate();
        return code;
    }

    //check password
    public Boolean checkpass(String plaintext, int id) throws SQLException {
        String hashed = "";
        String sql = "select pin from Account where id =?";
        PreparedStatement ps;
        ps = server.MyConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, id);
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

    private void changepassSQL(int id, String newpin) {
        String hashed = BCrypt.hashpw(newpin, BCrypt.gensalt(12));
        try {
            String sql = "update Account set pin =? where id =?";
            PreparedStatement ps;
            ps = server.MyConnection.getConnection().prepareStatement(sql);
            ps.setString(1, hashed);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BannImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<String> changepass(int id, String oldpass, String newpass, int count) throws RemoteException, SQLException {
        ArrayList<String> code = new ArrayList<>();
        if (checkpass(oldpass, id)) {
            if (!newpass.equals("old")) {
                changepassSQL(id, newpass);
            }
        } else {
            code.add("Mật khẩu cũ không đúng!");
        }
        return code;
    }

}
