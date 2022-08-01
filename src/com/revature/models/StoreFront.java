package com.revature.models;

import java.util.ArrayList;

public class StoreFront {

	String name;
	String address;
	ArrayList<Duckie> duckieList = new ArrayList<>();
	ArrayList<Order> orderList = new ArrayList<>();
	
	public StoreFront(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public void addDuckie(Duckie duckie) {
		if(!duckieList.contains(duckie)) {
			duckieList.add(duckie);
		} else {
			System.out.println("Duckie already exists in inventory.");
		}
	}
	
	public void removeDuckie(Duckie duckie) {
		if(duckieList.contains(duckie)) {
			duckieList.remove(duckie);
		} else {
			System.out.println("That duckie does not exist in inventory.");
		}
	}
	
	public void addOrder(Order order) {
		orderList.add(order);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Duckie> getDuckieList() {
		return duckieList;
	}

	public void setDuckieList(ArrayList<Duckie> duckieList) {
		this.duckieList = duckieList;
	}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "StoreFront [name=" + name + ", address=" + address + ", duckieList=" + duckieList + ", orderList="
				+ orderList + "]";
	}
	
	
	
	
}
