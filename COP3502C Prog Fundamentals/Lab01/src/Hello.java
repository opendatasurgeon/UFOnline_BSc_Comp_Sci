/**
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 * Description: Following is a small program to ask several questions to a user, and displaying their answer each time.
 * 
 */
import java.util.Scanner; // Imports scanner package

// Hello class containing the Lab01 program
public class Hello {

	public static void main(String[] args) {
		
		//Initiates scanner object to gt keyboard inputs
		Scanner scnr = new Scanner(System.in);//.useDelimiter(" "); 
		
		String userName = ""; //Variable that stores user's name
		String userAge = ""; //Stores given age of the user
		String userFrom = ""; //Holds where user lives
		
		//Prompts user to get some inputs
		System.out.print("Hello. What is your name? "); 
		userName = scnr.next(); //Get appropriate inputs and store them in their proper data types
		
		
		System.out.print("It's nice to meet you, " +  userName + ". How old are you? ");
		userAge = scnr.next();
		
		System.out.println("I see that you are still quite young at only " + userAge + "."); 
		
		
		System.out.print("Where do you live? "); 
		userFrom = scnr.next();
		
		System.out.print("Wow! I've always wanted to go to " + userFrom + ". Thanks for chatting with me. Bye!"); 
				 
		
	}

}
