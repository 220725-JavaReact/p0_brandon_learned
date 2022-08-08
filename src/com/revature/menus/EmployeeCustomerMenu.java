package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.client.EmployeeSpecificBusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Order;
import com.revature.models.StoreFront;

import DataLayer.CustomerDAO;
import DataLayer.OrderDAO;
import DataLayer.StoreFrontsDAO;

public class EmployeeCustomerMenu {

	public static void employeeCustomerMenu(Scanner scanner, Employee employee) {
		boolean isRunning = true;
		StoreFrontsDAO storeFrontDao = new StoreFrontsDAO();
		CustomerDAO customerDao = new CustomerDAO();
		OrderDAO orderDao = new OrderDAO();
		ArrayList<StoreFront> storeFronts = storeFrontDao.getAll();
		ArrayList<Customer> customersToView = customerDao.getAll();
		
		while(isRunning) {
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] Store Options"
					+ "\n[2] Customer Options"
					+ "\n[3] Other Options"
					+ "\n[4] Logout" );
			//other things to maybe do: create new stores/ create new products and allocated them to store
			System.out.println("-------------------------------------------------------------------------------");

			
			switch (scanner.nextLine()) {
			case "1":
				EmployeeStoreMenu.employeeStoreMenu(scanner, employee);
				break;
			case "2":
				String response = null;
				Customer customer = EmployeeSpecificBusinessLogic.selectCustomer(scanner);
				Order order = EmployeeSpecificBusinessLogic.chooseCustomerOrders(scanner, employee, customer);
				if(order != null) {
					response = EmployeeSpecificBusinessLogic.alterCustomerOrderType(scanner, order);
					if(response.equals("add")) {
						EmployeeSpecificBusinessLogic.addToOrder(scanner, order, employee, customer);
					} else if (response.equals("remove")) {
						EmployeeSpecificBusinessLogic.removeFromOrder(scanner, order, employee);
					} else if (response.equals("delete")) {
						customerDao.deleteOrder(customer, order);
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Order:");
						order.printOrder();
						System.out.println("was deleted from " + customer.getUsername() + "'s orders");
						System.out.println("-------------------------------------------------------------------------------");

					}
				}
				
				break;
			case "3":
				Customer customerToDelete = EmployeeSpecificBusinessLogic.selectCustomer(scanner);
				customerToDelete.setOrderList(orderDao.getAllByCustomerId(customerToDelete));;
				if(customerToDelete.getOrderList().size() == 0) {
					customerDao.deleteInstance(customerToDelete);
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("User " + customerToDelete.getUsername() + " has been successfully deleted");
					System.out.println("-------------------------------------------------------------------------------");
				} else {
					customerDao.deleteInstanceWithOrders(customerToDelete);
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("User " + customerToDelete.getUsername() + " successfully deleted");
					System.out.println("-------------------------------------------------------------------------------");
				}
				scanner.nextLine();
				
				break;
			case "4":
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Logging Out of User " + employee.getUsername() + "'s account...");
				System.out.println("-------------------------------------------------------------------------------");
				isRunning = false;
				break;
			default:
				break;
			}
			isRunning = false;
		}
	}
}
