package com.revature.client;

import java.util.ArrayList; 
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.Employee;
import com.revature.models.LineItem;
import com.revature.models.Order;
import com.revature.models.StoreFront;
import com.revature.util.Logger;
import com.revature.util.Logger.LogLevel;

import DataLayer.CustomerDAO;
import DataLayer.LineItemDAO;
import DataLayer.OrderDAO;
import DataLayer.StoreFrontsDAO;

public class BusinessLogic {

	
	//Account Logic - Line 31
	//Store Logic - Line 121
	//CUSTOMER ORDER LOGIC - Line 391
	//General Methods-Text based
	//ACCOUNT CREATION METHODS
	
	public static String verifyName(String name) {
		Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		boolean b = m.find();
		if(name.contains(" ")) {
			System.out.println("Names Cannot Contain Spaces, Numbers, or Special Characters");
			return "";
		} else if(b) {
			System.out.println("Names Cannot Contain Spaces, Numbers, or Special Characters");
			return "";
		} else if(name.length() < 1) {
			System.out.println("You Must Input a Name");
			return "";
		} else {
			String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
			return formattedName;
		}
	}
	
	public static String verifyUsername(String username) {
		Pattern p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(username);
		boolean b = m.find();
		CustomerDAO customerDao = new CustomerDAO();		
		
		if(customerDao.getByName(username) != null) {
			System.out.println("That Username is Already in Use. Please Try Another");
			return "";
		}
		
		if(username.contains(" ")) {
			System.out.println("Usernames Cannot Contain Spaces or Special Characters");
			return "";
		} else if(b) {
			System.out.println("Usernames Cannot Contain Spaces or Special Characters");
			return "";
		} else if(username.length() < 1) {
			System.out.println("You Must Input a Username");
			return "";
		} else if(username.length() < 8 || username.length() > 16){
			System.out.println("Username Must Be Between 8-16 Characters Long");
			return "";
		} else {
			String formattedName = username.toLowerCase();
			return formattedName;
		}
	}
	
	public static String verifyPassword(String password) {
		if(password.contains(" ")) {
			System.out.println("Passwords Cannot Contain Spaces");
			return "";
		} else if(password.length() < 1) {
			System.out.println("You Must Input a Password");
			return "";
		} else if(password.length() < 8 || password.length() > 20){
			System.out.println("Password Must Be Between 8-20 Characters Long");
			return "";
		} else {
			return password;
		}
	}
	
	public static String verifyEmail(String email) {
		if(email.length() < 1) {
			System.out.println("You Must Input an Email Address");
			return "";
		} 
		if (email.contains("@") && email.charAt(0) != '@' && email.charAt(email.length()-1) != '@') {
			String[] emailArray = email.split("@");
			if(emailArray.length > 2) {
				System.out.println("Not a Valid Email Address");
				return "";
			} else if (email.contains(" ")) {
				System.out.println("Not a Valid Email Address");
				return "";
			} 
			if(emailArray[1].startsWith(".")) {
				System.out.println("Not a Valid Email Address");
				return "";
			}
			if(emailArray[1].endsWith(".com") || emailArray[1].endsWith(".net")) {
				return email;
			} else {
				System.out.println("Not a Valid Email Address");
				return "";
			}
		} else {
			System.out.println("Not a Valid Email Address");
			return "";
		}
	}
	
