/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 * Description: This lab is submitted to illustrate concepts and characteristics of recursion, including the  difference  between 
 *				“regular recursion” and “tail recursion,” the use of base cases, the concept of the call stack, and arithmetic in 
 *				return statements. Lab contains three different public methods and a private helper method in this lab.
 */

import java.util.*;

public class Factorial {

		//A purely recursive function that calculates the factorial of n. This function calls only itself.
	
		public static long pureRecursive(int n) {
			
			checkIfLegal_n(n);
			
			if(n == 1) {
				return 1;
			}
			else {
				return (long) (n* pureRecursive(n-1));
			}
			
		}
		 
		//A kickoff method for tail recursion; it calls only the tail() method (see below).
		public static long tailRecursive(int n) {
			
			checkIfLegal_n(n);
			
			return tail(n, 1);
		}
	 
		// A  private  method  called  by  the  tail  recursion  kickoff  method.

		private static long tail(int n, long result) {
			
			if(n == 1) {
				return result;
			}else {
				return tail(n - 1, (long) n * result);
			}
			
		}
		
		// An  iterative  version  of  the  factorial  calculation.  This  method  is an "unwound"  version  of  the 
		// tailRecursive() method outlined above. It doesn't call itself or any other method but uses 
		// a looping structure to calculate the factorial. 
		public static long iterative(int n) {
			
			checkIfLegal_n(n);
			
			long result = 1;
			
			for(int i = n; i >= 1; i--) {
				
				result *= (long) i;
				
			}
			
			return result;
		}
		
		
		//For a non-positive number inc. 0, the function should throw an IllegalArgumentException. 
						
		public static void checkIfLegal_n(int n) {
		
			if(n <= 0) {
				 throw new IllegalArgumentException();
			}
			
		}
		
				
	public static void main(String[] args) {
		
		//Main function taking n and providing factorial result in a new line
		Scanner scnr = new Scanner(System.in);
		
		int n;
		
		try {
			
			System.out.print("Enter a number above 0: ");
			n = Integer.parseInt(scnr.next());
			
			System.out.print("Factorial: ");
			System.out.println(tailRecursive(n));
			
		} catch (NumberFormatException e) {
			
			// specifically handles the user input error.
			System.err.println("Error: " + e);
			
		}
		
		
		

	}

}
