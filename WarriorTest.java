package dd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WarriorTest {
	Warrior w1 = new Warrior();

	@Test
	void testCritWithDamage() {
		int[]solution = new int[3];
		int counter = 0;
		//with this for-loop we will attack 100 times
		for(int i = 0; i < 100; i++) {
			solution = w1.attack();
			if(solution[2] == 18 || solution[2] == 20) {
				counter++;
				
			}
			
		}
		System.out.println(counter);
		assertTrue(counter > 0 && counter < 10);
	}
//	@Test
//	void testAttack() {
//		w1.attack();
//	}

}
