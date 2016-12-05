package datum;

import java.util.ArrayList;



public class LecDayOfWeek {
	
	ArrayList<LecData> item;
	
	public LecDayOfWeek(){
		item = new ArrayList<LecData>();
	}
	
	public ArrayList<LecData> getArrayList(){
		return item;
	}
	
	public int size(){
		return item.size();
	}
	
	public void addItem(LecData input){
		item.add(input);
	}
	
	public LecData getItem(int i){
		return item.get(i);
	}
	
	
}
