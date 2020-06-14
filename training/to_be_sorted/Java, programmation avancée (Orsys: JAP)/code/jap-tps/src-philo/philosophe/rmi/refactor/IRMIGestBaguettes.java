package philosophe.rmi.refactor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIGestBaguettes extends Remote {
	public boolean acqBaguettes(int b1, int b2) throws IllegalArgumentException, RemoteException ;
	public void libBaguettes(int b1, int b2) throws IllegalArgumentException, RemoteException ;
}
