package DataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.Duckie;
import com.revature.models.LineItem;
import com.revature.models.StoreFront;
import com.revature.util.ConnectionFactory;

public class StoreItemDAO {

	public ArrayList getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addInstance(StoreFront storeFront, LineItem lineItem) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into storefront_items(storefront_id, product_id, quantity) values (?, ?, ?);";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, storeFront.getId());
			pstmt.setInt(2, lineItem.getDuckie().getId());
			pstmt.setInt(3, lineItem.getQuantity());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong");
			//e.printStackTrace();
		}		
	}

	public void updateInstance(LineItem lineItem, StoreFront storeFront) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "update storefront_items set quantity = ? where storefront_id = ? and product_id = ?;";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, lineItem.getQuantity());
			pstmt.setInt(2, storeFront.getId());
			pstmt.setInt(3, lineItem.getDuckie().getId());
			pstmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("Something went wrong");
		}
		
	}

	public Object getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteInstance(Duckie duckie, StoreFront storeFront) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "delete from storefront_items where product_id = ? and storefront_id = ?;";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, duckie.getId());
			pstmt.setInt(2, storeFront.getId());
			pstmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("Something went wrong");
		}
	}

	public Object getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
