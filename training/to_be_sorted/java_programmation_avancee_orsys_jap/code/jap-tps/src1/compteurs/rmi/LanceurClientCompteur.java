package compteurs.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LanceurClientCompteur {

	public static void main(String[] args) {
		try {
			IRMICompteur cpt = (IRMICompteur) Naming.lookup("rmi://localhost/compteur1");
			new ClientCompteur(cpt);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
