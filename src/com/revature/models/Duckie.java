package com.revature.models;

import java.util.Objects;

public class Duckie {
	
	int duckNumber;
	String name;
	int quantity;
	double price;
	String description;
	String duckiness;
	
	public Duckie(int duckNumber, String name, double price, String description, String duckiness) {
		this.name = name;
		this.quantity = 10;
		this.price = price;
		this.description = description;
		this.duckiness = duckiness;
		this.duckNumber = duckNumber;
	}
	
	public Duckie(int duckNumber, String name, int quantity, double price, String description, String duckiness) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.duckiness = duckiness;
		this.duckNumber = duckNumber;
	}

	
	public int getDuckNumber() {
		return duckNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	public void removeQuantity(int quantity) {
		if(this.quantity < quantity) {
			System.out.println("Do not have that much stock to remove");
		} else {
			this.quantity -= quantity;
		}
		
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuckiness() {
		return duckiness;
	}

	public void setDuckiness(String duckiness) {
		this.duckiness = duckiness;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, duckiness, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Duckie other = (Duckie) obj;
		return Objects.equals(description, other.description) && Objects.equals(duckiness, other.duckiness)
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}

	@Override
	public String toString() {
		return "Duck#: " + duckNumber + "\nDuckie Name: " + name + "\nQuantity: " + quantity + "\nDuckie Price: $" + price + 
				"\nDescription: " + description + "\nDuckiness: " + duckiness;
	}
	
	
}
