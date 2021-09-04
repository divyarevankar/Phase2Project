package com.simplilearn.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
	
	private Connection connection;
	
	public DbManager(String userName, String password, String dbUrl) throws ClassNotFoundException, SQLException {
		// Step 1 : Registering driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// Step 2:  Get connection object
		this.connection = DriverManager.getConnection(dbUrl, userName, password);
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	

}
