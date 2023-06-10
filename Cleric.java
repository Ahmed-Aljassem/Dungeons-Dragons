package dd;

import java.util.Scanner;

import miscUtil.MiscUtil;

public class Cleric extends Hero {

	/*
	 * the Cleric will be allowed to heal itself instead of attacking to do this we will need a scanner to get
	 * input from the user
	 */
	private Scanner scan;
	private boolean isHuman;

	public Cleric(String name, int maxHp, int xCoord, int yCoord, double baseChance, int doDamageMax, int doDamageMin,
			int direction, int rows, int columns, double chanceToBlock, Scanner scan, boolean isHuman) {
		super(name, maxHp, xCoord, yCoord, baseChance, doDamageMax, doDamageMin, direction, rows, columns,
				chanceToBlock);
		this.scan = scan;
		this.isHuman = isHuman;

	}

	public Cleric(Scanner scan) {
		//key word super will allow us to be able to access all the methods from the parent class
		super();
		this.scan = scan;
		this.isHuman = true;
	}
	@Override
	public int[] attack() {
		int[] solution = super.attack();
		int[] location  = super.getLocation();
		int healing = 0;
		int choice = 0;
		if(isHuman) {
			//we will ask the user if they want to heal
			 choice = MiscUtil.get_user_input_int(scan, 1, 2,
					"Do you want to heal yourself instead? \n 1 for yes \n 2 for no");
			}else {
				choice = MiscUtil.gen_random_int(1, 2);
				
			if(choice == 1) {
				healing = MiscUtil.gen_random_int(10, 20);
				System.out.println("You heal for " + healing);
				super.hit(healing *(-1));
				solution[2] = 0;
			}
		}
		return solution;
	}

}
