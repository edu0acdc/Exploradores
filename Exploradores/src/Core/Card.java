package Core;

public class Card {


	private CardColour colour;
	private int number;
	private boolean isHand;

	public Card(CardColour colour,int number) {
		this.colour = colour;
		this.number = number;
		this.isHand = false;
	}

	public Card(CardColour colour) {
		this.colour = colour;
		this.isHand = true;
	}

	public boolean isHand() {
		return isHand;
	}

	public CardColour getColour() {
		return colour;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		StringBuilder bob = new StringBuilder("Colour:"+this.colour);
		if(isHand) {
			bob.append(" Hand Card");
		}
		else {
			bob.append(" Number:"+this.number);
		}
		return bob.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Card)) {
			return false;
		}
		Card c2 = (Card) obj;
		return !(c2.number != this.number || c2.colour != this.colour || c2.isHand != this.isHand);
	}



}
