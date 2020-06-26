package monopoly;

public class PropertySquare extends MonopolySquare {
    private IPropertySquareState state;
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
	state = ForSaleState.getInstance();
    }

    public int getCost() {
	return cost;
    }

    public int getRent() {
	return rent;
    }

    public Player getOwner() {
	return owner;
    }

    public void setOwner(Player owner) {
	this.owner = owner;
    }

    public void landOnBy(Player p) {
	state.landOnBy(this, p);
    }

    void setState(IPropertySquareState s) {
	state = s;
    }
}

class ForSaleState implements IPropertySquareState {

    private static ForSaleState instance = null;

    private ForSaleState() { }

    static ForSaleState getInstance() {
	if (instance == null)
	    instance = new ForSaleState();
	return instance;
    }

    public void landOnBy(PropertySquare s, Player p) {
	   ((MonopolyPlayer)p).buy(s);
    }
}

class OwnedState implements IPropertySquareState {

    private static OwnedState instance = null;

    private OwnedState() { }

    static OwnedState getInstance() {
	if (instance == null)
	    instance = new OwnedState();
	return instance;
    }

    public void landOnBy(PropertySquare s, Player p) {
	if (p != s.getOwner()) {
	    p.pay(s.getRent());
	    s.getOwner().credit(s.getRent());
	    p.notifyObservers(p + " pays " + s.getRent() + " to " + s.getOwner());
	}
    }
}
