package monopoly;

public class LotGroup extends PropertyGroup {
    public LotGroup(String name) {
        super(name);
    }
    public int getRentFactorFor(Player owner) {
        if (isComplete())
          return 2;
        return 1;
    }
}
