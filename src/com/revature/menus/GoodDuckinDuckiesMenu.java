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
		
		StoreFront goodDuckinDuckies = new StoreFront("Good Duckin' Duckies", "123 Lake Avenue, Duck Town, Duck Nation, 11111");
		Order order = new Order(goodDuckinDuckies.getName());
		boolean isRunning = true;
		
		
		//This can be deleted once the Database is implemented
		goodDuckinDuckies.getDuckieList().add(new Duckie(1, "Rubber Duckie", 2.99, "A bisic Rubber Duckie", "Mildly Duckie"));
		goodDuckinDuckies.getDuckieList().add(new Duckie(2, "Darkwing Duckie", 13.99, "Drake Mallard Duckie dons a mask to fight crime. Let's get dangerous!", "Very Stealthy and Very Duckie"));
		goodDuckinDuckies.getDuckieList().add(new Duckie(3, "Donald Duckie", 9.95, "A classic duckie that is very difficult to understand at times", "Quite Duckie"));
		goodDuckinDuckies.getDuckieList().add(new Duckie(4, "Daffy Duckie", 6.99, "It's duck season, and I say 'fire'!", "Very Duckie"));
		goodDuckinDuckies.getDuckieList().add(new Duckie(5, "Jon Duckett Duckie", 5, 15.79, "Comes with a free copy of HTML and CSS: Design and Build Websites (1st Edition) by: Jon Duckett", "Super Duper Duckie"));
		goodDuckinDuckies.getDuckieList().add(new Duckie(6, "[LIMITED EDITION] Black Mage Donald Duckie", 2, 79.99, "ZETAFLAAAAARE!!!!!!", "The Duckiest"));
		//-------------------------------------------------
		
		
		System.out.println("Welcome to Good Duckin' Duckies!");
		
		while(isRunning) {
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] View Duckie Catalog \n[2] Add Duckie To Cart "
					+ "\n[3] Remove Duckie From Cart"
					+" \n[4] View Your Cart"
					+" \n[5] Checkout"
					+ " \n[6] Return to Store Selection" );
			
			switch (scanner.nextLine()) {
			case "1":	
				System.out.println("Good Duckin' Duckies - Duckie Catalog:\n--------------------");
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
