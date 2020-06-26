package philosophe.thread2.passif.optim;

import philosophe.api.IGestBaguettes;
import philosophe.gui.GestBaguettesGUI;
import philosophe.gui.IGestBagGUI0;

public class GestBagPassif1 implements IGestBaguettes, IGestBagGUI0 {
	//la classe Baguette remplace Boolean qui n'est pas utilisable ici (pb des singletons TRUE et FALSE)!!!
	private class Baguette {
		boolean estUtilisee;
	}
	
	private Baguette[] baguettes;
	private int nbPhilosophe;
	private GestBaguettesGUI gui;

	public GestBagPassif1(int nb, String ttl) {
		baguettes = new Baguette[nb];
		for (int i = 0; i < nb; i++) baguettes[i] = new Baguette();
		
		nbPhilosophe = nb;
		gui = new GestBaguettesGUI(nbPhilosophe, ttl, this);
	}
	
	public GestBagPassif1(int nb) { this(nb, ""); }

	public boolean acqBaguettes(int b1, int b2) {
		int nb1 = (b1 < b2) ? b1 : b2;
		int nb2 = (b1 < b2) ? b2 : b1;

		synchronized (baguettes[nb1]) { 
			while (baguettes[nb1].estUtilisee) {
				gui.acqBaguettes(nb1, false);
				try {
					baguettes[nb1].wait();
				} catch (InterruptedException e) {	e.printStackTrace(); }
			}
			baguettes[nb1].estUtilisee = true;
		}
		gui.acqBaguettes(nb1, true);
		
		synchronized (baguettes[nb2]) { 
			while (baguettes[nb2].estUtilisee) {
				gui.acqBaguettes(nb2, false);
				try {
					baguettes[nb2].wait();		
				} catch (InterruptedException e) { e.printStackTrace(); }
			}
			baguettes[nb2].estUtilisee = true;
		}
		gui.acqBaguettes(nb2, true);
		
		return true;
	}

	public void libBaguettes(int b1, int b2) {
		int nb1 = (b1 < b2) ? b1 : b2;
		int nb2 = (b1 < b2) ? b2 : b1;
		
		synchronized (baguettes[nb1]) { 
			baguettes[nb1].estUtilisee = false;
			baguettes[nb1].notifyAll();
		}
		gui.libBaguettes(nb1);
		
		synchronized (baguettes[nb2]) {
			baguettes[nb2].estUtilisee = false;
			baguettes[nb2].notifyAll();
		}
		gui.libBaguettes(nb2);
	}
	
	public void libBaguettes(int b1) {
		synchronized (baguettes[b1]) { baguettes[b1].estUtilisee = false; }
	}
}
