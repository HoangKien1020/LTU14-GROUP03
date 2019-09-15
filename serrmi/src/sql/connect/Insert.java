package sql.connect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import sercurity.Des;
import sercurity.Md5;

public class Insert {
	public static void main(String[] args) throws SQLException {
		Connection con = Msql.getConn();
		boolean ck=Msql.check("20158032", "pppp", con);
		System.out.println(ck);
		ck=Msql.check("20158034", "meo nho", con);
		System.out.println(ck);
		con.close();
	}
	public static void ins() {
		File sa = new File("data.txt");
		String s = sa.getAbsolutePath();
		List<String> h = getText(s);
		Connection con = Msql.getConn();
		String sql = "INSERT IGNORE INTO user(sothe,name,pass,tk) values (?,?,?,?) ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			for (String k : h) {
				String[] dt = k.split(":");
				ps.setString(1, dt[0]);
				ps.setString(2, dt[1]);
				ps.setString(3, Md5.hashStr(dt[2]));
				ps.setString(4, Des.encrypt(dt[3]));
				ps.executeUpdate();
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<String> getText(String s) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(s)));
			List<String> ret = new LinkedList<String>();
			String rd = in.readLine();
			while (rd != null) {
				ret.add(rd);
				rd = in.readLine();
			}
			in.close();
			return ret;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
