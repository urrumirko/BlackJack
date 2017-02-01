package com.blackjack;

import java.util.Scanner;

import com.blackjack.models.Deck;
import com.blackjack.service.BlackJackManager;
import com.blackjack.utils.DeckUtils;

/**
 * This is a Java console BlackJack game
 * @author mirkourru
 *
 */
public class Blackjack {

	public static void main(String[] args) {

		// playingDeck will be the deck the dealer holds
		Deck playingDeck = new Deck();
		DeckUtils.createFullDeck(playingDeck);
		DeckUtils.shuffle(playingDeck);

		// playerCards will be the cards the player has in their hand
		Deck playerCards = new Deck();

		playerCards.setPlayerMoney(1000.0);
		
		// dealerCards will be the cards the dealer has in their hand
		Deck dealerCards = new Deck();

		// Scanner for user input
		Scanner userInput = new Scanner(System.in);

		// Play the game while the player has money
		// Game loop
		System.out.println("\n                   Welcome to Blackjack!  \n");
		System.out.print("\n                    ________ Main Menu _______\n");
		System.out.print("                    |                        |\n");
		System.out.print("                    |    1 Play BlackJack    |\n");
		System.out.print("                    |    2 Instructions      |\n");
		System.out.print("                    |    3 Quit              |\n");
		System.out.print("                    |________________________|\n\n\n");
		System.out.print("                       Player Pocket = " + playerCards.getPlayerMoney() + "\n");
		
		System.out.println("Please indicate a voice:");
		int choice = userInput.nextInt();

		switch (choice) {
		case 1:
			BlackJackManager.round(playingDeck, playerCards, dealerCards, userInput);
			break;
		case 2:
			Instructions();
			break;
		case 3:
			break;
		default:
			System.out.println("     Sorry, that was an invalid choice.\n");
			break;
		}
		
		while (playerCards.getPlayerMoney() > 0) {
			BlackJackManager.round(playingDeck, playerCards, dealerCards, userInput);
		}
		// Game is over
		System.out.println("Game over! You lost all your money. :(");

		// Close Scanner
		userInput.close();

	}

	public static void Instructions() {
		System.out.print("\n\n      Hey , this is a short, fast card game of luck and skill.\n");
		System.out.print("          The dealer will start you with two cards from the deck.\n");
		System.out.print("          Your goal is to draw cards to get as close to the number\n");
		System.out.print("          21 as possible. If you go over 21, you are BUSTED. If you\n");
		System.out.print("          get closer to 21 than the dealer but not over, you win.\n");
		System.out.print("          The card values are:\n");
		System.out.print("\n             DEUCES = 2          EIGHT = 8");
		System.out.print("\n             THREE =  3          NINE =  9");
		System.out.print("\n             FOUR =   4          TEN =   10 ");
		System.out.print("\n             FIVE =   5          JACK =  10");
		System.out.print("\n             SIX =    6          QUEEN = 10");
		System.out.print("\n             SEVEN =  7          KING =  10");
		System.out.print("\n                                 ACE =   11 or 1\n");
		System.out.print("\n          ACEs are 1 or 11 depending on which value is advantageous.\n\n");

	}
}
