package index;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame {

   private JPanel contentPane;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Window frame = new Window();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public Window() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 1049, 452);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(new GridLayout(1, 4, 0, 0));
      
      
      /* �л� ���� (Ÿ��Ʋ, ����, �̸�, �й�) */
      JPanel panel1 = new JPanel();
      contentPane.add(panel1);
      panel1.setLayout(new GridLayout(4, 1, 0, 0));
      
      JLabel lblNewLabel_Title = new JLabel("�л� ����");
      lblNewLabel_Title.setSize(new Dimension(78, 50));
      lblNewLabel_Title.setMinimumSize(new Dimension(78, 50));
      lblNewLabel_Title.setMaximumSize(new Dimension(78, 50));
      lblNewLabel_Title.setBorder(null);
      lblNewLabel_Title.setFont(new Font("�����ٸ���� UltraLight", Font.PLAIN, 25));
      lblNewLabel_Title.setHorizontalAlignment(SwingConstants.CENTER);
      panel1.add(lblNewLabel_Title);
      
      JLabel lblNewLabel_Img = new JLabel("Image");
      lblNewLabel_Img.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_Img.setFont(new Font("a���ǵ�", Font.PLAIN, 30));
      panel1.add(lblNewLabel_Img);
      
      JLabel lblNewLabel_Name = new JLabel("�̸�");
      lblNewLabel_Name.setFont(new Font("�����ٸ���� Light", Font.PLAIN, 15));
      lblNewLabel_Name.setHorizontalAlignment(SwingConstants.CENTER);
      panel1.add(lblNewLabel_Name);
      
      JLabel lblNewLabel_SID = new JLabel("�й�");
      lblNewLabel_SID.setVerticalAlignment(SwingConstants.TOP);
      lblNewLabel_SID.setFont(new Font("�����ٸ���� Light", Font.PLAIN, 15));
      lblNewLabel_SID.setHorizontalAlignment(SwingConstants.CENTER);
      panel1.add(lblNewLabel_SID);
      
      
      /* �����ڷ� �� �������� */
      JPanel panel2 = new JPanel();
      contentPane.add(panel2);
      panel2.setLayout(new GridLayout(3, 1, 0, 0));
      
      JLabel lblNewLabel_Title2 = new JLabel("�����ڷ� �� ��������");
      lblNewLabel_Title2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_Title2.setFont(new Font("�����ٸ���� UltraLight", Font.PLAIN, 25));
      panel2.add(lblNewLabel_Title2);
      
      JPanel panel_DayBtn = new JPanel();
      panel2.add(panel_DayBtn);
      panel_DayBtn.setLayout(new GridLayout(1, 5, 0, 0));
      
      JButton btnNewButton_M = new JButton("M");
      btnNewButton_M.setFont(new Font("�����ٸ���� Light", Font.PLAIN, 15));
      panel_DayBtn.add(btnNewButton_M);
      
      JButton btnNewButton_TU = new JButton("T");
      btnNewButton_TU.setFont(new Font("�����ٸ���� Light", Font.PLAIN, 15));
      panel_DayBtn.add(btnNewButton_TU);
      
      JButton btnNewButton_W = new JButton("W");
      btnNewButton_W.setFont(new Font("�����ٸ���� Light", Font.PLAIN, 15));
      panel_DayBtn.add(btnNewButton_W);
      
      JButton btnNewButton_TH = new JButton("T");
      btnNewButton_TH.setFont(new Font("�����ٸ���� Light", Font.PLAIN, 15));
      panel_DayBtn.add(btnNewButton_TH);
      
      JButton btnNewButton_F = new JButton("F");
      btnNewButton_F.setFont(new Font("�����ٸ���� Light", Font.PLAIN, 15));
      panel_DayBtn.add(btnNewButton_F);
      
      
      /* ���� */
      JPanel panel3 = new JPanel();
      contentPane.add(panel3);
      panel3.setLayout(new GridLayout(2, 1, 0, 0));
      
      JLabel lblNewLabel_Title3 = new JLabel("����");
      lblNewLabel_Title3.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_Title3.setFont(new Font("�����ٸ���� UltraLight", Font.PLAIN, 25));
      panel3.add(lblNewLabel_Title3);
      
      
      /* �б� �������� */
      JPanel panel4 = new JPanel();
      contentPane.add(panel4);
      panel4.setLayout(new GridLayout(3, 1, 0, 0));
      
      JLabel lblNewLabel_Title4 = new JLabel("�б� ��������");
      lblNewLabel_Title4.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_Title4.setFont(new Font("�����ٸ���� UltraLight", Font.PLAIN, 25));
      panel4.add(lblNewLabel_Title4);
      
      JPanel panel_Notice_Set = new JPanel();
      panel4.add(panel_Notice_Set);
      panel_Notice_Set.setLayout(new GridLayout(1, 2, 0, 0));
      
      JButton btnNewButton_Notice = new JButton("Notice");
      btnNewButton_Notice.setFont(new Font("�����ٸ���� Light", Font.PLAIN, 15));
      panel_Notice_Set.add(btnNewButton_Notice);
      
      JButton btnNewButton_Set = new JButton("Setting");
      btnNewButton_Set.setFont(new Font("�����ٸ���� Light", Font.PLAIN, 15));
      panel_Notice_Set.add(btnNewButton_Set);
   }

}