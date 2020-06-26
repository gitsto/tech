package monopoly;

public class RaiderStrategy implements IBuyingStrategy
{
    private static RaiderStrategy instance = null;

    private RaiderStrategy() { }

    public static RaiderStrategy getInstance() {
	if (instance == null)
	    instance = new RaiderStrategy();
	return instance;
    }

    public void buy(PropertySquare s, MonopolyPlayer p)
    {
	// write this method
    }
}
