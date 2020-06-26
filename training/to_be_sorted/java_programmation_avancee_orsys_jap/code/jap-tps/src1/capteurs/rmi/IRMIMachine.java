package capteurs.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIMachine extends Remote {
	public double getTemperature()  throws RemoteException;
}
