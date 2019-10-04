package Client;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.rmi.ssl.SslRMIClientSocketFactory;

import Bean.Bank;

public class  RMIClient {
	static String host = "localhost";

	public static void main(String[] args) {
		if (args.length > 0) {
			host = args[0];
		}
		Registry registry;
		try {
			setSettings();
			registry = LocateRegistry.getRegistry(host, 8888, new SslRMIClientSocketFactory());
			Bank c = (Bank) registry.lookup("Hello");
			String NoCard = "1234567890";
			String Passwd = "909090";
			int count = 2;
			System.out.println("Result is :" + c.login(NoCard, Passwd, count));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void setSettings() {
		String path = new File("").getAbsolutePath();
		String pass = "dangcongcan"; // ko duoc tu tien thay doi
		System.setProperty("javax.net.ssl.debug", "all");
		System.setProperty("javax.net.ssl.keyStore", path + "/ssl/client/KeyStore.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", pass);
		System.setProperty("javax.net.ssl.trustStore", path + "/ssl/client/truststore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", pass);
	}

}
