package client;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.rmi.ssl.SslRMIClientSocketFactory;

import remote.Bank;
import sercurity.Des;

public class Atm {
	static String host = "localhost";

	public static void main(String[] args) {
		Registry registry;
		try {
			setSettings();
			registry = LocateRegistry.getRegistry(host, 8888, new SslRMIClientSocketFactory());
			Bank stub = (Bank) registry.lookup("Hello");
			String ap = stub.login("20158034", "meo nho", 0);
			System.out.println(ap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void setSettings() {
		String path = new File("").getAbsolutePath();
		String pass = "dangcongcan"; // ko duoc tu tien thay doi
		System.setProperty("javax.net.ssl.debug", "all");
		System.setProperty("javax.net.ssl.keyStore", path+"/ssl/client/KeyStore.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", pass);
		System.setProperty("javax.net.ssl.trustStore", path+"/ssl/client/truststore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", pass);
	}

}
