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
import DataLayer.StoreFrontsDAO;

public class BusinessLogic {

	
	//Account Logic - Line 25
	//Store Logic - Line 121
	//Employee Logic - Line 465
	
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
	public static void addDuckiesToCart(Scanner scanner, Customer customer, Order order, StoreFront goodDuckinDuckies) {
		boolean isRunning = true;
		boolean isRunning2 = true;
		int choicesNumber = 0;
		int inCartQuantity = 0;
		Duckie duckieToAdd = null;
		//int quantityToAdd = 0;
		LineItem lineToAdd = new LineItem(null, 0);
		
		while(isRunning) {
			//find out what duck to add
			while(duckieToAdd == null) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Please select the Duckie you wish to add to your cart");
				System.out.println("-------------------------------------------------------------------------------");
				for(Duckie duckie : goodDuckinDuckies.getDuckieList()) {
					System.out.println("[" + duckie.getDuckNumber() + "] " + duckie.getName());
					choicesNumber++;
				}
				System.out.println("-------------------------------------------------------------------------------");
				try {
					int desiredDuckie = scanner.nextInt();
					if(desiredDuckie < 1 || desiredDuckie > choicesNumber) {
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Not a valid option.");
						System.out.println("-------------------------------------------------------------------------------");
						choicesNumber = 0;
					} else {
						duckieToAdd = goodDuckinDuckies.getDuckieList().get(desiredDuckie-1);
						if(order.containsDuckie(duckieToAdd)) {
							for(LineItem lineItem : order.getLineItemArray()) {
								if(lineItem.getDuckie() == duckieToAdd) {
									inCartQuantity = lineItem.getQuantity();
								}
							}
						}
					}	
				} catch (Exception e) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Not a valid option");
					System.out.println("-------------------------------------------------------------------------------");
					choicesNumber = 0;
					scanner.nextLine();
					continue;
				}
			} //finished as of now
			
