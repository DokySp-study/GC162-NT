package index;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.sun.scenario.Settings;

import datum.SetVals;
import index.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Infrom{ //1

	/* Infrom cls global variables */
	public JPanel pnlInf = null;
	JPanel pnlBg;
	JPanel pnlImg;
	JPanel pnlContain;
	JPanel pnlSubContain1;
	JPanel pnlSubContain2;
	
	
	GridBagConstraints grid_const = null;
	Insets iss = null;
	
	Font fNanumBig = new Font("", Font.BOLD, 30);
	Font fNanumMed = new Font("", Font.PLAIN, 17);
	Font fNanumSml = new Font("", Font.PLAIN, 15);
	
	public static Icon icnPic;
	
	ImageIcon icnSet, icnBg;
//	String strName = "";
//	String strMajor = "";
//	String strNextCls = "";
//	String strUnivNotice = "";
	private JLabel lblPic = new JLabel("");
	JLabel lblName;
	JLabel lblMajor;
	JLabel lblNextCls;
	JLabel lblUnivNotice;
	JLabel lblAssignment;
	JButton btnSet;
	
	GridBagLayout gridBg = new GridBagLayout();
	GridBagLayout gridImg = new GridBagLayout();
	GridBagLayout gridContain = new GridBagLayout();
	GridBagLayout gridInf = new GridBagLayout();
	GridBagLayout gridSubContain2 = new GridBagLayout();
	GridBagLayout gridSubContain1 = new GridBagLayout();
	
	
	/* InformSet cls global variables */
	static boolean boolSet; //Setting창이 켜져있나 여부, T:O    F:X
	static boolean isProfileEnable; //보임 버튼이 눌렸는지 여부, T:사진 안띄움, F:사진 띄움
	
//	public static JFrame frmSet;
//	InformSet InfSet;
//	JFrame frmInfset = null;
	
	
	public Infrom()
	{
		/* (InformSet)variable & setting */
//		InfSet = new InformSet();
//		frmInfset = InfSet.GetFrmInfSet();
		boolSet = false;
		isProfileEnable = SetVals.isProfileEnable;
				
		/* inform main pnl */
		pnlInf = new JPanel();
		pnlBg = new JPanel();
		pnlImg = new JPanel();
		pnlContain = new JPanel();
		
		pnlSubContain1 = new JPanel();
		pnlSubContain2 = new JPanel();
		
		
		lblNextCls = new JLabel();
		lblAssignment = new JLabel();
		lblUnivNotice = new JLabel();
		
		
		pnlInf.setLayout(gridInf);
		pnlBg.setLayout(gridBg);
		pnlImg.setLayout(gridImg);
		
		pnlContain.setLayout(gridContain);
		pnlSubContain1.setLayout(gridSubContain1);
		pnlSubContain2.setLayout(gridSubContain2);
		

		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 0;
		gridBg.setConstraints(pnlBg, grid_const);
		pnlInf.add(pnlBg);
		
		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 0;
		pnlBg.add(pnlContain);
		
		pnlContain.setBackground(new Color(0,0,0,0));
		pnlSubContain1.setBackground(new Color(0,0,0,0));
		pnlSubContain2.setBackground(new Color(0,0,0,100));
		
		
		icnBg = new ImageIcon("img/index/bg2.png");
		Image tmpImg = icnBg.getImage();
		tmpImg = tmpImg.getScaledInstance(GUI.WINDOW_WIDTH/4, GUI.WINDOW_HEIGHT-20, Image.SCALE_SMOOTH);
		icnBg = new ImageIcon(tmpImg);
		
		JLabel lblBg = new JLabel();
		lblBg.setIcon(icnBg);
		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 0;
		gridBg.setConstraints(lblBg, grid_const);
		pnlBg.add(lblBg);
		
		
		
		/* (Inform)variable & setting */
//		if(isProfileEnable)
//			SetIcnPic("");
//		else 
			SetIcnPic("profile.png");
		
		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 0;
		gridImg.setConstraints(lblPic, grid_const);
		pnlImg.add(lblPic);
		
		JLabel lblMargin1 = new JLabel();
		lblMargin1.setIcon(makeMarginIcn(10,360));
		
		grid_const.gridx = 0;
		grid_const.gridy = 1;
		gridImg.setConstraints(lblMargin1, grid_const);
		pnlImg.add(lblMargin1);
		
		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 0;
		gridBg.setConstraints(pnlImg, grid_const);
		pnlBg.add(pnlImg);
		
		
		
		
		
		
		//Upper Margin
		JLabel lblMarginS1 = new JLabel();
		lblMarginS1.setIcon(this.makeMarginIcn(GUI.WINDOW_WIDTH/4, (int)(GUI.WINDOW_HEIGHT*(0.29)) ));
		
		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 0;
		gridContain.setConstraints(lblMarginS1, grid_const);
		pnlContain.add(lblMarginS1);
		
		
		
		
		//lblName
		lblName = new JLabel("");
		lblName.setFont(fNanumBig);
		
		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 1;
		gridContain.setConstraints(lblName, grid_const);
		pnlContain.add(lblName);
		
		
		//lblMajor
		lblMajor = new JLabel("");
		lblMajor.setFont(fNanumSml);
		
		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 2;
		gridContain.setConstraints(lblMajor, grid_const);
		pnlContain.add(lblMajor);
		
		
		//Middle Margin
		JLabel lblMarginS2 = new JLabel();
		lblMarginS2.setIcon(this.makeMarginIcn(GUI.WINDOW_WIDTH/4, (int)(GUI.WINDOW_HEIGHT*(0.52)) ));
		
		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 3;
		gridContain.setConstraints(lblMarginS2, grid_const);
		pnlContain.add(lblMarginS2);
		
		gridContain.setConstraints(pnlSubContain1, grid_const);
		pnlContain.add(pnlSubContain1);
		updateNotification();
		
		
		
		//Below Margin
		JLabel lblMarginS3 = new JLabel();
		lblMarginS3.setIcon(this.makeMarginIcn(GUI.WINDOW_WIDTH/4, (int)(GUI.WINDOW_HEIGHT*(0.05)) ));
		
		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 4;
		gridContain.setConstraints(lblMarginS3, grid_const);
		pnlContain.add(lblMarginS3);
		gridContain.setConstraints(pnlSubContain2, grid_const);
		pnlContain.add(pnlSubContain2);
		
		
		
//		//btnSet
//		btnSet = new JButton(icnSet);
//		btnSet.setBorderPainted(false);
//		btnSet.setContentAreaFilled(false);
//		btnSet.setFocusPainted(false);
//		btnSet.setOpaque(false);
//		
//		grid_const.gridx = 0;
//		grid_const.gridy = 5;
//		
//		ActionListener actionHandler = new ActionEventHandler();
//		btnSet.addActionListener(actionHandler);
//		grid_const = new GridBagConstraints();
//		
//		pnlContain.add(btnSet, grid_const);

	}

	
	
	
	public void SetboolSet()
	{

		boolSet = !boolSet;

	}

	
	
	
	public void SetName(String newName)
	{
		
		int len = newName.length();
		
		for(int i = 0, c = 1; i < len; c+=2, i++)
			newName = newName.substring(0, c) + " " + newName.substring(c);  
		
		newName = newName.trim();
		this.lblName.setText(newName);

	}

	
	
	
	public void SetMajor(String newMajor)
	{
		this.lblMajor.setText(newMajor);
	}

	
	
	
	public void SetNextCls(String newNC){
		
		if(newNC.equals(""))
			this.lblNextCls.setText("");
		else{
			removeWelcomeMsg();
			this.lblNextCls.setText(
					"<html><div style=\"text-align:center\"><b> 오늘 수업은<br>" + newNC +
					"<br>입니다.<br>&nbsp;</b></div></html>");
		}
		
		if(isAllLblEmpty())
			this.setWelcomeMsg();
		
	}
	
	
	
	public void SetUnivNotice(String newUN){
		
		if(newUN.equals(""))
			this.lblUnivNotice.setText("");
		else{
			removeWelcomeMsg();
			this.lblUnivNotice.setText(
					"<html><div style=\"text-align:center\"><b>새로운 \"" + newUN +
					"\" 관련<br>학교 공지사항이 있습니다.<br>&nbsp;</b></div></html>");
		}
		
		if(isAllLblEmpty())
			this.setWelcomeMsg();
		
	}
	
	
	
	public void SetAssignment(String message){
		
		if(message.equals(""))
			this.lblAssignment.setText("");
		else{
			removeWelcomeMsg();
			this.lblAssignment.setText(
					"<html><div style=\"text-align:center\"><b>오늘 마감인 과제가 있습니다.<br>\"" + message +
					"\"<br>&nbsp;</b></div></html>");
		}
		
		if(isAllLblEmpty())
			this.setWelcomeMsg();
		
	}
	
	
	
	
	private boolean isAllLblEmpty(){
		if(lblNextCls.getText().equals("") && lblUnivNotice.getText().equals("") && lblAssignment.getText().equals(""))
			return true;
		return false;
	}
	
	
	private void setWelcomeMsg(){
		lblNextCls.setText("<html><br><b>환영합니다</b></html>");
	}
	
	private void removeWelcomeMsg(){
		if(lblNextCls.getText().equals("<html><br><b>환영합니다</b></html>"))
			lblNextCls.setText("");
	}
	
	
	
	
	public void updateNotification(){
		
		//lblNextCls
		pnlSubContain1.remove(lblNextCls);
		pnlSubContain1.remove(lblAssignment);
		pnlSubContain1.remove(lblUnivNotice);
		
		//lblNextCls
		lblNextCls.setHorizontalAlignment(JLabel.CENTER);
		lblNextCls.setText("");
		lblNextCls.setFont(fNanumMed);
		
		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 0;
		gridSubContain1.setConstraints(lblNextCls, grid_const);
		pnlSubContain1.add(lblNextCls);
		
		
		
		//lblAssignment
		lblAssignment.setHorizontalAlignment(JLabel.CENTER);
		lblAssignment.setText("");
		lblAssignment.setFont(fNanumMed);
		
		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 1;
		gridSubContain1.setConstraints(lblAssignment, grid_const);
		pnlSubContain1.add(lblAssignment);
		
		
		//lblUnivNotice
		lblUnivNotice.setHorizontalAlignment(JLabel.CENTER);
		lblUnivNotice.setText("");
		lblUnivNotice.setFont(fNanumMed);

		grid_const = new GridBagConstraints();
		grid_const.gridx = 0;
		grid_const.gridy = 2;
		gridSubContain1.setConstraints(lblUnivNotice, grid_const);
		pnlSubContain1.add(lblUnivNotice);
		
		
		lblName.setForeground(Color.WHITE);
		lblMajor.setForeground(Color.WHITE);
		lblNextCls.setForeground(Color.WHITE);
		lblUnivNotice.setForeground(Color.WHITE);
		lblAssignment.setForeground(Color.WHITE);
		
		
		
	}
	
	
	
	
	
	
	private ImageIcon makeMarginIcn(int x,int y){
		
		ImageIcon icnMargin = new ImageIcon("img/index/margin.png");
		Image tmpImg = icnMargin.getImage();
		tmpImg = tmpImg.getScaledInstance(x, y, Image.SCALE_FAST);
		icnMargin = new ImageIcon(tmpImg);
		
		return icnMargin;
		
	}
	
	
	
	

	public JPanel GetPnlInf()
	{
		return this.pnlInf;
	}
	
	
	
	
	public void SetIcnPic(String strFileName)
	{
		
		//System.out.println(">>SetIconPic. ["+strFileName+"]");
		ImageIcon img=new ImageIcon(strFileName);
		Image tmpImg = img.getImage();
		tmpImg = tmpImg.getScaledInstance(118, 118, java.awt.Image.SCALE_DEFAULT);
		img = new ImageIcon(tmpImg);
		
		img.getImage().flush();
		lblPic.setIcon(img);
		
	}
	
	
//	//Fader
//	class LblThread extends Thread {
//		
//		
//		public void run(){
//			
//			try {
//				for(int i = 0; i <= 255; i+=3, Thread.sleep(100)){
//					lblNextCls.setForeground(new Color(255,255,255,i));System.out.println(i);
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}
	
	
	class ActionEventHandler extends JFrame implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			SetboolSet();
			
//			if(boolSet)
//				frmInfset.setVisible(true);
//			else 
//				frmInfset.setVisible(false);

		}
		
	}

}







