package com.revature.menus;

import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Order;
import com.revature.models.StoreFront;

public class CustomerMenu {

	public static void customerMenu(Scanner scanner, Customer customer) {

		//get all store fronts
		boolean isRunning = true;
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Success! Welcome, " + customer.getUsername());
		System.out.println("-------------------------------------------------------------------------------");
		
		while(isRunning) {
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] Select a Store To Shop From\n[2] View Previous Orders Placed \n[3] Logout" );
			System.out.println("-------------------------------------------------------------------------------");

			
			switch (scanner.nextLine()) {
			case "1":	
				StoreFront storeFront = BusinessLogic.selectStore(scanner, customer);
				StoreFrontMenu.storeFrontMenu(scanner, customer, storeFront);
				break;
			case "2":
				if(customer.getOrderList().size() == 0) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("You have no previous orders placed");
					System.out.println("-------------------------------------------------------------------------------");
				} else {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Your previous orders:");
					System.out.println("       -");
					for(Order order : customer.getOrderList()) {
						System.out.println("Purchased from: " + order.getStoreAddress());
						order.printOrderWithTax();
						if(order != customer.getOrderList().get(customer.getOrderList().size()-1)) {
							System.out.println(" ");
						}
					}
					System.out.println("-------------------------------------------------------------------------------");

				}

				break;
			case "3":
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Logging Out of User " + customer.getUsername() + "'s account...");
				System.out.println("-------------------------------------------------------------------------------");
				isRunning = false;
			default:
				break;
			}
			
		}
	}
	
}
