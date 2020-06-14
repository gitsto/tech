package fr.jurbert.formation.orsys.jap.tp6.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIBaguettesService extends Remote {

	public abstract void getBaguettes(int bag1, int bag2) throws RemoteException;

	public abstract void rendBaguettes(int bag1, int bag2) throws RemoteException;

}