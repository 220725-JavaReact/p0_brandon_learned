package com.revature.menus;

import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.client.EmployeeSpecificBusinessLogic;
import com.revature.client.UIUXBusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Employee;

import DataLayer.OrderDAO;

public class EmployeeCustomerMenu {

	public static void employeeCustomerMenu(Scanner scanner, Employee employee) {
		boolean isRunning = true;
		boolean isRunning2 = false;
		OrderDAO orderDao = new OrderDAO();
		
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
					BusinessLogic.viewCustomerOrdersForEmployees(scanner, customer);
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
