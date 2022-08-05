package com.revature.client;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.LineItem;
import com.revature.models.Order;
import com.revature.models.StoreFront;

import DataLayer.CustomerDAO;
import DataLayer.StoreFrontsDAO;

public class EmployeeSpecificBusinessLogic {

	
	public static Customer selectCustomer(Scanner scanner) {
		CustomerDAO customerDao = new CustomerDAO();
		ArrayList<Customer> customers = customerDao.getAll();
		Customer customer = null;
		int response = -1;
		boolean isRunning = true;
		
		while(isRunning) {
			while(response == -1) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Select a customer to view their placed orders(Enter a Number)");
				System.out.println("-------------------------------------------------------------------------------");	
				for(int i = 0; i<customers.size(); i++) {
					System.out.println("[" + (i+1) + "]" + customers.get(i).getUsername());
				}	
				System.out.println("-------------------------------------------------------------------------------");
				try {
					response = scanner.nextInt();
					if(response<1 || response>customers.size()) {
						System.out.println("Not a valid option");
						response = -1;
						break;
					} else {
						return customers.get(response-1);
					}
				} catch(Exception e) {
					System.out.println("Not a valid option");
					response = -1;
					scanner.nextLine(); //eat a line to avoid a loop
					break;
				}
			}
			
		}
		
		return customer;
	}
	
	
	public static Order chooseCustomerOrders(Scanner scanner, Employee employee, Customer customer) {

		Order returnOrder = null;
		boolean isRunning = true;
		ArrayList<Order> orders = customer.getOrderList();
		
		while(isRunning) {
			System.out.println("here");
			if(customer.getOrderList().isEmpty()) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Customer does not have any previous orders to view");
				System.out.println("-------------------------------------------------------------------------------");
				scanner.nextLine();
				return null;
			} else {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println(customer.getUsername() + "'s previous orders:");
				System.out.println("       -");
				for(int i = 0; i<customer.getOrderList().size(); i++) {
					System.out.println("Purchased at: " + orders.get(i).getStoreAddress());
					orders.get(i).printOrderWithTax();
					if(orders.get(i) != orders.get(customer.getOrderList().size()-1)) {
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
					int response = -1;
					while(response == -1) {
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Select a customer order to view(Enter a Number)");
						System.out.println("-------------------------------------------------------------------------------");
						for(int i = 0; i<customer.getOrderList().size(); i++) {
							System.out.println("[" + (i+1) + "] " + customer.getOrderList().get(i));
							if(orders.get(i) != orders.get(customer.getOrderList().size()-1)) {
								System.out.println(" ");
							}
						}	
						
						
						try { 
							response = scanner.nextInt();
							if(response < 1 || response > customer.getOrderList().size()) {
								System.out.println("Not a valid option");
								response = -1;
								scanner.nextLine();
							} else {
								returnOrder = customer.getOrderList().get(response-1);
								scanner.nextLine();//this
								return returnOrder;
							}
						} catch (Exception e) {
							System.out.println("Not a valid option");
							response = -1;
							scanner.nextLine();
						}	
						
					}
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
			
			
				isRunning = false;
				System.out.println("-------------------------------------------------------------------------------");
			isRunning = false;

			
		}
		return returnOrder;	
		
	}

	public static  String alterCustomerOrderType(Scanner scanner, Order order) {
		
		boolean isRunning = true;
		
		while(isRunning) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Select from the options below for altering the order: ");
			order.printOrder();
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("[1] Add Items \n[2] Remove Items \n[3] Delete Order \n[4] Return to Menu");
			System.out.println("-------------------------------------------------------------------------------");
			//Add items/remove items/delete order
						
			switch (scanner.nextLine()) {
			case "1":
				return "add";
			case "2":
				return "remove";
			case "3":
				return "delete";
			case "4":
				return null;
			default:
				break;
			}		
		}
		return null;
	}


	public static void addToOrder(Scanner scanner, Order order, Employee employee, Customer customer) {
		StoreFrontsDAO storeFrontDao = new StoreFrontsDAO();
		StoreFront storeFront = storeFrontDao.getByAddress(order.getStoreAddress());
		LineItem lineItem = null;
		
		//select line item to add to
		//select quantity to add (if exists in store amount)
		//increase cust amount, decrease store
		int response = -1;
		while(response == -1) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Please select the item you wish to alter");
			System.out.println("-------------------------------------------------------------------------------");
			for(int i = 0; i<order.getLineItemArray().size(); i++) {
				System.out.println("[" + (i+1) + "] " + order.getLineItemArray().get(i)); //maybe will have to format this
			}
			System.out.println("-------------------------------------------------------------------------------");
			try {
				response = scanner.nextInt();
				if(response < 1 || response > order.getLineItemArray().size()) {
					System.out.println("Not a valid option");
					scanner.nextInt();
					response = -1;
				} else {
					lineItem = order.getLineItemArray().get(response-1);
				}
				
			} catch (Exception e) {
				System.out.println("Not a valid option");
				scanner.nextLine();
				response = -1;
			}
		}
//		for() //working here
//		System.out.println("-------------------------------------------------------------------------------");
//		System.out.println("Please select the amount");
//		System.out.println("-------------------------------------------------------------------------------");
//		
		
	}


	public static void removeFromOrder(Scanner scanner, Order order, Employee employee) {
		// TODO Auto-generated method stub

		//select line item to add to
		//select quantity to add (if exists in store amount)
		//increase cust amount, decrease store
	}
	
}
	

