package com.revature.menus;

import java.util.Scanner;

import com.revature.client.UIUXBusinessLogic;
import com.revature.models.Employee;
import com.revature.util.Logger;
import com.revature.util.Logger.LogLevel;

public class EmployeeMenu {

	public static void employeeMenu(Scanner scanner, Employee employee) {		

		boolean isRunning = true;
		System.out.println(UIUXBusinessLogic.createSpaceBanner("Success! Welcome, " + employee.getUsername() + "!"));
		
		while(isRunning) {
			System.out.println(UIUXBusinessLogic.createBanner("EMPLOYEE MENU - " + employee.getUsername()));
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] Manage Current Stores" //maybe add sales trends
					+ "\n[2] Manage Customer Accounts" // search for customers, see their details, see their orders
					+ "\n[3] Create New Store (Not working as of yet)"
					+ "\n[4] Create New Products For Stores (Not working as of yet)"
					+ "\n[5] Logout" );
			//other things to maybe do: create new stores/ create new products and allocated them to store
			System.out.println(UIUXBusinessLogic.dashes());

			
			switch (scanner.nextLine()) {
			case "1":
				EmployeeStoreMenu.employeeStoreMenu(scanner, employee);
				break;
			case "2":
				EmployeeCustomerMenu.employeeCustomerMenu(scanner, employee);
				break;
			case "3":
				System.out.println("Under Maintainence");
				break;
			case "4":
				System.out.println("Under Maintainence");
				break;
			case "5":
				System.out.println(UIUXBusinessLogic.dashes());
				System.out.println("Logging Out of User " + employee.getUsername() + "'s account...");
				System.out.println(UIUXBusinessLogic.dashes());
				Logger.getLogger().log(LogLevel.info, "\nAdmin User: " + employee.getUsername() + " logged out of account\n");
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
