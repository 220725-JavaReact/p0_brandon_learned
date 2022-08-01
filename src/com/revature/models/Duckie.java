package com.revature.models;

import java.util.Objects;

public class Duckie {
	
	int duckNumber;
	String name;
	int stock;
	double price;
	String description;
	String duckiness;
	
	public Duckie(int duckNumber, String name, double price, String description, String duckiness) {
		this.name = name;
		this.stock = 10;
		this.price = price;
		this.description = description;
		this.duckiness = duckiness;
		this.duckNumber = duckNumber;
	}
	
	public Duckie(int duckNumber, String name, int stock, double price, String description, String duckiness) {
		this.name = name;
		this.stock = stock;
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
	
	public int getStock() {
		return stock;
	}

	public void addStock(int stock) {
		this.stock += stock;
	}
	
	public void removeStock(int stock) {
		if(this.stock < stock) {
			System.out.println("Do not have that much stock to remove");
		} else {
			this.stock -= stock;
		}
		
	}
	
	public void setStock(int stock) {
		this.stock = stock;
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
		return "Duck#: " + duckNumber + "\nDuckie Name: " + name + "\nStock: " + stock + "\nDuckie Price: $" + price + 
				"\nDescription: " + description + "\nDuckiness: " + duckiness + "\n--------------------";
	}
	
	
}
