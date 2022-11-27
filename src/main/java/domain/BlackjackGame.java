package domain;

import domain.card.Card;
import domain.dto.BlackjackResultDto;
import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final List<Card> cardDeck;

    public BlackjackGame(List<Player> players, Dealer dealer, List<Card> cardDeck) {
        this.players = players;
        this.dealer = dealer;
        this.cardDeck = cardDeck;
    }

    public void start() {
        dealer.giveCardsToPlayers(players, cardDeck);
        dealer.giveCardsToDealer(cardDeck);
    }

    public void finish() {

    }

    public boolean isBlackjack() {
        return dealer.isBlackjack() || playerGotBlackjack();
    }

    public BlackjackResultDto buildBlackjackResult() {
        List<Player> blackjackPlayers = findBlackjackPlayers();
        boolean dealerBlackjack = dealer.isBlackjack();
        boolean playerBlackjack = blackjackPlayers.size() > 0;
        return new BlackjackResultDto(dealerBlackjack, playerBlackjack, blackjackPlayers);
    }

    private boolean playerGotBlackjack() {
        return findBlackjackPlayers().size() > 0;
    }

    private List<Player> findBlackjackPlayers() {
        List<Player> blackjackPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.isBlackjack()) {
                blackjackPlayers.add(player);
            }
        }
        return blackjackPlayers;
    }

    public List<Player> findAffordablePlayers() {
        List<Player> result = new ArrayList<>();
        for (Player player : players) {
            if (player.isAffordable()) {
                result.add(player);
            }
        }
        return result;
    }
}