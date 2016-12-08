package loading;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {
	
	static JFrame window = new JFrame("Garam - Loading...");;
	static JFrame targetWindow;
	public static JLabel lblGauge = new JLabel();
	static GridBagLayout a = new GridBagLayout();
	static JLabel lblBg = new JLabel();
	
	public GUI(JFrame inTargetWindow){
		
		targetWindow = inTargetWindow;
		
		window.getContentPane().setLayout(a);
		window.setUndecorated(true);
		
		lblGauge.setText("0%");
		
		GridBagConstraints grid_c = new GridBagConstraints();
		grid_c.gridx = 0;
		grid_c.gridy = 0;
		a.setConstraints(lblBg, grid_c);
		window.add(lblBg);
		grid_c.gridx = 0;
		grid_c.gridy = 1;
		//a.setConstraints(lblGauge, grid_c);
		
		window.add(lblGauge);
		
	}
	
	
	
	
	public static void run(){
		
		
		ImageIcon imgicn = new ImageIcon("img/splash/splash.gif");
		lblBg.setIcon(imgicn);
		
		System.out.println("Loading frame running...");
		
		window.setBackground(new Color(0,0,0,50));
		
		window.setSize(targetWindow.getWidth(), targetWindow.getHeight());
		
		window.setBackground(new Color(0,0,0,50));
		
		window.setVisible(true);
		
		ThdFollow handler = new ThdFollow(window, targetWindow);
		handler.start();
		window.repaint();
		
	}
	
	
	
	public static void terminate(){
		window.setVisible(false);
	}
	
	
	
	private static class ThdFollow extends Thread {
		
		JFrame control;
		JFrame target;
		
		public ThdFollow(JFrame in, JFrame inTarget){
			control = in;
			target = inTarget;
		}
		
		public void run(){
			
			while(true){
				control.setBounds(target.getX(), target.getY(), 1100, 600); 
			}
			
		}
		
	}
	
	
	
	
//	public static void main(String args[]){
//		
//		JFrame as = new JFrame();
//		as.setSize(1100, 600);
//		as.setVisible(true);
//		
//		
//		GUI a = new GUI(as);
//
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		a.terminate();
//
//		}
	
	
}






