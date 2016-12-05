package datum;

import java.util.ArrayList;
import session.GachonSession;



public class LecData {
	
	
	public int forumSec;
	public int menuSec;
	public int articleNo;
	
	public String profName;
	public String lecName;
	public String lecTime;  //월5
	public String title;
	public String lecPlace;
	public int[] upToDate = new int[3];
	static GachonSession session;
	
	public LecData (){
		
	}
	
	//ArrayList<LecData> input
	
	public void getData (GachonSession inSession, ArrayList<LecData> inTarget){
		
		session = inSession;
		String input = session.getTargetXml("http://eclass.gachon.ac.kr/index.jsp");
		String tmp = "";
		
		
//		모든 이클래스 개설 강좌 긁어오기.        //<div class="eclass_list"> -- <h5 class="h5_report">
//			해당 이클래스 강의자료실 긁어오기.    //<div class="boardlist"> -- <div class="p_total">
//				해당 보드판에서 정보 긁어오기.   //setInfo();
		
		
		
		tmp = input.substring(input.indexOf("<div class=\"myeclass\">"), input.indexOf("<h5 class=\"h5_report\">"));
		String[] openEclass = tmp.split("<li>");
		
		
		for(int i = 1; i < openEclass.length; i++){
			
			//Find e=-class what was opened
			if(openEclass[i].indexOf("./images/icon_open.gif") == -1)
				continue;
			
			tmp = openEclass[i].substring(openEclass[i].indexOf("Forum_seq=") + 10, openEclass[i].indexOf("title="));
			tmp = tmp.trim();
			tmp = tmp.substring(0, tmp.length()-1);
//			System.out.println(tmp);
//			System.out.println("===========");
			ArrayList<LecData> target = inTarget;
			
			findAllBoard(Integer.parseInt(tmp), target);
//			a[i] = new thdProcess(Integer.parseInt(tmp), target);
//			a[i].start();
			
			
//			System.out.println("=========00=========");
			
		}
		
		
		System.out.println("=========done=========");
		
		
	}
	
	
	
	
	
	
	public void findAllBoard(int inputForumSec, ArrayList<LecData> target ){
		System.out.println("======th======");
		String tmp1 = session.getTargetXml("http://eclass.gachon.ac.kr/main/myMain.jsp?Forum_seq=" + inputForumSec);
		
		
		String inLecName = tmp1.substring(tmp1.indexOf("<div class=\"summary\">"), tmp1.indexOf("<div class=\"myeclass_box\">"));
		String[] tmpM = inLecName.split("<dd");
		
		
		inLecName = tmpM[0].substring(tmpM[0].indexOf("<dt>") + 4, tmpM[0].indexOf("&lt;"));
		inLecName = inLecName.trim();
		//<img src=  --  </dd>
		String lecPlace = tmpM[3].substring(tmpM[3].indexOf("<img src="), tmpM[3].indexOf("</dd>"));
		lecPlace = lecPlace.substring(lecPlace.indexOf("/>") + 2);
		lecPlace = lecPlace.trim();
		
		String lecTime = tmpM[2].substring(tmpM[2].indexOf("<img src="), tmpM[2].indexOf("</dd>"));
		lecTime = lecTime.substring(lecTime.indexOf("/>") + 2);
		lecTime = lecTime.trim();
		
		lecTime = lecTime.replaceAll("\uC6D4", "<d>0:");
		lecTime = lecTime.replaceAll("\uD654", "<d>1:");
		lecTime = lecTime.replaceAll("\uC218", "<d>2:");
		lecTime = lecTime.replaceAll("\uBAA9", "<d>3:");
		lecTime = lecTime.replaceAll("\uAE08", "<d>4:");
		
		String[] date = lecTime.split(" ,");
		String tmpStr = "";
		for(int ii = 0; ii < date.length; ii++)
			tmpStr += date[ii];
		lecTime = tmpStr;
		
//		System.out.println(inLecName);
//		System.out.println(lecPlace);
//		System.out.println(lecTime);
		
		LecData input = new LecData();
		
		input.forumSec = inputForumSec;
		
		input.lecName = inLecName;
		input.lecPlace = lecPlace;
		input.lecTime = lecTime;
		
		target.add(input);
		
		
		
		
		
		
		
		String[] menuList = tmp1.split("<li>");
		
		for(int i = 2; i < menuList.length; i++){
			
			if(menuList[i].indexOf("강의자료실") == -1)
				continue;
			
			//Menu_seq=
			tmp1 = menuList[i].substring(menuList[i].indexOf("Menu_seq=") + 9, menuList[i].indexOf("\">"));
//			System.out.println(tmp1);
//			System.out.println(i);
			
			findAllArticle(Integer.parseInt(tmp1), inputForumSec, target);
			
			
		}
		
	}
	
	
	
