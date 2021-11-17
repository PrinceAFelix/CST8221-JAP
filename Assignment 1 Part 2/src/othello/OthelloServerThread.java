package othello;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/**
 * OthelloServerThread<br>
 * The OthelloNetworkController represents the thread
 *  
 * 
 * @author Prince Adrianne Felix
 * @since 2021-08-15 CST221 Java Application Programming
 * @version 1.0
 * 
 */
public class OthelloServerThread implements Runnable{
	private Socket incoming;
	OthelloViewController ovc = new OthelloViewController();
	public OthelloServerThread(Socket i)
	   { 
	      incoming = i; 
	   }

	   public void run()
	   {  
	      try
	      {  
	         try
	         {
	            InputStream inStream = incoming.getInputStream();
	            OutputStream outStream = incoming.getOutputStream();
	            
	            Scanner in = new Scanner(inStream);         
	            PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);

	            String line = ovc.chatField.getText();
	         }
	         finally
	         {
	            incoming.close();
	         }
	      }
	      catch (IOException e)
	      {  
	         e.printStackTrace();
	      }
	   }

	   
}
