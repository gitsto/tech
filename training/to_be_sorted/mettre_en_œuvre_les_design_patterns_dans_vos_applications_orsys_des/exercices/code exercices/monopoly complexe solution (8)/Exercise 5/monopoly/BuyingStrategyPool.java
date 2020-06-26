package monopoly;

// BuyingStrategyPool hides the allocation mecanism for strategies.

public class BuyingStrategyPool {
    private static BuyingStrategyPool instance = null;

    private BuyingStrategyPool() { }

    public static BuyingStrategyPool getInstance() { // "singleton" method
	if (instance == null)
	    instance = new BuyingStrategyPool();
	return instance;
    }

    public IBuyingStrategy getMoneySaverStrategy(int threshold){
	return new MoneySaverStrategy(threshold);
    }

    public IBuyingStrategy getRaiderStrategy() {
	return RaiderStrategy.getInstance();
    }
}
