package Run;

import Exceptions.CLIAlreadyCreatedException;
import Exceptions.GameManagerAlreadyCreatedException;
import Game.GameManager;

public class Runner {

	
	public static void main(String[] args) throws GameManagerAlreadyCreatedException, CLIAlreadyCreatedException {
		GameManager manager = GameManager.createManager("Edu", "Vera");
		
		manager.newGame();
		
	}
}
