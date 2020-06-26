package compteurs.jmx;

import java.lang.reflect.Field;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import compteurs.base.ICompteur;

import archi.common.JAPGenericRequest;
import archi.common.JAPGenericResponse;

public class CompteurIntercept extends NotificationBroadcasterSupport implements ICompteur, CompteurInterceptMBean {
	private ICompteur target;
	private boolean suspended;
	private int nbSeq;

	public CompteurIntercept(ICompteur tgt) { target = tgt; }

	public int getValeur() {
		if (target == null) return 0;
		nbSeq++;
		Notification notif = new Notification("invocation", this, nbSeq);
		JAPGenericRequest req = new JAPGenericRequest("getValeur", new Class[] {}, new Object[] {}, int.class, 0);
		notif.setUserData(req);
		sendNotification(notif);
		int res = target.getValeur();
		notif = new Notification("resultat", this, nbSeq);
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
		target.incrementer(v);
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
		try {
			Field fld = target.getClass().getDeclaredField("valeur");
			fld.setAccessible(true);
			fld.setInt(target, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
