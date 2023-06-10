package dd;

/*there is no code in the interface 
 * In a base class can have code
 */

public interface IDC {
	/*
	 * you are listing code that will happen but you do no 
	 * know how it will happened
	 */
	public void move();
	public int[] attack();
	public void changeDirection(int direction);
	public int[] getLocation();
	public void hit(int damage);
	public boolean isAlive();
	public int setVars(int min, int max,int var);
	public boolean successfulAction(double chance);

}
