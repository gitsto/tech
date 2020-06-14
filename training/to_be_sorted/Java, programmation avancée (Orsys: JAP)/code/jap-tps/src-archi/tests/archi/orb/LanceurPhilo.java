package tests.archi.orb;

import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import archi.orb.naming.JAPNameService;

import philosophe.api.IGestBaguettes;
import philosophe.thread2.actif.PhiloActif;


public class LanceurPhilo {
	public static void main(String args[]) {
		int nbPhil = 5;
		int nbServ = 3;
		if (args.length > 0) nbPhil = Integer.parseInt(args[0], 10);
		if (nbPhil > 10) nbPhil = 10;
		if (args.length > 1) nbServ = Integer.parseInt(args[1], 10);
		
		ExecutorService serveurs = Executors.newFixedThreadPool(nbServ, Executors.defaultThreadFactory());
		try {
			JAPNameService sce = new JAPNameService(InetAddress.getLocalHost(), 7777);
			IGestBaguettes s = (IGestBaguettes) sce.lookup("synchro_baguettes_0");

			for (int i = 0; i < nbPhil; i++) {
				new PhiloActif(i, serveurs, s, i, (i + 1) % nbPhil).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
