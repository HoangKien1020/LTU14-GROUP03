package client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.Bank;
import sercurity.Des;

public class Atm {
	static String host = "localhost";
	static BufferedReader reader;
	static String line;
	
			
	public static void main(String[] args) {
		Registry registry;
		long count=0;
		try {
			reader = new BufferedReader(new FileReader("atmCard"));
			line = reader.readLine();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		line="123456:aaaaaaaa";
		
		String[] cardData = line.split(":");// id:passwd

		if ( cardData.length != 2)
		{
			System.out.println("Loi dinh dang the");
			System.exit(0);
		}
		try {
			registry = LocateRegistry.getRegistry(host, 8888);
			Bank stub = (Bank) registry.lookup("Hello");
			String ap = stub.login(genEncData(count,cardData[1],"123456"), cardData[0]);
			System.out.println(ap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static String genEncData(long count, String encPasswd, String userPasswd) {
		String sen = count + ":" + userPasswd ;
		return new Des(encPasswd).encrypt(sen);
	}
}
