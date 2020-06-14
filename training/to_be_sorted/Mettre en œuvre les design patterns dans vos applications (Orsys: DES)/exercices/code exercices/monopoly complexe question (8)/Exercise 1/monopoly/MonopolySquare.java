package monopoly;

public class MonopolySquare implements ISquare {
    private String name;
    private MonopolySquare next;
    protected final Monopoly game;

    public MonopolySquare(Monopoly game, String name) {
        this(game, name, null);
    }

    public MonopolySquare(Monopoly game, String name, MonopolySquare next) {
        this.game = game;
        this.name = name;
        this.next = next;
    }

    //Write the rest of this class...
}