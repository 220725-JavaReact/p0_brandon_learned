package DataLayer;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.Order;
import com.revature.util.ConnectionFactory;

public class CustomerDAO implements DAO<Customer>{

	@Override
	public ArrayList<Customer> getAll() {
		ArrayList<Customer> customers = new ArrayList<>();
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from customers";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				customers.add(new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("pw"), rs.getString("email")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return customers;
	}
	
	public LinkedList<ArrayList<Customer>> getAllIntoLinkedList() {
		LinkedList<ArrayList<Customer>> customers = new LinkedList<>();
		ArrayList<Customer> tempList = new ArrayList<>();
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from customers";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				if(tempList.size() < 10) {
					tempList.add(new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("pw"), rs.getString("email")));
				} else {
					customers.add(tempList);
					tempList = new ArrayList<>();
					tempList.add(new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("pw"), rs.getString("email")));
				}
				
			}
			if(tempList.size() != 0) {
				customers.add(tempList);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return customers;
	}
	
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
	public void updateInstance(Customer newInstance) {
		//this will be used to update their orders/stuff like that
		//because the customer itself will not be alters in this instance
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
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "delete from customers where customer_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, newInstance.getId());
			ResultSet rs = pstmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}		
	}
	
	public void deleteInstanceWithOrders(Customer newInstance) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "begin;\r\n"
					+ "		delete from order_items using orders where order_items.order_id = orders.order_id and orders.customer_id = ?;\r\n"
					+ "		delete from orders where customer_id = ?;\r\n"
					+ "		delete from customers where customer_id =?;\r\n"
					+ "		commit;";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, newInstance.getId());
			pstmt.setInt(2, newInstance.getId());
			pstmt.setInt(3, newInstance.getId());
			ResultSet rs = pstmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}	
	}

	public void deleteOrder(Customer newInstance, Order order) {
		newInstance.getOrderList().remove(order);
	}
	
//	public ArrayList<Order> getPreviousOrders(Customer customer) {
//		ArrayList<Order> orders = new ArrayList<>();
//		return null;		
//	}

	@Override
	public Customer getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
