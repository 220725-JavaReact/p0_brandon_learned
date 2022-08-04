package DataLayer;

import java.util.ArrayList;

import com.revature.models.Customer;
import com.revature.models.StoreFront;
import com.revature.tempdatastorage.TemporaryStorage;

public class StoreFrontsDAO implements DAO<StoreFront>{

	@Override
	public ArrayList<StoreFront> getAll() {
		ArrayList<StoreFront> storeFrontsArray = new ArrayList<>();
		for(StoreFront storeFront : TemporaryStorage.storeFronts) {
			storeFrontsArray.add(storeFront);
		}
		return storeFrontsArray;
	}

	@Override
	public void addInstance(StoreFront newInstance) {
		// TODO Auto-generated method stub
		
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

}
