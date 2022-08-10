package com.revature.menus;

import java.util.Scanner;

import com.revature.client.UIUXBusinessLogic;
import com.revature.models.Employee;
import com.revature.util.Logger;
import com.revature.util.Logger.LogLevel;

import DataLayer.DAO;
import DataLayer.EmployeeDAO;

public class EmployeeLogin {

	public static void employeeLogin(Scanner scanner) {
		

		DAO<Employee> employeeDao = new EmployeeDAO();
		boolean isRunning = true;
		
		System.out.println(UIUXBusinessLogic.createBanner("EMPLOYEE LOGIN"));

		while(isRunning) {
			
			System.out.println("Input Username: ");
			String attemptUsername = scanner.nextLine();
			System.out.println("Input Password: ");
			String attemptPassword = scanner.nextLine();
			Employee employee = employeeDao.getByName(attemptUsername);
		
			
			if(employee == null) {
				System.out.println(UIUXBusinessLogic.dashes());
				System.out.println("Incorrect Username or Password...");
				System.out.println(UIUXBusinessLogic.dashes());
				isRunning = false;
				break;
			}

			String targetPassword = employee.getPassword();
			if(!targetPassword.equals(attemptPassword)) {
				System.out.println(UIUXBusinessLogic.dashes());
				System.out.println("Incorrect Username or Password...");
				System.out.println(UIUXBusinessLogic.dashes());
				isRunning = false;
				break;
			}
			
			Logger.getLogger().log(LogLevel.info, "\nAdmin User: " + employee.getUsername() + " logged into account\n");
			EmployeeMenu.employeeMenu(scanner, employee);
					
			isRunning = false;
		}
		
	}
}
