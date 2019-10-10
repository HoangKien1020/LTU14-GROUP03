/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import bean.Bank;
import bean.Account;
import bean.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HK
 */
// Implementing the remote interface 
// Exporting the object of implementation class  
// (here we are exporting the remote object to the stub)
public class BannImpl extends UnicastRemoteObject implements Bank {

    String NoCard = "1234567890";
    String Passwd = "909090";
    int count = 2;

    public BannImpl() throws RemoteException {
    }
    // Implementing the interface method 

    @Override
    public String logintest(String cardNo, String PIN, int count) throws RemoteException {

        if (!cardNo.equals(NoCard)) {
            return "Khong co ma the";
        } else if (!PIN.equals(Passwd)) {
            return "Nhap sai mat khau";
        } else if (count <= this.count) {
            return "Khong phat lai thong diep";
        } else {
            return "Dang nhap thanh cong";
        }

    }

    @Override
    public ArrayList<String> login(String cardNo, String PIN, int count) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public Account getAccount(int id, int count) throws RemoteException, SQLException {
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
    public ArrayList<String> transfer(int id1, int id2, double amount, int count) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public ArrayList<String> withdraw(int id, double amount, int count) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public void doimatkhau(int id, String newpass, int count) throws RemoteException, SQLException {

    }

}
