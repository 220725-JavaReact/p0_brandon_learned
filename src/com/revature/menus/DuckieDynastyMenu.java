package com.revature.menus;

import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.Order;
import com.revature.models.StoreFront;

public class DuckieDynastyMenu {

	public static void duckieDynastyMenu(Scanner scanner, Customer customer) {
		
		StoreFront duckieDynasty = new StoreFront("Duckie Dynasty", "63111 Ducktail Way, Duck Town, Duck Nation, 11111");
		Order order = new Order(duckieDynasty.getName());
		boolean isRunning = true;
		
		
		//This can be deleted once the Database is implemented
		duckieDynasty.getDuckieList().add(new Duckie(1, "Rubber Duckie", 4.99, "A bisic Rubber Duckie of the Highest Quality", "Mildly Duckie"));
		duckieDynasty.getDuckieList().add(new Duckie(2, "Howard the Duckie", 17.99, "Drake Mallard Duckie dons a mask to fight crime. Let's get dangerous!", "Very Stealthy and Very Duckie"));
		duckieDynasty.getDuckieList().add(new Duckie(3, "Scrooge McDuckie", 9.95, "A classic duckie that is very difficult to understand at times", "Quite Duckie"));
		duckieDynasty.getDuckieList().add(new Duckie(4, "Psyduckie", 6.99, "It's duck season, and I say 'fire'!", "Very Duckie"));
		duckieDynasty.getDuckieList().add(new Duckie(6, "[LIMITED EDITION] Black Mage Donald Duckie", 2, 79.99, "ZETAFLAAAAARE!!!!!!", "The Duckiest"));
		//-------------------------------------------------
		
		
		System.out.println("Welcome to Good Duckie Dynasty!");
		
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
				BusinessLogic.printAllDuckies(duckieDynasty);
				break;
			case "2":
				BusinessLogic.addDuckiesToCart(scanner, customer, order, duckieDynasty);
				break;
			case "3":
				if(order.getLineItemArray().size() == 0) {
					BusinessLogic.printCart(order);
				} else {
					BusinessLogic.removeDuckiesFromCart(scanner, customer, order, duckieDynasty);
				}
				break;
			case "4":
				BusinessLogic.printCart(order);
				break;
			case "5":
				if(order.getLineItemArray().size() == 0) {
					BusinessLogic.printCart(order);
				} else {
					if(BusinessLogic.finalizeOrder(scanner, customer, order, duckieDynasty)) {
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
