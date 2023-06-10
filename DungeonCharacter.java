package dd;

import java.util.Random;

import miscUtil.MiscUtil;

public  abstract class DungeonCharacter implements IDC {
	/*
	 * when having an abstract class you cannot instantiate it
	 * it is a parent class we cannot run the game with just this method
	 * its not well defined enough to play the game.
	 * we will need to temporarily remove it for when needing to test our code.
	 */
	private String name;
	private char symbol;
	private int maxHp;
	private int currentHp;
	private int xCoord;
	private int yCoord;
	private double baseChance;
	private int doDamageMax, doDamageMin;
	private int direction;
	private int rows, columns;
	/*
	 * Whenever a variable is declared as static, this means there
	 * is only one copy of it for the entire class, rather than each
	 * instance having its own copy.
	 */
	public static boolean disable;

	/*
	 * 
	 */
	public DungeonCharacter(String name,int maxHp,int xCoord,int yCoord, double
			baseChance, int doDamageMax,int doDamageMin, int direction,
			int rows, int columns) {
		//this key word gives me access to all the attributes in the class
		this.name = name;
		this.symbol = this.name.charAt(0);
		this.maxHp = maxHp;
		this.currentHp = this.maxHp;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		/*
		 * base chance = is the probability or chance you have of making
		 * a hit.
		 * for example if you have 40 % chance to succeed or hit you will
		 * need to roll a 60 % or better for it to be counted as a hit.
		 */
		this.baseChance = baseChance;
		this.doDamageMax = doDamageMax;
		this.doDamageMin = doDamageMin;
		this.direction = direction;
		this.rows = rows;
		this.columns = columns;
		/*
		 * if classes are tightly coupled means if you change one 
		 * small thing about the code it will drastically change everything else 
		 * example - game class is tightly covered
		 * 
		 * if classes are loosely coupled ( which is better) is if we 
		 * change one thing about the code it will not greatly affect 
		 * everything else in the code
		 */



	}
	//method overloaded
	/*
	 * we are creating this constructor for test purposes
	 */
	public DungeonCharacter() {
		this.name = "Bob";
		this.symbol = 'B';
		this.maxHp = 700;
		this.currentHp = 700;
		this.xCoord = 0;
		this.yCoord = 0;
		this.baseChance = 1.0; //100% chance of making a hit
		this.doDamageMin = 9;
		this.doDamageMax = 10;
		this.direction = 3;
		this.rows = 8;
		this.columns = 8;


	}
	/*
	 * in this move method we will move the way we are facing
	 * we are going to use code that we previously wrote
	 */
	@Override
	public void move() {
		//index 0 is the y coordinate
		//index 1 is the x coordinate
		//we are getting the new location based on the code we wrote
		int[] newLocation = this.newIncrement();
		this.yCoord =  newLocation[0];
		this.xCoord = newLocation[1];

	}
	/*
	 * we are going to roll a dice and if we roll the correct chance we get to hit
	 * we attack in the location we are facing
	 * we are attacking in the box in front of us
	 * this method is only responsible for a hit in a location it does not worry about the damage
	 */
	@Override
	public int[] attack() {
		//we are attacking a location
		int damage = 0;

		if(this.successfulAction(this.baseChance) && disable == false) {
			damage = MiscUtil.gen_random_int(doDamageMin, doDamageMax);
			System.out.println(this.getName() + " does " + damage + " damage");
		}else if(disable) {
			System.out.println(this.name + " is disabled");
			disable = false;


		}else {
			System.out.println("Totally missed ");
		}

		int[] solution = new int[3]; //first 2 values are the location but the third value is the damage inflicted
		int[] location = this.newIncrement();
		solution[0] = location[0];
		solution[1] = location[1];
		solution[2] = damage;

		return solution;
	}

	public String getName() {
		return this.name;
	}

