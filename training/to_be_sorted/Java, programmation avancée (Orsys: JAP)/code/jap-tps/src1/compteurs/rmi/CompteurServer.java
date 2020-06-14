package compteurs.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CompteurServer {

	public static void main(String[] args) {
		RMICompteur cpt = null;
		
		try {
			LocateRegistry.createRegistry(1099);
			cpt = new RMICompteur();
			Naming.rebind("rmi://localhost/compteur1", cpt);
		} catch (RemoteException e) {
			e.printStackTrace();
			return;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}
	}

}
