package monopoly;

public class FreeParkSquare extends MonopolySquare {

    public FreeParkSquare(Monopoly game, String name) {
        super(game, name);
    }

    public FreeParkSquare(Monopoly game, String name, MonopolySquare next) {
        super(game, name, next);
    }

    public void landOnBy(Player p) {
        //Write the content of this method...(not essential)
    }
}
