package monopoly;

public class CompanySquare extends PropertySquare {

    public CompanySquare(Monopoly game, String name, int cost, int rent,
			  PropertyGroup group) {
	this(game, name, null, cost, rent, group);
    }

    public CompanySquare(Monopoly game, String name, MonopolySquare next,
			  int cost, int rent, PropertyGroup group) {
	super(game, name, next, cost, rent, group);
    }

    public int getRent() {
	return game.getDiceTotal() * group.getRentFactorFor(getOwner());
    }
}
