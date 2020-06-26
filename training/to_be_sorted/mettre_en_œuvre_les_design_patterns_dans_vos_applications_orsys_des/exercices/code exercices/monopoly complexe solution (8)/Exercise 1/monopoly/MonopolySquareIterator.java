package monopoly;

public class MonopolySquareIterator implements ISquareIterator {
    private MonopolySquare current;

    public MonopolySquareIterator(MonopolySquare first) {
        current = first;
    }

    public ISquare next() {
        current = current.next();
        return current;
    }

    public ISquare current() {
        return current;
    }
}

