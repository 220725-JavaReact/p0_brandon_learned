package com.revature.models;

public class LineItem {

	int id;
	Duckie duckie;
	int quantity;
	
	public LineItem(Duckie duckie, int quantity) {
		this.duckie = duckie;
		this.quantity = quantity;
	}
	
	public LineItem(int id, Duckie duckie, int quantity) {
		this.id = id;
		this.duckie = duckie;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Duckie getDuckie() {
		return duckie;
	}

	public void setDuckie(Duckie duckie) {
		this.duckie = duckie;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void increaseQuanity(int amount) {
		this.quantity += amount;
	}
	
	public void decreaseQuanity(int amount) {
		this.quantity -= amount;
	}

	@Override
	public String toString() {
		return duckie + "\nIn Stock: " + quantity;
	}
	
	
}
