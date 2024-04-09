package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		String user = "root";
		String pass = "09096712789";
		String url = "jdbc:mysql://localhost:3306/bookstore";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println(conn);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}
//	public static void main(String[] args) throws SQLException {
//		DatabaseUtil conn =new DatabaseUtil();
//		conn.getConnection();
//	}
}
