package game;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Manages the draw pile and discard pile.
 */
public class Deck {

    private ArrayList<Card> drawPile;
    private ArrayList<Card> discardPile;

    public Deck() {

        drawPile = new ArrayList<Card>();
        discardPile = new ArrayList<Card>();

        // The starting deck has five attacks and five defense cards.
        for (int index = 0; index < 5; index++) {
            drawPile.add(new Card(
                "Strike",
                "Deal 6 damage",
                1,
                6,
                Card.ATTACK
            ));
        }

        for (int index = 0; index < 5; index++) {
            drawPile.add(new Card(
                "Guard",
                "Gain 5 block",
                1,
                5,
                Card.SKILL
            ));
        }

        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(drawPile);
    }

    public Card drawCard() {

        if (drawPile.size() == 0) {

            if (discardPile.size() == 0) {
                return null;
            }

            drawPile.addAll(discardPile);
            discardPile.clear();

            shuffle();
        }

        return drawPile.remove(drawPile.size() - 1);
    }

    public void discardCard(Card card) {
        discardPile.add(card);
    }

    public void discardHand(ArrayList<Card> hand) {

        for (int index = 0; index < hand.size(); index++) {
            discardPile.add(hand.get(index));
        }

        hand.clear();
    }
}
