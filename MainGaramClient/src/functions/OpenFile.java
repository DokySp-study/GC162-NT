package functions;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

//Referred by...
//http://dorbae.blogspot.kr/2015/04/java-how-to-open-pdf-file-pdf.html

public class OpenFile {
	
	public static boolean open(int secNum, int boardNum, int artiNum){
		
		String rootDir = "lecFile/";
		String finalDir = rootDir + "/" + secNum + "/" + boardNum + "/" + artiNum;
		
		File totalList = new File(finalDir);
		File[] fList = totalList.listFiles();		
		
		
		if(Desktop.isDesktopSupported()){
			
			for(File target: fList){
			
				try {
					Desktop.getDesktop().open(target);
				} catch (IOException e) {
					System.out.println("Cannot open file");
					e.printStackTrace();
				}
			}
			
		}
		else{
			System.out.println("Cannot use system");
		}
		
		
		return true;
		
	}
	
	
}
