package cserver;

import java.rmi.RemoteException;

import remote.Bank;
import cserver.checkCount;
import sercurity.Des;
import sql.connect.Msql;

public class Call implements Bank {
	/**
	 * author amneiht
	 */
	Msql db;
	Des des;
	public String login(String a, String id) throws RemoteException {
		String key = Msql.getkey(id);
		checkCount checkCount = new checkCount();
		checkCount.start();
		int i=0;
		while(i<10000) {
			checkCount.takeCount(i);
			i++;
		}
		
		if (key== null) {
			return "k co tai khoan";
		}

		des = new Des(key);
		String rep = des.decrypt(a);
		if (rep == null)
			return "ban ma bi thay doi";
		String[] sp = rep.split(":");
		if (sp.length != 2)
			return "ban ma bi thay doi";
		
		db = new Msql(id, sp[1]);
		if (Msql.check(id,sp[1])) {
			System.out.println("doneeee");
		}
		else {
			System.out.println("saii");
		}
		return "thanh cong";
	}


	public int ark(String a) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hello() throws RemoteException {
		// TODO Auto-generated method stub
		return 10;
	}

}
