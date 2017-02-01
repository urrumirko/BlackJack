package com.blackjack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.blackjack.models.Card;
import com.blackjack.models.Deck;
import com.blackjack.service.BlackJackManager;
import com.blackjack.utils.DeckUtils;
import com.blackjack.values.Suit;
import com.blackjack.values.Value;

public class BlackJackTest {

	public Deck playingDeck = new Deck();

	@Before
	public void setUp() throws Exception {
		playingDeck = new Deck();
		DeckUtils.createFullDeck(playingDeck);
		DeckUtils.shuffle(playingDeck);
	}

	@Test
	public void testCheckBet() {
		Deck playerCards = new Deck();
		playerCards.setPlayerMoney(1000);
		double bet = 1100;

		Assert.assertFalse(DeckUtils.isBetValid(playerCards.getPlayerMoney(), bet));
	}

	@Test
	public void testRealisticScenarioPlayerWins() {
		Deck playerCards = new Deck();
		Deck dealerCards = new Deck();
		playerCards.setPlayerMoney(1000);
		BlackJackManager.endRound = false;

		Card playerCard1 = new Card(Suit.CLUB, Value.TEN);
		Card playerCard2 = new Card(Suit.DIAMOND, Value.JACK);

		// Player gets two cards
		playerCards.addCard(playerCard1);
		playerCards.addCard(playerCard2);

		Card dealerCard1 = new Card(Suit.CLUB, Value.EIGHT);
		Card dealerCard2 = new Card(Suit.DIAMOND, Value.NINE);
		// Dealer gets two cards
		dealerCards.addCard(dealerCard1);
		dealerCards.addCard(dealerCard2);

		BlackJackManager.ifPlayerWin(playerCards, dealerCards, 100);
		Assert.assertTrue(playerCards.getPlayerMoney() == 1100);
	}

	@Test
	public void testRealisticScenarioDealerWins() {
		Deck playerCards = new Deck();
		Deck dealerCards = new Deck();
		playerCards.setPlayerMoney(1000);
		BlackJackManager.endRound = false;

		Card playerCard1 = new Card(Suit.CLUB, Value.FOUR);
		Card playerCard2 = new Card(Suit.DIAMOND, Value.FIVE);

		// Player gets two cards
		playerCards.addCard(playerCard1);
		playerCards.addCard(playerCard2);

		Card dealerCard1 = new Card(Suit.CLUB, Value.EIGHT);
		Card dealerCard2 = new Card(Suit.DIAMOND, Value.NINE);
		// Dealer gets two cards
		dealerCards.addCard(dealerCard1);
		dealerCards.addCard(dealerCard2);

		BlackJackManager.revealDealerCards(playingDeck, playerCards, dealerCards, 100);
		Assert.assertTrue(playerCards.getPlayerMoney() == 900);
	}

	@Test
	public void testRealisticScenarioPush() {
		Deck playerCards = new Deck();
		Deck dealerCards = new Deck();
		playerCards.setPlayerMoney(1000);
		BlackJackManager.endRound = false;

		Card playerCard1 = new Card(Suit.CLUB, Value.TEN);
		Card playerCard2 = new Card(Suit.DIAMOND, Value.JACK);

		// Player gets two cards
		playerCards.addCard(playerCard1);
		playerCards.addCard(playerCard2);

		Card dealerCard1 = new Card(Suit.CLUB, Value.TEN);
		Card dealerCard2 = new Card(Suit.DIAMOND, Value.JACK);
		// Dealer gets two cards
		dealerCards.addCard(dealerCard1);
		dealerCards.addCard(dealerCard2);
		
		
		Assert.assertTrue(DeckUtils.cardsValue(dealerCards) == DeckUtils.cardsValue(playerCards));
	}
}
