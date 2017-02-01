package com.blackjack.models;

import java.util.ArrayList;
import java.util.Random;

import com.blackjack.values.Suit;
import com.blackjack.values.Value;
/**
 * 
 * @author mirkourru
 *
 */
public class Deck {

	public ArrayList<Card> cards;
	public double playerMoney;

	/**
	 * Create a new deck of playing cards
	 */
	public Deck() {
		
		this.cards = new ArrayList<Card>();
	}

	/**
	 * Remove a card from the deck
	 */
	public void removeCard(int i) {
		this.cards.remove(i);
	}

	/**
	 * Get card from deck
	 * @param index
	 * @return card
	 */
	public Card getCard(int i) {
		return this.cards.get(i);
	}

	/**
	 * Add card to deck
	 * @param Card to add
	 */
	public void addCard(Card addCard) {
		this.cards.add(addCard);
	}

	/**
	 * Draw a top card from deck
	 * @param Deck comingFrom
	 */
	public void draw(Deck comingFrom) {
		// Add card to this deck from whatever deck its coming from
		this.cards.add(comingFrom.getCard(0));
		// Remove the card in the deck its coming from
		comingFrom.removeCard(0);
	}
	
	public double getPlayerMoney() {
		return playerMoney;
	}

	public void setPlayerMoney(double playerMoney) {
		this.playerMoney = playerMoney;
	}

	/**
	 * Use to print out deck
	 */
	public String toString() {
		String cardListOutput = "";
		int i = 0;
		for (Card aCard : this.cards) {
			cardListOutput += "\n" + aCard.toString();
			i++;
		}
		return cardListOutput;
	}

	public void moveAllToDeck(Deck moveTo) {
		int thisDeckSize = this.cards.size();
		// put cards in moveTo deck
		for (int i = 0; i < thisDeckSize; i++) {
			moveTo.addCard(this.getCard(i));
		}
		// empty out the deck
		for (int i = 0; i < thisDeckSize; i++) {
			this.removeCard(0);
		}
	}

	public int deckSize() {
		return this.cards.size();
	}
}
