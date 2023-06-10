package dd;

import java.util.Scanner;

import miscUtil.MiscUtil;

public class Game {
	/*
	 * the game class will have dungeon character and will also have
	 * the board.
	 * it is not a parent class nor is it a child class of anything
	 * simply it will be used in this class, composition.
	 */
	private Scanner scan;
	private boolean keepPlaying = true, gameOver = false;
	int turn;
	DungeonCharacter dc1,dc2; // dc1 -> human && dc2 -> computer
	private Board board;
	private int rows, columns;



	public Game(Scanner scan, int rows, int columns) {
		this.scan = scan;
		this.rows = rows;
		this.columns = columns;

	}
	/*
	 * this method will be composed of an attacker and a defender
	 * that is what we will be passing in 
	 * The dungeon character attacks the cell that is in front of it in the direction it is facing
	 * we want to make it where if we extend this class with this method we can have it attack more than one cell
	 *  we get more than 1 x,y square
	 */
	private void combat(DungeonCharacter attacker, DungeonCharacter defender) {
		int[] solution = attacker.attack(); // see more on attack method
		// solution = combination of their x,y coordinate and the damage it has done
		int [] dLocation = defender.getLocation(); // getting the location of the defender character
		/*
		 * this array is always size 2 and we are getting the x,y coordinates
		 */
		int dy = dLocation [0];//y location is the first element of the array
		int dx = dLocation[1];// x location is the second element of the array
		int size = solution.length; // how many x,y's there are
		//we want to know how many pairs there are
		//if we get an array of 5 that means we receive one for the damage and whoever attacks gives us 4 elements of attack
		//that means there are 2 sets of x,y pairs.
		boolean hit = false;
		/*
		 * we want to check weather or not we hit or the hit connected.
		 */
		if(solution[solution.length - 1] > 0) {
			//this is the last index if the value is 0 forget about it and don't do - zero damage is done
			//if not do this
			for(int i = 0; i <= size - 3; i = i + 2) {
				if(dy == solution[i] && dx == solution[i+1]) {
					System.out.println(attacker.getName() + " hits and does " + 
							solution[solution.length - 1] + " damage.");
					hit = true;
					break; // stop the for-loop
					/*
					 * if i hit you on the first pair why would i bother looking at the second pair
					 */
				}else {
					System.out.println("You missed. ");
				}

			}
		}
		if(hit) {
			defender.hit(solution[solution.length - 1]);
		}

	}
	/*
	 * we know we have a instance variable called game over
	 * keep playing is for playing another game
	 * when is the game Over? when one of the player is dead
	 */
	private void checkGameOver() {
		this.keepPlaying = false;
		if(this.dc1.isAlive() == false) {
			System.out.println(this.dc2.getName() + " has won!");
			System.out.println("do you want to play another game? "
					+ "yes or no");
			String answer = scan.nextLine();
			if(answer == "yes") {
				playGame();
			}else if(answer == "no"){
				this.gameOver = true;
			}
//			System.out.println("something went wrong");

		}else if(this.dc2.isAlive() == false) {
			System.out.println(this.dc1.getName() + " has won!");
			System.out.println("do you want to play another game? "
					+ "yes or no");
			String answer = scan.nextLine();
			if(answer == "yes") {
				playGame();
			}else if(answer == "no"){
				this.gameOver = true;
			}

		}
//		System.out.println("something went wrong");


	}

	/*
	 * moving doesn't ask us for anything, they can just move in the direction they are facing
	 */
	private void move(DungeonCharacter dc) {
		int[] oldLocation = dc.getLocation(); //where the character was standing
		this.board.markBoard(oldLocation[0], oldLocation[1], ' ');// we take the old location and blank it out
		dc.move();
		int []newLocation = dc.getLocation();// move to knew location
		this.board.markBoard(newLocation[0], newLocation[1], dc.getSymbol());//mark the board with the new location


	}

	private void humanChangeDirection() {

		int direction = MiscUtil.get_user_input_int(scan, 1, 4, this.dc1.getName() + 
				" what is your new direction");
		this.dc1.changeDirection(direction);

	}

	private void computerChangeDirection() {
		int direction = MiscUtil.gen_random_int(1, 4);
		this.dc2.changeDirection(direction);
		System.out.println(this.dc2.getName() + " is " + this.dc2.facing());

	}

