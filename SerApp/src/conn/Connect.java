package conn;

import java.sql.Connection;
import java.sql.DriverManager;

import sql.table.Name;


public class Connect {
	public static Connection getConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connectionURL = "jdbc:mysql://" + Name.host + ":" + Name.port + "/" + Name.Db;
			Connection conn = DriverManager.getConnection(connectionURL, Name.user, Name.pass);
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
