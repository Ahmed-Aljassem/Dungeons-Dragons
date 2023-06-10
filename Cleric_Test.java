package dd;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class Cleric_Test {
	Scanner scan = new Scanner(System.in);
	Cleric c1 = new Cleric(scan);

	@Test
	void testHeal() {
		int[] solution =  c1.attack();
		assertTrue(solution[2] == 0);
	}
	@Test
	void testNoHeal() {
		int[] solution =  c1.attack();
		assertTrue(solution[2] > 0);
	}

}
