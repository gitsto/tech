package philosophe.rmi.adapter;

import java.rmi.RemoteException;
import java.rmi.server.*;

import philosophe.api.IGestBaguettes;

public class RMISkelToGestBagAdapter extends UnicastRemoteObject implements IRMIGestBaguettes {
	private static final long serialVersionUID = -3187450001679129454L;	
	
	IGestBaguettes arbitre;

	public RMISkelToGestBagAdapter(IGestBaguettes arb) throws RemoteException { arbitre = arb; }
	
	public boolean acqBaguettes(int b1, int b2) { return arbitre.acqBaguettes(b1, b2); }
	public synchronized void libBaguettes(int b1, int b2) { arbitre.libBaguettes(b1, b2); }
}
