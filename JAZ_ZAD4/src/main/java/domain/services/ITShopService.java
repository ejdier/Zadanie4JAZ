package domain.services;

import java.util.List;
import java.util.ArrayList;

import domain.ITShop;


public class ITShopService {

	private static List<ITShop> db = new ArrayList<ITShop>();
	private static int currentId = 1;
	public List<ITShop> getAll(){
		return db;
	}
	public ITShop get(int id){
		for(ITShop t : db){
			if(t.getId() == id)
				return t;
		}
		return null;
	}
	public void add(ITShop t){
		t.setId(++currentId);
		db.add(t);
	}
	public void update(ITShop thing){
		for(ITShop t : db){
			if(t.getId()==thing.getId()){
				t.setName(thing.getName());
				t.setType(thing.getType());
				t.setPrice(thing.getPrice());

			}
		}
	}
}
