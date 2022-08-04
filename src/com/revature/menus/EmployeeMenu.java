package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Order;
import com.revature.models.StoreFront;
import com.revature.tempdatastorage.TemporaryStorage;

import DataLayer.CustomerDAO;
import DataLayer.StoreFrontsDAO;

public class EmployeeMenu {

	public static void employeeMenu(Scanner scanner, Employee employee) {
		
		StoreFrontsDAO storeFrontDao = new StoreFrontsDAO();
		CustomerDAO customerDao = new CustomerDAO();
		ArrayList<StoreFront> storeFronts = storeFrontDao.getAll();
		ArrayList<Customer> customersToView = customerDao.getAll();
		
		StoreFront storeFront = null;
		

		boolean isRunning = true;
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Success! Welcome, " + employee.getUsername());
		System.out.println("-------------------------------------------------------------------------------");
		
		while(isRunning) {
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] Update Good Duckin' Duckies Stock"
					+ "\n[2] Update Discount Duckies Stock"
					+ "\n[3] Update Duckie Dynasty Stock"
					+ "\n[4] View Previous Orders Placed By Customers"
					+ "\n[5] Logout" );
			System.out.println("-------------------------------------------------------------------------------");

			
			switch (scanner.nextLine()) {
			case "1":	
				//update good duckin duckies
				System.out.println("Not implemented yet...");
				break;
			case "2":
				//update discount duckies
				System.out.println("Not implemented yet...");
				break;
			case "3":
				//update duckie dynasy
				System.out.println("Not implemented yet...");
				break;
			case "4":
				EmployeeExtendedMenu.employeeExtendedMenu(scanner, employee, customersToView);
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
