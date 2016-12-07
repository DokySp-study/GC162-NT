package index;

import javax.swing.*;

import core.MainClass;
import datum.LecData;
import functions.MakeMargin;
import functions.ManageDir;
import functions.OpenFile;
import functions.SessionDownloader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LectureNote implements ActionListener { //2
    
	public static LectureNote sharedClass;
	
    Font fNanumBig = new Font("맑은 고딕", Font.PLAIN, 30);
    Font fNanumMed = new Font("맑은 고딕", Font.PLAIN, 23);
    Font fNanumSml = new Font("맑은 고딕", Font.PLAIN, 18);
    public JPanel pnlLn;
    GridBagConstraints gbc;
    JLabel lblTitle;
    JPanel pnlDays;
    JButton btnM, btnTu, btnW, btnTh, btnF;
    ScrollList scrollList;
    public JScrollPane scrollPan;
    
    JButton[] btnDate = {new JButton(), new JButton(), new JButton(), new JButton(), new JButton()};
    
    LectureNote LNSet;
    
    GridBagLayout tGrid;
    GridBagConstraints grid_conf;
    
    
    //강의 리스트
    public LectureNote()
    {
    	sharedClass = this;
    	
        pnlLn = new JPanel();
        pnlLn.setBackground(new Color(255,255,255));
        
        tGrid = new GridBagLayout();
        grid_conf = new GridBagConstraints();
        pnlLn.setLayout(tGrid);
        
        
        JLabel lblMargin = new JLabel();
        lblMargin.setIcon(MakeMargin.build(1, 12));
        grid_conf.gridx = 0;
        grid_conf.gridy = 0;
        tGrid.setConstraints(lblMargin, grid_conf);
        pnlLn.add(lblMargin);
        
        
        /////////////////////////////////
        /////////// u_Label /////////////
        /////////////////////////////////
        
        lblTitle = new JLabel("Lecture note");
        lblTitle.setFont(fNanumBig);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setPreferredSize(new Dimension(GUI.WINDOW_WIDTH/4, (int)(GUI.WINDOW_HEIGHT*(0.1))));
        
        grid_conf.gridx = 0;
        grid_conf.gridy = 1;
        tGrid.setConstraints(lblTitle, grid_conf);
        pnlLn.add(lblTitle);
        
        ////////////////////////////////
        /////////// Button /////////////
        ////////////////////////////////
        
        JPanel pnlBtnList = new JPanel();
        pnlBtnList.setBackground(new Color(255,255,255));
        
        GridBagLayout sGrid = new GridBagLayout();
        grid_conf = new GridBagConstraints();
        
        pnlBtnList.setLayout(sGrid);
        
        
        grid_conf.gridy = 0;
        for(int i = 0; i < 5; i++){
        	
        	ImageIcon icnDOW = new ImageIcon("img/index/dayOfWeek/" + i + ".png");
        	Image imgTmp = icnDOW.getImage();
        	imgTmp = imgTmp.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        	icnDOW = new ImageIcon(imgTmp);
        	
            grid_conf.gridx = i;
            btnDate[i].addActionListener(this);
            btnDate[i].setActionCommand(i+"");
            sGrid.setConstraints(btnDate[i], grid_conf);
            btnDate[i].setIcon(icnDOW);
            btnDate[i].setBorderPainted(false);
            pnlBtnList.add(btnDate[i]);
        }
        
        
        pnlBtnList.setPreferredSize(new Dimension(GUI.WINDOW_WIDTH/4, (int)(GUI.WINDOW_HEIGHT*(0.08))));
        grid_conf.gridx = 0;
        grid_conf.gridy = 2;
        tGrid.setConstraints(pnlBtnList, grid_conf);
        pnlLn.add(pnlBtnList);
        
        
        
        ////////////////////////////////
        /////////// Scroll /////////////
        ////////////////////////////////
        String[] input = {":!오늘 강의가 없습니다."};
        updateScrollList(input);
        scrollPan.repaint();
        
        
    }
    
    
    public void updateScrollList(String[] input){
    	
        scrollList = new ScrollList(input);
        scrollList.BuildScrollList();
        
        if(scrollPan != null){
        	pnlLn.remove(scrollPan);
        }
        
        
        System.out.println("--");
        scrollPan = new JScrollPane(scrollList.getScrollList());
        scrollPan.setBorder(BorderFactory.createEmptyBorder());
        
        
        
        grid_conf = new GridBagConstraints();
        scrollPan.setPreferredSize(new Dimension((int)(GUI.WINDOW_WIDTH/2.1), (int)(GUI.WINDOW_HEIGHT*(0.76))));
        
        grid_conf.gridx = 0;
        grid_conf.gridy = 3;
        tGrid.setConstraints(scrollPan, grid_conf);
        pnlLn.add(scrollPan);
        
    }
    
    
    public JPanel GetPnlLn()
    {
        return this.pnlLn;
    }
    
    
    public void packSize(int x, int y){
        pnlLn.setPreferredSize(new Dimension(x,y));
    }


    
    
    
    
    
    
    static int saveBtnNum = -1;
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String input = e.getActionCommand();
		int res = Integer.parseInt(input);
		if(saveBtnNum != res){
			buildDOWScrollList(res);
		}
		
	}
    
	
	public void buildDOWScrollList(int res){
		
		System.out.println("update");
		
		paintBtn(res);
		
		String writeScrollList = makeScrollData(res);
		System.out.println(writeScrollList);
		makeScrollList(writeScrollList);
		saveBtnNum = res;
		
	}
	
	
	
	public void paintBtn(int input){
		
		ImageIcon icnTmp;
		saveBtnNum = input;
		
		for(int i = 0; i < 5; i++){
			icnTmp = new ImageIcon("img/index/dayOfWeek/" + i + ".png");
        	Image imgTmp = icnTmp.getImage();
        	imgTmp = imgTmp.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        	icnTmp = new ImageIcon(imgTmp);
			btnDate[i].setIcon(icnTmp);
		}
		
		icnTmp = new ImageIcon("img/index/dayOfWeek/" + input + "s.png");
    	Image imgTmp = icnTmp.getImage();
    	imgTmp = imgTmp.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    	icnTmp = new ImageIcon(imgTmp);
		
		btnDate[input].setIcon(icnTmp);
		
	}
	
	
	
	public String makeScrollData(int dowSelect){
		
		int secNum = 0;
		boolean isEmptyLecNote = false;
		String writeScrollList = "";
		
		for(int j = 0; j < MainClass.lecList[dowSelect].size(); j++){
			
			LecData tmp = MainClass.lecList[dowSelect].getItem(j);
			
			System.out.println(tmp.lecName);
			
			//if(tmp.menuSec != 0 && tmp.articleNo != 0){
				
				if(secNum != tmp.forumSec){
					
					if(isEmptyLecNote){
						writeScrollList += ":!";
						writeScrollList += "<html>&nbsp;&nbsp;&nbsp;Empty<br>&nbsp;</html>";
						writeScrollList += ", ";
					}
					
					secNum = tmp.forumSec;
					writeScrollList += ":!";
					writeScrollList += "<html><font size = 4>&nbsp;</font><br>";
						writeScrollList += tmp.lecName;
					writeScrollList += "<br><font size = 4>&nbsp;</font></html>";
					writeScrollList += ", ";
					isEmptyLecNote = true;
					
				}
				else{
					isEmptyLecNote = false;
					
					//FileCheck
					//T- :@, F- :#
					if(ManageDir.find(tmp.forumSec, tmp.menuSec, tmp.articleNo))
						writeScrollList += ":@";
					else
						writeScrollList += ":#";
					
					
					writeScrollList += "<html>";
						writeScrollList += tmp.title;
						writeScrollList += "<br>";
						writeScrollList += "<h4>";
							writeScrollList += tmp.upToDate[0] + "." + tmp.upToDate[1] + "." + tmp.upToDate[2];
							writeScrollList += " / " + tmp.profName;
						writeScrollList += "</h4>";
						
						writeScrollList += "<!-->";
							writeScrollList += "##item#" + tmp.forumSec;
							writeScrollList += "##item#" + tmp.menuSec;
							writeScrollList += "##item#" + tmp.articleNo + "##item#";
						writeScrollList += "-->";
						//<div style = "display:none"></div>
						
					writeScrollList += "</html>";
					writeScrollList += ", ";
					
					//:#<html>profile1<br><h4>This is the algorithm class</h4></html>
				}
				
				System.out.println(isEmptyLecNote);
				
				
			//}
			
		}
		
		if(isEmptyLecNote){
			writeScrollList += ":!";
			writeScrollList += "<html>&nbsp;&nbsp;&nbsp;Empty<br>&nbsp;</html>";
			writeScrollList += ", ";
		}
		
		return writeScrollList;
		
	}
	
	
	public void makeScrollList(String writeScrollList){
		
		if(writeScrollList.length() != 0){
			String[] printScroll = writeScrollList.split(", ");
			updateScrollList(printScroll);
			GUI.repaint();
		}
		else{
			String[] printScroll = {":!금일 수업이 없습니다."};
			updateScrollList(printScroll);
			GUI.repaint();
		}
		
	}
	
	
	
	
	public static void checkNote(int index, int sec_num, int board_num, int article_num){
		
		loading.GUI.run();
		
		boolean isDownloaded = false;
		
		//if(file is exist)
		//  open file in local  boolean isDownloaded
		//else
			isDownloaded = SessionDownloader.download(MainClass.session.getSession(), sec_num, board_num, article_num);
		
		
		if(isDownloaded){
			sharedClass.buildDOWScrollList(saveBtnNum);
			GUI.repaint();
		}
		else{
			OpenFile.open(sec_num, board_num, article_num);
		}
		
		loading.GUI.terminate();
		
	}
	
	
	
//	
//	
//	for(int i = 0; i < 5; i++){
//		System.out.println(i + "요일 수업");
//		for(int j = 0; j < lecList[i].size(); j++){
//			
//		}
//	}
//	
	
	//Setting values
	//indexWindow.INF.SetNextCls("에듀에코세미나");
	//indexWindow.INF.SetUnivNotice("창업");
	//indexWindow.INF.SetAssignment("알고리즘,컴네");
	
	//String[] input = {":!알고리즘", ":#<html>profile1<br><h4>This is the algorithm class</h4></html>", ":! ", ":!컴퓨터네트워크.", ":@image", ":#f3",":! ", ":!알고리즘 ", ":@profile1",":@profile2",":#profile3", ":! "};
	//indexWindow.LN.updateScrollList(input);
	
	
	
	
	
}
