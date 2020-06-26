package jmx.exemple1;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Exemple extends NotificationBroadcasterSupport implements ExempleMBean {
	private String ReadOnly= "Archibald";
	private String ReadWrite;
	public void operation1(String a0) { System.out.println("hello, " + a0); }
	public void operation1() { operation1(ReadOnly); }
	public String operation2() {
	sendNotification(new Notification("operation2", this, System.currentTimeMillis()));
	return ReadWrite + ReadOnly;
	}
	public String getReadOnly() { return ReadOnly; }
	public synchronized void setReadWrite(String rw) { ReadWrite = rw; }
	public String getReadWrite() { return ReadWrite; }
}