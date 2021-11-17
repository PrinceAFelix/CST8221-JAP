package othello;

import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import java.net.Socket;



/**
 * OthelloNetworkController<br>
 * The OthelloNetworkController represents the client
 *  
 * 
 * @author Prince Adrianne Felix
 * @since 2021-08-15 CST221 Java Application Programming
 * @version 1.0
 * 
 */
public class OthelloNetworkController {

	OthelloNetworkModalViewController server = new OthelloNetworkModalViewController();
	
	final int DEFAULT_PORT = 61050;
	
	public OthelloNetworkController(int port, String host) {
		
		Socket s;
		
		try {
			
			s = new Socket(host, port);
			s.connect(new InetSocketAddress(InetAddress.getByName(host),port),10000);
			s.setSoTimeout(10000);
		
			try {
				
			}finally {
				s.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
			
	}
}
