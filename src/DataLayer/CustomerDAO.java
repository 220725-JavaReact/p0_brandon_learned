package DataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.Customer;
import com.revature.models.Order;
import com.revature.tempdatastorage.TemporaryStorage;
import com.revature.util.ConnectionFactory;

public class CustomerDAO implements DAO<Customer>{

	@Override
	public void addInstance(Customer newInstance) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into customers (first_name, last_name, username, pw, email) values (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setString(1, newInstance.getfName());
			pstmt.setString(2, newInstance.getlName());
			pstmt.setString(3, newInstance.getUsername());
			pstmt.setString(4, newInstance.getPassword());
			pstmt.setString(5, newInstance.getEmail());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong");
			//e.printStackTrace();
		}
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
		
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from customers where username = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("pw"), rs.getString("email"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}

	@Override
	public void deleteInstance(Customer newInstance) {
		// TODO Auto-generated method stub
		
	}

	public void deleteOrder(Customer newInstance, Order order) {
		newInstance.getOrderList().remove(order);
	}
	
	public ArrayList<Order> getPreviousOrders(Customer customer) {
		ArrayList<Order> orders = new ArrayList<>();
		
		
		
		
		return null;		
	}
	
}
