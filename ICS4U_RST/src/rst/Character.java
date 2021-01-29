package rst;

public class Character {
	
	private String name, colour, size, hat, markings;
	
	public Character(String characteristics, String fishName) {
		
		if (characteristics.charAt(0) == 'R') {
			this.colour = "red";
		} else if (characteristics.charAt(0) == 'Y') {
			this.colour = "yellow";
		} else {
			this.colour = "blue";
		}
		
		if (characteristics.charAt(1) == 'N') {
			this.hat = "none";
		} else if (characteristics.charAt(1) == 'F') {
			this.hat = "fedora";
		} else {
			this.hat = "baseball cap";
		}
		
		if (characteristics.charAt(2) == 'S') {
			this.size = "small";
		} else if (characteristics.charAt(2) == 'M') {
			this.size = "medium";
		} else {
			this.size = "large";
		}
		
		if (characteristics.charAt(3) == 'L') {
			this.markings = "stripes";
		} else {
			this.markings = "spots";
		}
		
		this.name = fishName;
	}
	
	public String getName() {
		return name;
		
	}
	
	public String getSize() {
		return size;
		
	}
	
	public String getColour() {
		return colour;
		
	}
	
	public String getHat() {
		return hat;
		
	}
	
	public String getMarkings() {
		return markings;
		
	}
	
}