	private void displayInfo() {
		String info = this.dc1.toString();
		info+= this.dc2.toString();
		System.out.println(info);
		this.board.displayBoard();

	}

	public void playGame() {

		this.initializeGame();
		while(!this.gameOver) {
			this.takeTurns();

		}
		//you can add a while loop at the top asking if they want to play again



	}

	private void initializeGame() {
		initializeCharacters();
		this.board = new Board(this.rows,this.columns);
		int[] humanLocation = dc1.getLocation();
		this.board.markBoard(humanLocation[0], humanLocation[1],dc1.getSymbol());
		int[] computerLocation  = dc2.getLocation();
		this.board.markBoard(computerLocation[0], computerLocation[1],dc2.getSymbol());
		this.displayInfo();
		this.gameOver = false;
		this.keepPlaying = true;
		this.turn = 0;
	}
	/*
	 * this method is responsible for:
	 * who's turn it is,
	 * take that turn,
	 * and clean up everything
	 */
	private void takeTurns() {
		if(this.turn  % 2 == 0) {
			this.takeHumanTurn();
		}else {
			this.takeComputerTurn();
		}
		this.checkGameOver();
		this.turn++;
		this.displayInfo();

	}

	private void takeHumanTurn() {
		System.out.println(dc1.getName() + " make your choice");
		int choice = MiscUtil.get_user_input_int(this.scan, 1, 3, 
				" 1 Change direction\n 2 Move\n 3 Combat");
		this.takeTheTurn(dc1, dc2,choice);

	}

	/*
	 * instead of having two very similar methods that will take turn
	 * for the computer character and a method that will take a  turn
	 * for the human character we will create one singular method that will take 
	 * both of their turns in one method
	 * in this method we will be passing in the dungeon character as a parameter.
	 */
	private void takeTheTurn(DungeonCharacter dca,DungeonCharacter dcd, int choice) {
		/*
		 * switch cases is used when there are a few conditions that we need
		 * if statements is when there is a lot of conditions
		 */
		switch(choice){
		case 1:
			/*
			 * the computer will pick a random number that correlated to a direction
			 * and change based on the  number it randomly picked
			 */
			System.out.println(dca.getName() + " changes directions");
			if(this.turn % 2 == 0) {
				this.humanChangeDirection();
			}else {
				this.computerChangeDirection();
			}
			break;


		case 2:
			/*
			 * the computer will move in the direction it is facing
			 */
			System.out.println(dca.getName() + " moves");
			this.move(dca);
			break;
			/*
			 * this case will make the computer attack
			 * the computer is the attacker and the human is the defender
			 */
		case 3:
			System.out.println(dca.getName() + " attacks");
			this.combat(dca, dcd);
			break;
		default:
			System.out.println("Something went wrong");

		}


	}
	private void takeComputerTurn() {
		int choice = MiscUtil.gen_random_int(1, 3);
		this.takeTheTurn(dc2, dc1, choice);

	}

	private void initializeCharacters() {
		/*
		 * we want to create an array that will hold the values of the parameters 
		 * this will make it easier for us when we instantiate the characters
		 * the problem is that we have different data types int's, Strings, and doubles
		 * to get around this we will create an array of objects
		 */
		Object [] params = new Object[10];
		params[1] = 100;//hp
		params[4] = 0.6;//base chance
		params[5] = 20;//max damage
		params[6] = 4;//min damage
		params[8] = this.rows;
		params[9] = this.columns;

		initializeHumanCharacter(params);
		initializeComputerCharacter(params);
	}
	/*
	 * creating the human Character
	 * this is a helper method - it will be used in another method(initialize characters)
	 */
	private void initializeHumanCharacter(Object[]params) {
		System.out.println("Human enter your name: ");
		params[0] = this.scan.nextLine();
		params[2] = 0;
		params[3] = 0;
		params[7] = 3;

		this.dc1 = instantiateWorrior(params);


		int choice = MiscUtil.get_user_input_int(scan, 1, 4,
				"Do you want to be a Warrior, Troll, Cleric or Goblin\n1 for Warrior, "
				+ "2 for Troll\n 3 for Cleric \n 4 for Goblin");

		//instead of having a lot of if statements else if, else if, else if
		//you can use a switch case since we know that the input from the user 
		//is an atomic number
		switch(choice){
		case 1:
			this.dc1 = instantiateWorrior(params);
			break;
		case 2:
			this.dc1 = instantiateTroll(params);
			break;
		case 3:
			this.dc1 = instantiateCleric(params,false);
			break;
		case 4:
			this.dc1 = instantiateGoblin(params);
			break;
			
		}
		System.out.println("Welcome " + this.dc1.getName());
		System.out.println();
		/*
		 * String name, 0 
		 * int maxHp, 1
		 * int xCoord, 2
		 * int yCoord, 3
		 * double baseChance, 4
		 * int doDamageMax, 5
		 * doDamageMin, 6
		 * int direction, 7
		 * introws, 8 
		 * int columns, 9 
		 */

	}
	
