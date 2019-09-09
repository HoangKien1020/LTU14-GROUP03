/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import bean.Calculator;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author HK
 */
public class RMIClient {

    public static void main(String[] args) {
        try {
            // Looking up the registry for the remote object 
            Calculator c = (Calculator) Naming.lookup("rmi://192.168.1.8:6789/RMI");
            // Calling the remote method using the obtained object 
            System.out.println("Result is :" + c.add(80, 100));

        } catch (NotBoundException ex) {

        } catch (MalformedURLException ex) {

        } catch (RemoteException ex) {

        }
    }
}
