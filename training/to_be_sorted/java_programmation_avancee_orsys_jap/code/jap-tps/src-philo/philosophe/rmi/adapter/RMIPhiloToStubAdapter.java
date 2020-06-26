package philosophe.rmi.adapter;

import java.rmi.RemoteException;

import philosophe.api.IGestBaguettes;


public class RMIPhiloToStubAdapter implements IGestBaguettes {

	private static final long serialVersionUID = -3187450001679129454L;
	
	IRMIGestBaguettes arbitre;

	public RMIPhiloToStubAdapter(IRMIGestBaguettes arb) throws RemoteException { arbitre = arb; }
	
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
}
