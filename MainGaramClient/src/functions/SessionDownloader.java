package functions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

//Referred by..
//http://stackoverflow.com/questions/1858412/download-a-non-html-file-with-htmlunit


public class SessionDownloader {
//	
//	private static int forum_sec;
//	private static int menu_seq;
//	private static int article_no;
//	
	
	public static boolean download(WebClient webClient, int forum_sec, int menu_seq, int article_no){
		
		HtmlPage page;
		
		if(ManageDir.find(forum_sec, menu_seq, article_no)){
			System.out.println("Already exists!");
			return false;
		}
		
		try {
			String url = "http://eclass.gachon.ac.kr/board/view.jsp?Forum_seq="+forum_sec+"&Menu_seq="+menu_seq+"&article_no="+article_no;
			String upperDiv = null;
			List<String> filenames = new ArrayList<String>();
			
			
			page = (HtmlPage) webClient.getPage(url);
			url = page.asXml();
			
			upperDiv = url.substring(url.indexOf("<div class=\"boardview\">"), url.length());
			upperDiv = upperDiv.substring(0, upperDiv.indexOf("</div>"));
			//이클래스 내 상단 부분 따오는 부분.
			
			String cropFilename = upperDiv;
			
			while(cropFilename.indexOf("<a href=\"../board/FileDownLoad.jsp?") != -1){
				
				String tmpFilename = cropFilename.substring(cropFilename.indexOf("<a href=\"../board/FileDownLoad.jsp?"));
				tmpFilename = tmpFilename.substring(tmpFilename.indexOf("\">") + 2);
				tmpFilename = tmpFilename.substring(0, tmpFilename.indexOf("</a>"));
				tmpFilename = tmpFilename.trim();
				
				filenames.add(tmpFilename);
				
				cropFilename = cropFilename.substring(cropFilename.indexOf("<a href=\"../board/FileDownLoad.jsp?"));
				cropFilename = cropFilename.substring(cropFilename.indexOf("</a>"));
				cropFilename = cropFilename.substring(0, cropFilename.indexOf("</td>") + 5);
				
			}
			//여기까지가 한 파일 이름 따오는 코드.

			System.out.println(filenames.size());
			System.out.println(filenames.get(0));
			
			//http://eclass.gachon.ac.krboard/FileDownLoad.jsp?Forum_seq=115389&amp;Menu_seq=838254&amp;article_no=1490349&amp;af_seq=438501
			for(int i = 0; i < filenames.size(); i++){
				
				HtmlAnchor clickLink = page.getAnchorByText(filenames.get(i));
				InputStream in = clickLink.click().getWebResponse().getContentAsStream();
				
				String tmpName = filenames.get(i);
				tmpName = tmpName.substring(0, tmpName.indexOf(" ("));
				
				
				
				ManageDir.make(forum_sec, menu_seq, article_no);
				
				File f = new File("lecFile/" + forum_sec + "/" + menu_seq + "/" + article_no + "/" + tmpName);
				FileOutputStream ou = new FileOutputStream(f);
				
				byte[] buffer = new byte[1500];
				int len = 0;
				len = in.read(buffer);
				while(len > 0){
					ou.write(buffer, 0, len);
					len = in.read(buffer);
				}
				ou.close();
				in.close();
				
				System.out.println(i + "'th done.");
				
			}
			
			return true;
			
			
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
			return false;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
	
}




//url = url.substring(url.indexOf("<div class=\"boardview\">"), url.length());
//url = url.substring(0, url.indexOf("</div>"));
//
//url = url.substring(url.indexOf("<a href=\"../board/FileDownLoad.jsp?"), url.length());
//url = url.substring(0, url.indexOf(">"));
//url = "http://eclass.gachon.ac.kr" + url.substring(12, url.length()-1);
////url = url.substring(9, url.length()-1);
////여기까지가 한 세션 첫 번째 파일 주소 따오는 코드.
