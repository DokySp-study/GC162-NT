package index;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/* InformSet cls */
public class SettingPopup extends JFrame {
	private static final long serialVersionUID = 1L;
	JFrame frmSet = null;
	GridBagConstraints gbc;
	Font fNanumBig = new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 30);
	Font fNanumMed = new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 23);
	Font fNanumSml = new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 18);
	JButton btnPic;
	public JLabel test;

	public SettingPopup()
	{

		
		/* Variables setting */
		Insets iss = new Insets(10, 10, 10, 10);
		gbc = new GridBagConstraints();
		gbc.insets = iss;
		gbc.gridx = 0;
		gbc.weightx = 0.5; 
		ActionListener actionHandler = new ActionEventHandler();

		/* frmSet setting */
		frmSet = new JFrame("Setting");
		frmSet.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frmSet.getContentPane().setBackground(new Color(240,248,255));
		frmSet.setUndecorated(true);
		frmSet.setBounds(80, 430, 150, 450);
		frmSet.setLayout(new GridBagLayout());

		/* lbl º≥¡§ */
		//JLabel lblTitle = new JLabel(new ImageIcon("gear_cho.png"));
		JLabel lblTitle = new JLabel("º≥¡§");
		gbc.gridy = 0; gbc.weighty = 0.1;
		frmSet.add(lblTitle, gbc);
		lblTitle.setFont(fNanumMed);
		
		/* btn ∫∏¿” */
		btnPic = new JButton();
		
//		if(isProfileEnable)
//			btnPic.setText("∫∏¿”");
//		else 
//			btnPic.setText("æ»∫∏¿”");
		
		/*btnPic.setBorder(null);
		btnPic.setBorderPainted(false);
		btnPic.setContentAreaFilled(false);
		btnPic.setFocusPainted(false);
		btnPic.setOpaque(false);*/
		gbc.gridy = 1; gbc.weighty = 0.2;
		frmSet.add(btnPic, gbc);
		btnPic.setFont(fNanumSml);
		btnPic.addActionListener(actionHandler);
		
		/* lbl test */
		test = new JLabel();
		gbc.gridy = 2; gbc.weighty = 0.3;
		frmSet.add(test, gbc);
	}
	
	public JFrame GetFrmInfSet()
	{
		return this.frmSet;
	}
	
	
	class ActionEventHandler implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{

//			if(!isProfileEnable){
//				btnPic.setText("æ»∫∏¿”");
//				SetIcnPic("img/index/NoImage.png");
//				
//			}
//			else{ 
//				btnPic.setText("∫∏¿”");
//				SetIcnPic("img/index/lion.png");
//			}
//			
//			isProfileEnable = !isProfileEnable;
		}
	}
}