package com.revature.tempdatastorage;

import java.util.ArrayList;

import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.Employee;
import com.revature.models.Order;
import com.revature.models.StoreFront;

public class TemporaryStorage {

	public static ArrayList<Customer> customers = new ArrayList<>();
	public static ArrayList<Employee> employees = new ArrayList<>();
	public static ArrayList<Duckie> store1Duckies = new ArrayList<>();
	public static ArrayList<Order> store1Orders = new ArrayList<>();
	StoreFront discountDuckies = new StoreFront("Discout Duckies", "5732 Pond Road, Duck Town, Duck Nation, 11111");
	StoreFront duckieDynasty = new StoreFront("Duckie Dynasty", "63111 Ducktail Way, Duck Town, Duck Nation, 11111");
	
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
	
	public static void printAllDuckiesFromStore1() {
		for(Duckie duckie : store1Duckies) {
			System.out.println(duckie);
		}
	}
}
