package DataLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Duckie;
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
				duckies.add(new Duckie(rs.getString("team_name"), rs.getInt("points"), rs.getInt("team_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teams.getAllElements();

		
		return null;
	}

	@Override
	public void addInstance(Duckie newInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInstance(Duckie newInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Duckie getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInstance(Duckie newInstance) {
		// TODO Auto-generated method stub
		
	}

}
