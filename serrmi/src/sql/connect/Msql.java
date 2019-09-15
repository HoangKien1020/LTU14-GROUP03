package sql.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sercurity.Des;
import sercurity.Md5;

public class Msql {
	public static Connection getConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connectionURL = "jdbc:mysql://" + Conn.host + ":" + Conn.port + "/" + Conn.nsql;
			Connection conn = DriverManager.getConnection(connectionURL, Conn.user, Conn.pass);
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public static boolean check(String a, String b, Connection con) {
		try {
			String sql = "select * from user where sothe = ? and pass = ? ;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, a);
			ps.setString(2, Md5.hashStr(b));
			ResultSet rs = ps.executeQuery();
			rs.beforeFirst();
			boolean sa = rs.next();
			rs.close();
			ps.close();
			return sa;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public static Long getMoney(String a, String b, Connection con) {
		try {
			String sql = "select * from user where sothe = ? and pass = ? ;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, a);
			ps.setString(2, Md5.hashStr(b));
			ResultSet rs = ps.executeQuery();
			rs.beforeFirst();
			rs.next();// den hang dau tien
			String dta = rs.getString(4);
			rs.close();
			ps.close();
			return Long.parseLong(Des.decrypt(dta));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1l;
		}
	}

	public static void SetMoney(String a, String b, int c, Connection con) {
		try {
			String sql = "update user set tk= ? where sothe = ? and pass= ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(2, a);
			ps.setString(3, Md5.hashStr(b));
			String k = c + "";
			k = Des.encrypt(k);
			ps.setString(1, k);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
