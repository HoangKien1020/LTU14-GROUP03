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
			// LocateRegistry.createRegistry(8888);
			Registry registry = LocateRegistry.createRegistry(8888);
			Bank bk=(Bank) UnicastRemoteObject.exportObject(call, 0);  
			registry.bind("Hello",bk);  
			System.out.println("start");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}

}
