/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 * Description: This lab is designed to introduce us to the Bash Command Line Interface (CLI) and the concept of CLI 
 *				arguments and give us practice writing classes. For this lab, we are recommended to use a Unix-based 
 *			    command line editor. The cowsay utility is a popular Unix program from the 20th century (see https://en.wikipedia.org/wiki/Cowsay). 
 *				We will write a slightly simplified cowsay program that takes in several arguments and prints out different text 
 *				depending on the arguments.
 */

import java.util.*;

public class Cowsay {

	
	// Displays the list of available cows from an array of Cow objects.
	private static void listCows(Cow[] cows) {
		
		for(Cow c : cows) {
			System.out.print(c.getName() + " ");
		}
		
		System.out.println();
	}
	
	
	// Given a name and an array of Cow objects, return the Cow object with the specified name.
	private static Cow findCow(String name, Cow[] cows) {
		
		for(Cow c : cows) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		
		return null;
		
	}
	
	
	// Check if out of bounds
	public static boolean outOfBoundsCheck(String[] args_arr, int index) {
		
		try {
			
			String i = args_arr[index];
			
		} catch (Exception e) {
			
			return false;
			
		}
	
		return true;
	}
	
	public static void main(String[] args) {
	
		// gets all the cows from HeiferGenerator class
		Cow[] cows = HeiferGenerator.getCows();
		
		//cheeck if the argumes are empty
		if(args.length <= 0) {
			
			// put default cow
			System.out.println("No arguments were passed!");
			
		// check if arguments at 0 exists and if it starts with -n and the size is great is larger than 1
		}else if(outOfBoundsCheck(args, 0) && args[0].equals("-n") && args.length >= 1) {
			
			// check if there is a argument at 1
			if(outOfBoundsCheck(args, 1)) {
				
				//find the cow in cows type array
				Cow cow = findCow(args[1], cows);
				
				//if the cow doesn't exist, say it
				if(cow == null) {
					System.out.println("Could not find "+ args[1] +" cow!");
				}
				//check if the argument at 1 equals the two known cows
				else { // if((args[1].equals("kitteh") || args[1].equals("heifer"))) {
										
					// if there are more cows, check if the one more index exist
					if(args.length >= 2 && outOfBoundsCheck(args, 2)) {
						
						// append the messages and show above the cow image
						String message = ""; 
						
						for(int i = 2; i < args.length; i++) {
							message += " " + args[i];
						}
						
						System.out.println(message);
						System.out.println(cow.getImage());
						
						//check if the cow object is an instance of two subclasses
						if(cow instanceof IceDragon || cow instanceof Dragon) {
							
							//check if the cow is a an object of Dragon
							if(((Dragon) cow).canBreatheFire()) {
								
								// if it's a fiery dragon print the message
								System.out.println("This dragon can breathe fire.");
								
							}else {
								
								System.out.println("This dragon cannot breathe fire.");
								
							}	
						}
					
					}
				
				}
					
			}
		
		//check if the argument at 0 is -l
		} else if(outOfBoundsCheck(args, 0) && args[0].equals("-l") && args.length == 1) {
			
			//show list of cows
			System.out.print("Cows available: ");
			listCows(cows);
			
		}else {
			
			// if no cows were inputed , show default cow image with the message
			Cow cow = findCow("heifer", cows);
			
			if(outOfBoundsCheck(args, 0)) {
				
				String message = ""; 
				
				for(int i = 0; i < args.length; i++) {
					message += " " + args[i];
				}
				
				System.out.println(message);
				System.out.println(cow.getImage());
				
			}
			
			
		}
		
	
		return;
	}
	

}
