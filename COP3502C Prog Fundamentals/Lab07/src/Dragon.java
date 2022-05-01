/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * The Dragon class derives from the Cow class and makes all of its methods available. 
 * 
 */

public class Dragon extends Cow{

	String name;
	String image;
	
	// Constructor; creates a new Dragon object with the given name and image. This is the only public 
	// constructor for the Dragon class!
	public Dragon(String name, String image) {
		
		super(name);
		super.setImage(image);
	
		this.name = name;
		this.image = image;
		
	}
	
	
	// This method exists in every Dragon class. For the default Dragon type, it always returns true
	public boolean canBreatheFire() {
		return true;
	}
	

}
