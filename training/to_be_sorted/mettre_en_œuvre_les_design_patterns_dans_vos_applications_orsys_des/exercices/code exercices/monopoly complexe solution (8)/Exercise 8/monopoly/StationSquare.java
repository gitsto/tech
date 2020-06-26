package monopoly;

public class StationSquare extends PropertySquare {

    public StationSquare(Monopoly game, String name, int cost, int rent,
			  PropertyGroup group) {
	this(game, name, null, cost, rent, group);
    }

    public StationSquare(Monopoly game, String name, MonopolySquare next,
			  int cost, int rent, PropertyGroup group) {
	super(game, name, next, cost, rent, group);
    }
    protected int getRentFactor(){
      return group.countPropertiesOwnedBy(getOwner());
    }

    public int getRent() {
	return rent * getRentFactor();
    }
}
