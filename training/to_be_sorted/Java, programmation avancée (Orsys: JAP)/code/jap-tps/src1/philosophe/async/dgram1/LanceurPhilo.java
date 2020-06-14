package philosophe.async.dgram1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import philosophe.async.thread.PhiloAsync;


public class LanceurPhilo {
	public static void main(String args[]) {
		int nbPhil = Integer.parseInt(args[0], 10);
		if (nbPhil > 10) nbPhil = 10;
		int nbServ = Integer.parseInt(args[1], 10);	
		ExecutorService serveurs = Executors.newFixedThreadPool(nbServ, Executors.defaultThreadFactory());
		
		DGStub stub = new DGStub(args[2], args[3]);
		for (int i = 0; i < nbPhil; i++) {
			PhiloAsync pRef = new PhiloAsync(i, serveurs, stub, i, (i + 1) % nbPhil);
			DGPhiloCallbackSkel skel = new DGPhiloCallbackSkel(pRef.getPhiloCallback(),"" + (5678 + i));
			skel.service();
			pRef.start();
		}
	}
}
