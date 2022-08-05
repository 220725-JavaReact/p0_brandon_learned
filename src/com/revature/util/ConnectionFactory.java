package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static ConnectionFactory connectionFactory;
	
	static {
		try { //make sure postgresql driver is ready to use
			Class.forName("org.postgres1l.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private ConnectionFactory() {
		
	}
	
	public static ConnectionFactory getInstance() {
		//lazy loading - create instance only when needed
		if(connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
	
	// factories are characterized by some method that contains logic for object creation
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(System.getenv("db_url"), 
					System.getenv("db_username"), 
					System.getenv("db_password"));
			return connection;
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Something went wrong");
		}
		return connection;
	}
}
