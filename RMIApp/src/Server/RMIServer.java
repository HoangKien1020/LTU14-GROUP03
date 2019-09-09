/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author HK
 */
public class RMIServer {

    public static void main(String[] args) {
        try {
            System.out.println(">>>>>INFO: RMI Server started!!!!!!!!");
            // Instantiating the implementation class 
            CalculatorImpl impl = new CalculatorImpl();
            // Binding the remote object (stub) in the registry 
            LocateRegistry.createRegistry(6789);
            Naming.bind("rmi://192.168.1.8:6789/RMI", impl);

        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (AlreadyBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

    }

}
