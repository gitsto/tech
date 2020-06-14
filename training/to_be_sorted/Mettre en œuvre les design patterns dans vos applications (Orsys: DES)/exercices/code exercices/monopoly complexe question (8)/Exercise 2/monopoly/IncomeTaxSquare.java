package monopoly;

public class IncomeTaxSquare extends MonopolySquare {
    private int maxTax;

    public IncomeTaxSquare(Monopoly game, String name, int maxTax) {
	this(game, name, null, maxTax);
    }

    public IncomeTaxSquare(Monopoly game, String name, MonopolySquare next, int maxTax) {
	super(game, name, next);
	this.maxTax = maxTax;
    }

    public void landOnBy(Player p) {
      //Write the content of this method...(not essential)
    }
}
