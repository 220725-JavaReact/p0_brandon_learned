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

public class LineItemDAO implements DAO<LineItem>{

	@Override
	public ArrayList<LineItem> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<LineItem> getAllByOrderId(Order order) {
		DuckieDAO duckieDao = new DuckieDAO();
		ArrayList<LineItem> lineItems = new ArrayList<>();
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from order_items where order_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, order.getId());
			ResultSet rs = pstmt.executeQuery();			
			while(rs.next()) {
				lineItems.add(new LineItem(duckieDao.getById(rs.getInt("product_id")), rs.getInt("quantity")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return lineItems;
	}
	
	public int getProductQuantityByStoreId(StoreFront storeFront, Duckie duckie) {
		
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from products\r\n"
					+ "		natural inner join storefront_items where storefront_id = 1 and product_id = 2;";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, storeFront.getId());
			pstmt.setInt(2, duckie.getId());
			ResultSet rs = pstmt.executeQuery();
			return rs.getInt("quantity");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return (Integer) null;
		}
	}

	@Override
	public void addInstance(LineItem newInstance) {
	}
	
	public void addInstanceByOrder(LineItem newInstance, Order order) { //needs to be an order with an id
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into order_items (order_id, product_id, quanity) values (?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, order.getId());
			pstmt.setInt(2, newInstance.getDuckie().getId());
			pstmt.setInt(3, newInstance.getQuantity());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong");
			//e.printStackTrace();
		}
	}

	@Override
	public void updateInstance(LineItem newInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LineItem getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInstance(LineItem newInstance) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "delete from order_items where order_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, newInstance.getId());
			ResultSet rs = pstmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}		
	}

	@Override
	public LineItem getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
