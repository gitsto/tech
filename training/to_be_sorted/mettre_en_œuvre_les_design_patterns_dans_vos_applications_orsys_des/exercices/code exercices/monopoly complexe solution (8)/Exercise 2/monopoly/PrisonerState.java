package monopoly;

public class PrisonerState implements IPlayerState {
    private int nbTurnInJail = 0;

    public void singleDice(Player p, int dicesValue) {
	nbTurnInJail++;
	if (nbTurnInJail==3) {
	    System.out.println(p.getName()+" pays $50 to be freed.");
	    p.pay(50);
	    p.setToFree();
	    p.move(dicesValue);
	}
    }

    public void doubleDice(Player p, int dicesValue)
    {
	System.out.println(p.getName()+" is freed for free ");
	p.setToFree();
	p.move(dicesValue);
    }

    public void onEntry(Player p)
    {
	nbTurnInJail=0;
	System.out.println(p.getName()+" is prisoner ");
    }
}


