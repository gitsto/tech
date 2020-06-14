package monopoly;

public class StartSquare extends MonopolySquare {
    private int amount;

    public StartSquare(Monopoly game, String name, int amount) {
        this(game, name, null, amount);
    }

    public StartSquare(Monopoly game, String name, MonopolySquare next, int amount) {
        super(game, name, next);
        this.amount = amount;
    }

    public void passOverBy(Player p) {
        //Write the content of this method...(not essential)
    }

    public void landOnBy(Player p) {
        //Write the content of this method...(not essential)
    }
}


