package othello;

//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.border.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

    /**
    * This class creates and manages a network connection UI.  Add yourself an extra @author line.
    * @author Daniel Cormier
    * @version 1.3.1
    * @since 1.8.0_291
    * @see OthelloViewController
    */

public class OthelloNetworkModalViewController extends JDialog
{
    /** Has the user pressed the connect button? */
    Boolean hasConnected=false;
    
    /** Instance of the inner class Controller for handling UI events */
    Controller handler = new Controller();
    
    /** Instance of the ComboBox you need to build. You'll want to change this comment. */
    //NOTE: In the case of this and other UI elements, you may rename them as you see fit.
    //Just be careful that you catch every instance of it if you do.
    JComboBox<String> portInput;
    
    /** Textfield for entering the FQDN/IP address */
    JTextField addressInput;
    
    /** Textfield for entering the user's name. */
    JTextField nameInput;

    /** Label for reporting error messages pertaining to user names. */
    JLabel nameError;
    
    /** Label for reporting error messages pertaining to addresses. */
    JLabel addressError;
    
    /** Label for reporting error messages pertaining to port numbers */
    JLabel portError;

    //NOTE:  We're not doing much with those labels for now.  But we will be for part 2.

    //This will hold your UI.  Of course you may rename it.
    Container networkPanel = getContentPane();
    
    GridBagConstraints c = new GridBagConstraints();
    
    OthelloNetworkModalViewController(){}

