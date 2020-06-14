package philosophe.async.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import philosophe.api.IGestBaguettes1;
import philosophe.api.IPhiloCallback;
import philosophe.gui.GestBaguettesGUI;
import philosophe.gui.IGestBagGUI0;

public class GestBagAsync implements IGestBaguettes1, IGestBagGUI0 {
	private boolean[] estUtilisee;
	private int nbBaguettes;
	private GestBaguettesGUI gui;
	
	private class ReqBaguettesInfo {
		int bag1;
		int bag2;
		IPhiloCallback philo;
		
		ReqBaguettesInfo(int b1, int b2, IPhiloCallback p) { bag1 = b1; bag2 = b2; philo = p; }
	}
	
	private List<ReqBaguettesInfo> callbacks;

	public GestBagAsync(int nb, String ttl) {
		estUtilisee = new boolean[nb];
		nbBaguettes = nb;
		gui = new GestBaguettesGUI(nbBaguettes, ttl, this);
		callbacks = new ArrayList<ReqBaguettesInfo>();
	}
	
	public GestBagAsync(int nb) { this(nb, ""); }

	public synchronized boolean acqBaguettes(int b1, int b2) throws IllegalArgumentException {
		return false;
	}

	public synchronized void libBaguettes(int b1, int b2) throws IllegalArgumentException {
		if (! isValid(b1)) throw new IllegalArgumentException("argument de baguette illegal : " + b1);
		if (! isValid(b2)) throw new IllegalArgumentException("argument de baguette illegal : " + b2);

		estUtilisee[b1] = false;
		gui.libBaguettes(b1);
		estUtilisee[b2] = false;
		gui.libBaguettes(b2);
		Iterator<ReqBaguettesInfo> iter = callbacks.iterator();
		while (iter.hasNext()) {
			ReqBaguettesInfo info = iter.next();
			if (estUtilisee[info.bag1] || estUtilisee[info.bag2]) continue;
			estUtilisee[info.bag1] = true;
			estUtilisee[info.bag2] = true;
			info.philo.baguettesAllouees();
			iter.remove();
		}
	}
	
	public void libBaguettes(int b1) {
		estUtilisee[b1] = false;
	}
	
	private boolean isValid(int b) { return b >= 0 && b < nbBaguettes; }

	public void reqBaguettes(int b1, int b2, IPhiloCallback p) throws IllegalArgumentException {
		if (! isValid(b1)) throw new IllegalArgumentException("argument de baguette illegal : " + b1);
		if (! isValid(b2)) throw new IllegalArgumentException("argument de baguette illegal : " + b2);

		if (estUtilisee[b1] || estUtilisee[b2]) {
			callbacks.add(new ReqBaguettesInfo(b1,b2, p));
			return;
		}
		estUtilisee[b1] = true;
		gui.acqBaguettes(b1, true);
		estUtilisee[b2] = true;
		gui.acqBaguettes(b2, true);
		p.baguettesAllouees();
	}
}
