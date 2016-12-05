package index;

import javax.imageio.ImageIO;
import javax.swing.*;

//import index.InformSet.ActionEventHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI {
	
	public static JFrame frm = null;
	public JPanel pnlInf = null;
	public JPanel pnlLn = null;
	public JPanel pnlAssn = null;
	public JPanel pnlUniv = null;
	
	public static final int WINDOW_WIDTH = 1100;
	public static final int WINDOW_HEIGHT = 600;
	
	public Infrom INF;
	public LectureNote LN;
	public Assignment ASSN;
	public UnivNotice UNIV;
	
	
	public Font fNanum = new Font("", Font.PLAIN, 15);
	
	//Testing Module
	public static void main(String args[]){
		GUI indexWindow = new GUI();
		
		indexWindow.INF.SetName("김도균");
		indexWindow.INF.SetMajor("소프트웨어학과");
		//indexWindow.INF.SetNextCls("에듀에코세미나");
		indexWindow.INF.SetUnivNotice("창업");
		indexWindow.INF.SetAssignment("알고리즘,컴네");
		
		
	}
	
	
	public GUI()
	{
		/* frm Setting */
		//instance
		INF = new Infrom();
		LN = new LectureNote();
		ASSN = new Assignment();
		UNIV = new UnivNotice();	
		
		pnlInf = INF.GetPnlInf();
		pnlLn = LN.GetPnlLn();
		pnlAssn = ASSN.GetPnlAssn();
		pnlUniv = UNIV.GetPnlUniv();
		
		
		frm = new JFrame("Garam");
		frm.setBounds(170, 150, WINDOW_WIDTH, WINDOW_HEIGHT);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		frm.setLocation((width-WINDOW_WIDTH)/2, (height-WINDOW_HEIGHT)/2);
		
		//frm.setLayout(new GridLayout(1, 4, 0, 0));
		frm.setLayout(new BorderLayout());
		
		
//		grid_c.gridx = 0;
//		grid_c.gridy = 0;
//		grid.setConstraints(pnlInf, grid_c);
		frm.add(pnlInf, "West"); //panel info
		
//		grid_c.gridx = 1;
//		grid_c.gridy = 0;
//		grid.setConstraints(pnlInf, grid_c);
		frm.add(pnlLn, "Center");  //panel lecture note
		
//		grid_c.gridx = 2;
//		grid_c.gridy = 0;
//		grid.setConstraints(pnlInf, grid_c);
		frm.add(pnlAssn, "East");//panel assignment
		
//		grid_c.gridx = 3;
//		grid_c.gridy = 0;
//		grid.setConstraints(pnlInf, grid_c);
//		frm.add(pnlUniv);//panel univ information
		
		//frm.setResizable(false);
		frm.repaint();
		frm.setVisible(true);
	}
	
	/*
	 * 	public JPanel pnlLn = null;
	public JPanel pnlAssn = null;
	public JPanel pnlUniv = null;
	 * 
	 */
	
	public static void repaint(){
		int currWinX = frm.getX();
		int currWinY = frm.getY();
		frm.setBounds(currWinX, currWinY, GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT+1);
		frm.setBounds(currWinX, currWinY, GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);
	}
	
	public JFrame GetFrame()
	{
		return this.frm;
	}
	
	public JPanel GetPnlInf()
	{
		return this.pnlInf;
	}
	
	public JPanel GetPnlLn()
	{
		return this.pnlLn;
	}
	
	public JPanel GetPnlAssn()
	{
		return this.pnlAssn;
	}
	
	public JPanel GetpnlUniv()
	{
		return this.pnlAssn;
	}
	
	
	
	public LectureNote getLNObject(){
		return this.LN;
	}
	
	public Assignment getASSNObject(){
		return this.ASSN;
	}
	
	public UnivNotice getUNIVObject(){
		return this.UNIV;
	}
	
	public Infrom getINFObject(){
		return this.INF;
	}
	
	
	
}


