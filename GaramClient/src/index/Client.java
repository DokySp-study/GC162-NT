package index;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import functions.ManageDir;

public class Client {
	
	
	
	public static void getFromServer(int forum_no, int board_no, int arti_no){
		
		OutputStream sOut;
		FileInputStream fin;
		
		try{
			
			String hostName = "uhug.iptime.org";
			int port = 9001;
			int timeout = 5000;
			
			SocketAddress socketAddress = new InetSocketAddress(hostName, port);
			Socket soc = new Socket();
			
			soc.connect(socketAddress, timeout);
			
			System.out.println("Connected!");
			
			sOut = soc.getOutputStream();
			InputStream sIn = soc.getInputStream();
			DataOutputStream sOutStream = new DataOutputStream(sOut);
			DataInputStream sInStream = new DataInputStream(sIn);
			
			boolean available = true;
			
			Scanner scan = new Scanner(System.in);
			
			
			
				
			System.out.println("Preparing to Send");
				
			//while(true){
				sOutStream.writeInt(forum_no);
				sOutStream.writeInt(arti_no);
				
				System.out.println("Send query messages");
				
				if(sInStream.readInt() == 1){
					
					System.out.println("File exists!");
					System.out.println("File Transmitted Server to Client");
					
					//////////////////////////////////
					/////////////receiver/////////////
					//////////////////////////////////
					int fileNum = sInStream.readInt();
					
					System.out.println(fileNum + " files Transmitted Client to Server");
					
					
					///////////////
					///////////////
					
					for(int i = 0; i < fileNum; i++){
						
						//////////
						String fileName = sInStream.readUTF();
						//////////
						int date = sInStream.readInt();
						
						String dir = "lecFile/" + forum_no + "/" + arti_no + "/" + fileName;
						ManageDir.make(forum_no, board_no, arti_no);
						
						
						
						//////////
						int data = sInStream.readInt();
						
						File file = new File(dir);
						FileOutputStream out = new FileOutputStream(file);
						
						int dataLen = data;
						System.out.print(dataLen + "kb will be transmitted: " + soc);
						byte[] buffer = new byte[1024];
						int len;
						int gauge;
						
						for(;data > 0; data--){
							
							gauge = (int)((((double)dataLen - (double)data)  / (double)dataLen)*100);
							if(gauge / 10 == 0)
								System.out.println("Receiving data: " + gauge + "%");
							
							len = sIn.read(buffer);
							out.write(buffer, 0, len); 
							
						}	
					
						out.flush();
						out.close();
						
						///////////////
						///////////////
						
					}
					
					
				}
				else{
					System.out.println("File not exists!");
					
					available = scan.nextBoolean();
					//Download lecture note from home page
					
					if(available){
						//File not available -> Transmit file.
						sOutStream.writeInt(1);
						System.out.println("File Transmitted Client to Server");
						
							
						//////////////////////////////////
						//////////////sender//////////////
						//////////////////////////////////
						sOutStream.writeInt(1);
						
						
						//int fileNum = result.length;
						int fileNum = 0;
						System.out.println(fileNum + " files Transmitted Client to Server");
						///////////
						sOutStream.writeInt(fileNum);
						
						for(int i = 0; i < fileNum; i++){
							
							String dir = "lecFile/" + forum_no + "/" + board_no + "/" + arti_no;
							
							////////
							//--sOutStream.writeUTF(fileName);
							
							fin = new FileInputStream(new File(dir));
							
								byte[] buffer = new byte[1024];
								int len;
								int data = 0;
								
								while((len = fin.read(buffer)) > 0)
									data++;
								
								int dataLen = data;
							
							fin.close();
							
							
							
							
							fin = new FileInputStream(new File(dir));
								
								////////
								sOutStream.writeInt(data);
				
								len = 0;
								int gauge;
								
								for(; data > 0; data--){
									gauge = (int)((((double)dataLen - (double)data)  / (double)dataLen)*100);
									if(gauge / 10 == 0)
										System.out.println("Receiving data: " + gauge + "%");
									len = fin.read(buffer);
									sOutStream.write(buffer, 0, len);
								}
								
								System.out.println(dataLen);
							
							fin.close();
							
						}
						
						
						
						
						
						
						
						
						
						
						
						
						
					}
					else{
						//File not available -> close connections.
						sOutStream.writeInt(0);
						System.out.println("Nothing to transmit");
						
					}
					
				}
					
			
		}
		catch(IOException e){
			System.out.println(e);
		}
		
	}
	
}




/*
package myPackage;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class TCPClient {
	
	public static void main(String args[]){
		
		OutputStream out;
		FileInputStream fin;
		
		try{
			Socket soc = new Socket("localhost", 3456);
			System.out.println("Connected!");
			out = soc.getOutputStream();
			DataOutputStream dout = new DataOutputStream(out);
			
			Scanner scan = new Scanner(System.in);
			
			//while(true){
				String filename = scan.next();
				
				System.out.println("Preparing to Send");
				
				fin = new FileInputStream(new File(filename));
				
				byte[] buffer = new byte[1024];
				int len;
				int data = 0;
				
				while((len = fin.read(buffer)) > 0)
					data++;
				
				int dataLen = data;
				
				fin.close();
				fin = new FileInputStream(new File(filename));
				dout.writeInt(data);
				dout.writeUTF(filename);
				
				len = 0;
				
				for(; data > 0; data--){
					System.out.println("Sending data: " + (int)((((double)dataLen - (double)data)  / (double)dataLen)*100) + "%");
					len = fin.read(buffer);
					out.write(buffer, 0, len);
				}
				
				System.out.println(dataLen);
				
			//}
			
		}
		catch(IOException e){
			System.out.println(e);
		}
		
	}
	
}
*/