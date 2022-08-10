package com.revature.menus;

import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.client.UIUXBusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Order;
import com.revature.models.StoreFront;
import com.revature.util.Logger;
import com.revature.util.Logger.LogLevel;

import DataLayer.OrderDAO;

public class CustomerMenu {

	public static void customerMenu(Scanner scanner, Customer customer) {
		
		OrderDAO orderDao = new OrderDAO();
		
		boolean isRunning = true;
		System.out.println(UIUXBusinessLogic.createSpaceBanner("Success! Welcome, " + customer.getUsername() + "!"));

		while(isRunning) {
			System.out.println(UIUXBusinessLogic.createBanner("CUSTOMER MENU - " + customer.getUsername()));
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] Select a Store To Shop From"
					+ "\n[2] View Previously Placed Orders "
					+ "\n[3] Logout" );
			System.out.println(UIUXBusinessLogic.dashes());

			
			switch (scanner.nextLine()) {
			case "1":	
				StoreFront storeFront = BusinessLogic.selectStore(scanner);
				StoreFrontMenu.storeFrontMenu(scanner, customer, storeFront);
				break;
			case "2":
				customer.setOrderList(orderDao.getAllByCustomerId(customer));
				BusinessLogic.viewCustomerOrders(customer);
				break;
			case "3":
				System.out.println(UIUXBusinessLogic.dashes());
				System.out.println("Logging Out of User " + customer.getUsername() + "'s account...");
				System.out.println(UIUXBusinessLogic.dashes());
				Logger.getLogger().log(LogLevel.info, "\nUser: " + customer.getUsername() + " logged out of account\n");
				isRunning = false;
			default:
				break;
			}
			
		}
	}
	
}
