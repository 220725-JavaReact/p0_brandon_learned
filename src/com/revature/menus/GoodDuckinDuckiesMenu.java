package com.revature.menus;

import java.util.Scanner; 

import com.revature.client.BusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.LineItem;
import com.revature.models.Order;
import com.revature.models.StoreFront;
import com.revature.tempdatastorage.TemporaryStorage;

public class GoodDuckinDuckiesMenu {

	public static void goodDuckinDuckiesMenu(Scanner scanner, Customer customer) {
		
		StoreFront goodDuckinDuckies = TemporaryStorage.goodDuckinDuckies;
		Order order = new Order(goodDuckinDuckies.getName());
		boolean isRunning = true;

		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Welcome to Good Duckin' Duckies!");
		System.out.println("-------------------------------------------------------------------------------");

		while(isRunning) {
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] View Duckie Catalog \n[2] Add Duckie To Cart "
					+ "\n[3] Remove Duckie From Cart"
					+" \n[4] View Your Cart"
					+" \n[5] Checkout"
					+ " \n[6] Return to Store Selection" );
			
			switch (scanner.nextLine()) {
			case "1":	
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Good Duckin' Duckies - Duckie Catalog:");
				System.out.println("-------------------------------------------------------------------------------");
				BusinessLogic.printAllDuckies(goodDuckinDuckies);
				break;
			case "2":
				BusinessLogic.addDuckiesToCart(scanner, customer, order, goodDuckinDuckies);
				break;
			case "3":
				if(order.getLineItemArray().size() == 0) {
					BusinessLogic.printCart(order);
				} else {
					BusinessLogic.removeDuckiesFromCart(scanner, customer, order, goodDuckinDuckies);
				}
				break;
			case "4":
				BusinessLogic.printCart(order);
				break;
			case "5":
				if(order.getLineItemArray().size() == 0) {
					BusinessLogic.printCart(order);
				} else {
					if(BusinessLogic.finalizeOrder(scanner, customer, order, goodDuckinDuckies)) {
						isRunning = false;
						break;
					}
				}
				break;
			case "6":
				System.out.println("Returning to Store Selection");
				isRunning = false;
				break;
			default:
				break;
			}	
		}		
	}
}
