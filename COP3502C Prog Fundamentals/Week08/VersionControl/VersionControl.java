import java.util.*;

public class VersionControl {

    public static void main(String[] args) {

        try{
	        	
            System.out.println("Enter two #s to add them.");
            System.out.print("Enter x: ");
            int x = scnr.nextInt();
            System.out.print("Enter y: ");
            int y= scnr.nextInt();

            System.out.println("Sum: "+ (x+y));
            
            System.out.println();
            
            System.out.println("Now, enter two #s to subtract them .");
            System.out.print("Enter j: ");
            int j = scnr.nextInt();
            System.out.print("Enter k: ");
            int k= scnr.nextInt();

            System.out.println("Difference: " + (j-k));

        }catch(Exception e){
            System.err.println("An error occurred: "+ e);
        }
    
        scnr.close();
        
    }
}