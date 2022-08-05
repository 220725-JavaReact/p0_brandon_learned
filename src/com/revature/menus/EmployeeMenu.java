package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.client.EmployeeSpecificBusinessLogic;
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
			System.out.println("[1] Update Store Product"
					+ "\n[2] Update Customer Orders"
					+ "\n[3] Delete Customer"
					+ "\n[4] Logout" );
			//other things to maybe do: create new stores/ create new products and allocated them to store
			System.out.println("-------------------------------------------------------------------------------");

			
			switch (scanner.nextLine()) {
			case "1":	
				//update a store
				System.out.println("Not implemented yet...");
				break;
			case "2":
				String response = null;
				Customer customer = EmployeeSpecificBusinessLogic.selectCustomer(scanner);
				Order order = EmployeeSpecificBusinessLogic.chooseCustomerOrders(scanner, employee, customer);
				if(order != null) {
					response = EmployeeSpecificBusinessLogic.alterCustomerOrderType(scanner, order);
					if(response.equals("add")) {
						EmployeeSpecificBusinessLogic.addToOrder(scanner, order, employee, customer);
					} else if (response.equals("remove")) {
						EmployeeSpecificBusinessLogic.removeFromOrder(scanner, order, employee);
					} else if (response.equals("delete")) {
						customerDao.deleteOrder(customer, order);
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Order:");
						order.printOrder();
						System.out.println("was deleted from " + customer.getUsername() + "'s orders");
						System.out.println("-------------------------------------------------------------------------------");

					}
				}
				
				break;
			case "3":
				//choose a customer and delete them (line items and orders too)
				break;
			case "4":
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
