package philosophe.rmi.adapter;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.net.*;

import philosophe.api.IGestBaguettes;
//import philosophe.thread2.actif.GestBagActif;
import philosophe.thread2.passif.base.GestBagPassif;

class GestBagServer {
	public static void main(String[] args) {
		try {
			int nbPhil = 5;
	  		int nbS = args.length > 1 ? Integer.parseInt(args[1]) : 1;	  		
	  		LocateRegistry.createRegistry(1099);
	  		
	   		for (int i = 0; i < nbS; i++) {
	   			//IGestBaguettes gest = new GestBagActif(nbPhil,"servant RMI : " + "rmi://" + args[0] + "/philo" + i);
	   			IGestBaguettes gest = new GestBagPassif(nbPhil,"servant RMI : " + "rmi://" + args[0] + "/philo" + i);
	   	   		RMISkelToGestBagAdapter adapt = new RMISkelToGestBagAdapter(gest);
	   	   		Naming.rebind("rmi://" + args[0] + "/philo" + i, adapt);
	   		}
		} catch (RemoteException e) {
			System.out.println("Probleme dans RMISynchro Server: " + e);
		} catch (MalformedURLException e) {
			System.out.println("Probleme dans RMISynchro Server: " + e);
		}
	}
}
