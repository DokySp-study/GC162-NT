package functions;

import java.io.File;

//Referred by...
//http://ra2kstar.tistory.com/129

public class ManageDir {
	
	public ManageDir(){
		
	}
	
	
	public static boolean find(int secNum, int boardNum, int artiNum){
		
		String rootDir = "lecFile/";
		String finalDir = rootDir + "/" + secNum + "/" + boardNum + "/" + artiNum;
		File route = new File(finalDir);
		
		return route.exists();
		
	}
	
	
	public static boolean make(int secNum, int boardNum, int artiNum){
		
		String rootDir = "lecFile/";
		String finalDir = rootDir + "/" + secNum + "/" + boardNum + "/" + artiNum;
		File route = new File(finalDir);
		
		return route.mkdirs();
		
	}
	
}
