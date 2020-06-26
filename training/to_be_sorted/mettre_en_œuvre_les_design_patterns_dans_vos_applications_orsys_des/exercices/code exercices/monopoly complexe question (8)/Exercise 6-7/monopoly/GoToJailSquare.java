package monopoly;

public class GoToJailSquare extends MonopolySquare {

    public GoToJailSquare(Monopoly game, String name) {
	super(game, name);
    }

    public GoToJailSquare(Monopoly game, String name, MonopolySquare next) {
	super(game, name, next);
    }

    public void landOnBy(Player p) { //go to jail !!
	((MonopolyPlayer)p).sendToJail(); // "you are prisoner"
    }
}

