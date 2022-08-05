package com.revature;

import java.util.Scanner; 

import com.revature.menus.CreateAccount;
import com.revature.menus.CustomerLogin;
import com.revature.menus.EmployeeLogin;
import com.revature.tempdatastorage.TemporaryStorage;

public class Driver {

	public static void main(String[] args) {
		
		TemporaryStorage.initializeData();//This is Temporary and should be deleted after database is implemented
		
		boolean isRunning = true;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Hello and welcome to Quality-Quackin' Ducks! Where everyday is your Duckie Day!");
		
		while(isRunning) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] Customer Login \n[2] Employee Login \n[3] Create Account \n[4] Exit Application" );
			System.out.println("-------------------------------------------------------------------------------");

			switch (scanner.nextLine()) {
			case "1":	
				CustomerLogin.customerLogin(scanner);
				break;
			case "2":
				EmployeeLogin.employeeLogin(scanner);
				break;
			case "3":
				CreateAccount.createAccount(scanner);
				break;
			case "4":
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Come 'Quack' Soon! ;)");
				System.out.println("-------------------------------------------------------------------------------");
				scanner.close();
				isRunning = false;
			default:
				break;
			}
			
		}
		
		
	}
}
