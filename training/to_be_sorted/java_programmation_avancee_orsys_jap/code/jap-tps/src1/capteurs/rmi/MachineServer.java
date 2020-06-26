package capteurs.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MachineServer {

	public static void main(String[] args) {
		RMIMachine cpt = null;
		
		try {
			LocateRegistry.createRegistry(1099);
			cpt = new RMIMachine(10, 20);
			Naming.rebind("rmi://localhost/capteurs/machine1", cpt);
		} catch (RemoteException e) {
			e.printStackTrace();
			return;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}
	}

}
