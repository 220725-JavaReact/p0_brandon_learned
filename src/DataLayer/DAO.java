package DataLayer;

import java.util.ArrayList;

public interface DAO<T> {

	//return type, name, expected passed variables
	
	ArrayList<T> getAll();
	void addInstance(T newInstance);
	void updateInstance(T newInstance);
	T getByName(String name);
	
	
	//get all customers
	//get customer by username
	//update customer
	
	
}
