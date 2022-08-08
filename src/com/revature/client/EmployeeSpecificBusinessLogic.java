package com.revature.client;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.Employee;
import com.revature.models.LineItem;
import com.revature.models.Order;
import com.revature.models.StoreFront;

import DataLayer.CustomerDAO;
import DataLayer.DuckieDAO;
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
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Please choose from the stores below to Manage: ");
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
	
	
	//EMPLOYEE STORE MENU OPTIONS
	//STOREFRONT ITEM METHODS
	public static void viewAllStoreProducts(Scanner scanner, StoreFront storeFront) {
		
		System.out.println("-------------------------------------------------------------------------------");
		for(int i = 0; i<storeFront.getLineItems().size(); i++) {
			System.out.println("[" + (i+1) + "] " + 				
					storeFront.getLineItems().get(i).getDuckie().getName() + " - " + 
					storeFront.getLineItems().get(i).getQuantity() + " in stock - $" + storeFront.getLineItems().get(i).getDuckie().getPrice());
		}
		System.out.println("-------------------------------------------------------------------------------");
		//give options to select a product to view details on
	}
	
	public static void viewProductDetails(LineItem lineItem) {
		//pass in a product, view it all!
	}
	
	
	public static LineItem chooseProductToAlter(Scanner scanner, Employee employee, StoreFront storeFront) {
		LineItem lineItemToReturn = null;
		int response = -1;
		
		if(storeFront.getLineItems().size() == 0) {
			return null;
		}

		while(response == -1) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Please select a Product you wish to alter");
			System.out.println("-------------------------------------------------------------------------------");
			for(int i = 0; i<storeFront.getLineItems().size(); i++) {
				System.out.println("[" + (i+1) + "] " + 				
						storeFront.getLineItems().get(i).getDuckie().getName() + " - " + 
						storeFront.getLineItems().get(i).getQuantity() + " in stock");
			}
			System.out.println("-------------------------------------------------------------------------------");

			try {
				response = scanner.nextInt();
				if(response < 1 || response > storeFront.getLineItems().size()) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Not a Valid Option");
					scanner.nextLine();
					response = -1;
				} else {
					lineItemToReturn = storeFront.getLineItems().get(response-1);
					scanner.nextLine();
					return lineItemToReturn;
				}		
			} catch (Exception e) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Not a Valid Option");
				scanner.nextLine();
				response = -1;
			}
		}
		return lineItemToReturn;	
	}	
	
	public static void alterProduct(Scanner scanner, Employee employee, LineItem lineItem, StoreFront storeFront) {
		boolean isRunning = true;
		StoreItemDAO storeItemsDao = new StoreItemDAO();
		
		while(isRunning) {
			Duckie duckie = lineItem.getDuckie();
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("How would you like to alter Product: " + lineItem.getDuckie().getName() + "?\n");
			System.out.println("[1] Increase Stock"
					+ "\n[2] Decrease Stock"
					//+ "\n[3] Remove This Product From Product Line"
					+ "\n[3] Do Not Alter");
			System.out.println("-------------------------------------------------------------------------------");
			switch (scanner.nextLine()) {
			case "1":
				increaseStock(scanner, employee, storeFront, lineItem);
				isRunning = false;
				break;
			case "2":
				decreaseStock(scanner, employee, storeFront, lineItem);
				isRunning = false;
				break;
//			case "3":
//				//remove product
//				break;
			case "3":
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Product " + lineItem.getDuckie().getName() + " was not altered");
				System.out.println("-------------------------------------------------------------------------------");
				isRunning = false;
				break;
			default:
				break;
			}
		}
	}
	
	public static void increaseStock(Scanner scanner, Employee employee, StoreFront storeFront, LineItem lineItem) {
		StoreItemDAO storeItemDao = new StoreItemDAO();
		Duckie duckie = lineItem.getDuckie();
		int response = -1;
		
		while(response == -1) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Current " + duckie.getName() + " stock: " + lineItem.getQuantity());
			System.out.println("\nHow many " + duckie.getName() + "s would you like to add?(Input a number)");
			System.out.println("-------------------------------------------------------------------------------");
	
			try {
				response = scanner.nextInt();
				if(response < 1) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Not a Valid Input");
					response = -1;
					scanner.nextLine();
				} else {
					boolean isRunning = true;
					scanner.nextLine();

					while (isRunning){
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Add " + response + " " + duckie.getName() + "(s) to stock?[Y/N]");
						System.out.println("-------------------------------------------------------------------------------");
						
						switch (scanner.nextLine()) {
						case "Y":
						case "y":
							lineItem.setQuantity(lineItem.getQuantity() + response);
							storeItemDao.updateInstance(lineItem, storeFront);
							System.out.println("-------------------------------------------------------------------------------");
							System.out.println(response + " " + duckie.getName() + "s added to stock");
							System.out.println("-------------------------------------------------------------------------------");						
							isRunning = false;
							break;
						case "N":
						case "n":
							response = -1;
							isRunning = false;
							break;
						default:
							System.out.println("-------------------------------------------------------------------------------");
							System.out.println("INVALID OPTION");
							break;
						}
					}	
				}
			} catch (Exception e) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Not a Valid Input");
				response = -1;
				scanner.nextLine();
			}
		}		
	}
	
	public static void decreaseStock(Scanner scanner, Employee employee, StoreFront storeFront, LineItem lineItem) {
		StoreItemDAO storeItemDao = new StoreItemDAO();
		Duckie duckie = lineItem.getDuckie();
		int response = -1;
		
		while(response == -1) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Current " + duckie.getName() + " stock: " + lineItem.getQuantity());
			System.out.println("\nHow many " + duckie.getName() + "s would you like to remove?(Input a number)");
			System.out.println("-------------------------------------------------------------------------------");
	
			try {
				response = scanner.nextInt();
				if(response < 1) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Not a Valid Input");
					response = -1;
					scanner.nextLine();
				} else if(response > lineItem.getQuantity()){
					System.out.println("There are only " + lineItem.getQuantity() + " " + duckie.getName() + "(s) in stock. You may not remove " + response);
					response = -1;
					scanner.nextLine();
				} else {
					boolean isRunning = true;
					scanner.nextLine();

					while (isRunning){
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Remove " + response + " " + duckie.getName() + "(s) from stock?[Y/N]");
						System.out.println("-------------------------------------------------------------------------------");
						
						switch (scanner.nextLine()) {
						case "Y":
						case "y":
							lineItem.setQuantity(lineItem.getQuantity() - response);
							storeItemDao.updateInstance(lineItem, storeFront);
							System.out.println("-------------------------------------------------------------------------------");
							System.out.println(response + " " + duckie.getName() + "s removed to stock");
							System.out.println("-------------------------------------------------------------------------------");						
							isRunning = false;
							break;
						case "N":
						case "n":
							response = -1;
							isRunning = false;
							break;
						default:
							System.out.println("-------------------------------------------------------------------------------");
							System.out.println("INVALID OPTION");
							break;
						}
					}	
				}
			} catch (Exception e) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Not a Valid Input");
				response = -1;
				scanner.nextLine();
			}
		}		
	}
	
	public static void addNewProducts(Scanner scanner, Employee employee, StoreFront storeFront) {
		boolean isRunning = true;
		DuckieDAO duckieDao = new DuckieDAO();
		StoreItemDAO storeItemDao = new StoreItemDAO();
		StoreFrontsDAO storeDao = new StoreFrontsDAO();
		ArrayList<Duckie> storeDuckies = duckieDao.getAllByStoreId(storeFront);
		ArrayList<Duckie> allDuckies = duckieDao.getAll();
		ArrayList<Duckie> duckies = new ArrayList<>();
		
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
	
		if(allDuckies.size() == storeDuckies.size()) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println(storeFront.getName() + " already contains all available Duckies. There is no new products available to be added at this time.");
			System.out.println("-------------------------------------------------------------------------------");
			isRunning = false;
		}

		Duckie duckie = null;
		int quantity = 0;
		
		while(isRunning) {
			
			int response = 0;
			while(response == 0) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Please choose a Duckie to add to " + storeFront.getName() + "'s Product Catalog(Input a number)");
				System.out.println("-------------------------------------------------------------------------------");

				for(int i = 0; i<duckies.size(); i++) {
					System.out.println("[" + (i+1) + "] " + duckies.get(i).getName() 
							+ " - $" + duckies.get(i).getPrice() + " - " + duckies.get(i).getQuality());
				}
				System.out.println("-------------------------------------------------------------------------------");

				try {
					response = scanner.nextInt();
					if(response < 1 || response > duckies.size()) {
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Invalid Input");
						scanner.nextLine();
						response = 0;
					} else {
						duckie = duckies.get(response-1);
						scanner.nextLine();
					}
				} catch (Exception e) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Invalid Input");
					scanner.nextLine();
					response = 0;
				}		
			}
			while(quantity == 0) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("How many " + duckie.getName() + "s would you like to add to " + storeFront.getName() + "? (Input a number - MAX: 100)");
				System.out.println("-------------------------------------------------------------------------------");
				try {
					quantity = scanner.nextInt();
					if((quantity < 1) || (quantity > 100)) {
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Invalid Input");
						scanner.nextLine();
						quantity = 0;
					} else {
						duckie = duckies.get(response-1);
						scanner.nextLine();
					}
				} catch (Exception e) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Invalid Input");
					scanner.nextLine();
					quantity = 0;
				}		
			}

			storeItemDao.addInstance(storeFront, new LineItem(duckie, quantity));
			System.out.println(duckie.getName() + " was successfully added to " + storeFront.getName() + "'s Product Line!");
			storeDao.initializeAllDuckies(storeFront);
			isRunning = false;
		}
	}
	
	public static void removeProducts(Scanner scanner, Employee employee, StoreFront storeFront) {
		boolean isRunning = true;
		DuckieDAO duckieDao = new DuckieDAO();
		StoreItemDAO storeItemDao = new StoreItemDAO();
		StoreFrontsDAO storeDao = new StoreFrontsDAO();
		ArrayList<LineItem> lineItems = storeFront.getLineItems();
		Duckie duckie = null;
		int quantity = 0;
		
		if(lineItems.size() == 0) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println(storeFront.getName() + " does not currently contain any available Duckies. You can not remove products at this time.");
			System.out.println("-------------------------------------------------------------------------------");
			isRunning = false;
		}
		
		
		while(isRunning) {	
			int response = 0;
			while(response == 0) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Please choose a Duckie to remove from " + storeFront.getName() + "'s Product Catalog(Input a number)");
				System.out.println("-------------------------------------------------------------------------------");

				for(int i = 0; i<lineItems.size(); i++) {
					System.out.println("[" + (i+1) + "] " + lineItems.get(i).getDuckie().getName() 
							+ " - $" + lineItems.get(i).getDuckie().getPrice() + " - " + lineItems.get(i).getDuckie().getQuality());
				}
				System.out.println("-------------------------------------------------------------------------------");

				try {
					response = scanner.nextInt();
					if(response < 1 || response > lineItems.size()) {
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Invalid Input");
						scanner.nextLine();
						response = 0;
					} else {
						duckie = lineItems.get(response-1).getDuckie();
						scanner.nextLine();
					}
				} catch (Exception e) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("Invalid Input");
					scanner.nextLine();
					response = 0;
				}		
			}
