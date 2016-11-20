package index;


//This is Main class and function for server program.

public class Server {
	
	public static void main(String args[]){
		
		
		
		
		
	}
	
}




//Referenced by..
//http://boxfoxs.tistory.com/209
//
//package myPackage;
//
//import java.net.*;
//import java.io.*;
//
//public class TCPServer {
//	
//	private static ServerSocket echo;
//	
//	public static void main (String args[]){
//	
//		try{
//			echo = new ServerSocket(3456);
//			System.out.println("Echo server running");
//			
//			while(true)
//				new Handler(echo.accept()).start();
//			
//		}
//	    catch (java.net.BindException e){ // Server port is already opened.
//	    	System.out.println("Server is already running");
//	    }
//		catch (IOException e){ //IOException for ServerSocket class.
//	    	System.out.println(e);
//	    }
//	    finally {
//	    	try{ //If server is closed, close listening socket.
//	    		echo.close();
//	    	} catch(Exception e){ // Any other exceptions are occured.
//	    		System.out.println(e);
//	    	}
//	    }
//			
//	}
//	
//}
