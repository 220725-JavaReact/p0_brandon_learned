package com.revature.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.tempdatastorage.TemporaryStorage;

public class EmployeeLogin {

	public static void employeeLogin(Scanner scanner) {
		

		ArrayList<Employee> employeesForLogin = TemporaryStorage.employees;
		boolean isRunning = true;
		
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("EMPLOYEE LOGIN:");
		System.out.println("-------------------------------------------------------------------------------");

		while(isRunning) {
			
			System.out.println("Input Username: ");
			String attemptUsername = scanner.nextLine();
			String targetPassword = "";
			
			for(Employee employee : employeesForLogin) {
				if(employee.getUsername().equals(attemptUsername)) {
					targetPassword = employee.getPassword();
				}
				
				if(!targetPassword.equals("")) {
					System.out.println("Input Password");
					if(scanner.nextLine().equals(targetPassword)) {
						EmployeeMenu.employeeMenu(scanner, employee);
						isRunning = false;
						break;
					} else {
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Incorrect Username or Password");
						System.out.println("-------------------------------------------------------------------------------");
						isRunning = false;
						break;
					}
				}
			}
			
			if(isRunning) {
				System.out.println("Input Password");
				scanner.nextLine();
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Incorrect Username or Password");
				System.out.println("-------------------------------------------------------------------------------");

			}			
			isRunning = false;
		}
		
	}
}
