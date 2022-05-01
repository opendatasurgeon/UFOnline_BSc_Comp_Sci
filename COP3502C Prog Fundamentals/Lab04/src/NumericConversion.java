import java.lang.*;
import java.util.Scanner;

/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 * Description: This project is designed to get practice with loops and the Java String class, characters, and arithmetic. 
 *					This program will take a hexadecimal or binary number string as an input and print out its decimal value. 
 * 
 */


public class NumericConversion {
	
	// made it to print the menu
	public static void DecodingMenu() {
		
		System.out.println("Decoding Menu\n"
						+ "-------------\n"
						+ "1. Decode hexadecimal\n"
						+ "2. Decode binary\n"
						+ "3. Convert binary to hexadecimal\n"
						+ "4. Quit");
	}

	// Decodes a single hexadecimal digit and returns its value
	public static short hexCharDecode(char digit) { 
		
		short result = 0; // for storing the output
		
		char digitChar = 0; // for manipulating the input
		
		if(Character.isUpperCase(digit)) { // if uppercase, leave digit as is
			
			digitChar =  digit;
			
		}else {
			
			digitChar = formatDecodeCharacterInput(digit); //other wise invoke a function, and make it uppercase
			
		}
		
		// subtracts char value (i.e. A = 97, B= 98 etc) and gives decimal range of 10-15
		if((int) digitChar >= (int)'A' && (int) digitChar <= (int)'F') {
			
			result = (short) (10 + (int) digitChar - (int)'A');
			
		}else {
			// if between 0-9, just return the number
			result = (short) ((int) digitChar - (int)'0');
			
		}
		
		return result;
	}

	//Decodes a single decimal digit and returns its hex value

	public static String charHexDecode(int digit) { 
		
		// this is an alternate route I took to get the Hex values from an array
		String [] decTohexArrayString = {"A", "B", "C", "D", "E", "F"};
		
		String result = ""; // stores and returns a string value
			
		// here I check if the digit is in higher hex range, if yes, subtract ten and get the value with index position
		if(digit >= 10 && digit <= 15) {
			
			result = decTohexArrayString[(digit - 10)];
			
		}else {
			
			// otherwise just convert the int to string
			result = String.valueOf(digit);
			
		}
		
		return result;
	}
	
	//Decodes an entire hexadecimal string and returns its value
	public static long hexStringDecode(String hex) { 
			
		long result = 0;
		
		// first make the string to uppercase and remove 0b or 0x
		String hexString = formatDecodeStringInput(hex);
		
		// start i at a higher value so we can read the string characters backwards
		// however, increment exponent since character at righmost position has the nk*e^n....n1*e^1 + n0*e^0 (where k is an integer)
		for(int i = (hexString.length() - 1), exponent = 0; i >= 0; --i , ++exponent) {
			
			// here we want to get the decimal value of each hex character and multiply it by 16 times an exponent
			result += (long) hexCharDecode(hexString.charAt(i)) * (Math.pow(16, exponent));
			
		}
		
		return result; // return the result
			
	}
	
	//Decodes a binary string and returns its value
	public static short binaryStringDecode(String binary) { 
		
		short result = 0;
		
		// get an uppercase string with 0b or 0x removed
		String binaryString = formatDecodeStringInput(binary);
		
		// start i at a higher value so we can read the string characters backwards
		// however, increment exponent since character at righmost position has the nk*e^n....n1*e^1 + n0*e^0 (where k is an integer)

		for(int i = (binaryString.length() - 1), exponent = 0; i >= 0; --i , ++exponent) {
			
			// here we want to get the decimal value of each binary character and multiply it by 2 times an exponent
			result += (short) Character.getNumericValue(binaryString.charAt(i)) * (Math.pow(2, exponent));
		
		}
		
		return result; // return the result
	}
	
