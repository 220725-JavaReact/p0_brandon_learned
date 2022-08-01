package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.tempdatastorage.TemporaryStorage;

public class CustomerLogin {

	public static void customerLogin(Scanner scanner) {
		
		//This can be deleted when database is implemented
		ArrayList<Customer> customers = new ArrayList<>();
		customers.add(new Customer("Brandon", "Learned", "blearned92", "password", "email@email.com"));
		customers.add(new Customer("Jon", "Duckett", "duckduckgoose", "password", "jon@email.com"));
		//---------------------------------------------
		
		
		boolean isRunning = true;
		
		while(isRunning) {
			
			System.out.println("Input Username: ");
			String attemptUsername = scanner.nextLine();
			String targetPassword = "";
			
			//Search is username exists in array
			for(Customer customer : customers) {
				if(attemptUsername.equals(customer.getUsername())) {
					targetPassword = customer.getPassword();
					System.out.println("Input Password");
					String attemptedPassword = scanner.nextLine();
					if(attemptedPassword.equals(targetPassword)) {
						CustomerMenu.customerMenu(scanner, customer);
						isRunning = false;
						break;
					} else {
						System.out.println("Incorrect Username or Password \n--------------------");
						isRunning = false;
						break;
					}
				} else {
					System.out.println("Input Password");
					scanner.nextLine();
					System.out.println("Incorrect Username or Password\n--------------------");
					isRunning = false;
					break;
				}
			}
			
		}
		
	}
}
