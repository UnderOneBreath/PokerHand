import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHand {
    private Hand hand;

    public PokerHand(String handString) {
        this.hand = parseHand(handString);
    }

    private Hand parseHand(String handString) {
        List<Card> cards = Arrays.stream(handString.split(" "))
                .map(this::parseCard)
                .collect(Collectors.toList());
        return new Hand(cards);
    }

    private Card parseCard(String cardString) {
        String rankString = cardString.substring(0, cardString.length() - 1);
        String suitString = cardString.substring(cardString.length() - 1);

        Card.Rank rank;
        switch (rankString) {
            case "2":
                rank = Card.Rank.TWO;
                break;
            case "3":
                rank = Card.Rank.THREE;
                break;
            case "4":
                rank = Card.Rank.FOUR;
                break;
            case "5":
                rank = Card.Rank.FIVE;
                break;
            case "6":
                rank = Card.Rank.SIX;
                break;
            case "7":
                rank = Card.Rank.SEVEN;
                break;
            case "8":
                rank = Card.Rank.EIGHT;
                break;
            case "9":
                rank = Card.Rank.NINE;
                break;
            case "T":
                rank = Card.Rank.TEN;
                break;
            case "J":
                rank = Card.Rank.JACK;
                break;
            case "Q":
                rank = Card.Rank.QUEEN;
                break;
            case "K":
                rank = Card.Rank.KING;
                break;
            case "A":
                rank = Card.Rank.ACE;
                break;
            default:
                return null;
        }

        Card.Suit suit;
        switch (suitString) {
            case "H":
                suit = Card.Suit.HEARTS;
                break;
            case "D":
                suit = Card.Suit.DIAMONDS;
                break;
            case "C":
                suit = Card.Suit.CLUBS;
                break;
            case "S":
                suit = Card.Suit.SPADES;
                break;
            default:
                return null;
        }
        return new Card(suit, rank);
    }


    @Override
    public String toString() {
        return hand.toString();
    }

    public boolean isStraightFlush() {
        return hand.isStraightFlush();
    }

    public boolean hasFourOfAKind() {
        return hand.hasFourOfAKind();
    }

    public boolean hasFullHouse() {
        return hand.hasFullHouse();
    }

    public boolean isFlush() {
        return hand.isFlush();
    }

    public boolean isStraight() {
        return hand.isStraight();
    }

    public static void main(String[] args) {
        ArrayList<PokerHand> hands = new ArrayList<PokerHand>();
        hands.add(new PokerHand("KS 2H 5C JD TD"));
        hands.add(new PokerHand("2C 3C AC 4C 5C"));
        hands.add(new PokerHand("AD AC AH AS 7C"));
        hands.add(new PokerHand("AD AC AH KH KS"));
        hands.add(new PokerHand("QC 8C 7C 5C 2C"));
        hands.add(new PokerHand("AH KS QH JC TD"));

        for (PokerHand pokerHand : hands) {
            System.out.println("Hand: " + pokerHand);
            if (pokerHand.isStraightFlush()) {
                System.out.println("Is Straight Flush");
            } else if (pokerHand.hasFourOfAKind()) {
                System.out.println("Is Four of a Kind");
            } else if (pokerHand.hasFullHouse()) {
                System.out.println("Is Full House");
            } else if (pokerHand.isFlush()) {
                System.out.println("Is Flush");
            } else if (pokerHand.isStraight()) {
                System.out.println("Is Straight");
            }
        }
    }
}
