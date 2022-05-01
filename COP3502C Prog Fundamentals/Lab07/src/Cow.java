
/*
 *  Student: Mihir Patel (COP3502C Spring 2022 with Dr. Aman Kapoor)
 * 
 *  The Cow class facilitates the creation and use of cow objects:
 */

public class Cow {

	private String name;
	private String image;
	
	//This method should be the only constructor for this class. There should not be a default constructor!
	public Cow(String name) {
		this.name = name;
		this.image = null;
	}

	//Returns the name of the cow.
	public String getName() {
		return this.name;
	}
	
	//Returns the image used to display the cow (after the message).
	public String getImage() {
		return this.image;
	}
	
	//Sets the image used to display the cow (after the message).
	public void setImage(String _image) {
		this.image = _image;
	}
	
	
}
