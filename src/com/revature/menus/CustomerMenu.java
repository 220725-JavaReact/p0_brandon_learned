package com.revature.menus;

import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.models.Customer;
import com.revature.models.LineItem;
import com.revature.models.Order;

public class CustomerMenu {

	public static void customerMenu(Scanner scanner, Customer customer) {

		boolean isRunning = true;
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Success! Welcome, " + customer.getUsername());
		System.out.println("-------------------------------------------------------------------------------");
		
		while(isRunning) {
			System.out.println("Please choose from the stores below to browse our extensive catalog of Rubber Duckies!: ");
			System.out.println("[1] Good Duckin' Duckies \n[2] Discount Duckies \n[3] Duckie Dynasty "
					+ "\n[4] View Previous Orders Placed \n[5] Logout" );
			System.out.println("-------------------------------------------------------------------------------");

			
			switch (scanner.nextLine()) {
			case "1":	
				GoodDuckinDuckiesMenu.goodDuckinDuckiesMenu(scanner, customer);
				break;
			case "2":
				DiscountDuckiesMenu.discountDuckiesMenu(scanner, customer);
				break;
			case "3":
				DuckieDynastyMenu.duckieDynastyMenu(scanner, customer);
				break;
			case "4":
				if(customer.getOrderList().size() == 0) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("You have no previous orders placed");
					System.out.println("-------------------------------------------------------------------------------");
				} else {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Previous orders:");
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
			case "5":
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
