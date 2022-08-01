package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.tempdatastorage.TemporaryStorage;

public class CustomerLogin {

	public static void customerLogin(Scanner scanner) {
		
		ArrayList<Customer> customersForLogin = TemporaryStorage.customers;
		boolean isRunning = true;
		
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("CUSTOMER LOGIN:");
		System.out.println("-------------------------------------------------------------------------------");

		while(isRunning) {
			
			System.out.println("Input Username: ");
			String attemptUsername = scanner.nextLine();
			String targetPassword = "";
			
			for(Customer customer : customersForLogin) {
				if(customer.getUsername().equals(attemptUsername)) {
					targetPassword = customer.getPassword();
				}
				
				if(!targetPassword.equals("")) {
					System.out.println("Input Password");
					if(scanner.nextLine().equals(targetPassword)) {
						CustomerMenu.customerMenu(scanner, customer);
						isRunning = false;
						break;
					} else {
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Incorrect Username or Password");
						System.out.println("-------------------------------------------------------------------------------");
						isRunning = false;
						break;
					}
				}
			}
			
			if(isRunning) {
				System.out.println("Input Password");
				scanner.nextLine();
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Incorrect Username or Password");
				System.out.println("-------------------------------------------------------------------------------");

			}			
			isRunning = false;
		}
		
	}
}
