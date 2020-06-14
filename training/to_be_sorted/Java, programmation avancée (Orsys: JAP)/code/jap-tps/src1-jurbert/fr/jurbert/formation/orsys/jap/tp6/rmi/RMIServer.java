package fr.jurbert.formation.orsys.jap.tp6.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import fr.jurbert.formation.orsys.jap.tp6.baguette.Baguette;
import fr.jurbert.formation.orsys.jap.tp6.baguette.LocalBaguettesService;

public class RMIServer {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialize GUI
		Baguette[] baguettes = new Baguette[10];
		for (int i = 0; i < baguettes.length; i++) {
			Baguette baguette = new Baguette();
			baguettes[i] = baguette;
			RMIServerGui.getInstance().addBaguette(baguette, i);
		}
		RMIServerGui.getInstance().init();
		
		try {
			// Launch server
			LocateRegistry.createRegistry(1099);
			LocalBaguettesService service = new LocalBaguettesService(baguettes);
			RMIBaguettesServiceSkeleton skeleton = new RMIBaguettesServiceSkeleton(service);
			Naming.rebind("rmi://localhost/baguettes", skeleton);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
