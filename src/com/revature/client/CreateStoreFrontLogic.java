package com.revature.client;

import java.text.DecimalFormat;

public class CreateStoreFrontLogic {

	//StoreName
	public static String verifyStoreFrontName(String attemptName) { //Make sure everything is letters
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
		if(attemptCity.length()<1) {
			System.out.println("You must input a City Name");
			return "";
		}
		
		if(attemptCity.length() > 25) {
			System.out.println("Max Characters: 25");
			return "";
		}
		
		return attemptCity;
	}
	
	//State
	public static String verifyState (String attemptState) {  //Make sure everything is letters
		if(attemptState.length()<1) {
			System.out.println("You must input a State");
			return "";
		}
		
		if(attemptState.length() != 2) {
			System.out.println("Input Must be in the Format of 'AZ'");
			return "";
		}
		
		return attemptState.toUpperCase();
	}
	
	//Zip Code
	public static String verifyZip (String attemptZip) { //make sure everything is numeric
		if(attemptZip.length()<1) {
			System.out.println("You must input a State");
			return "";
		}
		
		if(attemptZip.length() != 2) {
			System.out.println("Input Must be in the Format of 'AZ'");
			return "";
		}
		
		return attemptZip;
	}
	
	
	
	public static String verifyQuality(String attemptName) {
		if(attemptName.length()<1) {
			System.out.println("You must input a Quality");
			return "";
		}
		
		if(attemptName.length() > 50) {
			System.out.println("Max Characters: 50");
			return "";
		}
		
		return attemptName;
	}
	
	public static String verifyDescription(String attemptName) {
		if(attemptName.length()<1) {
			System.out.println("You must input a Description");
			return "";
		}
		
		if(attemptName.length() > 100) {
			System.out.println("Max Characters: 100");
			return "";
		}
		
		return attemptName;
	}


	public static double verfiyPrice(String attamptPrice) {
		DecimalFormat df = new DecimalFormat("0.00");
		
		if(attamptPrice.length() < 1) {
			System.out.println("You must input a price");
			return 0;
		}
		if(attamptPrice.length() < 4) {
            System.out.println("Input Must be a Number in the Format of #.##");
			return 0;
		}
		if(!attamptPrice.contains(".")) {
            System.out.println("Input Must be a Number in the Format of #.##");
			return 0;
		}
		if(attamptPrice.contains(".")) {
			String[] arr = attamptPrice.split("\\.");
			if(arr.length != 2) {
	            System.out.println("Input Must be a Number in the Format of #.##");
				return 0;
			}
			if(arr[1].length() != 2) {
	            System.out.println("Input Must be a Number in the Format of #.##");
				return 0;
			}
		}
		
		
		try{
	        double isNum = Double.parseDouble(attamptPrice);
	        if(isNum == Math.floor(isNum)) {
	            String returnString = df.format(isNum);
	            if(Double.parseDouble(returnString) == 0){
	            	System.out.println("You must input a price");
	            }
		       return Double.parseDouble(returnString);
	        }else {
	            String returnString = df.format(isNum);
	            if(Double.parseDouble(returnString) == 0){
	            	System.out.println("You must input a price");
	            }
		       return Double.parseDouble(returnString);
	        }
	    } catch(Exception e) {
	        if(attamptPrice.toCharArray().length == 1) {
	            System.out.println("Input Must be a Number in the Format of #.##");
	             //enter a double again
	        }else {
	            System.out.println("Input Must be a Number in the Format of #.##");
	            //enter a double again
	        }
	    }
		return 0;
	}
	
}
