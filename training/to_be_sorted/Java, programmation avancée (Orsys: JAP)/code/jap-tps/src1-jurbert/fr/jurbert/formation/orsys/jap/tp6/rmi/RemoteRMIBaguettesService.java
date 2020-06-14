package fr.jurbert.formation.orsys.jap.tp6.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import fr.jurbert.formation.orsys.jap.tp6.baguette.IBaguettesService;

public class RemoteRMIBaguettesService implements IBaguettesService {
	
	private IRMIBaguettesService service;
	
	public RemoteRMIBaguettesService() {
		try {
			service = (IRMIBaguettesService) Naming.lookup("rmi://localhost/baguettes");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getBaguettes(int bag1, int bag2) {
		if (service != null) {
			try {
				service.getBaguettes(bag1, bag2);
				return true;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public void rendBaguettes(int bag1, int bag2) {
		if (service != null) {
			try {
				service.rendBaguettes(bag1, bag2);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString() {
		return "rmi service";
	}

}
