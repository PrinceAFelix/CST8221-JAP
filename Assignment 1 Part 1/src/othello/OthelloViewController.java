package othello;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;


public class OthelloViewController extends JFrame {

	private static final long serialVersionUID = 1L;

	
	/**Represents the black color*/
	Color black = new Color(0, 0, 0);
	/**Represents the white color*/
	Color white = new Color(255, 255, 255);

	JLabel mainFrame = new JLabel();
	
	//the panel to include the chess board and the buttons
	JPanel westPanel, eastPanel, bluePanel, playerMovesPanel, chatPanel, showMovesPanel, sideNumber, sideLetter, sideNumber1, sideLetter1;

	/**Represents the game board*/
	JLabel[][] gameBoard = new JLabel[8][8];

	/**Represents the Side Letters*/
	private static final String letters = "ABCDEFGH";
	/**Represents the top and bottom numbers*/
	private static final String numbers = " 12345678 ";
	
	//Buttons
	
	/**Represents the submit button*/
	JButton submitButton = new JButton();
	/**Represents the up button*/
	JButton topButton = new JButton();
	/**Represents the left button*/
	JButton leftButton = new JButton();
	/**Represents the right button*/
	JButton rightButton = new JButton();
	/**Represents the down button*/
	JButton botButton = new JButton();
	/**Represents the move button*/
	JButton moveButton = new JButton();

	/**Represents the show moves check box*/
	JCheckBox showMoves;

	/**Represents the chat field*/
	private JTextField chatField;
	
	

