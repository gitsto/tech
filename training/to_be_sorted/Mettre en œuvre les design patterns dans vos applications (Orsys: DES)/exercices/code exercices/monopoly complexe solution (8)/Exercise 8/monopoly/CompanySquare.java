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
    protected int getRentFactor(){
	if (group.isComplete())
	   return 10;
	return 4;
    }

    public int getRent() {
	return game.getDiceTotal() * getRentFactor();
    }
}
