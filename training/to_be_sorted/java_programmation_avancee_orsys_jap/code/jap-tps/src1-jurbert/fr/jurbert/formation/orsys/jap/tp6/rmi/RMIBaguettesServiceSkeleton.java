package fr.jurbert.formation.orsys.jap.tp6.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import fr.jurbert.formation.orsys.jap.tp6.baguette.LocalBaguettesService;

class RMIBaguettesServiceSkeleton extends UnicastRemoteObject implements IRMIBaguettesService {
	private static final long serialVersionUID = 1L;

	private final LocalBaguettesService localService;

	public RMIBaguettesServiceSkeleton(LocalBaguettesService localService) throws RemoteException {
		if (localService == null) {
			throw new IllegalArgumentException("\'localService\' parameter cannot be null");
		}

		this.localService = localService;
	}

	public void getBaguettes(int bag1, int bag2) throws RemoteException {
		localService.getBaguettes(bag1, bag2);
	}

	public void rendBaguettes(int bag1, int bag2) throws RemoteException {
		localService.rendBaguettes(bag1, bag2);
	}
}
