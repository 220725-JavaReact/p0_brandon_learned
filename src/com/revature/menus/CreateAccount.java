package com.revature.menus;

import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.client.UIUXBusinessLogic;
import com.revature.models.Customer;
import com.revature.util.Logger;
import com.revature.util.Logger.LogLevel;

import DataLayer.CustomerDAO;
import DataLayer.DAO;

public class CreateAccount {

	public static void createAccount(Scanner scanner) {
		DAO<Customer> customerDao = new CustomerDAO();
		boolean isRunning = true;
		String firstName = "";
		String lastName = "";
		String username = "";
		String password = "";
		String email = "";
		
		while(isRunning) {
			
			System.out.println(UIUXBusinessLogic.createBanner("CUSTOMER REGISTRY"));

			System.out.println("\nPlease enter a First Name. (Cannot contain Spaces, Numbers, or Special Characters)");
			while(firstName.equals("")) {
				String attemptName =  scanner.nextLine();
				firstName = BusinessLogic.verifyName(attemptName);
			}
			
			System.out.println("\nPlease enter a Last Name. (Cannot contain Spaces, Numbers, or Special Characters)");
			while(lastName.equals("")) {
				String attemptName =  scanner.nextLine();
				lastName = BusinessLogic.verifyName(attemptName);
			}
			
			System.out.println("\nPlease enter a Username. (Cannot contain Spaces, Special Characters, and Must Be \nBetween 8-16 Characters Long)");
			while(username.equals("")) {
				String attemptName =  scanner.nextLine();
				username = BusinessLogic.verifyUsername(attemptName);
			}
			
			System.out.println("\nPlease enter a Password. (Cannot contain Spaces and Must Be Between 8-20 \nCharacters Long. Passwords are case sensitive.)");
			while(password.equals("")) {
				String attemptPassword =  scanner.nextLine();
				password = BusinessLogic.verifyPassword(attemptPassword);
			}
			
			System.out.println("\nPlease enter an Email Address.");
			while(email.equals("")) {
				String attemptEmail =  scanner.nextLine();
				email = BusinessLogic.verifyEmail(attemptEmail);
			}
			
			System.out.println("\nName: " + firstName + " " + lastName 
					+"\nUsername: " + username
					+"\nPassword: " + password 
					+"\nEmail: " + email
					+"\nDoes this look correct to you? \n[Y/N]"
					+ "\n" + UIUXBusinessLogic.dashes());
			
			switch (scanner.nextLine()) {
			case "Y":	
			case "y":
				isRunning = false;
				break;
			case "N":
			case "n":
				firstName = "";
				lastName = "";
				username = "";
				password = "";
				email = "";
			default:
				break;
			}
		}
		
		//this should be altered for when database is implemented
		Customer customer = new Customer(firstName, lastName, username, password, email);
		customerDao.addInstance(customer);
		System.out.println(UIUXBusinessLogic.createSpaceBanner("User " + customer.getUsername() + " successfully created!"));
		Logger.getLogger().log(LogLevel.info, "\nNew User Created: " + customer.toString() + "\n");
		
		//login as customer
		
		
	}
}
