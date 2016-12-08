package datum;

import java.util.ArrayList;

import session.GachonSession;

public class AssiData {
	
	public int forumSec;
	public int menuSec;
	public int articleNo;
	
	public int status;
	
	public String lecName;
	public String title;
	public int[] dueDate = new int[3];
	static GachonSession session;
	
	
	public void getData (GachonSession inSession, ArrayList<AssiData> inTarget){
		
		session = inSession;
		String input = session.getTargetXml("http://eclass.gachon.ac.kr/index.jsp");
		String tmp = "";
		
		
		
		tmp = input.substring(input.indexOf("<div class=\"eclass_report\">"), input.indexOf("<div class=\"eclass_notice_l\">"));
		String[] openEclass = tmp.split("<li>");
		
		System.out.println("=========Assi=========");
		
		
		for(int i = 1; i < openEclass.length; i++){
			
			AssiData tmpCls = new AssiData();
			
			String getStatus = openEclass[i].substring(openEclass[i].indexOf("/images/icon_report_") + 20, openEclass[i].indexOf("</span>"));
			String getLink = openEclass[i].substring(openEclass[i].indexOf("<a href=") + 10, openEclass[i].indexOf("<strong>"));
			String getLecName = openEclass[i].substring(openEclass[i].indexOf("<strong>") + 8, openEclass[i].indexOf("</strong>"));
			getLecName = getLecName.trim();
			String getTitle = openEclass[i].substring(openEclass[i].indexOf("</strong>") + 9, openEclass[i].indexOf("</a>"));
			getTitle = getTitle.trim();
			
			
			
			if(getStatus.indexOf("ok") != -1)
				tmpCls.status = 1;
			else if(getStatus.indexOf("no") != -1)
				tmpCls.status = 0;
			else
				tmpCls.status = -1;
			
			
			
			String tmpLink = getLink.substring(getLink.indexOf("Forum_seq=") + 10, getLink.indexOf("Menu_seq="));
			tmpLink = tmpLink.replaceAll("[^0-9]", "");
			tmpCls.forumSec = Integer.parseInt(tmpLink);
			tmpLink = getLink.substring(getLink.indexOf("Menu_seq=") + 9, getLink.indexOf("article_no="));
			tmpLink = tmpLink.replaceAll("[^0-9]", "");
			tmpCls.menuSec = Integer.parseInt(tmpLink);
			tmpLink = getLink.substring(getLink.indexOf("article_no=") + 11);
			tmpLink = tmpLink.replaceAll("[^0-9]", "");
			tmpCls.articleNo = Integer.parseInt(tmpLink);
			
			
			
			tmpCls.lecName = getLecName;
			
			
			tmpCls.title = getTitle;
			
			
//			System.out.println(tmpCls.status);
//			System.out.println(tmpCls.forumSec);
//			System.out.println(tmpCls.menuSec);
//			System.out.println(tmpCls.articleNo);
//			System.out.println(tmpCls.lecName);
//			System.out.println(getTitle);
			
			inTarget.add(tmpCls);
			
			
		}
		
		
		System.out.println("=========Assi-done=========");
		
		
	}
	
	
}
