package com.revature.client;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateStoreFrontLogic {

	//StoreName
	public static String verifyStoreFrontName(String attemptName) { 
		if(attemptName.length()<1) {
			System.out.println("You must input a StoreFront Name");
			return "";
		}
		
		if(attemptName.length() > 25) {
			System.out.println("Max Characters: 25");
			return "";
		}
		
		return attemptName;
	}
	
	//Street Address
	public static String verifyStreetAddress (String attemptAddress) {
		if(attemptAddress.length()<1) {
			System.out.println("You must input a Street Address");
			return "";
		}
		
		if(attemptAddress.length() > 25) {
			System.out.println("Max Characters: 25");
			return "";
		}
		
		return attemptAddress;
	}
	
	//City
	public static String verifyCity (String attemptCity) { //make sure everything is letters
		Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(attemptCity);
		boolean b = m.find();
		if(b) {
			System.out.println("City Names Cannot Contain Numbers or Special Characters");
			return "";
		} else if(attemptCity.length() < 1) {
			System.out.println("You Must Input a City Name");
			return "";
		} else if(attemptCity.length() > 25) {
			System.out.println("Max Characters: 25");
			return "";
		} else {
			String formattedName = attemptCity.substring(0, 1).toUpperCase() + attemptCity.substring(1).toLowerCase();
			return formattedName;
		}
	}
	
	//State
	public static String verifyState (String attemptState) {  //Make sure everything is letters
		Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(attemptState);
		boolean b = m.find();
		if(attemptState.length() < 1) {
			System.out.println("You must input a State");
			return "";
		} else if(attemptState.length() != 2) {
			System.out.println("Input Must be in the Format of 'AZ'");
			return "";
		} else if(b) {
			System.out.println("State Cannot Contain Numbers or Special Characters");
			return "";
		} else {
			return attemptState.toUpperCase();
		}
	}
	
	//Zip Code
	public static int verifyZip (String attemptZip) { //make sure everything is numeric
		
		if(attemptZip.length() < 1) {
			System.out.println("You must input a Zip Code");
			return 0;
		}
		if(attemptZip.length() != 5) {
            System.out.println("Input Must be a Number in the Format of #####");
			return 0;
		}		
		
		try{
	        double isNum = Double.parseDouble(attemptZip);
	        if(isNum == Math.floor(isNum)) {
		       return (int) isNum;
	        }else {
	           System.out.println("Input Must be a Number in the Format of #####");
		       return 0;
	        }
	    } catch(Exception e) {
	        if(attemptZip.toCharArray().length == 1) {
	            System.out.println("Input Must be a Number in the Format of #####");
	             //enter a double again
	        }else {
	            System.out.println("Input Must be a Number in the Format of #####");
	            //enter a double again
	        }
	    }
		return 0;
	}
	
}