	//DUCKIE SHOP METHODS
	public static void addDuckiesToCart(Scanner scanner, Customer customer, Order order, StoreFront storeFront) {
		boolean isRunning = true;
		boolean isRunning2 = true;
		int inCartQuantity = 0;
		Duckie duckieToAdd = null;
		int previousIndex = 0;
		LineItem lineToAdd = new LineItem(null, 0);
		LineItem tempItem = null;
		int tempQuantity = 0;
		
		if(storeFront.getLineItems().size() == 0) {
			System.out.println(UIUXBusinessLogic.createSpaceBanner(storeFront.getName() + " is fresh out of Duckies! Please try again later"));
			isRunning = false;
		}
		
		while(isRunning) {
			//find out what duck to add
			while(duckieToAdd == null) {
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Select the Duckie you wish to add to your cart"));
				for(int i=0; i<storeFront.getLineItems().size();i++) {
					if(i+1 < 10) {
						System.out.println("[" + (i+1) + "]  " + UIUXBusinessLogic.formatProductsIfIndex(storeFront.getLineItems().get(i).getDuckie(), storeFront.getLineItems().get(i).getQuantity()));
					} else {
						System.out.println("[" + (i+1) + "] " + UIUXBusinessLogic.formatProductsIfIndex(storeFront.getLineItems().get(i).getDuckie(), storeFront.getLineItems().get(i).getQuantity()));
					}
				}
				System.out.println(UIUXBusinessLogic.dashes());
				try {
					int desiredDuckie = scanner.nextInt();
					if(desiredDuckie < 1 || desiredDuckie > storeFront.getLineItems().size()) {
						inValidOption();
					} else if (storeFront.getLineItems().get(desiredDuckie-1).getQuantity() == 0) {
						System.out.println(UIUXBusinessLogic.createSpaceBanner("That duckie is no longer available to add to cart"));
					} else {
						duckieToAdd = storeFront.getLineItems().get(desiredDuckie-1).getDuckie();
						previousIndex = desiredDuckie-1;
						if(order.containsDuckie(duckieToAdd)) {
							for(LineItem lineItem : order.getLineItemArray()) {
								if(lineItem.getDuckie().getName().equals(duckieToAdd.getName())) {
									inCartQuantity = lineItem.getQuantity();
									tempItem = lineItem;
								}
							}
						}
					}	
				} catch (Exception e) {
					inValidOption();
					scanner.nextLine();
					continue;
				}
			} //finished as of now
			
			//find out how many to add
			while(lineToAdd.getQuantity() == 0) {
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Enter the number of " + duckieToAdd.getName() + "s you wish to add"));
				try {
					int desiredQuantity = scanner.nextInt();
					if(desiredQuantity < 1) {
						inValidOption();
					} else if(desiredQuantity > storeFront.getLineItems().get(previousIndex).getQuantity()) {
						System.out.println(UIUXBusinessLogic.createSpaceBanner("There are not enough of that duckie in stock to add to your cart"));
						System.out.println(UIUXBusinessLogic.centerText(storeFront.getLineItems().get(previousIndex).getDuckie().getName() + "s currently in stock: " + storeFront.getLineItems().get(previousIndex).getQuantity()));
					} else {
						lineToAdd.setQuantity(desiredQuantity);
						tempQuantity = desiredQuantity;
					}
				} catch (Exception e) {
					inValidOption();
					scanner.nextLine();
					continue;
				}
			}	
			scanner.nextLine();
			
			while(isRunning2) {
				if(lineToAdd.getQuantity() == 0) {
					System.out.println(UIUXBusinessLogic.createSpaceBanner("No duckies were added to card"));
					break;
				}
				System.out.println(UIUXBusinessLogic.createSpaceBanner("You wish to add " + lineToAdd.getQuantity() + 
						" " + duckieToAdd.getName() + 
						"(s) to cart?[Y/N]"));
				String reply = scanner.nextLine();
				if(reply.toLowerCase().equals("y")) {
					if(order.containsDuckie(duckieToAdd)) {
						storeFront.getLineItems().get(previousIndex).decreaseQuanity(tempQuantity);
						order.increaseLineItemQuantity(duckieToAdd, lineToAdd.getQuantity());
						System.out.println(UIUXBusinessLogic.createSpaceBanner(lineToAdd.getQuantity() + " " + 
								duckieToAdd.getName() + 
								"(s) added to Cart"));
								printCart(order);
								isRunning2 = false;
					
					} else {
						storeFront.getLineItems().get(previousIndex).decreaseQuanity(tempQuantity);
						lineToAdd.setDuckie(duckieToAdd);
						order.addLineItem(lineToAdd);
						System.out.println(UIUXBusinessLogic.createSpaceBanner(lineToAdd.getQuantity() + " " + 
								duckieToAdd.getName() + 
								"(s) added to Cart"));
						printCart(order);
						isRunning2 = false;
					}					
				} else if(reply.toLowerCase().equals("n")) {
					System.out.println(UIUXBusinessLogic.createSpaceBanner(lineToAdd.getQuantity() + " " + 
							duckieToAdd.getName() + 
							"(s) were not added to Cart"));
					printCart(order);
					isRunning2 = false;
				} else {
					inValidOption();
				}
				
				isRunning = false;
			}			
			isRunning = false;
		}
	}
	
