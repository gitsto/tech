package compteurs.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMICompteur extends UnicastRemoteObject implements IRMICompteur {
	private static final long serialVersionUID = 7089407799453427453L;
	
	private int valeur;
	
	public RMICompteur(int v) throws RemoteException { valeur = v; }
	public RMICompteur() throws RemoteException { this(0); }

	public int getValeur() { return valeur; }

	public void incrementer() { incrementer(1);}
	public void incrementer(int v) { valeur += v; }
}
