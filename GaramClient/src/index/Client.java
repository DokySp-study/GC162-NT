package index;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	
	public static void main(String args[]){
		
		OutputStream sOut;
		FileInputStream fin;
		
		try{
			Socket soc = new Socket("localhost", 3456);
			System.out.println("Connected!");
			sOut = soc.getOutputStream();
			InputStream sIn = soc.getInputStream();
			DataOutputStream sOutStream = new DataOutputStream(sOut);
			DataInputStream sInStream = new DataInputStream(sIn);
			
			boolean available = true;
			
			Scanner scan = new Scanner(System.in);
			
			available = scan.nextBoolean();
			int sec_id = scan.nextInt();
			String file_name = scan.next();
			
			
			//while(true){s
				//String filename = scan.next();
				
				System.out.println("Preparing to Send");
				
				//while(true){
					sOutStream.writeInt(sec_id);
					sOutStream.writeUTF(file_name);
					
					System.out.println("Send 2 message");
					
					if(sInStream.readInt() == 1){
						System.out.println("File exists!");
						System.out.println("File Transmitted Server to Client");
					}
					else{
						System.out.println("File not exists!");
						//Download lecture note from home page
						if(available){
							//File not available -> Transmit file.
							sOutStream.writeInt(1);
							System.out.println("File Transmitted Client to Server");
						}
						else{
							//File not available -> close connections.
							sOutStream.writeInt(0);
							System.out.println("Nothing to transmit");
							
						}
					}
					
				//}
				
				
				
//				fin = new FileInputStream(new File(filename));
//				
//				byte[] buffer = new byte[1024];
//				int len;
//				int data = 0;
//				
//				while((len = fin.read(buffer)) > 0)
//					data++;
//				
//				int dataLen = data;
//				
//				fin.close();
//				fin = new FileInputStream(new File(filename));
//				sOutStream.writeInt(data);
//				sOutStream.writeUTF(filename);
//				
//				len = 0;
//				
//				for(; data > 0; data--){
//					System.out.println("Sending data: " + (int)((((double)dataLen - (double)data)  / (double)dataLen)*100) + "%");
//					len = fin.read(buffer);
//					sOutStream.write(buffer, 0, len);
//				}
//				
//				System.out.println(dataLen);
				
			//}
			
		}
		catch(IOException e){
			System.out.println(e);
		}
		
	}
	
}
