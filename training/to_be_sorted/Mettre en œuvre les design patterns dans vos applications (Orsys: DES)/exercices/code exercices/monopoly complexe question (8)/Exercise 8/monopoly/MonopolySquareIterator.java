package monopoly;

public class MonopolySquareIterator implements ISquareIterator {
    private MonopolySquare current;

    public MonopolySquareIterator(MonopolySquare first) {
	current = first;
    }

    public ISquare[] nextPossibles() {
	// no real direction choice in MonopolyIterator: we return "MonopolySquare.next()"
	// this should be different with a "CluedoIterator"
	MonopolySquare[] tab=new MonopolySquare[1];
	tab[0]=current.next();
	return tab;
    }

    // Set the iterator to the choosen square, after checking it is possible.
    public void chooseCurrent(ISquare s) throws BadNextException
    {
	ISquare[] tab=nextPossibles();
	for (int i = 0; i<tab.length ;i++) {
	    if (s==tab[i]) {
		current=(MonopolySquare)s;
		return;
	    }
	}
	throw new BadNextException();  // not found...
    }

    public ISquare current() {
	return current;
    }
}

