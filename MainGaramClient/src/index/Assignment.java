package index;

import javax.swing.*;

import core.MainClass;
import datum.AssiData;
import datum.LecData;
import functions.MakeMargin;
import functions.ManageDir;

import java.awt.*;



public class Assignment {
    
    Font fNanumBig = new Font("¸¼Àº °íµñ", Font.PLAIN, 30);
    Font fNanumMed = new Font("¸¼Àº °íµñ", Font.PLAIN, 23);
    Font fNanumSml = new Font("¸¼Àº °íµñ", Font.PLAIN, 18);
    
    public JPanel pnlLn;
    GridBagConstraints gbc;
    JLabel lblTitle;
    ScrollList scrollList;
    public JScrollPane scrollPan;
    
    GridBagLayout tGrid;
    GridBagConstraints grid_conf;
    
    
    //°úÁ¦ ¸®½ºÆ®
    public Assignment()
    {
        
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
        
        lblTitle = new JLabel("Assignments");
        lblTitle.setFont(fNanumBig);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setPreferredSize(new Dimension(GUI.WINDOW_WIDTH/4, (int)(GUI.WINDOW_HEIGHT*(0.1))));
        
        grid_conf.gridx = 0;
        grid_conf.gridy = 1;
        tGrid.setConstraints(lblTitle, grid_conf);
        pnlLn.add(lblTitle);
        
        
        /////////////////////////////////
        /////////// u_Label /////////////
        /////////////////////////////////
        
        JLabel lblMargin2 = new JLabel();
        lblMargin2.setIcon(MakeMargin.build(1, 12));
        grid_conf.gridx = 0;
        grid_conf.gridy = 2;
        tGrid.setConstraints(lblMargin2, grid_conf);
        pnlLn.add(lblMargin2);
        
        
        
        ////////////////////////////////
        /////////// Scroll /////////////
        ////////////////////////////////
        
        String[] input = {":! ÁØºñÁßÀÔ´Ï´Ù."};
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
        scrollPan.setPreferredSize(new Dimension(GUI.WINDOW_WIDTH/4, (int)(GUI.WINDOW_HEIGHT*(0.82))));
        
        grid_conf.gridx = 0;
        grid_conf.gridy = 3;
        tGrid.setConstraints(scrollPan, grid_conf);
        pnlLn.add(scrollPan);
        
    }
    
    
    
public String makeScrollData(){
		
		String writeScrollList = "";
		
		for(int j = 0; j < MainClass.assiData.size(); j++){
			
			AssiData tmp = MainClass.assiData.get(j);
			
					
					//FileCheck
					//ok- :$+1    no- :$+0    done- :$-1
					writeScrollList += ":$";
					if(tmp.status >= 0)
						writeScrollList += "+" + tmp.status;
					else
						writeScrollList += tmp.status;
					
					
					writeScrollList += "<html>";
						writeScrollList += tmp.title;
						writeScrollList += "<br>";
						writeScrollList += "<h4>";
							writeScrollList += tmp.lecName;
						writeScrollList += "</h4>";
						
						writeScrollList += "<!-->";
							writeScrollList += "##item#" + tmp.forumSec;
							writeScrollList += "##item#" + tmp.menuSec;
							writeScrollList += "##item#" + tmp.articleNo + "##item#";
						writeScrollList += "-->";
						
					writeScrollList += "</html>";
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
			String[] printScroll = {":!°úÁ¦°¡ ¾ø½À´Ï´Ù."};
			updateScrollList(printScroll);
			GUI.repaint();
		}
		
	}
    
    
    
    
    public JPanel GetPnlAssn(){
        return this.pnlLn;
    }
    
    
    
    public void packSize(int x, int y){
        pnlLn.setPreferredSize(new Dimension(x,y));
    }
    
}
