package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.client.EmployeeSpecificBusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.Employee;
import com.revature.models.LineItem;
import com.revature.models.Order;
import com.revature.models.StoreFront;

import DataLayer.CustomerDAO;
import DataLayer.DuckieDAO;
import DataLayer.OrderDAO;
import DataLayer.StoreFrontsDAO;

public class EmployeeStoreMenu {

	public static void employeeStoreMenu(Scanner scanner, Employee employee) {
		boolean isRunning = true;
		StoreFrontsDAO storeFrontDao = new StoreFrontsDAO();
		OrderDAO orderDao = new OrderDAO();
		StoreFront storeFront = EmployeeSpecificBusinessLogic.selectStore(scanner);
		storeFrontDao.initializeAllDuckies(storeFront);

		while(isRunning) {
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] View All Current Products for " + storeFront.getName()
					+ "\n[2] Manage Current Product Stock" //Add new, remove current, add stock, remove stock
					+ "\n[3] Add New Products"
					+ "\n[4] Remove Products"
					+ "\n[5] View Previously Placed Orders" //view orders
					+ "\n[6] Return to Employee Menu Options" );
			System.out.println("-------------------------------------------------------------------------------");

			
			switch (scanner.nextLine()) {
			case "1":
				EmployeeSpecificBusinessLogic.viewAllStoreProducts(scanner, storeFront);
				break;
			case "2":			
				LineItem lineItem = EmployeeSpecificBusinessLogic.chooseProductToAlter(scanner, employee, storeFront);
				EmployeeSpecificBusinessLogic.alterProduct(scanner, employee, lineItem, storeFront);
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
					System.out.println("-------------------------------------------------------------------------------");			
					System.out.println("No Previous orders to Display");
					System.out.println("-------------------------------------------------------------------------------");			
				} else {
					System.out.println("-------------------------------------------------------------------------------");			
					System.out.println(storeFront.getName() + " order history:");
					System.out.println("       -");
					for(int i=0; i<orders.size(); i++) {
						System.out.println(orders.get(i).toString());
						if(orders.get(i) != orders.get(orders.size()-1)) {
							System.out.println(" ");
						}
					}
					System.out.println("-------------------------------------------------------------------------------");				
				}
				break;
			case "6":
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Logging Out of User " + employee.getUsername() + "'s account...");
				System.out.println("-------------------------------------------------------------------------------");
				isRunning = false;
				break;
			default:
				break;
			}
		}

		 isRunning = false;
	}
}
