package com.revature.menus;

import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.Order;
import com.revature.models.StoreFront;

public class DiscountDuckiesMenu {

	public static void discountDuckiesMenu(Scanner scanner, Customer customer) {
		
		StoreFront discountDuckies = new StoreFront("Discout Duckies", "5732 Pond Road, Duck Town, Duck Nation, 11111");
		Order order = new Order(discountDuckies.getName());
		boolean isRunning = true;
		
		
		//This can be deleted once the Database is implemented
		discountDuckies.getDuckieList().add(new Duckie(1, "Rubber Duckie", 1.99, "A bisic Rubber Duckie", "Mildly Duckie"));
		discountDuckies.getDuckieList().add(new Duckie(2, "Red Duckie", 2.99, "A basic Rubber Duckie, but red!", "Kinda Duckie?"));
		discountDuckies.getDuckieList().add(new Duckie(3, "Alien Duckie", 9.95, "Keep your cows under a tight watch!", "Quite Duckie"));
		discountDuckies.getDuckieList().add(new Duckie(4, "Duckie Lasek", 6.99, "Skateboarding pro Duckie", "The Steeziest Duckie"));
		discountDuckies.getDuckieList().add(new Duckie(5, "Mighty Duckie", 9.99, "Ever seen a duck fight? No. Why? Because other animals are afraid", "The Mightiest and Duckiest"));
		//-------------------------------------------------
		
		
		System.out.println("Welcome to Discount Duckies!");
		
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
				BusinessLogic.printAllDuckies(discountDuckies);
				break;
			case "2":
				BusinessLogic.addDuckiesToCart(scanner, customer, order, discountDuckies);
				break;
			case "3":
				if(order.getLineItemArray().size() == 0) {
					BusinessLogic.printCart(order);
				} else {
					BusinessLogic.removeDuckiesFromCart(scanner, customer, order, discountDuckies);
				}
				break;
			case "4":
				BusinessLogic.printCart(order);
				break;
			case "5":
				if(order.getLineItemArray().size() == 0) {
					BusinessLogic.printCart(order);
				} else {
					if(BusinessLogic.finalizeOrder(scanner, customer, order, discountDuckies)) {
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
