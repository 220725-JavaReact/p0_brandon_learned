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
						+ "\n[2] View User " + customer.getUsername() + "'s Previously Placed Orders");
				System.out.println(UIUXBusinessLogic.dashes());
				System.out.println("[x] Return to Customer Selection");
				System.out.println(UIUXBusinessLogic.dashes());

				String reply = scanner.nextLine();
				if(BusinessLogic.isInt(reply)) {
					switch (reply) {
					case "1":
						boolean inLoop = true;
						while(inLoop) {
							System.out.println(UIUXBusinessLogic.createSpaceBanner(customer.getUsername().toUpperCase() + " ACCOUNT DETAILS"));
							System.out.println(customer.toString());
							System.out.println(UIUXBusinessLogic.dashes());						
							System.out.println("[x] Return to Menu Options");
							System.out.println(UIUXBusinessLogic.dashes());						
							if(scanner.nextLine().toLowerCase().equals("x")) {
								inLoop = false;
								break;
							}
						}
						break;
					case "2":		
						customer.setOrderList(orderDao.getAllByCustomerId(customer));
						BusinessLogic.viewCustomerOrdersForEmployees(scanner, customer);
						break;
					default:
						break;
					}
				} else {
					if(reply.toLowerCase().equals("x")) {
						System.out.println(UIUXBusinessLogic.dashes());
						System.out.println("Returning to Customer Selection...");
						System.out.println(UIUXBusinessLogic.dashes());
						isRunning2 = false;
						break;
					}
				}
			}
		}
	}
}
