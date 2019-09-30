/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author HK
 */
// Creating Remote interface for our application 
public interface Bank extends Remote {

    public String login(String cardNo, String PIN, int count) throws RemoteException;
}