	/**
	 * No arguments Constructor
	 */
	public OthelloViewController() {
	
		//Set the layout and border for the Main Frame
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY)); 

	
		//Create the west panel where the game board will be
		westPanel = new JPanel();
		westPanel.setLayout(new BorderLayout());
		westPanel.setPreferredSize(new Dimension(550,500));
		westPanel.setBackground(new Color(220, 220, 220));
		westPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,5, Color.GRAY));

		//Board Field Panel which use Grid Layout of 8x8
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(8,8)); 
		for (int i = 0; i < 8; i++) {  //Nested Loops to place all the label in 8 by 8 Grid
			for (int j = 0; j < 8; j++) {				
				if(i%2 == 0) { //If i % of 2 is equal to 2
					if(j%2 == 0) {
						if(i == 4 && j == 4) {
							gameBoard[i][j] = new JLabel(new ImageIcon(getClass().getResource("/images/white.png")));
							createBoard(gameBoard, i, j, 'w');
						} else {
							gameBoard[i][j] = new JLabel();
							createBoard(gameBoard, i, j, 'w');
						}
					}
					else {
						if(i == 4 && j == 3) {
							gameBoard[i][j] = new JLabel(new ImageIcon(getClass().getResource("/images/black.png")));
							createBoard(gameBoard, i, j, 'b');
						} else {
							gameBoard[i][j] = new JLabel();
							createBoard(gameBoard, i, j, 'b');	
						}
					}
				}else {
					if(j%2 != 0) {
						if(i == 3 && j == 3) {
							gameBoard[i][j] = new JLabel(new ImageIcon(getClass().getResource("/images/white.png")));
							createBoard(gameBoard, i, j, 'w');
						} else {
							gameBoard[i][j] = new JLabel();
							createBoard(gameBoard, i, j, 'w');
						}
					}
					else { 
						if(i == 3 && j == 4) {
							gameBoard[i][j] = new JLabel(new ImageIcon(getClass().getResource("/images/black.png")));
							createBoard(gameBoard, i, j, 'b');
						} else {
							gameBoard[i][j] = new JLabel();
							createBoard(gameBoard, i, j, 'b');
						}
					}
				}
				boardPanel.add(gameBoard[i][j]);              
			}
		}

		sideLetter = new JPanel();
		sideLetter.setLayout(new GridLayout(8, 1));
		for(int i = 0; i < 8; i++) {
			JLabel letter = new JLabel(letters.substring(i, i+1), JLabel.CENTER);
			letter.setBackground(new Color(220, 220, 220));
			letter.setFont(new Font(letter.getFont().getName(), letter.getFont().getStyle(), 20));
			letter.setPreferredSize(new Dimension(60, 60));
			sideLetter.add(letter);
		}
		
	

		sideLetter1 = new JPanel();
		sideLetter1.setLayout(new GridLayout(8, 1));
		for(int i = 0; i < 8; i++) {
			JLabel letter = new JLabel(letters.substring(i, i+1), JLabel.CENTER);
			letter.setBackground(new Color(220, 220, 220));
			letter.setFont(new Font(letter.getFont().getName(), letter.getFont().getStyle(), 20));
			letter.setPreferredSize(new Dimension(60, 60));			
			sideLetter1.add(letter);
		}

		sideNumber = new JPanel();
		sideNumber.setLayout(new GridLayout(1, 8));
		for(int i = 0; i < 10; i++) {
			
			
			/*
			JLabel number;
			if(i == 0 || i == 9){
				number  = new JLabel();
			}else{
				number  = new JLabel(numbers.substring(i, i+1), JLabel.CENTER);
			}*/
			JLabel number = new JLabel(numbers.substring(i, i+1), JLabel.CENTER);
			number.setBackground(new Color(220, 220, 220));
			number.setFont(new Font(number.getFont().getName(), number.getFont().getStyle(), 20));
			number.setPreferredSize(new Dimension(60, 60));
			sideNumber.add(number);
		}
		
		sideNumber1 = new JPanel();
		sideNumber1.setLayout(new GridLayout(1, 10));
		for(int i = 0; i < 10; i++) {
			JLabel number = new JLabel(numbers.substring(i, i+1), JLabel.CENTER);
			number.setBackground(new Color(220, 220, 220));
			number.setFont(new Font(number.getFont().getName(), number.getFont().getStyle(), 20));
			number.setPreferredSize(new Dimension(60, 60));
			sideNumber1.add(number);
		}

		//Add side numbers to the north and south of west panel
		westPanel.add(sideNumber, BorderLayout.NORTH);
		westPanel.add(sideNumber1, BorderLayout.SOUTH);
		
		//Add game board to the center of west panel
		westPanel.add(boardPanel, BorderLayout.CENTER);

		//Add side letters to the east of West panel
		westPanel.add(sideLetter, BorderLayout.EAST);
		westPanel.add(sideLetter1, BorderLayout.WEST);

		//Create text field for the chat field
		chatField = new JTextField();
		chatField.setEditable(true);
		chatField.setHorizontalAlignment(JTextField.LEFT);

		//Create the submit button for the chat field
		submitButton.setPreferredSize(new Dimension(50, 20));
		submitButton.setBackground(Color.BLACK);
		submitButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
		submitButton.setText("Submit"); 
		submitButton.setForeground(Color.RED);

		//Set up action listener for the submit button
		submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("sub");
            }
        });
	
		//Create the Chat Panel
		chatPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout());
		chatPanel.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.GRAY));
		
		//add button and chat field to the center and east of chat panel
		chatPanel.add(chatField, BorderLayout.CENTER);
		chatPanel.add(submitButton, BorderLayout.EAST);
		
		//setup east panel
		eastPanel = new JPanel();
		eastPanel.setLayout(new BorderLayout());
		eastPanel.setPreferredSize(new Dimension(504,500));
		eastPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));

		//Set up show moves Panel
		showMovesPanel = new JPanel();
		showMovesPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.GRAY));
		showMovesPanel.setLayout(new BorderLayout(0,0)); 

		//Set up the check box
		showMoves = new JCheckBox("Show Valid Moves");
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new BorderLayout(0,0));
		checkBoxPanel.setBackground(Color.WHITE);
		checkBoxPanel.add(showMoves);
		//Add to the North side of show moves panel
		showMovesPanel.add(checkBoxPanel, BorderLayout.NORTH);
		
		//Place the show moves to the North side of east panel
		eastPanel.add(showMovesPanel, BorderLayout.NORTH);

		//PLayer Moves Panel
		playerMovesPanel = new JPanel();
		playerMovesPanel.setLayout(new BorderLayout(0,0)); 
		playerMovesPanel.setPreferredSize(new Dimension(100,500));
		playerMovesPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));

		
		//Set up a wrapper to gather all the moves button
		JLabel buttonsWrapper = new JLabel();
		buttonsWrapper.setLayout(new GridLayout(3, 3, 3 ,3)); //A 3 by 3 grid to place all the button
		buttonsWrapper.setPreferredSize(new Dimension(170,300));
		buttonsWrapper.setBackground(new Color(175, 175, 255));
		
		//Set up move button
		moveButton = new JButton("move");
		moveButton.setFont(new Font(moveButton.getFont().getName(), moveButton.getFont().getStyle(), 9));
		moveButton(moveButton, 'm');

		//Create all 4 direction button
		topButton = new JButton(new ImageIcon(getClass().getResource("/images/uparrow.png"))); //Up button
		moveButton(topButton, 'u');

		leftButton = new JButton(new ImageIcon(getClass().getResource("/images/leftarrow.png"))); //Left button
		moveButton(leftButton, 'l');

		botButton = new JButton(new ImageIcon(getClass().getResource("/images/downarrow.png"))); //Down button
		moveButton(botButton, 'd');

		rightButton = new JButton(new ImageIcon(getClass().getResource("/images/rightarrow.png"))); //Right button
		moveButton(rightButton, 'r');
		
		//Add all buttons to the wrapper which is 3 by 3 Grid
		buttonsWrapper.add(new JLabel(""));
		buttonsWrapper.add(topButton);
		buttonsWrapper.add(new JLabel(""));
		buttonsWrapper.add(leftButton);
		buttonsWrapper.add(moveButton);
		buttonsWrapper.add(rightButton);
		buttonsWrapper.add(new JLabel(""));
		buttonsWrapper.add(botButton);
		buttonsWrapper.add(new JLabel(""));

		//Place the wrapper to the west side of east Panel
		eastPanel.add(buttonsWrapper, BorderLayout.WEST);
		
		//Create a wrapper to gather the Player Piece Info
		JLabel playerPieceWrapper = new JLabel();
		playerPieceWrapper.setLayout(new GridLayout(2, 2)); //2 by 2 Grid Layout
		playerPieceWrapper.setPreferredSize(new Dimension(250,300));
		playerPieceWrapper.setBackground(new Color(175, 175, 255));

		//Create the Label and images for both player 1 and player 2
		JLabel p1 = new JLabel("Player 1 Pieces: ");
		JLabel img1 = new JLabel(new ImageIcon(getClass().getResource("/images/white.png")));
		img1.setText("2");
		JLabel p2 = new JLabel("Player 2 Pieces: ");
		JLabel img2 = new JLabel(new ImageIcon(getClass().getResource("/images/black.png")));
		img2.setText("2");
		
		//add the player info to the player wrapper which is 2 by 2
		playerPieceWrapper.add(p1);
		playerPieceWrapper.add(img1);
		playerPieceWrapper.add(p2);
		playerPieceWrapper.add(img2);

		//Place the player wrapper to the east side of east panel
		eastPanel.add(playerPieceWrapper, BorderLayout.EAST);

		//Set up Blue Panel
		bluePanel = new JPanel();
		bluePanel.setLayout(new BorderLayout(0,0)); 

		//Create the label for player moves info
		JLabel pMoves = new JLabel();
		pMoves.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.GRAY));
		pMoves.setLayout(new BorderLayout());
		pMoves.setPreferredSize(new Dimension(50,450)); 
		pMoves.setBackground(new Color(175, 175, 255));
		pMoves.setText("<html>  Player 1 initialized with 2 pieces.<br>  Player 2 initialized with 2 pieces.</html>");
		pMoves.setOpaque(true);
		
		//Add blue panel
		bluePanel.add(pMoves);
		
		//Place the blue panel to the south side of east panel
		eastPanel.add(bluePanel, BorderLayout.SOUTH);
		
		//Place the West Panel to the East side of the Main Frame
		mainFrame.add(westPanel, BorderLayout.WEST);
		//Place the East Panel to the East side of the Main Frame
		mainFrame.add(eastPanel, BorderLayout.EAST);
		//Place the Chat Panel to the East side of the Main Frame
		mainFrame.add(chatPanel, BorderLayout.SOUTH);
		//mainFrame.add(playerMovesPanel, BorderLayout.NORTH);
		
		//Add Frame
		add(mainFrame);
	}
	
	/**This method create the setting for the Jbutton and apply active listeners
	 * 
	 * @param myButton represents the JButton
	 * @param dir represents the direction
	 * @return JButton
	 */
	public JButton moveButton(JButton myButton, char dir){
		
		//Apply settings for Button
		myButton.setBackground(Color.WHITE);
		myButton.setOpaque(true);
		myButton.setPreferredSize(new Dimension(60, 60));
		myButton.setMargin(new Insets(10, 10, 10, 10));
		
		//Add Action Listener
		myButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
//            	String aCommand = ae.getActionCommand();
//    			System.out.println(aCommand);
				
            	switch(dir){
					case 'u':
						System.out.println("up");
						break;
					case 'd':
						System.out.println("down");
						break;
					case 'l':
						System.out.println("left");
						break;
					case 'r':
						System.out.println("right");
						break;
					case 'm':
						System.out.println("move");
						break;

					default:
						break;
			
				}
				
            }
        });
	
		//Return JButton
		return myButton;
	}
	
	/**This method will create the 2D array of JLabels settings
	 * 
	 * @param myBoard represents 2D array JLabel 
	 * @param i represents the index i
	 * @param j represents the index j
	 * @param color represents which color of the board
	 * @return 2D array JLabel  
	 */
	public JLabel[][] createBoard(JLabel[][] myBoard, int i, int j, char color){
		
		//Checks if color is 'b'
		if(color == 'b') {
			//Apply setting for White
			myBoard[i][j].setBackground(black);
		}else {
			//Apply setting for Black
			myBoard[i][j].setBackground(white);
		}
		myBoard[i][j].setOpaque(true);
		myBoard[i][j].setPreferredSize(new Dimension(60, 60));
		
		//Return the label
		return myBoard;
		
	}


	

	

	/**Inner Class  to manage all of buttons and the check box
	 * 
	 * @author princ
	 *
	 */
	public class Controller implements ActionListener {
		

		
		@Override
		public void actionPerformed(ActionEvent ae) {
			String aCommand = ae.getActionCommand();
			System.out.println(aCommand);
		}
	}
	
}