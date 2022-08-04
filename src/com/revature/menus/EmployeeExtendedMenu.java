package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Order;

public class EmployeeExtendedMenu {

	public static void employeeExtendedMenu(Scanner scanner, Employee employee, ArrayList<Customer> customersToView) {
		
		boolean isRunning = true;
		
		while(isRunning) {
			int response = 0;
			Customer customer = null;
			while(response == 0) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Select a customer to view their placed orders(Enter a Number)");
				System.out.println("-------------------------------------------------------------------------------");	
				for(int i = 0; i<customersToView.size(); i++) {
					System.out.println("[" + (i+1) + "]" + customersToView.get(i).getUsername());
				}	
				System.out.println("-------------------------------------------------------------------------------");
				try {
					response = scanner.nextInt();
					if(response<1 || response>customersToView.size()) {
						System.out.println("Not a valid option");
						response = 0;
						continue;
					} else {
						customer = customersToView.get(response-1);
						break;
					}
				} catch(Exception e) {
					System.out.println("Not a valid option");
					response = 0;
					scanner.nextLine(); //eat a line to avoid a loop
				}
			}
			
			if(customer.getOrderList().isEmpty()) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Customer does not have any previous orders to view");
				System.out.println("-------------------------------------------------------------------------------");
				scanner.nextLine();
				isRunning = false;
				break;
			} else {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println(customersToView.get(response-1).getUsername() + "'s previous orders:");
				System.out.println("       -");
				for(Order order : customersToView.get(response-1).getOrderList()) {
					System.out.println("Purchased at: " + order.getStoreAddress());
					order.printOrderWithTax();
					if(order != customersToView.get(response-1).getOrderList().get(customersToView.get(response-1).getOrderList().size()-1)) {
						System.out.println(" ");
					}
				}	
				System.out.println("-------------------------------------------------------------------------------");
				scanner.nextLine();
			}

			String reply = "";
			while(reply.equals("")) {
				System.out.println("Whould you like to alter " + customer.getUsername() + "'s previously placed orders?[Y/N]");
				System.out.println("-------------------------------------------------------------------------------");
				reply = scanner.nextLine();
				
				if(reply.toLowerCase().equals("y")) {
					BusinessLogic.alterCustomerOrders(scanner, customer, employee);
					isRunning = false;
					break;
				} else if(reply.toLowerCase().equals("n")) {
					System.out.println(customer.getUsername() + "'s previously placed orders were not altered");
					isRunning = false;
					break;
				} else {
					System.out.println("Not a valid option");
					reply = "";
				}
			}
			
			
			
			
			
			

				//if no, back to previous
				
				
				//probably use a switch statement
				
				
//				System.out.println("-------------------------------------------------------------------------------");
//				System.out.println(customersToView.get(response-1).getUsername() + "'s previous orders:");
//				System.out.println("       -");
//				for(Order order : customersToView.get(response-1).getOrderList()) {
//					System.out.println("Purchased at: " + order.getStoreAddress());
//					order.printOrderWithTax();
//					if(order != customersToView.get(response-1).getOrderList().get(customersToView.get(response-1).getOrderList().size()-1)) {
//						System.out.println(" ");
//					}
//				}
//				Customer customer = customersToView.get(response-1);
//				if(customer.getOrderList().size() == 0) {
//					
//				} else {
//					
//				}
				
				
				//alterOrders(scanner, customer, employee);
				isRunning = false;
				System.out.println("-------------------------------------------------------------------------------");
			isRunning = false;

			
		}		
	}
}
