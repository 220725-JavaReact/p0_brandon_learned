package com.revature.tempdatastorage;

import java.util.ArrayList;

import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.Employee;
import com.revature.models.StoreFront;

public class TemporaryStorage {

	public static ArrayList<Customer> customers = new ArrayList<>();
	public static ArrayList<Employee> employees = new ArrayList<>();

	public static StoreFront goodDuckinDuckies = new StoreFront("Good Duckin' Duckies", "123 Lake Avenue, Duck Town, Duck Nation, 11111");
	public static StoreFront discountDuckies = new StoreFront("Discount Duckies", "5732 Pond Road, Duck Town, Duck Nation, 11111");
	public static StoreFront duckieDynasty = new StoreFront("Duckie Dynasty", "63111 Ducktail Way, Duck Town, Duck Nation, 11111");

	

	public static void initializeData() {
		//This can be deleted once the Database is implemented
		goodDuckinDuckies.getDuckieList().add(new Duckie(1, "Rubber Duckie", 2.99, "A bisic Rubber Duckie", "Mildly Duckie"));
		goodDuckinDuckies.getDuckieList().add(new Duckie(2, "Darkwing Duckie", 13.99, "Drake Mallard Duckie dons a mask to fight crime. Let's get dangerous!", "Very Stealthy and Very Duckie"));
		goodDuckinDuckies.getDuckieList().add(new Duckie(3, "Donald Duckie", 9.95, "A classic duckie that is very difficult to understand at times", "Quite Duckie"));
		goodDuckinDuckies.getDuckieList().add(new Duckie(4, "Daffy Duckie", 6.99, "It's duck season, and I say 'fire'!", "Very Duckie"));
		goodDuckinDuckies.getDuckieList().add(new Duckie(5, "Jon Duckett Duckie", 5, 15.79, "Comes with a free copy of HTML and CSS: Design and Build Websites (1st Edition) by: Jon Duckett", "Super Duper Duckie"));
		goodDuckinDuckies.getDuckieList().add(new Duckie(6, "[LIMITED EDITION] Black Mage Donald Duckie", 2, 79.99, "ZETAFLAAAAARE!!!!!!", "The Duckiest"));
		//-------------------------------------------------
		//This can be deleted once the Database is implemented
		discountDuckies.getDuckieList().add(new Duckie(1, "Rubber Duckie", 1.99, "A bisic Rubber Duckie", "Mildly Duckie"));
		discountDuckies.getDuckieList().add(new Duckie(2, "Red Duckie", 2.99, "A basic Rubber Duckie, but red!", "Kinda Duckie?"));
		discountDuckies.getDuckieList().add(new Duckie(3, "Alien Duckie", 9.95, "Keep your cows under a tight watch!", "Quite Duckie"));
		discountDuckies.getDuckieList().add(new Duckie(4, "Duckie Lasek", 6.99, "Skateboarding pro Duckie", "The Steeziest Duckie"));
		discountDuckies.getDuckieList().add(new Duckie(5, "Mighty Duckie", 9.99, "Ever seen a duck fight? No. Why? Because other animals are afraid", "The Mightiest and Duckiest"));
		//-------------------------------------------------
		//This can be deleted once the Database is implemented
		duckieDynasty.getDuckieList().add(new Duckie(1, "Rubber Duckie", 4.99, "A bisic Rubber Duckie of the Highest Quality", "Mildly Duckie"));
		duckieDynasty.getDuckieList().add(new Duckie(2, "Howard the Duckie", 17.99, "Drake Mallard Duckie dons a mask to fight crime. Let's get dangerous!", "Very Stealthy and Very Duckie"));
		duckieDynasty.getDuckieList().add(new Duckie(3, "Scrooge McDuckie", 9.95, "A classic duckie that is very difficult to understand at times", "Quite Duckie"));
		duckieDynasty.getDuckieList().add(new Duckie(4, "Psyduckie", 6.99, "It's duck season, and I say 'fire'!", "Very Duckie"));
		duckieDynasty.getDuckieList().add(new Duckie(6, "[LIMITED EDITION] Black Mage Donald Duckie", 2, 79.99, "ZETAFLAAAAARE!!!!!!", "The Duckiest"));
		//-------------------------------------------------
		customers.add(new Customer("Brandon", "Learned", "blearned92", "password", "email@email.com"));
		customers.add(new Customer("Jon", "Duckett", "duckieman", "password", "jon@email.com"));
		//-------------------------------------------------
		employees.add(new Employee("user", "password"));
		employees.add(new Employee("user2", "password"));
		
	}
	
	public static void printAllCustomers() {
		for(Customer cust : customers) {
			System.out.println(cust.toString());
		}
	}
	
	public static void printAllEmployees() {
		for(Employee emp : employees) {
			System.out.println(emp.toString());
		}
	}
//	
//	public static void printAllDuckiesFromStore1() {
//		for(Duckie duckie : store1Duckies) {
//			System.out.println(duckie);
//		}
//	}
}
