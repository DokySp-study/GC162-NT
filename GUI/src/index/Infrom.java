package index;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import index.Window;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//�ڷ� ��ó �����!

public class Infrom{ //1

	/* Infrom cls global variables */
	public static JPanel pnlInf = null;
	GridBagConstraints gbc = null;
	Insets iss = null;
	Font fNanumBig = new Font("���� ���", Font.PLAIN, 30);
	Font fNanumMed = new Font("���� ���", Font.PLAIN, 23);
	Font fNanumSml = new Font("���� ���", Font.PLAIN, 18);
	public static Icon icnPic;
	Icon icnSet, icnBg;
	String strName = "";
	String strMajor = "";
	String strNextCls = "";
	String strUnivNotice = "";
	public JLabel lblPic = new JLabel("");
	
	/* InformSet cls global variables */
	static boolean bInfSet; //InformSettingâ�� �����ֳ� ����, T:O    F:X
	static boolean bBtnPic; //���� ��ư�� ���ȴ��� ����, T:���� �ȶ��, F:���� ���
	
	public static JFrame frmSet;
	InformSet InfSet;
	JFrame frmInfset = null;
	
	
	public Infrom()
	{
		/* (InformSet)variable & setting */
		InfSet = new InformSet();
		frmInfset = InfSet.GetFrmInfSet();
		bInfSet = false;
		bBtnPic = true;
		
		/* (Inform)variable & setting */
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);
		
		icnBg = new ImageIcon("bgc.png");
		icnSet = new ImageIcon("gear.png");
		gbc = new GridBagConstraints();
		gbc.fill = gbc.BOTH;
		iss = new Insets (10, 10, 10, 10);
		gbc.insets = iss;
		gbc.weightx = 0.5;
		
		JLabel lblName;
		JLabel lblMajor;
		JLabel lblNextCls;
		JLabel lblUnivNotice;
		JButton btnSet;

		this.SetName("ȫ�浿");
		this.SetMajor("����Ʈ�����а�");
		this.SetNextCls("��ǻ�� ��Ʈ��ũ(���� ����)");
		this.SetUnivNotice("â��");

		/* inform main pnl */
		pnlInf = new JPanel();
		pnlInf.setLayout(new GridBagLayout());

		pnlInf.setBackground(new Color(135,206,235)); //rgb

		//lblPic
		if(bBtnPic)
			SetIcnPic("lion.png");
		else 
			SetIcnPic("NoImage.png");
		
		lblPic.setHorizontalAlignment(JLabel.CENTER);
		gbc.weighty = 0.4; gbc.weightx = 0.5;
		gbc.gridx = 0; gbc.gridy = 0;
		pnlInf.add(lblPic, gbc);
		lblPic.setOpaque(false);

		//lblName
		lblName = new JLabel(strName);
		lblName.setFont(fNanumBig);
		gbc.weighty = 0.1; gbc.weightx = 0.5;
		gbc.gridx = 0; gbc.gridy = 1;
		lblName.setHorizontalAlignment(JLabel.CENTER);
		pnlInf.add(lblName, gbc);
		lblName.setOpaque(false);

		//lblMajor
		lblMajor = new JLabel(strMajor);
		lblMajor.setFont(fNanumSml);
		gbc.weighty = 0.1; gbc.weightx = 0.5;
		gbc.gridx = 0; gbc.gridy = 2;
		lblMajor.setHorizontalAlignment(JLabel.CENTER);
		pnlInf.add(lblMajor, gbc);
		lblMajor.setOpaque(false);

		//lblNextCls
		lblNextCls = new JLabel();
		lblNextCls.setHorizontalAlignment(JLabel.CENTER);
		lblNextCls.setText(strNextCls);
		lblNextCls.setFont(fNanumMed);
		gbc.weighty = 0.2; gbc.weightx = 0.5;
		gbc.gridx = 0; gbc.gridy = 3;
		pnlInf.add(lblNextCls, gbc);
		lblNextCls.setOpaque(false);

