package dd;

public abstract class Hero extends DungeonCharacter {
	/*
	 * this character will have the chance to block the opponents attack
	 * to do this we will need a instance variable as well as override the attack method
	 * because we will need to use it but change things about it.
	 */
	private double chanceToBlock;

	/*
	 * we inherit everything form the parent class...all the public methods
	 */
	
	public Hero(String name, int maxHp, int xCoord, int yCoord, double baseChance, 
			int doDamageMax, int doDamageMin, int direction, int rows, int columns, double chanceToBlock) {
		
		//this super will run the first constructor form the parent class and pass them on
		super(name, maxHp, xCoord, yCoord, baseChance, doDamageMax, doDamageMin, direction, rows, columns);
		this.chanceToBlock = chanceToBlock;
		
	}

	/*
	 * the key word this means everything that is applicable to the class i am typing in
	 */
	public Hero() {
		// super method will call and run the constructor from the parent class
		super();
		this.chanceToBlock = 0.4;

	}
	/*
	 * we are overriding the hit method we inherit from the parent class
	 */
	@Override
	public void hit(int damage) {
		/*
		 * we are going to make an if statement from the parent class and to do that i would use
		 * the key word super.methodname();
		 */
		if(super.successfulAction(chanceToBlock )&& damage > 0) {
			System.out.println(super.getName() + " has blocked the attack!");
			damage = 0;
			
		}
		super.hit(damage);
		
	}
	

}
