package com.employee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection dbConn(){
		//Path of the URL to connect to database
		String url = "jdbc:mysql://127.0.0.1:3307/employee";
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
}
