package DataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Duckie;
import com.revature.models.StoreFront;
import com.revature.util.ConnectionFactory;

public class DuckieDAO implements DAO<Duckie>{

	@Override
	public ArrayList<Duckie> getAll() {
		ArrayList<Duckie> duckies = new ArrayList<>();
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from products";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				duckies.add(new Duckie(rs.getInt("product_id"), rs.getString("product_name"), rs.getDouble("price"), rs.getString("description"), rs.getString("quality")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return duckies;
	}
	
	public ArrayList<Duckie> getAllNotInStoreById(StoreFront storeFront) {
		ArrayList<Duckie> duckies = new ArrayList<>();
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from products natural inner join storefronts natural inner join storefront_items where storefront_id != " + storeFront.getId() + " order by product_id;";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				duckies.add(new Duckie(rs.getInt("product_id"), rs.getString("product_name"), rs.getDouble("price"), rs.getString("description"), rs.getString("quality")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return duckies;
	}
	
	
	
	public ArrayList<Duckie> getAllByStoreId(StoreFront storeFront) {
		ArrayList<Duckie> duckies = new ArrayList<>();
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from storefront_items natural inner join products where storefront_id = ?;";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, storeFront.getId());
			ResultSet rs = pstmt.executeQuery();			
			while(rs.next()) {
				duckies.add(new Duckie(rs.getInt("product_id"),  rs.getString("product_name"), rs.getDouble("price"), rs.getString("description"), rs.getString("quality")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return duckies;
	}

	@Override
	public void addInstance(Duckie newInstance) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "insert into products (product_name, price, description, quality) values (?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setString(1, newInstance.getName());
			pstmt.setDouble(2, newInstance.getPrice());
			pstmt.setString(3, newInstance.getDescription());
			pstmt.setString(4, newInstance.getQuality());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong");
			//e.printStackTrace();
		}
	}

	@Override
	public void updateInstance(Duckie newInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Duckie getByName(String name) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from products where product_name = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Duckie(rs.getInt("product_id"), rs.getString("product_name"), rs.getDouble("price"), rs.getString("description"), rs.getString("quality"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}

	@Override
	public void deleteInstance(Duckie newInstance) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "delete from products where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, newInstance.getId());
			ResultSet rs = pstmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}

	@Override
	public Duckie getById(int id) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from products where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Duckie(rs.getInt("product_id"), rs.getString("product_name"), rs.getDouble("price"), rs.getString("description"), rs.getString("quality"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}

}
