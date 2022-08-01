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
	
	
	
	@Override
	public String toString() {
		return "Employee Username: " + username + "\nPassword: " + password + "\n--------------------";
	}
	

	
	
	
}
