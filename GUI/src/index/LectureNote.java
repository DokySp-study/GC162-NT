package index;

import javax.swing.*;
import java.awt.*;

public class LectureNote extends JFrame { //2
	
	public JPanel pnlLn;
	
	public LectureNote()
	{
		pnlLn = new JPanel();
	}
	
	public JPanel GetPnlLn()
	{
		return this.pnlLn;
	}
}
