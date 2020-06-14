package philosophe.async.rmi;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.Hashtable;

import philosophe.api.IGestBaguettes1;
import philosophe.api.IPhiloCallback;

public class RMISkelToGestBagAdapter extends UnicastRemoteObject implements IRMIGestBaguettes1 {
	private static final long serialVersionUID = -3187450001679129454L;	
	
	Hashtable<IRMIPhiloCallback, IPhiloCallback> callbackAdapteurs = new Hashtable<IRMIPhiloCallback, IPhiloCallback>();
	IGestBaguettes1 arbitre;

	public RMISkelToGestBagAdapter(IGestBaguettes1 arb) throws RemoteException { arbitre = arb; }
	
	public boolean acqBaguettes(int b1, int b2) { return arbitre.acqBaguettes(b1, b2); }
	public synchronized void libBaguettes(int b1, int b2) { arbitre.libBaguettes(b1, b2); }

	public void reqBaguettes(int b1, int b2, IRMIPhiloCallback p) throws IllegalArgumentException {
		IPhiloCallback cb = callbackAdapteurs.get(p);
		if (cb == null) {
			cb = new RMIGestBagToSkelAdapter(p);
			callbackAdapteurs.put(p, cb);
		}
		arbitre.reqBaguettes(b1, b2, cb);	
	}
}
