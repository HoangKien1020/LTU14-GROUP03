/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import bean.Calculator;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author HK
 */
// Implementing the remote interface 
// Exporting the object of implementation class  
// (here we are exporting the remote object to the stub)
public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

    public CalculatorImpl() throws RemoteException {
    }
    // Implementing the interface method 

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

}
