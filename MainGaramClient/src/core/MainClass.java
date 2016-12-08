package core;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import datum.AssiData;
import datum.LecData;
import datum.LecDayOfWeek;
import functions.SessionDownloader;
import functions.URLDownloader;
import index.Assignment;
import index.GUI;
import index.Infrom;
import index.LectureNote;
import index.UnivNotice;
import session.CyberGachonSession;
import session.GachonSession;

//Referred by...
//http://woowowoong.blogspot.kr/2011/02/java-cellrenderer-demo.html

public class MainClass {
	
	
	public Font fNanum;// = new Font("맑은고딕", Font.PLAIN, 15);
	public static LecDayOfWeek[] lecList = {new LecDayOfWeek(), new LecDayOfWeek(), new LecDayOfWeek(), new LecDayOfWeek(), new LecDayOfWeek()};
	public static ArrayList<AssiData> assiData;
	
	public static GachonSession session;
	public static loading.GUI loadingWindow;
	
	public MainClass() {
		
	}
	
	
	public static void main(String args[]) throws InterruptedException{
		
		welcome.GUI loginWindow = new welcome.GUI();
		int[] currTime = getCurrTime();
		loginWindow.start();
		
		while(!loginWindow.isLogOn())
			Thread.sleep(10);
		
		
		
		
		
		session = loginWindow.getSession();
		CyberGachonSession sessionC = new CyberGachonSession();
		int statusC = sessionC.logIn(loginWindow.getID(), loginWindow.getPW());
		loginWindow = null;
		
		
		
		//new MainClass();
		GUI indexWindow = new GUI();
		new loading.GUI(indexWindow.GetFrame());
		
		
		
		if(statusC != 0){
			System.out.println("cannot connet cyber campus");
		}
		else{
			
			
			loading.GUI.run();
			
			
			System.out.println("cyber campus connected");
			String myCyber = sessionC.getXml();
			
			
			String myPic = myCyber.substring(myCyber.indexOf("<a class=\"user-info-popover\"")); //<img src=
			myPic = myPic.substring(myPic.indexOf("<img src=") + 10);
			//class = userpicture
			myPic = myPic.substring(0, myPic.indexOf("\" alt="));
			myPic = myPic.substring(0,myPic.indexOf("/f") + 2) + "3";
			URLDownloader.download(myPic, "profile.png");
			indexWindow.INF.SetIcnPic("profile.png");
			
			
			//<div class="user-info-picture">
			//<h4>name
			//<p class="department">dept.
			String myInfo = myCyber.substring(myCyber.indexOf("<div class=\"user-info-picture\">"), myCyber.indexOf("<ul class=\"user-info-submenu clearfix\">"));
			String userName = myInfo.substring(myInfo.indexOf("<h4>")+5, myInfo.indexOf("</h4>"));
			String userDept = myInfo.substring(myInfo.indexOf("<p class=\"department\">")+24, myInfo.indexOf("</p>"));
			userName = userName.trim();
			userDept = userDept.trim();
			
//			System.out.println("=======");
//			System.out.println(userName);
//			System.out.println(userDept);
//			System.out.println("=======");

			
			indexWindow.INF.SetName(userName);
			indexWindow.INF.SetMajor(userDept);
			
			GUI.repaint();
			
			
			
			ArrayList<LecData> lectureData = new ArrayList<LecData>();
			assiData = new ArrayList<AssiData>();
			
			new LecData().getData(session, lectureData);
			new AssiData().getData(session, assiData);
			//파일 및 사용자 추가 주소에서 긁어오는 부분 필요
			
			
			
			
			for(int i = 0; i < lectureData.size(); i++){
				
				LecData tmp = new LecData();
				tmp = lectureData.get(i);
				
				//if(tmp.menuSec == 0 && tmp.articleNo == 0){
					
//					System.out.println("Forum: " + tmp.forumSec);
//					System.out.println("Menu: " + tmp.menuSec);
//					System.out.println("Article: " + tmp.articleNo);
//					
//					System.out.println("강의명: " + tmp.lecName);
//					System.out.println("장소: " + tmp.lecPlace);
//					System.out.println("강의시간: " + tmp.lecTime);
//					
//					System.out.println("교수: " + tmp.profName);
//					System.out.println("파일: " + tmp.title);
//					System.out.println("================");

					String[] tmpStr = tmp.lecTime.split("<d>");
					
					
					for(String input: tmpStr){
						
						if(input.equals(""))
							continue;
						
						int dayOW = input.charAt(0) - '0';
						
						
						//중복 방지
						if(!lecList[dayOW].getArrayList().contains(tmp))
							lecList[dayOW].addItem(tmp);
						
//						if(dayOW == currTime[5]){
//							System.out.println("오늘 수업: " + tmp.lecName);
//						}
						
					}
					
					
				//}
				
			}
			
//			for(int i = 0; i < 5; i++){
//				System.out.println(i + "요일 수업");
//				for(int j = 0; j < lecList[i].size(); j++){
//					
//					LecData tmp = lecList[i].getItem(j);
//					System.out.println("Forum: " + tmp.forumSec);
//					System.out.println("Menu: " + tmp.menuSec);
//					System.out.println("Article: " + tmp.articleNo);
//					
//					System.out.println("강의명: " + tmp.lecName);
//					System.out.println("장소: " + tmp.lecPlace);
//					System.out.println("강의시간: " + tmp.lecTime);
//					
//					System.out.println("교수: " + tmp.profName);
//					System.out.println("파일: " + tmp.title);
//					System.out.println("================");
//					
//					
//				}
//				System.out.println();
//			}
			
			
			
			//오늘 버튼 색
			indexWindow.LN.paintBtn(currTime[5]);
			
			//오늘 꺼 출력!!
			System.out.println(currTime[5] + "요일 수업");
			
			String writeScrollList = indexWindow.LN.makeScrollData(currTime[5]);
			String writeAssiScrollList = indexWindow.ASSN.makeScrollData();
			
			indexWindow.LN.makeScrollList(writeScrollList);
			indexWindow.ASSN.makeScrollList(writeAssiScrollList);
			
			GUI.repaint();
			//buildDOWScrollList
			
			
			
			
			
			String todayClass = "";
			for(int j = 0; j < lecList[currTime[5]].size(); j++){
				LecData tmp = lecList[currTime[5]].getItem(j);
				if(tmp.articleNo == 0 && tmp.menuSec == 0){
					todayClass += "\"" + tmp.lecName.substring(0,12) + "...\"<br>";
				}
			}
			if(!todayClass.equals(""))
				todayClass = todayClass.substring(0, todayClass.length()-4);
			
			indexWindow.INF.SetNextCls(todayClass);
			
			
			
			//Setting values
			//indexWindow.INF.SetNextCls("에듀에코세미나");
			//indexWindow.INF.SetUnivNotice("창업");
			//indexWindow.INF.SetAssignment("알고리즘,컴네");
			
			//String[] input = {":!알고리즘", ":#<html>profile1<br><h4>This is the algorithm class</h4></html>", ":! ", ":!컴퓨터네트워크.", ":@image", ":#f3",":! ", ":!알고리즘 ", ":@profile1",":@profile2",":#profile3", ":! "};
			//indexWindow.LN.updateScrollList(input);
			
			//String[] inputt = {":!알고리즘 - Funny chese", ":!데이터베이스 - HW10 ", ":!컴퓨터네트워크 - HW02"};
			//indexWindow.ASSN.updateScrollList(inputt);
			//indexWindow.repaint();
			
			
		}
		
		//SessionDownloader.download(session.getSession(), a, b, c);
		
		loading.GUI.terminate();
		
	}
	
	
	
	public static int[] getCurrTime(){
		
		Calendar cal = Calendar.getInstance();
		
		int[] res = new int[6];
		
		res[0] = cal.get(Calendar.YEAR);
		res[1] = cal.get(Calendar.MONTH) + 1;
		res[2] = cal.get(Calendar.DAY_OF_MONTH);
		res[3] = cal.get(Calendar.HOUR_OF_DAY);
		res[4] = cal.get(Calendar.MINUTE);
		res[5] = cal.get(Calendar.DAY_OF_WEEK);
		if(res[5] == 1)
			res[5] = 6;
		else
			res[5] -= 2;
		
		return res;
		
	}
	
}

