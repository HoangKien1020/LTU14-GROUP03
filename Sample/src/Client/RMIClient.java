/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Bean.Bank;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author HK
 */
public class RMIClient {

    public static void main(String[] args) {
        try {
            // Looking up the registry for the remote object 
            Bank c = (Bank) Naming.lookup("rmi://localhost:6789/RMI");
            // Calling the remote method using the obtained object 
            //String NoCard="1234567890";
            //String Passwd="909090";
            Scanner hi = new Scanner(System.in);
            System.out.println("Input card number:");
            String NoCard = hi.nextLine();
            System.out.println("Input PIN:");
            String Passwd = hi.nextLine();

            int count = 2;

            System.out.println("Result is :" + c.login(NoCard, Passwd, count));

        } catch (NotBoundException ex) {

        } catch (MalformedURLException ex) {

        } catch (RemoteException ex) {

        }
    }
}
