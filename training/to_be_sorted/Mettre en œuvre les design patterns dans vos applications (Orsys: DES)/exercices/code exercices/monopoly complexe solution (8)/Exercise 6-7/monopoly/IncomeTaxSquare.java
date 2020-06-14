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
	int tax = (new Long(Math.round(p.getMoney() * 0.10))).intValue();
	if (tax > maxTax)
	    tax = maxTax;
	p.pay(tax);
	game.addBoardMoney(tax);
    }
}
