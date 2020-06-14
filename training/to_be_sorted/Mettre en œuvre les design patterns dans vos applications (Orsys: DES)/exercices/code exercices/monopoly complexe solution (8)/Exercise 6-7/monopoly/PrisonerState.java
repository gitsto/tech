package monopoly;

public class PrisonerState implements IPlayerState {
    private int nbTurnInJail = 0;

    public void singleDice(Player p, int dicesValue) {
	nbTurnInJail++;
	if (nbTurnInJail==3) {
	    p.notifyObservers(p.getName()+" pays $50 to be freed.");
	    p.pay(50);
	    ((MonopolyPlayer)p).setToFree(); // it's possible to avoid cast, but code gets less readable.
	    p.move(dicesValue);
	}
    }

    public void doubleDice(Player p, int dicesValue)
    {
	p.notifyObservers(p.getName()+" is freed for free ");
	((MonopolyPlayer)p).setToFree();
	p.move(dicesValue);
    }

    public void onEntry(Player p)
    {
	nbTurnInJail=0;
	p.notifyObservers(p.getName()+" is prisoner ");
    }
}


