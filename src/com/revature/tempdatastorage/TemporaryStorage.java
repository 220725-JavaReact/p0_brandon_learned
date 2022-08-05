package com.revature.tempdatastorage;

import java.util.ArrayList;

import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.Employee;
import com.revature.models.StoreFront;

import DataLayer.StoreFrontsDAO;

public class TemporaryStorage {

	public static ArrayList<Customer> customers = new ArrayList<>();
	public static ArrayList<Employee> employees = new ArrayList<>();
	public static ArrayList<StoreFront> storeFronts = new ArrayList<>();
	
	public static void initializeData() {
		storeFronts.add(new StoreFront("Good Duckin' Duckies", "123 Lake Avenue, Duck Town, Duck Nation, 11111"));
		storeFronts.add(new StoreFront("Discount Duckies", "5732 Pond Road, Duck Town, Duck Nation, 11111"));
		storeFronts.add(new StoreFront("Duckie Dynasty", "63111 Ducktail Way, Duck Town, Duck Nation, 11111"));
		//This can be deleted once the Database is implemented
		storeFronts.get(0).getDuckieList().add(new Duckie(1, "Rubber Duckie", 2.99, "A bisic Rubber Duckie", "Mildly Duckie"));
		storeFronts.get(0).getDuckieList().add(new Duckie(2, "Darkwing Duckie", 13.99, "Drake Mallard Duckie dons a mask to fight crime. Let's get dangerous!", "Very Stealthy and Very Duckie"));
		storeFronts.get(0).getDuckieList().add(new Duckie(3, "Donald Duckie", 9.95, "A classic duckie that is very difficult to understand at times", "Quite Duckie"));
		storeFronts.get(0).getDuckieList().add(new Duckie(4, "Daffy Duckie", 6.99, "It's duck season, and I say 'fire'!", "Very Duckie"));
		storeFronts.get(0).getDuckieList().add(new Duckie(5, "Jon Duckett Duckie", 5, 15.79, "Comes with a free copy of HTML and CSS: Design and Build Websites (1st Edition) by: Jon Duckett", "Super Duper Duckie"));
		storeFronts.get(0).getDuckieList().add(new Duckie(6, "[LIMITED EDITION] Black Mage Donald Duckie", 2, 79.99, "ZETAFLAAAAARE!!!!!!", "The Duckiest"));
		//-------------------------------------------------
		//This can be deleted once the Database is implemented
		storeFronts.get(1).getDuckieList().add(new Duckie(1, "Rubber Duckie", 1.99, "A bisic Rubber Duckie", "Mildly Duckie"));
		storeFronts.get(1).getDuckieList().add(new Duckie(2, "Red Duckie", 2.99, "A basic Rubber Duckie, but red!", "Kinda Duckie?"));
		storeFronts.get(1).getDuckieList().add(new Duckie(3, "Alien Duckie", 9.95, "Keep your cows under a tight watch!", "Quite Duckie"));
		storeFronts.get(1).getDuckieList().add(new Duckie(4, "Duckie Lasek", 6.99, "Skateboarding pro Duckie", "The Steeziest Duckie"));
		storeFronts.get(1).getDuckieList().add(new Duckie(5, "Mighty Duckie", 9.99, "Ever seen a duck fight? No. Why? Because other animals are afraid", "The Mightiest and Duckiest"));
		//-------------------------------------------------
		//This can be deleted once the Database is implemented
		storeFronts.get(2).getDuckieList().add(new Duckie(1, "Rubber Duckie", 4.99, "A bisic Rubber Duckie of the Highest Quality", "Mildly Duckie"));
		storeFronts.get(2).getDuckieList().add(new Duckie(2, "Howard the Duckie", 10.59, "No one laughs are the master of Quack-Fu!", "Marvelously Duckie"));
		storeFronts.get(2).getDuckieList().add(new Duckie(3, "Scrooge McDuckie", 20.95, "A very greedy duckie", "Very Greedy and Very Duckie"));
		storeFronts.get(2).getDuckieList().add(new Duckie(4, "Psyduckie", 6.99, "This duckie is very prone to massive headaches", "Very Duckie"));
		storeFronts.get(2).getDuckieList().add(new Duckie(5, "Golduckie", 2, 7.99, "The evolved Psyduckie!", "The Duckiest"));
		//-------------------------------------------------
//		customers.add(new Customer("Brandon", "Learned", "blearned92", "password", "email@email.com"));
//		customers.add(new Customer("Jon", "Duckett", "duckieman", "password", "jon@email.com"));
		//-------------------------------------------------
//		employees.add(new Employee("user", "password"));
//		employees.add(new Employee("user2", "password"));
		
	}
	
//	public static void printAllCustomers() {
//		for(Customer cust : customers) {
//			System.out.println(cust.toString());
//		}
//	}
//	
//	public static void printAllEmployees() {
//		for(Employee emp : employees) {
//			System.out.println(emp.toString());
//		}
//	}
//	
//	public static void printAllDuckiesFromStore1() {
//		for(Duckie duckie : store1Duckies) {
//			System.out.println(duckie);
//		}
//	}
}
