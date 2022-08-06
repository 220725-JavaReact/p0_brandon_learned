package DataLayer;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.Employee;
import com.revature.util.ConnectionFactory;

public class EmployeeDAO implements DAO<Employee>{

	@Override
	public ArrayList<Employee> getAll() {
		return null;
		
	}

	@Override
	public void addInstance(Employee newInstance) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateInstance(Employee newInstance) {
		// TODO Auto-generated method stub
	}

	@Override
	public Employee getByName(String name) {
		try(Connection connection = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from employees where username = ?";
			PreparedStatement pstmt = connection.prepareStatement(query); //conevert to prepared statement
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Employee(rs.getInt("employee_id"),rs.getString("username"), rs.getString("pw"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}

	@Override
	public void deleteInstance(Employee newInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
