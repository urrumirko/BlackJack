package com.blackjack.utils;

import java.util.ArrayList;
import java.util.Random;

import com.blackjack.models.Card;
import com.blackjack.models.Deck;
import com.blackjack.values.Suit;
import com.blackjack.values.Value;

public class DeckUtils {
	
	/**
	 * 	Add 52 playing cards to a deck
	 * @param deck
	 */
	public static void createFullDeck(Deck deck) {
		// Generate Cards
		// Loop Through Suits
		for (Suit cardSuit : Suit.values()) {
			// Loop through Values
			for (Value cardValue : Value.values()) {
				// Add new card to the mix
				deck.cards.add(new Card(cardSuit, cardValue));
			}
		}
	}

	/**
	 * Shuffle deck of cards
	 * @param deck of cards
	 */
	public static void shuffle(Deck deck) {
		// Create a new arraylist to hold the shuffled cards temporarily
		ArrayList<Card> tmpDeck = new ArrayList<Card>();
		// Randomly pick from the old deck and copy values to the new deck
		Random random = new Random();
		int randomCardIndex = 0;
		int originalSize = deck.cards.size();
		for (int i = 0; i < originalSize; i++) {
			// gen random num according to int randomNum = rand.nextInt((max -
			// min) + 1) + min;
			randomCardIndex = random.nextInt((deck.cards.size() - 1 - 0) + 1) + 0;
			// throw random card into new deck
			tmpDeck.add(deck.cards.get(randomCardIndex));
			// remove picked from old deck
			deck.cards.remove(randomCardIndex);
		}
		// set this.deck to our newly shuffled deck
		deck.cards = tmpDeck;
	}

	/**
	 * Calculate the value of deck
	 * @param deck
	 * @return numeric values
	 */
	public static int cardsValue(Deck deck) {
		int totalValue = 0;
		int aces = 0;
		// For every card in the deck
		for (Card aCard : deck.cards) {
			// Switch of possible values
			switch (aCard.getValue()) {
			case TWO:
				totalValue += 2;
				break;
			case THREE:
				totalValue += 3;
				break;
			case FOUR:
				totalValue += 4;
				break;
			case FIVE:
				totalValue += 5;
				break;
			case SIX:
				totalValue += 6;
				break;
			case SEVEN:
				totalValue += 7;
				break;
			case EIGHT:
				totalValue += 8;
				break;
			case NINE:
				totalValue += 9;
				break;
			case TEN:
				totalValue += 10;
				break;
			case JACK:
				totalValue += 10;
				break;
			case QUEEN:
				totalValue += 10;
				break;
			case KING:
				totalValue += 10;
				break;
			case ACE:
				aces += 1;
				break;
			}
		}

		// Determine the total current value with aces
		// Aces worth 11 or 1 - if 11 would go over 21 make it worth 1
		for (int i = 0; i < aces; i++) {
			// If they're already at over 10 getting an ace valued at 11 would
			// put them up to 22, so make ace worth one
			if (totalValue > 10) {
				totalValue += 1;
			} else {
				totalValue += 11;
			}
		}

		// Return
		return totalValue;

	}
	
	public static boolean isBetValid(double playerMoney, double playerBet) {

		if (playerBet > playerMoney) {
			// Break if they bet too much
			System.out.println("You cannot bet more than you have.");
			return false;
		}
		return true;
	}

}
