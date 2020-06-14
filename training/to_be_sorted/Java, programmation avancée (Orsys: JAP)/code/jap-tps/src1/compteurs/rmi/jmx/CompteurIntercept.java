package compteurs.rmi.jmx;

import java.rmi.RemoteException;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import compteurs.base.ICompteur;
import compteurs.rmi.IRMICompteur;

import archi.common.JAPGenericRequest;
import archi.common.JAPGenericResponse;

public class CompteurIntercept extends NotificationBroadcasterSupport implements ICompteur, CompteurInterceptMBean {
	private IRMICompteur target;
	private boolean suspended;
	private int nbSeq;

	public CompteurIntercept(IRMICompteur tgt) { target = tgt; }

	public int getValeur() {
		if (target == null) return 0;
		nbSeq++;
		Notification notif = new Notification("invocation", this, nbSeq);
		JAPGenericRequest req = new JAPGenericRequest("getValeur", new Class[] {}, new Object[] {}, int.class, 0);
		notif.setUserData(req);
		sendNotification(notif);
		int res = -1;;
		try {
			res = target.getValeur();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		notif = new Notification("retour", this, nbSeq);
		notif.setUserData(new JAPGenericResponse(req, res));
		sendNotification(notif);
		return res;
	}

	public void incrementer() { incrementer(1); }

	public void incrementer(int v) {
		if (target == null) return;
		nbSeq++;
		if (suspended) try { synchronized(this) { wait(); } } catch(InterruptedException e) {}
		Notification notif = new Notification("invocation", this, nbSeq);
		JAPGenericRequest req = new JAPGenericRequest("incrementer", new Class[] {}, new Object[] {}, void.class, 0);
		notif.setUserData(req);
		sendNotification(notif);
		try {
			target.incrementer(v);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		notif = new Notification("resultat", this, nbSeq);
		notif.setUserData(new JAPGenericResponse(req,null));
		sendNotification(notif);
	}

	public void resume() {
		suspended = false;
		synchronized(this) { notifyAll(); }
	}

	public void suspend() { suspended = false; }

	public void raz() {
		if (target == null) return;
		System.out.println("service non implemente");
	}
}
