package cserver;

import java.rmi.RemoteException;

import remote.Bank;
import sercurity.Des;
import sql.connect.Msql;

public class Call implements Bank {
	/**
	 * author amneiht
	 */
	Msql db;
	Des des;
	static final int session = 1000 * 60 * 5;// 5 p

	
	// a=pass:token:date+time
	public String login(String a, String id) throws RemoteException {
		String key = Msql.getkey(id);
		if (key == null)
			return "khong ton tai tai khoan";
		des = new Des(key);
		String rep = des.decrypt(a);
		String[] sp = rep.split(":");
		if (sp.length != 3)
			return "ban ma bi thay doi";
		long millis = System.currentTimeMillis();
		try {
			long ds = Long.parseLong(sp[2]);
			if ( millis-ds > session)
				return "tan cong phat lai";
		} catch (Exception e) {
			return "false";
		}
		db = new Msql(id, sp[0]);
//		int token=Integer.parseInt(sp[1])+1;
//		String s=token+":"+sp[2];
		return "true";
	}

	@Override
	// chua nghi ra
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
