package philosophe.thread2.passif.base;

import philosophe.api.IGestBaguettes;
import philosophe.gui.GestBaguettesGUI;
import philosophe.gui.IGestBagGUI0;

public class GestBagPassif implements IGestBaguettes, IGestBagGUI0 {
	private boolean[] estUtilisee;
	private int nbPhilosophe;
	private GestBaguettesGUI gui;

	public GestBagPassif(int nb, String ttl) {
		estUtilisee = new boolean[nb];
		nbPhilosophe = nb;
		gui = new GestBaguettesGUI(nbPhilosophe, ttl, this);
	}
	
	public GestBagPassif(int nb) { this(nb, ""); }

	public synchronized boolean acqBaguettes(int b1, int b2) throws IllegalArgumentException {
		if (! isValid(b1)) throw new IllegalArgumentException("argument de baguette illegal : " + b1);
		if (! isValid(b2)) throw new IllegalArgumentException("argument de baguette illegal : " + b2);
		
		while (estUtilisee[b1] || estUtilisee[b2]) {
			if (estUtilisee[b1]) gui.acqBaguettes(b1, false);
			if (estUtilisee[b2]) gui.acqBaguettes(b2, false);
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		estUtilisee[b1] = true;
		gui.acqBaguettes(b1, true);
		estUtilisee[b2] = true;
		gui.acqBaguettes(b2, true);
		return true;
	}

	public synchronized void libBaguettes(int b1, int b2) throws IllegalArgumentException {
		if (! isValid(b1)) throw new IllegalArgumentException("argument de baguette illegal : " + b1);
		if (! isValid(b2)) throw new IllegalArgumentException("argument de baguette illegal : " + b2);
		estUtilisee[b1] = false;
		gui.libBaguettes(b1);
		estUtilisee[b2] = false;
		gui.libBaguettes(b2);
		notifyAll();
	}
	
	public void libBaguettes(int b1) throws IllegalArgumentException {
		if (! isValid(b1)) throw new IllegalArgumentException("argument de baguette illegal : " + b1);
		estUtilisee[b1] = false;
	}
	
	private boolean isValid(int b) { return b >= 0 && b < nbPhilosophe; }
}
