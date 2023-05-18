package algorithms.chapter2.section1;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import algorithms.Common;

public class Deck {

	private Card[] cards;
	
	public Deck() {
		int totalCards = Suit.values().length * CardType.values().length;
		
		Card[] generatedcards = new Card[totalCards];
		 
		int i = 0;
		for (Suit suit : Suit.values()) {
			for (CardType cardType : CardType.values()) {
				generatedcards[i++] = new Card(suit, cardType);
			}
		}
		
		List<Card> list = Arrays.asList(generatedcards);
		Collections.shuffle(list);
		this.cards = list.toArray(new Card[0]);
	}
	
	public void sort() {
		Insertion.sort(cards);
	}
	
	/**
	 * Kind of bubble sort.
	 */
	public void dequeueSort() {
		int n = cards.length;
		boolean swapped;
		
		do {
			swapped = false;
			for (int i = 0; i < n - 1; i++) {
				if (Common.less(cards[i + 1], cards[i])) {
					Common.exchange(cards, i + 1, i);
					swapped = true;
				}
			}
			
		} while (swapped);
	}
	
	public void show(PrintStream out) {
		for (Card card : cards) {
			out.println(card);
		}
	}
	
	public static void main(String[] args) {
		Deck deck = new Deck();
//		deck.sort();
		deck.dequeueSort();
		deck.show(System.out);
	}
	
	
	
	private class Card implements Comparable<Card> {
		
		private Suit suit;
		private CardType type;
		
		public Card(Suit suit, CardType type) {
			this.suit = suit;
			this.type = type;
		}

		@Override
		public int compareTo(Card o) {
			if (this.suit.order == o.suit.order) {
				return this.type.value - o.type.value;
			}
			
			return this.suit.order - o.suit.order;
		}
		
		@Override
		public String toString() {
			return "Suit = " + this.suit + "\t| Type = " + type;
		}
		
	}
	
	private enum CardType {
		A(1),
		TWO(2),
		THREE(3),
		FOUR(4),
		FIVE(5),
		SIX(6),
		SEVEN(7),
		EIGHT(8),
		NINE(9),
		TEN(10),
		JACK(11),
		QUEEN(12),
		KING(13);
		
		private final int value;
		
		CardType(int value) {
			this.value = value;
		}
		
	}
	
	private enum Suit {
		SPADE(1),
		HEART(2),
		CLUB(3),
		DIAMOND(4);
		
		private final int order;
		
		Suit(int order) {
			this.order = order;
		}
	}

}
