package philosophe.async.rmi;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.net.*;

import philosophe.api.IGestBaguettes1;
import philosophe.async.thread.GestBagAsync;

class GestBagServer {
	public static void main(String[] args) {
		try {
			int nbPhil = 10;
	  		int nbS = args.length > 1 ? Integer.parseInt(args[1]) : 1;
	  		
	  		LocateRegistry.createRegistry(1099);
	  		
	   		for (int i = 0; i < nbS; i++) {
	   			IGestBaguettes1 gest = new GestBagAsync(nbPhil,"servant RMI : " + "rmi://" + args[0] + "/philos1");
	   	   		RMISkelToGestBagAdapter adapt = new RMISkelToGestBagAdapter(gest);
	   	   		Naming.rebind("rmi://" + args[0] + "/philos1" + i, adapt);
	   		}
		} catch (RemoteException e) {
			System.out.println("Probleme dans RMISynchro Server: " + e);
		} catch (MalformedURLException e) {
			System.out.println("Probleme dans RMISynchro Server: " + e);
		}
	}
}
