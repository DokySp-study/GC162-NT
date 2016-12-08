package functions;

import java.io.IOException;

//Referred by...
//http://mwultong.blogspot.com/2006/10/java-text-file-write.html
//http://mwultong.blogspot.com/2007/01/java-os-id.html

public class URLOpen {
	
//	public static void main(String args[]){
//		open("http://www.naver.com");
//	}
	
	public static boolean open(String url){
		
		String OS = System.getProperty("os.name");
		
				
		Runtime rt = Runtime.getRuntime();
		try {
			
			if(OS.indexOf("Mac") == -1) //Windows
				rt.exec("explorer " + url);
			else  						//Mac
				rt.exec("open " + url);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	
}
