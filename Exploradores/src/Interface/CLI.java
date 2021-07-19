package Interface;

import java.util.List;
import java.util.Scanner;

import Core.Card;
import Core.CardColour;
import Exceptions.CLIAlreadyCreatedException;
import Game.Action;
import Game.GameAction;
import Game.Player;
import Game.SharedBoard;

public class CLI {

	private static CLI singleton = null;
	private Scanner reader;
	
	
	private CLI() {
		reader = new Scanner(System.in);
	}
	
	public static CLI createCLI() throws CLIAlreadyCreatedException {
		if(singleton == null) {
			singleton = new CLI();
			return singleton;
		}
		else {
			throw new CLIAlreadyCreatedException();
		}
	}
	
	public GameAction takeTurn(Player p) {
		System.out.println("PLAYER: "+p.getName());
		System.out.println("Your Hand: ");
		List<Card> hand = p.getCurrentHand();
		int i = 1;
		for (Card c : hand) {
			System.out.println(i+" - " + c);
			i += 1;
		}
		System.out.print("Your board: "+p.getBoardState());
		System.out.print("\nCard to play\n> ");
		int i_card = reader.nextInt() - 1;
		
		GameAction action = new GameAction(p);
		action.setCard(hand.get(i_card));
		
		System.out.print("1 - To your board\n2 - To the shareboard\n> ");
		int a = reader.nextInt();
		if(a == 1) {
			action.setAction(Action.HAND_TO_BOARD);
		}
		else if(a == 2) {
			action.setAction(Action.HAND_TO_SHAREBOARD);
		}
		
		return action;
		
		
	}

	public GameAction askDrawOrBoard(Player p,SharedBoard sb) {
		GameAction action = new GameAction(p);
		if(sb.isEmpty()){
			action.setAction(Action.DRAW_CARD);
			return action;
		}
		System.out.println("SHAREBOARD: "+sb.toString());
		
		System.out.print("1 - Draw card\n2 - Take from shareboard\n> ");
		int choice = reader.nextInt();
		
		if(choice == 1) {
			action.setAction(Action.DRAW_CARD);
		}
		else if(choice == 2) {
			action.setAction(Action.TAKE_FROM_SHAREBOARD);

			while(true) {
				CardColour[] clrs = CardColour.values();
				for (int i = 0; i < clrs.length; i++) {
					System.out.println(i +" - "+clrs[i]);
				}
				System.out.print("> ");
				choice = reader.nextInt();
				CardColour cc = clrs[choice];
				if(sb.colourIsEmpty(cc)) {
					System.out.println("No card to take!");
				}
				else {
					action.setColourToTake(cc);
					break;
				}
			}
			
		}
		
		return action;
	}
	
	
	
	
}
