package othello;

import java.awt.*;
import javax.swing.*;

public class OthelloSplashScreen extends JWindow {
	private static final long serialVersionUID = 6248477390124803341L;
	private final int duration;
	
	 public OthelloSplashScreen(int duration) {
		    this.duration = duration;
	 }
	
	public void showSplashWindow() {
		JPanel  content  = new JPanel(new BorderLayout());
	    content.setBackground(Color.GRAY);
	    
	    int width = 534 + 10;
	    int height = 264 + 10;
	    
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    
	    int x = (screen.width-width)/2;
	    int y = (screen.height-height)/2;
	    
	    //set the location and the size of the window
	    setBounds(x,y,width,height);
	    
	    JLabel label = new JLabel(new ImageIcon(getClass().getResource("/images/SplashScreen.gif"))); 
	    
	    JLabel text = new JLabel("Prince's Othello Splash Screen", JLabel.CENTER);
	    text.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
	    
	    content.add(label, BorderLayout.CENTER);
	    content.add(text, BorderLayout.SOUTH);
	    
	    content.setBorder(BorderFactory.createLineBorder(new Color(44, 197, 211), 10));
	    
	    //replace the window content pane with the content JPanel
	    setContentPane(content);
	    
	    setVisible(true);
	    
	    // Snooze for awhile, pretending the code is loading something awesome while
	    // our splashscreen is entertaining the user.
	    try {
	    	
	    	 Thread.sleep(duration); }
	    catch (InterruptedException e) {e.printStackTrace();}
	    //destroy the window and release all resources
	    dispose(); 
	    //You can hide the splash window. The resources will not be released.
	    //setVisible(false);
	  }

}
