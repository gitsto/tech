package monopoly;

public class MonopolyPlayer extends Player {

    public MonopolyPlayer(String name, int money, IGameBoard game) {
	super( name, money, game);
	// FreeState is a specific state of the MonopolyPlayer :
	this.state=new FreeState();
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
}