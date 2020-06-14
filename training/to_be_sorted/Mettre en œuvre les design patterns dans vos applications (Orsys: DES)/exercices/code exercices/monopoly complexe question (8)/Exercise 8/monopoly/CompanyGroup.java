package monopoly;

public class CompanyGroup extends PropertyGroup {
    public CompanyGroup(String name) {
	super(name);
    }
    public int getRentFactorFor(Player owner) {
	if (countPropertiesOwnedBy(owner) == 2)
	  return 10;
	return 4;
    }
}

