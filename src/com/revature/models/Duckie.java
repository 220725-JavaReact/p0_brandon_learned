package com.revature.models;

import java.text.DecimalFormat;
import java.util.Objects;

public class Duckie {
	
	int id;
	String name;
	double price;
	String description;
	String quality;
	
	public Duckie(String name, double price, String description, String quality) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.quality = quality;
	}
	
	public Duckie(int id, String name, double price, String description, String quality) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.quality = quality;
		this.id = id;
	}
	
	public void setId(int id) {
		this.id = id;
	}



	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getQuality() {
		return quality;
	}

	public void setQuality(String duckiness) {
		this.quality = duckiness;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, quality, name, price);
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
		return Objects.equals(description, other.description) && Objects.equals(quality, other.quality)
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.00");
		return "Duckie Name: " + name + "\nDuckie Price: $" + df.format(price) + 
				"\nDescription: " + description + "\nDuckiness: " + quality;
	}
	
	
}
