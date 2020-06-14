package monopoly;

public class Player {
    private String name;
    private IGameBoard game;
    private IPlayerState state;
    private ISquareIterator iterator;
    private int money;

    public Player(String name, int money, IGameBoard game) {
	this.name = name;
	this.money = money;
	this.game = game;
	iterator = game.createIterator();
	this.state = new FreeState();
    }

    public void play() {
	// this method throws the dices then move...or not !!
	int v=game.throwDices();
	// Write the rest of this method...
    }

    public final void move(int value) {
	for (int i = 1; i < value; i++) iterator.next().passOverBy(this);
	iterator.next().landOnBy(this);
	System.out.println(this.getClass().getName() + ": Player " + this + " to square " + iterator.current());
    }

    public void pay(int amount) {
	money -= amount;
    }

    public void credit(int amount) {
	money += amount;
    }

    public int getMoney() {
	return money;
    }

    public IGameBoard getGame() {
	return game;
    }

    public void setToFree() {
	// Write the content of the method
    }

    public void setToPrisoner() {
	// Write the content of the method
    }
    public boolean isPrisoner(IPlayerState s) {
	// Write the content of the method
    }

    public ISquareIterator getSquareIterator() {
	return iterator;
    }

    public String getName() {
	return name;
    }

    public String toString() {
	return name;
    }
}
