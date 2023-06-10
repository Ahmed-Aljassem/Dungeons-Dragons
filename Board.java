package dd;

public class Board {
	private char [][] board;
	private int rows, columns;

	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		initializeBoard();
	}
	public void initializeBoard() {
		/*
		 * this part of the code is creating the board in memory 
		 * and saving it into the computer
		 */
		board = new char[this.rows][this.columns]; 
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.columns; j++) {
				this.board[i][j] = ' ';
			}
		}

	}
	public void displayBoard() {
		String horizontalBar = "|";
		String mainLine = "|";
		/*
		 * the for loop below will concatenate how many columns we need
		 * if there are 10  boxes it will create 10 columns
		 */
		for(int i = 0; i < this.columns - 1; i++) {
			mainLine += "===+";

		}
		mainLine += "===|";
		System.out.println(mainLine);
		/*
		 * this code will print all the information in the array
		 * first we will print all the information then
		 * we will print the horizontal board and the lines between the rows
		 */
		for(int i = 0; i < this.rows; i ++) {
			for(int j = 0; j < this.columns; j ++) {
				/*
				 * print information from the board
				 */
				System.out.print(horizontalBar + " " + this.board[i][j] + " ");
			}
			System.out.print(horizontalBar);
			System.out.println();
			System.out.println(mainLine);
		}
		System.out.println();
	}
	public void markBoard(int y, int x, char symbol) {
		/*
		 * this code will mark the board no matter what
		 * it does not care if it is a valid spot or not
		 */
		this.board[y][x] = symbol;
	}

}
