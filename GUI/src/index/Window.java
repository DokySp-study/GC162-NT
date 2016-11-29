package index;

import javax.imageio.ImageIO;
import javax.swing.*;

//import index.InformSet.ActionEventHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Window extends JFrame{
	
	public JFrame frm = null;
	public JPanel pnlInf = null;
	public JPanel pnlLn = null;
	public JPanel pnlAssn = null;
	public JPanel pnlUniv = null;
	
	public Font fNanum = new Font("¸¼Àº°íµñ", Font.PLAIN, 15);
	//Font fNanum = new Font("³ª´®¹Ù¸¥°íµñ UltraLight", Font.PLAIN, 15);
	
	public Window()
	{
		/* frm Setting */
		//instance
		Infrom INF = new Infrom();
		LectureNote LN = new LectureNote();
		Assignment ASSN = new Assignment();
		UnivNotice UNIV = new UnivNotice();	
		
		pnlInf = INF.GetPnlInf();
		pnlLn = LN.GetPnlLn();
		pnlAssn = ASSN.GetPnlAssn();
		pnlUniv = UNIV.GetPnlUniv();
		
		frm = new JFrame("Garam");
		frm.setBounds(50, 100, 1800, 800);
		frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		frm.setLayout(new GridLayout(1, 4, 10, 10));
		
		frm.add(pnlInf);
		frm.add(pnlLn);
		frm.add(pnlAssn);
		frm.add(pnlUniv);
		
		/* pnl1 */
		
		
		
		/* pnl2 */
		
		
		/* pnl3 */
		
		
		/* pnl4 */
		
		
		frm.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Window mainFrm = new Window();
		
	}
	
	/*
	 * 	public JPanel pnlLn = null;
	public JPanel pnlAssn = null;
	public JPanel pnlUniv = null;
	 * 
	 */
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
}


