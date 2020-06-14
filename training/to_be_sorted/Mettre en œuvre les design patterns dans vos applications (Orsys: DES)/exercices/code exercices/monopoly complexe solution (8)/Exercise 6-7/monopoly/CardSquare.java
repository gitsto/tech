package monopoly;

public class CardSquare extends MonopolySquare {
    public CardSquare(Monopoly game, String name) {
        super(game, name);
    }

    public CardSquare(Monopoly game, String name, MonopolySquare next) {
        super(game, name, next);
    }
}