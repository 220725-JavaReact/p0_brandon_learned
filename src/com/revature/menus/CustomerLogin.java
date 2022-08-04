package com.revature.menus;

import java.util.Scanner;

import com.revature.models.Customer;

import DataLayer.CustomerDAO;
import DataLayer.DAO;

public class CustomerLogin {

	public static void customerLogin(Scanner scanner) {
		
		DAO<Customer> customerDao = new CustomerDAO();
		boolean isRunning = true;
		
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("CUSTOMER LOGIN:");
		System.out.println("-------------------------------------------------------------------------------");

		while(isRunning) {
			
			System.out.println("Input Username: ");
			String attemptUsername = scanner.nextLine();
			Customer customer = customerDao.getByName(attemptUsername);
			System.out.println("Input Password");
			String attemptPassword = scanner.nextLine();
			
			
			if(customer == null) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Incorrect Username or Password");
				System.out.println("-------------------------------------------------------------------------------");
				isRunning = false;
				break;
			}

			String targetPassword = customer.getPassword();
			if(!targetPassword.equals(attemptPassword)) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Incorrect Username or Password");
				System.out.println("-------------------------------------------------------------------------------");
				isRunning = false;
				break;
			}
			
			CustomerMenu.customerMenu(scanner, customer);
					
			isRunning = false;
		}
		
	}
}
