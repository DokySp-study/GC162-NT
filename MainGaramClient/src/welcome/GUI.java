package welcome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.sun.javafx.tk.Toolkit;

import session.GachonSession;

public class GUI extends Thread implements ActionListener {
	
	private static ImageIcon img = new ImageIcon("img/welcome/splash.gif");
	private static JLabel gifImg = new JLabel();
	private static JButton btnLogin = new JButton();
	private static JFrame index = new JFrame("Garam");
	JPanel AniPan = new JPanel();
	JPanel indexPan = new JPanel();
	JPanel IDPWPan = new JPanel();
	JTextField txtID = new JTextField(10);
	JPasswordField txtPW = new JPasswordField(10);
	JLabel lblStatus = new JLabel("  ");
	private boolean isLogOn = false;

	GachonSession session = new GachonSession();
	
	
	public GUI() {

		GridBagLayout gridIDPWIndex = new GridBagLayout();
		GridBagLayout gridIDPW = new GridBagLayout();
		GridBagConstraints grid_c = new GridBagConstraints();
		
		//http://www.okjsp.pe.kr:8080/article/267721
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		

		index.getContentPane().add(AniPan);
		index.getContentPane().add(indexPan);
		
		//Testing
		txtID.setText("");
		txtPW.setText("");
		txtID.grabFocus();
		
		indexPan.setLayout(gridIDPWIndex);
		grid_c.gridx = 0;
		grid_c.gridy = 0;
		gridIDPWIndex.setConstraints(IDPWPan, grid_c);
		indexPan.add(IDPWPan);
		
		grid_c.gridx = 1;
		grid_c.gridy = 0;
		gridIDPWIndex.setConstraints(btnLogin, grid_c);
		indexPan.add(btnLogin);
		btnLogin.setIcon(new ImageIcon("img/welcome/btnLoginImg.png"));
		btnLogin.setBorderPainted(false);
		
		grid_c.gridx = 0;
		grid_c.gridy = 1;
		grid_c.gridwidth = 2;
		lblStatus.setForeground(Color.RED);
		gridIDPWIndex.setConstraints(lblStatus, grid_c);
		indexPan.add(lblStatus);
		grid_c.gridwidth = 1;
		
		
		IDPWPan.setLayout(gridIDPW);
		
		grid_c.weightx = 0.2;
		grid_c.gridx = 0;
		grid_c.gridy = 0;
		JLabel IDLab = new JLabel("ID: ");
		gridIDPW.setConstraints(IDLab, grid_c);
		IDPWPan.add(IDLab);
		
		grid_c.weightx = 0.8;
		grid_c.gridx = 1;
		grid_c.gridy = 0;
		gridIDPW.setConstraints(txtID, grid_c);
		IDPWPan.add(txtID);
		
		grid_c.gridx = 0;
		grid_c.gridy = 1;
		JLabel PWLab = new JLabel("PW: ");
		gridIDPW.setConstraints(PWLab, grid_c);
		IDPWPan.add(PWLab);
		
		grid_c.gridx = 1;
		grid_c.gridy = 1;
		gridIDPW.setConstraints(txtPW, grid_c);
		IDPWPan.add(txtPW);
		
		
		gifImg.setIcon(img);
		AniPan.add(gifImg, "Center");
		
		index.getContentPane().setBackground(new Color(255,255,255));
		indexPan.setBackground(new Color(255,255,255));
		AniPan.setBackground(new Color(255,255,255));
		IDPWPan.setBackground(new Color(255,255,255));
		
		
		index.add(AniPan,"North");
		index.add(indexPan);
		indexPan.setVisible(false);
		
		
		index.setSize(img.getIconWidth(), img.getIconHeight()+130); //70
		index.setLocation(width/2 - img.getIconWidth()/2, height/2-(img.getIconHeight() + 130)/2);
		index.setResizable(false);
		
		index.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		index.setVisible(true);
		
		btnLogin.addActionListener(this);
		txtID.addActionListener(this);
		txtPW.addActionListener(this);
		
		//Testing module
		//loginProcess();
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		loginProcess();
		
	}
	
	
	private void loginProcess(){
		
		int res = isIDPWvalid();
		LblThread lblStatusBlink = new LblThread();
		
		if(res == 0){
			
			lblStatus.setText("  ");
			txtID.setEnabled(false);
			txtPW.setEnabled(false);
			
			int status = session.logIn(txtID.getText(), txtPW.getText());
			
			//////////////////////
			//Fail to auto login//
			//////////////////////
			if(status != 0)
				index.setSize(img.getIconWidth(), img.getIconHeight() + 130);
			//////////////////////
			
			if(status == 0){
				logOn();
			}
			else if(status == -1){
				lblStatus.setText("Cannot login homepage!");
				lblStatusBlink.start();
				txtID.setEnabled(true);
				txtPW.setEnabled(true);

				
			}
			else{
				lblStatus.setText("Internet connection problem");
				lblStatusBlink.start();
				txtID.setEnabled(true);
				txtPW.setEnabled(true);
			}
			
		}
		else if(res == -1){
			lblStatus.setText("Enter ID");
			lblStatusBlink.start();
			txtID.grabFocus();
		}
		else if(res == -2){
			lblStatus.setText("Enter PW");
			lblStatusBlink.start();
			txtPW.grabFocus();
		}
		
	}
	
	
	private class LblThread extends Thread {
		
