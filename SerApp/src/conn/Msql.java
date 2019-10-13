package conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sql.security.BCrypt;
import sql.table.Acc;
import sql.table.His;
import sql.table.Use;

public class Msql {
	public static boolean insert(String id, String name, String pin, String address, String card, String phone,
			String money, Connection con) {
		try {
			if (check(card, con))
				return false;
			String sql = "INSERT  INTO " + Use.name + " values (?,?,?,?) ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(4, phone);
			ps.executeUpdate();
			ps.close();
			sql = "INSERT  INTO " + Acc.name + " value(?,?,?,?) ";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, card);
			ps.setString(3, BCrypt.hashpw(pin, BCrypt.gensalt()));
			ps.setString(4, money);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	public static String[][] getUser(Connection con) {
		try {
			String[][] ret = null;
			String sql = "select * from " + Use.name;
			ResultSet rs = con.createStatement().executeQuery(sql);
			rs.last();
			int h = rs.getRow();
			ret = new String[h][4];
			rs.first();
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < 4; j++) {
					ret[i][j] = rs.getString(j + 1);
				}
				rs.next();
			}
			rs.close();
			return ret;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public static String[][] monitor(Connection con, int id, String... arg) {
		try {
			String[][] ret = null;
			String sql = null;
			switch (id) {
			case 0:
				sql = " select " + His.id1 + "," + His.id2 + "," + His.tien + "," + His.lydo +  "," +His.date+ " from " + His.name
						+ " where " + His.id1 + " = " + arg[0];
				break;
			case 1:
				sql = " select " + His.id1 + "," + His.id2 + "," + His.tien + "," + His.lydo + "," +His.date+ " from " + His.name
						+ " where " + His.id2 + " = " + arg[1];
				break;
			default:
				sql = " select " + His.id1 + "," + His.id2 + "," + His.tien + "," + His.lydo +  "," +His.date+ " from " + His.name
						+ " where " + His.id1 + " = " + arg[0] + " and " + His.id2 + " = " + arg[1];
				break;
			}
			ResultSet rs = con.createStatement().executeQuery(sql);
			rs.last();
			int h = rs.getRow();
			int p=5;
			ret = new String[h][p];
			rs.first();
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < p; j++) {
					ret[i][j] = rs.getString(j + 1);
				}
				rs.next();
			}
			rs.close();
			return ret;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public static boolean check(String card, Connection con) {
		try {
			String sql = "select * from " + Acc.name + " where " + Acc.card + " = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, card);
			ResultSet rs = ps.executeQuery();
			rs.beforeFirst();
			boolean sa = rs.next();
			rs.close();
			ps.close();
			return sa;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

}
