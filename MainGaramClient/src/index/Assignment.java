package index;

import javax.swing.*;
import functions.MakeMargin;
import java.awt.*;



public class Assignment {
    
    Font fNanumBig = new Font("¸¼Àº °íµñ", Font.PLAIN, 30);
    Font fNanumMed = new Font("¸¼Àº °íµñ", Font.PLAIN, 23);
    Font fNanumSml = new Font("¸¼Àº °íµñ", Font.PLAIN, 18);
    
    public JPanel pnlLn;
    GridBagConstraints gbc;
    JLabel lblTitle;
    ScrollList scrollList;
    public JScrollPane scrollPan;
    
    GridBagLayout tGrid;
    GridBagConstraints grid_conf;
    
    
    //°úÁ¦ ¸®½ºÆ®
    public Assignment()
    {
        
        pnlLn = new JPanel();
        pnlLn.setBackground(new Color(255,255,255));
        
        tGrid = new GridBagLayout();
        grid_conf = new GridBagConstraints();
        pnlLn.setLayout(tGrid);
        
        JLabel lblMargin = new JLabel();
        lblMargin.setIcon(MakeMargin.build(1, 12));
        grid_conf.gridx = 0;
        grid_conf.gridy = 0;
        tGrid.setConstraints(lblMargin, grid_conf);
        pnlLn.add(lblMargin);

        
        
        /////////////////////////////////
        /////////// u_Label /////////////
        /////////////////////////////////
        
        lblTitle = new JLabel("Assignments");
        lblTitle.setFont(fNanumBig);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setPreferredSize(new Dimension(GUI.WINDOW_WIDTH/4, (int)(GUI.WINDOW_HEIGHT*(0.1))));
        
        grid_conf.gridx = 0;
        grid_conf.gridy = 1;
        tGrid.setConstraints(lblTitle, grid_conf);
        pnlLn.add(lblTitle);
        
        
        /////////////////////////////////
        /////////// u_Label /////////////
        /////////////////////////////////
        
        JLabel lblMargin2 = new JLabel();
        lblMargin2.setIcon(MakeMargin.build(1, 12));
        grid_conf.gridx = 0;
        grid_conf.gridy = 2;
        tGrid.setConstraints(lblMargin2, grid_conf);
        pnlLn.add(lblMargin2);
        
        
        
        ////////////////////////////////
        /////////// Scroll /////////////
        ////////////////////////////////
        
        String[] input = {":! asdf"};
        updateScrollList(input);
        scrollPan.repaint();
        
        
    }
    
    
    
    public void updateScrollList(String[] input){
    	
        scrollList = new ScrollList(input);
        scrollList.BuildScrollList();
        
        if(scrollPan != null){
        	pnlLn.remove(scrollPan);
        }
        
        
        System.out.println("--");
        scrollPan = new JScrollPane(scrollList.getScrollList());
        scrollPan.setBorder(BorderFactory.createEmptyBorder());
        
        
        
        grid_conf = new GridBagConstraints();
        scrollPan.setPreferredSize(new Dimension(GUI.WINDOW_WIDTH/4, (int)(GUI.WINDOW_HEIGHT*(0.82))));
        
        grid_conf.gridx = 0;
        grid_conf.gridy = 3;
        tGrid.setConstraints(scrollPan, grid_conf);
        pnlLn.add(scrollPan);
        
    }
    
    
    
    public JPanel GetPnlAssn(){
        return this.pnlLn;
    }
    
    
    
    public void packSize(int x, int y){
        pnlLn.setPreferredSize(new Dimension(x,y));
    }
    
}
