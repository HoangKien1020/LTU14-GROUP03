package Server;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

/**
 *
 * @author HK
 */
public class RMIServer {

	public static void main(String[] args) {

		String host = "localhost";
		try {
			setSettings();
			BannImpl call = new BannImpl();
			LocateRegistry.createRegistry(8888, new SslRMIClientSocketFactory(),
					new SslRMIServerSocketFactory(null, null, true));
			Registry registry = LocateRegistry.getRegistry(host, 8888, new SslRMIClientSocketFactory());

			registry.bind("Hello", call);
			System.out.println(">>>>>INFO: RMI Server started!!!!!!!!");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static void setSettings() {
		String pass = "123456";
		System.setProperty("javax.net.ssl.debug", "all");
		System.setProperty("javax.net.ssl.keyStore", "/home/rc/Desktop/1ShareVmware/ptpmpt/LTU14-GROUP03/sslRmi/src/key/serverKey/server.keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", pass);
		System.setProperty("javax.net.ssl.trustStore", "/home/rc/Desktop/1ShareVmware/ptpmpt/LTU14-GROUP03/sslRmi/src/key/serverKey/server.truststore");
		System.setProperty("javax.net.ssl.trustStorePassword", pass);
	}
}
