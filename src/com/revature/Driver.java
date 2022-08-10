package com.revature;

import java.util.Scanner;

import com.revature.client.BusinessLogic;
import com.revature.client.UIUXBusinessLogic;
import com.revature.menus.CreateAccount;
import com.revature.menus.CustomerLogin;
import com.revature.menus.EmployeeLogin;

public class Driver {

	public static void main(String[] args) {
		
		boolean isRunning = true;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println(UIUXBusinessLogic.createSpaceBanner(" Hello and welcome to Quality-Quackin' Ducks! Where every day is your Duckie Day!"));
		System.out.println(" ");
		while(isRunning) {
			System.out.println(UIUXBusinessLogic.createBanner("MAIN MENU"));
			System.out.println("Please choose from the options below: ");
			System.out.println("[1] Customer Login \n[2] Employee Login \n[3] Create Account \n[4] Exit Application" );
			System.out.println(UIUXBusinessLogic.dashes());

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
				System.out.println(UIUXBusinessLogic.createSpaceBanner("Come 'Quack' Soon! ;)"));
				scanner.close();
				isRunning = false;
			default:
				break;
			}
			
		}
		
		
	}
}
