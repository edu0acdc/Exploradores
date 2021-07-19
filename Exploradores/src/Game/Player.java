package Game;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import Core.Card;
import Core.CardColour;
import Core.Deck;
import Exceptions.IllegalMovementException;

public class Player {

	private Deck hand;
	private EnumMap<CardColour, List<Card>> board;
	private String name;


	public Player(String name) {
		this.name = name;
		this.hand = new Deck();
		this.board = new EnumMap<>(CardColour.class);
		for(CardColour c : CardColour.values()) {
			this.board.put(c, new ArrayList<>());
		}
	}

	public String getName() {
		return name;
	}

	public List<Card> getCurrentHand() {
		return this.hand.getCurrentCards();
	}

	public boolean removeCard(Card c) throws IllegalMovementException {
		if(!this.hand.hasCard(c)) {
			throw new IllegalMovementException();
		}
		this.hand.removeCard(c);
		return true;
	}

	public void addCardToHand(Card c) {
		this.hand.addCard(c);
	}

	public void addCardToBoard(Card c) throws IllegalMovementException {
		List<Card> current_list = this.board.get(c.getColour());
		if(c.isHand()) {
			if(!current_list.isEmpty()) {
				throw new IllegalMovementException();
			}
			else {
				current_list.add(c);
				this.board.put(c.getColour(), current_list);
			}
		}
		else {
			if(!current_list.isEmpty()) {
				Card last = current_list.get(current_list.size() - 1);
				if(c.getNumber() <= last.getNumber()) {
					throw new IllegalMovementException();
				}
			}
			current_list.add(c);
			this.board.put(c.getColour(), current_list);
		}
	}

	public String getBoardState() {
		StringBuilder bob = new StringBuilder();
		for (CardColour colour : CardColour.values()) {
			bob.append(colour+" "+this.board.get(colour)+" | ");
		}
		return bob.toString();
	}

	public void clearGame() {
		this.board = new EnumMap<>(CardColour.class);
		this.hand = new Deck();
	}


}
