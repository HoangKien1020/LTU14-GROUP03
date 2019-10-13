package run;

import java.sql.Connection;

import conn.Connect;
import conn.Msql;

public class Test {
	public static void main(String[] args) {
		Connection con = Connect.getConn();
		String [][] ss=Msql.getUser(con);
		for(String[] s:ss)
		{
			for(String p:s)
			{
				System.out.print(p+" ");
			}
			System.out.println();
		}
	}
}
