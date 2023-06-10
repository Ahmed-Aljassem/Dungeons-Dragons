package dd;

import miscUtil.MiscUtil;

public abstract class Monster extends DungeonCharacter {
	/*
	 * this monster character is able to regenerate health
	 * and the amount of health will be a range instead of a set value
	 * to do this we will need to create 3 variable one accounting for the chance
	 * that the action will happen.
	 * and 2 for the lowest number of health and highest number of health
	 * the character will be able to heal.
	 * we will also need to override the hit method and make it where we hit ourselves
	 * but instead of taking away from our health we will be adding (negative value)
	 */
	private double chanceToRegen;
	private int minRegen,maxRegen;

	public Monster(String name, int maxHp, int xCoord, int yCoord, double baseChance, int doDamageMax, int doDamageMin,
			int direction, int rows, int columns, double chanceToRegen, int maxRegen, int minRegen) {
		super(name, maxHp, xCoord, yCoord, baseChance, doDamageMax, doDamageMin, direction, rows, columns);

		this.chanceToRegen = chanceToRegen;
		this.maxRegen = maxRegen;
		this.minRegen = minRegen;
	}

	/*
	 * the reason we have 2 different constructors is for testing purposes
	 * and instead of having to instantiate it we can assign the values inside the constructor
	 */
	public Monster() {
		super();
		this.chanceToRegen = 1;
		this.maxRegen = 10;
		this.minRegen = 9;

	}
	@Override
	public void hit(int damage) {
		// to call a method from the super class you would use the key word super.(methodName)
		super.hit(damage);
		if(super.isAlive() && super.successfulAction(chanceToRegen)) { 
				int healing = MiscUtil.gen_random_int(minRegen, maxRegen) * - 1;// how much healing to the character
				System.out.println(super.getName() + " gained " + healing + " healing points");
				super.hit(damage);
			
		}

	}
	@Override
	public String toString() {
		// we are going to concatenate 
		String info = "The monster ";
			info+=	super.toString();
		
		return info;
	}


}
