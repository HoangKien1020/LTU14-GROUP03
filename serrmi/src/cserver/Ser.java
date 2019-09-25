package cserver;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import remote.Bank;

public class Ser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Call call = new Call();
			Registry registry = LocateRegistry.createRegistry(8888);
			 UnicastRemoteObject.exportObject(call, 0);  
			registry.bind("Hello",call);  
			System.out.println("start");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}

}
