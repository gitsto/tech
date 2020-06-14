package monopoly;

public class LotSquare extends PropertySquare {
    public LotSquare(Monopoly game, String name, int cost, int rent,
                          PropertyGroup group) {
        this(game, name, null, cost, rent, group);
    }

    public LotSquare(Monopoly game, String name, MonopolySquare next,
                          int cost, int rent, PropertyGroup group) {
        super(game, name, next, cost, rent, group);
    }
    protected int getRentFactor(){
        if (group.isComplete())
          return 2;
        return 1;
    }

    public int getRent() {
        return rent * getRentFactor();
    }
}
