package philosophe.async.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIGestBaguettes1 extends Remote {
	public boolean acqBaguettes(int b1, int b2) throws java.rmi.RemoteException, IllegalArgumentException;
	void libBaguettes(int b1, int b2) throws java.rmi.RemoteException, IllegalArgumentException;
	public void reqBaguettes(int b1, int b2, IRMIPhiloCallback p) throws IllegalArgumentException, RemoteException ;
}
