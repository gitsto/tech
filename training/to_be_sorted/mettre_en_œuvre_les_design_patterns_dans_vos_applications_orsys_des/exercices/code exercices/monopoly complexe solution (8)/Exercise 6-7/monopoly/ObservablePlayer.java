package monopoly;

import java.util.Vector;
import java.util.Enumeration;

public abstract class ObservablePlayer {

    private Vector observers = new Vector();

    public abstract int getMoney();
    public abstract String getName();
    public abstract String getCurrentSquareName();

    public final void addPlayerListener(IPlayerListener l) {
	if (!observers.contains(l))
	    observers.addElement(l);
    }

    public final void removePlayerListener(IPlayerListener l) {
	observers.removeElement(l);
    }

    public final void notifyObservers(String msg) {
	for (Enumeration e = observers.elements(); e.hasMoreElements(); )
	    ((IPlayerListener) e.nextElement()).playerPlayed(this,msg);
    }
}

