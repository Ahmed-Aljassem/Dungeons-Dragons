package dd;

public class Troll extends Monster {
	/*
	 * This troll monster character will have the ability to throw a bowling ball that will inflict damage
	 * in its path the set back would be if it were to miss it would have its turn skipped
	 */
	private double chanceToBoulder;//chance to roll the boulder
	private boolean incapcitated;

	public Troll(String name, int maxHp, int xCoord, int yCoord, double baseChance, int doDamageMax, int doDamageMin,
			int direction, int rows, int columns, double chanceToRegen, int maxRegen, int minRegen, double chanceToBoulder) {
		super(name, maxHp, xCoord, yCoord, baseChance, doDamageMax, doDamageMin, direction, rows, columns,
				chanceToRegen, maxRegen, minRegen);
		this.chanceToBoulder = chanceToBoulder;
		this.incapcitated = false;
	}

	public Troll() {
		super();// this is a specific call to the constructor
		this.chanceToBoulder = 0.5;
		this.incapcitated = false;
		
	}
	
	
	/*
	 * if the character is too tired the character cannot move and would have its turn skipped
	 */
	@Override
	public void move() {
		if(this.incapcitated) {
		System.out.println("Troll too tired to move");
		this.incapcitated = false;
		}else {
			super.move();
		}
	}
	@Override
	public void changeDirection(int direction) {
		if(this.incapcitated) {
			System.out.println("Troll too tired to move");
			this.incapcitated = false;
		}else {
			super.changeDirection(direction);
		}
		
	}
	
	
	
	
	
	/*
	 * in order to skip the turn of the character if the character misses they're hit we need to override a couple of methods
	 * such as the move method the attack method and get direction method.
	 */
	@Override
	public int[] attack() {
		//we will capture the original solution array, we will modify it and then return it back
		int[] solution = super.attack();
		if(incapcitated) {
			solution[solution.length - 1] = 0;
			this.incapcitated = false;
			System.out.println("Troll tired can't fight");
			return solution;
			
		}else if(super.successfulAction(chanceToBoulder)) {
			System.out.println("The troll rolls a boulder");
			this.incapcitated = true;
			return this.extendSolution(solution);
			
			
		}else {
			return solution;
			
		}
		
		
		
	}
	
	//helper method
	// we will find one square in front of the square we are attacking
	private int[] extendSolution(int[] solution) {
		// we will get which way they are facing
		int direction = super.getDirection();
		// we will switch according to direction... if we fire we want one more in the y axis
		int [] newSolution = new int[5];
		//index 2 and 3 are blank they are representing the new y,x pair
		newSolution[0] = solution[0];
		newSolution[1] = solution[1];
		newSolution[4] = solution[2];

		switch(direction) {
		//going north
		case 1:
			//going north we are decrementing y
			newSolution[2] = super.setVars(0,super.getRows() - 1,solution[0] - 1);
			newSolution[3] = solution[1];
			break;
			//going east
		case 2:
			newSolution[2] = solution[0];
			newSolution[3] = super.setVars(0, super.getColumns()-1, solution[1] + 1);
			break;
			
			
			//going south
		case 3:
			newSolution[2] = super.setVars(0, super.getRows() - 1, solution[0] + 1);
			newSolution[3] = solution[1];
			break;
			//going west
		case 4:
			newSolution[2] = solution[0];
			newSolution[3] = super.setVars(0, super.getColumns()-1, solution[1] - 1);
			break;
		
		
		}
		return solution;
		
	}

}
