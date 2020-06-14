package philosophe.rmi.jmx;

import javax.management.Notification;

import archi.common.JAPGenericRequest;
import archi.common.JAPGenericResponse;
import archi.jmx.infra.JAPGenericInterceptor;

import philosophe.api.IGestBaguettes;

public class JMXStub extends JAPGenericInterceptor implements IGestBaguettes {
	private static long ID = 0;
	private IGestBaguettes target;
	
	private static String[] methodIndex = new String[] { "acqBaguettes(II)Z", "libBaguettes(II)V"};

	private boolean[] preNotification = new boolean[] { true, true };
	private boolean[] postNotification = new boolean[] { true, true };
	
	public JMXStub(IGestBaguettes tgt) { 
		target = tgt; 
	}

	public boolean acqBaguettes(int b1, int b2) {
		_sync();
		JAPGenericRequest req = null;
		Notification notif = null;
		if (preNotification[0] || postNotification[0])
			req = new JAPGenericRequest("acqBaguettes",new Class<?>[] { int.class, int.class }, new Object[] { b1, b2 }, boolean.class,ID++);
		if (preNotification[0])  {
			notif = new Notification("invocation",this, 0);
			notif.setUserData(req);
			sendNotification(notif);
		}
		boolean res = target.acqBaguettes(b1, b2);
		if (postNotification[0]) {
			JAPGenericResponse rep = new JAPGenericResponse(req, res);
			notif = new Notification("resultat",this, ID);
			notif.setUserData(rep);
			sendNotification(notif);
		}
		return res;
	}

	public void libBaguettes(int b1, int b2) {
		_sync();
		JAPGenericRequest req = null;
		Notification notif = null;
		if (preNotification[1] || postNotification[1])
			req = new JAPGenericRequest("libBaguettes",new Class<?>[] { int.class, int.class }, new Object[] { b1, b2 }, void.class,ID++);
		if (preNotification[1]) {
			notif = new Notification("invocation",this, 0);
			notif.setUserData(req);
			sendNotification(notif);
		}
		target.libBaguettes(b1, b2);
		if (postNotification[1]) {
			JAPGenericResponse rep = new JAPGenericResponse(req, null);
			notif = new Notification("resultat",this, ID);
			notif.setUserData(rep);
			sendNotification(notif);
		}
	}

	@Override
	public void setTarget(Object itf) throws IllegalArgumentException {
		if (!(itf instanceof IGestBaguettes)) throw new IllegalArgumentException();
		target = (IGestBaguettes) itf;
	}
	
	public void configureNotificationMask(String methNm, String sign, boolean pre, boolean post) {
		String m = methNm + sign;
		for (int i = 0; i < methodIndex.length; i++) {
			if (methodIndex[i].equals(m)) {
				preNotification[i] = pre;
				postNotification[i] = post;
				return;
			}
		}
 	}

	public void configureNotificationMask(String methNm, boolean pre, boolean post) {
		System.out.println("methode non implementee");
	}

	public void configureNotificationMask(boolean pre, boolean post) { 
		System.out.println("methode non implementee");
	}
}