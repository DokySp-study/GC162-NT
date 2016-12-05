package session;

public class MainClass {
	
	public static void main(String args[]){
		
		CyberGachonSession tmp1 = new CyberGachonSession();
		GachonSession tmp2 = new GachonSession();
		
		tmp1.logIn("##user_id", "##user_pw");
		System.out.println(tmp1.getXml());
		
		tmp2.logIn("##user_id", "##user_pw");
		System.out.println(tmp2.getXml());
		System.out.println(tmp2.getTargetXml("http://eclass.gachon.ac.kr/main"+"/myMain.jsp?Forum_seq=##Seq_num"));
		
	}
	
}
