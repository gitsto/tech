package monopoly;

public class RaiderStrategy implements IBuyingStrategy
{
    private static RaiderStrategy instance = null;

    private RaiderStrategy() { }

    public static RaiderStrategy getInstance() { // "singleton" method
	if (instance == null)
	    instance = new RaiderStrategy();
	return instance;
    }

    public void buy(PropertySquare s, MonopolyPlayer p)
    {
	if (p.getMoney() > s.getCost()) {
	    p.pay(s.getCost());
	    s.setOwner(p);
	    s.setState(OwnedState.getInstance());
	    System.out.println(p + " buys square " + s);
	    System.out.println(p + " credit = " + p.getMoney());
	}
    }
}
