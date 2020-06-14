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

    public void landOnBy(Player p) {
    }

    public void passOverBy(Player p) {
    }

    public MonopolySquare next() {
        return next;
    }

    public void setNext(MonopolySquare next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}