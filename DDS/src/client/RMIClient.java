/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import bean.Bank;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.Scanner;
import javax.rmi.ssl.SslRMIClientSocketFactory;

/**
 *
 * @author HK
 */
public class RMIClient {

    static String host = "localhost";

    public static void main(String[] args) {
        if (args.length > 0) {
            host = args[0];
        }
        Registry registry;
        try {
            setSettings();
            registry = LocateRegistry.getRegistry(host, 8888, new SslRMIClientSocketFactory());
            Bank c = (Bank) registry.lookup("bank");
            String NoCard = "1234567890";
            String Passwd = "909090";
            int count = 3;
            System.out.println("Result is :" + c.logintest(NoCard, Passwd, count));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void setSettings() {
        String path = new File("").getAbsolutePath();
        String pass = "123456";
        System.setProperty("javax.net.ssl.debug", "all");
        System.setProperty("javax.net.ssl.keyStore", path + "/sslkey/clientKey/client.keystore");
        System.setProperty("javax.net.ssl.keyStorePassword", pass);
        System.setProperty("javax.net.ssl.trustStore", path + "/sslkey/clientKey/client.truststore");
        System.setProperty("javax.net.ssl.trustStorePassword", pass);
    }
}
