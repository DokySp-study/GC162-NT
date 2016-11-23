package index;

public class Client {
	public static void main(String args[]){
		
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