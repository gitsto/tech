package philosophe.rmi.refactor;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.net.*;

class GestBagServer {
	public static void main(String[] args) {
		try {
			int nbPhil = 5;
	  		int nbS = args.length > 1 ? Integer.parseInt(args[1]) : 1;	  		
	  		LocateRegistry.createRegistry(1099);
	  		
	   		for (int i = 0; i < nbS; i++) {
	   			IRMIGestBaguettes gest = new GestBagActif(nbPhil,"servant RMI : " + "rmi://" + args[0] + "/philo" + i);
	   	   		Naming.rebind("rmi://" + args[0] + "/philo" + i, gest);
	   		}
		} catch (RemoteException e) {
			System.out.println("Probleme dans RMISynchro Server: " + e);
		} catch (MalformedURLException e) {
			System.out.println("Probleme dans RMISynchro Server: " + e);
		}
	}
}
