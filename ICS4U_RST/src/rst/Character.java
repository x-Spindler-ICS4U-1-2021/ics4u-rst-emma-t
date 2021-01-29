package rst;

/**
 * The Character class
 * The Character class reads the characteristics chosen in the GWPlayer and turns the chars into fish characteristics
 * 
 * @author s427549
 * @since 2021-1-18
 */
public class Character {
	
	//Characteristics of each character
	private String name, colour, size, hat, markings;
	
	/**
	 * Sets characteristics of the fish
	 * @param characteristics String This is the first parameter of the Character method and is a String of 4 chars that
	 * represent each characteristic
	 * @param fishName String This is the second parameter of the Character method and it is the name inputted by the user
	 */
	public Character(String characteristics, String fishName) {
		
		//Sets character colour from the first char
		if (characteristics.charAt(0) == 'R') {
			this.colour = "red";
		} else if (characteristics.charAt(0) == 'Y') {
			this.colour = "yellow";
		} else {
			this.colour = "blue";
		}
		
		//Sets character hat from the second char
		if (characteristics.charAt(1) == 'N') {
			this.hat = "none";
		} else if (characteristics.charAt(1) == 'F') {
			this.hat = "fedora";
		} else {
			this.hat = "baseball cap";
		}
		
		//Sets character size from the third char
		if (characteristics.charAt(2) == 'S') {
			this.size = "small";
		} else if (characteristics.charAt(2) == 'M') {
			this.size = "medium";
		} else {
			this.size = "large";
		}
		
		//Sets character markings from the fourth char
		if (characteristics.charAt(3) == 'L') {
			this.markings = "stripes";
		} else {
			this.markings = "spots";
		}
		
		//Sets character name
		this.name = fishName;
	}
	
	/**
	 * Getter method for the name
	 * @return String Returns name of the character
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter method for the colour
	 * @return String Returns colour of the character
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * Getter method for the hat
	 * @return String Returns the hat of the character
	 */
	public String getHat() {
		return hat;
	}
	
	/**
	 * Getter method for the size
	 * @return String Returns size of the character
	 */
	public String getSize() {
		return size;
	}
	
	/**
	 * Getter method for the markings
	 * @return String Returns the markings of the character
	 */
	public String getMarkings() {
		return markings;
	}
}