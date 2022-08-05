package com.revature.menus;

import java.util.Scanner;

import com.revature.models.Employee;

import DataLayer.DAO;
import DataLayer.EmployeeDAO;

public class EmployeeLogin {

	public static void employeeLogin(Scanner scanner) {
		

		DAO<Employee> employeeDao = new EmployeeDAO();
		boolean isRunning = true;
		
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("EMPLOYEE LOGIN:");
		System.out.println("-------------------------------------------------------------------------------");

		while(isRunning) {
			
			System.out.println("Input Username: ");
			String attemptUsername = scanner.nextLine();
			Employee employee = employeeDao.getByName(attemptUsername);
			System.out.println("Input Password");
			String attemptPassword = scanner.nextLine();
			
			
			if(employee == null) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Incorrect Username or Password");
				System.out.println("-------------------------------------------------------------------------------");
				isRunning = false;
				break;
			}

			String targetPassword = employee.getPassword();
			if(!targetPassword.equals(attemptPassword)) {
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Incorrect Username or Password");
				System.out.println("-------------------------------------------------------------------------------");
				isRunning = false;
				break;
			}
			
			EmployeeMenu.employeeMenu(scanner, employee);
					
			isRunning = false;
		}
		
	}
}
