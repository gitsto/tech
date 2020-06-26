package philosophe.async.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIPhiloCallback extends Remote {
	public void baguettesAllouees() throws RemoteException;
}
