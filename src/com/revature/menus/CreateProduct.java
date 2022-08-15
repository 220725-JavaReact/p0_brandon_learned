package com.revature.menus;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.client.CreateProductLogic;
import com.revature.client.UIUXBusinessLogic;
import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.Employee;
import com.revature.util.Logger;
import com.revature.util.Logger.LogLevel;

import DataLayer.CustomerDAO;
import DataLayer.DAO;
import DataLayer.DuckieDAO;

public class CreateProduct {
	
	public static void createProduct(Scanner scanner, Employee employee) {
		
		DAO<Duckie> duckieDao = new DuckieDAO();
		boolean isRunning = true;
		String productName = "";
		double price = 0;
		String description = "";
		String quality = "";
		String reply  ="";
		
		while(isRunning) {
			
			System.out.println(UIUXBusinessLogic.createBanner("PRODUCT REGISTRY"));
	
			System.out.println("\nPlease enter a Product Name. (Max Characters: 50)");
			while(productName.equals("")) {
				String attemptName =  scanner.nextLine();
				productName = CreateProductLogic.verifyProductName(attemptName);
			}
			
			while(price == 0) {
				System.out.println("\nPlease enter a Product Price. (Format: #.##)");
				String attamptPrice = scanner.nextLine();
				price = CreateProductLogic.verfiyPrice(attamptPrice);
			}
			
			System.out.println("\nPlease enter a Product Description. (Max Characters: 50)");
			while(description.equals("")) {
				String attemptDescription =  scanner.nextLine();
				description = CreateProductLogic.verifyDescription(attemptDescription);
			}
			
			System.out.println("\nPlease enter a Product Quality. (Ex: 'Like New!') (Max Characters: 50)");
			while(quality.equals("")) {
				String attemptQuality =  scanner.nextLine();
				quality = CreateProductLogic.verifyQuality(attemptQuality);
			}
			
			while(reply.equals("")) {
				DecimalFormat df = new DecimalFormat("0.00");
				System.out.println("\nProduct Name: " + productName  
						+"\nPrice: $" + df.format(price)
						+"\nDescription: " + description
						+"\nQuality: " + quality
						+"\nDoes this look correct to you? \n[Y/N]"
						+ "\n" + UIUXBusinessLogic.dashes());
				reply = scanner.nextLine();
				switch (reply) {
				case "Y":	
				case "y":
					//this should be altered for when database is implemented
					Duckie duckie = new Duckie(productName, price, description, quality);
					duckieDao.addInstance(duckie);
					System.out.println(UIUXBusinessLogic.createSpaceBanner("Product " + duckie.getName() + " successfully created!"));
					Logger.getLogger().log(LogLevel.info, "\nAdmin User " + employee.getUsername() + " Created New Product: " + duckie.toString() + "\n");
					isRunning = false;
					break;
				case "N":
				case "n":
					isRunning = false;
					break;
				default:
					reply = "";
					break;
				}
			}
			
				
		}
	}
}
