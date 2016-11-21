package index;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import functions.SQL;

public class ServerHandler extends Thread {
	
	private Socket socket = null;
	
	public ServerHandler(Socket soc){
		socket = soc;
	}
	
	public void run(){
		
		FileOutputStream out = null;
		
		try{
			//This field is main-stream function of Server thread class.
			System.out.println("Thread is running: " + socket);
			
			SQL sample = new SQL("FMDB");  //FileList (sec_id_, file_name_, date, TTL)
			InputStream sIn = socket.getInputStream();
			OutputStream sOut = socket.getOutputStream();
			DataInputStream sInStream = new DataInputStream(sIn);
			DataOutputStream sOutStream = new DataOutputStream(sOut);
			
			System.out.println("Set");
			
			int sec_id = sInStream.readInt();
			String file_name = sInStream.readUTF();
			
			
			String result = sample.getResult(
					"select * from filelist where sec_id = " + sec_id +
					" and file_name = '" + file_name + "'");
			
			if(result == null){
				System.out.println("File Not Exists");
				sOutStream.writeInt(0);
				
				if(sInStream.readInt() == 0)
					System.out.println("File Transmitted Failed");
				else
					System.out.println("File Transmitted Client to Server");
				
				
				///////////////
				///////////////
				
				
				
//				int data = sInStream.readInt();
//				String filename = "received" + sInStream.readUTF(); //not exist readString -> can replace readUTF.
//				File file = new File(filename);
//				out = new FileOutputStream(file);
//				
//				int dataLen = data;
//				byte[] buffer = new byte[1024];
//				int len;
//				
//				for(;data > 0; data--){
//					len = sIn.read(buffer);
//					out.write(buffer, 0, len); //
//				}
//				
//				System.out.print(dataLen + "kb was transmitted: " + socket);
//				
//				out.flush();
//				out.close();
//				socket.close();
				
				///////////////
				///////////////
				
			}
			else{
				
				sOutStream.writeInt(1);
				
				System.out.println("File Transmitted Server to Client");
				
//				int data = sInStream.readInt();
//				String filename = "received" + sInStream.readUTF(); //not exist readString -> can replace readUTF.
//				File file = new File(filename);
//				out = new FileOutputStream(file);
//				
//				int dataLen = data;
//				byte[] buffer = new byte[1024];
//				int len;
//				
//				for(;data > 0; data--){
//					len = sIn.read(buffer);
//					out.write(buffer, 0, len); //
//				}
//				
//				System.out.print(dataLen + "kb was transmitted: " + socket);
//				
//				out.flush();
//				out.close();
//				socket.close();
				
			}
			
		}
		catch(IOException e){
			System.out.println(e);
		}
		finally{
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Thread is terminated: " + socket);

		}
		
	}
	
}
