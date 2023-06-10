package dd;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class Goblin_Test {
	/*
	 * to test the goblin we need the goblin and another character
	 * because the goblin's ability is to disarm the opposing character
	 */
	Goblin g1 = new Goblin();
	Warrior w1 = new Warrior();

	/*
	 * we are testing this to see the default status of the disable boolean is false
	 * assertFalse
	 */
	@Test
	void test_Disarm() {
		g1.attack();
		w1.attack();
		w1.attack();
		assertFalse(DungeonCharacter.getDisable());
	}
	@Test
	public void test_Attack() {
		g1.attack();
		assertTrue(DungeonCharacter.getDisable());
		
	}

}
