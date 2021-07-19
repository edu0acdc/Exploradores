package Game;

import Core.Card;
import Core.CardColour;

public class GameAction {

	
	private Player p;
	private Action action;
	private Card c;
	private CardColour colourToTake;
	public GameAction(Player p) {
		this.p = p;
	}	
	
	public void setColourToTake(CardColour colourToTake) {
		this.colourToTake = colourToTake;
	}
	
	public CardColour getColourToTake() {
		return colourToTake;
	}
	
	
	public void setCard(Card c) {
		this.c = c;
	}
	
	public Card getCard() {
		return c;
	}
	
	
	public Player getPlayer() {
		return p;
	}
	
	
	public Action getAction() {
		return action;
	}
	
	public void setAction(Action action) {
		this.action = action;
	}
	
}
