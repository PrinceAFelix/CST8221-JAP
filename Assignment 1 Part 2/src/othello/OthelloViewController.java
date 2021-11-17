package othello;

import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

import java.awt.*;


/**
 * OthelloViewController<br>
 * The OthelloViewController runs the whole game Othello
 *  
 * 
 * @author Prince Adrianne Felix
 * @since 2021-07-19 CST221 Java Application Programming
 * @version 1.0
 * 
 */
public class OthelloViewController extends JFrame {

	private static final long serialVersionUID = 1L;

	Controller myController = new Controller();
	OthelloModel myModel = new OthelloModel();
	
	JFrame dialogFrame = new JFrame();
	
	
	
	
	OthelloNetworkModalViewController networkDialog = new OthelloNetworkModalViewController(this);
	
	
	
	int offsetX = 0;
	int offsetY = 0;
	
	/**Represents the black color*/
	Color black = new Color(0, 0, 0);
	/**Represents the white color*/
	Color white = new Color(255, 255, 255);
	
	String whitePiece = "white.png";
	String blackPiece = "black.png";
	String checkMark = "checkmark.png";
	
	ImageIcon whitePlayer = new ImageIcon(getClass().getResource(whitePiece));
	ImageIcon blackPlayer = new ImageIcon(getClass().getResource(blackPiece));
	ImageIcon validMoves = new ImageIcon(getClass().getResource(checkMark));

	JLabel mainFrame = new JLabel();
	
	JLabel pMoves;
	
	//the panel to include the chess board and the buttons
	JPanel westPanel, eastPanel, bluePanel, playerMovesPanel, chatPanel, showMovesPanel, sideNumber, sideLetter, sideNumber1, sideLetter1;
	
	JPanel boardPanel = new JPanel();

	/**Represents the game board*/
	JLabel[][] gameBoard = new JLabel[8][8];

	/**Represents the Side Letters*/
	private static final String letters = "ABCDEFGH";
	/**Represents the top and bottom numbers*/
	private static final String numbers = " 12345678 ";
	
	//Buttons
	
	/**Represents the submit button*/
	private JButton submitBtn = new JButton();
	/**Represents the up button*/
	JButton topBtn = new JButton();
	/**Represents the left button*/
	JButton leftBtn = new JButton();
	/**Represents the right button*/
	JButton rightBtn = new JButton();
	/**Represents the down button*/
	JButton botBtn = new JButton();
	/**Represents the move button*/
	private JButton moveBtn = new JButton();

	/**Represents the show moves check box*/
	JCheckBox showMoves = new JCheckBox("Show Valid Moves");

	/**Represents the chat field*/
	JTextField chatField;
	
	JLabel blackScore = new JLabel(blackPlayer);
	JLabel whiteScore = new JLabel(whitePlayer);
	
	String initialScore = "2";
	
	JMenuBar menuBar;
	
	JMenu file, game, help, boardSubMenu, debugSubMenu, network;
	
	//File Menu
	JMenuItem newGame, load, save, exit , newConnection, disconnect;
	
	//Game Menu
	JRadioButtonMenuItem  normalGame, corner, side, oneCapture, twoCapture, emptyBoard, inner, upArrow;
	JMenuItem normal, canadian, customColor;
	
	//Help Menu
	JMenuItem about;
	
	
	
	
	

