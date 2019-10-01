package cserver;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

public class Ser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			setSettings();
			Call call = new Call();
			LocateRegistry.createRegistry(8888, new SslRMIClientSocketFactory(),
					new SslRMIServerSocketFactory(null, null, true));
			Registry registry = LocateRegistry.getRegistry("amneiht", 8888, new SslRMIClientSocketFactory());
			UnicastRemoteObject.exportObject(call, 0);
			registry.bind("Hello", call);
			System.out.println("start");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}

	private static void setSettings() {
		String path = new File("").getAbsolutePath();
		String pass = "dangcongcan"; // ko duoc tu tien thay doi
		System.setProperty("javax.net.ssl.debug", "all");
		System.setProperty("javax.net.ssl.keyStore", path + "/ssl/server/KeyStore.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", pass);
		System.setProperty("javax.net.ssl.trustStore", path + "/ssl/server/truststore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", pass);
	}
}
