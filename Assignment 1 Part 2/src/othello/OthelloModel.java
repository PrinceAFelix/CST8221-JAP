package othello;


/**
 * OthelloModel<br>
 * The OthelloModel class operates the changing template of the game, logic
 *  
 * 
 * @author Prince Adrianne Felix
 * @since 2021-07-19 CST221 Java Application Programming
 * @version 1.0
 */
public class OthelloModel
{
	private int[][] board;
	
    //Some class constants for your use:
    public static final int NORMAL=0;
    public static final int CORNER_TEST=1;
    public static final int OUTER_TEST=2;
    public static final int TEST_CAPTURE=3;
    public static final int TEST_CAPTURE2=4;
    public static final int UNWINNABLE=5;
    public static final int INNER_TEST=6;
    public static final int ARROW=7;

    public static final int EMPTY=0;
    public static final int BLACK=1;
    public static final int WHITE=2;
    
    //Default constructor prepares a normal game.
    /**
     * Default constructor
     */
    public OthelloModel()
    {
        prepareBoard(NORMAL);
    }

    /**This method determines the board type that the user wants to use
     * 
     * @param mode represents the board type
     */
	public void prepareBoard(int mode)
	{
		switch (mode)
		{
		case CORNER_TEST: 
			board=new int[][]{
				{2, 0, 0, 0, 0, 0, 0, 1},
				{0, 1, 0, 0, 0, 0, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 0, 0, 0, 0, 1, 0},
                {2, 0, 0, 0, 0, 0, 0, 2}};
            break;
                
		case OUTER_TEST:
			board = new int[][] {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 2, 2, 2, 2, 2, 2, 0},
				{0, 2, 1, 1, 1, 1, 2, 0},
				{0, 2, 1, 0, 0, 1, 2, 0},
				{0, 2, 1, 0, 0, 1, 2, 0},
				{0, 2, 1, 1, 1, 1, 2, 0},
				{0, 2, 2, 2, 2, 2, 2, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
			break;
                
		case INNER_TEST:
			board = new int[][] {
				{2, 2, 2, 2, 2, 2, 2, 2},
				{2, 0, 0, 0, 0, 0, 0, 2},
				{2, 0, 2, 2, 2, 2, 0, 2},
				{2, 0, 2, 1, 1, 2, 0, 2},
				{2, 0, 2, 1, 1, 2, 0, 2},
				{2, 0, 2, 2, 2, 2, 0, 2},
				{2, 0, 0, 0, 0, 0, 0, 2},
				{2, 2, 2, 2, 2, 2, 2, 2}};
			break;
                
		case UNWINNABLE:
			board = new int[][] {
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
			break;
                
		case TEST_CAPTURE:
			board=new int[][]{
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 1, 2, 2, 2, 1, 1, 0},
				{0, 1, 2, 0, 2, 1, 1, 0},
				{0, 1, 2, 2, 2, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
				break;
				
		case TEST_CAPTURE2:
			board=new int[][]{
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 2, 2, 2, 1, 2, 1, 1},
				{1, 2, 2, 2, 2, 2, 1, 1},
				{1, 2, 2, 0, 2, 2, 1, 1},
				{1, 2, 2, 2, 2, 1, 1, 1},
				{1, 2, 1, 2, 2, 2, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1}};
				break;
                
            case ARROW:
                board=new int[][]{
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0},
                {0, 1, 0, 1, 1, 0, 1, 0},
                {1, 0, 0, 1, 1, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0}};
                break;
                
		default:
			board = new int[][]{
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 2, 1, 0, 0, 0},
				{0, 0, 0, 1, 2, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}};
		}	
	}
    
    
	/**This method will return the value of a given square
	 * 
	 * @param row represents the row
	 * @param col represents the column
	 * @return value of the square
	 */
	public int getSquare(int row, int col)
	{
		
		return board[row][col];

	}
	

    //
	/**THis method will determine if the player can make a valid move
	 * 
	 * @param row represents the row
	 * @param col represents the column
	 * @param player represents the player type
	 * @return true if can move else false
	 */
	public boolean canMove(int row, int col, int player)
	{
		if(player == 0) {
			for(int i = 0; i < 8; i++)
				for(int j = 0; j < 8; j++) {
					if(board[row][col] != EMPTY)
						return false;
				}
		}
	
		
		return true;
        
    }
	
    //The player is trying to move at the specified square.
    //Return the number of chips captured, 0 for an invalid move.
	/**This method will return the number of chips captured 
	 * 
	 * @param row represents the row
	 * @param col represents the column
	 * @param player represents the player type
	 * @return the number of chips captured
	 */
	public int tryMove(int row, int col, int player)
	{
		
		int chipCount = 0;
		return chipCount;
        
    }
        
    
	/**This method determines if there is a valid move for the player
	 * 
	 * @param player represents the player
	 * @return true if valid move else false
	 */
    public boolean moveTest(int player)
    {
		return false;

    }
    
    
    /**This method calculate how many chips this player have on board
     * 
     * @param player represents the player
     * @return chip counts
     */
    public int chipCount(int player)
    {
		int chipCount = 0;
		
		if(player == 1) {
			for(int i = 0; i < 8; i++)
				for(int j = 0; j < 8; j++) {
					if (board[i][j] == 1)
						chipCount++;
				}
		}else if(player == 2) {
			for(int i = 0; i < 8; i++)
				for(int j = 0; j < 8; j++) {
					if (board[i][j] == 2)
						chipCount++;
				}
		}
		
		return chipCount;
    }
		
}
	
