/*	
 * ConnectFour.java
 * This file includes a program for the game Connect four: a 2 player game involving a Yellow:Y player and Red:R player
 * where they compete with each other in order to have a sequence of 4 Y's or R's respectively either horizontally, vertically
 * or diagonally to win the game.
 *  
 * 	Sanskar Lamsal
 * 	EECS 1510 - Intro to Object Oriented
 * 	Dr. Joseph Hobbs
 */
import java.util.Scanner;
/*
 * Class ConnectFour is responsible for running the game Connect Four 
 */
public class ConnectFour {
	
	public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);//defining stdin so that user can send in an input through the console
	char[][] board = new char[6][7]; //defining an array of 6 rows and 7 columns
	reset (board);//This returns back the board array with all characters converted into Space: ' '
	char color='Y';//defining the initial color as Yellow : Y
	printBoard(board);//Calling the printBoard Method and passing the array board
	boolean k = true;//initialize k to true so that the while loop will initially run
	int column; //column is an integer which will store the value of column that the user inputs later
	
	while (k) {
		if (boardIsFull(board) == true || isWinner(board)==true){//Checks if the board is full or someone has just won the game
			
			if(boardIsFull(board) == true) {
				System.out.println("I declare a draw");//Declaring a draw if the board is full
			}
			System.out.println("DO YOU WANT TO PLAY A NEW GAME? (type 1 for yes)");
			
			int decision = stdin.nextInt();//asking if the user wants to continue playing or not
			
			if (decision == 1) {//If user inputs 1, i.e. Yes, Reset the board, change current player to Y and print the board
				reset(board);
				color = 'Y';
				printBoard(board);
			}
			if(decision == 0) {//If user inputs 0, i.e. No, break the while loop and assign false to k
				k=false;
				break;//breaks the while loop if the player decides to end the game
			}
		}
		
		column = stdin.nextInt();//takes user input for the column they wish to drop either Y or R
		
		if((column >= 0 && column <=6&&(columnIsFull(board,column)==false))) {//
			color = dropDisk(board, column, color);
			printBoard(board);
			if (isWinner(board)==true) {//if someone has won the game
				color = (color=='R')?'Y':'R';//change the color back to the current player
				System.out.println(color + " has WON the game!");//Display that the current player has won
				System.out.println("DO YOU WANT TO PLAY A NEW GAME? (type 1 for yes)");//Ask the user if they want to play a new game
				int decision = stdin.nextInt();//Takes in the user input
				
				if (decision == 1) {//If user inputs 1, i.e. Yes, Reset the board, change current player to Y and print the board
					 reset(board);
					 color = 'Y';
					printBoard(board);
				}
				if(decision == 0) {//If user inputs 0, i.e. No, break the while loop and assign false to k
					k=false;
					break;
				}
			}
		}
		else {//If the user has input a column that is not 0-6 then, display the error message
			System.out.println("YOU ENTERED AN INVALID INTEGER OR THE COLUMN IS FULL.");
			}	
	}
}

	public static void printBoard(char[][] board) {
		/*
		 * Method printBoard prints/updates the board that is to be displayed in the screen
		 * It does not return any value
		 * This creates the user interface
		 */		
		 for(int i = board.length - 1; i >= 0; i--)
		 {
		 System.out.print("| ");
		 for(int j = 0; j <board[i].length; j++)
		 {
		 System.out.print(board[i][j] + " | ");
		 }
		 System.out.println();//prints new line
		 }
	}
	
	public static char dropDisk(char[][] board, int column, char color) {
		/*
		 * Method dropDisk drops the character Y or R into the array after determining the empty space from bottom to
		 * top(Like gravity would do)
		 * 
		 * It also returns back the next character
		 */
		int RowCounter = 0;
				while((board[RowCounter][column] != ' ')&&(RowCounter >= 0 && RowCounter<=5)) {
		//this loop ends exactly when ' ' is detected from bottom so that a charcter can be dropped
			RowCounter++;
		}
		board[RowCounter][column]= color;//drop the current Y or R into the array
        return ((color=='R')?'Y':'R');//returns the next player
	}
	
	public static boolean isWinner(char[][] board) {
		
		/*
		 *This method checks if either of the player has won the game: i.e if anyone has achieved either:
		 * a. 4 character Horizontally 
		 * b. 4 character Vertically
		 * c. 4 character Diagonally 
		 */

		for(int j=0;j<4;j++) {//This loop checks characters Horizontally 
			for(int i=0; i<6; i++) {
				if(board[i][j]!=' ') {
					if((board[i][j]== board[i][j+1]&&(board[i][j]==board[i][j+2])&&board[i][j]==board[i][j+3])) {
						return true;//returns true if the horizontal has 3 consecutive character
					}
				}
			}
		}
		
		for(int j=0; j<7; j++) {//This loop checks characters Vertically
			for(int i=0;i<3;i++) {
				if(board[i][j]!=' ') {
					if((board[i][j]== board[i+1][j]&&(board[i][j]==board[i+2][j])&&board[i][j]==board[i+3][j])) {
						return true;//returns true if the vertical has 3 consecutive character
					}
				}
			}
		}
		
		for(int j=0;j<4;j++) {//this loop checks for character Diagonally from Left to Right up
			for(int i=0; i<3; i++) {
				if(board[i][j]!=' ') {
					if((board[i][j]== board[i+1][j+1]&&(board[i][j]==board[i+2][j+2])&&board[i][j]==board[i+3][j+3])) {
						return true;//returns true if the diagonal has 3 consecutive character
					}
				}
			}
		}
	
		for(int i=0;i<board.length-3; i++) {//this loop checks for character Diagonally from Left to Right down
			for(int j=3;j<board[i].length;j++) {
				if(board[i][j]!=' ') {
					if((board[i][j]== board[i+1][j-1]&&(board[i][j]==board[i+2][j-2])&&board[i][j]==board[i+3][j-3])) {
						return true;//returns true if the diagonal has 3 consecutive character
					}
				}	
			}
		}		
		return false;//returns false if none of the conditions earlier in the method satisfy
	}
	
	public static boolean columnIsFull(char[][] board, int col) {
		/*
		 * Method columnIsFull checks whether the user-input column is full or not
		 * Then, it returns the result as a boolean: True representing Full 
		 */
		for (int i=0;i<6;i++) {
				if (board[i][col] == ' ') {//checks whether if the column has a ' ' or not. ' ' is what we had reset the array to
					return false;
				}
			}
		return true;//returns true if no ' ' is detected in the column
	}
	public static boolean boardIsFull(char[][] board) {
		/*
		 * Method boardIsFull checks whether the whole board is full or not
 		 * Then, it returns the result as a boolean: True representing Full 
		 */
		for (int i=0;i<6;i++) {
			for(int j=0;j<7;j++) {
				if (board[i][j] == ' ') {//checks whether if the board has a ' ' or not. ' ' is what we had reset the array to
					return false;
				}
			}
		}
		return true;//returns true if no ' ' is detected in the board		
	}
	
	public static char[][] reset(char[][] board){
		/*
		 * Method reset assigns all the character in the array to ' ' : A single space character 
		 * This method also returns the array back
		 */
		for (int i=0;i<6;i++) {
			for(int j=0;j<7;j++) {
				board[i][j] = ' ';//assigns ' ' to current board[i][j]
			}
		}
		return board;//returns the board with all character ' '
	}
}