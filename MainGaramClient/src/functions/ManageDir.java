package functions;

import java.io.File;
import java.util.ArrayList;

//Referred by...
//http://ra2kstar.tistory.com/129

public class ManageDir {
	
	public static void main(String args[]){
		fileList(115389, 838254, 1492607);
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
	
	
	public static ArrayList<File> fileList(int secNum, int boardNum, int artiNum){
		
		ArrayList<File> result = new ArrayList<File>();
		
		String rootDir = "lecFile/";
		String finalDir = rootDir + "/" + secNum + "/" + boardNum + "/" + artiNum;
		File route = new File(finalDir);
		
		File[] tmpList = route.listFiles();
		
		for(File tmp: tmpList)
			result.add(tmp);
		
		for(int i = 0; i < result.size(); i++){
			if(result.get(i).getName().charAt(0) == '.')
				result.remove(i);
		}
		
		return result;
		
	}
	
}
