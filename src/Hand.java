import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    public boolean isFlush() {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()))
                .size() == 1;
    }

    public boolean isStraight() {
        List<Integer> ranks = cards.stream()
                .map(card -> card.getRank().ordinal())
                .sorted()
                .collect(Collectors.toList());

        boolean isSequential = true;
        for (int i = 1; i < ranks.size(); i++) {
            if (ranks.get(i) != ranks.get(i - 1) + 1) {
                isSequential = false;
                break;
            }
        }

        boolean isWheel = ranks.equals(List.of(0, 1, 2, 3, 12));

        return isSequential || isWheel;
    }

    public boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    public boolean hasFourOfAKind() {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()))
                .containsValue(4L);
    }

    public boolean hasFullHouse() {
        var rankCounts = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        return rankCounts.containsValue(3L) && rankCounts.containsValue(2L);
    }
}
