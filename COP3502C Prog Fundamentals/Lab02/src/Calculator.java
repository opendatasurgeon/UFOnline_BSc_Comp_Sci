/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 * Description: Following is a  four-function one-run calculator on the command line. The program will first 
 * 				prompt  the  user  for  two  numbers,  then  display  a  menu  with  five  operations.  It  will  allow  the  user  to  select  an  
 *				option by reading input using a scanner object. The program will then display the result of the operation and exit.
 * 
 */

import java.util.Scanner;

public class Calculator {


	public static void main(String[] args) {
		
		// stores two operands, and the result from arithmetic operations
		double first_operand, second_operand, result;
		
		// holds value for calculator menu choice
		int calc_menu_choice;
		
		Scanner operand_scan = new Scanner(System.in);
		
		// these get the first two operands
		System.out.print("Enter first operand: ");
		first_operand = operand_scan.nextDouble();
		
		System.out.print("Enter second operand: ");
		second_operand = operand_scan.nextDouble();
		
		// menu layout
		System.out.print("\nCalculator Menu\n"+
						"---------------\n" +
						"1. Addition\n" +
						"2. Subtraction\n" + 
						"3. Multiplication\n" +
						"4. Division\n\n");
	
		// gets the operation instruction
		System.out.print("Which operation do you want to perform? ");
		calc_menu_choice = operand_scan.nextInt();
		
		System.out.println();
		
		//Switch statement to compute the operation and show the result
		switch(calc_menu_choice){
		case 1:
			result = first_operand + second_operand;
			System.out.println("The result of the operation is " + result + ". Goodbye!");
			break;
		case 2:
			result = first_operand - second_operand;
			System.out.println("The result of the operation is " + result + ". Goodbye!");
			break;
		case 3:
			result = first_operand * second_operand;
			System.out.println("The result of the operation is " + result + ". Goodbye!");
			break;
		case 4:
			result = first_operand / second_operand;
			System.out.println("The result of the operation is " + result + ". Goodbye!");
			break;
		default: // default case is here in case incorrect choice gets picked
			System.out.println("Error: Invalid selection! Terminating program.");
		}
		
		//closes the scanner when done
		operand_scan.close();
	}

}
