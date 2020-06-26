package archi.jmx.infra;

import javax.management.ListenerNotFoundException;
import javax.management.MBeanNotificationInfo;
import javax.management.NotificationBroadcaster;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

public class JAPGenericInterceptorCtl implements JAPGenericInterceptorCtlMBean, NotificationBroadcaster {
	
	protected JAPGenericInterceptor interceptor;

    public JAPGenericInterceptorCtl(JAPGenericInterceptor itf) { interceptor = itf; }
    public JAPGenericInterceptorCtl() { this(null); }

	public void setTarget(JAPGenericInterceptor itf) {
		interceptor = itf;
	}

	public JAPGenericInterceptor getTarget() { return interceptor; }
	
	public void setNotificationMessage(String id) { interceptor.setNotificationMessage(id); }
	public String getNotificationMessage() { return interceptor.getNotificationMessage(); }

	public synchronized void activate() { interceptor.activate(); }	
	public synchronized void desactivate() { interceptor.desactivate(); }
	
	public void configureNotificationMask(String methNm, String sign, boolean pre, boolean post) {
		interceptor.configureNotificationMask(methNm, sign, pre, post);
 	}
	
	public void configureNotificationMask(String methNm, boolean pre, boolean post) {
		interceptor.configureNotificationMask(methNm, pre, post);
 	}
	
	public void configureNotificationMask(boolean pre, boolean post) {
		interceptor.configureNotificationMask(pre, post);
 	}
	
	public void addNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback) throws IllegalArgumentException {
		interceptor.addNotificationListener(listener, filter, handback);
		
	}
	public MBeanNotificationInfo[] getNotificationInfo() {
		return interceptor.getNotificationInfo();
	}
	public void removeNotificationListener(NotificationListener listener) throws ListenerNotFoundException {
		interceptor.removeNotificationListener(listener);
		
	}
}