package index;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;

import functions.SessionDownloader;
import functions.URLOpen;

//Referred by...
//http://woowowoong.blogspot.kr/2011/02/java-cellrenderer-demo.html
//http://stackoverflow.com/questions/4344682/double-click-event-on-jlist-element

public class ScrollList {
	
    private JList<?> list;
    private JComboBox<?> combo;
    private String[] data;
    private boolean isDone;
    
    public ScrollList(String[] inputData){
    	data = inputData;
    	isDone = false;
    }
    
    
    public void BuildScrollList(){
    	
    	isDone = false;
    	
    	//data = inputData;
    	System.out.println("st");
        list = new JList<Object>(data);
        
        list.setCellRenderer(new IconCellRenderer());
        
        list.addMouseListener(new MouseAdapter() {
        
            public void mouseClicked(MouseEvent evt) {
                JList<?> list = (JList<?>)evt.getSource();
                if (evt.getClickCount() == 2) {
                	//System.out.println(list.getSelectedIndex());
                	//System.out.println(list.getSelectedValue());
                	
                	//"##item#"
                	String tmp = (String)list.getSelectedValue();
                	String[] tmpSplit = tmp.split("##item#");
                	
                	//System.out.println(Integer.parseInt(tmpSplit[1]) + "/" + Integer.parseInt(tmpSplit[2]) + "/" + Integer.parseInt(tmpSplit[3]));
                	if(tmp.indexOf(":@") == 0 && tmp.indexOf(":#") == 0)
                		LectureNote.checkNote(list.getSelectedIndex(), Integer.parseInt(tmpSplit[1]), Integer.parseInt(tmpSplit[2]), Integer.parseInt(tmpSplit[3]) );
                	else if(tmp.indexOf(":!") == 0){
                		System.out.println(tmp);
                		URLOpen.open("http://eclass.gachon.ac.kr/main/myMain.jsp?Forum_seq="+Integer.parseInt(tmpSplit[1]));
                	}
                	else if(tmp.indexOf(":$") == 0){
                		URLOpen.open("http://eclass.gachon.ac.kr/board/view.jsp?Forum_seq="+Integer.parseInt(tmpSplit[1]) +"&Menu_seq=" + Integer.parseInt(tmpSplit[2]) + "&article_no=" + Integer.parseInt(tmpSplit[3]));
                	}
                	
                }
            }
            
        });
        
        System.out.println("en");
        isDone = true;
        
    }
    
    
    public JList<?> getScrollList(){
    	return list;
    }
    
    
    
}



class IconCellRenderer extends DefaultListCellRenderer{
	
	private static final long serialVersionUID = 1L;
	protected Hashtable<String, ImageIcon> icons;
    public IconCellRenderer(){
        icons = new Hashtable<String, ImageIcon>();
    }
    
    
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean CellHasFocus){
                
				String text = value.toString();
				
        		if(text.indexOf(":$") == 0)
        			setText(text.substring(4));
        		else
        			setText(text.substring(2));
        		
        		
        		
        		
                this.setFont(new Font("", Font.PLAIN, 20));
                
                
                if(!icons.containsKey(text)){
                	
                	ImageIcon img;
                	
                	if(text.indexOf(":@") == 0){
                		img = new ImageIcon("img/index/btnComplete.png");
                	}
                	else if(text.indexOf(":#") == 0){
                		img = new ImageIcon("img/index/btnDownload.png");
                	}
                	
                	else if(text.indexOf(":$") == 0){
                		if(Integer.parseInt(text.substring(2,4)) == 1)
                			img = new ImageIcon("img/index/btnOk.png");
                		else if(Integer.parseInt(text.substring(2,4)) == 0)
                			img = new ImageIcon("img/index/btnNo.png");
                		else
                			img = new ImageIcon("img/index/btnDn.png");
                	}
                	
                	else{ //":!"
                		img = new ImageIcon("empty");
                	}
                    
                    Image tmpImg = img.getImage();
                    tmpImg = tmpImg.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                    img = new ImageIcon(tmpImg);
                    icons.put(text, img);   
                    setIcon(img);
                    
                }else{
                    setIcon((ImageIcon)icons.get(text));
                }
                
                
                
                if(isSelected == true){  //isSelected == true && text.indexOf(":!"
                    setBackground(new Color(240,240,240));
                    setForeground(Color.black);
        	        
                }else{
                    setBackground(Color.white);
                    setForeground(Color.black);
                }
                
                return this;
                
            }
	
	
//	class Fader extends Thread {
//	    
//	    Component selected;
//	    
//	    public Fader(Component input){
//	        selected = input;
//	    }
//	    
//	    public void run(){
//
//            try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//            setBackground(Color.WHITE);
//            System.out.println("ther " + 2);
//	    	
//	    }
//	    
//	}
	
	
}




