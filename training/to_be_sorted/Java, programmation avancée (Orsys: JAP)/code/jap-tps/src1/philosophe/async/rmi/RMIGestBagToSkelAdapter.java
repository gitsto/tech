package philosophe.async.rmi;

import java.rmi.RemoteException;

import philosophe.api.IPhiloCallback;

public class RMIGestBagToSkelAdapter implements IPhiloCallback {
	private IRMIPhiloCallback callback;
	
	public RMIGestBagToSkelAdapter(IRMIPhiloCallback c) { callback = c; }
	public void baguettesAllouees() { 
		if (callback != null)
			try {
				callback.baguettesAllouees();
			} catch (RemoteException e) {
				e.printStackTrace();
			} 
	}
}
