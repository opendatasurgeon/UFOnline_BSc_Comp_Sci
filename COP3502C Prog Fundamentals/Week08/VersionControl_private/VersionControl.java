/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 * Description: Objective of this lab is to understand the purpose and usefulness of version control.
 * 
 */

import java.util.*;

public class VersionControl {

    public static void main(String[] args) {

        try{
	        // take user inputs	
            System.out.println("Enter two #s to add them.");
            System.out.print("Enter x: ");
            int x = scnr.nextInt();
            System.out.print("Enter y: ");
            int y= scnr.nextInt();
            //show their sum
            System.out.println("Sum: "+ (x+y));
            
            System.out.println();
            
            //then take another set of input
            System.out.println("Now, enter two #s to subtract them .");
            System.out.print("Enter j: ");
            int j = scnr.nextInt();
            System.out.print("Enter k: ");
            int k= scnr.nextInt();

            //and subtract them
            System.out.println("Difference: " + (j-k));

        }catch(Exception e){
            System.err.println("An error occurred: "+ e);
        }
    
        scnr.close();
        
    }
}