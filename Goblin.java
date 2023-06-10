package dd;

public class Goblin extends Monster {

	/*
	 * This goblin character will be able to disarm the opponent
	 * to do this we will need to create a variable that will be a chance to disarm the opponent
	 * and we will override the attack method because we will need to use and change this about it
	 */
	private double chanceToDisarm;

	public Goblin(String name, int maxHp, int xCoord, int yCoord, double baseChance, int doDamageMax, int doDamageMin,
			int direction, int rows, int columns, double chanceToRegen, int maxRegen, int minRegen, double chanceToDisarm) {
		super(name, maxHp, xCoord, yCoord, baseChance, doDamageMax, doDamageMin, direction, rows, columns,
				chanceToRegen, maxRegen, minRegen);
		this.chanceToDisarm = chanceToDisarm;

	}

	public Goblin() {
		// key word super will allow us to use the constructor from the monster class
		super();
		this.chanceToDisarm = 0.4; // for testing purposes we should change this chance to 100%


	}

	// the goblin will always disable
	@Override
	public int[] attack() {
		int[] solution = new int[3];
		if(super.successfulAction(chanceToDisarm)) {
			System.out.println(super.getName() + " disarms the opponent");
			DungeonCharacter.setDisable(true);
			//putting in where we are standing
			int location[] = super.getLocation();
			solution[0] = location[0];
			solution [1] = location[1];
			solution[2] = 0;
		}else {
			solution = super.attack();
		}
		return solution;

	}
	@Override
	public String toString() {
		String info = "Type: Goblin \n";
		info+= super.toString();
		return info;
		
	}

}
