/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Remote.Bank;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;


public class BankImpl extends UnicastRemoteObject implements Bank {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BankImpl() throws RemoteException {
    }
    // Implementing the interface method 

    @Override
    public String login(String cardID, String pin) throws RemoteException {
        Boolean isValidLogin = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bank", "root", "123456");
            MySQL sql = new MySQL();

            isValidLogin = sql.verifyLogin(connection, cardID, pin);

        } catch (Exception e){
            System.out.println(e);
        }
        if (isValidLogin){
            System.out.println("New Login ID: " + cardID);
            JWToken JWToken = new JWToken();
            return JWToken.signToken(cardID);
        } else{
            return null;
        }

    }

    @Override
    public Boolean verifyToken(String token) {
        JWToken JWToken = new JWToken();
        return JWToken.verifyToken(token);
    }

    @Override
    public String getBalance(String cardID, String token) throws RemoteException {
        String accountBalance = "";
        JWToken JWToken = new JWToken();
        Boolean isTokenValid = JWToken.verifyToken(token);
        if(isTokenValid){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/bank", "root", "123456");
                MySQL sql = new MySQL();
                accountBalance = sql.getCardInfo(connection, cardID, "balance");
            } catch (Exception e){
                System.out.println(e);
            }
        } else {
            return "[Error] Unauthorized";
        }
        return accountBalance;
    }

    @Override
    public String createAccount(String name, String cardID, String password) throws  RemoteException {
        PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
        String hashedPassword = passwordEncryptor.encrypt(password);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bank", "root", "123456");
            MySQL sql = new MySQL();

            sql.insertNewCard(connection, name, cardID, hashedPassword);
        } catch (Exception e){
            System.out.println(e);
        }


        return hashedPassword;
    }


}
