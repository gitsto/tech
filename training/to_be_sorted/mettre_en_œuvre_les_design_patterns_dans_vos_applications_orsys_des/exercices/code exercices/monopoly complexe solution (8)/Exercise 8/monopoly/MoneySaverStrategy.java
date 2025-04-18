package monopoly;

public class MoneySaverStrategy implements IBuyingStrategy
{
   private int threshold;

   public MoneySaverStrategy(int thresehold)
   {
	this.threshold=thresehold;
   }

   public void buy(PropertySquare s, MonopolyPlayer p)
   {
	if (p.getMoney() > s.getCost() && p.getMoney() > threshold) {
	    p.pay(s.getCost());
	    s.setOwner(p);
	    s.setState(OwnedState.getInstance());
	    p.notifyObservers(p + " buys (carefully...) square " + s);
	}
   }
}
