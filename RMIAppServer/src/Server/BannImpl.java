/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import bean.AES;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import bean.Bank;

/**
 *
 * @author HK
 */
// Implementing the remote interface 
// Exporting the object of implementation class  
// (here we are exporting the remote object to the stub)
public class BannImpl extends UnicastRemoteObject implements Bank {
 String NoCard="1234567890";
   String Passwd="909090";
   int count=2;
 //  String concat=NoCard+":"+Passwd+":"+(count+1);
    String encryptKEY="ABCDEFGHIJKLMNOP";
    public BannImpl() throws RemoteException {
    }
    // Implementing the interface method 

    @Override
    public String login(String id) throws RemoteException {
          System.out.println(AES.decrypt(id,encryptKEY));
          String split[]=AES.decrypt(id,encryptKEY).split(":");
          if(!split[0].equals(NoCard)) return "Khong co ma the";
          else if (!split[1].equals(Passwd)) return "Nhap sai mat khau";
          else if(Integer.parseInt(split[2]) <= (count+1)) return "Khong phat lai thong diep";
          else 
        return "Dang nhap thanh cong";
       
    }

    
  
    

 
}