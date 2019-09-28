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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 */
public class RMIServer {

    public static void main(String[] args) {
        try {
            System.out.println(">>>>>INFO: RMI Server started!!!!!!!!");
            // Instantiating the implementation class

            BankImpl auth = new BankImpl();

            // Binding the remote object (stub) in the registry 
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://127.0.0.1:1099/RMI", auth);


        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (AlreadyBoundException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bank", "root", "123456");
        } catch (Exception e) {
            System.out.println(e);
        }


    }

}
