package monopoly;

import java.util.Vector;
import java.util.Enumeration;

public abstract class ObservablePlayer {

    private Vector observers = new Vector();

    public abstract int getMoney();
    public abstract String getName();
    public abstract String getCurrentSquareName();

    public final void addPlayerListener(IPlayerListener l) {
	//Write the content of this method...
    }

    public final void removePlayerListener(IPlayerListener l) {
	//Write the content of this method...
    }

    public final void notifyObservers(String msg) {
	//Write the content of this method...
    }
}

