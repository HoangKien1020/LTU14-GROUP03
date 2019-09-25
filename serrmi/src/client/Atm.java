package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.Bank;
import sercurity.Des;

public class Atm {
	static String host = "localhost";

	public static void main(String[] args) {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(host, 8888);
			Bank stub = (Bank) registry.lookup("Hello");
			String ap = stub.login("1ddd78f1bf53ead82a5325a263ed427df0613ca2895e95003cd23dc65aedbd9e", "20158034");
			System.out.println(ap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//1ddd78f1bf53ead82a5325a263ed427df0613ca2895e95003cd23dc65aedbd9e
		// Looking up the registry for the remote object

	}

	static String test() {
		long millis = System.currentTimeMillis();
		int d = 18; // random
		String pass = "meo nho";
		String key = "aabbccdd"; // key value in in put file
		String sen = pass + ":" + d + ":" + millis;
		return new Des(key).encrypt(sen);

	}

	static String test2()// kiem tra tan cong phat lai
	{
		long millis = System.currentTimeMillis() - 1000 * 60 * 7;
		int d = 18; // random
		String pass = "meo nho";
		String key = "aabbccdd"; // key value in in put file
		String sen = pass + ":" + d + ":" + millis;
		return new Des(key).encrypt(sen);

	}
}