	/**
	 * No arguments Constructor
	 *
	 */
	public OthelloViewController() {
		
		networkDialog.setSize(320,200);
	
	
		menuBar  = new JMenuBar();
		setJMenuBar(menuBar);
		
		file = new JMenu("File");
		game = new JMenu("Game");
			boardSubMenu = new JMenu("Board Colours");
			debugSubMenu = new JMenu("Debug Scenarios");
		
		network = new JMenu("Network");
			newConnection = new JMenuItem("New Connection");
			disconnect = new JMenuItem("Disconnect");
			newConnection.addActionListener(myController);
			disconnect.setEnabled(false);
			disconnect.addActionListener(myController);
		
		help = new JMenu("Help");
		
		
		//File Menu
		file.add(newGame = new JMenuItem("New Game"));
		newGame.addActionListener(myController);
		file.add(load = new JMenuItem("Load"));
		load.setEnabled(false);
		file.add(save = new JMenuItem("Save"));
		save.setEnabled(false);
		file.add(exit = new JMenuItem("Exit"));
		exit.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
	
		
		//Game Menu
		debugSubMenu.add(normalGame = new JRadioButtonMenuItem("Normal Game"));
		debugSubMenu.add(corner = new JRadioButtonMenuItem("Cornert Test"));
		corner.addActionListener(myController);
		debugSubMenu.add(side = new JRadioButtonMenuItem("Side Test"));
		side.addActionListener(myController);
		debugSubMenu.add(oneCapture = new JRadioButtonMenuItem("1x Capture Test"));
		oneCapture.addActionListener(myController);
		debugSubMenu.add(twoCapture = new JRadioButtonMenuItem("2x Capture Test"));
		twoCapture.addActionListener(myController);
		debugSubMenu.add(emptyBoard = new JRadioButtonMenuItem("Empty Board"));
		emptyBoard.addActionListener(myController);
		debugSubMenu.add(inner = new JRadioButtonMenuItem("Inner Square Test"));
		inner.addActionListener(myController);
		debugSubMenu.add(upArrow = new JRadioButtonMenuItem("Up Arrow Orientation Test"));
		upArrow.addActionListener(myController);
		
		
		boardSubMenu.add(normal = new JMenuItem("Normal"));
		normal.addActionListener(myController);
		boardSubMenu.add(canadian = new JMenuItem("Canadian"));
		
		boardSubMenu.add(customColor = new JMenuItem("Custom Board Colours"));
		
		
		game.add(boardSubMenu);
		game.add(debugSubMenu);
		
		//Network
		network.add(newConnection);
		network.add(disconnect);
		
		
		
		
		//Help Menu (About)
		help.add(about = new JMenuItem("About"));
		about.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(null, "<html>Othello Game <br>by Prince Felix <br><br>June 2021</html>", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		menuBar.add(file);
		menuBar.add(game);
		menuBar.add(network);
		menuBar.add(help);
	
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
		boardPanel.setLayout(new GridLayout(8,8)); 
		createMyBoard(boardPanel, false);
	

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
		submitBtn.setPreferredSize(new Dimension(50, 20));
		submitBtn.setBackground(Color.BLACK);
		submitBtn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
		submitBtn.setText("Submit"); 
		submitBtn.setEnabled(false);
		submitBtn.setForeground(Color.RED);

		//Set up action listener for the submit button
		
		submitBtn.addActionListener(myController);
		
	
		//Create the Chat Panel
		chatPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout());
		chatPanel.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.GRAY));
		
		//add button and chat field to the center and east of chat panel
		chatPanel.add(chatField, BorderLayout.CENTER);
		chatPanel.add(submitBtn, BorderLayout.EAST);
		
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
		showMoves.addActionListener(myController);
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
		buttonsWrapper.setPreferredSize(new Dimension(110,300));
		buttonsWrapper.setBorder(new EmptyBorder(5, 5, 5,0));
		buttonsWrapper.setBackground(new Color(175, 175, 255));
		
		//Set up move button
		moveBtn = new JButton("move");
		moveBtn.setFont(new Font(moveBtn.getFont().getName(), moveBtn.getFont().getStyle(), 9));
		moveButton(moveBtn);
		
		//Create all 4 direction button
		topBtn = new JButton(new ImageIcon(getClass().getResource("uparrow.png"))); //Up button
		moveButton(topBtn);
		

		leftBtn = new JButton(new ImageIcon(getClass().getResource("leftarrow.png"))); //Left button
		moveButton(leftBtn);
		
		botBtn = new JButton(new ImageIcon(getClass().getResource("downarrow.png"))); //Down button
		moveButton(botBtn);
		
		
		rightBtn = new JButton(new ImageIcon(getClass().getResource("rightarrow.png"))); //Right button
		moveButton(rightBtn);
		
		
		//Add all buttons to the wrapper which is 3 by 3 Grid
		buttonsWrapper.add(new JLabel(""));
		buttonsWrapper.add(topBtn);
		buttonsWrapper.add(new JLabel(""));
		buttonsWrapper.add(leftBtn);
		buttonsWrapper.add(moveBtn);
		buttonsWrapper.add(rightBtn);
		buttonsWrapper.add(new JLabel(""));
		buttonsWrapper.add(botBtn);
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
		
		blackScore.setText(initialScore);
		JLabel p2 = new JLabel("Player 2 Pieces: ");

		whiteScore.setText("2");
		
		//add the player info to the player wrapper which is 2 by 2
		playerPieceWrapper.add(p1);
		playerPieceWrapper.add(blackScore);
		playerPieceWrapper.add(p2);
		playerPieceWrapper.add(whiteScore);

		//Place the player wrapper to the east side of east panel
		eastPanel.add(playerPieceWrapper, BorderLayout.EAST);

		//Set up Blue Panel
		bluePanel = new JPanel();
		bluePanel.setLayout(new BorderLayout()); 
		bluePanel.setPreferredSize(new Dimension(0,395)); 
		bluePanel.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.GRAY));
		bluePanel.setBackground(new Color(175, 175, 255));

		//Create the label for player moves info
		pMoves = new JLabel();
		pMoves.setBackground(new Color(175, 175, 255));
		pMoves.setText("<html>  Player 1 initialized with 2 piece(s).<br>  Player 2 initialized with 2 piece(s).");
		pMoves.setFont(new Font("Calibri", Font.PLAIN, 12));
		pMoves.setBorder(new EmptyBorder(0, 5, 0, 0));
		//pMoves.setPreferredSize(new Dimension(0,395)); 
	
		pMoves.setOpaque(true);
		
		//Add blue panel
		bluePanel.add(pMoves, BorderLayout.NORTH);
		
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
	
	public void createMyBoard(JPanel boardPanel, boolean update) {
		
		if(update) {
			for (int i = 0; i < 8; i++) {  //Nested Loops to place all the label in 8 by 8 Grid
				for (int j = 0; j < 8; j++) {	
					
					final int row = i;
					final int col = j;
					int val = myModel.getSquare(i, j);
					if(i % 2 == 0) { //If i % of 2 is equal to 2
						if(j %2  == 0) {
							
						
							gameBoard[i][j] = new JLabel();
							
							if(val == 1)
								gameBoard[i][j] = new JLabel(blackPlayer);
							else if(val == 2)
								gameBoard[i][j] = new JLabel(whitePlayer);
							
							createBoard(gameBoard, i, j, 'w');
							
				
						}
						else {
							gameBoard[i][j] = new JLabel();
							
							if(val == 1)
								gameBoard[i][j] = new JLabel(blackPlayer);
							else if(val == 2)
								gameBoard[i][j] = new JLabel(whitePlayer);
							
							createBoard(gameBoard, i, j, 'b');
							
							
						}
					}else {
						if(j % 2 != 0) {
							
							gameBoard[i][j] = new JLabel();
							
							if(val == 1)
								gameBoard[i][j] = new JLabel(blackPlayer);
							else if(val == 2)
								gameBoard[i][j] = new JLabel(whitePlayer);
							createBoard(gameBoard, i, j, 'w');
							
							
							
						}
						else { 
							gameBoard[i][j] = new JLabel();
							
							if(val == 1)
								gameBoard[i][j] = new JLabel(blackPlayer);
							else if(val == 2)
								gameBoard[i][j] = new JLabel(whitePlayer);
							
							createBoard(gameBoard, i, j, 'b');
							
						
						}
					}
					
					
					boardPanel.add(gameBoard[i][j]);              
				}
			}
		}else {
			for (int i = 0; i < 8; i++) {  //Nested Loops to place all the label in 8 by 8 Grid
				for (int j = 0; j < 8; j++) {	
					
					final int row = i;
					final int col = j;
					
					if(i % 2 == 0) { //If i % of 2 is equal to 2
						if(j %2  == 0) {
							if(i == 4 && j == 4) {
								gameBoard[i][j] = new JLabel(whitePlayer);
							} else {
								gameBoard[i][j] = new JLabel();
							}
							createBoard(gameBoard, i, j, 'w');
							
							customColor.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent arg0) {
									createBoard(gameBoard, row, col, 'y');
									
								}
								
							});
							
							normal.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									createBoard(gameBoard, row, col, 'w');
								}
								
							});
						}
						else {
							if(i == 4 && j == 3) {
								gameBoard[i][j] = new JLabel(blackPlayer);
							} else {
								gameBoard[i][j] = new JLabel();
							}
							
							createBoard(gameBoard, i, j, 'b');
							
							/*Board Color Changing*/
							canadian.addActionListener(new ActionListener() {
	
								@Override
								public void actionPerformed(ActionEvent arg0) {
									createBoard(gameBoard, row, col, 'r');
									
								}
								
							});
							
							customColor.addActionListener(new ActionListener() {
	
								@Override
								public void actionPerformed(ActionEvent arg0) {
									createBoard(gameBoard, row, col, 'c');
								}
								
							});
							
							normal.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									createBoard(gameBoard, row, col, 'b');
								}
								
							});
						}
					}else {
						if(j % 2 != 0) {
							
							if(i == 3 && j == 3) {
								gameBoard[i][j] = new JLabel(whitePlayer);
							} else {
								gameBoard[i][j] = new JLabel();
							}
							createBoard(gameBoard, i, j, 'w');
							
							
							customColor.addActionListener(new ActionListener() {
	
								@Override
								public void actionPerformed(ActionEvent arg0) {
									createBoard(gameBoard, row, col, 'y');
								}
								
							});
							
							normal.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									createBoard(gameBoard, row, col, 'w');
								}
								
							});
						}
						else { 
							if(i == 3 && j == 4) {
								gameBoard[i][j] = new JLabel(blackPlayer);
							} else {
								gameBoard[i][j] = new JLabel();
							}
							
							createBoard(gameBoard, i, j, 'b');
							
							/*Board Color Changing*/
							canadian.addActionListener(new ActionListener() {
	
								@Override
								public void actionPerformed(ActionEvent arg0) {
									createBoard(gameBoard, row, col, 'r');
									
								}
								
							});
							
							customColor.addActionListener(new ActionListener() {
	
								@Override
								public void actionPerformed(ActionEvent arg0) {
									createBoard(gameBoard, row, col, 'c');
								}
								
							});
							
							normal.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									createBoard(gameBoard, row, col, 'b');
								}
								
							});
						}
					}
					
					
					boardPanel.add(gameBoard[i][j]);              
				}
			}
		}
	}
	
	/**This method create the setting for the Jbutton and apply active listeners
	 * 
	 * @param myButton represents the JButton
	 * @return myButton
	 */
	public JButton moveButton(JButton myButton){
		
		//Apply settings for Button
		myButton.setBackground(Color.WHITE);
		myButton.setOpaque(true);
		myButton.setPreferredSize(new Dimension(60, 60));
		myButton.setMargin(new Insets(10, 10, 10, 10));
		myButton.setBorder(new EmptyBorder(5, 2, 2,2 ));
		myButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		
		myButton.addActionListener(myController);
	
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
		}else if(color == 'w'){
			//Apply setting for Black
			myBoard[i][j].setBackground(white);
		}else if(color == 'r') {
			myBoard[i][j].setBackground(Color.RED);
		}else if(color == 'c') {
			myBoard[i][j].setBackground(Color.CYAN);
		}else if(color == 'y') {
			myBoard[i][j].setBackground(Color.YELLOW);
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
	class Controller implements ActionListener {
	
		//Represents i and j in loops
		int row = 3;
		int col = 3;
		
		//Represents scores
		int bScore = 0;
		int wScore = 0;
		
		//Boolean variable
		boolean greenZone = false;
		boolean isBlackTurn = true;
		boolean isAvailable = false;
		
		//OthelloModel object
		OthelloModel myModel = new OthelloModel();
		
		//Use to determine the model type
		int model = 0;
		
	
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			
			//Center the dialog box
			Point thisLocation = getLocation();
			Dimension parentSize = getSize();
			Dimension dialogSize = networkDialog.getSize();
			
			offsetX =  (parentSize.width-dialogSize.width)/2+thisLocation.x;
			offsetY = (parentSize.height-dialogSize.height)/2+thisLocation.y;
			
			
			//Check for top button click
			if(ae.getSource() == topBtn) {
				
				if(row <= 0 ) {
					pMoves.setText(pMoves.getText() + "<br>Out of Bounds");
				}else {
					pMoves.setText(pMoves.getText() + "<br>Player Moves Up");
					gameBoard[row][col].setBorder(null);
					row--;
					gameBoard[row][col].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					greenZone = true;
				}
				
			}else if(ae.getSource() == botBtn) {//Check for bottom button click
				if(row >= 7) {
					pMoves.setText(pMoves.getText() + "<br>Out of Bounds");
				}else{
					pMoves.setText(pMoves.getText() + "<br>Player Moves Down");
					gameBoard[row][col].setBorder(null);
					row++;
					gameBoard[row][col].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					greenZone = true;
				}
				
			}else if(ae.getSource() == leftBtn) { //Check for left button click
				if(col <= 0) {
					pMoves.setText(pMoves.getText() + "<br>Out of Bounds");
				}else {
					pMoves.setText(pMoves.getText() + "<br>Player Moves Left");
					gameBoard[row][col].setBorder(null);
					col--;
					gameBoard[row][col].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					greenZone = true;
				}
			}else if(ae.getSource() == rightBtn) { //Check for right button click
				if(col >= 7) {
					pMoves.setText(pMoves.getText() + "<br>Out of Bounds");
				}else {
					pMoves.setText(pMoves.getText() + "<br>Player Moves Right");
					gameBoard[row][col].setBorder(null);
					col++;
					gameBoard[row][col].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					greenZone = true;
				}
				
			}
			
			
			//Check for move button click to add piece depending on which player is on
			if(ae.getSource() == moveBtn) {
				
			
				if(isBlackTurn == true) {
					if(gameBoard[row][col].getIcon() == null) {
						gameBoard[row][col].setIcon(blackPlayer);
						gameBoard[row][col].setHorizontalAlignment(JLabel.CENTER);
						
						
						isBlackTurn = false;
					}else {
						pMoves.setText(pMoves.getText() + "<br>Cannot place a piece here");
					}
					
					
				}else {
					if(gameBoard[row][col].getIcon() == null) {
						gameBoard[row][col].setIcon(whitePlayer);
						gameBoard[row][col].setHorizontalAlignment(JLabel.CENTER);
						
						
						isBlackTurn = true;
					}else {
						pMoves.setText(pMoves.getText() + "<br>Cannot place a piece here");
					}
					
					
				}
				
				for (int i = 0; i < 8; i++) {  //Nested Loops to place all the label in 8 by 8 Grid
					for (int j = 0; j < 8; j++) {	
						if(gameBoard[i][j].getIcon() == whitePlayer) {
							wScore++;
							
						}else if(gameBoard[i][j].getIcon() == blackPlayer){
							bScore++;
							
						}
					}
				}
				
				blackScore.setText(String.valueOf(bScore));
				whiteScore.setText(String.valueOf(wScore));
				
				//Resets score counter
				bScore = 0;
				wScore = 0;
				
				
			}
			
			//Check for show moves 
			if(ae.getSource() == showMoves) {
				
				
				
				int r = 0;
				int c = 0;
				if(showMoves.isSelected()) { //If selected show all the possible moves

					
					for (int i = 0; i < 8; i++) {  //Nested Loops to loop around the board
						for (int j = 0; j < 8; j++) {	

							r = i;
							c = j;
							
							if(isBlackTurn) {
								
								
								//Check all the possible moves
								if(gameBoard[i][j].getIcon() == blackPlayer) {
									
									//Up
									if(r-2 < 0 || c > 7 || c < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i - 1][j].getIcon() == whitePlayer) {
											if(isAvailable(gameBoard, i-2, j))
												revealMoves(gameBoard, i - 2, j);
										}
									}
									
									//Down
									if(r < 0 || c > 7 || c < 0 || r+2 > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i + 1][j].getIcon() == whitePlayer) {
											if(isAvailable(gameBoard, i+2, j))
												revealMoves(gameBoard, i + 2, j);
										}
									}
									
									//Left
									if(r < 0 || c+2 > 7 || c < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i][j + 1].getIcon() == whitePlayer) {
											if(isAvailable(gameBoard, i, j+2))
												revealMoves(gameBoard, i, j + 2);
										}
									}
									
									//Right
									if(r < 0 || c > 7 || c-2 < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i][j - 1].getIcon() == whitePlayer) {
											if(isAvailable(gameBoard, i, j-2))
												revealMoves(gameBoard, i, j - 2);
										}
									}
									
									
									//Diagonal
									if(r-2 < 0 || c > 7 || c-2 < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i-1][j-1].getIcon() == whitePlayer) {
											if(isAvailable(gameBoard, i-2, j-2))
												revealMoves(gameBoard, i-2, j - 2);
										}
									}
									
									if(r < 0 || c > 7 || c-2 < 0 || r+2 > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i+1][j-1].getIcon() == whitePlayer) {
											if(isAvailable(gameBoard, i+2, j-2))
												revealMoves(gameBoard, i+2, j - 2);
										}
									}
									
									if(r-2 < 0 || c+2 > 7 || c < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i-1][j+1].getIcon() == whitePlayer) {
											if(isAvailable(gameBoard, i-2, j+2))
												revealMoves(gameBoard, i-2, j + 2);
										}
									}
									
									if(r < 0 || c+2 > 7 || c < 0 || r+2 > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i+1][j+1].getIcon() == whitePlayer) {
											if(isAvailable(gameBoard, i+2, j+2))
												revealMoves(gameBoard, i+2, j + 2);
										}
									}

									
									
								}
							
							}else {
								
								if(gameBoard[i][j].getIcon() == whitePlayer) {
									
									//Up
									if(r-2 < 0 || c > 7 || c < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i - 1][j].getIcon() == blackPlayer) {
											if(isAvailable(gameBoard, i-2, j))
												revealMoves(gameBoard, i - 2, j);
										}	
									}
									
									//Down
									if(r < 0 || c > 7 || c < 0 || r+2 > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i + 1][j].getIcon() == blackPlayer) {
											if(isAvailable(gameBoard, i+2, j))
												revealMoves(gameBoard, i + 2, j);
										}
									}
									
									
									//Left
									if(r < 0 || c+2 > 7 || c < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i][j + 1].getIcon() == blackPlayer) {
											if(isAvailable(gameBoard, i, j+2))
												revealMoves(gameBoard, i, j + 2);
										}
									}
									
									//Right
									if(r < 0 || c > 7 || c-2 < 0 || r+2 > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i][j - 1].getIcon() == blackPlayer) {
											if(isAvailable(gameBoard, i, j-2))
												revealMoves(gameBoard, i, j - 2);
										}
									}
									
									
									//Diagonal
									
									if(r-2 < 0 || c > 7 || c-2 < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i-1][j-1].getIcon() == blackPlayer) {
											if(isAvailable(gameBoard, i-2, j-2))
												revealMoves(gameBoard, i-2, j - 2);
										}
									}
									
									if(r < 0 || c > 7 || c-2 < 0 || r+2 > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i+1][j-1].getIcon() == blackPlayer) {
											if(isAvailable(gameBoard, i+2, j-2))
												revealMoves(gameBoard, i+2, j - 2);
										}
									}
								
									if(r-2 < 0 || c+2 > 7 || c < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i-1][j+1].getIcon() == blackPlayer) {
											if(isAvailable(gameBoard, i-2, j+2))
												revealMoves(gameBoard, i-2, j + 2);
										}
									}
									
									if(r < 0 || c+2 > 7 || c < 0 || r+2 > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i+1][j+1].getIcon() == blackPlayer) {
											if(isAvailable(gameBoard, i+2, j+2))
												revealMoves(gameBoard, i+2, j + 2);
										}
									}
									
									
									
								}
							
							}
							
						}
					}
					
				}else if(showMoves.isSelected() == false){ //Hide the possible moves when show moves is not selected
					for (int i = 0; i < 8; i++) { 
						for (int j = 0; j < 8; j++) {
							
							r  = i;
							c = j;
							if(isBlackTurn) {
								if(gameBoard[i][j].getIcon() == blackPlayer) {
									
									if(r-2 < 0 || c > 7 || c < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i - 1][j].getIcon() == whitePlayer)
											hideMoves(gameBoard, i - 2, j);
									}
								
									if(r < 0 || c > 7 || c < 0 || r+2 > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {	
										if(gameBoard[i + 1][j].getIcon() == whitePlayer)
											hideMoves(gameBoard, i + 2, j);
									}
									
									if(r < 0 || c+2 > 7 || c < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i][j + 1].getIcon() == whitePlayer)
											hideMoves(gameBoard, i, j + 2);
									}
									
									if(r < 0 || c > 7 || c-2 < 0 || r+2 > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i][j - 1].getIcon() == whitePlayer)
											hideMoves(gameBoard, i, j - 2);
									}
									
									//Diagonal
									if(r-2 < 0 || c > 7 || c-2 < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i-1][j-1].getIcon() == whitePlayer)
											hideMoves(gameBoard, i-2, j-2);
									}
									
									if(r < 0 || c > 7 || c-2 < 0 || r+2 > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i+1][j-1].getIcon() == whitePlayer)
											hideMoves(gameBoard, i+2, j-2);
									}
									
									if(r-2 < 0 || c+2 > 7 || c < 0 || r > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i-1][j+1].getIcon() == whitePlayer)
											hideMoves(gameBoard, i-2, j+2);
									}
									
									if(r < 0 || c+2 > 7 || c < 0 || r+2 > 7) {
										//System.out.println("Index Out Of Bounds");
									}else {
										if(gameBoard[i+1][j+1].getIcon() == whitePlayer)
											hideMoves(gameBoard, i+2, j+2);
									}
									
									
								}
							}else {
								if(gameBoard[i][j].getIcon() == whitePlayer) {
									
									if(r-2 < 0 || c > 7 || c < 0 || r > 7) {
										System.out.println("1");
									}else {
										if(gameBoard[i - 1][j].getIcon() == blackPlayer) 
											hideMoves(gameBoard, i - 2, j);
									}
									
									if(r < 0 || c > 7 || c < 0 || r+2 > 7) {
										System.out.println("2");
									}else {
										if(gameBoard[i + 1][j].getIcon() == blackPlayer)
											hideMoves(gameBoard, i + 2, j);
									}
									
									if(r < 0 || c+2 > 7 || c < 0 || r > 7) {
										System.out.println("3");
									}else {
										if(gameBoard[i][j + 1].getIcon() == blackPlayer)
											hideMoves(gameBoard, i, j + 2);
									}
									
									if(r < 0 || c > 7 || c-2 < 0 || r+2 > 7) {
										System.out.println("4");
									}else {
										if(gameBoard[i][j - 1].getIcon() == blackPlayer)
											hideMoves(gameBoard, i, j - 2);
									}
									
									
									if(r-2 < 0 || c > 7 || c-2 < 0 || r > 7) {
										System.out.println("5");
									}else {
										if(gameBoard[i-1][j-1].getIcon() == blackPlayer)
											hideMoves(gameBoard, i-2, j-2);
									}
									
									if(r < 0 || c > 7 || c-2 < 0 || r+2 > 7) {
										System.out.println("6");
									}else {
										if(gameBoard[i+1][j-1].getIcon() == blackPlayer)
											hideMoves(gameBoard, i+2, j-2);
									}
									
									
									if(r-2 < 0 || c+2 > 7 || c < 0 || r > 7) {
										System.out.println("7");
									}else {
										if(gameBoard[i-1][j+1].getIcon() == blackPlayer)
											hideMoves(gameBoard, i-2, j+2);
									}
									
									if(r < 0 || c+2 > 7 || c < 0 || r+2 > 7) {
										System.out.println("Index out of bounds");
									}else {
										if(gameBoard[i+1][j+1].getIcon() == blackPlayer)
											hideMoves(gameBoard, i+2, j+2);
									}
									
									
									
								}
							}
						}
					}
				}
			}
			
			if(ae.getSource() == corner) {
				model = OthelloModel.CORNER_TEST;
			}else if(ae.getSource() == side) {
				model = OthelloModel.OUTER_TEST;
			}else if(ae.getSource() == oneCapture) {
				model = OthelloModel.TEST_CAPTURE;
			}else if(ae.getSource() == twoCapture) {
				model = OthelloModel.TEST_CAPTURE2;
			}else if(ae.getSource() == emptyBoard) {
				model = OthelloModel.UNWINNABLE;
			}else if(ae.getSource() == inner) {
				model = OthelloModel.INNER_TEST;
			}else if(ae.getSource() == upArrow) {
				model = OthelloModel.ARROW;
			}
			
			//If new Game is Pressed
			if(ae.getSource() == newGame) {
				myModel.prepareBoard(model);
				createMyBoard(boardPanel, true);
				System.out.println("Worked");
			}
			
			
			
			//Network
			if(ae.getSource() == newConnection) { //If the user pressed new connection
				networkDialog.setLocation(offsetX, offsetY);
				networkDialog.setVisible(true);
				
				//Condition for Pressed Connect and Cancel
				if(networkDialog.pressedConnect()) {//Connect Pressed
					//pMoves.setText(pMoves.getText() + "<br><br>Connecting to " + networkDialog.getAddress() + "<br>On Port " + networkDialog.getPort() + "<br>With name: " + networkDialog.getName());
					new OthelloNetworkController(networkDialog.getPort(), networkDialog.getAddress());
					new OthelloServer(networkDialog.getPort(), networkDialog.getAddress());
					disconnect.setEnabled(true);
					submitBtn.setEnabled(true);
				}else {//Cancel Pressed
					
				}
			}
			else if(ae.getSource() == disconnect) { //If Disconnect is pressed
				pMoves.setText(pMoves.getText() + "<br>Disconnected from server");
				disconnect.setEnabled(false);
				submitBtn.setEnabled(false);
			}
			
			if(ae.getSource() == submitBtn) {
				pMoves.setText(pMoves.getText() + "<br>" + chatField.getText());
				chatField.setText("");
			}
			
		}
		
		/**Use to reveal available moves
		 * 
		 * @param myBoard represents gameboard
		 * @param i integer i
		 * @param j integer j
		 */
		public void revealMoves(JLabel myBoard[][], int i, int j) {
			if(myBoard[i][j].getIcon() != blackPlayer && myBoard[i][j].getIcon() != whitePlayer) {
				myBoard[i][j].setIcon(validMoves);
				myBoard[i][j].setHorizontalAlignment(JLabel.CENTER);
			}
			
		}
		
		/**Use to hide available moves
		 * 
		 * @param myBoard represents gameboard
		 * @param i integer i
		 * @param j integer j
		 */
		public void hideMoves(JLabel myBoard[][], int i, int j) {
			if(myBoard[i][j].getIcon() != blackPlayer && myBoard[i][j].getIcon() != whitePlayer) {
				myBoard[i][j].setIcon(null);
				myBoard[i][j].setHorizontalAlignment(JLabel.CENTER);
			}
		
		}
		
		/**Check if specific location if available
		 * 
		 * @param myBoard represents gameboard
		 * @param i integer i
		 * @param j integer j
		 * @return true if available
		 */
		public boolean isAvailable(JLabel myBoard[][], int i, int j) {
			if(myBoard[i][j].getIcon() == null)
				return true;
			else if(myBoard[i][j].getIcon() == blackPlayer || myBoard[i][j].getIcon() == whitePlayer)
				return false;
			else
				return false;
		}

	}
	
}