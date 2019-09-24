package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank extends Remote {
	public int hello() throws RemoteException;

	public String login(String s, String id) throws RemoteException;

	public int ark(String b) throws RemoteException;
}
