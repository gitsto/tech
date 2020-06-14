package philosophe.securite.sign;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import philosophe.thread2.actif.PhiloActif;


public class LanceurSignPhilo {
	public static void main(String args[]) {
		
		int nbPhil = Integer.parseInt(args[0], 10);
		if (nbPhil > 10) nbPhil = 10;
		int nbServ = Integer.parseInt(args[1], 10);	
		ExecutorService serveurs = Executors.newFixedThreadPool(nbServ, Executors.defaultThreadFactory());

		for (int i = 0; i < nbPhil; i++) {
			PhiloActif pRef = new PhiloActif(i, serveurs, new SignStub(args[2], args[3], "fho"), i, (i + 1) % nbPhil);
			pRef.start();
		}
	}
}
