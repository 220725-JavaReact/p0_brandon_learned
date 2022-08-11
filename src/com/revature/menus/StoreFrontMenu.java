package com.revature.menus;

import java.util.Scanner;   

import com.revature.client.BusinessLogic;
import com.revature.client.EmployeeSpecificBusinessLogic;
import com.revature.client.UIUXBusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Order;
import com.revature.models.StoreFront;
import com.revature.util.Logger;
import com.revature.util.Logger.LogLevel;

import DataLayer.StoreFrontsDAO;

public class StoreFrontMenu {

	public static void storeFrontMenu(Scanner scanner, Customer customer, StoreFront storeFront) {
		
		StoreFrontsDAO storeFrontDao = new StoreFrontsDAO();
		storeFrontDao.initializeAllDuckies(storeFront);
		Order order = new Order(storeFront.getName()); //may need to change
		boolean isRunning = true;


		while(isRunning) {
			System.out.println(UIUXBusinessLogic.createBanner(storeFront.getName().toUpperCase() +" MENU"));
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] View Duckie Catalog "
					+ "\n[2] Add Duckie To Cart "
					+ "\n[3] Remove Duckie From Cart"
					+" \n[4] View Your Cart"
					+" \n[5] Checkout");
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println("[x] Return to Menu Options");
			System.out.println(UIUXBusinessLogic.dashes());

			String reply = scanner.nextLine();
			if(BusinessLogic.isInt(reply)) {
				switch (reply) {
				case "1":	
					EmployeeSpecificBusinessLogic.newViewAllStoreProducts(scanner, storeFront);
					break;
				case "2":
					BusinessLogic.addDuckiesToCart(scanner, customer, order, storeFront);
					break;
				case "3":
					if(order.getLineItemArray().size() == 0) {
						BusinessLogic.printCart(scanner, order);
					} else {
						BusinessLogic.removeDuckiesFromCart(scanner, customer, order, storeFront);
					}
					break;
				case "4":
					BusinessLogic.printCart(scanner, order);
					break;
				case "5":
					if(order.getLineItemArray().size() == 0) {
						BusinessLogic.printCart(scanner, order);
					} else {
						if(BusinessLogic.finalizeOrder(scanner, customer, order, storeFront)) {
							isRunning = false;
							break;
						}
					}
					break;
				default:
					break;
				}
			} else {
				if(reply.toLowerCase().equals("x")) {
					System.out.println(UIUXBusinessLogic.dashes());
					System.out.println("Returning to Menu Options...");
					System.out.println(UIUXBusinessLogic.dashes());
					isRunning = false;
				}
			}	
		}		
	}
}