	public static void removeDuckiesFromCart(Scanner scanner, Customer customer, Order order, StoreFront storeFront) {
		boolean isRunning = true;
		int quantityToRemove = -1;
		Duckie duckieToRemove = null;
		int previousReply = 0;
		
		while(isRunning) {

			while(duckieToRemove == null) {
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Which duckies would you like to remove from your cart?"));
				for(int i = 0; i< order.getLineItemArray().size(); i++) {
					int indexPlus = i+1;
					System.out.println("[" + indexPlus + "]" + " " + 
							order.getLineItemArray().get(i).getDuckie().getName() +
							" x " + order.getLineItemArray().get(i).getQuantity());
				}
				System.out.println(UIUXBusinessLogic.dashes());
				try {
					int reply = scanner.nextInt();
					if(reply < 1 || reply > order.getLineItemArray().size()) {
						inValidOption();
					} else {
						duckieToRemove = order.getLineItemArray().get(reply-1).getDuckie();
						previousReply = reply;
					}
				} catch (Exception e) {
					inValidOption();
					scanner.nextLine();
					continue;
				}
				
			}

			while(quantityToRemove == -1) {
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Enter the number of " + duckieToRemove.getName() + "s you wish to remove"));
				try {
					int reply = scanner.nextInt();
					if(reply < 1) {
						System.out.println(UIUXBusinessLogic.createSpaceBanner("You must select an amount greater than 0"));
					} else if(reply > order.getLineItemArray().get(previousReply-1).getQuantity()){
						System.out.println(UIUXBusinessLogic.createSpaceBanner("You do not have that many duckies of that type in your cart"));
					} else {
						quantityToRemove = reply;
						scanner.nextLine();

					}
				} catch (Exception e) {
					inValidOption();
					scanner.nextLine();
					continue;
				}
			}
			
			
			System.out.println(UIUXBusinessLogic.createSpaceBanner("You wish to remove " + quantityToRemove + " " + 
			duckieToRemove.getName() +
			"(s) from your cart?[Y/N]"));
		
			switch (scanner.nextLine()) {
			case "Y":
			case "y":
				for(LineItem lineItem : storeFront.getLineItems()) {
					if(lineItem.getDuckie().getId() == duckieToRemove.getId()) {
						lineItem.increaseQuanity(quantityToRemove);

					}
				}

				order.removeFromLineItemQuantity(duckieToRemove, quantityToRemove);
				System.out.println(UIUXBusinessLogic.createSpaceBanner(quantityToRemove + " " + duckieToRemove.getName() + "(s) were removed from your cart"));
				printCart(order);
				isRunning = false;
				break;
			case "N":
			case "n":
				System.out.println(UIUXBusinessLogic.createSpaceBanner(quantityToRemove + " " + duckieToRemove.getName() + "(s) were not removed from your cart"));
				printCart(order);
				isRunning = false;
				break;
			default:
				inValidOption();
				break;
			}		
		}
	}
	
	public static void printCart(Order order) {
		if(order.getLineItemArray().isEmpty()) {
			System.out.println(UIUXBusinessLogic.createSpaceBanner("Your cart is empty"));
		} else {
			System.out.println(UIUXBusinessLogic.createSpaceBanner("Your Current Duckie Cart:"));
			order.printOrder();
			System.out.println(UIUXBusinessLogic.dashes());
		}
	}

	public static void printAllDuckies(StoreFront storeFront) {
		LineItemDAO lineItemDao = new LineItemDAO();
		for(LineItem lineItem : storeFront.getLineItems()) {
			System.out.println(lineItem);
			System.out.println(UIUXBusinessLogic.dashes());

		}
	}

	public static boolean finalizeOrder(Scanner scanner, Customer customer, Order order, StoreFront storeFront) {
		boolean isRunning = true;
		CustomerDAO customerDao = new CustomerDAO();
		StoreFrontsDAO storeFrontDao = new StoreFrontsDAO();
		OrderDAO orderDao = new OrderDAO();
		
		while(isRunning) {
			System.out.println(UIUXBusinessLogic.createSpaceBanner("Your current order:"));
			order.printOrderWithTax();
			System.out.println(UIUXBusinessLogic.createSpaceBanner("Would you like to finalize your order?[Y/N]"));
			switch (scanner.nextLine()) {
			case "Y":
			case "y":
				customer.addOrder(order);
				storeFront.addOrder(order);
				orderDao.upDateOrdersAndStoreItems(customer, order, storeFront);
				
				
				
				Logger.getLogger().log(LogLevel.info, "\n" + order.toString() + "\npurchased by customer: " + customer.getUsername() + "\n");
				System.out.println(UIUXBusinessLogic.createSpaceBanner("YOUR ORDER OF"));
				order.printOrder();
				System.out.println(UIUXBusinessLogic.dashes());
				System.out.println(UIUXBusinessLogic.centerText("HAS BEEN FINALIZED!"));
				System.out.println(UIUXBusinessLogic.centerText("Thank you for shopping with " + storeFront.getName() + "!"));
				System.out.println(UIUXBusinessLogic.dashes());
				return true;
			case "N":
			case "n":
				System.out.println(UIUXBusinessLogic.createSpaceBanner("YOUR ORDER OF"));
				order.printOrder();
				System.out.println(UIUXBusinessLogic.createSpaceBanner("WAS NOT FINALIZED"));

				return false;
			default:
				break;
			}
			
		}
		return false;	
	}

