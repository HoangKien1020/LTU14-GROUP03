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

 //  public ArrayList<String> login(String numAccount,String cmnd,String pin) throws RemoteException,SQLException;
public String login(String id) throws RemoteException;
}
