/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.math.BigDecimal;
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

    public ArrayList<String> login(String cardNo, String PIN) throws RemoteException, SQLException;

    public Account getAccount(String cardNo) throws RemoteException, SQLException;

    public ArrayList<String> inquiry(String cardNo) throws RemoteException, SQLException;

    public ArrayList<String> transfer(String cardNo1, String cardNo2, BigDecimal amount) throws RemoteException, SQLException;

    public ArrayList<String> withdraw(String cardNo, BigDecimal amount) throws RemoteException, SQLException;

    public ArrayList<String> changepass(String cardNo, String oldpass, String newpass) throws RemoteException, SQLException;
    
    public void changeStatus(String cardNo) throws RemoteException;
}
