/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author HK
 */
// Creating Remote interface for our application 
public interface Calculator extends Remote {

    public int add(int a, int b) throws RemoteException;
}
