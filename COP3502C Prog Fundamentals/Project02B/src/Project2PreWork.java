/*
 * Simple practice...Project 2 Pre-work
 */

import java.util.ArrayList;

public class Project2PreWork {
	
	// Useful for method 2 (countRuns) and 3 (encodeRle)
	public static boolean consecutiveFours(int[] arr) {
		
		boolean result = false;
		int yes4 = 1; //start at 1, otherwise we will have to check 3
		
		for(int i=0; i < arr.length-1; ++i) {
			
			//check current and next. Because i is a length shorter, i will always be in bound
			if(arr[i] == arr[i+1]){
				yes4++;
			}
			else {
			//else if(arr[i] != arr[i+1]) //also works
				yes4 = 1;
				//continue;
			}
			
			if(yes4 == 4) { //break right away if consecutive
				result = true;
				break;
			}
			
		}
		
		return result;
	}
	
	// Useful for method 4 (getDecodedLength)
	public static int[] sumByParity(int[] arr) {
		
		int[] result_arr = new int[2];
		
		int evenIdenxSum = 0, oddIndexSum = 0;
		
		for(int i=0; i < arr.length; ++i) {
			
			
			if(i % 2 == 0) {
				evenIdenxSum += arr[i];
			}
			if(i % 2 != 0) {
				oddIndexSum += arr[i];
			}
			
		}
		
		result_arr[0] = evenIdenxSum;
		result_arr[1] = oddIndexSum;
		
		return result_arr;
	}
	
	//Useful for method 5 (decodeRle)
	public static int[] expandByIndex(int[] arr) {
		
		int result_arr_size = 0;
		int fill_in = 0;
		
		for(int i = 0; i < arr.length; ++i) {
			result_arr_size += arr[i];
		}
		
		int[] result_arr = new int[result_arr_size];
		
		for(int i = 0; i < arr.length; ++i) {
			
			for(int j = 0; j < arr[i]; ++j) {
								
				result_arr[fill_in] = i;
								
				++fill_in;
			}
			
		}
		
		return result_arr;
		
	}
	
	//Useful for method 6 (stringToData)
	public static int numericalCount(String string) {
		
		int numCounter = 0;
		
		for(int i=0; i < string.length(); ++i) {
			if(Character.isDigit(string.charAt(i))) {
				numCounter++;
			}
		}
		
		return numCounter;
	}
	
	public static void main(String[] args) {
		
		
		/* check	boolean consecutiveFours(int[] arr) */
		System.out.println(consecutiveFours(new int[]{3,2,5,4,4,4,5,55,55,55}));
		
		System.out.println(consecutiveFours(new int[]{3,2,5,4,4,4,4,7,12})); //DONE
		
		System.out.println();
		
		/* check	int[] sumByParity(int[] arr) */
		int[] parity_arr = sumByParity(new int[]{5,12,8,5,3,11,7,2,3,16,4}) ;
		
		System.out.println("[" + parity_arr[0] + "," + parity_arr[1] + "]"); //DONE

		System.out.println();
		
		/* check	int[] expandByIndex(int[] arr) */
		
		int[] expand_arr = expandByIndex(new int[]{2,5,3});
		
		for(int i =0; i < expand_arr.length; ++i) {
			
			if(i == 0) {
				System.out.print("[");
			}
			
			if(i == expand_arr.length-1) {
				System.out.print(expand_arr[i] + "]");
			}
			else {
				System.out.print(expand_arr[i] + ",");
			}
			
		}
		
		System.out.println('\n');
		
		/* check	int numericalCount(String string) */
		
		int countNums = numericalCount("abcd3fgh-634561");
				
		System.out.print(countNums);
	}

	


// ------- OTHER
	
//	   private static void selectionSort(int[] numbers){
//	      
//	      int minIndex;
//	      
//	      
//	      for(int i = 0; i < numbers.length; ++i){
//	         
//	         minIndex = findMin(numbers, i);
//	         swap(numbers, i, minIndex);
//	         
//	      }
//	      
//	      
//	   }
//	   
//	   private static void swap(int[] numbers, int i, int minIndex){
//	      int temp = numbers[i];
//	      numbers[i] = numbers[minIndex];
//	      numbers[minIndex] = temp;
//	   }
//	   
//	   private static int findMin(int[] numbers, int start){
//	      int minIndex = start;
//	      
//	      for(int i = start; i < numbers.length; i++){
//	         if(numbers[i] < numbers[minIndex]){
//	            minIndex = i;
//	         }
//	      }
//	      
//	      return minIndex;
//	   }
//	   
//	   private static void shuffle(int[]numbers){
//		      
//		      for(int i = 0; i < numbers.length; i++){
//		    	  
//		        int random = (int) (Math.random() * numbers.length);
//		        
//		        int temp = numbers[i];
//		        numbers[i] = numbers[random];
//		        numbers[random] = temp;
//		        
//		      }
//		      
//	    }
//	   
//	   private static void shuffle(int[][] numbers){
//		      
//		      for(int i = 0; i < numbers.length; i++){
//		    	 for(int j = 0; j < numbers[i].length; j++) {
//		    		 int row_random = (int) (Math.random() * numbers.length);
//		    		 int column_random = (int) (Math.random() * numbers[i].length);
//				        
//				        int temp = numbers[i][j];
//				        numbers[i][j] = numbers[row_random][column_random];
//				        numbers[row_random][column_random] = temp;
//		    	 }
//		       
//		        
//		      }
//		      
//	    }
//	   
//	   public static void main(String[] args) {
//	      
//	      int[][] srray = { {5,12,8},
//	    		  			{1,6,11} };
//	      
//	      //selectionSort(srray);
//	      
//	      shuffle(srray);
//	      
//	      for(int i = 0; i < srray.length; i++){
//	         System.out.println(srray[i]);
//	      }
//	      
//	      for(int i = 0; i < srray.length; i++){
//	    	  for(int j = 0; j < srray[i].length; j++) {
//		         System.out.print(srray[i][j] + " ");
//	    	  }
//	    	  System.out.println();
//	      }
//	   }
//
//
}
