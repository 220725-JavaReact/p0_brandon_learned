package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.client.EmployeeSpecificBusinessLogic;
import com.revature.client.UIUXBusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.Employee;
import com.revature.models.LineItem;
import com.revature.models.Order;
import com.revature.models.StoreFront;
import com.revature.util.Logger;
import com.revature.util.Logger.LogLevel;

import DataLayer.CustomerDAO;
import DataLayer.DuckieDAO;
import DataLayer.OrderDAO;
import DataLayer.StoreFrontsDAO;

public class EmployeeStoreMenu {

	public static void employeeStoreMenu(Scanner scanner, Employee employee) {
		boolean isRunning = true;
		StoreFrontsDAO storeFrontDao = new StoreFrontsDAO();
		OrderDAO orderDao = new OrderDAO();
		StoreFront storeFront = BusinessLogic.selectStore(scanner);
		boolean inLoop = true;
		
		if(storeFront == null) {
			isRunning = false;
		} else {
			storeFrontDao.initializeAllDuckies(storeFront);
		}

		while(isRunning) {
			System.out.println(UIUXBusinessLogic.createBanner(storeFront.getName().toUpperCase() +" EMPLOYEE MENU"));
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] View All Current Products for " + storeFront.getName()
					+ "\n[2] Manage Current Product Stock" //Add new, remove current, add stock, remove stock
					+ "\n[3] Add New Products"
					+ "\n[4] Remove Products"
					+ "\n[5] View Previously Placed Orders" //view orders
					+ "\n[6] View Profit Reports");
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println("[x] Return to Employee Menu Options" );
			System.out.println(UIUXBusinessLogic.dashes());

			String reply = scanner.nextLine();
			if(BusinessLogic.isInt(reply)) {
				switch (reply) {
				case "1":
					EmployeeSpecificBusinessLogic.newViewAllStoreProducts(scanner, storeFront);
					break;
				case "2":
					while(inLoop) {
						LineItem lineItem = EmployeeSpecificBusinessLogic.chooseProductToAlter(scanner, employee, storeFront);
						if(lineItem == null) {
							inLoop = false;
						} else {
							EmployeeSpecificBusinessLogic.alterProduct(scanner, employee, lineItem, storeFront);
						}
					}
					inLoop = true;
					break;
				case "3":
					EmployeeSpecificBusinessLogic.addNewProducts(scanner, employee, storeFront);
					break;
				case "4":
					EmployeeSpecificBusinessLogic.removeProducts(scanner, employee, storeFront);
					break;
				case "5":
					ArrayList<Order> orders = orderDao.getAllByStorefrontId(storeFront);
					if(orders.size() == 0) {
						System.out.println(UIUXBusinessLogic.createSpaceBanner("No Previous orders to Display..."));
					} else {
						while(inLoop) {
							System.out.println(UIUXBusinessLogic.dashes());
							System.out.println(UIUXBusinessLogic.centerText(storeFront.getName().toUpperCase() + " ORDER HISTORY"));
							System.out.println(UIUXBusinessLogic.centerText("---------------"));
							for(int i=0; i<orders.size(); i++) {
								UIUXBusinessLogic.formatOrder(orders.get(i));
								if(orders.get(i) != orders.get(orders.size()-1)) {
									System.out.println(" ");
								}
							}
							System.out.println(" ");
							System.out.println(UIUXBusinessLogic.dashes());
							System.out.println("[x] Return to Menu Options");
							System.out.println(UIUXBusinessLogic.dashes());

							if(scanner.nextLine().toLowerCase().equals("x")) {
								inLoop = false;
							}
						}
							
					}
					inLoop = true;
					break;
				case "6":
					EmployeeSpecificBusinessLogic.profitReports(scanner, employee, storeFront);
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
		 isRunning = false;
	}
}
