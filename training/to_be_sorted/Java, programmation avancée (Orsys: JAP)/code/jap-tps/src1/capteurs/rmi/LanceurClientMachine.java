package capteurs.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LanceurClientMachine {

	public static void main(String[] args) {
		try {
			IRMIMachine cpt = (IRMIMachine) Naming.lookup("rmi://localhost/capteurs/machine1");
			new RMIClientMachine(cpt);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