//			while(quantity == 0) {
//				System.out.println("-------------------------------------------------------------------------------");
//				System.out.println("How many " + duckie.getName() + "s would you like to add to " + storeFront.getName() + "? (Input a number - MAX: 100)");
//				System.out.println("-------------------------------------------------------------------------------");
//				try {
//					quantity = scanner.nextInt();
//					if((quantity < 1) || (quantity > 100)) {
//						System.out.println("-------------------------------------------------------------------------------");
//						System.out.println("Invalid Input");
//						scanner.nextLine();
//						quantity = 0;
//					} else {
//						duckie = duckies.get(response-1);
//						scanner.nextLine();
//					}
//				} catch (Exception e) {
//					System.out.println("-------------------------------------------------------------------------------");
//					System.out.println("Invalid Input");
//					scanner.nextLine();
//					quantity = 0;
//				}		
//			}

			storeItemDao.deleteInstance(duckie, storeFront);
			System.out.println(duckie.getName() + " was successfully  " + storeFront.getName() + "'s Product Line!");
			storeDao.initializeAllDuckies(storeFront);
			isRunning = false;
		}
	}
	
	//CUSTOMER RELATED METHODS
	public static Customer selectCustomer(Scanner scanner) {
		CustomerDAO customerDao = new CustomerDAO();
		ArrayList<Customer> customers = customerDao.getAll();
		Customer customer = null;
		int response = -1;
		boolean isRunning = true;
		
		while(isRunning) {
			while(response == -1) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Select a customer to view their placed orders(Enter a Number)");
				System.out.println("-------------------------------------------------------------------------------");	
				for(int i = 0; i<customers.size(); i++) {
					System.out.println("[" + (i+1) + "]" + customers.get(i).getUsername());
				}	
				System.out.println("-------------------------------------------------------------------------------");
				try {
					response = scanner.nextInt();
					if(response<1 || response>customers.size()) {
						System.out.println("Not a valid option");
						response = -1;
						break;
					} else {
						return customers.get(response-1);
					}
				} catch(Exception e) {
					System.out.println("Not a valid option");
					response = -1;
					scanner.nextLine(); //eat a line to avoid a loop
					break;
				}
			}	
		}
		return customer;
	}
	
	public static Order chooseCustomerOrders(Scanner scanner, Employee employee, Customer customer) {

		Order returnOrder = null;
		boolean isRunning = true;
		ArrayList<Order> orders = customer.getOrderList();
		
		while(isRunning) {
			System.out.println("here");
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
								System.out.println("Not a valid option");
								response = -1;
								scanner.nextLine();
							} else {
								returnOrder = customer.getOrderList().get(response-1);
								scanner.nextLine();//this
								return returnOrder;
							}
						} catch (Exception e) {
							System.out.println("Not a valid option");
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
					System.out.println("Not a valid option");
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
					System.out.println("Not a valid option");
					scanner.nextInt();
					response = -1;
				} else {
					lineItem = order.getLineItemArray().get(response-1);
				}
				
			} catch (Exception e) {
				System.out.println("Not a valid option");
				scanner.nextLine();
				response = -1;
			}
		}		
	}


	public static void removeFromOrder(Scanner scanner, Order order, Employee employee) {
	}
}
	

