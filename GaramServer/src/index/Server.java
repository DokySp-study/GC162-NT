//Referenced by..
//http://boxfoxs.tistory.com/209
//This is Main class and function for server program.

package index;

import java.net.*;
import java.io.*;

public class Server {
	
	private static ServerSocket echo;
	
	public static void main (String args[]){
		
		try{
			
			//This field is main-stream function of Server class.			
			
			echo = new ServerSocket(9001);
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
