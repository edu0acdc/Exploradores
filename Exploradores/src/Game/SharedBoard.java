package Game;

import java.util.EnumMap;
import java.util.Stack;

import Core.Card;
import Core.CardColour;

public class SharedBoard {

	private EnumMap<CardColour, Stack<Card>> board;
	
	
	public SharedBoard() {
		board = new EnumMap<>(CardColour.class);
		for (CardColour c : CardColour.values()) {
			board.put(c,new Stack<>());
		}
	}
	
	
	public void addCard(Card c) {
		Stack<Card> s = board.get(c.getColour());
		s.push(c);
		board.put(c.getColour(), s);
	}
	
	
	public Card takeCard(CardColour colour){
		Stack<Card> s = board.get(colour);
		Card c = s.pop();
		board.put(colour, s);
		return c;
	}


	public boolean isEmpty() {
		for (Stack<Card> s : board.values()) {
			if(!s.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean colourIsEmpty(CardColour cc) {
		return board.get(cc).isEmpty();
	}
	
	@Override
	public String toString() {
		StringBuilder bob = new StringBuilder();
		for (CardColour colour : CardColour.values()) {
			bob.append(colour+" "+this.board.get(colour)+" | ");
		}
		return bob.toString();
	}
}
