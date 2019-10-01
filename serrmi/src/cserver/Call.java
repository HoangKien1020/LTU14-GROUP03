package cserver;
//phat lai ? 
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
	int ct;
	static final int session = 1000 * 10;
	//	Node no chi la cai ten thoi con trai , qui dinh thoi gian chenh lech giua noi gui va nhan
	// t dat ten la sesssion vi t thich the
	
	public String login(String a, String id) throws RemoteException {
		System.out.println(a);
		String key = Msql.getkey(id);
		if (key == null)
			return "khong ton tai tai khoan";
		des = new Des(key);
		String rep = des.decrypt(a);
		if (rep == null)
			return "ban ma bi thay doi";
		String[] sp = rep.split(":");
		if (sp.length != 3)
			return "ban ma bi thay doi";
		long millis = System.currentTimeMillis();
		try {
			long ds = Long.parseLong(sp[2]);
			if (millis - ds > session)
				return "tan cong phat lai";
		} catch (Exception e) {
			return "false";
		}
		db = new Msql(id, sp[0]);
		return "true";
	}

	public String login(String id, String PIN, int count) throws RemoteException {
		// TODO Auto-generated method stub
		String key = Msql.getkey(id);
		if (key == null)
			return "khong ton tai tai khoan";
		db = new Msql(id,PIN);
		if(!db.check()) return "sai mat khau";
		ct=count ;
		return "ket noi thanh cong";
	}

}
