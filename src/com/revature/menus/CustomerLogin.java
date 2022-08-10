package com.revature.menus;

import java.util.Scanner;

import com.revature.client.UIUXBusinessLogic;
import com.revature.models.Customer;
import com.revature.util.Logger;
import com.revature.util.Logger.LogLevel;

import DataLayer.CustomerDAO;
import DataLayer.DAO;

public class CustomerLogin {

	public static void customerLogin(Scanner scanner) {
		
		DAO<Customer> customerDao = new CustomerDAO();
		boolean isRunning = true;
		
		System.out.println(UIUXBusinessLogic.createBanner("CUSTOMER LOGIN"));

		while(isRunning) {
			
			System.out.println("Input Username: ");
			String attemptUsername = scanner.nextLine();
			System.out.println("Input Password: ");
			String attemptPassword = scanner.nextLine();
			Customer customer = customerDao.getByName(attemptUsername);
			
			if(customer == null) {
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Incorrect Username or Password..."));
				isRunning = false;
				break;
			}

			String targetPassword = customer.getPassword();
			if(!targetPassword.equals(attemptPassword)) {
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Incorrect Username or Password..."));
				isRunning = false;
				break;
			}
			
			Logger.getLogger().log(LogLevel.info, "\nUser: " + customer.getUsername() + " logged into account\n");
			CustomerMenu.customerMenu(scanner, customer);
					
			isRunning = false;
		}
		
	}
}