	//Decodes a binary string, re-encodees it as hexadecimal, and returns the hexadecimal string
	public static String binaryToHex (String binary) { 
		
		String result = "";
		
		// we first make the string uppercase, remove 0b or 0x then get the decimal value from that binary string (we've made this function above already)
		short decimalFrombinaryStringDecode = binaryStringDecode(formatDecodeStringInput(binary));
				
		// once have the decimal value, check if the quotient is not 0, if it is then decimal is 0 and return that
		while(decimalFrombinaryStringDecode != 0) {
			
			//at each iteration get the remainder by diding 16 from decimal value, and appeand hex value we get backwards so new hex adds at left most position
			result = charHexDecode((decimalFrombinaryStringDecode % 16 )) + result;
			
			// after appending the hex value, divide the decimal value by 16 and update the quotient
			decimalFrombinaryStringDecode = (short) (decimalFrombinaryStringDecode / (short) 16 ) ;
			
		}
		
		return result; // return the hex string

	}
	
	// fix the user input string by removing prefixes and making it uppercase
	public static String formatDecodeStringInput(String str) {
		
		String numericString = str.toUpperCase(); // uppercase
		
		// check if the first 2 characters are 0B, & the string has length higher than or equals to 3 
		if(numericString.length() >= 3 && numericString.substring(0, 2).equals("0B")) {
					
			numericString = numericString.substring(2); // then remove first 2 characters from a prefixed string 
					
		}else if(numericString.length() >= 3 && numericString.substring(0, 2).equals("0X")) { // same as above (& don't convert actual 0b hex without prefix)
					
			numericString = numericString.substring(2); // remove the first 2 character from a prefixed string
					
		}
		
		return numericString; // return properly formatted string
	}
	
	// returns the uppercase of a character
	public static char formatDecodeCharacterInput(char ch) {
			
		return Character.toUpperCase(ch);
	}
	
	// takes a string input and returns it
	public static String takeDecodeStringInput(Scanner menu_input) {
			
		String numericString = ""; 
		
		System.out.print("Please enter the numeric string to convert: ");
		numericString = menu_input.next();
		
		return numericString;
	}
	
	// based on user input, call appropriate decode function and prints the result
	// this function is made so user can invoke whichever function they please with keyboard (than calling it directly)
	public static void stringOperationCaller(int user_choice, Scanner scnr) {
		
		short shortOutput = 0;
		long longOutput = 0;
		String stringOutputString = "";
		
		if(user_choice == 1) {
			
			// get the string, fix it, call the the hex to decimal function
			longOutput = hexStringDecode(formatDecodeStringInput(takeDecodeStringInput(scnr)));
			
			System.out.println("Result: " + longOutput + '\n');
			
		}else if(user_choice == 2) { 
			
			// get the string, fix it, call the the binary to decimal function

			shortOutput = binaryStringDecode(formatDecodeStringInput(takeDecodeStringInput(scnr)));
			
			System.out.println("Result: " + shortOutput + "\n");
			
		}else if(user_choice == 3) { 
			
			// get the string, fix it, call the the binary to hex function

			stringOutputString = binaryToHex(formatDecodeStringInput(takeDecodeStringInput(scnr)));
			
			System.out.println("Result: " + stringOutputString + "\n");
		
		}else{ // dummy catch all
			System.out.println("\n~uh-oh~\n");
		}

		return;
	}
		
	// checks the validity of user inputs
	public static boolean check_input_for_looping(Scanner menu_input) {
		
		int user_choice;
		
		boolean return_val = true;
		
		System.out.print("\nPlease enter an option: ");
		
		user_choice = menu_input.nextInt(); 
		
		// checks if inputs are in range
		if(user_choice < 1 || user_choice > 4) {
			
			System.out.println("\nError: Invalid selection!\n");
			
		}else if(user_choice == 4) { // checks if user wants to quit
			
			return_val = false;	
			
		}else { 
			// other wise call the operation to get an appropriate decoder
			stringOperationCaller(user_choice, menu_input);
			
		}
		
		return return_val;  // return a boolean
	}
	

	public static void main(String[] args) {
		
		Scanner userInputScanner = new Scanner(System.in);
		
		boolean main_loop_cond = true;
		
		// loop that controls the program
		while(main_loop_cond) {
			
			DecodingMenu();
			
			main_loop_cond = check_input_for_looping(userInputScanner); // let's user input and play with the program
			
		}
		
		System.out.println("\nGoodbye!");
		
		userInputScanner.close();
	}

}
