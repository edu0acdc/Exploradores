package Game;

import Core.Card;
import Core.Deck;
import Exceptions.CLIAlreadyCreatedException;
import Exceptions.GameManagerAlreadyCreatedException;
import Exceptions.IllegalMovementException;
import Interface.CLI;

public class GameManager {

	public static final int NUMBER_OF_CARDS = 8;
	public int turn;
	public Player p1;
	public Player p2;
	public Deck d;
	public SharedBoard sb;
	public boolean gameRunning;
	private static GameManager singleton = null;
	private CLI cli;

	private GameManager(String p1,String p2) throws CLIAlreadyCreatedException {
		this.p1 = new Player(p1);
		this.p2 = new Player(p2);
		gameRunning = false;
		turn = 0;
		cli = CLI.createCLI();
	}


	public static GameManager createManager(String p1,String p2) throws GameManagerAlreadyCreatedException, CLIAlreadyCreatedException {
		if(singleton == null) {
			singleton = new GameManager(p1, p2);
			return singleton;
		}
		else {
			throw new GameManagerAlreadyCreatedException();
		}
	}

	public void destroyGame() {
		gameRunning = false;
		d = null;
		sb = null;
		p1.clearGame();
		p2.clearGame();
	}
	
	private void cliTurnPlayer(Player p) {
		boolean allowed = false;
		GameAction ga = cli.takeTurn(p);
		if(ga.getAction() == Action.HAND_TO_BOARD) {
			allowed = handToBoard(p,ga.getCard());
		}
		else if (ga.getAction() == Action.HAND_TO_SHAREBOARD) {
			allowed = handToShareboard(p,ga.getCard());
		}
		if(allowed) {
			ga = cli.askDrawOrBoard(p,sb);
			if(ga.getAction() == Action.DRAW_CARD) {
				p.addCardToHand(d.takeCard());
			}
			else if(ga.getAction() == Action.TAKE_FROM_SHAREBOARD) {
				p.addCardToHand(sb.takeCard(ga.getColourToTake()));
			}
		}
	}
	
	
	private void cliGame() {
		if(turn == 0) {
			cliTurnPlayer(p1);
		}
		else if(turn == 1) {
			cliTurnPlayer(p2);
		}
		turn = turn == 1 ? 0 : 1;
	}


	public void newGame() {
		this.d = Deck.generateDeck();
		this.sb = new SharedBoard();
		drawCards();
		while(!d.isEmpty()) {
			gameRunning = true;
			cliGame();
		}
		
		gameRunning = false;
		
	}

	private boolean handToShareboard(Player p, Card card) {
		try {
			p.removeCard(card);
			sb.addCard(card);
		}catch (IllegalMovementException e) {
			return false;
		}
		return true;
	}


	private boolean handToBoard(Player p, Card card) {
		try {
			p.removeCard(card);
			p.addCardToBoard(card);
		}catch (IllegalMovementException e) {
			return false;
		}
		return true;
		
	}


	private void drawCards() {
		for (int i = 0; i < NUMBER_OF_CARDS; i++) {
			this.p1.addCardToHand(this.d.takeCard());
		}

		for (int i = 0; i < NUMBER_OF_CARDS; i++) {
			this.p2.addCardToHand(this.d.takeCard());
		}
	}
}
