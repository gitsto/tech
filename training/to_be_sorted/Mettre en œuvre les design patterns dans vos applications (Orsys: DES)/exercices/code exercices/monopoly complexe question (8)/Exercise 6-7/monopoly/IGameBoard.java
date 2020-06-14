package monopoly;

public interface IGameBoard {
    public int throwDices();
    public boolean doubleDice();
    public ISquareIterator createIterator();
}

