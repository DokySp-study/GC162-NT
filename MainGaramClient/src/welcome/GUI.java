package welcome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.sun.javafx.tk.Toolkit;

public class GUI {
	
	private static ImageIcon img;
	
	public static void main(String args[]) throws IOException, InterruptedException{
		JFrame index = new JFrame("Garam");
		img = new ImageIcon("1.gif");
		JLabel gifImg = new JLabel();
		JTextField ID = new JTextField(10);
		JTextField PW = new JTextField(10);
		
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints grid_c = new GridBagConstraints();
		
		//http://www.okjsp.pe.kr:8080/article/267721
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		
		
		
		
		JPanel AniPan = new JPanel();
		JPanel indexPan = new JPanel();
		JPanel IDPWPan = new JPanel();
		
		index.getContentPane().add(AniPan);
		index.getContentPane().add(indexPan);
		

		indexPan.add(IDPWPan);
		
		IDPWPan.setLayout(grid);
		
		grid_c.weightx = 0.2;
		grid_c.gridx = 0;
		grid_c.gridy = 0;
		JLabel IDLab = new JLabel("ID: ");
		grid.setConstraints(IDLab, grid_c);
		IDPWPan.add(IDLab);
		
		grid_c.weightx = 0.8;
		grid_c.gridx = 1;
		grid_c.gridy = 0;
		grid.setConstraints(ID, grid_c);
		IDPWPan.add(ID);
		
		grid_c.gridx = 0;
		grid_c.gridy = 1;
		JLabel PWLab = new JLabel("PW: ");
		grid.setConstraints(PWLab, grid_c);
		IDPWPan.add(PWLab);
		
		grid_c.gridx = 1;
		grid_c.gridy = 1;
		grid.setConstraints(PW, grid_c);
		IDPWPan.add(PW);
		
		grid_c.gridx = 0;
		grid_c.gridy = 2;
		grid_c.gridwidth = 2;
		JButton btnLogin = new JButton();
		btnLogin.setIcon(new ImageIcon("1.png"));
		btnLogin.setBorderPainted(false);
		grid.setConstraints(btnLogin, grid_c);
		IDPWPan.add(btnLogin);
		
		
		
		gifImg.setIcon(img);
		AniPan.add(gifImg, "Center");
		
		
		indexPan.setBackground(new Color(255,255,255));
		AniPan.setBackground(new Color(255,255,255));
		IDPWPan.setBackground(new Color(255,255,255));
		
		
		index.add(AniPan,"North");
		index.add(indexPan);
		IDPWPan.setVisible(false);
		
		
		index.setSize(img.getIconWidth(), img.getIconHeight() + 130);
		index.setLocation(width/2 - img.getIconWidth()/2, height/2-(img.getIconHeight() + 130)/2);
		index.setResizable(false);
		
		//index.getContentPane().setBackground(new Color(255,255,255));
		
		index.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		index.setVisible(true);
		
		Thread.sleep(1500);
		
		img = new ImageIcon("splash.png");
		gifImg.setIcon(img);
		IDPWPan.setVisible(true);
		
		
		
		System.out.println("asdf");
		
		
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
