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
	
	private static String query = null;
	
	public SQL(String queryInput){
		query = queryInput;
	}
	
	public static int isFileAvailable(){
		
		Connection con = null;
		
		try{
			
			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "hsvf7735!");
			
			java.sql.Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
//			rs = st.executeQuery("use fmdb"); //null;
//			
////			if(st.execute("show databases"))
////				rs = st.getResultSet();
//			
//			rs = st.executeQuery("show tables");
			rs = st.executeQuery(query);
			
			while(rs.next()){
				String str = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);
				System.out.println(str);
			}
			
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
		}
		
		
		return 0;
	}
	
}