	//CUSTOMER ORDER LOGIC
	
	public static void viewCustomerOrders(Customer customer) {
		if(customer.getOrderList().size() == 0) {
			System.out.println(UIUXBusinessLogic.createSpaceBanner("You have no previously placed orders..."));
		} else {
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println(UIUXBusinessLogic.centerText("YOUR PREVIOUSLY PLACED ORDERS"));
			System.out.println(UIUXBusinessLogic.centerText("--------------------------------------------"));
			for(Order order : customer.getOrderList()) {
				UIUXBusinessLogic.formatOrder(order);
				if(order != customer.getOrderList().get(customer.getOrderList().size()-1)) {
					System.out.println(" ");
				}
			}
			System.out.println(" ");
		}
	}
	
	public static void viewCustomerOrdersForEmployees(Customer customer) {
		if(customer.getOrderList().size() == 0) {
			System.out.println(UIUXBusinessLogic.createSpaceBanner("User " + customer.getUsername() + " has no previously placed orders..."));
		} else {
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println(UIUXBusinessLogic.centerText(customer.getUsername().toUpperCase() + "'S PREVIOUSLY PLACED ORDERS"));
			System.out.println(UIUXBusinessLogic.centerText("--------------------------------------------"));
			for(Order order : customer.getOrderList()) {
				UIUXBusinessLogic.formatOrder(order);
				if(order != customer.getOrderList().get(customer.getOrderList().size()-1)) {
					System.out.println(" ");
				}
			}
			System.out.println(" ");
		}
	}
	
	
	private static void alterOrders(Scanner scanner, Customer customer, Employee employee) {

		boolean isRunning = true;
		while(isRunning) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Would you like to alter " + customer.getUsername() + "'s orders?[Y/N]");
			System.out.println("-------------------------------------------------------------------------------");
			scanner.nextLine(); //clear scanner
			String reply = scanner.nextLine();
			if(reply.toLowerCase().equals("y")) {
				//move to menu method
				isRunning = false;
				break;
			} else if(reply.toLowerCase().equals("n")) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("User " + customer.getUsername() + "'s orders were not altered");
				System.out.println("-------------------------------------------------------------------------------");
				isRunning = false;
				break;
			} else {
				inValidOption();

			}
			
		}
		
	}

	public static StoreFront selectStore(Scanner scanner) {
		StoreFrontsDAO storeFrontsDao = new StoreFrontsDAO();
		StoreFront storeFront = null;
		ArrayList<StoreFront> storeFronts = storeFrontsDao.getAll();
		int response = -1;
		while(response == -1) {
			System.out.println(UIUXBusinessLogic.createBanner("STORE SELECTION MENU"));
			for(int i=0; i<storeFronts.size();i++) {
				System.out.println("[" + (i+1) + "] " + storeFronts.get(i).getName());
			}
			System.out.println(UIUXBusinessLogic.dashes());
			try {
				response = scanner.nextInt();
				if(response < 1 || response > storeFronts.size()+1) {
					inValidOption();
					response = -1;
				} else {
					storeFront = storeFronts.get(response-1);
					scanner.nextLine();
					return storeFront;
				}
			} catch (Exception e) {
				inValidOption();
				response = -1;
				scanner.nextLine();
			}
		}
		return storeFront;	
	}
	
	
	public static void inValidOption() {
		System.out.println(UIUXBusinessLogic.dashes());
		System.out.println("Not a valid option...");
	}
	
	
	public static boolean isInt(String string) {

    	try {
    	    int x = Integer.parseInt(string);
    	    return true;
    	} catch (NumberFormatException e) {
    	    return false;
    	}
	}
	
	public static int convertToInt(String string) {

    	int x = Integer.parseInt(string);
    	return x;
	}
	
	
}


//"[^a-z0-9 ]"