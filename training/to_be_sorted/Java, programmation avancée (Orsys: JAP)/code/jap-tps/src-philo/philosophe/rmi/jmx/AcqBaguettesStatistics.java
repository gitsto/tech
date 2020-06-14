package philosophe.rmi.jmx;

import java.util.HashMap;

import javax.management.Notification;
import javax.management.NotificationListener;

import archi.common.JAPGenericRequest;
import archi.common.JAPGenericResponse;

public class AcqBaguettesStatistics extends Thread implements NotificationListener {
	private HashMap<Long, Long> requests = new HashMap<Long, Long>();
	private long totDuration;
	private int nbRequest;

	public synchronized void handleNotification(Notification notif, Object handback) {
		if ("invocation".equals(notif.getType())) {
			JAPGenericRequest r = (JAPGenericRequest) notif.getUserData();
			if (!"acqBaguettes".equals(r.getMethodName())) return;
				requests.put(((JAPGenericRequest) notif.getUserData()).getIdent(), notif.getTimeStamp());
		} else {
			JAPGenericResponse r = (JAPGenericResponse) notif.getUserData();
			if (!"acqBaguettes".equals(r.getMethodName())) return;
			long ts = requests.get(r.getIdent());
			totDuration += (notif.getTimeStamp() -ts);
			nbRequest ++;
		}
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(5000);
				if (nbRequest > 0)
					System.out.println("duree moyenne d'attente des baguettes : " + (totDuration /nbRequest) + " [" + nbRequest + "]");
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}
