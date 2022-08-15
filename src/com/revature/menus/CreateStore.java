package com.revature.menus;

import java.util.Scanner;

import com.revature.client.CreateStoreFrontLogic;
import com.revature.client.UIUXBusinessLogic;
import com.revature.models.Employee;
import com.revature.models.StoreFront;
import com.revature.util.Logger;
import com.revature.util.Logger.LogLevel;

import DataLayer.DAO;
import DataLayer.StoreFrontsDAO;

public class CreateStore {

	public static void createStore(Scanner scanner, Employee employee) {
		
		DAO<StoreFront> storeFrontDao = new StoreFrontsDAO();
		boolean isRunning = true;
		String name = "";
		String street = "";
		String city = "";
		String state = "";
		int zip = 0;
		String reply  ="";
		
		while(isRunning) {
			
			System.out.println(UIUXBusinessLogic.createBanner("STOREFRONT REGISTRY"));
	
			while(name.equals("")) {
				System.out.println("\nPlease enter a StoreFront Name. (Max Characters: 25)");
				String attemptName =  scanner.nextLine();
				name = CreateStoreFrontLogic.verifyStoreFrontName(attemptName);
			}
			
			while(street.equals("")) {
				System.out.println("\nPlease enter a Street Address (Ex: 1234 Dillan Street) (Max Characters: 25)");
				String attemptStreet = scanner.nextLine();
				street = CreateStoreFrontLogic.verifyStreetAddress(attemptStreet);
			}
			
			while(city.equals("")) {
				System.out.println("\nPlease enter a City. (Max Characters: 25)");
				String attemptCity =  scanner.nextLine();
				city = CreateStoreFrontLogic.verifyCity(attemptCity);
			}
			
			while(state.equals("")) {
				System.out.println("\nPlease enter a State. (Format Ex: 'AZ')"); //make sure to return capital version
				String attemptState =  scanner.nextLine();
				state = CreateStoreFrontLogic.verifyState(attemptState);
			}
			
			while(zip == 0) {
				System.out.println("\nPlease enter a Zip Code (Format Ex: '#####')");
				String attemptZip = scanner.nextLine();
				zip = CreateStoreFrontLogic.verifyZip(attemptZip);
			}
			
			while(reply.equals("")) {
				System.out.println("\nStoreFront Name: " + name  
						+"\nStreet Address: " + street
						+"\nCity: " + city
						+"\nState: " + state
						+"\nZip: " + zip
						+"\nDoes this look correct to you? \n[Y/N]"
						+ "\n" + UIUXBusinessLogic.dashes());
				String Address = street + ", " + city + ", " + state + ", " + zip;
				reply = scanner.nextLine();
				switch (reply) {
				case "Y":	
				case "y":
					StoreFront storeFront = new StoreFront(name, Address);
					storeFrontDao.addInstance(storeFront);
					System.out.println(UIUXBusinessLogic.createSpaceBanner("StoreFront " + storeFront.getName() + " successfully created!"));
					Logger.getLogger().log(LogLevel.info, "\nAdmin User " + employee.getUsername() + " Created New StoreFront: " + storeFront.toString() + "\n");
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
