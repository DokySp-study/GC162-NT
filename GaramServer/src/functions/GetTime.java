package functions;

import java.util.Calendar;

public class GetTime {
	
//	public static void main(String args[]){
//		
//		System.out.println(GetTime.current());   //current time
//		System.out.println(GetTime.dayAfter(-7)); //The day after a week
//		System.out.println(GetTime.dayAfter(7));  //The day before a week
//		
//	}
	
	
	
	public static int current(){
		
		Calendar cal = Calendar.getInstance();
		
		int[] res = new int[3];
		
		res[0] = cal.get(Calendar.YEAR);
		res[1] = cal.get(Calendar.MONTH) + 1;
		res[2] = cal.get(Calendar.DAY_OF_MONTH);
//		res[3] = cal.get(Calendar.HOUR_OF_DAY);
//		res[4] = cal.get(Calendar.MINUTE);
//		res[5] = cal.get(Calendar.DAY_OF_WEEK);
		
		int curr = res[0]*10000 + res[1]*100 + res[2];
		
		return curr;
		
	}
	
	
	
	public static int dayAfter(int push){
		
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DAY_OF_MONTH, push);
		
		int[] res = new int[6];
		
		res[0] = cal.get(Calendar.YEAR);
		res[1] = cal.get(Calendar.MONTH) + 1;
		res[2] = cal.get(Calendar.DAY_OF_MONTH);
//		res[3] = cal.get(Calendar.HOUR_OF_DAY);
//		res[4] = cal.get(Calendar.MINUTE);
//		res[5] = cal.get(Calendar.DAY_OF_WEEK);
		
		int curr = res[0]*10000 + res[1]*100 + res[2];
		
		return curr;
		
	}
	
	
}
