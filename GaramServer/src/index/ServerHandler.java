package index;

public class ServerHandler {
	
	public static void main(String args[]){
		
		
	}
	
}

//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.*;
//
//public class Handler extends Thread {
//	
//	private Socket socket = null;
//	
//	public Handler(Socket soc){
//		socket = soc;
//	}
//	
//	public void run(){
//		
//		FileOutputStream out = null;
//		
//		try{
//			System.out.println("Thread is running: " + socket);
//			InputStream in = socket.getInputStream();
//			DataInputStream din = new DataInputStream(in);
//			
//			//while(true){
//				
//				int data = din.readInt();
//				String filename = "received" + din.readUTF();
//				File file = new File(filename);
//				out = new FileOutputStream(file);
//				
//				int dataLen = data;
//				byte[] buffer = new byte[1024];
//				int len;
//				
//				for(;data > 0; data--){
//					len = in.read(buffer);
//					out.write(buffer, 0, len); //시작점 , 읽을 크기  
//				}
//				
//				System.out.print("약 " + dataLen + "kb 전송됨: " + socket);
//				
//				out.flush();
//				out.close();
//				socket.close();
//				
//			//}
//			
//		}
//		catch(IOException e){
//			System.out.println(e);
//		}
//		finally{
//			try {
//				out.flush();
//				out.close();
//				socket.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
//		
//	}
//	
//}