			//find out how many to add
			while(lineToAdd.getQuantity() == 0) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Please select the amount of duckies you wish to add (Enter a number)");
				System.out.println("-------------------------------------------------------------------------------");
				try {
					int desiredQuantity = scanner.nextInt();
					if(desiredQuantity < 1) {
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Not a valid quantity");
						System.out.println("-------------------------------------------------------------------------------");
					} else if(desiredQuantity + inCartQuantity > goodDuckinDuckies.getDuckieList().get(duckieToAdd.getDuckNumber()-1).getQuantity()) {
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("There are not enough of that duckie in stock");
						System.out.println("-------------------------------------------------------------------------------");
						break;
					} else {
						lineToAdd.setQuantity(desiredQuantity);
					}
				} catch (Exception e) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Not a valid option");
					System.out.println("-------------------------------------------------------------------------------");
					scanner.nextLine();
					continue;
				}
			}	
			scanner.nextLine();
			
			while(isRunning2) {
				if(lineToAdd.getQuantity() == 0) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("No duckies were added to card");
					System.out.println("-------------------------------------------------------------------------------");
					break;
				}
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("You wish to add " + lineToAdd.getQuantity() + 
						" " + duckieToAdd.getName() + 
						"(s) to cart?[Y/N]");
				System.out.println("-------------------------------------------------------------------------------");
				String reply = scanner.nextLine();
				
				if(reply.toLowerCase().equals("y")) {
					if(order.containsDuckie(duckieToAdd)) {
						order.increaseLineItemQuantity(duckieToAdd, lineToAdd.getQuantity());
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println(lineToAdd.getQuantity() + " " + 
								duckieToAdd.getName() + 
								"(s) added to Cart!");
						System.out.println("-------------------------------------------------------------------------------");						
								printCart(order);
								isRunning2 = false;
					
					} else {
						lineToAdd.setDuckie(duckieToAdd);
						order.addLineItem(lineToAdd);
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println(lineToAdd.getQuantity() + " " + 
						duckieToAdd.getName() + 
						"(s) added to Cart!");
						System.out.println("-------------------------------------------------------------------------------");
						printCart(order);
						isRunning2 = false;
					}					
				} else if(reply.toLowerCase().equals("n")) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println(lineToAdd.getQuantity() + " " + 
							duckieToAdd.getName() + 
							"(s) were not added to your cart");
					System.out.println("-------------------------------------------------------------------------------");
					printCart(order);
					isRunning2 = false;
				} else {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Not a valid option");
					System.out.println("-------------------------------------------------------------------------------");
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
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Which duckies would you like to remove from your cart?(Enter a number)");
				System.out.println("-------------------------------------------------------------------------------");
				for(int i = 0; i< order.getLineItemArray().size(); i++) {
					int indexPlus = i+1;
					System.out.println("[" + indexPlus + "]" + " " + 
							order.getLineItemArray().get(i).getDuckie().getName() +
							" x " + order.getLineItemArray().get(i).getQuantity());
				}
				System.out.println("-------------------------------------------------------------------------------");
				try {
					int reply = scanner.nextInt();
					if(reply < 1 || reply > order.getLineItemArray().size()) {
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("That is not a valid option");
						System.out.println("-------------------------------------------------------------------------------");
					} else {
						duckieToRemove = order.getLineItemArray().get(reply-1).getDuckie();
						previousReply = reply;
					}
				} catch (Exception e) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Not a valid option");
					System.out.println("-------------------------------------------------------------------------------");
					scanner.nextLine();
					continue;
				}
				
			}

			while(quantityToRemove == -1) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("How many " + duckieToRemove.getName() + "s would you like to remove from your cart?(Enter a number)");
				System.out.println("-------------------------------------------------------------------------------");
				try {
					int reply = scanner.nextInt();
					if(reply < 1) {
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("You must select an amount greater than 0");
						System.out.println("-------------------------------------------------------------------------------");
					} else if(reply > order.getLineItemArray().get(previousReply-1).getQuantity()){
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("You do not have that many duckies of that type in your cart");
						System.out.println("-------------------------------------------------------------------------------");
					} else {
						quantityToRemove = reply;
					}
				} catch (Exception e) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Not a valid option");
					System.out.println("-------------------------------------------------------------------------------");
					scanner.nextLine();
					continue;
				}
			}
			
			scanner.nextLine();
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("You wish to remove " + quantityToRemove + " " + 
			duckieToRemove.getName() +
			" from your cart?[Y/N]");
			System.out.println("-------------------------------------------------------------------------------");
		
			switch (scanner.nextLine()) {
			case "Y":
			case "y":
				order.removeFromLineItemQuantity(duckieToRemove, quantityToRemove);
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println(quantityToRemove + " " + duckieToRemove.getName() + "(s) were removed from your cart");
				System.out.println("-------------------------------------------------------------------------------");
				printCart(order);
				isRunning = false;
				break;
			case "N":
			case "n":
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println(quantityToRemove + " " + duckieToRemove.getName() + "(s) were not removed from your cart");
				System.out.println("-------------------------------------------------------------------------------");
				printCart(order);
				isRunning = false;
				break;
			default:
				break;
			}		
		}
	}
	
	public static void printCart(Order order) {
		if(order.getLineItemArray().isEmpty()) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Your cart is empty");
			System.out.println("-------------------------------------------------------------------------------");
		} else {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Your Current Duckie Cart:");
			System.out.println("-------------------------------------------------------------------------------");
			order.printOrder();
			System.out.println("-------------------------------------------------------------------------------");
		}
	}

	public static void printAllDuckies(StoreFront storeFront) {
		for(Duckie duckie : storeFront.getDuckieList()) {
			System.out.println(duckie);
			System.out.println("-------------------------------------------------------------------------------");

		}
	}

	public static boolean finalizeOrder(Scanner scanner, Customer customer, Order order, StoreFront storeFront) {
		boolean isRunning = true;
		CustomerDAO customerDao = new CustomerDAO();
		StoreFrontsDAO storeFrontDao = new StoreFrontsDAO();
		
		while(isRunning) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Your current order:");
			System.out.println("-------------------------------------------------------------------------------");
			order.printOrderWithTax();
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Would you like to finalize your order?[Y/N]");
			System.out.println("-------------------------------------------------------------------------------");
			switch (scanner.nextLine()) {
			case "Y":
			case "y":
				customer.addOrder(order);
				storeFront.addOrder(order);
				customerDao.updateInstance(customer);
				storeFrontDao.updateInstance(storeFront);
				for(Duckie duckie : storeFront.getDuckieList()) {
					for(LineItem lineItem : order.getLineItemArray()) {
						if(duckie.getName().equals(lineItem.getDuckie().getName())) {
							duckie.removeQuantity(lineItem.getQuantity());
						}
					}
				}
				Logger.getLogger().log(LogLevel.info, "\n" + order.toString() + "\npurchased by customer: " + customer.getUsername() + "\n");
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Your order of:");
				System.out.println("-------------------------------------------------------------------------------");
				order.printOrder();
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("has been finalized!");
				System.out.println("Thank you for shopping with " + storeFront.getName() + "!");
				System.out.println("-------------------------------------------------------------------------------");
				return true;
			case "N":
			case "n":
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Your order of:");
				System.out.println("-------------------------------------------------------------------------------");
				order.printOrder();
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("was not finalized!");
				System.out.println("-------------------------------------------------------------------------------");

				return false;
			default:
				break;
			}
			
		}
		return false;	
	}

	//EMPLOYEE LOGIC
	
	public static void viewCustomerOrders(Scanner scanner, ArrayList<Customer> customersToView, Employee employee) {
		boolean isRunning = true;
		while(isRunning) {
			System.out.println("-------------------------------------------------------------------------------");
			for(int i = 0; i<customersToView.size(); i++) {
				System.out.println("[" + (i+1) + "]" + customersToView.get(i).getUsername());
			}
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Select a customer to view their placed orders(Enter a Number)");
			System.out.println("-------------------------------------------------------------------------------");			
			try {
				int response = scanner.nextInt();
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println(customersToView.get(response-1).getUsername() + "'s previous orders:");
				System.out.println("       -");
				for(Order order : customersToView.get(response-1).getOrderList()) {
					System.out.println("Purchased at: " + order.getStoreAddress());
					order.printOrderWithTax();
					if(order != customersToView.get(response-1).getOrderList().get(customersToView.get(response-1).getOrderList().size()-1)) {
						System.out.println(" ");
					}
				}
				Customer customer = customersToView.get(response-1);
//				if(customer.getOrderList().size() == 0) {
//					
//				} else {
//					
//				}
				
				
				alterOrders(scanner, customer, employee);
				scanner.nextLine();
				isRunning = false;
				System.out.println("-------------------------------------------------------------------------------");
			} catch(Exception e) {
				System.out.println("Not a vlid option");
				break;
			}
			scanner.nextLine();
			isRunning = false;

			
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
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Not a valid option");
				System.out.println("-------------------------------------------------------------------------------");
			}
			
		}
		
	}

	public static StoreFront selectStore(Scanner scanner) {
		StoreFrontsDAO storeFrontsDao = new StoreFrontsDAO();
		StoreFront storeFront = null;
		ArrayList<StoreFront> storeFronts = storeFrontsDao.getAll();
		int response = -1;
		while(response == -1) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Please choose from the stores below to browse Rubber Duckie Catalogs!: ");
			System.out.println("-------------------------------------------------------------------------------");
			for(int i=0; i<storeFronts.size();i++) {
				System.out.println("[" + (i+1) + "] " + storeFronts.get(i).getName());
			}
			System.out.println("-------------------------------------------------------------------------------");
			try {
				response = scanner.nextInt();
				if(response < 1 || response > storeFronts.size()+1) {
					System.out.println("Not a valid input");
					response = -1;
				} else {
					storeFront = storeFronts.get(response-1);
					scanner.nextLine();
					return storeFront;
				}
			} catch (Exception e) {
				System.out.println("Not a valid input");
				response = -1;
				scanner.nextLine();
			}
		}
		return storeFront;		
	}
}


//"[^a-z0-9 ]"