package monopoly;

public class MonopolyPlayer extends Player {
    IBuyingStrategy strategy;
    public MonopolyPlayer(String name, int money, IGameBoard game, IBuyingStrategy strategy) {
	super( name, money, game);
	// FreeState is a specific state of the MonopolyPlayer
	this.state=new FreeState();
	// BuyingStrategy is specific to Monopoly.
	this.strategy=strategy;
    }

    public void setToFree() {
	state=new FreeState();
	state.onEntry(this);
    }

    public void sendToJail() {
	while (getSquareIterator().current()!= ((Monopoly)game).getJail())
	     try {
		iterator.chooseCurrent(iterator.nextPossibles()[0]);
	     }
	     catch (BadNextException e)
	     {
		System.out.println(e);
	     }
	state=new PrisonerState();
	state.onEntry(this);
    }

    public boolean isPrisoner() {
	return state instanceof PrisonerState;
    }

    public ISquare chooseNextDirection(ISquare[] candidates)
    {
	// for a monopoly game, there is no real direction choice: we take
	// the unique square instance of the array.
	return candidates[0];
    }

    public void buy(PropertySquare prop)
    {
	strategy.buy(prop,this);
    }


}