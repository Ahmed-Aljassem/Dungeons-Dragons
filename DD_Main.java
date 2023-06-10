package dd;
import java.util.Scanner;

public class DD_Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Game theGame = new Game(scan,8,8);
		theGame.playGame();
		

	}

}
