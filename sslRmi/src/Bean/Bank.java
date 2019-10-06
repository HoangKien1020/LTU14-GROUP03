package Bean;

import java.rmi.Remote;
import java.rmi.RemoteException;
// Creating Remote interface for our application 
public interface Bank extends Remote {

    public String login(String cardNo, String PIN, int count) throws RemoteException;

}