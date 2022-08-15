package com.revature.client;

import java.text.DecimalFormat;

public class CreateProductLogic {

	public static String verifyProductName(String attemptName) {
		if(attemptName.length()<1) {
			System.out.println("You must input a Product Name");
			return "";
		}
		
		if(attemptName.length() > 50) {
			System.out.println("Max Characters: 50");
			return "";
		}
		
		return attemptName;
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
