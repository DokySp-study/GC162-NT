package functions;

import java.io.IOException;
import java.net.MalformedURLException;

//Referred by…
//http://blog.naver.com/PostView.nhn?blogId=battle50&logNo=220216222487&categoryNo=10&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView
//RSS 2.0 Documentation
//http://comefeel.com/tt/comefeel/entry/Javascript-를-이용한-초간단-Rss-Reader-구현하기-1

import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RSSReader {   
  
   private URL rssURL;
   private Document currDoc;
   private String totalContents;
   private boolean isUpdated;
  
   private RSSReader() {
	   
	   isUpdated = false;
	   
	   try {
		   rssURL = new URL("http://www.gachon.ac.kr/etc/rss.jsp?boardType_seq=358");
		   
	       DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();   
	       currDoc = builder.parse(rssURL.openStream());   //java.net.UnknownHostException
		   
	   } catch (MalformedURLException e) {
		   System.out.println("RSS link was expired!");
		   e.printStackTrace();
	   } catch (ParserConfigurationException e) {
		   e.printStackTrace();
	   } catch (SAXException e) {
		   e.printStackTrace();
	   } catch (IOException e) {
		   e.printStackTrace();
	   }  
	   
   }
   
  
   public void writeFeed(HashMap<String, String> index) {   
      try {   
         
         totalContents = "<RSS>\n";
         NodeList items = currDoc.getElementsByTagName("item");
         
         //title: 제목.
         //author: 글쓴이.
         //link: 원문 링크.
         //category: 글로벌/메디컬/공통.
         //description: contents.
         //dc:date: date when is uploaded.
         
         for (int i = 0; i < items.getLength(); i++) {
        	 Element item = (Element) items.item(i);
        	 String currContents = "";
        	 
        	 if(!index.containsKey(getValue(item, "link"))){
        		 currContents += "<item>\n";
        		 currContents += getValue(item, "title") + " <" + getValue(item, "category") + ">\n";
	             currContents += getValue(item, "dc:date") + '\n';
	             //currContents += getValue(item, "author") + '\n';
	             currContents += getValue(item, "link") + '\n';
	             currContents += getValue(item, "category") + '\n';
	             currContents += getValue(item, "description") + '\n';
	             currContents += "</item>\n";
	             index.put(getValue(item, "link"), currContents);
	             
	             totalContents += currContents;
	             currContents = "";
	             isUpdated = true;
	             
        	 }
        	 else{
        		 System.out.println("abort!!");
        	 }
         }
         
         //totalContents = totalContents.replaceAll("&nbsp;&nbsp;", "&nbsp;");
         
         totalContents = totalContents.replaceAll("&nbsp;", "\n");
         
         totalContents += "</RSS>\n";
         //System.out.println(totalContents);
         
      }
      catch(java.lang.NullPointerException e){
    	  System.out.println("Tag not exist!");
    	  e.printStackTrace();	
      }
      catch(Exception e){
    	  System.out.println("Unknown error was corrupted");
    	  e.printStackTrace();
      }
      
   }   
  
   
   public String getValue(Element parent, String nodeName) {   
      return parent.getElementsByTagName(nodeName).item(0).getFirstChild().getNodeValue();   
   }
   
   
   public Document getCurrDoc(){
	   return currDoc;
   }
   
   
   public String getDocumentText(){
	   return totalContents;
   }
   
   
   public boolean isUpdated(){
	   return isUpdated;
   }
   
   
//   public static void main(String[] args) {
//	   
//	   RSSReader reader = new RSSReader();
//	   HashMap a = new HashMap<String, String>();
//	   reader.writeFeed(a);
//	   
//	   Set<Entry<String, String>> tmpSet = a.entrySet();
//	   Iterator<Entry<String, String>> tmp = tmpSet.iterator();
//	   
//	   while(tmp.hasNext()){
//		   Entry<String, String> asdf = tmp.next();
//		   System.out.println(asdf.getKey() + "\n********************************\n");
//	   }
//	   
//   }
   
}
