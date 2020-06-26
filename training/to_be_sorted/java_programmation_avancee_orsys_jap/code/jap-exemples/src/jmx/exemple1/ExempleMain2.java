package jmx.exemple1;
import java.lang.management.*;
import javax.management.*;

public class ExempleMain2 {
	public static void main(String[] args) {
		try {
			Exemple mbean = new Exemple();
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ObjectName name = new ObjectName("jap.mbeans:type=exemple2");
			mbs.registerMBean(mbean, name);
			mbs.addNotificationListener(name, new ExempleListener(), null, null);
			
			mbs.setAttribute(name,new Attribute("ReadWrite", "Zorgh"));
			mbs.invoke(name,"operation1",new Object[] { "mr Dupont" }, new String[ ] { "java.lang.String" });
			mbs.invoke(name,"operation2",new Object[] {}, new String[ ] {});
		} catch(Exception e) { }
		try { Thread.sleep(1000 * 600); } catch(InterruptedException e) {}
	}
}
