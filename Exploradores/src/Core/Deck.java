package Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

	
	private List<Card> cards;
	private Random r;
	
	public Deck() {
		cards = new ArrayList<>();
		r = new Random();
	}
	
	public static Deck generateDeck() {
		Deck d = new Deck();
		
		for(CardColour c : CardColour.values()) {
			for (int i = 2; i <= 10; i++) {
				d.addCard(new Card(c,i));
			}
			for (int i = 0; i < 3; i++) {
				d.addCard(new Card(c));
			}
		}
		Collections.shuffle(d.cards);
		Collections.shuffle(d.cards);
		return d;
	}
	
	
	
	public void addCard(Card c) {
		this.cards.add(c);
	}
	
	public String toString() {
		return this.cards.toString();
	}
	
	public Card takeCard() {
		int i = r.nextInt(this.cards.size());
		return this.cards.remove(i);
	}

	public List<Card> getCurrentCards() {
		return this.cards;
	}

	public boolean hasCard(Card c) {
		return this.cards.contains(c);
	}

	public void removeCard(Card c) {
		this.cards.remove(c);
	}

	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
	
}
