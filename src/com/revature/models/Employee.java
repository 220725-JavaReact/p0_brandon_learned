package com.revature.models;

public class Employee {

	String username;
	String password;
	
	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}

	
	
		//view all customers
	//view all orders
	//view orders of specific customers
	//alter the orders
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "Employee Username: " + username + "\nPassword: " + password + "\n--------------------";
	}
	

	
	
	
}
