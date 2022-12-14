package com.revature.client;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

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
import DataLayer.DuckieDAO;
import DataLayer.OrderDAO;
import DataLayer.StoreFrontsDAO;
import DataLayer.StoreItemDAO;

public class EmployeeSpecificBusinessLogic {

	//GENERAL STORE METHODS - LINE 24
	
	
	
	
	//GENERAL STORE METHODS
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
					BusinessLogic.inValidOption();
					response = -1;
				} else {
					storeFront = storeFronts.get(response-1);
					scanner.nextLine();
					return storeFront;
				}
			} catch (Exception e) {
				BusinessLogic.inValidOption();
				response = -1;
				scanner.nextLine();
			}
		}
		return storeFront;		
	}
	
	
	//EMPLOYEE STORE MENU OPTIONS
	//STOREFRONT ITEM METHODS
	public static void viewAllStoreProducts(Scanner scanner, StoreFront storeFront) {
		
		System.out.println(UIUXBusinessLogic.createBanner(storeFront.getName().toUpperCase() + " AVAILABLE PRODUCTS FOR PURCHASE"));
		for(int i = 0; i<storeFront.getLineItems().size(); i++) {
			System.out.println(UIUXBusinessLogic.formatProducts(storeFront.getLineItems().get(i).getDuckie(), storeFront.getLineItems().get(i).getQuantity()));
		}
		System.out.println(UIUXBusinessLogic.createSpaceBanner("Would you like to view Product Details for a Duckie?[Y/N]"));
		
		boolean isRunning = true;
		while(isRunning) {			
			switch (scanner.nextLine()) {
			case "Y":
			case "y":			
				int response = -1;
				while(response == -1) {
					System.out.println(UIUXBusinessLogic.createSpaceBanner("Please select a Duckie to view"));
					for(int i = 0; i<storeFront.getLineItems().size(); i++) {
						if(i+1 < 10) {
							System.out.println("[" + (i+1) + "]  " + 				
								storeFront.getLineItems().get(i).getDuckie().getName());
						} else {
							System.out.println("[" + (i+1) + "] " + 				
									storeFront.getLineItems().get(i).getDuckie().getName());
						}
						
					}
					System.out.println(UIUXBusinessLogic.dashes());
					try {
						response = scanner.nextInt();
						if(response < 1 || response > storeFront.getLineItems().size()) {
							BusinessLogic.inValidOption();
							response = -1;
						} else {
							System.out.println(UIUXBusinessLogic.dashes());
							System.out.println(storeFront.getLineItems().get(response-1).toString());
							System.out.println(UIUXBusinessLogic.dashes());
							scanner.nextLine();
							System.out.println(UIUXBusinessLogic.createSpaceBanner("Would you like to view Product Details for another Duckie?[Y/N]"));
						}
						
					} catch (Exception e) {
						BusinessLogic.inValidOption();
						response = -1;
						scanner.nextLine();
					}
				}
				break;
			case "N":
			case "n":
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Returning to Menu Options..."));
				isRunning = false;
				break;
			default:
				BusinessLogic.inValidOption();
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Would you like to view Product Details for a Duckie?[Y/N]"));
				break;
			}
		}
	}
	
	
	public static void newViewAllStoreProducts(Scanner scanner, StoreFront storeFront) {
		
		
		ArrayList<LineItem> itemList = new ArrayList<>();
		LinkedList<ArrayList<LineItem>> duckieLists = new LinkedList<>();
		boolean isRunning = true;
		int currentList = 0;
		String reply = "";
		
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
		
		while(isRunning) {
			ArrayList<LineItem> currentItems = duckieLists.get(currentList);
			System.out.println(UIUXBusinessLogic.createBanner(storeFront.getName().toUpperCase() + " AVAILABLE PRODUCTS FOR PURCHASE"));
			while(reply.equals("")) {
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
				System.out.println(UIUXBusinessLogic.centerText("[x] return to menu"));
				System.out.println(UIUXBusinessLogic.dashes());

				reply = scanner.nextLine();
				
				if(BusinessLogic.isInt(reply)) {
					for(int i = 0; i<currentItems.size(); i++) {
						if(BusinessLogic.convertToInt(reply)-1 == i) {
							boolean inLoop = true;
							while(inLoop) {
								System.out.println(currentItems.get(i));
								System.out.println(UIUXBusinessLogic.createSpaceBanner("[x] return to Products"));
								if(scanner.nextLine().toLowerCase().equals("x")) {
									inLoop = false;
								}
							}
						}
					}
					BusinessLogic.inValidOption();
				} else {
					if(reply.toLowerCase().equals("x")) {
						isRunning = false;
					} else if (reply.toLowerCase().equals("q")){
						if(currentList == 0) {
							break;
						} else {
							currentList -= 1;
							break;
						}
					} else if (reply.toLowerCase().equals("e")){
						if(currentList == duckieLists.size()-1) {
							break;
						} else {
							currentList += 1;
							break;
						}
					} else {
						BusinessLogic.inValidOption();
						reply = "";
						break;
					}	
				}
			}
			reply = "";
		}
	}
	
	public static LineItem chooseProductToAlter(Scanner scanner, Employee employee, StoreFront storeFront) {
		LineItem lineItemToReturn = null;
		ArrayList<LineItem> itemList = new ArrayList<>();
		LinkedList<ArrayList<LineItem>> duckieLists = new LinkedList<>();
		boolean isRunning = true;
		int response = -1;
		int currentList = 0;
		String reply = "";
		
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
		
		if(duckieLists.get(0).size() == 0) {
			System.out.println("there are no products to view");
			return null;
		}

		while(isRunning) {
			ArrayList<LineItem> currentItems = duckieLists.get(currentList);
			System.out.println(UIUXBusinessLogic.createBanner(storeFront.getName().toUpperCase() + " AVAILABLE PRODUCTS FOR PURCHASE"));
			while(reply.equals("")) {
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
				System.out.println(UIUXBusinessLogic.centerText("[x] return to menu"));
				System.out.println(UIUXBusinessLogic.dashes());

				reply = scanner.nextLine();
				
				if(BusinessLogic.isInt(reply)) {
					for(int i = 0; i<currentItems.size(); i++) {
						if(BusinessLogic.convertToInt(reply)-1 == i) {
							lineItemToReturn = currentItems.get(i);
							return lineItemToReturn;	
						}
					}
					BusinessLogic.inValidOption();
				} else {
					if(reply.toLowerCase().equals("x")) {
						isRunning = false;
					} else if (reply.toLowerCase().equals("q")){
						if(currentList == 0) {
							break;
						} else {
							currentList -= 1;
							break;
						}
					} else if (reply.toLowerCase().equals("e")){
						if(currentList == duckieLists.size()-1) {
							break;
						} else {
							currentList += 1;
							break;
						}
					} else {
						BusinessLogic.inValidOption();
						reply = "";
						break;
					}	
				}
			}
			reply = "";
		}
		return lineItemToReturn;	
	}	
	
	public static void alterProduct(Scanner scanner, Employee employee, LineItem lineItem, StoreFront storeFront) {
		boolean isRunning = true;
		StoreItemDAO storeItemsDao = new StoreItemDAO();
		
		while(isRunning) {
			Duckie duckie = lineItem.getDuckie();
			System.out.println(UIUXBusinessLogic.createSpaceBanner("Current " + lineItem.getDuckie().getName() + " stock: " + lineItem.getQuantity()));
			System.out.println("[1] Increase Stock"
					+ "\n[2] Decrease Stock");
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println("[x] Do Not Alter");
			System.out.println(UIUXBusinessLogic.dashes());
			
			String reply = scanner.nextLine();
			if(BusinessLogic.isInt(reply)) {
				switch (reply) {
				case "1":
					increaseStock(scanner, employee, storeFront, lineItem);
					break;
				case "2":
					decreaseStock(scanner, employee, storeFront, lineItem);
					break;
				default:
					break;
				}  
				
			} else {
				if(reply.toLowerCase().equals("x")) {
					System.out.println(UIUXBusinessLogic.dashes());
					System.out.println("Returning to " + storeFront.getName() + " Employee Menu");
					System.out.println(UIUXBusinessLogic.dashes());
					isRunning = false;
				}
			}
			
			
		}
	}
	
	public static void increaseStock(Scanner scanner, Employee employee, StoreFront storeFront, LineItem lineItem) {
		StoreItemDAO storeItemDao = new StoreItemDAO();
		Duckie duckie = lineItem.getDuckie();
		int response = -1;
		boolean isRunning = true;

		while(isRunning) {
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println(UIUXBusinessLogic.centerText("Current " + duckie.getName() + " stock: " + lineItem.getQuantity()));
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println(UIUXBusinessLogic.centerText("Input the number of " + duckie.getName() + "s to add"));
			System.out.println(UIUXBusinessLogic.centerText("(or)"));
			System.out.println(UIUXBusinessLogic.centerText("[x] Return to Stock Options"));
			System.out.println(UIUXBusinessLogic.dashes());
			
			String reply = scanner.nextLine();
			if(BusinessLogic.isInt(reply)) {
				if (BusinessLogic.convertToInt(reply) < 1){
					BusinessLogic.inValidOption();
				} else {
					response = BusinessLogic.convertToInt(reply);
					while (isRunning){
						System.out.println(UIUXBusinessLogic.createSpaceBanner("Add " + response + " " + duckie.getName() + "(s) to stock?[Y/N]"));
						switch (scanner.nextLine()) {
						case "Y":
						case "y":
							lineItem.setQuantity(lineItem.getQuantity() + response);
							storeItemDao.updateInstance(lineItem, storeFront);
							Logger.getLogger().log(LogLevel.info, "\nAdmin User: " + employee.getUsername() + ""
									+ " increased the stock of " + lineItem.getDuckie().getName() + " by " + response
									+ " for storefront " + storeFront.getName() + "\n");

							System.out.println(UIUXBusinessLogic.createSpaceBanner(response + " " + duckie.getName() + "s added to stock"));
							isRunning = false;
							break;
						case "N":
						case "n":
							response = -1;
							isRunning = false;
							break;
						default:
							BusinessLogic.inValidOption();
							break;
						}
					}
				}
			} else {
				if(reply.toLowerCase().equals("x")) {
					System.out.println(UIUXBusinessLogic.createSpaceBanner("Returning to Stock Options..."));
					isRunning = false;
				} else {
					BusinessLogic.inValidOption();
				}
			}
		}		
	}
	
	public static void decreaseStock(Scanner scanner, Employee employee, StoreFront storeFront, LineItem lineItem) {
		StoreItemDAO storeItemDao = new StoreItemDAO();
		Duckie duckie = lineItem.getDuckie();
		int response = -1;
		boolean isRunning = true;
		
		if(lineItem.getQuantity() == 0) {
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println(UIUXBusinessLogic.centerText("There are no " + lineItem.getDuckie().getName() + "s to remove"));
			System.out.println(UIUXBusinessLogic.dashes());
			isRunning = false;
		}

		while(isRunning) {
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println(UIUXBusinessLogic.centerText("Current " + duckie.getName() + " stock: " + lineItem.getQuantity()));
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println(UIUXBusinessLogic.centerText("Input the number of " + duckie.getName() + "s to remove"));
			System.out.println(UIUXBusinessLogic.centerText("(or)"));
			System.out.println(UIUXBusinessLogic.centerText("[x] Return to Stock Options"));
			System.out.println(UIUXBusinessLogic.dashes());
			
			String reply = scanner.nextLine();
			if(BusinessLogic.isInt(reply)) {
				if (BusinessLogic.convertToInt(reply) < 1){
					BusinessLogic.inValidOption();
				} else if(BusinessLogic.convertToInt(reply) > lineItem.getQuantity()) {
					System.out.println(UIUXBusinessLogic.dashes());
					System.out.println(UIUXBusinessLogic.centerText("Not enough of that duckie to remove"));
				} else {
					response = BusinessLogic.convertToInt(reply);
					while (isRunning){
						System.out.println(UIUXBusinessLogic.createSpaceBanner("Remove " + response + " " + duckie.getName() + "(s) from stock?[Y/N]"));
						switch (scanner.nextLine()) {
						case "Y":
						case "y":
							lineItem.setQuantity(lineItem.getQuantity() - response);
							storeItemDao.updateInstance(lineItem, storeFront);
							System.out.println(UIUXBusinessLogic.createSpaceBanner(response + " " + duckie.getName() + "s removed to stock"));
							Logger.getLogger().log(LogLevel.info, "\nAdmin User: " + employee.getUsername() + ""
									+ " decreased the stock of " + lineItem.getDuckie().getName() + " by " + response
									+ " for storefront " + storeFront.getName() + "\n");
							isRunning = false;
							break;
						case "N":
						case "n":
							response = -1;
							isRunning = false;
							break;
						default:
							BusinessLogic.inValidOption();
							break;
						}
					}
				}
			} else {
				if(reply.toLowerCase().equals("x")) {
					System.out.println(UIUXBusinessLogic.createSpaceBanner("Returning to Stock Options..."));
					isRunning = false;
				} else {
					BusinessLogic.inValidOption();
				}
			}
		}		
	}
	
	public static boolean addNewProducts(Scanner scanner, Employee employee, StoreFront storeFront) {
		boolean isRunning = true;
		DecimalFormat df = new DecimalFormat("#.00");
		DuckieDAO duckieDao = new DuckieDAO();
		StoreItemDAO storeItemDao = new StoreItemDAO();
		StoreFrontsDAO storeDao = new StoreFrontsDAO();
		
		ArrayList<Duckie> storeDuckies = duckieDao.getAllByStoreId(storeFront);
		ArrayList<Duckie> allDuckies = duckieDao.getAll();	
		ArrayList<Duckie> duckies = new ArrayList<>();
		
		String reply = "";
		String reply2 = "";
		int currentList = 0;
		ArrayList<Duckie> newDuckies = new ArrayList<>();
		LinkedList<ArrayList<Duckie>> duckieLists = new LinkedList<>();

		boolean duckieFound = false;
		for(Duckie allDuckie : allDuckies) {
			for(Duckie storeDuckie : storeDuckies) {
				if(allDuckie.getId() == storeDuckie.getId()) {
					duckieFound = true;
				}
			}
			if(duckieFound == false) {
				duckies.add(allDuckie);
			} else {
				duckieFound = false;
			}
		}
		
		for(int i = 0; i<duckies.size(); i++) {
			if(newDuckies.size() < 10) {
				newDuckies.add(duckies.get(i));
			} else {
				duckieLists.add(newDuckies);
				newDuckies = new ArrayList<>();
				newDuckies.add(duckies.get(i));	
			}
		} 
		duckieLists.add(newDuckies);
	
		if(allDuckies.size() == storeDuckies.size()) {
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println(UIUXBusinessLogic.centerText(storeFront.getName() + " already contains all available Duckies."));
			System.out.println(UIUXBusinessLogic.centerText("There are no new products available to be added at this time."));	
			System.out.println(UIUXBusinessLogic.dashes());
			isRunning = false;
		}

		Duckie duckie = null;
		int quantity = 0;
		
		while(isRunning) {
			
			ArrayList<Duckie> currentItems = duckieLists.get(currentList);
			System.out.println(UIUXBusinessLogic.createBanner("Please choose a Duckie to add to the " + storeFront.getName() + " Product Catalog"));
			while(reply.equals("")) {
				if(duckieLists.size() > 1 && (currentList != 0) && currentList != duckieLists.size()-1) {
					UIUXBusinessLogic.centerTwoString("<- Last [q]", "[e] Next ->");
				} else if (duckieLists.size() > 1 && (currentList == 0)) {
					UIUXBusinessLogic.centerTwoString("           ", "[e] Next ->");
				} else if (duckieLists.size() > 1 && (currentList == duckieLists.size()-1)) {
					UIUXBusinessLogic.centerTwoString("<- Last [q]", "           ");
				}
				System.out.println(UIUXBusinessLogic.dashes());
				for(int i = 0; i<currentItems.size(); i++) {
					System.out.println(UIUXBusinessLogic.formatProductsWithIndexAndCost(i, currentItems.get(i)));
				}				

				if(currentItems.size()<10) {
					int temp = currentItems.size();
					while(temp<10) {
						System.out.println(" ");
						temp++;
					}
				}
				System.out.println(UIUXBusinessLogic.dashes());
				System.out.println(UIUXBusinessLogic.centerText("[x] return to menu"));
				System.out.println(UIUXBusinessLogic.dashes());

				reply = scanner.nextLine();
				
				if(BusinessLogic.isInt(reply)) {
					for(int i = 0; i<currentItems.size(); i++) {
						if(BusinessLogic.convertToInt(reply)-1 == i) {
							duckie = currentItems.get(i);
							while(quantity == 0) {
								System.out.println(UIUXBusinessLogic.dashes());
								System.out.println(UIUXBusinessLogic.centerText("How many " + duckie.getName() + "s would you like to add to"));	
								System.out.println(UIUXBusinessLogic.centerText("the " + storeFront.getName() + " Product Catalog?(Input a number - MAX: 100)"));
								System.out.println(UIUXBusinessLogic.dashes());
								try {
									quantity = scanner.nextInt();
									if((quantity < 1) || (quantity > 100)) {
										BusinessLogic.inValidOption();
										scanner.nextLine();
										quantity = 0;
									} else {
										duckie = currentItems.get(BusinessLogic.convertToInt(reply)-1);
										scanner.nextLine();
									}
								} catch (Exception e) {
									BusinessLogic.inValidOption();
									scanner.nextLine();
									quantity = 0;
								}		
							}
							
							while(isRunning) {
								
								System.out.println(UIUXBusinessLogic.createSpaceBanner("Add " + quantity + " " + duckie.getName() + "(s) to " + storeFront.getName() + " product catalog?[Y/N]"));
								reply2 = scanner.nextLine();
								if(reply2.toLowerCase().equals("y")) {
									storeItemDao.addInstance(storeFront, new LineItem(duckie, quantity));
									Logger.getLogger().log(LogLevel.info, "\nAdmin User: " + employee.getUsername() + ""
											+ " added product " + duckie.getName() + " to storefront " + storeFront.getName() + ""
													+ " with a stock of " + quantity + "\n");
									System.out.println(UIUXBusinessLogic.dashes());
									System.out.println(UIUXBusinessLogic.centerText(duckie.getName() + " was successfully added"));	
									System.out.println(UIUXBusinessLogic.centerText("to " + storeFront.getName() + "'s Product Catalog!"));
									System.out.println(UIUXBusinessLogic.dashes());
									storeDao.initializeAllDuckies(storeFront);
									return true;
								} else if(reply2.toLowerCase().equals("n")) {
									System.out.println(UIUXBusinessLogic.dashes());
									System.out.println(UIUXBusinessLogic.centerText(duckie.getName() + " was not added to " + storeFront.getName() + "'s Product Catalog!"));	
									System.out.println(UIUXBusinessLogic.dashes());
									return true;
								} else {
									BusinessLogic.inValidOption();
								}
							}	
						}
					}
				} else {
					if(reply.toLowerCase().equals("x")) {
						return false;
					} else if (reply.toLowerCase().equals("q")){
						if(currentList == 0) {
							break;
						} else {
							currentList -= 1;
							break;
						}
					} else if (reply.toLowerCase().equals("e")){
						if(currentList == duckieLists.size()-1) {
							break;
						} else {
							currentList += 1;
							break;
						}
					} else {
						BusinessLogic.inValidOption();
						reply = "";
						break;
					}	
				}
			}
			if(isRunning) {
				reply = "";
			}
			
		}
		return false;
	}
	
	public static boolean removeProducts(Scanner scanner, Employee employee, StoreFront storeFront) {
		boolean isRunning = true;
		DecimalFormat df = new DecimalFormat("#.00");
		StoreItemDAO storeItemDao = new StoreItemDAO();
		StoreFrontsDAO storeDao = new StoreFrontsDAO();
		ArrayList<LineItem> lineItems = storeFront.getLineItems();
		Duckie duckie = null;
		int currentList = 0;
		String reply = "";
		ArrayList<LineItem> newItems = new ArrayList<>();
		LinkedList<ArrayList<LineItem>> linkedList = new LinkedList<>();
		//make rotation, make return boolean to decide if it keeps running
		
		if(lineItems.size() == 0) {
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println(UIUXBusinessLogic.centerText(storeFront.getName() + " does not currently contain any available Duckies."));	
			System.out.println(UIUXBusinessLogic.centerText("You can not remove products at this time."));
			System.out.println(UIUXBusinessLogic.dashes());
			return false;
		}
		
		for(int i = 0; i<lineItems.size(); i++) {
			if(newItems.size() < 10) {
				newItems.add(lineItems.get(i));
			} else {
				linkedList.add(newItems);
				newItems = new ArrayList<>();
				newItems.add(lineItems.get(i));	
			}
		} 
		linkedList.add(newItems);
		
		while(isRunning) {	
			lineItems = linkedList.get(currentList);
			int response = 0;
			while(response == 0) {
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Please choose a Duckie to remove from the " + storeFront.getName() + " Product Catalog"));
				if(linkedList.size() > 1 && (currentList != 0) && currentList != linkedList.size()-1) {
					UIUXBusinessLogic.centerTwoString("<- Last [q]", "[e] Next ->");
				} else if (linkedList.size() > 1 && (currentList == 0)) {
					UIUXBusinessLogic.centerTwoString("           ", "[e] Next ->");
				} else if (linkedList.size() > 1 && (currentList == linkedList.size()-1)) {
					UIUXBusinessLogic.centerTwoString("<- Last [q]", "           ");
				}
				for(int i = 0; i<lineItems.size(); i++) {
					System.out.println(UIUXBusinessLogic.formatProductsWithIndexAndCost(i, lineItems.get(i).getDuckie()));
				}
				if(lineItems.size()<10) {
					int temp = lineItems.size();
					while(temp<10) {
						System.out.println(" ");
						temp++;
					}
				}
				System.out.println(UIUXBusinessLogic.dashes());
				System.out.println(UIUXBusinessLogic.centerText("[x] return to menu"));
				System.out.println(UIUXBusinessLogic.dashes());

				reply = scanner.nextLine();
				if(BusinessLogic.isInt(reply)) {
					if(BusinessLogic.convertToInt(reply) > lineItems.size() || BusinessLogic.convertToInt(reply) < 1) {
						BusinessLogic.inValidOption();
					}
					for(int i = 0; i<lineItems.size(); i++) {
						if(BusinessLogic.convertToInt(reply)-1 == i) {
							duckie = lineItems.get(i).getDuckie();
							response = BusinessLogic.convertToInt(reply);
							break;
						}					
					}	
				} else {
					if(reply.toLowerCase().equals("x")) {
						return false;
					} else if (reply.toLowerCase().equals("q")){
						if(currentList == 0) {
							break;
						} else {
							currentList -= 1;
							break;
						}
					} else if (reply.toLowerCase().equals("e")){
						if(currentList == linkedList.size()-1) {
							break;
						} else {
							currentList += 1;
							break;
						}
					} else {
						BusinessLogic.inValidOption();
						reply = "";
						break;
					}
				}		
			}
			
			while(isRunning && duckie != null) {
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Remove " + duckie.getName() + " from " + storeFront.getName() + " product catalog?[Y/N]"));
				reply = scanner.nextLine();
				if(reply.toLowerCase().equals("y")) {
					storeItemDao.deleteInstance(duckie, storeFront);
					Logger.getLogger().log(LogLevel.info, "\nAdmin User: " + employee.getUsername() + ""
							+ " removed product " + duckie.getName() + " from storefront " + storeFront.getName() + "\n");
					System.out.println(UIUXBusinessLogic.dashes());
					System.out.println(UIUXBusinessLogic.centerText(duckie.getName() + " was successfully removed"));
					System.out.println(UIUXBusinessLogic.centerText("from the " + storeFront.getName() + "'s Product Line!"));
					System.out.println(UIUXBusinessLogic.dashes());
					storeDao.initializeAllDuckies(storeFront);
					return true;
				} else if (reply.toLowerCase().equals("n")) {
					System.out.println(UIUXBusinessLogic.dashes());
					System.out.println(UIUXBusinessLogic.centerText(duckie.getName() + " was not removed from " + storeFront.getName() + "'s Product Catalog!"));	
					System.out.println(UIUXBusinessLogic.dashes());
					return true;
				} else {
					BusinessLogic.inValidOption();
				}
			}
		}
		return true;
	}
	
	public static void profitReports(Scanner scanner, Employee employee, StoreFront storeFront) {

		OrderDAO orderDao = new OrderDAO();
		ArrayList<Order> orders = orderDao.getAllByStorefrontId(storeFront);
		Map<Duckie, Integer> duckieSales = new HashMap<>();
		DecimalFormat df = new DecimalFormat("#.00");
		boolean inLoop = true;
		
		while(inLoop) {
			for(Order order :  orders) {
				for(LineItem lineItem : order.getLineItemArray()) {
					if(!duckieSales.containsKey(lineItem.getDuckie())) {
						duckieSales.put(lineItem.getDuckie(), lineItem.getQuantity());
					} else {
						duckieSales.put(lineItem.getDuckie(), duckieSales.get(lineItem.getDuckie()) + lineItem.getQuantity());				}
				}
			}
			
			System.out.println(UIUXBusinessLogic.createSpaceBanner(storeFront.getName().toUpperCase() + " PROFIT REPORTS"));
	
			Duckie bestSellingDuckie = null;
			double totalSales = 0;
			int currentValue = 0;
			 Iterator<Entry<Duckie, Integer> > new_Iterator
	         = duckieSales.entrySet().iterator();
	
	     // Iterating every set of entry in the HashMap
		     while (new_Iterator.hasNext()) {
		         Map.Entry<Duckie, Integer> new_Map
		             = (Map.Entry<Duckie, Integer>)
		                   new_Iterator.next();
		
		         // Displaying HashMap
		         System.out.println(UIUXBusinessLogic.formatProfits(new_Map.getKey(), new_Map.getValue()));
		         totalSales += new_Map.getKey().getPrice() * new_Map.getValue();
		         if(new_Map.getValue() > currentValue) {
		        	 currentValue = new_Map.getValue();
		        	 bestSellingDuckie = new_Map.getKey();
		         }
		     }
		    
		     if(bestSellingDuckie == null) {
		    	 System.out.println(UIUXBusinessLogic.centerText("NO PROFITS TO REPORT"));
		     } else {
		    	System.out.println("\n" + UIUXBusinessLogic.centerText("Best Selling Product: " + bestSellingDuckie.getName()));
				System.out.println(UIUXBusinessLogic.centerText( "Quantity Sold: " + currentValue + " - Total Product Revenue: $" + df.format(bestSellingDuckie.getPrice() * currentValue)));
			    System.out.println(UIUXBusinessLogic.centerText("---------------------------"));
				System.out.println(UIUXBusinessLogic.centerText("Total Sales: $" + df.format(totalSales)));
 
		     }
			System.out.println(UIUXBusinessLogic.dashes());
			System.out.println("[x] Return to Menu Options");
			System.out.println(UIUXBusinessLogic.dashes()); 
			if(scanner.nextLine().toLowerCase().equals("x")) {
				inLoop = false;
			}
		}
			
	}
	
	
	public static void printStoreFrontOrders(Scanner scanner, StoreFront storeFront) {
		OrderDAO orderDao = new OrderDAO();
		ArrayList<Order> orders = orderDao.getAllByStorefrontId(storeFront);
		boolean inLoop = true;
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
				System.out.println(UIUXBusinessLogic.dashes());
				
				System.out.println(UIUXBusinessLogic.dashes());
				System.out.println("[x] Return to Menu Options");
				System.out.println(UIUXBusinessLogic.dashes());

				if(scanner.nextLine().toLowerCase().equals("x")) {
					inLoop = false;
				}
			}
				
		}
	}

	//CUSTOMER RELATED METHODS
	public static Customer customerSelecter(Scanner scanner) {
		CustomerDAO customerDao = new CustomerDAO();
		LinkedList<ArrayList<Customer>> customerLists = customerDao.getAllIntoLinkedList();
		int currentList = 0;
		String reply = "";
		boolean isRunning = true;
		System.out.println(customerLists.size());
		
		while(isRunning) {
			ArrayList<Customer> customers = customerLists.get(currentList);
			System.out.println(UIUXBusinessLogic.createBanner("CUSTOMER SELECTION MENU"));
			while(reply.equals("")) {
				if(customerLists.size() > 1) {
					UIUXBusinessLogic.centerTwoString("<- Last [q]", "[e] Next ->");
				}
				System.out.println(UIUXBusinessLogic.dashes());
				for(int i = 0; i<customers.size(); i++) {
					if(i+1 < 10) {
						System.out.println("[" + (i+1) + "]  " + 				
								customers.get(i).getUsername());
					} else {
						System.out.println("[" + (i+1) + "] " + 				
								customers.get(i).getUsername());
					}
				}				

				if(customers.size()<10) {
					int temp = 1;
					while(temp<10) {
						System.out.println(" ");
						temp++;
					}
				}
				System.out.println(UIUXBusinessLogic.dashes());
				System.out.println(UIUXBusinessLogic.centerText("[x] return to menu"));
				System.out.println(UIUXBusinessLogic.dashes());

				reply = scanner.nextLine();
				
				if(BusinessLogic.isInt(reply)) {
					for(int i = 0; i<customers.size(); i++) {
						if(BusinessLogic.convertToInt(reply)-1 == i) {
							return customers.get(i);
						}
					}
					BusinessLogic.inValidOption();
				} else {
					if(reply.toLowerCase().equals("x")) {
						return null;
					} else if (reply.toLowerCase().equals("q")){
						if(currentList == 0) {
							currentList = customerLists.size()-1;
							break;
						} else {
							currentList -= 1;
							break;
						}
					} else if (reply.toLowerCase().equals("e")){
						if(currentList == customerLists.size()-1) {
							currentList = 0;
							break;
						} else {
							currentList += 1;
							break;
						}
					} else {
						BusinessLogic.inValidOption();
						reply = "";
						break;
					}	
				}
			}
			reply = "";
		}
		return null;
	}
	
	public static Order chooseCustomerOrders(Scanner scanner, Employee employee, Customer customer) {

		Order returnOrder = null;
		boolean isRunning = true;
		ArrayList<Order> orders = customer.getOrderList();
		
		while(isRunning) {
			if(customer.getOrderList().isEmpty()) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Customer does not have any previous orders to view");
				System.out.println("-------------------------------------------------------------------------------");
				scanner.nextLine();
				return null;
			} else {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println(customer.getUsername() + "'s previous orders:");
				System.out.println("       -");
				for(int i = 0; i<customer.getOrderList().size(); i++) {
					System.out.println("Purchased at: " + orders.get(i).getStoreAddress());
					orders.get(i).printOrderWithTax();
					if(orders.get(i) != orders.get(customer.getOrderList().size()-1)) {
						System.out.println(" ");
					}
				}	
				System.out.println("-------------------------------------------------------------------------------");
				scanner.nextLine();
			}

			String reply = "";
			while(reply.equals("")) {
				System.out.println("Whould you like to alter " + customer.getUsername() + "'s previously placed orders?[Y/N]");
				System.out.println("-------------------------------------------------------------------------------");
				reply = scanner.nextLine();
				
				if(reply.toLowerCase().equals("y")) {
					int response = -1;
					while(response == -1) {
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Select a customer order to view(Enter a Number)");
						System.out.println("-------------------------------------------------------------------------------");
						for(int i = 0; i<customer.getOrderList().size(); i++) {
							System.out.println("[" + (i+1) + "] " + customer.getOrderList().get(i));
							if(orders.get(i) != orders.get(customer.getOrderList().size()-1)) {
								System.out.println(" ");
							}
						}	
						
						
						try { 
							response = scanner.nextInt();
							if(response < 1 || response > customer.getOrderList().size()) {
								BusinessLogic.inValidOption();
								response = -1;
								scanner.nextLine();
							} else {
								returnOrder = customer.getOrderList().get(response-1);
								scanner.nextLine();//this
								return returnOrder;
							}
						} catch (Exception e) {
							BusinessLogic.inValidOption();
							response = -1;
							scanner.nextLine();
						}	
						
					}
					isRunning = false;
					break;
				} else if(reply.toLowerCase().equals("n")) {
					System.out.println(customer.getUsername() + "'s previously placed orders were not altered");
					isRunning = false;
					break;
				} else {
					BusinessLogic.inValidOption();
					reply = "";
				}
			}			
				isRunning = false;
				System.out.println("-------------------------------------------------------------------------------");
			isRunning = false;
		}
		return returnOrder;	
	}

	
	public static  String alterCustomerOrderType(Scanner scanner, Order order) {
		
		boolean isRunning = true;
		
		while(isRunning) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Select from the options below for altering the order: ");
			order.printOrder();
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("[1] Add Items \n[2] Remove Items \n[3] Delete Order \n[4] Return to Menu");
			System.out.println("-------------------------------------------------------------------------------");
			//Add items/remove items/delete order
						
			switch (scanner.nextLine()) {
			case "1":
				return "add";
			case "2":
				return "remove";
			case "3":
				return "delete";
			case "4":
				return null;
			default:
				break;
			}		
		}
		return null;
	}


	public static void addToOrder(Scanner scanner, Order order, Employee employee, Customer customer) {
		StoreFrontsDAO storeFrontDao = new StoreFrontsDAO();
		StoreFront storeFront = storeFrontDao.getByAddress(order.getStoreAddress());
		LineItem lineItem = null;
		
		//select line item to add to
		//select quantity to add (if exists in store amount)
		//increase cust amount, decrease store
		int response = -1;
		while(response == -1) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Please select the item you wish to alter");
			System.out.println("-------------------------------------------------------------------------------");
			for(int i = 0; i<order.getLineItemArray().size(); i++) {
				System.out.println("[" + (i+1) + "] " + order.getLineItemArray().get(i)); //maybe will have to format this
			}
			System.out.println("-------------------------------------------------------------------------------");
			try {
				response = scanner.nextInt();
				if(response < 1 || response > order.getLineItemArray().size()) {
					BusinessLogic.inValidOption();
					scanner.nextInt();
					response = -1;
				} else {
					lineItem = order.getLineItemArray().get(response-1);
				}
				
			} catch (Exception e) {
				BusinessLogic.inValidOption();
				scanner.nextLine();
				response = -1;
			}
		}		
	}


	public static void removeFromOrder(Scanner scanner, Order order, Employee employee) {
	}

}
	

