package com.revature.menus;

import java.util.Scanner;
import com.revature.models.Employee;

public class EmployeeMenu {

	public static void employeeMenu(Scanner scanner, Employee employee) {		

		boolean isRunning = true;
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Success! Welcome, " + employee.getUsername());
		System.out.println("-------------------------------------------------------------------------------");
		
		while(isRunning) {
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] Manage Current Stores"
					+ "\n[2] Manage Customer Accounts (Not working as of yet)"
					+ "\n[3] Create New Store (Not working as of yet)"
					+ "\n[4] Create New Products For Stores (Not working as of yet)"
					+ "\n[4] Logout" );
			//other things to maybe do: create new stores/ create new products and allocated them to store
			System.out.println("-------------------------------------------------------------------------------");

			
			switch (scanner.nextLine()) {
			case "1":
				EmployeeStoreMenu.employeeStoreMenu(scanner, employee);
				break;
			case "2":
				System.out.println("Under Maintainence");
				//EmployeeCustomerMenu.employeeCustomerMenu(scanner, employee);
				break;
			case "3":
				System.out.println("Under Maintainence");
				break;
			case "4":
				System.out.println("Under Maintainence");
				break;
			case "5":
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Logging Out of User " + employee.getUsername() + "'s account...");
				System.out.println("-------------------------------------------------------------------------------");
				isRunning = false;
				break;
			default:
				break;
			}
		
		//update stock for different stores
		//view a list of all customers
			//view their orders
				//change their orders
		
		}
		
	}
}
