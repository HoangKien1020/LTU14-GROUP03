/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HK
 */
// Creating Remote interface for our application 
public interface Bank extends Remote {
    //to check,ignore it.
    public String logintest(String cardNo, String PIN, int count) throws RemoteException;
    //beginning
    public ArrayList<String> login(String cardNo, String PIN, int count) throws RemoteException, SQLException;

    public Account getAccount(int id, int count) throws RemoteException, SQLException;

    public User getUser(int id, int count) throws RemoteException, SQLException;

    public ArrayList<String> inquiry(int id, int count) throws RemoteException, SQLException;

    public ArrayList<String> transfer(int id1, int id2, double amount, int count) throws RemoteException, SQLException;

    public ArrayList<String> withdraw(int id, double amount, int count) throws RemoteException, SQLException;

    public void doimatkhau(int id, String newpass, int count) throws RemoteException, SQLException;

}
