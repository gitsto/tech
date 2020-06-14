package philosophe.async.rmi;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.Hashtable;

import philosophe.api.IGestBaguettes1;
import philosophe.api.IPhiloCallback;

public class RMIPhiloToStubAdapter implements IGestBaguettes1 {

	private static final long serialVersionUID = -3187450001679129454L;
	
	IRMIGestBaguettes1 arbitre;
	Hashtable<IPhiloCallback, IRMIPhiloCallback> callbackAdapteurs = new Hashtable<IPhiloCallback, IRMIPhiloCallback>();

	public RMIPhiloToStubAdapter(IRMIGestBaguettes1 arb) throws RemoteException { arbitre = arb; }
	
	public boolean acqBaguettes(int b1, int b2) throws IllegalArgumentException {
		try {
			return arbitre.acqBaguettes(b1, b2);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}
	public void libBaguettes(int b1, int b2) throws IllegalArgumentException {
		try {
			arbitre.libBaguettes(b1, b2);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void reqBaguettes(int b1, int b2, IPhiloCallback p) throws IllegalArgumentException {
		try {
			IRMIPhiloCallback cb = callbackAdapteurs.get(p);
			if (cb == null) {
				cb = (IRMIPhiloCallback) UnicastRemoteObject.exportObject(new RMIStubToPhiloCallbackAdapter(p));
				callbackAdapteurs.put(p, cb);
			}
			arbitre.reqBaguettes(b1, b2,cb);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
