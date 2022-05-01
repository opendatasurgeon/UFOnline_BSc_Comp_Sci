/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 * Description: This project is practice for building and working with object-oriented programming 
 *				constructs including classes and objects by building classes to represent creatures and a cataloguing system.
 */

import java.util.*;
import java.lang.*;


public class PakuriProgram {

	//Welcome message
	public static void pakudexMenuHeader() {
		System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");
	}
	
	// Good bye message
	public static void pakudexMenuFooter() {
		System.out.println("Thanks for using Pakudex! Bye!");
	}
	
	// Menu
	public static void pakudexMenu() {
		System.out.println("\nPakudex Main Menu\n"
							+ "-----------------\n"
							+ "1. List Pakuri\n"
							+ "2. Show Pakuri\n"
							+ "3. Add Pakuri\n"
							+ "4. Evolve Pakuri\n"
							+ "5. Sort Pakuri\n"
							+ "6. Exit\n");
	}
	
	
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		
		int pakudexCapacity = 0; // array capacity
		int menuChoice = 0; // menu option
		
		String species_nameString = null; // gets the animal input
		String[] species_string_arrayStrings = null; // gets the list of animals
		int[] stats_array = null; // stores the animal stats
		
		pakudexMenuHeader();
		
		while(true) {
			
			// try block to test if the input is okay. On okay input loop break, otherwise catch with message and loop back
			try {
				
				System.out.print("Enter max capacity of the Pakudex: ");
				pakudexCapacity = Integer. parseInt(scnr.next());
				
				if(pakudexCapacity <= 0) {
					
					System.out.println("Please enter a valid size.");
					
				}
				else {
					
					System.out.println("The Pakudex can hold " + pakudexCapacity + " species of Pakuri.");
					break;
					
				}
				
			}catch (Exception e) {
				
				System.out.println("Please enter a valid size.");
//				scnr.nextLine(); // clear input buffer
//				if(scnr.hasNextLine()) {
//					scnr.nextLine();
//				}
				
			}
			
		}
		
		// when the size input is correct, put the capacity
		Pakudex pakudex = new Pakudex(pakudexCapacity);
		

		
		while(true) {
			
			pakudexMenu();
			
			// try block to test if the string inpuit is okay. On okay input loop continues to ask correct input

			try {
			
				System.out.print("What would you like to do? ");
				menuChoice = Integer. parseInt(scnr.next());
				
				// on number input outside the range
				if(menuChoice <= 0 || menuChoice > 6) {
					
					System.out.println("Unrecognized menu selection!");
					
				}else if (menuChoice == 1){
					
					// get array, check its length and print
					species_string_arrayStrings = pakudex.getSpeciesArray();
					
					if(species_string_arrayStrings == null || species_string_arrayStrings.length == 0) {
						
						System.out.println("No Pakuri in Pakudex yet!");
					}
					else {
						int i = 1;
						System.out.println("Pakuri In Pakudex: ");
						
						for(String s : species_string_arrayStrings) {
							
							System.out.println(i + ". " + s);
							i++;
							
						}
					}
					
										
				}else if (menuChoice == 2){
					
					System.out.print("Enter the name of the species to display: ");
					species_nameString = scnr.next();
					
					// get array, check its length and print
					stats_array = pakudex.getStats(species_nameString);
					
					if(stats_array == null || stats_array.length == 0) {
						
						System.out.println("Error: No such Pakuri!");

					}else {
						
						System.out.println("\nSpecies: " +  species_nameString + "\n"
										+ "Attack: " +  stats_array[0] + "\n"
										+ "Defense: " +  stats_array[1] + "\n"
										+ "Speed: " +  stats_array[2]);
						
					}
					

					
				}else if (menuChoice == 3){
						
					// check if elements can be added. If can, add them as a Pakuri object into Pakudex arraylist
						if(pakudex.getSize() == pakudex.getCapacity()) {
						
						System.out.println("Error: Pakudex is full!");
						
						}
						else {
							
							System.out.print("Enter the name of the species to add: ");
							species_nameString = scnr.next();
							
							if(pakudex.addPakuri(species_nameString)) {
								
								System.out.println("Pakuri species " + species_nameString + " successfully added!");
								
							}else {
								
								System.out.println("Error: Pakudex already contains this species!");
								
							}
							
						}
					
				}else if (menuChoice == 4){
					
					// evolve the species if the species exist
					System.out.print("Enter the name of the species to evolve: ");
					species_nameString = scnr.next();
					
					if(pakudex.evolveSpecies(species_nameString)) {
						System.out.println(species_nameString + " has evolved!");
					}else {
						System.out.println("Error: No such Pakuri!");
					}
					
				}else if (menuChoice == 5){
					
					// just call the method to sort the arraylist
					System.out.println("Pakuri have been sorted!");
					
					pakudex.sortPakuri();
		
				}else if (menuChoice == 6){
					
					pakudexMenuFooter();
					break;
					
				}
				
			}
			catch (Exception e) {
				
				System.out.println("Unrecognized menu selection!");
//				scnr.nextLine(); // clear input buffer
//				if(scnr.hasNextLine()) {
//					scnr.nextLine();
//				}
				
			}
						
		}
		
		
		scnr.close();
		
		return;
	}

	

}
