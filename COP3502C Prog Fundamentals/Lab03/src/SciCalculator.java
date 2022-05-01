/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 * Description: This project builds a scientific calculator on the command line. The program will display a menu 
 *				of options which includes several arithmetic operations as well as options to clear the result, display statistics, 
 *				and exit the program. The project is designed to practice looping, type conversion, and data persistence.
 * 
 */

import java.util.Scanner;
import java.lang.*;

public class SciCalculator {
	
	// three public static global vars that help user see calculated outputs, and averages
	static double current_result = 0.0;	
	static int num_of_calc = 0;	
	static double trailing_sum = 0.0;	
	
	//return current result value
	public static double get_current_result() {
		return current_result;
	}
	
	//returns how many valid calculations there are. Averages are not counted toward this valid calc. count
	public static int get_num_of_calc() {
		return num_of_calc;
	}
	
	//return the value of summation so far
	public static double get_trailing_sum() {
		return trailing_sum;
	}
	
	// shows menu upon start and when loop is iterating
	public static void calc_menu(double curr_result_change) {
		
		current_result = curr_result_change; // This gets reset every time
		
		// print current result and print menu
		System.out.println("\nCurrent Result: "+ current_result + "\n"); 
		
		System.out.println("Calculator Menu\n"
						+ "---------------\n"
						+ "0. Exit Program\n"
						+ "1. Addition\n"
						+ "2. Subtraction\n"
						+ "3. Multiplication\n"
						+ "4. Division\n"
						+ "5. Exponentiation\n"
						+ "6. Logarithm\n"
						+ "7. Display Average\n");
		
		return;
	}
	
	// function that does all the calculations based on a valid user input. Takes user input, double first & second operand
	public static void calc_math(int user_choice, double fst_op, double sec_op) {
		
		double curr_result_change= 0.0;
		
		// prints the average, calls trailing sum, number of calculations, and computes their averages
		if(user_choice == 7) {
		
			System.out.println("\nSum of calculations: " + get_trailing_sum());
			System.out.println("Number of calculations: " + get_num_of_calc());
			System.out.printf("Average of calculations: %.2f\n\n", (1.0* (get_trailing_sum() / get_num_of_calc())));
			
			
		}else if(user_choice == 6) { // for log base N to M related calculations
			
			curr_result_change = (Math.log(sec_op) / Math.log(fst_op));  // divide second operands by first
			trailing_sum += curr_result_change; // add the output to the sum
			
			calc_menu(curr_result_change); // call the menu only on calculation related operations
			
		}else if(user_choice == 5) { // computes exponential operations
			
			curr_result_change = Math.pow(fst_op, sec_op);  
			trailing_sum += curr_result_change; 
			
			calc_menu(curr_result_change); 
			
		}else if(user_choice == 4) { // does division operation
			
			// if divide by zero, give message and subtract from number of valid operations
			if((Math.abs(sec_op) < 2 * Double.MIN_VALUE)){
				
			   System.out.println("Can't divide by 0!\n"); 
			   
			   --num_of_calc;
			}
			else{ // otherwise just divide
				
			   curr_result_change = fst_op / sec_op;
			   
			   trailing_sum += curr_result_change; 
			   
			   calc_menu(curr_result_change);  
			}
			
		}else if(user_choice == 3) { // multiplication operation
			
			curr_result_change = fst_op * sec_op;   
			trailing_sum += curr_result_change; 
			
			calc_menu(curr_result_change); 
			
		}else if(user_choice == 2) { // subtraction operation
			
			curr_result_change = fst_op - sec_op;   
		    trailing_sum += curr_result_change; 
			
			calc_menu(curr_result_change);
			
		}
		else if(user_choice == 1) { // addition operation
			
			curr_result_change = fst_op + sec_op; 
			trailing_sum += curr_result_change; 
						
			calc_menu(curr_result_change); 
			
		}else{ // dummy catch all
			System.out.println("~Something Went Wrong~");
		}
		
		return;
	}
	
	
	//gets the first operand from user. We take it as String since they can put RESULT as an input. Take scanner parameter.
	public static double fst_oper_take_input(Scanner menu_input) {
		
		String frst_oprnd; 
		double frst_oprnd_dbl;
		
		System.out.print("Enter first operand: ");
		frst_oprnd = menu_input.next();
		
		//if the user puts result as an operand, call the current result, convert it a double, and set that as first operand
		if(frst_oprnd.toLowerCase().equals("result")) {
			frst_oprnd_dbl = get_current_result();
		}
		else {
			frst_oprnd_dbl = Double.valueOf(frst_oprnd); // if no result, just cast whatever string there is as double
		}
		
		return frst_oprnd_dbl; // return a double value
	}
	
	// gets the second operand from user. We take it as String since they can put RESULT as an input. Take scanner parameter.
	public static double sec_oper_take_input(Scanner menu_input) {
			
		String sec_oprnd;
		double second_oprnd_dbl;
		 
		System.out.print("Enter second operand: ");
		sec_oprnd = menu_input.next();
		
		//if the user puts result as an operand, call the current result, convert it a double, and set that as second operand
		if(sec_oprnd.toLowerCase().equals("result")) {
			second_oprnd_dbl = get_current_result();
		}
		else {
			second_oprnd_dbl = Double.valueOf(sec_oprnd); // if no result, just cast whatever string there is as double
		}
				
		return second_oprnd_dbl; // return a double value
	}
	
	// checks all the input from the user, calls in calculations methods if valid input. Takes scanner parameter
	public static boolean check_input_for_looping(Scanner menu_input) {
		
		int user_choice;
		boolean return_val = true;
		
		System.out.print("Enter Menu Selection: ");
		
		user_choice = menu_input.nextInt(); //take inputs for various math calculations
		
		
		// check valid range
		if(user_choice < 0 || user_choice > 7) {
			
			System.out.println("\nError: Invalid selection!\n");
			
		}else if(user_choice == 7 && (Math.abs(trailing_sum) < 2 * Double.MIN_VALUE)) { //if the average is called when sum is 0.0, we output the message and run loop again
			
			System.out.println("\nError: No calculations yet to average!\n"); 
			
		}else if(user_choice == 0) {
			
			return_val = false;	// return false to end loop
			
		} else if(user_choice == 7) {
			
		    calc_math(user_choice, 0.0, 0.0);	// here user just wants to see the average,so no need to take operand
		    
		}
		else { 
			
			++num_of_calc; // increment number of calculations at each valid menu entry
			
			// then pass in what user picked, call the methods that take both operands, and pass their double casted value
			calc_math(user_choice, fst_oper_take_input(menu_input), sec_oper_take_input(menu_input));  

		}
		
		return return_val; // return appropriate loop var to run/end loop in main 
	}
	

	// main to call all the methods
	public static void main(String[] args) {
		
		// scanner object for keyboard input
		Scanner user_input = new Scanner (System.in);
		
		// allows the initial run of the loop
		boolean main_loop_cond = true;
		
		//call the Calculator menu method, and prints Current result and prints menu
		calc_menu(0.0);
		
		// this loop controls the program, where each run call calculations based on user inputs
		while(main_loop_cond) {
			
			// initially we want to check for invalid loops
			main_loop_cond = check_input_for_looping(user_input);
			
		}
		
		// prints goodbye, and close the input
		System.out.println("\nThanks for using this calculator. Goodbye!");
		
		user_input.close();
		
		return;
	}

}
