package functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//This is the code what can search local SQL file and Find file is exist or not
//Return 1: Found file in SQL DB.
//Return 0: Cannot found.
//Return -: Something problem in SQL

public class SQL {
	
	private String query = null;
	private String currDB = null;
	private java.sql.Statement st = null;
	private ResultSet rs = null;
	
	public SQL(String databaseName){
		currDB = databaseName;
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "hsvf7735!");
			st = con.createStatement();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
		}
		
	}
	
	
	
	public String[] getResult(String queryInput){
		
		query = queryInput;
		String result = "";
		
		try{
			st.execute("use " + currDB);
			rs = st.executeQuery(query);
			
			if(!rs.first())
				return null;
			
			do{
				result += rs.getString("forum_sec") + "/*.*/" + rs.getString("article_no") + "/*.*/" + rs.getString("file_name") + "/*.*/" + rs.getString("date") + "/*.*/" + rs.getString("TTL");
				result += "##inf##";
			}
			while(rs.next());
			
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			return null;
		}
		System.out.println(result);
		String[] out = result.split("##inf##");
		
		return out;
	}
	
	
	
	public boolean sendQuery(String query){
		
		try {
			
			st.execute("use " + currDB);
			st.executeUpdate(query);
			
			return true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	public int isFileAvailable(String queryInput){
		
		query = queryInput;
		
		try{
			
			st.execute("use " + currDB);

			rs = st.executeQuery(query);
			
			if(!rs.first())
				return 0;
			return 1;
			
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			return -1;
		}
		
	}
	
}
