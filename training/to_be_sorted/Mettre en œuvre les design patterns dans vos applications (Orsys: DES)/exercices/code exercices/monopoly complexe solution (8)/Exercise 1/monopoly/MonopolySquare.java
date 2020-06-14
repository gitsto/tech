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
        System.out.println("Player " + p + " arrive to square " + name);
    }

    public void passOverBy(Player p) {
        System.out.println("Player " + p + " cross square " + name);
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
}