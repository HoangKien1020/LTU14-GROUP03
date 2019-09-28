/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Creating Remote interface for our application 
public interface Bank extends Remote {

    public String login(String cardID, String pin) throws RemoteException;
    public Boolean verifyToken(String token) throws RemoteException;
    public String getBalance(String cardID, String token) throws RemoteException;
    public String createAccount(String name, String cardID, String password) throws RemoteException;
}
