package com.revature.client;

import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.menus.CreateAccount;
import com.revature.menus.CustomerLogin;
import com.revature.menus.EmployeeLogin;
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
		} else if(name.length() > 50) {
			System.out.println("Names Cannot Exceed 50 Characters");
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
		if(email.length() > 50) {
			System.out.println("Emails Cannot Exceed 50 Characters");
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
		int inCartQuantity = 0;
		int tempQuantity = 0;
		ArrayList<LineItem> itemList = new ArrayList<>();
		LinkedList<ArrayList<LineItem>> duckieLists = new LinkedList<>();
		int currentList = 0;
		String reply = "";
		
		//get linked list of array lists
		for(int i = 0; i<storeFront.getLineItems().size(); i++) {
			if(itemList.size() < 10) {
				itemList.add(storeFront.getLineItems().get(i));
			} else {
				duckieLists.add(itemList);
				itemList = new ArrayList<>();
				itemList.add(storeFront.getLineItems().get(i));	
			}
		} 
		duckieLists.add(itemList);
		
		
		if(duckieLists.get(currentList).size() == 0) {
			System.out.println(UIUXBusinessLogic.createSpaceBanner(storeFront.getName() + " is fresh out of Duckies! Please try again later"));
			isRunning = false;
		}
		
		while(isRunning) {
			
			//START LOOP 1 - SELECT DUCKIE
			Duckie duckieToAdd = null;
			int previousIndex = 0;
			LineItem tempItem = null;
			
			while(duckieToAdd == null && isRunning == true) {
				
				//PRINTS THE MENU 
				ArrayList<LineItem> currentItems = duckieLists.get(currentList);
				System.out.println(UIUXBusinessLogic.createBanner("SELECT A DUCKIE  TO ADD TO CART"));

					if(duckieLists.size() > 1 && (currentList != 0) && currentList != duckieLists.size()-1) {
						UIUXBusinessLogic.centerTwoString("<- Last [q]", "[e] Next ->");
					} else if (duckieLists.size() > 1 && (currentList == 0)) {
						UIUXBusinessLogic.centerTwoString("           ", "[e] Next ->");
					} else if (duckieLists.size() > 1 && (currentList == duckieLists.size()-1)) {
						UIUXBusinessLogic.centerTwoString("<- Last [q]", "           ");
					}
					System.out.println(UIUXBusinessLogic.dashes());
					for(int i = 0; i<currentItems.size(); i++) {
						System.out.println(UIUXBusinessLogic.formatProductsWithIndex(i, currentItems.get(i).getDuckie(), currentItems.get(i).getQuantity()));
					}				

					if(currentItems.size()<10) {
						int temp = currentItems.size();
						while(temp<10) {
							System.out.println(" ");
							temp++;
						}
					}
					System.out.println(UIUXBusinessLogic.dashes());
					System.out.println(UIUXBusinessLogic.centerText("[x] Return to Menu Options"));
					System.out.println(UIUXBusinessLogic.dashes());
				
					reply = scanner.nextLine();
					
					if(BusinessLogic.isInt(reply)) {
						for(int i = 0; i<currentItems.size(); i++) {
							if(BusinessLogic.convertToInt(reply)-1 == i) {
								if(currentItems.get(i).getQuantity() == 0) {
									System.out.println(UIUXBusinessLogic.createSpaceBanner(currentItems.get(i).getDuckie().getName() + " is no longer available to add to cart"));
								} else {
									duckieToAdd = currentItems.get(i).getDuckie();
									previousIndex = BusinessLogic.convertToInt(reply)-1;
									if(order.containsDuckie(duckieToAdd)) {
										for(LineItem lineItem : order.getLineItemArray()) {
											if(lineItem.getDuckie().getName().equals(duckieToAdd.getName())) {
												inCartQuantity = lineItem.getQuantity();
												tempItem = lineItem;
											}
										}
									}
								}
								
							}
						}
					} else {
						if(reply.toLowerCase().equals("x")) {
							isRunning = false;
							break;
						} else if (reply.toLowerCase().equals("q")){
							if(currentList == 0) {
							} else {
								currentList -= 1;
							}
						} else if (reply.toLowerCase().equals("e")){
							if(currentList == duckieLists.size()-1) {
							} else {
								currentList += 1;
							}
						} else {
							inValidOption();
						}	
					}
			} 
			
			//START LOOP 2 - SELECT QUANTITY
			LineItem lineToAdd = new LineItem(null, 0);
			ArrayList<LineItem> currentItems = duckieLists.get(currentList);

			while(lineToAdd.getQuantity() == 0 && isRunning == true) {
				
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Enter the number of " + duckieToAdd.getName() + "s you wish to add"));
				try {
					int desiredQuantity = scanner.nextInt();
					if(desiredQuantity < 1) {
						inValidOption();
					} else if(desiredQuantity > currentItems.get(previousIndex).getQuantity()) {
						System.out.println(UIUXBusinessLogic.createSpaceBanner("There are not enough of that duckie in stock to add to your cart"));
						break;
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
			if(isRunning == true) {
				scanner.nextLine();
			}
			
			
			//LOOP 3 - CONFIRM
			boolean isRunning2 = true;
			while(isRunning2 == true && lineToAdd.getQuantity() != 0) {
				if(lineToAdd.getQuantity() == 0) {
					System.out.println(UIUXBusinessLogic.createSpaceBanner("No duckies were added to card"));
					break;
				}
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Add " + lineToAdd.getQuantity() + 
						" " + duckieToAdd.getName() + 
						"(s) to cart?[Y/N]"));
				reply = scanner.nextLine();
				if(reply.toLowerCase().equals("y")) {
					if(order.containsDuckie(duckieToAdd)) {
						currentItems.get(previousIndex).decreaseQuanity(tempQuantity);
						order.increaseLineItemQuantity(duckieToAdd, lineToAdd.getQuantity());
						System.out.println(UIUXBusinessLogic.createSpaceBanner(lineToAdd.getQuantity() + " " + 
								duckieToAdd.getName() + 
								"(s) added to Cart"));
								//printCart(scanner, order);
								isRunning2 = false;
					
					} else {
						currentItems.get(previousIndex).decreaseQuanity(tempQuantity);
						lineToAdd.setDuckie(duckieToAdd);
						order.addLineItem(lineToAdd);
						System.out.println(UIUXBusinessLogic.createSpaceBanner(lineToAdd.getQuantity() + " " + 
								duckieToAdd.getName() + 
								"(s) added to Cart"));
						//printCart(scanner, order);
						isRunning2 = false;
					}					
				} else if(reply.toLowerCase().equals("n")) {
					System.out.println(UIUXBusinessLogic.createSpaceBanner(lineToAdd.getQuantity() + " " + 
							duckieToAdd.getName() + 
							"(s) were not added to Cart"));
					//printCart(scanner, order);
					isRunning2 = false;
				} else {
					inValidOption();
				}
			}			
		}
	}
		
	
	public static void removeDuckiesFromCart(Scanner scanner, Customer customer, Order order, StoreFront storeFront) {
		boolean isRunning = true;
		int quantityToRemove = -1;
		Duckie duckieToRemove = null;
		int previousReply = 0;
		
		while(isRunning) {

			while(duckieToRemove == null) {
				if(order.getLineItemArray().size() == 0) {
					System.out.println(UIUXBusinessLogic.dashes());
					System.out.println(UIUXBusinessLogic.centerText("YOUR CART IS EMPTY"));
				} else {
					System.out.println(UIUXBusinessLogic.createBanner("SELECT A DUCKIE TO REMOVE FROM CART"));
				}
				for(int i = 0; i< order.getLineItemArray().size(); i++) {
					System.out.println(UIUXBusinessLogic.formatCartProductsWithIndex(i, order.getLineItemArray().get(i).getDuckie(), order.getLineItemArray().get(i).getQuantity()));
				}
				System.out.println(UIUXBusinessLogic.dashes());
				System.out.println("[x] Return to Menu Options");
				System.out.println(UIUXBusinessLogic.dashes());
				
				
				String reply = scanner.nextLine();
				if(BusinessLogic.isInt(reply)) {
					if(convertToInt(reply) < 1 || convertToInt(reply) > order.getLineItemArray().size()) {
						inValidOption();
					} else {
						duckieToRemove = order.getLineItemArray().get(convertToInt(reply)-1).getDuckie();
						previousReply = convertToInt(reply);
					}					
				} else {
					if(reply.toLowerCase().equals("x")) {
						isRunning = false;
						break;
					}
				}
				
			}

			while(quantityToRemove == -1 && isRunning == true) {
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Enter the number of " + duckieToRemove.getName() + "s you wish to remove"));
				try {
					int reply = scanner.nextInt();
					if(reply < 1) {
						System.out.println(UIUXBusinessLogic.createSpaceBanner("You must select an amount greater than 0"));
						break;
					} else if(reply > order.getLineItemArray().get(previousReply-1).getQuantity()){
						System.out.println(UIUXBusinessLogic.createSpaceBanner("You do not have " + reply + " " + duckieToRemove.getName() + "s in your cart"));
						duckieToRemove = null;
						scanner.nextLine();
						break;
					} else {
						quantityToRemove = reply;
						scanner.nextLine();

					}
				} catch (Exception e) {
					scanner.nextLine();
					inValidOption();
					continue;
				}
			}
			
			if(isRunning && duckieToRemove != null && quantityToRemove != -1) {
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Remove " + quantityToRemove + " " + 
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
							duckieToRemove = null;
							quantityToRemove = -1;
							break;
						case "N":
						case "n":
							System.out.println(UIUXBusinessLogic.createSpaceBanner(quantityToRemove + " " + duckieToRemove.getName() + "(s) were not removed from your cart"));
							duckieToRemove = null;
							quantityToRemove = -1;
							break;
						default:
							inValidOption();
							break;
						}
			}
					
		}
	}
	
	public static void printCart(Scanner scanner, Order order) {
		boolean isRunning = true;
		
			while(isRunning) {
				if(order.getLineItemArray().isEmpty()) {
					System.out.println(UIUXBusinessLogic.createSpaceBanner("YOUR CART IS EMPTY"));
				} else {
					System.out.println(UIUXBusinessLogic.createBanner("YOUR CURRENT DUCKIE CART:"));
					order.printOrder();
					System.out.println(UIUXBusinessLogic.dashes());
				}
				System.out.println("[x] Return to Menu Options");
				System.out.println(UIUXBusinessLogic.dashes());
				if(scanner.nextLine().toLowerCase().equals("x")) {
					isRunning = false;
			}
			
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
			System.out.println(UIUXBusinessLogic.createBanner("YOUR CURRENT ORDER"));
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
	
	public static void viewCustomerOrders(Scanner scanner, Customer customer) {
		boolean isRunning = true;
		
		while(isRunning){
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
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println("[x] Return to Menu Options");
			System.out.println(UIUXBusinessLogic.dashes());

			if(scanner.nextLine().toLowerCase().equals("x")){
				isRunning = false;
			} else {
				inValidOption();
			}
		
		}
	}
	
	public static void viewCustomerOrdersForEmployees(Scanner scanner, Customer customer) {
				
		boolean isRunning = true;
				
		while(isRunning){
		
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
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println("[x] Return to Menu Options");
			System.out.println(UIUXBusinessLogic.dashes());
			if(scanner.nextLine().toLowerCase().equals("x")){
				isRunning = false;
			} else {
				inValidOption();
			}
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
			System.out.println("[x] Return to Menu Options");
			System.out.println(UIUXBusinessLogic.dashes());

			String reply = scanner.nextLine();
			if(isInt(reply)) {
				for(int i=0; i<storeFronts.size();i++) {
					if(BusinessLogic.convertToInt(reply)-1 == i) {
						return storeFronts.get(i);
					}
				}
				inValidOption();
			} else {
				if(reply.toLowerCase().equals("x")) {
					return null;
				} else {
					inValidOption();
				}	
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