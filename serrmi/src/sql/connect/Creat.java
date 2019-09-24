package sql.connect;

import java.sql.Connection;
import java.sql.Statement;

public class Creat {
	public static void main(String[] args) {
		try {
			Connection con = Msql.getDConn();
			String sql = "CREATE DATABASE IF NOT EXISTS " + Conn.nsql
					+ " CHARACTER SET utf8 COLLATE utf8_vietnamese_ci;";
			Statement stmt = con.createStatement();
			stmt.execute(sql);
			sql = "CREATE TABLE IF NOT EXISTS " + Conn.nsql + "." + Conn.table
					+ "( sothe VARCHAR(20) NOT NULL , name VARCHAR(50) NOT NULL , pass VARCHAR(40) NOT NULL , tk VARCHAR(70) NOT NULL , khoa VARCHAR(70) NOT NULL , PRIMARY KEY (sothe)) ENGINE = InnoDB;";
			stmt.execute(sql);
			con.close();
			Insert.ins();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