	public char getSymbol() {
		return this.symbol;
	}
	public int getDirection() {
		return this.direction;
	}
	@Override
	public void changeDirection(int direction) {
		/*
		 * we know the range we will accept 1-4
		 * range = direction
		 */
		direction = this.setVars(1, 4, direction);
		this.direction = direction;

	}
	@Override
	public int[] getLocation() {
		//think rows and columns for position
		//y = row && x = column
		int[] location = {this.yCoord,this.xCoord};

		return location;

	}
	@Override
	/*
	 * This method is going to keep track and subtract from 
	 * the hit points.
	 * it will also keep the integrity of the values,
	 * they will be within a range
	 */
	public void hit(int damage) {
		int newHp = this.currentHp - damage;
		newHp = this.setVars(0, this.maxHp,newHp);
		this.currentHp = newHp;


	}
	@Override
	public boolean isAlive() {
		return this.currentHp > 0;
	}
	@Override
	/*
	 * This method is used to set and monitor the values of 
	 * the range 1-4 if not i will make sure it is
	 * if the user inputs a 5 which is not a set direction
	 * it will change it to 4 a valid input
	 */
	public int setVars(int min, int max, int var) {

		if(var < min) {


			var = min;
		}else if(var > max){
			var = max;
		}

		return var;
	}
	/*
	 * think rows and columns
	 * to go east we will look at our current position and add 1 to the x coordinate
	 * if you go east you will increase the columns
	 * we will create a new array and take the changed x coordinate and 
	 * copy it and make it the new x coordinate and change nothing about
	 * the y coordinate as it will stay the same.
	 */
	private int[] incrementEast() {
		int newX = this.xCoord + 1;
		newX = this.setVars(0, this.columns - 1, newX);

		int[] increment = {this.yCoord, newX};

		return increment;
	}
	private int[] incrementWest() {
		int newX = this.xCoord - 1;
		newX = this.setVars(0, this.columns - 1, newX);

		int[] increment = {this.yCoord, newX};

		return increment;
	}
	private int[] incrementNorth() {
		int newY = this.yCoord - 1;
		newY = this.setVars(0, this.rows - 1, newY);

		int[] increment = {newY, this.xCoord};

		return increment;
	}
	private int[] incrementSouth() {
		int newY = this.yCoord + 1;
		newY = this.setVars(0, this.rows - 1, newY);

		int[] increment = {newY, this.xCoord};

		return increment;
	}
	/*
	 * some constraints of switch statements are it is somewhat limited
	 * you can only have one out put for one case.
	 * unlike if statements where if something doesn't happen you can do else if
	 */
	private int[] newIncrement() {
		switch(this.direction) {
		case 1:
			return this.incrementNorth();
		case 2: 
			return this.incrementEast();
		case 3:
			return this.incrementSouth();
		case 4: 
			return this.incrementWest();
		default:
			System.out.println("opps something went wrong");
			return null;

		}
	}
	@Override
	/*helper method
	 * double chance is used for anything related to a chance
	 * for example if you the character has a special ability there 
	 * is a chance they are able to use this ability so you would 
	 * use this chance double
	 * this can also be used on many different things ,dice roll, hp.
	 */
	public boolean successfulAction(double chance) {
		//rolling dice - generate a random number between 0,1
		Random rand = new Random();
		double dieRoll = rand.nextDouble();
		if(dieRoll <= chance) {
			//have to roll lower than the chance to succeed.
			//if the chance is only 30% you need to roll .3 or less
			return true;
		}else {

			return false;
		}

	}
	/*
	 * this method will translate numbers to string - to make it easier for the user
	 * instead of hearing you are facing 1 or 2 you'd get your facing north or south
	 * you can also use chars if you wanted to 
	 * if you input a number that is not a case i.e 1-4 (invalid input)
	 * you would get not valid direction
	 */
	public String facing() {
		switch(this.direction) {
		case 1:
			return "facing north";
		case 2:
			return "facing east";
		case 3: 
			return "facing south";
		case 4:
			return "facing west";
		default:
			return "Not valid direction";

		}

	}
	@Override
	/*
	 * when the game is ran we want to give info to the user
	 * we are inheriting it from the "object class"
	 * an object class is a built in class in the java language system
	 * we are also overriding the toString method and making it print out 
	 * what we want to the user.
	 */
	public String toString() {
		String info = "Name: " + this.name + "\n";
		info += this.facing() + "\n";
		info += "HP: " + this.currentHp + "\n";

		return info;
	}

	public int getRows() {
		return this.rows;
	}

	public int getColumns() {
		return this.columns;
	}

	public static void setDisable(boolean setI) {
		disable = setI;
	}
	public static boolean getDisable() {

		return disable;
	}
	public int getHP() {
		return this.currentHp;
	}


}
