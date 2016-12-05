package functions;

import java.io.*; 
import java.net.*; 

/* Referred by...
 * http://sugame.tistory.com/622
 * 
 * Command line program to download data from URLs and save 
 * it to local files. Run like this: 
 * java FileDownload http://schmidt.devlib.org/java/file-download.html 
 * @author Marco Schmidt 
 */ 

//Usage examples... 
//download("http://cyber.gachon.ac.kr/pluginfile.php/00000/user/icon/coursemosv2/ab", "image.png")); 
//download("http://cyber.gachon.ac.kr/pluginfile.php/00000/user/icon/coursemosv2/ppt.pptx")); 


public class URLDownloader { 
	
    public static int download(String address, String localFileName) { 
        OutputStream out = null; 
        URLConnection conn = null; 
        InputStream  in = null; 
        try { 
            URL url = new URL(address); 
            out = new BufferedOutputStream( 
                new FileOutputStream(localFileName)); 
            conn = url.openConnection(); 
            in = conn.getInputStream(); 
            
            //파일이름 : localFileName
            //파일크기 : conn.getContentLengthLong()
            
            byte[] buffer = new byte[1500]; 
            int numRead; 
            while ((numRead = in.read(buffer)) != -1)
                out.write(buffer, 0, numRead);
            return 0;

        } catch (java.io.FileNotFoundException e){
        	return -1;
        } catch (Exception exception) { 
            exception.printStackTrace(); 
            return -1;
        } finally { 
            try { 
                if (in != null) { 
                    in.close(); 
                } 
                if (out != null) { 
                    out.close(); 
                }
            } catch (IOException ioe) {
            } 
        } 
    }
    
    
    public static int download(String address) { 
    	
    	int lastSlashIndex = address.lastIndexOf('/'); 
        
        if (lastSlashIndex >= 0 && 
            lastSlashIndex < address.length() - 1) { 
        	return download(address, address.substring(lastSlashIndex + 1));
        } else { 
            System.err.println("Could not figure out local file name for " + 
                address); 
            return -1;
        } 
        
    }
    
}
