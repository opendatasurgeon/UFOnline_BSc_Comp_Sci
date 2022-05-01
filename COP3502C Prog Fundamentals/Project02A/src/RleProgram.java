/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 * Description: This is a 3 part project. This project develops routines to encode and decode data for images using run-length encoding 
 *				(RLE). Upon completion, project will implement encoding and decoding of raw data, conversion between data and strings, and 
 *				display of information by creating procedures that can be called from within their programs and externally. This
 *				project allows the practice with loops, strings, arrays, methods, and type-casting.
 *
 *				-------------------
 *				~~~ PROJECT 2A:~~~
 *				-------------------
 *
 *				For part A of this assignment, there is a set up of a standalone menu alongside the 4 requirements listed on page 2 and 4 of the document in the Folder 
 *				(P2 - Image RLE.pdf). Project has three RLE functionalities. There is options 1 (loading an image), 2 (loading specifically the test image), and 6 
 *				(displaying whatever image was loaded) in order to help grasp the bigger picture of the project.
 *
 *				This part of the projects uses ConsoleGfx.java file and utilizes its methods. We use ConsoleGfx.displayImage(…) to display images.
 * 				It takes in a decoded array of bytes. This is the format in which we locally store any image data that you are working with. 
 * 				“loaded”  means that something is stored as an array of flat (decoded) bytes.
 * 
 */

import java.util.*;
import java.lang.*;

public class RleProgram {
	
	// prints the welcome message
	public static void printWelcomeMessage() {
		
		System.out.println("Welcome to the RLE image encoder!\n");
		
		return;
	}
	
	//prints menu
	public static void printRLEMenu() {
		
		System.out.println("\n\nRLE Menu\n"
							+"--------\n"
							+ "0. Exit\n"
							+ "1. Load File\n"
							+ "2. Load Test Image\n"
							+ "3. Read RLE String\n"
							+ "4. Read RLE Hex String\n"
							+ "5. Read Data Hex String\n"
							+ "6. Display Image\n"
							+ "7. Display RLE String\n"
							+ "8. Display Hex RLE Data\n"
							+ "9. Display Hex Flat Data");
		
		return;
	}
	
	// main
    public static void main(String[] args) {
        
    	boolean continue_loop = true; // control variable for loop
    	int user_choice = -1; // store user menu selections
		String image_path = ""; // stores image paths
		byte[] imageData = null; // captures the array return by the ConsoleGfx.java file
		
    	Scanner input_scnr = new Scanner(System.in);
    	
    	// 1. Display welcome message
    	
    	printWelcomeMessage();
    	
        // 2. Display color test with the message 
    	
    	System.out.println("Displaying Spectrum Image:");
    	
        ConsoleGfx.displayImage(ConsoleGfx.testRainbow);
        
        

        // 3. Display the menu
        //- Part A: while loop or if-else chains 
        
        while(continue_loop) {
        	
        	printRLEMenu(); //prints RLE menu at every iteration
        	
        	System.out.print("\nSelect a Menu Option: ");
			
			user_choice = input_scnr.nextInt(); // takes int based user input
			
			// option 1, option 2 and option 6
			
			// check for invalid input and returns error message if invalid input is there
			if(user_choice < 0 || user_choice > 9) {
								
				System.out.print("Error! Invalid input.");
												
			}else if(user_choice == 0) {
				
				continue_loop = false;	// exits the program
				
			}else if(user_choice == 1) {
				
				 // 3.1 - option 1
	            // ConsoleGfx.loadFile(userInput) and stores the returned byte[] into imageData array
				
				System.out.print("Enter name of file to load: ");
				image_path = input_scnr.next(); // takes the string input for file name
				
				imageData = ConsoleGfx.loadFile(image_path);
				
			}else if(user_choice == 2) {
				
				// 3.2 - option 2
	            // store ConsoleGfx.testImage into the imageData array
				
				System.out.println("Test image data loaded.");
				
				imageData = ConsoleGfx.testImage; // stores the testImage array into imageData array
				
				
			}else if(user_choice == 3) {
				
				// since 3,4,5,7,8,9 weren't part of this project showing them as failed
				System.out.print("Reading RLE String: (Reading failed)");
				
				
			}else if(user_choice == 4) {
				
				System.out.print("Reading RLE Hex String: (Reading failed)");
				
				
			}else if(user_choice == 5) {
				
				System.out.print("Reading Data Hex String: (Reading failed)");
				
				
			}else if(user_choice == 6) {
				
				// 3.6 - option 6
	            // display image stored inside of imageData array
				
				// checks if the imageData array is null or has length 0, if yes, show error message
				if(imageData == null || imageData.length == 0) {
					
					System.out.print("Displaying image...\n"
										+ "(no data)");
					
				}
				else {
					
					System.out.println("Displaying image...");
					
					ConsoleGfx.displayImage(imageData); // show image on the console otherwise
				}
				
			}else if(user_choice == 7) {
				
				System.out.print("RLE representation: (no data)");
				
			}else if(user_choice == 8) {
				
				System.out.print("Hex RLE representation: (no data)");
				
			}else if(user_choice == 9) {
				
				System.out.print("Flat hex values: (no data)");
				
			}
			
        }
        // close the scanner upon program's completion
    	System.out.println("Thanks for using RLE image encoder. Good bye!");
    	input_scnr.close();
    }
}
