package philosophe.reflect;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import philosophe.thread2.actif.PhiloActif;


public class LanceurPhilo {
	public static void main(String args[]) {
		int nbPhil = Integer.parseInt(args[0], 10);
		if (nbPhil > 10) nbPhil = 10;
		int nbServ = Integer.parseInt(args[1], 10);	
		ExecutorService serveurs = Executors.newFixedThreadPool(nbServ, Executors.defaultThreadFactory());
		
		for (int i = 0; i < nbPhil; i++) {
			PhiloActif pRef;
			try {
				pRef = new PhiloActif(i, serveurs, new RfStub(args[2], args[3]), i, (i + 1) % nbPhil);
				pRef.start();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
