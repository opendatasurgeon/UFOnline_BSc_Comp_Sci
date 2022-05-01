/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 * Description: This project is a practice of 2-D arrays by recreating one of okay childhood games: Connect-Four. 
 * 				The project loop through arrays, and manipulate them- without a single ArrayIndexOutOfBounds Exception!
 * 
 */

import java.lang.*;
import java.util.*;

public class ConnectFour {
	
	//This will print the board. Backwards.

	public static void printBoard(char[][] array) {
		
		//start from last row and decrement
		for (int i = array.length-1; i >= 0; i--) {
			
			//unless i equals to row count, keep inserting line break
			if(i !=  array.length-1) {
				System.out.print("\n");
			}
			
			// when looping incrementally through columns, add spaces to prints
			for (int j = 0; j < array[i].length ; j++) {
				
				System.out.print(array[i][j] + " ");
			}
			
		}	
		System.out.print("\n");
		

	}
	
	//This will set each spot in the array to "-"
	
	public static void initializeBoard(char[][] array) {
		
		// set all the cells to - by loopinh through array of arrays
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				
				array[i][j] = '-';
			}
			
		}
	}
	
	//Places the token in the column that the user has chosen. Will find the next available spot in that column if there are already 
	//tokens there. The row that the token is placed in is returned.

	public static int insertChip(char[][] array, int col, char chipType) {
		
		int row_empty = -1; //variable that store valid/invalid rows
		boolean do_looping = true; //if - exists in chipped column, stop loop
		
		// only loop through rows/height since it will be 1D array insert
		for(int i = 0; i < array.length && do_looping; ) {
			
			if(array[i][col] == '-') {
				
				array[i][col]= chipType;
				row_empty = i; // return row where chip got inserted
				do_looping = false;
				
			}
			else {
				i++; //increment loop if x or o present
			}
			
		}
		
		
		return row_empty;
	}
	
	//After a token is added, checks whether the token in this location, of the specified chip type, creates four in a row. Will 
	//return true if someone won, and false otherwise
	
	public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
		
		
		/**********************************************************************/
		//Override the rows and columns so that we can pass Zybooks Test case 9
		row = array.length;
		col = array[0].length;
		/********************************************************************/
		
		// checks 2d grid horizontally
		// We only increment through rows by 1, but columns by previous j+1 ; that way same sets/neighbors can be found row wise
		// < col-3 to prevent outer loop causing out of bounds when iterating through array width
		for(int i = 0; i < row ; ++i) { 
			for(int j = 0; j < col-3 ; ++j) { 
				
				if(array[i][j]== chipType &&
				   array[i][j+1] == chipType &&
				   array[i][j+2] == chipType &&
				   array[i][j+3] == chipType) {
					
					return true; // if found return true
					
				}
			}
		}
		
		// checks 2d grid vertically
		// We only increment through columns by 1, but rows by previous j+1 ; that way same sets/neighbors can be found column wise
		// < row-3 to prevent outer loop causing out of bounds when iterating through array height
		for(int i = 0; i < row-3; ++i) {
			for(int j = 0; j < col; ++j) {
				
				if(array[i][j]== chipType &&
				   array[i+1][j] == chipType &&
				   array[i+2][j] == chipType &&
				   array[i+3][j] == chipType) {
					
					return true;
					
				}
			}
		}
		
		
		return false; // return false if same tokens (vertically/horizontally) are not present
	}
	
	// This function check if the game is tied. Simply check the last row in the 2d array
	
	public static boolean checkIfTie(char[][] array) {
		
		// run through last row and check if - exists, if it does return true
		for(int i = 0; i < array[0].length; ++i) {
			
			if(array[array.length-1][i] == '-') {
				return false;
			}
			continue;
		}
		return true;
	}

	//main
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		
		boolean goofer_1 = true; // variables that loop player 1 during invalid input
		boolean goofer_2 = true; // variables that loop player 2 during invalid input
		
		int row = 0; // array height
		int column = 0; // array width
		
		char player_1_mark = 'x'; 
		char player_2_mark = 'o';
		
		int player_1_place = 0; //player 1s input
		int player_2_place = 0; // player 2s input
		
		int chip_row = 0; //made it to check if the players_place returned a valid row
		
		char[][] board = null;
		
		
		System.out.print("What would you like the height of the board to be? ");
		row = scnr.nextInt();
		
		System.out.print("What would you like the length of the board to be? ");
		column = scnr.nextInt();
		
		if(row < 4 || column < 4) {
			System.out.print("\nPretty cute. It's a Connect FOUR game. See ya.\n");
			scnr.close();
			return;
		}
		
		board = new char[row][column]; // makes the board
		
		initializeBoard(board); // fills the board i -
		
		printBoard(board); //prints the board
		
		System.out.println("\nPlayer 1: x \nPlayer 2: o");

		//loop that controls the game
		while(true) {
			
			//player 1 loop
			while(goofer_1) {
				
				System.out.print("\nPlayer 1: Which column would you like to choose? ");
				player_1_place = scnr.nextInt();
				
				// get input and check if the input is out of bounds
				if(player_1_place < 0 || player_1_place >= board[0].length) {
					
					chip_row = -1; // if it is, make the chip invalid
					
				}else {
					
					// otherwise put the player's chip in the 2d grid
					chip_row = insertChip(board, player_1_place, player_1_mark);
					
					printBoard(board);
				}
			
				//if invalid chip, loop  player 1
				if(chip_row == -1) {
					
					goofer_1 = true;
					System.out.print("\nPlayer 1: Particular column is full/invalid. Pick another.\n");				
					
				}else {
					
					goofer_1 = false; // if valid row was returned, exit player 1's loop
					goofer_2 = true; // and open loop for player 2
					
				}
				
			}

			// check if the last entry was a winner. Pass in arary height, width, player 1 mark
			if(checkIfWinner(board, column, row, player_1_mark)){
				
				System.out.println("\nPlayer 1 won the game!");
				break; // exit if winner
				
			}
					
			System.out.println();
			
			// player 2s loop (idea is same as above)
			while(goofer_2) {
				
	
				System.out.print("Player 2: Which column would you like to choose? ");
				player_2_place = scnr.nextInt();
				
				if(player_2_place < 0 || player_2_place >= board[0].length) {

					chip_row = -1;
					
				}else {
					
					chip_row = insertChip(board, player_2_place, player_2_mark);
					
					printBoard(board);
					

				}
			

				if(chip_row == -1) {
					
					goofer_2 = true;
					System.out.print("\nPlayer 2: Particular column is full/invalid. Pick another.\n");				

				}else {
					
					goofer_2 = false; //if valid row was returned, exit loop for player 2
					goofer_1 = true; //open loop 1 for player 1
					
				}
				
				
			}
			
			
			if(checkIfWinner(board,  column, row, player_2_mark)){
				
				System.out.println("\nPlayer 2 won the game!");
				break;
				
			}
			
			// check at the end if the board's last row has no -
			if(checkIfTie(board)) {
				
				System.out.println("\nDraw. Nobody wins.");
				break;
				
			}
			
		}
		
		//??????????????????????????????????????????????????
//		// Somehow Test 9 fails in zybooks.
//		char board[][] = {  {'-', '-', '-', '-', '-'}, 
//							{'-', '-', '-', '-', '-'},
//							{'-', '-', '-', '-', '-'},
//							{'-', '-', '-', '-', '-'},
//							{'-', '-', '-', '-', '-'},
//							{'-', '-', '-', '-', '-'},
//							{'o', '-', '-', '-', '-'},
//							{'o', '-', '-', '-', '-'},
//							{'o', '-', '-', '-', '-'},
//							{'o', '-', '-', '-', '-'}   };
//		
//		System.out.print(checkIfWinner(board, board[0].length-1, board.length-1, 'o'));
		
		
		scnr.close();
		return;
	}

}
