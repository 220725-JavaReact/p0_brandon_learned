package DataLayer;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Duckie;
import com.revature.models.LineItem;
import com.revature.models.Order;
import com.revature.models.StoreFront;
import com.revature.util.ConnectionFactory;

public class StoreFrontsDAO implements DAO<StoreFront>{

	@Override
	public ArrayList<StoreFront> getAll() {
		ArrayList<StoreFront> storeFronts = new ArrayList<>();
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from storefronts";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				storeFronts.add(new StoreFront(rs.getInt("storefront_id"), rs.getString("storefront_name"), rs.getString("address")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return storeFronts;
	}

	@Override
	public void addInstance(StoreFront newInstance) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into storefronts(storefront_name, address) values (?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setString(1, newInstance.getName());
			pstmt.setString(2, newInstance.getAddress());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong");
			//e.printStackTrace();
		}
		
	}

	@Override
	public void updateInstance(StoreFront newInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StoreFront getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public StoreFront getByAddress(String address) {
//		for(StoreFront storeFront : TemporaryStorage.storeFronts) {
//			if(storeFront.getAddress().equals(address)) {
//				return storeFront;
//			}
//		}
		return null;
	}

	@Override
	public void deleteInstance(StoreFront newInstance) {
		// TODO Auto-generated method stub	
	}

	@Override
	public StoreFront getById(int id) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from storefronts where storefront_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new StoreFront(rs.getInt("storefront_id"), rs.getString("storefront_name"), rs.getString("address"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}
	
	public void initializeAllDuckies(StoreFront storeFront){

		ArrayList<LineItem> lineItems = new ArrayList<>(); //create line item, add it to order
		
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from products\r\n"
					+ "natural inner join storefront_items where storefront_id = ?;";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, storeFront.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Duckie duckie = new Duckie(rs.getInt("product_id"), 
						rs.getString("product_name"),
						rs.getDouble("price"),
						rs.getString("description"),
						rs.getString("quality"));
				LineItem lineItem = new LineItem(duckie, rs.getInt("quantity"));
				lineItems.add(lineItem);
			}	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something went wrong");
		}

		storeFront.setLineItems(lineItems);

	}
	
	public ArrayList<Order> initializeAllOrders(){
		return null;
	}

}
