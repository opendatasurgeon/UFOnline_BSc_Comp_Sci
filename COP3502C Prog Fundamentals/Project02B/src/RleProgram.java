/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 * Description: This is a 3 part project. This project develops routines to encode and decode data for images using run-length encoding 
 *				(RLE). Upon completion, project will implement encoding and decoding of raw data, conversion between data and strings, and 
 *				display of information by creating procedures that can be called from within their programs and externally. This
 *				project allows the practice with loops, strings, arrays, methods, and type-casting.
 *
 *				-------------------
 *				~~~ PROJECT 2B:~~~
 *				-------------------
 *
 *				For part B of this assignment, the will contains the first 6 methods on page 3 of project document. They are:
 *				- toHexString (Translates data (RLE or raw) a hexadecimal string (without delimiters). This method can also aid debugging)
 *				- countRuns (Returns number of runs of data in an image data set; double this result for length of encoded (RLE) byte array)
 *				- encodeRle (Returns encoding (in RLE) of the raw data passed in; used to generate RLE representation of a data.)
 *				- getDecodedLength (Returns decompressed size RLE data; used to generate flat data from RLE encoding. (Counterpart to #2))
 *				- decodeRle (Returns the decoded data set from RLE encoded data. This decompresses RLE data for use. (Inverse of #3))
 *				- stringToData (Translates a string in hexadecimal format into byte data (can be raw or RLE). (Inverse of #1))
 * 
 */

import java.util.*;

import java.lang.*;

public class RleProgram {
	
	
	//Decodes a single decimal digit and returns its hex value
	public static String charHexDecode(byte digit) { 
		
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
	
	// converts each hex value to their decimal decimal equivalent (later gets converted to a byte)
	public static int hexCharDecode(char charString) { 
				
		int result = -1; // stores and returns an int value
			
		// here I check if the charString is in higher hex range, if yes, subtract add 10 and get the decimal value
		if(charString >= 'A' && charString <= 'F') {
			
			result = (charString - 'A') + 10;// subtract ascii and add 10 to get decimal value
			
		}else {
			
			// otherwise just put the char to int
			result = Character.getNumericValue(charString);
			
		}
		
		return result;
	}
	
	// Translates data (RLE or raw) a hexadecimal string (without delimiters)
	public static String toHexString(byte[] data) {
		
		String hexString = ""; //stores the final string containing hex codes 
		
		// check the values, if not proper return null
		for(byte range: data){
			  if (range < 0 || range > 15) {
				  return null; 
			  }
				 
		} 
		
		//run at each element, pass it in char hex to get the hex value and append to string
		for(int i = 0; i < data.length; ++i) {
			
			hexString += charHexDecode(data[i]);
			
		}
		
		return hexString.toLowerCase(); // return a lower case of string
	}
	
	
	// This (and/or the function below it) is a badly written function. Put together in a fast manner. I apologize. 
	// Returns number of runs of data in an image data set; double this result for length of encoded (RLE) byte array.
	public static int countRuns(byte[] flatData) {

		
		// check for bad elements, return -1 
		for(byte range: flatData){
			  if (range < 0 || range > 15) {
				  return -1; 
			  }
				 
		} 
		
		// if the array has one element return 1
		if(flatData.length == 1) {
			return 1;
		}
				

		int c15 = 0 ;  // tracks if an elements has been seen 15 times
		int c = 0; // counter for each element
		boolean open = true; // blocks clause after the current and next element at i are same
		int current_i_value = 0; // tracks the last i value so it can be used outside the loop to check edge cases
		int same_neighbors_counter = 1; // if the elements are same, count up

		// start loop at 0 and end at length -1 since, we are doing the scoping the next index at each iteration, 
		// otherwise causes out of bounds error

		for(int i = 0; i < flatData.length-1; i++) {
			
			current_i_value++; // start with counting the current value up
			
			//if the current and next elements are same
			if(flatData[i] == flatData[i+1]) {
				
				++c15; // count up for 15 counter
				
				// enter the block at beginning when the neighbors are same
				if(open) {
					 
					c++; // count the element once if they are consecutive
					open = false; // make the block unavilable so that same elements don't add to countrun unless criterias are met
					continue; //skip
					
				}
			}else {
				// if the element are not same, count up
				c++;
				c15 = 0; // reset counter
				
			}
			// if a value has been seen at leat 15 times

			if(c15 >= 15) {
				c15 = 0; // reset 15 counter
				same_neighbors_counter++; // count up since same neighbors are detected
				open = true; //open the block  
			}
		}
		
		// now for the edge case, because we might get short due to our loop restrictions
		// If the i is at one index before the end, 
		//AND the previous value and current i value ARE NOT same 
		//AND there is no block and there are same neighbors
		if((current_i_value == flatData.length-1 && flatData[current_i_value-1] != flatData[current_i_value]) && (open == true && same_neighbors_counter >= 2)) {
				
			c++;// add to counter 
				
		}else if((current_i_value == flatData.length-1 && flatData[current_i_value-1] == flatData[current_i_value]) && (open == true && same_neighbors_counter >= 2)) {
			c++; /* else- if the i is at one index before the end, AND the previous value and current i value ARE same 
						AND there is no block and there are same neighbors, count the CountRun up*/

		}else if((current_i_value == flatData.length-1 && flatData[current_i_value-1] == flatData[current_i_value]) && (open == true && same_neighbors_counter < 2)) {
			
			c++; /* else- if the i is at one index before the end, AND the previous value and current i value ARE same 
						AND there is no block and there are NO same neighbors, count the CountRun up*/

		}else if((current_i_value == flatData.length-1 && flatData[current_i_value-1] != flatData[current_i_value]) && (open == true && same_neighbors_counter < 2)) {
			
			c++; /* else- if the i is at one index before the end, AND the previous value and current i value are NOT same 
						AND there is no block and there are NO same neighbors, count the CountRun up*/

		}
		
		return c;
				
	}
	
	//Returns encoding (in RLE) of the raw data passed in; used to generate RLE representation of a data.
	public static byte[] encodeRle(byte[] flatData) {
		
		// the size of this array is what countruns return * 2
		byte[] resultByteArray = new byte [ countRuns(flatData) * 2];	
		
		int counterIndex = 0; //this keeps position of where element counter needs to get places
		int elementindex = 1; // since element is placed to right of element position, this keeps position of that
		int elementCounter = 1; // this is simple counter that counts each element in array
		int yes15 = 1; // this counter tracks if an element has occurred 15 times
		
		
		// check for bad values
		for(byte range: flatData){
			  if (range < 0 || range > 15) {
				  return null; 
			  }
				 
		}
		
		// if there is single value in the array, return occurs 1 times with the value
		if(flatData.length == 1) {
			
			resultByteArray[0] = 1;
			resultByteArray[1] = flatData[0];
			
			return resultByteArray;
		}
		
		// if the array length is greater than 1
		for(int i = 0; i < flatData.length-1; i++) {
			
			// enter if the value for an element is 15 and the value at current and next index are same
			if(yes15 >= 15 && (flatData[i] == flatData[i+1])) {
				
				// places the value and the max count- which is 15
				resultByteArray[counterIndex] = (byte) elementCounter;
				resultByteArray[elementindex] = flatData[i];
				
				// reset the 15 tracking counter and element counter, and move the positions of counter and element placements
				yes15 = 1;
				elementCounter = 1;
				counterIndex += 2;
				elementindex += 2;
				
				continue; // and skip the rest to prevent extra count up of elements
			}
			
			//if the current element and the next element are same
			if(flatData[i] == flatData[i+1]) {
				yes15++; // add to 15 counter
				elementCounter++; // add to element counter
				
			// if the current and next element not the same
			}else if(flatData[i] != flatData[i+1]) {
				
				// place values at the current indices
				resultByteArray[counterIndex] = (byte) elementCounter;
				resultByteArray[elementindex] = flatData[i];
				
				// reset the 15 tracking counter and element counter, and move the positions of counter and element placements
				elementCounter = 1;
				counterIndex += 2;
				elementindex += 2;
				yes15 = 1;
			
			}
			
			// after that check if the current and next element are same, and if the next i counter is in the end (prevent the out of bounds)
			if ((flatData[i] == flatData[i+1]) && (i+1 == flatData.length-1)) {
				
				// if true, place the values and at the counter indecies
				resultByteArray[counterIndex] = (byte) elementCounter;
				resultByteArray[elementindex] = flatData[i];
				
				// reset element counter, and move the positions of counter and element placements
				elementCounter = 1;
				counterIndex += 2;
				elementindex += 2;
			}
			
			// Then heck if the current and next element are not same, and if the next i counter is in the end (prevent the out of bounds)
			if ((flatData[i] != flatData[i+1]) && (i+1 == flatData.length-1)) {
				
				// if true, place the values and at the counter indecies
				resultByteArray[counterIndex] = (byte) elementCounter;
				resultByteArray[elementindex] = flatData[i+1];
				
				// reset element counter, and move the positions of counter and element placements
				elementCounter = 1;
				counterIndex += 2;
				elementindex += 2;
			}
		}
	
		return resultByteArray;
		
	}
	
	//Returns decompressed size RLE data; used to generate flat data from RLE encoding. 
	public static  int getDecodedLength(byte[] rleData) {
		
		int sumEvenIndex = 0;
		
		// check the values in array, if bad, return -1
		for(byte range: rleData){
			  if (range < 0 || range > 15) {
				  return -1; 
			  }
				 
		}
		
		// the value at even indices gives us an idea of the total array size when we are unfolding the array to its original shape
		// we simply sum them at each even indices
		for(int i = 0; i < rleData.length; i += 2) {
			sumEvenIndex += rleData[i];
		}
		
		return sumEvenIndex;
	}
	
	//Returns the decoded data set from RLE encoded data. This decompresses RLE data for use.
	public static byte[] decodeRle(byte[] rleData) {
		
		//here the length of the array would be the length value return from decoded array
		byte[] resultByteArray = new byte [ getDecodedLength(rleData)];
		
		// move i to 2 steps each time since it tells us how many time a element needs to get repeated, it also controls the inner loop
		// move j to i+1 each time- this tells us which value we have to place i times
		// move m to 1 steps and this places the value at j 
		for(int i = 0, j = 1, m = 0; i < rleData.length; i+= 2, j = i+ 1) {
			
			for(int k = 0; k < rleData[i]; k++) {
				
				resultByteArray[m] = rleData[j];
				
				m++;

			}
		}
		
		return resultByteArray;
	}
	
	//Translates a string in hexadecimal format into byte data (can be raw or RLE). 
	public static byte[] stringToData(String dataString) {
		 
		// remove prefixes, make the string upper case
		 dataString = formatDecodeStringInput(dataString);
		 
		 // make a new array, the string length is the array size
		 byte[] resultArray = new byte[dataString.length()];
		 
		 //run loop until string length, return decimal value from the hex value and store it back in the byte array
		 for(int i = 0; i < dataString.length(); ++i) {
			 resultArray[i] = (byte) hexCharDecode(dataString.charAt(i));
		 }
		
		 return resultArray;
	}
	
	
	// main
    public static void main(String[] args) {
    	
    	/*Blocked // Test cases check
    	// test toHexString(byte[] data)
    	
    	System.out.println( "toHexString Result: " + toHexString(new byte[] { 3, 15, 6, 4, 6,7,10 })); 
    	
    	System.out.println();
    	
    	System.out.println( "countRuns Result: " + countRuns(new byte[] {4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 }));
    	
    	System.out.println();
    	
    	System.out.print( "encodeRle Result: ");
    	
    	byte[] result =  encodeRle(new byte[] {4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 });

    	for(byte a : result) {
    		System.out.print(a + " ");
    	}
    	
    	
    	System.out.println();
    	System.out.println();
    	
    	System.out.print( "getDecodedLength Result: " + getDecodedLength(new byte[] { 0, 15, 14, 4 }));
    	
    	System.out.println();
    	System.out.println();
    	
    	System.out.print( "decodeRle Result: ");
    	
    	byte[] result_2 =  decodeRle(new byte[] {2,4,15,1,15,1,5,1,1,8,1,7});

    	for(byte a : result_2) {
    		System.out.print(a + " ");
    	}
    			
    	System.out.println();
    	System.out.println();
    	
    	System.out.print( "stringToData Result: ");
    	byte[] result_3 = stringToData ("3f6BBB");
    	
    	for(byte a : result_3) {
    		System.out.print(a + " ");
    	}
    	*/
    	return;
    }
}
