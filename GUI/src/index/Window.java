package index;

import java.awt.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class Window extends JFrame {
	Image img = null;
	GridBagConstraints gbc;
	SimpleAttributeSet attribs;
	
	public static void main(String[] args)
	{
		Window wdw = new Window();
	}
	public Window()
	{
		gbc = new GridBagConstraints();
		attribs = new SimpleAttributeSet();
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		
		JFrame frm = new JFrame();
		JPanel pnl1 = new JPanel();
		JPanel pnl2 = new JPanel();
		JPanel pnl3 = new JPanel();
		JPanel pnl4 = new JPanel();
		Font fNanum = new Font("나눔바른고딕 UltraLight", Font.PLAIN, 25);
		
		String strName = "홍길동", strMajor = "소프트웨어 학과", 
				strNoticeCls1 = "다음 수업은 \n\"", strNoticeKey = "컴퓨터 네트워크(원어강의)", strNoticeCls2 = "\"입니다.",
				strNoticeList1 = "새로운 \"", strNoticeListKey = "창업", strNoticeList2 = "\"관련 \n학교 공지사항이 있습니다.",
				strNoticeConcat = strNoticeCls1 + strNoticeKey + strNoticeCls2, 
				strNoticeListConcat = strNoticeList1 + strNoticeListKey + strNoticeList2;
		
		
		
		frm.setBounds(80, 80, 1800, 900);
		frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frm.setLayout(new GridLayout(1, 4, 0, 0));
		//add panels to frame
		frm.add(pnl1); frm.add(pnl2); frm.add(pnl3); frm.add(pnl4);
		//Paint
		pnl1.setOpaque(true); pnl2.setOpaque(true); pnl3.setOpaque(true); pnl4.setOpaque(true);
		pnl1.setBackground(Color.GREEN); pnl2.setBackground(Color.WHITE);
		pnl3.setBackground(Color.GREEN); pnl4.setBackground(Color.WHITE);
		
		/* pnl1 */
		//pnl1 배경
		pnl1.setLayout(new GridBagLayout());
		ImageIcon bg = new ImageIcon("bgc.png");
		
		
		Icon iconImg = new ImageIcon("lion.png");
		JLabel lblImg = new JLabel(iconImg); //Picture
		lblImg.setFont(fNanum);
		gbc.weightx = 0.5; gbc.weighty = 0.2;
		gbc.gridx = 0; gbc.gridy = 0;
		pnl1.add(lblImg, gbc); 
		
		
		JLabel lblName = new JLabel(strName, SwingConstants.CENTER); //Name
		gbc.weightx = 0.5; gbc.weighty = 0.05;
		pnl1.add(lblName);
		lblName.setFont(fNanum);
		gbc.gridx = 0; gbc.gridy = 1;
		pnl1.add(lblName, gbc); 
		
		JLabel lblMajor = new JLabel(strMajor, SwingConstants.CENTER); //Major
		gbc.weightx = 0.5; gbc.weighty = 0.05;
		lblMajor.setFont(fNanum); 
		gbc.gridx = 0; gbc.gridy = 2;
		pnl1.add(lblMajor, gbc); 
		
		
		JTextPane jtpNoticeCls = new JTextPane(); //Next class is ~
		gbc.weightx = 0.5; gbc.weighty = 0.1;
		gbc.gridx = 0; gbc.gridy = 3;
		jtpNoticeCls.setText(strNoticeConcat);
		jtpNoticeCls.setFont(fNanum);
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		jtpNoticeCls.setParagraphAttributes(attribs, true);
		jtpNoticeCls.setEditable(false);
		jtpNoticeCls.setOpaque(false);
		pnl1.add(jtpNoticeCls, gbc);
		
		
		JTextPane jtpNoticeList = new JTextPane(); //New announcement is~
		gbc.weightx = 0.5; gbc.weighty = 0.1;
		gbc.gridx = 0; gbc.gridy = 4;
		jtpNoticeList.setText(strNoticeListConcat);
		jtpNoticeList.setFont(fNanum);
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		jtpNoticeList.setParagraphAttributes(attribs, true);
		jtpNoticeList.setEditable(false);
		pnl1.add(jtpNoticeList, gbc);
		jtpNoticeList.setOpaque(false);

		
		Icon iconSet = new ImageIcon("gear.png"); //setting btn
		JButton btnSetting = new JButton(iconSet);
		btnSetting.setBorderPainted(false);
		btnSetting.setContentAreaFilled(false);
		btnSetting.setFocusPainted(false);
		btnSetting.setOpaque(false);
		//버튼 왼쪽으로 정렬
		gbc.weightx = 0.5; gbc.weighty = 0.05;
		gbc.gridx = 0; gbc.gridy = 5;
		pnl1.add(btnSetting, gbc);
		
		
		frm.setVisible(true);
	}

}