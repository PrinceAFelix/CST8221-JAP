package othello;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;


/**
 * Othello<br>
 * The Othello class runs the game othello
 *  
 * 
 * @author Prince Adrianne Felix
 * @since 2021-07-19 CST221 Java Application Programming
 * @version 1.0
 */
public class Othello {
	
	public static void main(String[] args) {
		int duration = 590;
		if(args.length == 1){
	    	try{
	    	 duration = Integer.parseInt(args[0]);
	    	}catch (NumberFormatException mfe){
	    	  System.out.println("Wrong command line argument: must be an integer number");
	    	  System.out.println("The default duration 10000 milliseconds will be used");
	    	  //mfe.printStackTrace();	
	    	} 
	    }
		
		OthelloSplashScreen splashWindow = new OthelloSplashScreen(duration);

		splashWindow.showSplashWindow();
		
		EventQueue.invokeLater(new Runnable(){
		       @Override
		       public void run(){ 	
		        JFrame frame = new OthelloViewController();
		        frame.setTitle("Prince's Othello Client");
		        frame.setMinimumSize(new Dimension(1080, 650));
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setLocationRelativeTo(null);//screen center
		        frame.setLocationByPlatform(true);
		        frame.setVisible(true);  
		        frame.setResizable(false);
		       
		        
		       }
		});
	}
}