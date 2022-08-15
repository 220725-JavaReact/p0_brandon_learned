package com.revature.menus;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.client.CreateProductLogic;
import com.revature.client.UIUXBusinessLogic;
import com.revature.models.Duckie;
import com.revature.models.Employee;
import com.revature.util.Logger;
import com.revature.util.Logger.LogLevel;

import DataLayer.DAO;
import DataLayer.DuckieDAO;

public class CreateStore {

	public static void createStore(Scanner scanner, Employee employee) {
		
		DAO<Duckie> duckieDao = new DuckieDAO();
		boolean isRunning = true;
		String productName = "";
		double price = 0;
		String description = "";
		String quality = "";
		String reply  ="";
		
		//StoreName = format replace all ' with '' (2 of them) (MAX 50)
		//Address - street (MAX FOR ENTIRE ADDRESS IS 100)
		// city
		// state
		// zip 
		
		while(isRunning) {
			
			System.out.println(UIUXBusinessLogic.createBanner("STOREFRONT REGISTRY"));
	
			System.out.println("\nPlease enter a StoreFront Name. (Max Characters: 25)");
			while(productName.equals("")) {
				String attemptName =  scanner.nextLine();
				productName = CreateProductLogic.verifyProductName(attemptName);
			}
			
			while(price == 0) {
				System.out.println("\nPlease enter a Street Address (Max Characters: 25)");
				String attamptPrice = scanner.nextLine();
				price = CreateProductLogic.verfiyPrice(attamptPrice);
			}
			
			System.out.println("\nPlease enter a City. (Max Characters: 25)");
			while(description.equals("")) {
				String attemptDescription =  scanner.nextLine();
				description = CreateProductLogic.verifyDescription(attemptDescription);
			}
			
			System.out.println("\nPlease enter a City. (Format Ex: 'AZ')"); //make sure to return capital version
			while(quality.equals("")) {
				String attemptQuality =  scanner.nextLine();
				quality = CreateProductLogic.verifyQuality(attemptQuality);
			}
			
			while(price == 0) {
				System.out.println("\nPlease enter a Zip Code (Format Ex: '#####')");
				String attamptPrice = scanner.nextLine();
				price = CreateProductLogic.verfiyPrice(attamptPrice);
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
