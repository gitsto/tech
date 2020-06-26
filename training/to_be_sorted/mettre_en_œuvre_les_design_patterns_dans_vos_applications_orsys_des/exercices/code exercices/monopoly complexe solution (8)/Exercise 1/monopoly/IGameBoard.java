package monopoly;

public interface IGameBoard {
    public int throwDices();
    public ISquareIterator createIterator();
}

