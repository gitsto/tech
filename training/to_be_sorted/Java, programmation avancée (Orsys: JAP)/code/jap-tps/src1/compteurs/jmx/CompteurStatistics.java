package compteurs.jmx;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.management.Notification;
import javax.management.NotificationListener;

import archi.common.JAPGenericRequest;


public class CompteurStatistics extends Thread implements NotificationListener {
	private Hashtable<String, Long> lastInvocation;
	private Hashtable<String, List<Long>> durations;
	
	public CompteurStatistics() {
		lastInvocation = new Hashtable<String, Long>();
		durations = new Hashtable<String, List<Long>>();
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(10000);
				afficheStatistics("incrementer");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void handleNotification(Notification notif, Object handback) {
		String tp = notif.getType();
		JAPGenericRequest meth = (JAPGenericRequest) notif.getUserData();
		String nm = meth.getMethodName();
		if ("invocation".equals(tp)) {
			Long lI = lastInvocation.get(nm);
			lastInvocation.put(nm, Long.valueOf(notif.getTimeStamp()));
			if (lI == null) return;
			long dur = notif.getTimeStamp() -  lI;
			List<Long> ls = durations.get(nm);
			if (ls == null) { ls = new ArrayList<Long>(); durations.put(nm, ls); }
			ls.add(Long.valueOf(dur));
		}
	}
	
	public synchronized void afficheStatistics(String meth) {
		List<Long> ls = durations.get(meth);
		if (ls == null || ls.size() == 0) {
			System.out.println("pas de statistiques disponibles pour " + meth);
			return;
		}

		long dMin = Long.MAX_VALUE;
		long dMax = Long.MIN_VALUE;
		double dMean = 0;
			
		for (int i = 0; i < ls.size(); i++) {
			long d = (Long) ls.get(i);
			if (d < dMin) dMin = d;
			if (d > dMax) dMax = d;
			dMean += d;
		}
		dMean = dMean/ls.size();
		System.out.println("\t\tstatistiques pour " + meth + " : dMin = " + dMin + ", dMax = " + dMax + ", dMoy = " + dMean);
	}

}
