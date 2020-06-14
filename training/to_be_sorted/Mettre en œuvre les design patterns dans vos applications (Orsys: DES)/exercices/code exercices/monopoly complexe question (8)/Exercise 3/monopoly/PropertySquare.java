package monopoly;

public class PropertySquare extends MonopolySquare {
    protected Player owner = null;
    protected final int cost;
    protected final int rent;

    public PropertySquare(Monopoly game, String name, int cost, int rent) {
        this(game, name, null, cost, rent);
    }

    public PropertySquare(Monopoly game, String name, MonopolySquare next, int cost, int rent) {
        super(game, name, next);
        this.cost = cost;
        this.rent = rent;
    }
}
