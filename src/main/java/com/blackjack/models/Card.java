package com.blackjack.models;

import com.blackjack.values.Suit;
import com.blackjack.values.Value;
/**
 * 
 * @author mirkourru
 *
 */
public class Card {

	private Suit suit;
	private Value value;
	
	public Card(Suit suit, Value value){
		this.suit = suit;
		this.value = value;
	}
	
	public String toString(){
		return this.suit.toString() + "-" + this.value.toString();
	}
	
	public Value getValue(){
		return this.value;
	}


	
}
