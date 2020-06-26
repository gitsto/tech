package monopoly;

public class MonopolyPlayer extends Player {

    public MonopolyPlayer(String name, int money, IGameBoard game) {
	super( name, money, game);
	// FreeState is a specific state of the MonopolyPlayer :
	this.state=new FreeState();
    }

    // Monopoly specific operations have been moved here
    public void setToFree() {
	state=new FreeState();
	state.onEntry(this);
    }

    public void sendToJail() {
    // this method was previously called "setToPrisoner", and was not responsible of moving users.
    // Because of the "new" complexity of moving algorithm, it have been decided to
    // factorize the moving to jail here.

	while (getSquareIterator().current()!= ((Monopoly)game).getJail())
	     try {
		iterator.chooseCurrent(iterator.nextPossibles()[0]);
	     }
	     catch (BadNextException e) {
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
	//Write the rest of this method
    }
}