		//lblUnivNotice
		lblUnivNotice = new JLabel();
		lblUnivNotice.setHorizontalAlignment(JLabel.CENTER);
		lblUnivNotice.setText(strUnivNotice);
		lblUnivNotice.setFont(fNanumMed);
		gbc.weighty = 0.2; gbc.weightx = 0.5;
		gbc.gridx = 0; gbc.gridy = 4;
		pnlInf.add(lblUnivNotice, gbc);
		lblUnivNotice.setOpaque(false);


		//btnSet
		btnSet = new JButton(icnSet);
		btnSet.setBorderPainted(false);
		btnSet.setContentAreaFilled(false);
		btnSet.setFocusPainted(false);
		btnSet.setOpaque(false);
		ActionListener actionHandler = new ActionEventHandler();
		btnSet.addActionListener(actionHandler);
		gbc.weighty = 0.05; gbc.weightx = 0.5;
		gbc.gridx = 0; gbc.gridy = 5;
		pnlInf.add(btnSet, gbc);

	}

	public void SetBInfSet()
	{

		bInfSet = !bInfSet;

	}

	public void SetName(String newName)
	{
		int nLen = newName.length(), i;
		char[] chTmp = new char[nLen+3];

		chTmp[0] = newName.charAt(0);

		for(i=1; i<=nLen+2; i++)
		{
			if(i % 2 == 0)
				chTmp[i] = newName.charAt(i/2);
			else
				chTmp[i] = ' ';
		}

		for(i=0; i<=nLen+2; i++)
			strName = strName + chTmp[i];

	}

	public void SetMajor(String newMajor)
	{
		this.strMajor = newMajor;
	}

	public void SetNextCls(String newNC)
	{
		this.strNextCls = 
				"<html><div style=\"text-align:center\"> ���� ������<br>\"" + newNC +
				"\"<br>�Դϴ�.</div></html>";
	}

	public void SetUnivNotice(String newUN)
	{
		this.strUnivNotice = 
				"<html><div style=\"text-align:center\">���ο� \"" + newUN +
				"\" ����<br>�б� ���������� �ֽ��ϴ�.</div></html>";
	}


	public JPanel GetPnlInf()
	{
		return this.pnlInf;
	}
	
	
	public void SetIcnPic(String strFileName)
	{
		
		System.out.println(">>SetIconPic. ["+strFileName+"]");
		ImageIcon img=new ImageIcon(strFileName);
		img.getImage().flush();
		lblPic.setIcon(img);
		
	}
	
	class ActionEventHandler extends JFrame implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			SetBInfSet();
			bBtnPic = bBtnPic;
			
			if(bInfSet)
				frmInfset.setVisible(true);
			else 
				frmInfset.setVisible(false);

		}
	}

	
	/* InformSet cls */
	class InformSet extends JFrame
	{
		JFrame frmSet = null;
		GridBagConstraints gbc;
		Font fNanumBig = new Font("���� ���", Font.PLAIN, 30);
		Font fNanumMed = new Font("���� ���", Font.PLAIN, 23);
		Font fNanumSml = new Font("���� ���", Font.PLAIN, 18);
		JButton btnPic;
		public JLabel test;

		public InformSet()
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

			/* lbl ���� */
			//JLabel lblTitle = new JLabel(new ImageIcon("gear_cho.png"));
			JLabel lblTitle = new JLabel("����");
			gbc.gridy = 0; gbc.weighty = 0.1;
			frmSet.add(lblTitle, gbc);
			lblTitle.setFont(fNanumMed);
			
			/* btn ���� */
			btnPic = new JButton();
			
			if(bBtnPic)
				btnPic.setText("����");
			else 
				btnPic.setText("�Ⱥ���");
			
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

				if(!bBtnPic){
					btnPic.setText("�Ⱥ���");
					SetIcnPic("NoImage.png");
					
				}
				else{ 
					btnPic.setText("����");
					SetIcnPic("lion.png");
				}
				
				bBtnPic = !bBtnPic;
			}
		}
	}

}





