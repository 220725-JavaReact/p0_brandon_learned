package com.revature.models;

import java.text.DecimalFormat; 
import java.util.ArrayList;

public class Order {

	ArrayList<LineItem> lineItemArray = new ArrayList<>();
	String storeAddress;
	double totalPriceOfItems;
	
	public Order(String storeAddress) {
		this.storeAddress = storeAddress;
		this.totalPriceOfItems = 0;
	}
	
	public void addLineItem(LineItem lineItem) {
		lineItemArray.add(lineItem);
		this.totalPriceOfItems += lineItem.getQuantity() * lineItem.getDuckie().getPrice();
	}
	
	public void removeLineItem(LineItem lineItem) {
		this.totalPriceOfItems -= lineItem.getQuantity() * lineItem.getDuckie().getPrice();
		lineItemArray.remove(lineItem);
	}
	
	public void resetLineItemArray() {
		this.lineItemArray = new ArrayList<LineItem>();
	}
	
	
	public void increaseLineItemQuantity(Duckie duckie, int amount) {
		for(LineItem item : lineItemArray) {
			if(item.getDuckie() == duckie) {
				item.increaseQuanity(amount);
				this.totalPriceOfItems += amount * item.getDuckie().getPrice();
			}
		}
	}
	
	public void removeFromLineItemQuantity(Duckie duckie, int amount) {
		for(LineItem item : lineItemArray) {
			if(item.getDuckie() == duckie) {
				item.decreaseQuanity(amount);
				this.totalPriceOfItems -= amount * item.getDuckie().getPrice();
			}
			if(item.getQuantity() <= 0) {
				if(lineItemArray.size() == 1) {
					this.lineItemArray = new ArrayList<LineItem>();
				} else {
					removeLineItem(item);
				}
				
			}
			
		}
	}
	
	public boolean containsDuckie(Duckie duckie) {
		for(LineItem item : lineItemArray) {
			if(item.getDuckie() == duckie) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<LineItem> getLineItemArray() {
		return lineItemArray;
	}

	public void setLineItemArray(ArrayList<LineItem> lineItemArray) {
		this.lineItemArray = lineItemArray;
	}

	public double getTotalPriceOfItems() {
		return totalPriceOfItems;
	}

	public void setTotalPriceOfItems(double totalPriceOfItems) {
		this.totalPriceOfItems = totalPriceOfItems;
	}

	public void printOrder() {
		DecimalFormat df = new DecimalFormat("#.00");
		for(LineItem lineItem : lineItemArray) {
			System.out.println(lineItem.getQuantity() + " x " + lineItem.getDuckie().getName() + 
					" - $" + df.format(lineItem.getDuckie().getPrice() * lineItem.getQuantity()));
		}
		System.out.println("Total: $" + df.format(this.totalPriceOfItems));
	}
	
	public void printOrderWithTax() {
		DecimalFormat df = new DecimalFormat("#.00");
		for(LineItem lineItem : lineItemArray) {
			System.out.println(lineItem.getQuantity() + " x " + lineItem.getDuckie().getName() + 
					" - $" + df.format(lineItem.getDuckie().getPrice() * lineItem.getQuantity()));
		}
		System.out.println("Sales tax: " + df.format(this.totalPriceOfItems * 0.07));
		System.out.println("Items Price: " + df.format(this.totalPriceOfItems));
		System.out.println("Total: $" + df.format(this.totalPriceOfItems + (this.totalPriceOfItems * 0.07)));
	}
	
	public String getOrderForToString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<lineItemArray.size();i++) {
			sb.append(lineItemArray.get(i).quantity + " x " + lineItemArray.get(i).duckie.name);
			if(i<lineItemArray.size()-1) {
				sb.append("\n");
			}
			sb.toString();
		}
		return sb.toString();
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	@Override
	public String toString() {
		return "Order: \n" + getOrderForToString() + "\nStoreAddress: " + storeAddress + "\nTotalPriceOfItems: "
				+ totalPriceOfItems;
	}
	
	
	
}
