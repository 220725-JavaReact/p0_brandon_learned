package com.revature.menus;

import java.util.ArrayList;

import com.revature.models.Employee;
import com.revature.tempdatastorage.TemporaryStorage;

public class EmployeeMenu {

	public static void employeeMenu() {
		
		ArrayList<Employee> employees = new ArrayList<>();
		TemporaryStorage.employees.add(new Employee("user", "password"));
		TemporaryStorage.employees.add(new Employee("user2", "password"));
		TemporaryStorage.printAllEmployees();
		
		
		//update stock for different stores
		//view a list of all customers
			//view their orders
				//change their orders
		
		
		
	}
}
