package monopoly;

public class Player {
    private String name;
    private IGameBoard game;
    private ISquareIterator iterator;
    private int money;

    public Player(String name, int money, IGameBoard game) {
        this.name = name;
        this.money = money;
        this.game = game;
        iterator = game.createIterator();
    }

    //Write the rest of this class...

    public void play() {
        int value = game.throwDices();
        for (int i = 1; i < value; i++) iterator.next().passOverBy(this);
        iterator.next().landOnBy(this);
    }

    public String toString() {
        return name;
    }
}
