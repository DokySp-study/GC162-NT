package index;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Assignment extends JFrame { //3
	
	public JPanel pnlAssn;
	public JLabel lblTitle;
	public JPanel pnlDays;
	public JPanel pnlListLec;
	GridBagConstraints gbc = new GridBagConstraints();
	ArrayList<JButton> btnListDays = new ArrayList<JButton>();
	
	
	public Assignment()
	{
		pnlAssn = new JPanel();
		
	}
	
	public JPanel GetPnlAssn()
	{
		return this.pnlAssn;
	}

}
