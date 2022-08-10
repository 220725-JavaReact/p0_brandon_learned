package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.client.EmployeeSpecificBusinessLogic;
import com.revature.client.UIUXBusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Order;
import com.revature.models.StoreFront;

import DataLayer.CustomerDAO;
import DataLayer.OrderDAO;
import DataLayer.StoreFrontsDAO;

public class EmployeeCustomerMenu {

	public static void employeeCustomerMenu(Scanner scanner, Employee employee) {
		boolean isRunning = true;
		boolean isRunning2 = false;
		StoreFrontsDAO storeFrontDao = new StoreFrontsDAO();
		CustomerDAO customerDao = new CustomerDAO();
		OrderDAO orderDao = new OrderDAO();
		ArrayList<StoreFront> storeFronts = storeFrontDao.getAll();
		ArrayList<Customer> customersToView = customerDao.getAll();
		
		while(isRunning) {
			Customer customer = EmployeeSpecificBusinessLogic.customerSelecter(scanner);
			if (customer == null) {
				isRunning = false;
				break;
			}
			isRunning2 = true;
			
			while (isRunning2){
				System.out.println(UIUXBusinessLogic.createBanner("CUSTOMER ACCOUNT - " + customer.getUsername().toUpperCase()));
				
				System.out.println("Please choose from the options below: ");
				System.out.println("[1] View User " + customer.getUsername() + "'s Account Details"
						+ "\n[2] View User " + customer.getUsername() + "'s Previously Placed Orders"
						+ "\n[3] Return to Customer Selection" );
				//other things to maybe do: create new stores/ create new products and allocated them to store
				System.out.println(UIUXBusinessLogic.dashes());

				switch (scanner.nextLine()) {
				case "1":
					System.out.println(UIUXBusinessLogic.createSpaceBanner(customer.getUsername().toUpperCase() + " ACCOUNT DETAILS"));
					System.out.println(customer.toString());
					break;
				case "2":		
					customer.setOrderList(orderDao.getAllByCustomerId(customer));
					BusinessLogic.viewCustomerOrdersForEmployees(customer);
					break;
				case "3":
					System.out.println(UIUXBusinessLogic.dashes());
					System.out.println("Returning to Customer Selection...");
					System.out.println(UIUXBusinessLogic.dashes());
					isRunning2 = false;
					break;
				default:
					break;
				}
				
				
				
			}
			
			
		}
	}
}
