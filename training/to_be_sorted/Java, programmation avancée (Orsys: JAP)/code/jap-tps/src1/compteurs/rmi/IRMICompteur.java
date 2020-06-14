package compteurs.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMICompteur extends Remote {
	public void incrementer() throws RemoteException;
	public void incrementer(int v) throws RemoteException;
	public int getValeur() throws RemoteException;
}
