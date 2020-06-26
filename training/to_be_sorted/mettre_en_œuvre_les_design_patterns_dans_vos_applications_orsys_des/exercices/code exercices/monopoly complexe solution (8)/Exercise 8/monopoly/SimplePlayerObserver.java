package monopoly;

public class SimplePlayerObserver implements IPlayerListener {

    public SimplePlayerObserver() {
    }

    public void playerPlayed(ObservablePlayer source, String msg) {
	if (msg!=null)
	    System.out.println(msg);
	else
	    System.out.println("Player " + source.getName() + " is on " + source.getCurrentSquareName() + ": credit = " + source.getMoney());
    }
}