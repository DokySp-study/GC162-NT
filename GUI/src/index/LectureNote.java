package index;

import javax.swing.*;
import java.awt.*;

public class LectureNote extends JFrame { //2
	
	Font fNanumBig = new Font("¸¼Àº °íµñ", Font.PLAIN, 30);
	Font fNanumMed = new Font("¸¼Àº °íµñ", Font.PLAIN, 23);
	Font fNanumSml = new Font("¸¼Àº °íµñ", Font.PLAIN, 18);
	public JPanel pnlLn;
	GridBagConstraints gbc;
	JLabel lblTitle;
	JPanel pnlDays;
	JButton btnM, btnTu, btnW, btnTh, btnF;
	//°­ÀÇ ¸®½ºÆ®
	
	public LectureNote()
	{
		/* panel setting */
		pnlLn = new JPanel();
		pnlLn.setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		Insets iss = new Insets(10, 10, 10, 10);
		gbc.insets = iss;
		gbc.fill = gbc.BOTH;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		
		/* component */
		/* lblTitle */
		gbc.gridy = 0;
		gbc.weighty = 0.1;
		lblTitle = new JLabel("<html><div style=\"text-align:center\"> °­ÀÇ</div></html>");
		lblTitle.setFont(fNanumBig);
		pnlLn.add(lblTitle, gbc);
		
		
		/* pnlDays */
		gbc.gridy = 1;
		gbc.weighty = 0.5;
		pnlDays = new JPanel();
		pnlDays.setLayout(new GridLayout(1, 5, 10, 10));
		pnlLn.add(pnlDays, gbc);
		//btnM, btnTu, btnW, btnTh, btnF
		btnM = new JButton("M");
		btnTu = new JButton("T");
		btnW = new JButton("W");
		btnTh = new JButton("T");
		btnF = new JButton("F");
		pnlDays.add(btnM); pnlDays.add(btnTu); pnlDays.add(btnW); pnlDays.add(btnTh); pnlDays.add(btnF);
		
		
	}
	
	public JPanel GetPnlLn()
	{
		return this.pnlLn;
	}
}
