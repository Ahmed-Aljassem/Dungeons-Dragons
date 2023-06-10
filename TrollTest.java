package dd;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TrollTest {
	Troll t1 = new Troll();
	int counter = 0;

	@Test
	void testBoulder() {
		int[] solution;
		for(int i = 0; i < 280; i++) {
			solution = t1.attack();
			if(solution.length == 5) {
				counter ++;
			}
		}
		System.out.println(counter);
		assertTrue(counter >= 70 && counter <= 90);
		
		
	}
	@Test
	void testMove() {
		int[] solution;
		for(int i = 0; i < 100; i ++) {
			solution = t1.attack();
			if(solution.length == 5) {
				break;
			}
		}
		//out current location or where we are standing
		int[] firstLocation = t1.getLocation();
		t1.move();
		t1.move();
		int[]secondLocation = t1.getLocation();
		assertTrue(secondLocation[0] == 1);

	}
	
	
	

}
