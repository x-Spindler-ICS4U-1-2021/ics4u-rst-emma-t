package rst;

import static org.junit.Assert.*;
import org.junit.Test;

public class CharacterTest {
	
	@Test
	public void testNormalScenario() {
		Character c = new Character("YNSS","name");
		assertEquals("name",c.getName());
		assertEquals("none",c.getHat());
		assertEquals("small",c.getSize());
		assertEquals("spots",c.getMarkings());
	}
	
	@Test
	public void testNull() {
		Character c = new Character("RFLS","");
		assertEquals("",c.getName());
	}

}

