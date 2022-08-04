package DataLayer;

import java.util.ArrayList;

import com.revature.models.Customer;
import com.revature.tempdatastorage.TemporaryStorage;

public class CustomerDAO implements DAO<Customer>{

	@Override
	public void addInstance(Customer newInstance) {
		TemporaryStorage.customers.add(newInstance);
	}

	@Override
	public ArrayList<Customer> getAll() {
		ArrayList<Customer> customerArray = new ArrayList<>();
		for(Customer customer : TemporaryStorage.customers) {
			customerArray.add(customer);
		}
		return customerArray;
	}

	@Override
	public void updateInstance(Customer newInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer getByName(String name) {
		for(Customer customer : TemporaryStorage.customers) {
			if(customer.getUsername().equals(name)) {
				return customer;
			}
		}
		return null;
	}

	
	
	
}
