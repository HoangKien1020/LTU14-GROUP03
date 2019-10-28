/*
 * To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

/**
 *
 * @author HK
 */
public class RMIServer {

    public static  void init() {
        String host = "localhost";
        try {
            setSettings();
            //System.setSecurityManager(new SecurityManager());
            BannImpl impl = new BannImpl();
            //System.setProperty("java.rmi.server.hostname", host);
            LocateRegistry.createRegistry(8888, new SslRMIClientSocketFactory(),
                    new SslRMIServerSocketFactory(null, null, true));
            Registry registry = LocateRegistry.getRegistry(host, 8888, new SslRMIClientSocketFactory());
            registry.rebind("bank", impl);
            System.out.println(">>>>>INFO: RMI Server started!!!!!!!!");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    private static void setSettings() {
        String path = new File("").getAbsolutePath();
        String pass = "123456";
        System.setProperty("javax.net.ssl.debug", "all");
        System.setProperty("javax.net.ssl.keyStore", path + "/sslkey/serverKey/server.keystore");
        System.setProperty("javax.net.ssl.keyStorePassword", pass);
        System.setProperty("javax.net.ssl.trustStore", path + "/sslkey/serverKey/server.truststore");
        System.setProperty("javax.net.ssl.trustStorePassword", pass);
    }
}