    public OthelloNetworkModalViewController (JFrame mainView)
    {
    	
    	
        //The superclass needs to know what JFrame it's intercepting.
        //The title doesn't matter, nobody will see it.
        super(mainView,"Enter Network Address",true);
        
        //Uncomment the below line ONLY when you're just about done.
        //It will be much easier to manage your dialog when you have the bar at the top.
        setUndecorated(true);
        
        //Seriously, uncomment the above only when you're just about done.
        //It is a monumental hassle otherwise.
        
      
       
       // c.insets = new Insets(3,3,3,3);


        networkPanel = new JPanel();
      
        networkPanel.setLayout(new GridBagLayout());
        ((JComponent) networkPanel).setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));
        
        //Your UI code goes here.  I suggest using a GridBagLayout, but you can use
        //whatever you like, so long as it doesn't take pixels for input.

        //Labels
        JLabel addressL = new JLabel("Address:");
        JLabel portL = new JLabel("Port:");
        JLabel nameL = new JLabel("Name:");
        
      
        //Address Field
        addressInput = new JTextField();
        
        //Port Fields
        String[] portNumbers = {"", "32300", "42300", "52300"};
        portInput = new JComboBox<>(portNumbers);
        portInput.setEditable(true);
        portInput.setSelectedIndex(0);
        
        //Create Name Field
        nameInput = new JTextField();
        
        //Create Buttons
        JButton connect = new JButton("Connect");
        JButton cancel = new JButton("Cancel");
        
        //Set the action command, to be use in action listener
        connect.setActionCommand("C");
        cancel.setActionCommand("X");
        
        //Add two buttons to action listener
        connect.addActionListener(handler);
        cancel.addActionListener(handler);
        
        
        //I had to work around this. It's messy but I got the exact UI
        
       
	   
        addressError = new JLabel();
        addressError.setForeground(Color.RED);
        portError = new JLabel();
        portError.setForeground(Color.RED);
        nameError = new JLabel();
        nameError.setForeground(Color.RED);
        
        
        
        
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridwidth = 2;
    	c.ipadx = 1;
    	c.gridx = 1;
 	    c.gridy = 3;
 	    networkPanel.add(addressError, c);
 	    
 	    c.gridx = 1;
 	    c.gridy = 5;
 	    networkPanel.add(portError, c);
   	
   		c.gridx = 1;
	    c.gridy = 7;
	    networkPanel.add(nameError,c);
	    
	    
	    c.gridwidth = 1;
        c.fill = GridBagConstraints.RELATIVE;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 8;
        networkPanel.add(connect, c);
        
        c.gridx = 2;
        c.gridy = 8;
        networkPanel.add(cancel, c);
        
        c.weighty = 0;
        
        
        c.gridx = 0;
        c.gridy = 0;
        networkPanel.add(addressL, c);
        
        c.gridx = 0;
        c.gridy = 6;
        networkPanel.add(nameL,c);
        
        
        c.gridx = 0;
        c.gridy = 4;
        networkPanel.add(portL, c);
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
      
        
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        networkPanel.add(addressInput, c);
        
        
        //Resets gridwidth
        c.gridwidth = 1;
        
        
        c.gridx = 1;
        c.gridy = 4;
      
        networkPanel.add(portInput, c);
        
      
        c.fill = GridBagConstraints.HORIZONTAL;
       
        
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 6;
        networkPanel.add(nameInput,c);
       

       
  
      
        add(networkPanel);
        //And when you're done, of course you'll pack it.
        pack();
  
    }


	/** 
    * A basic get method to return address input.
    * @return The FQDN/IP address from the user.
    */
    public String getAddress()
    {
        if (hasConnected)
        {
            return (addressInput.getText());
        }
        else
        {
            //You're free to return whatever error message you like.
            //The code may never wind up using this, but it gives you a fallback.
            return ("Error:  Invalid Address or attempt cancelled.");
        }
    }
    
    /** 
    * A basic get method to return name input.
    * @return The user's name.
    */
    public String getName()
    {
        return (nameInput.getText());
    }

    /** 
    * A basic get method to return the selected port.
    * @return The selected port, or custom input.
    */
    
    public int getPort()
    {
        //I stored the ports as Strings in the ComboBox.  You may need to convert them
        //safely to integers.
        
        //Important note: I am NOT returning safe data in this method.
        //It would be a good idea to do it here.
        
        int portnum=-1;
        if (hasConnected)
        {
            try
            {
                portnum = Integer.parseInt((String)portInput.getSelectedItem());
            }
            catch(NumberFormatException nfe)
            {
                portnum = -1;
            }

            return portnum;
        }
        //In my case, if the port number is invalid for ANY reason, I return -1.
        return -1;
    }
    
    /**
    * This method hides the modal when the input process is complete.
    */
    
    public void hideModal()
    {
        //Add any code that you may want to do after the user input has been processed
        //and you're figuratively closing up the shop.
        //Once this method is done, control will return to the code that set this dialog
        //to be visible.
        
        //As a suggestion, you may want to reset error messages
        //and potentially clear out the input for part 2, especially if cancel was pressed.
        //It's up to you, of course.
    	
    	
        
        setVisible(false);
        
    }
    
    /**
    * For use in the main UI:  Has the user pressed the connect or cancel button?
    * @return True if connect, false if cancel.
    */
    
    public boolean pressedConnect()
    {
        return hasConnected;
    }
    
    
 
    
    /**
    * The Controller that manages all UI events in the dialog.
    * @author Daniel Cormier
    * @version 1.3.1
    * @since 1.8.0_291
    * @see OthelloViewController
    */

    private class Controller implements ActionListener
    {
    	
    	
    	
        @Override
        public void actionPerformed(ActionEvent ae)
        {
        	
            String s = ae.getActionCommand();
           
            
            //User selects "Connect":  I set "C" to be my Connect button's "action command".
            if ("C".equals(s)) //Connect option.
            {
                
                hasConnected=true;
                
            	 int count = 0;
                 
            	 boolean isInteger = true;
            	 
            	 //Check if port is NaN
            	 try {
            		 Integer.parseInt((String) portInput.getSelectedItem());
            	 }catch(NumberFormatException nfe) {
            		 isInteger = false;
            	 }
            	 
            	 //Count how many characters are in the name
                 for(int i = 0; i < getName().length(); i++) {
                	 if(getName().charAt(i) != ' ')    
                         count++;     
                 }
                 
         	    
                //Will work more in Part 2
                if(getAddress().equals("")){
                	addressError.setText("The address must not be blank");
                	addressError.setVisible(true);
                }
                if(getPort() == -1) {
                	portError.setVisible(true);
                }
                if(!isInteger) {
                	portError.setText("The port must be an integer");
                	portError.setVisible(true);
                }else if(getPort() < 0 || getPort() > 65535) {
                	portError.setText("The port must be between 0 and 65535");
                	portError.setVisible(true);
                }
                if(count < 3 || count <= 0) {
                	nameError.setText("Name too short");
                	nameError.setVisible(true);
                }
                
                if(getAddress() != null && getPort() != -1 && (count > 3 && count >= 0) && isInteger == true && (getPort() > 0 && getPort() < 65535)) {
                	hideModal();
                }
           
                
               
            }
            //I set "X" to be my Cancel button's "action command":
            else
            {
                hasConnected=false;
                addressError.setVisible(false);
                portError.setVisible(false);
                nameError.setVisible(false);
                hideModal();
                
            }


           
            
        }
    }
}
        

        