		public void run(){
			try {
				for(int i = 255; i > 60; i-=3, Thread.sleep(1))
					lblStatus.setForeground(new Color(255,0,0,i));
				for(int i = 60; i <= 255; i+=3, Thread.sleep(1))
					lblStatus.setForeground(new Color(255,0,0,i));
				for(int i = 255; i > 60; i-=3, Thread.sleep(1))
					lblStatus.setForeground(new Color(255,0,0,i));
				for(int i = 60; i <= 255; i+=3, Thread.sleep(1))
					lblStatus.setForeground(new Color(255,0,0,i));
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	public void run(){
		
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		img = new ImageIcon("img/welcome/splash.png");
		gifImg.setIcon(img);
		indexPan.setVisible(true);
		txtID.grabFocus();
		
	}
	
	
	private void logOn() {
		index.setVisible(false);
		isLogOn = true;
	}
	
	
	public boolean isLogOn(){
		return isLogOn;
	}
	
	
	public String getID(){
		return txtID.getText();
	}
	
	
	public String getPW(){
		return txtPW.getText();
	}
	
	
	public GachonSession getSession(){
		return session;
	}
	
	
	private int isIDPWvalid(){
		
		if(txtID.getText().equals(""))
			return -1;
		if(txtPW.getText().equals(""))
			return -2;
		return 0;
		
	}
	
}





//JTextField textField = new JTextField(25);
//JTextArea messageArea = new JTextArea(10, 30);
//JPanel upperPanel = new JPanel();
//JButton btnWhisperEnabler = new JButton("Whisper");
//JTextField txtTargetPerson = new JTextField(5);
//
//
//
////Set GUI layout.
//textField.setEditable(false);
//messageArea.setEditable(false);
//index.add(upperPanel, "North");
//
//
//upperPanel.add(textField, "West");
//upperPanel.add(btnWhisperEnabler, "East");
//index.add(txtTargetPerson, "South");
//index.getContentPane().add(new JScrollPane(messageArea), "Center");
//
//txtTargetPerson.setVisible(false);
//txtTargetPerson.setBackground(new Color(220,220,255));
//
//
//
//index.pack();


//grid_c.gridx = 0;
//grid_c.gridy = 2;
//grid_c.gridwidth = 2;
//btnLogin = new JButton();
//btnLogin.setIcon(new ImageIcon("1.png"));
//btnLogin.setBorderPainted(false);
//gridIDPW.setConstraints(btnLogin, grid_c);
//IDPWPan.add(btnLogin);

