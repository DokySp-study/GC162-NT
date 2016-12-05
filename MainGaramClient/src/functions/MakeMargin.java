package functions;

import java.awt.Image;

import javax.swing.ImageIcon;

public class MakeMargin {
	
	public static ImageIcon build(int x,int y){
		
		ImageIcon icnMargin = new ImageIcon("img/index/margin.png");
		Image tmpImg = icnMargin.getImage();
		tmpImg = tmpImg.getScaledInstance(x, y, Image.SCALE_FAST);
		icnMargin = new ImageIcon(tmpImg);
		
		return icnMargin;
		
	}
	
}
