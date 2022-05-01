/*
 * Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * The IceDragon class is derived from the Dragon class and must make all of its methods available
 * 
 */
public class IceDragon extends Dragon{

	String name;
	String image;
	
	// Constructor; creates a new IceDragon object with the given name and image. This is the only public 
	// constructor for the IceDragon class!
	public IceDragon(String name, String image) {
		
		super(name, image);
	
		this.name = name;
		this.image = image;
	}
	
	//For the IceDragon type, this method always returns false
	public boolean canBreatheFire() {
		return false;
	}
	
	

}