	/*
	 * this is how you create a dungeon character.
	 * for now
	 * this is a helper method - it will be used in another method(initialize characters)
	 */
	private void initializeComputerCharacter(Object[] params) {
		/*
		 * the computer character will start at the bottom right corner
		 */
		params[0] = "Hal3000";
		
		params[2] = this.columns - 1;
		params[3] = this.rows - 1;
		params[7] = 1;
		
		int choice = MiscUtil.gen_random_int(1, 3);
		
		switch(choice){
		case 1:
			this.dc2 = instantiateWorrior(params);
			System.out.println(this.dc2.getName() + " chooses Warrior character");
			break;
		case 2:
			this.dc2 = instantiateTroll(params);
			System.out.println(this.dc2.getName() + " chooses Troll character");
			break;
		case 3:
			this.dc2 = instantiateGoblin(params);
			System.out.println(this.dc2.getName() + " chooses Goblin character");
			break;
		case 4:
			this.dc2 = instantiateCleric(params,false);
			System.out.println(this.dc2.getName() + " chooses Cleric character");
		}
		System.out.println("Welcome " + this.dc2.getName());
		System.out.println();
		
	}

	/*
	 * since the Dungeon Character class is now abstract we will not be able to instantiate it
	 * in the game class.
	 * we now will ask the user if they want to be a hero or a monster.
	 * we will make helper methods that will help us out.
	 */
	// we will have all the attributes to normal dungeon character but on top of that we will be able to block
	private DungeonCharacter instantiateWorrior(Object[] params) {
		double chance_to_block = 0.4;
		DungeonCharacter dc = new Warrior((String)params[0],(int)params[1],
				(int)params[2],
				(int)params[3],(double)params[4],(int)params[5],
				(int)params[6],(int)params[7],
				(int)params[8],(int)params[9],chance_to_block);
		return dc;
	}
	/*
	 * we do the same for the cleric class
	 */
	private DungeonCharacter instantiateCleric(Object[] params, boolean isHuman) {
		double chance_to_block = 0.4;
		DungeonCharacter dc = new Cleric((String)params[0],(int)params[1],
				(int)params[2],
				(int)params[3],(double)params[4],(int)params[5],
				(int)params[6],(int)params[7],
				(int)params[8],(int)params[9],chance_to_block,this.scan, isHuman);
		return dc;
	}

	private DungeonCharacter instantiateTroll(Object[] params) {
		double chance_to_regen = 0.4;
		int min_regen= 3, max_regen = 12;
		double chanceToBoulder = 0.6;
		DungeonCharacter dc = new Troll((String)params[0],(int)params[1],
				(int)params[2],
				(int)params[3],(double)params[4],(int)params[5],
				(int)params[6],(int)params[7],
				(int)params[8],(int)params[9],chance_to_regen,min_regen,
				max_regen,chanceToBoulder);
		return dc;
	}
	private DungeonCharacter instantiateGoblin(Object[] params) {
		double chance_to_regen = 0.4;
		int min_regen= 3, max_regen = 12;
		double chanceToDisarm = 0.4;
		DungeonCharacter dc = new Troll((String)params[0],(int)params[1],
				(int)params[2],
				(int)params[3],(double)params[4],(int)params[5],
				(int)params[6],(int)params[7],
				(int)params[8],(int)params[9],chance_to_regen,min_regen,
				max_regen,chanceToDisarm);
		return dc;
	}

}
