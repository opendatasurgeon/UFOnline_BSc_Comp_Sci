/*
 * This class is the blueprint for the different critter objects that a user will create. Class stores
 * information about the critter’s species, attack level, defense level, and speed. All variables storing information 
 * about the critters are private.
 * 
 */

public class Pakuri implements Comparable<Pakuri>{

	private String species; 
	private int attack, defense, speed; 
	

	//This method should be the only constructor for this class.
	public Pakuri(String species) {
		this.species = species;
		
		// setters
		setAttack((this.species.length() * 7) + 9);
		setDefense((this.species.length() * 5) + 17);
		setSpeed((this.species.length() * 6) + 13);
	}
	
	//Returns the species of this critter 
	public String getSpecies() {
		return species;
	}
	
	//Returns the attack value for this critter 
	public int getAttack() {
		return attack;
	}
	
	//Returns the defense value for this critter 
	public int getDefense() {
		return defense;
	}
	
	//Returns the speed of this critter 
	public int getSpeed() {
		return speed;
	}
	
	//Changes the attack value for this critter to newAttack 
	public void setAttack(int newAttack){
		this.attack = newAttack;
	}
	
	//--------- OPTIONAL --------- Changes the defense value for this critter to newAttack 
	public void setDefense(int newDefense){
		this.defense = newDefense;
	}
		
	//--------- OPTIONAL --------- Changes the speed value for this critter to newAttack 
	public void setSpeed(int newSpeed){
		this.speed = newSpeed;
	}
	
	//Will evolve the critter as follows: a) double the attack; b) quadruple the defense; and c) triple the speed 
	public void evolve(){
		attack *= 2;
		defense *= 4; 
		speed *= 3;
	}

	//--------- OPTIONAL --------- Returns integer determining order of this object and target object
	
	@Override
	public int compareTo(Pakuri o) {
		
		return this.species.compareTo(o.species);
		
	}
		
}
