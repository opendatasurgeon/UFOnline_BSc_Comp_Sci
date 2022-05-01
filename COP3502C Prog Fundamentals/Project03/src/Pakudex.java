/*
*  The Pakudex class contains all the Pakuri that program will encounter as Pakuri objects. Note: The Pakudex 
*  will have a set size determined by user input at the beginning of the program’s run; the number of species 
*  contained in the Pakudex will never grow beyond this point
*/

import java.util.*;
import java.lang.*;


public class Pakudex {
		
	private int pakudex_size;
	private int pakudex_size_fill_count;
	
	private ArrayList<Pakuri> pakuri_ArrayList = new ArrayList<Pakuri>(getCapacity());

	
	//Default constructor; if called, the default size for the Pakudex should be 20 
	public Pakudex() {
		
		this(20);
		
	}
	
	//Initializes this object to contain exactly capacity objects when completely full 
	public Pakudex(int capacity) {
		
		pakudex_size = capacity;
		
	}
	
	//Returns the number of critters currently being stored in the Pakudex 
	public int getSize() {
		
		return pakudex_size_fill_count;
		
	}
	
	//Returns the number of critters that the Pakudex has the capacity to hold at most 
	public int getCapacity() {
		
		return pakudex_size;
		
	}
	
	//Returns a String array containing the species of the critters as ordered in the Pakudex; if there are no species 
	//added yet, this method should return null
	public String[] getSpeciesArray() {
		
		int i = 0;
				
		String[] speciest_liStrings = new String[getSize()];
		
		if(pakudex_size_fill_count <= 0) {
			
			speciest_liStrings = null;
		}
		else {
			for(Pakuri pakuri : pakuri_ArrayList) {		
	
				speciest_liStrings[i] = pakuri.getSpecies();
				
				++i;
				
			}
		
		}
		
		return speciest_liStrings;
	}
	
	//Returns  an  int  array  containing  the  attack,  defense,  and  speed  statistics  of  species  at  indices  0,  
	// 1,  and  2 respectively; if species is not in the Pakudex, returns null
	public int[] getStats(String species) {
		
		int[] small_stats_array = null;
		
		for(Pakuri pakuri : pakuri_ArrayList) {
			
	
			if(pakuri.getSpecies().equals(species)) {
				
				small_stats_array = new int[3];
								
				small_stats_array[0] = pakuri.getAttack();
				small_stats_array[1] = pakuri.getDefense();
				small_stats_array[2] = pakuri.getSpeed();
				
			}
			
		}
		
		return small_stats_array;
	}
	

	
	//Sorts the Pakuri objects in this Pakudex according to Java standard lexicographical ordering of species name
	public void sortPakuri() {
		
		// please see Pakuri class for @Override
		Collections.sort(pakuri_ArrayList);
		
		//--------- OPTIONAL --------- Returns integer determining order of this object and target object
		
//		    Collections.sort(pakuri_ArrayList,
//		        new Comparator<Pakuri>()
//		        {
//		    	
//		            public int compare(Pakuri pp1, Pakuri pp2)
//		            {
//		                return pp1.getSpecies().compareTo(pp2.getSpecies());
//		            } 
//		            
//		        });
		    		
		//--------- OPTIONAL --------- Returns integer determining order of this object and target object
//		  pakuri_ArrayList.sort(Comparator.comparing(Pakuri::getSpecies));
		
	}
	

	//Adds species to the Pakudex; if successful, return true, and false otherwise 
	public boolean addPakuri(String species) {
		
		for(Pakuri pakuri : pakuri_ArrayList) {
				
			if(pakuri.getSpecies().equals(species)) {				
				
				return false;
			}
				
		}
			
		pakuri_ArrayList.add(new Pakuri(species));
		
		++pakudex_size_fill_count;
		
		return true;
	 
	}
	
	//Attempts to evolve species within the Pakudex; if successful, return true, and false otherwise
	public boolean evolveSpecies(String species) {
		
		for(Pakuri pakuri : pakuri_ArrayList) {
			
			if(pakuri.getSpecies().equals(species)) {
				
				pakuri.evolve();
				
				return true;
			}

		}
		
		return false;
		
	}
		
}

