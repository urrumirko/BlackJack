package com.blackjack.service;

import java.util.Scanner;

import com.blackjack.models.Deck;
import com.blackjack.utils.DeckUtils;

/**
 * 
 * @author mirkourru
 *
 */
public class BlackJackManager {

	public static boolean endRound = false;

	public static void round(Deck playingDeck, Deck playerCards, Deck dealerCards, Scanner userInput) {
		// Take Bet
		double playerBet = 0.0;
		endRound = false;
		do {
			System.out.println("You have $" + playerCards.getPlayerMoney() + ", how much would you like to bet?");
			playerBet = userInput.nextDouble();

		} while (!DeckUtils.isBetValid(playerCards.getPlayerMoney(), playerBet));

		System.out.println("Dealing...");
		// Player gets two cards
		playerCards.draw(playingDeck);
		playerCards.draw(playingDeck);

		// Dealer gets two cards
		dealerCards.draw(playingDeck);
		dealerCards.draw(playingDeck);

		// While loop for drawing new cards
		while (true) {
			// Display player cards
			System.out.println("Your Hand:" + playerCards.toString());

			// Display Value
			System.out.println("Your hand is currently valued at: " + DeckUtils.cardsValue(playerCards));
			if (DeckUtils.cardsValue(playerCards) == 21) {
				System.out.println("BLACK JACK!");
				ifPlayerWin(playerCards, dealerCards, playerBet);
				// End of hand - put cards back in deck
				playerCards.moveAllToDeck(playingDeck);
				dealerCards.moveAllToDeck(playingDeck);
				System.out.println("End of Hand.");
				return;
			}
			// Display dealer cards
			System.out.println("Dealer Hand: " + dealerCards.getCard(0).toString() + " and [hidden]");

			// What do they want to do
			System.out.print("\n     The Dealer asks: do you want a HIT or will you STAY?\"\n\n");

			System.out.print("     _______________ Options _____________ \n");
			System.out.print("     |                                    |\n");
			System.out.print("     |          (H)it       (S)tay        |\n");
			System.out.print("     |____________________________________|\n\n     ");

			String response = userInput.next();
			// They hit
			if (response.equalsIgnoreCase("H")) {
				hit(playingDeck, playerCards, playerBet);
				if (endRound)
					break;
			}

			// Stand
			if (response.equalsIgnoreCase("S")) {
				break;
			}

		}

		// Reveal dealer cards
		if (!endRound)
			revealDealerCards(playingDeck, playerCards, dealerCards, playerBet);

		// Determinate if is Push
		if (!endRound)
			isPush(playerCards, dealerCards);

		// Determine if player wins
		if (!endRound)
			ifPlayerWin(playerCards, dealerCards, playerBet);

		// End of hand - put cards back in deck
		playerCards.moveAllToDeck(playingDeck);
		dealerCards.moveAllToDeck(playingDeck);
		System.out.println("End of Hand.");

	}

	public static void ifPlayerWin(Deck playerCards, Deck dealerCards, double playerBet) {
		if ((DeckUtils.cardsValue(playerCards) > DeckUtils.cardsValue(dealerCards)) && !endRound) {
			System.out.println("You win the hand.");
			playerCards.setPlayerMoney(playerCards.getPlayerMoney() + playerBet);
			endRound = true;
		}
	}

	public static void isPush(Deck playerCards, Deck dealerCards) {
		// Determine if push
		if ((DeckUtils.cardsValue(dealerCards) == DeckUtils.cardsValue(playerCards)) && endRound == false) {
			System.out.println("Push.");
			endRound = true;
		}
	}

	public static void revealDealerCards(Deck playingDeck, Deck playerCards, Deck dealerCards, double playerBet) {
		System.out.println("Dealer Cards:" + dealerCards.toString());
		// See if dealer has more points than player
		if ((DeckUtils.cardsValue(dealerCards) > DeckUtils.cardsValue(playerCards)) && endRound == false) {
			System.out.println("Dealer beats you " + DeckUtils.cardsValue(dealerCards) + " to "
					+ DeckUtils.cardsValue(playerCards));
			playerCards.setPlayerMoney(playerCards.getPlayerMoney() - playerBet);
			endRound = true;
			return;
		}

		// Dealer hits at 16 stands at 17
		while ((DeckUtils.cardsValue(dealerCards) < 17) && endRound == false) {
			dealerCards.draw(playingDeck);
			System.out.println("Dealer draws: " + dealerCards.getCard(dealerCards.deckSize() - 1).toString());
		}
		// Display value of dealer
		System.out.println("Dealers hand value: " + DeckUtils.cardsValue(dealerCards));
		// Determine if dealer busted
		if ((DeckUtils.cardsValue(dealerCards) > 21) && endRound == false) {
			System.out.println("Dealer Busts. You win!");
			playerCards.setPlayerMoney(playerCards.getPlayerMoney() + playerBet);
			endRound = true;
			return;
		} else if ((DeckUtils.cardsValue(dealerCards) > DeckUtils.cardsValue(playerCards)) && endRound == false) {
			System.out.println("Dealer beats you " + DeckUtils.cardsValue(dealerCards) + " to "
					+ DeckUtils.cardsValue(playerCards));
			playerCards.setPlayerMoney(playerCards.getPlayerMoney() - playerBet);
			endRound = true;
		}

	}

	public static void hit(Deck playingDeck, Deck playerCards, double playerBet) {

		playerCards.draw(playingDeck);
		System.out.println("You draw a:" + playerCards.getCard(playerCards.deckSize() - 1).toString());
		System.out.println("Player hand value: " + DeckUtils.cardsValue(playerCards));
		// Bust if they go over 21
		if (DeckUtils.cardsValue(playerCards) > 21) {
			System.out.println("Bust. Currently valued at: " + DeckUtils.cardsValue(playerCards));
			playerCards.setPlayerMoney(playerCards.getPlayerMoney() - playerBet);
			endRound = true;

		}
	}

}
