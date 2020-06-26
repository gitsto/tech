package monopoly;

public class LuxuryTaxSquare extends MonopolySquare {
    private int tax;

    public LuxuryTaxSquare(Monopoly game, String name, int tax) {
        this(game, name, null, tax);
    }

    public LuxuryTaxSquare(Monopoly game, String name, MonopolySquare next, int tax) {
        super(game, name, next);
        this.tax = tax;
    }

    public void landOnBy(Player p) {
        //Write the content of this method...(not essential)
    }
}

