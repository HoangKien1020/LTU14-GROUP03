package server;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author 1BestCsharp
 */
public class MyConnection {

    // create a function to connect with mysql database
    public static Connection getConnection() {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://192.168.1.2:3306/bank", "root","group03");
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, "Error connect to Database -connection ");
        }

        return con;
    }

}
