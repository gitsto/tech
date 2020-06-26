package jmx.exemple1;
import java.lang.management.*;
import javax.management.*;

public class ExempleMain3 {
	public static void main(String[] args) {
		try {
			Exemple mbean = new Exemple();
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ObjectName name = new ObjectName("jap.mbeans:type=exemple2");
			mbs.registerMBean(mbean, name);
			mbs.addNotificationListener(name, new ExempleListener(), null, null);
		} catch(Exception e) { }
		try { Thread.sleep(1000 * 600); } catch(InterruptedException e) {}
	}
}
