package DataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Customer;
import com.revature.models.Duckie;
import com.revature.models.LineItem;
import com.revature.models.Order;
import com.revature.models.StoreFront;
import com.revature.util.ConnectionFactory;

public class OrderDAO implements DAO<Order>{

	DuckieDAO duckieDao = new DuckieDAO();
	
	@Override
	public ArrayList<Order> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public ArrayList<Order> getAllByCustomerId(Customer customer) {

//		StoreFrontsDAO storeFrontDao = new StoreFrontsDAO();
//		DuckieDAO duckieDAO = new DuckieDAO();
		ArrayList<Order> orders = new ArrayList<>(); //create line item, add it to order
		
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from orders \r\n"
					+ "natural inner join order_items \r\n"
					+ "natural inner join products \r\n"
					+ "natural inner join storefronts\r\n"
					+ "where customer_id = ?\r\n"
					+ "order by order_id;";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, customer.getId());
			ResultSet rs = pstmt.executeQuery();
			
			boolean doNext = true;

			while(rs.next()) {
				for(Order order : orders) {
					if(order.getId() == rs.getInt("order_id")) {
						Duckie duckie = new Duckie(rs.getInt("product_id"), 
								rs.getString("product_name"),
								rs.getDouble("price"), 
								rs.getString("description"), 
								rs.getString("quality"));
						LineItem lineItem = new LineItem(duckie, rs.getInt("quantity"));
						order.addLineItem(lineItem);
						doNext = false;
					} 
				}
				if(doNext == true) {
					Order order = new Order(rs.getInt("order_id"), rs.getString("storefront_name"));
					Duckie duckie = new Duckie(rs.getInt("product_id"), 
							rs.getString("product_name"),
							rs.getDouble("price"), 
							rs.getString("description"), 
							rs.getString("quality"));
					LineItem lineItem = new LineItem(duckie, rs.getInt("quantity"));
					order.addLineItem(lineItem);
					orders.add(order);
				} else {
					doNext = true;
				}
				
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something went wrong");
			return null;
		}
		return orders;
	}
	
	public ArrayList<Order> getAllByStorefrontId(StoreFront storeFront) {

		ArrayList<Order> orders = new ArrayList<>(); //create line item, add it to order
		
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from orders \r\n"
					+ "natural inner join order_items \r\n"
					+ "natural inner join products \r\n"
					+ "natural inner join storefronts\r\n"
					+ "where storefront_id = ?\r\n"
					+ "order by order_id;";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, storeFront.getId());
			ResultSet rs = pstmt.executeQuery();
			
			boolean doNext = true;

			while(rs.next()) {
				for(Order order : orders) {
					if(order.getId() == rs.getInt("order_id")) {
						Duckie duckie = new Duckie(rs.getInt("product_id"), 
								rs.getString("product_name"),
								rs.getDouble("price"), 
								rs.getString("description"), 
								rs.getString("quality"));
						LineItem lineItem = new LineItem(duckie, rs.getInt("quantity"));
						order.addLineItem(lineItem);
						doNext = false;
					} 
				}
				if(doNext == true) {
					Order order = new Order(rs.getInt("order_id"), rs.getString("storefront_name"));
					Duckie duckie = new Duckie(rs.getInt("product_id"), 
							rs.getString("product_name"),
							rs.getDouble("price"), 
							rs.getString("description"), 
							rs.getString("quality"));
					LineItem lineItem = new LineItem(duckie, rs.getInt("quantity"));
					order.addLineItem(lineItem);
					orders.add(order);
				} else {
					doNext = true;
				}
				
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something went wrong");
			return null;
		}
		return orders;
	}
	
	

	@Override
	public void addInstance(Order newInstance) {
		// TODO Auto-generated method stub
		
		
//	"begin;\r\n"
//	+ "	\r\n"
//	+ "	insert into orders(customer_id, storefront_id) values (10, 1);\r\n"
//	+ "	insert into order_items(order_id, product_id, quantity) values ((select max(order_id) from orders), 6, 2);\r\n"
//	+ "	update storefront_items set quantity = quantity+2 where storefront_id = 1 and product_id =6;\r\n"
//	+ "	insert into order_items(order_id, product_id, quantity) values ((select max(order_id) from orders), 2, 2);\r\n"
//	+ "	update storefront_items set quantity = quantity+2 where storefront_id = 1 and product_id =2;\r\n"
//	+ "commit;"
		//create order
		//create order_items
		//add order items
		//reduce store_items quantity for each product
		
		//needs to updates storeitems and orders items, and add a new order itself
		
	}
	
	public void upDateOrdersAndStoreItems(Customer customer, Order order, StoreFront storeFront) {
		
		StringBuilder query = new StringBuilder();
		query.append("begin;\r\n");
		query.append(" insert into orders(customer_id, storefront_id) values (" 
		+ customer.getId() + ", " + storeFront.getId() + ");\r\n");
		for(LineItem lineItem : order.getLineItemArray()) {
			query.append(
					" insert into order_items(order_id, product_id, quantity) values "
					+ "((select max(order_id) from orders), " + lineItem.getDuckie().getId() 
					+ ", " + lineItem.getQuantity() + ");\r\n");
			query.append(
					" update storefront_items set quantity = quantity-" + lineItem.getQuantity() 
					+ " where storefront_id = " + storeFront.getId() + " and product_id =" + lineItem.getDuckie().getId() + ";\r\n"
					
					);
		}
		query.append("commit;");
		
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			PreparedStatement pstmt = connection.prepareStatement(query.toString()); //conevert to prepared statement
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong");
			//e.printStackTrace();
		}
		
	}

	@Override
	public void updateInstance(Order newInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInstance(Order newInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
