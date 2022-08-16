package com.revature.client;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.revature.models.Duckie;
import com.revature.models.LineItem;
import com.revature.models.Order;

public class UIUXBusinessLogic {

	public static String createBanner(String string) {
		int targetLen = ((82 - string.length())/2)-2;
		StringBuilder sb = new StringBuilder();
		while(sb.toString().length() < targetLen) {
			sb.append("-");
		}
		sb.append(" ");
		sb.append(string);
		sb.append(" ");
		while(sb.toString().length()<82) {
			sb.append("-");
		}
		return dashes() + "\n" + sb.toString() + "\n" + dashes();
		
	}
	
	public static String createSpaceBanner(String string) {
		int targetLen = ((82 - string.length())/2)-1;
		StringBuilder sb = new StringBuilder();
		while(sb.toString().length() < targetLen) {
			sb.append(" ");
		}
		sb.append(string);
		while(sb.toString().length()<82) {
			sb.append(" ");
		}
		return dashes() + "\n" + sb.toString() + "\n" + dashes();
	}
	
	public static String replaceWithDashes(String string) {
		StringBuilder sb = new StringBuilder();
		while(sb.toString().length() < string.length()) {
			sb.append("-");
		}

		return sb.toString();
	}
	
	public static void leftAlignPrintTwoString(String string1, String string2) {
		StringBuilder sb = new StringBuilder();
		int lineLength = 82;
		sb.append(string1);
		while (sb.toString().length()<lineLength/2) {
			sb.append(" ");
		}
		sb.append(string2);
		System.out.println(sb.toString());
	}
	
	
	public static String centerText(String string) {
		int targetLen = ((82 - string.length())/2)-1;
		StringBuilder sb = new StringBuilder();
		while(sb.toString().length() < targetLen) {
			sb.append(" ");
		}
		sb.append(string);
		while(sb.toString().length()<82) {
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public static void centerTwoString(String string1, String string2) {
		int targetLen = (82 - (string1.length() + string2.length()))/2;
		StringBuilder sb = new StringBuilder();
		while(sb.toString().length()<targetLen) {
			sb.append(" ");
		}
				
		System.out.println(centerText(string1 + sb.toString() + string2));
	}
	
	public static void centerThreeString(String string1, String string2, String string3) {
		int targetLen = (82 - (string1.length() + string2.length()+string3.length()))/3;
		StringBuilder sb = new StringBuilder();
		while(sb.toString().length()<targetLen) {
			sb.append(" ");
		}
				
		System.out.println(centerText(string1 + sb.toString() + string2 + sb.toString() + string3));
	}
	
	
	public static String formatProducts(Duckie duckie, int quantity) {
		StringBuilder sb = new StringBuilder();
		if(quantity == 0) {
			sb.append("Product: " + duckie.getName());
			while(sb.toString().length() < 82 - "OUT OF STOCK".length()) {
				sb.append(" ");
			}	
			sb.append("OUT OF STOCK");
		} else {
			sb.append("Product: " + duckie.getName());
			while(sb.toString().length() < 82 - ("STOCK: " + quantity).length()) {
				sb.append(" ");
			}
			sb.append("Stock: " + quantity);
		}
		return sb.toString();
	}
	
	public static String formatProductsWithIndex(int i, Duckie duckie, int quantity) {
		StringBuilder sb = new StringBuilder();

		if(quantity == 0) {
			if(i+1 < 10) {
				sb.append("[" + (i+1) + "]  " + duckie.getName());
				while(sb.toString().length() < 82 - "OUT OF STOCK".length()) {
					sb.append(".");
				}	
				sb.append("OUT OF STOCK");
			} else {
				sb.append("[" + (i+1) + "] " + duckie.getName());
				while(sb.toString().length() < 82 - "OUT OF STOCK".length()) {
					sb.append(".");
				}	
				sb.append("OUT OF STOCK");
			}
		} else {
			if(i+1 < 10) {
				sb.append("[" + (i+1) + "]  " + duckie.getName());
				while(sb.toString().length() < 82 - ("STOCK: " + quantity).length()) {
					sb.append(".");
				}
				sb.append("Stock: " + quantity);
			} else {
				sb.append("[" + (i+1) + "] " + duckie.getName());
				while(sb.toString().length() < 82 - ("STOCK: " + quantity).length()) {
					sb.append(".");
				}	
				sb.append("Stock: " + quantity);
			}
		}
		return sb.toString();
	}
	
	public static String formatProductsWithIndexAndCost(int i, Duckie duckie) {
		StringBuilder sb = new StringBuilder();
		
		if(i+1 < 10) {
			sb.append("[" + (i+1) + "]  " + duckie.getName());
			while(sb.toString().length() < 82 - ("Price: $" + duckie.getPrice()).length()) {
				sb.append(".");
			}	
			sb.append(("Price: $" + duckie.getPrice()));
		} else {
			sb.append("[" + (i+1) + "] " + duckie.getName());
			while(sb.toString().length() < 82 - ("Price: $" + duckie.getPrice()).length()) {
				sb.append(".");
			}	
			sb.append(("Price: $" + duckie.getPrice()));
		}
		
		
		return sb.toString();
	}
	
	public static String formatCartProductsWithIndex(int i, Duckie duckie, int quantity) {
		StringBuilder sb = new StringBuilder();

		if(i+1 < 10) {
			sb.append("[" + (i+1) + "]  " + duckie.getName());
			while(sb.toString().length() < 82 - ("In Cart: " + quantity).length()) {
				sb.append(".");
			}
			sb.append("In Cart: " + quantity);
		} else {
			sb.append("[" + (i+1) + "] " + duckie.getName());
			while(sb.toString().length() < 82 - ("In Cart: " + quantity).length()) {
				sb.append(".");
			}	
			sb.append("In Cart: " + quantity);

		}
		return sb.toString();
	}
	
	public static String formatProfits(Duckie duckie, int quantity) {
		StringBuilder sb = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#.00");

		sb.append(duckie.getName() + "s Sold: " + quantity);
		while(sb.toString().length() < 82 - ("Product Profits: $" + df.format(duckie.getPrice() * quantity)).length()) {
			sb.append(" ");
		}
		sb.append("Product Profits: $" + df.format(duckie.getPrice() * quantity));
		return sb.toString();
	}	
	
	public static void formatOrder(Order order) {
		DecimalFormat df = new DecimalFormat("#.00");
		ArrayList<LineItem> lineItems = order.getLineItemArray();
		int lineLength = 0; //our of 82

		System.out.println("Order Id: " + order.getId());
		for(LineItem lineItem : lineItems) {
			System.out.println(dottedFormatDouble(lineItem.getQuantity() + " x " + lineItem.getDuckie().getName(), "$" + df.format(lineItem.getDuckie().getPrice() * lineItem.getQuantity())));
		}
		
		System.out.println(dottedFormatDouble("Sales Tax:", "$" + df.format(order.getTotalPriceOfItems() * 0.07)));
		System.out.println(dottedFormatDouble("Items Price:", "$" + df.format(order.getTotalPriceOfItems())));
		System.out.println(dottedFormatDouble("Total:", "$" + df.format(order.getTotalPriceOfItems() + (order.getTotalPriceOfItems() * 0.07))));
	}	
	
	public static String dottedFormatSingle(String string) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(string);
		while(sb.toString().length() < 82) {
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public static String dottedFormatDouble(String string, String string2) {
		StringBuilder sb = new StringBuilder();
		int lineLength = 0;
		
		sb.append(string);
		lineLength = string.length();
		while(lineLength < 82-string2.length()) {
			sb.append(".");
			lineLength++;
		}
		sb.append(string2);
		return sb.toString();
	}
	
	
	public static String formatProductsIfIndex(Duckie duckie, int quantity) {
		StringBuilder sb = new StringBuilder();
		sb.append("Product: " + duckie.getName());
		while(sb.toString().length() < 65) {
			sb.append(".");
		}
		if(quantity == 0) {
			sb.append("OUT OF STOCK");
		} else {
			sb.append("Stock: " + quantity);
		}
		return sb.toString();
	}
	
	public static String dashes() {
		return "----------------------------------------------------------------------------------";

	}
}
