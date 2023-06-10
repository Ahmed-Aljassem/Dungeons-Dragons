package dd;

import miscUtil.MiscUtil;

public class Warrior extends Hero {
	/*
	 * we now created a sub-sub class this Warrior class extends the hero class which is passed through the DungeonCharacter class
	 * with this warrior character we want to make it where if you roll the dice and get a high number like 20 it will make it produce a big hit
	 * with a lot of damage. 
	 * Conversely if the warrior rolls a low number on the dice he will hit yourself and hurt yourself
	 */

	public Warrior(String name, int maxHp, int xCoord, int yCoord, double baseChance, int doDamageMax, int doDamageMin,
			int direction, int rows, int columns, double chanceToBlock) {
		super(name, maxHp, xCoord, yCoord, baseChance, doDamageMax, doDamageMin, direction, rows, columns,
				chanceToBlock);
		
	}

	/*
	 * this constructor has the name as the one above it but with no parameters; this is used for testing purposes
	 */
	public Warrior() {
		//super method will allow us to use methods from the parent class which is the hero
		super();
	}
	/*
	 * we know the attack is going to be different from the parent class so we will override it and make it different
	 * depending on our requirements.
	 * we will roll the dice and we hit on the roll a 2
	 */
	@Override
	public int[] attack(){
		/* we know the damage will be last index in the solution array
		 * to access this index we can take the array and subtract one from it to access it
		 */
		
		int[] solution = super.attack();
		int size = solution.length;
		int damage = solution[size - 1];
		int roll = MiscUtil.gen_random_int(1, 20);
		if(roll == 20) {
			if(damage == 0) {
				super.hit(12);
				System.out.println("Bad luck, you hit yourself");
			}else {
				super.hit(-12);
				damage *= 2;
				System.out.println("Critical hit and you heal yourself!");
				
			}
			
		}else if(roll == 1) {
			if(damage == 0) {
				super.hit(-12);
				damage = 12;
				System.out.println("Good luck from bad, you heal yourself!");
				
			}else {
				super.hit(12);
				damage = 0;
				System.out.println("Bad luck, you hit yourself");
			}
		}
		 solution[size - 1] = damage;
		return solution;
		
	}
	

}
