//Referenced by..
//http://boxfoxs.tistory.com/209
//This is Main class and function for server program.

package index;

import java.net.*;
import java.io.*;
import functions.SQL;

public class Server {
	
	private static ServerSocket echo;
	
	public static void main (String args[]){
		
		SQL sample = new SQL("mydb");
		
		System.out.println(sample.isFileAvailable("select course_id from course where course_id = 'EE'"));
		System.out.println(sample.isFileAvailable("select course_id from course where course_id like 'CS%'"));
		System.out.println(sample.getResult("select course_id from course"));
		
		try{
			echo = new ServerSocket(3456);
			System.out.println("Echo server running");
			
			while(true)
				new ServerHandler(echo.accept()).start();
			
		}
	    catch (java.net.BindException e){ // Server port is already opened.
	    	System.out.println("Server is already running");
	    }
		catch (IOException e){ //IOException for ServerSocket class.
	    	System.out.println(e);
	    }
	    finally {
	    	try{ //If server is closed, close listening socket.
	    		echo.close();
	    	} catch(Exception e){ // Any other exceptions are occured.
	    		System.out.println(e);
	    	}
	    }
			
	}
	
}
