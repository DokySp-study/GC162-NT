package functions;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

//Referred by...
//http://dorbae.blogspot.kr/2015/04/java-how-to-open-pdf-file-pdf.html

public class OpenFile {
	
//Referred by...
//http://kin.naver.com/qna/detail.nhn?d1id=1&dirId=1040201&docId=163369551&qb=amF2YSDsm7ntjpjsnbTsp4Ag7Je06riw&enc=utf8&section=kin&rank=1&search_sort=0&spq=0&pid=TZTRfwpVuECsscOOc8dsssssss0-416678&sid=au5Mj3trN3MikymIEtXy3A%3D%3D
//	public static void main(String[] args) {
//		//String cmdName = "explorer http://www.naver.com";
//		String cmdName = "open http://eclass.gachon.ac.kr/board/view.jsp?Forum_seq=115389&Menu_seq=838254&pageNum=0&pageSize=10&article_no=1488992&searchopt=TITLE&searchword=";
//		try {
//			Runtime.getRuntime().exec(cmdName);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	} 
	
	
	
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
