package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.Bank;
import sercurity.Des;

public class Atm {
	public static void main(String[] args) {
		// demo thong tin = 20158034:dang cong can:meo nho:aabbccdd
		// gui di
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry("localhost", 8888);
			Bank stub = (Bank) registry.lookup("Hello");
			String ap=stub.login(test(), "20158034");
			System.out.println(ap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Looking up the registry for the remote object

	}
	
	static String test()
	{
		long millis = System.currentTimeMillis();
		int d=18;   // random
		String pass="meo nho";
		String key="aabbccdd";   // key value in in put file
		String sen=pass+":"+d+":"+millis;
		return new Des(key).encrypt(sen);
				
	}
	static String test2()// kiem tra tan cong phat lai
	{
		long millis = System.currentTimeMillis()-1000*60*7;
		int d=18;   // random
		String pass="meo nho";
		String key="aabbccdd";   // key value in in put file
		String sen=pass+":"+d+":"+millis;
		return new Des(key).encrypt(sen);
				
	}
}
