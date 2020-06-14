package philosophe.rmi.adapter;

import java.rmi.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.*;

import philosophe.thread2.actif.PhiloActif;


public class LanceurPhilo {
	public static void main(String args[]) {
		int nbPhil = 5;
		int nbServ = 3;
		
		if (args.length > 0) {
			nbPhil = Integer.parseInt(args[0], 10);
			nbServ = Integer.parseInt(args[1], 10);
		}

		String regURL = "rmi://" + args[2] + "/philo";
		ExecutorService serveurs = Executors.newFixedThreadPool(nbServ, Executors.defaultThreadFactory());

		for (int i = 0; i < nbPhil; i++) {
			try {
				IRMIGestBaguettes sync = (IRMIGestBaguettes) Naming.lookup(regURL + 0);
				PhiloActif pRef = new PhiloActif(i, serveurs, new RMIPhiloToStubAdapter(sync), i, (i + 1) % nbPhil);
				pRef.start();
			} catch (MalformedURLException e) {
				System.err.println("probleme [" + e + "] dans le lookup du Philosophe : " + i);
			} catch (NotBoundException e) {
				System.err.println("probleme [" + e + "] dans le lookup du Philosophe : " + i);
			} catch (RemoteException e) {
				System.err.println("probleme [" + e + "] dans le lookup du Philosophe : " + i);
			}
		}
	}
}