	public void findAllArticle(int inputMenuSec, int inputForumSec, ArrayList<LecData> target){
		
		String tmp = session.getTargetXml("http://eclass.gachon.ac.kr/board/list.jsp?"
				+ "Forum_seq=" + inputForumSec + "&Menu_seq=" + inputMenuSec + "&pageSize=100");
		int inArticleNo = 0;
		String inLecName;
		String lecPlace;
		String lecTime;  //월5
		String inTitle;
		String inDate;
		
		String inProfName = tmp.substring(tmp.indexOf("<div id=\"container\">"), tmp.indexOf("<ul class=\"dep\">"));
		String tmpN[] = inProfName.split("<strong>");
		inProfName = tmpN[1].substring(tmpN[1].indexOf("</strong>")+9, tmpN[1].indexOf("</li>"));
		inProfName = inProfName.trim();
		inProfName = inProfName.substring(1, inProfName.length()-1);
		
		
		
//		System.out.println(inLecName);
//		System.out.println("=========");
//		System.out.println(lecPlace);
//
//		System.out.println("=========");
//		System.out.println(lecTime);
//
//		System.out.println("=========");
		
		if(tmp.indexOf("등록된 글이 없습니다") != -1)
			return;
		
		
		
		//<div class="summary"> -- <div class="total">
		inLecName = tmp.substring(tmp.indexOf("<div class=\"summary\">"), tmp.indexOf("<div class=\"total\">"));
		String[] tmpM = inLecName.split("<dd");
		
		inLecName = tmpM[0].substring(tmpM[0].indexOf("<dt>") + 4, tmpM[0].indexOf("&lt;"));
		inLecName = inLecName.trim();
		//<img src=  --  </dd>
		lecPlace = tmpM[3].substring(tmpM[3].indexOf("<img src="), tmpM[3].indexOf("</dd>"));
		lecPlace = lecPlace.substring(lecPlace.indexOf("/>") + 2);
		lecPlace = lecPlace.trim();
		
		lecTime = tmpM[2].substring(tmpM[2].indexOf("<img src="), tmpM[2].indexOf("</dd>"));
		lecTime = lecTime.substring(lecTime.indexOf("/>") + 2);
		lecTime = lecTime.trim();
		
		
		lecTime = lecTime.replaceAll("\uC6D4", "<d>0:");
		lecTime = lecTime.replaceAll("\uD654", "<d>1:");
		lecTime = lecTime.replaceAll("\uC218", "<d>2:");
		lecTime = lecTime.replaceAll("\uBAA9", "<d>3:");
		lecTime = lecTime.replaceAll("\uAE08", "<d>4:");
		
		String[] date = lecTime.split(" ,");
		String tmpStr = "";
		for(int ii = 0; ii < date.length; ii++)
			tmpStr += date[ii];
		lecTime = tmpStr;
		
		
		
		
		
		
		
		
		
		tmp = tmp.substring(tmp.indexOf("<div class=\"total\">"), tmp.indexOf("<div class=\"p_total\">"));
		
		String[] lecNoteList = tmp.split("<tr>");
		
		
		
		for(int i = 2; i < lecNoteList.length; i++){
			
			String total = lecNoteList[i];
			String[] tmpO = total.split("<td");
			
//			System.out.println("cont: " + total);
			
			//)"> -- </a>
			inTitle = tmpO[2].substring(tmpO[2].indexOf("javascript"), tmpO[2].indexOf("</a>"));
			inTitle = inTitle.substring(inTitle.indexOf("\">") + 2);
			inTitle = inTitle.trim();
			//inTitle = inTitle.replaceAll("[?]", " ");
			
			
			
			// -- </td>
			inDate = tmpO[4].substring(1, tmpO[4].indexOf("</td>"));
			inDate = inDate.trim();
			
//			System.out.println("title: " + inTitle);
//			System.out.println("date: " + inDate);
			
			
			String articleNum = total.substring(total.indexOf("javascript:goPage") + 17, total.indexOf("</a>"));
			
			articleNum = articleNum.substring(articleNum.indexOf(",") + 1, articleNum.indexOf(")"));
			
			inArticleNo = Integer.parseInt(articleNum);
			
			//System.out.println("=============");
			
			
			
			//Build new node data
			LecData input = new LecData();
			
			input.articleNo = inArticleNo;
			input.forumSec = inputForumSec;
			input.menuSec = inputMenuSec;
			
			input.lecName = inLecName;
			input.lecPlace = lecPlace;
			input.lecTime = lecTime;
			
			input.profName = inProfName;
			input.title = inTitle;
			String[] tmpP = inDate.split("[.]");
			if(tmpP.length == 3){
				input.upToDate[0] = Integer.parseInt(tmpP[0]);
				input.upToDate[1] = Integer.parseInt(tmpP[1]);
				input.upToDate[2] = Integer.parseInt(tmpP[2]);
			}
			
			target.add(input);
			
			//System.out.println("http://eclass.gachon.ac.kr/board/view.jsp?Forum_seq="+inputForumSec+"&Menu_seq="+inputMenuSec+"&article_no=" + inArticleNo);
		}
		
	}
	
	
	
	
	public ArrayList<Integer> checkData(ArrayList<LecData> compare){
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		a.add(12);
		
		return a;
	}
	
	
	
	
	
//	private class thdProcess extends Thread {
//		
//		int forumSec;
//		ArrayList<LecData> target;
//		
//		public thdProcess(int inputForumSec, ArrayList<LecData> input){
//			forumSec = inputForumSec;
//			target = input;
//			
//		}
//		
//		public void run(){
//			findAllBoard(forumSec, target);
//		}
//		
//	}
	
	//java.lang.StringIndexOutOfBoundsException
	
}


//public static void setInfo(String input, LecData target){
//
//String ContainerDiv = input.substring(input.indexOf("<div class=\"l_top\">"), input.indexOf("<ul class=\"dep\">"));
//String lecInfoDiv = input.substring(input.indexOf("<div class=\"eclass\">"), input.indexOf("<div class=\"total\">"));
//String boardDiv = input.substring(input.indexOf("<div class=\"boardview\">"), input.indexOf("<div class=\"foot\">"));
//
//System.out.println("=======");
//System.out.println(ContainerDiv);
//System.out.println("=======");
//System.out.println(lecInfoDiv);
//System.out.println("=======");
//System.out.println(boardDiv);
//System.out.println("=======");
//
//
//}





//http://eclass.gachon.ac.kr/index.jsp
//Example file exists in eclass.txt.

//http://eclass.gachon.ac.kr/board/list.jsp?Forum_seq=115389&Menu_seq=838254&pageSize=100
//Example file exists in eclass_list.txt.

//http://eclass.gachon.ac.kr/board/view.jsp?Forum_seq=115389&Menu_seq=838254&article_no=1491519
//Example file exists in eclass_article.txt.