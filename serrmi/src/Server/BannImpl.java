package Server;

import Bean.Bank;
import sercurity.Des;
import sql.connect.Msql;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author HK
 */
// Implementing the remote interface
// Exporting the object of implementation class
// (here we are exporting the remote object to the stub)
public class BannImpl extends UnicastRemoteObject implements Bank {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String NoCard = "1234567890";
	String Passwd = "909090";
	int count = 2;

	public BannImpl() throws RemoteException {
	}
	// Implementing the interface method

	@Override
	public String login(String cardNo, String PIN, int count) throws RemoteException {

		if (!cardNo.equals(NoCard)) {
			return "Khong co ma the";
		} else if (!PIN.equals(Passwd)) {
			return "Nhap sai mat khau";
		} else if (count <= this.count) {
			return "Khong phat lai thong diep";
		} else {
			return "Dang nhap thanh cong";
		}

	}

	Msql db;
	Des des;
	int ct;

	public String login(String a, String id) throws RemoteException {
		int session = 1000 * 10;
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

	public String login2(String id, String PIN, int count) throws RemoteException {
		String key = Msql.getkey(id);
		if (key == null)
			return "khong ton tai tai khoan";
		db = new Msql(id, PIN);
		if (!db.check())
			return "sai mat khau";
		ct = count;
		return "ket noi thanh cong";
	}

}