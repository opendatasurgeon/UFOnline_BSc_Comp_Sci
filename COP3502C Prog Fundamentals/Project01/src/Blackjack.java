/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 * Description: Following project  ill simulate a simplified version of the game Blackjack. Project implements random 
 *				card drawing, calculation, state tracking, and console input systems. The project is designed to get a 
 *				practical opportunity to practice data types, console I/O, control structures, and libraries. 
 * 
 */

import java.util.Scanner;
import java.lang.String;

public class Blackjack {

	public static void main(String[] args) {
		
		// define variables for game related tracking
		int game_counter, blackjack_menu_choice, tie_counter = 0, dealer_win_counter = 0, player_win_counter = 0, card_value, player_hand=0;
		boolean new_game_loop = true, current_game_loop, error_or_stat_loop;
		String card = "";
		
		
		// generate random numbers using the pre-made class file
		P1Random randomGen = new P1Random();
		
		// invoke scanner object for keyboard input
		Scanner user_input = new Scanner(System.in);
		
		
		// Here outer loop continues  until the user hits 4 in the keyboard. Loop breaks with a false boolean value which we assign within proper conditional block
		for(game_counter = 1; new_game_loop; game_counter++) {
			
			System.out.println("START GAME #" +  game_counter + "\n");
			
			// this boolean variable is needed for outer while loop in new games or when the user chooses to make a hit
			current_game_loop = true;
			
			while(current_game_loop) {
				
				// this boolean variable 
				error_or_stat_loop = true;
				
				// generate random values between 1-13 (inclusive) here
				card_value = randomGen.nextInt(13)+ 1;	
				
				//assign card values and update the hand
				if(card_value == 1) {
					card = "ACE!";
					player_hand += card_value;
				}else if (card_value >= 2 && card_value <= 10) {
					card =  String.valueOf(card_value) + "!";
					player_hand += card_value;
				}else if(card_value == 11) {
					card = "JACK!";
					player_hand += 10;
				}else if(card_value == 12) {
					card = "QUEEN!";
					player_hand += 10;
				}else if(card_value == 13) {
					card = "KING!";
					player_hand += 10;
				}
				
				// display string value and hand (total points)
				System.out.println("Your card is a " +  card);
				System.out.println("Your hand is: " +  player_hand + "\n");					
				
				// Because we draw the card at the beginning of the game automatically, we should check players status before the dealer
				
				if(player_hand >= 21) {
					// if players hand adds up 21 or above, declare them a winner and reset the loops and their hand
					switch(player_hand) {
					case 21:
						
						System.out.println("BLACKJACK! You win!\n");
						
						player_hand = 0;
						current_game_loop = false;
						error_or_stat_loop = false;
						++player_win_counter; // up players win count
						break;
						
					default: 
						// if the player hand goes above 21, up dealers hand and reset players hand
						System.out.println("You exceeded 21! You lose.\n");
						player_hand = 0;
						current_game_loop = false;
						error_or_stat_loop = false;
						++dealer_win_counter;
						break;
					}

				}
				
				// inner most loop tracks and maintain the game's state
				while(error_or_stat_loop) {
					
					// we set the inner loop to false immediately so that loop runs once, unless the user doesn't want the stats or enters invalid input  
					error_or_stat_loop = false;
					
					// menu layout
					System.out.print("1. Get another card\n" +
									 "2. Hold hand\n" + 
									 "3. Print statistics\n" +
									 "4. Exit\n\n");
				
					// gets the player instruction
					System.out.print("Choose an option: ");
					blackjack_menu_choice = user_input.nextInt();
					
					System.out.println();					
					
					// Begin by checking the inputs validity
					if (blackjack_menu_choice <= 0 || blackjack_menu_choice >= 5) {
						
						// if invalid, print the message and re-run the inner most while loop
						System.out.println("Invalid input!");
						System.out.println("Please enter an integer value between 1 and 4.\n");
						
						error_or_stat_loop = true;
						
					} else if(blackjack_menu_choice != 4) {
											
						if(blackjack_menu_choice == 1) {
												
							continue; // this block continues if player chooses to hit
							
						}else if(blackjack_menu_choice == 2) {
							// this block runs on hold
							
							// we again generate a random number for a dealers hand
							int dealer_hand = randomGen.nextInt(11) + 16;	
							
							// print dealers hand value and check how it compares to players hand
							System.out.println("Dealer's hand: " +  dealer_hand);
							System.out.println("Your hand is: " +  player_hand + "\n");
							
								if(dealer_hand > player_hand && dealer_hand <= 21) {
									
									System.out.println("Dealer wins!\n");
									++dealer_win_counter; // count up if the dealer has better hand
									
								} else if (dealer_hand == player_hand) {
									
									System.out.println("It's a tie! No one wins!\n");
									++tie_counter; // count towards tie if both hands are same
									
								} else if (dealer_hand > 21) {
									
									System.out.println("You win!\n");
									++player_win_counter; // count up for player if the dealer has hand > 21
								}
							
							player_hand = dealer_hand = 0; // we reset the total for both dealer and the player in conclusion
							current_game_loop = false; // And set the outer while loop to false to indicate for loop to run again
							
						}else if(blackjack_menu_choice == 3) {
							
							// This block prints stat at any point in the game
							System.out.println("Number of Player wins: " +  player_win_counter);
							System.out.println("Number of Dealer wins: " +  dealer_win_counter);
							System.out.println("Number of tie games: " +  tie_counter);
							System.out.println("Total # of games played is: " +  (game_counter - 1 ));
							System.out.println("Percentage of Player wins: " +  (Math.round(player_win_counter * 100.0) / (double)(game_counter-1)) + "%\n");
							
							error_or_stat_loop = true; //since the stat was printed, we want to repeat the menu again without a tally
						}
						
					} else {
						// this block sets all the loop boolean conditions to false so the program can exit
						new_game_loop = false;
						current_game_loop = false;
					}
					
				}
				
			}
			
		}
		
		// close the scanner
		user_input.close();
	}

}
