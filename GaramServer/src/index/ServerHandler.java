package index;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

import functions.GetTime;
import functions.ManageDir;
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
			
			SQL database = new SQL("FMDB");  //FileList (sec_id_, file_name_, date, TTL)
			InputStream sIn = socket.getInputStream();
			OutputStream sOut = socket.getOutputStream();
			DataInputStream sInStream = new DataInputStream(sIn);
			DataOutputStream sOutStream = new DataOutputStream(sOut);
			
			System.out.println("Set");
			
			////////////////////////
			////Get sec_id value////
			////////////////////////
			int forum_sec = sInStream.readInt();
			
			/////////////////////////
			////Get arti_no value////
			/////////////////////////
			int article_no = sInStream.readInt();
			
			
			
			
			String[] result = database.getResult(
					"select * from filelist where "
					+ "forum_sec = " + forum_sec + " and "
					+ "article_no = " + article_no
					);
			
			
			if(result == null){
				System.out.println("File Not Exists");
				
				/////////////////////////////
				////Send file isn't exist////
				/////////////////////////////
				sOutStream.writeInt(0);
				
				/////////////////////////////////
				////Check client have a files////
				/////////////////////////////////
				if(sInStream.readInt() == 0){
					///////////////////////////////////////
					////Client don't have files neither////
					///////////////////////////////////////
					System.out.println("File Transmitted Failed");
				}
				else{
					//////////////////////////////////
					////Client send data to server////
					//////////////////////////////////
					int fileNum = sInStream.readInt();
					
					System.out.println(fileNum + " files Transmitted Client to Server");
					
					
					///////////////
					///////////////
					for(int i = 0; i < fileNum; i++){
						
						//////////
						//sOutStream.writeInt(1);
						String fileName = sInStream.readUTF();
						System.out.println("File name: " + fileName);
						//////////
						int date = sInStream.readInt();
						System.out.println("Date: " + date);
						
						String dir = "lecFile/" + forum_sec + "/" + article_no + "/" + fileName;
						System.out.println(dir);
						ManageDir.make(forum_sec, article_no);
						
						
						
						//////////
						int data = sInStream.readInt();
						System.out.println("Data: " + data);
						
						File file = new File(dir);
						out = new FileOutputStream(file);
						
						int dataLen = data;
						System.out.print(dataLen + "kb will be transmitted: " + socket);
						byte[] buffer = new byte[2];
						int len;
						int gauge;
						int total = 0;
						
						for(;data > 0; data--){
							
//							gauge = (int)((((double)dataLen - (double)data)  / (double)dataLen)*100);
//							if(gauge / 10 == 0)
//								System.out.println("Receiving data: " + gauge + "%");
							
							len = sInStream.read(buffer);
							out.write(buffer, 0, len);
							System.out.println(len);
							total += len;
							
						}
						
						System.out.println(total);
						
						out.flush();
						out.close();
						
						String query = "insert into filelist values "
								+ "(" + forum_sec + ", "
								+ article_no + ", "
								+ "'" + fileName + "', "
								+ date + ", "
								+ GetTime.dayAfter(7) + ");";
						
						System.out.println(query);
						database.sendQuery(query);
						
						
					}
					///////////////
					///////////////
					
				}
				
			}
			else{
				//////////////////////////////////
				////Server send data to Client////
				//////////////////////////////////
				sOutStream.writeInt(1);
				
				for(String tmp: result){
					System.out.println(tmp + " ----");
				}
				
				int fileNum = result.length;
				System.out.println(fileNum + " files Transmitted Server to Client");
				///////////
				sOutStream.writeInt(fileNum);
				
				for(int i = 0; i < fileNum; i++){
					
					String[] query = result[i].split("/[*].[*]/");
					
					for(String tmp: query){
						System.out.println(tmp + "/");
					}
					
					String dir = "lecFile/" + query[0] + "/" + query[1] + "/" + query[2];
					
					////////
					
					
					FileInputStream fin = new FileInputStream(new File(dir));
					
						byte[] buffer = new byte[2];
						int len;
						int data = 0;
						
						while((len = fin.read(buffer)) > 0)
							data++;
						
						int dataLen = data;
					
					fin.close();
					
					
					sOutStream.writeUTF(query[2]);
					
					sOutStream.writeUTF(query[3]);
					
					
					fin = new FileInputStream(new File(dir));
						
						////////
						sOutStream.writeInt(data);

						len = 0;
//						int gauge;
						
						for(; data > 0; data--){
//							gauge = (int)((((double)dataLen - (double)data)  / (double)dataLen)*100);
//							if(gauge / 10 == 0)
//								System.out.println("Receiving data: " + gauge + "%");
							len = fin.read(buffer);
							sOutStream.write(buffer, 0, len);
						}
						
						System.out.println(dataLen);
					
					fin.close();
					
					//TTL update
					
				}
				
			}
			
			
			socket.close();
			
			
		}
		catch(java.io.FileNotFoundException e){
			System.out.println(e);
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
