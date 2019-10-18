/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author HK
 */
public class MyConnection {
    // create a function to connect with mysql database

    public static Connection getConnection() {

        //String url = "jdbc:mysql://157.245.240.61/bank?user=group03&password=group03&useUnicode=true&characterEncoding=utf8";
        String url = "jdbc:mysql://192.168.216.129/bank?user=root&password=1997&useUnicode=true&characterEncoding=utf8";

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url);
            // System.out.println("Success");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error connect to Database -connection ");
        }

        return con;
    }

    public static void main(String[] args) {
        getConnection();
    }

}
