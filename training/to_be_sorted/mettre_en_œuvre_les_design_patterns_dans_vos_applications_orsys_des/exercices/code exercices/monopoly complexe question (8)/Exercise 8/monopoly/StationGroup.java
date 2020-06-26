package monopoly;

public class StationGroup extends PropertyGroup {

    public StationGroup(String name) {
	super(name);
    }
    public int getRentFactorFor(Player owner) {
	return countPropertiesOwnedBy(owner);
    }
}
