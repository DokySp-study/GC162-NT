package session;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

//Refered by http://nuripetstory.tistory.com/entry/Facebook-Post-자동-등록-성공
//To log in into facebook homepage
//http://gwpark.tistory.com/2081
//https://ko.wikipedia.org/wiki/XPath



public class GachonSession {
	
	private static String URL;
	private WebClient webClient;
	private HtmlPage currPage;
	
	
	public GachonSession(){
		
	}
	
	
	public String logIn(String inputID, String inputPW) {
		
	  	try {
	  		URL = "https://sson.kyungwon.ac.kr/sso/pmi-sso-login-uid-password2.jsp?uid=" + inputID + "&password=" + inputPW + "&gid=gid_lib&return_url=http://www.gachon.ac.kr/main.jsp";
	  		
	  		webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
	  		
	  		webClient.addRequestHeader("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.5,en;q=0.3");
	        webClient.addRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	        webClient.addRequestHeader("Accept-Encoding", "gzip, deflate, br");
	        webClient.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	        webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:50.0) Gecko/20100101 Firefox/50.0");
	        
	        webClient.getOptions().setCssEnabled(false);
	        webClient.getOptions().setRedirectEnabled(true);
	        webClient.getOptions().setDoNotTrackEnabled(false);
	        webClient.getOptions().setJavaScriptEnabled(false);
	        webClient.getOptions().setActiveXNative(true);
	        webClient.getOptions().setAppletEnabled(true);
	        webClient.getCookieManager().setCookiesEnabled(true);
	        webClient.getJavaScriptTimeout();
	        
	        webClient.getOptions().setTimeout(5000);
	        
	        
	        //Prevent SSL handshaking problems
	        //Refered by 
	        //http://stackoverflow.com/questions/5336280/why-doesnt-htmlunit-work-on-this-https-webpage
	        webClient.getOptions().setUseInsecureSSL(true);
	        
	        HtmlPage page = (HtmlPage) webClient.getPage(URL);
	        page = webClient.getPage("http://eclass.gachon.ac.kr/index.jsp");
	        currPage = page;
	        
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
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	}
	  	
	  	return URL;
	  	
	}
	
	
	public String getXml(){
		return currPage.asXml();
	}
	
	
	public String getText(){
		return currPage.asText();
	}
	
	
	public String getTargetXml(String targetURL){
		
		HtmlPage page = null;
		
		try {
			page = webClient.getPage(targetURL);
		}
		catch(java.lang.NullPointerException e){
	  		System.out.println("Wrong URL!");
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
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	}
		
		return page.asXml();
		
	}
	
}
