package monopoly;

public abstract class Player {
    protected String name;
    protected IGameBoard game;
    protected ISquareIterator iterator;
    protected int money;
    protected IPlayerState state;

    public Player(String name, int money, IGameBoard game) {
	this.name = name;
	this.game = game;
	iterator = game.createIterator();
	this.money = money;
	this.state = null; // state should be instancied by any "concrete" player
    }

    public void play() {
	// this method throws the dices then move...or not !!
	int v=game.throwDices();
	if (game.doubleDice())
	    state.doubleDice(this,v);
	else
	    state.singleDice(this,v);
    }

    public abstract ISquare chooseNextDirection(ISquare[] candidates);
    // this is the "hook" method: see "MonopolyPlayer class" for an implementation;

    public final void move(int value) {
    // re-Write this method as a "template method"
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

    public ISquareIterator getSquareIterator() {
	return iterator;
    }

    public String getName() {
	return name;
    }

    public String toString() {
	return name;
    }

    public void setState(IPlayerState st)
    {
	state=st;
    }

    public IPlayerState getState()
    {
	return state;
    }

}
