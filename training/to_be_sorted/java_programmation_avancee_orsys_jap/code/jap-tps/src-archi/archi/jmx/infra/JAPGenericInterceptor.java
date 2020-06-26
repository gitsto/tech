package archi.jmx.infra;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import archi.common.JAPGenericRequest;
import archi.common.JAPGenericResponse;
import archi.jmx.util.JMXHelper;

public abstract class JAPGenericInterceptor extends NotificationBroadcasterSupport implements JAPGenericInterceptorCtlMBean {

	protected Object target;
	protected String notifMessage = "";
	protected boolean isActive;

    public JAPGenericInterceptor(Object itf, boolean isA) { 
    	target = itf;
    	isActive = isA; 
    }

    public JAPGenericInterceptor(Object itf) throws IllegalArgumentException { this(itf, true); }
    
    public JAPGenericInterceptor() { this(null, false); }

	public abstract void setTarget(Object itf) throws IllegalArgumentException;
	public Object getTarget() { return target; }
	
	public void setNotificationMessage(String id) { notifMessage = id; }
	public String getNotificationMessage() { return notifMessage; }
	
	protected synchronized void _sync() {
		while (!isActive) {
			try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }
		}
	}

	public synchronized void activate() {
		if (target != null) { isActive = true; notifyAll(); }
	}

	public synchronized void desactivate() { isActive = false; }

	protected void _preNotif(JAPGenericRequest req, long sID) {
		Notification notif = new Notification("invocation",this, sID);
		notif.setUserData(req);
		sendNotification(notif);
	}
	
	protected void _postNotif(JAPGenericResponse rep, long sID) {
	    Notification notif = new Notification("resultat",this, sID);
	    notif.setUserData(rep);
	    sendNotification(notif);
	}

	protected static void _configNMask(String[] meths, boolean[] preN, boolean postN[], String metNm, Class<?>[] args, boolean pre, boolean post) {
		String key = metNm + JMXHelper.argSign(args);
		for (int i = 0; i < meths.length; i++) {
			if (key.equals(meths[i])) {
				preN[i] = pre;
				postN[i] = post;
				return;
			}
		}
	}
		
	protected static void _configNMask(String[] meths, boolean[] preN, boolean postN[], String metNm, String sign, boolean pre, boolean post) {
		String key = metNm + sign;
		for (int i = 0; i < meths.length; i++) {
			if (key.equals(meths[i])) {
				//System.out.println("_config (sign) " + key + " :: " + meths[i]);
				preN[i] = pre;
				postN[i] = post;
				return;
			}
		}
	}
	
	protected static void _configNMask(String[] meths, boolean[] preN, boolean postN[], String metNm, boolean pre, boolean post) {
		int l = metNm.length();
		for (int i = 0; i < meths.length; i++) {
			if (meths[i].length() < l + 2) continue;
			if (meths[i].charAt(l)!= '(') continue;
			if (!meths[i].startsWith(metNm)) continue;
			//System.out.println("_config (meth) " + metNm + " :: " + meths[i]);
			preN[i] = pre;
			postN[i] = post;
		}
	}
	
	protected static void _configNMask(String[] meths, boolean[] preN, boolean postN[], boolean pre, boolean post) {
		for (int i = 0; i < meths.length; i++) {
			//System.out.println("_config (all) " + meths[i]);
			preN[i] = pre;
			postN[i] = post;
		}
	}
}