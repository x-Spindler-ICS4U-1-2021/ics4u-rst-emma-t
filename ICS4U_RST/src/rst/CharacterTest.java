package rst;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Character Test program
 * The CharacterTest program is an automated JUnit test to test the Character class
 * 
 * @author s427549
 * @since 2021-1-28
 */
public class CharacterTest {
	
	@Test
	public void testNormalScenario() {
		//This creates a new normal Character and tests its characteristics
		Character c = new Character("YNSS","name");
		
		assertEquals("name",c.getName());
		assertEquals("yellow", c.getColour());
		assertEquals("none",c.getHat());
		assertEquals("small",c.getSize());
		assertEquals("spots",c.getMarkings());
	}
	
	@Test
	public void testNull() {
		//This creates a new Character with the name "" and tests its name
		Character c = new Character("RFLS","");
		
		assertEquals("",c.getName());
	}
	
}
