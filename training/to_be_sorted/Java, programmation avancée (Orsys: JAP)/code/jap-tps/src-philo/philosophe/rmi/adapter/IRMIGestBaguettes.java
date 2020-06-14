package philosophe.rmi.adapter;

public interface IRMIGestBaguettes extends java.rmi.Remote {
	public boolean acqBaguettes(int b1, int b2) throws java.rmi.RemoteException, IllegalArgumentException;
	void libBaguettes(int b1, int b2) throws java.rmi.RemoteException, IllegalArgumentException;
}
