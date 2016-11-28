package session;

//Refered by http://nuripetstory.tistory.com/entry/Facebook-Post-자동-등록-성공
//To log in into facebook homepage

//http://gwpark.tistory.com/2081
//https://ko.wikipedia.org/wiki/XPath

import java.io.*;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;



public class CyberGachonSession {
	
	private static final String URL = "http://cyber.gachon.ac.kr";
	private WebClient webClient;
	private HtmlPage currPage;
	
	
	public CyberGachonSession(){
		
	}
	
	
	public void logIn(String inputID, String inputPW) {
	
		try {
			
			webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
			
			//Setting parts
			webClient.addRequestHeader("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.5,en;q=0.3");
		    webClient.addRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		    webClient.addRequestHeader("Accept-Encoding", "gzip, deflate, br");
		    webClient.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	        //webClient.addRequestHeader("Referer", "https://www.gachon.ac.kr/site/login_sso.jsp");
	        //webClient.addRequestHeader("Connection", "keep-alive");
	        //webClient.addRequestHeader("Upgrade-Insecure-Requests", "1");
	        //webClient.addRequestHeader("(Request-Line)", "POST /sso/pmi-sso-login-uid-password2.jsp HTTP/1.1");
		    
		    webClient.getOptions().setCssEnabled(false);
		    webClient.getOptions().setRedirectEnabled(true);
		    webClient.getOptions().setDoNotTrackEnabled(false);
		    webClient.getOptions().setJavaScriptEnabled(false);
		    webClient.getOptions().setActiveXNative(true);
		    webClient.getOptions().setAppletEnabled(true);
		    
		    webClient.getOptions().setTimeout(5000);
		    
		    webClient.getCookieManager().setCookiesEnabled(true);
		    ///////////////
	        
		    
	        //Prevent SSL handshaking problems
	        //Refered by...
	        //http://stackoverflow.com/questions/5336280/why-doesnt-htmlunit-work-on-this-https-webpage
	        webClient.getOptions().setUseInsecureSSL(true);
	        
	        
	        
	        //Get cyber page
	        HtmlPage page = (HtmlPage) webClient.getPage(URL);
	        
	        //Get forms and pick unique form
	        List<HtmlForm> htmlf = page.getForms();
	        HtmlForm form = htmlf.get(0);
	        
	        //Cyber campus input type
	        form.getInputByName("username").type(inputID);
	      	HtmlInput b = form.getInputByName("password");
	      	b.type(inputPW);
	      	
	      	//Send 'Enter' key
	        page = (HtmlPage) b.type('\n');
	        currPage = page;
	        
	        //Check status
	      	if(page.asXml().indexOf("http://cyber.gachon.ac.kr/login/logout.php") != -1)
	      		System.out.println("Log in Success");
	      	else
	      		System.out.println("Fail!");
	      	
		    webClient.close();
		
		}
		catch(java.net.UnknownHostException e){
			System.out.println("Internet disconnected!");
		}
		catch(org.apache.http.conn.ConnectTimeoutException e){
			System.out.println("Timeout!");
		}
		catch(java.net.SocketTimeoutException e){
			System.out.println("Timeout!");
		}
		catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public String getXml(){
		return currPage.asXml();
	}
	
	
	public String getText(){
		return currPage.asText();
	}
	
}