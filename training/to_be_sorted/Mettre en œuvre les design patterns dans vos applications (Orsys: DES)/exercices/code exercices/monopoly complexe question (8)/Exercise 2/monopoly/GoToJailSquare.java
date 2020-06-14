package monopoly;

public class GoToJailSquare extends MonopolySquare {
    private MonopolySquare jail;

    public GoToJailSquare(Monopoly game, String name) {
        super(game, name);
    }

    public GoToJailSquare(Monopoly game, String name, MonopolySquare next) {
        super(game, name, next);
    }

    public void landOnBy(Player p) {
        //Write the content of this method...
    }
}

