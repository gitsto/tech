package jmx.exemple1;

import javax.management.Notification;
import javax.management.NotificationListener;

public class ExempleListener implements NotificationListener {
	public void handleNotification(Notification arg0, Object arg1) {
		System.out.println("invocation de " + arg0.getType() + " :: " + arg0.getTimeStamp());
	}
}