package DataLayer;

import java.util.ArrayList;

import com.revature.models.Customer;
import com.revature.models.Order;

public interface DAO<T> {

	//return type, name, expected passed variables
	
	ArrayList<T> getAll();
	void addInstance(T newInstance);
	void updateInstance(T newInstance);
	T getByName(String name);
	void deleteInstance(T newInstance);
	T getById(int id);
	//get all customers
	//get customer by username
	//update customer
	
	
}
