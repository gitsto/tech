package monopoly;

public interface ISquareIterator {

    // Return all the possible followers
    public ISquare[] nextPossibles();

    // Set the iterator to the choosen square, after checking it is possible.
    public void chooseCurrent(ISquare s) throws BadNextException;

    public ISquare current();
}