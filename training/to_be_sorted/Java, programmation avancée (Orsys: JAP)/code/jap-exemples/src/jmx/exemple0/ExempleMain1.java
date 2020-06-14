package jmx.exemple0;
import java.lang.management.*;
import javax.management.*;

public class ExempleMain1 {
	public static void main(String[] args) {
		try {
			Exemple mbean = new Exemple();
		
			ObjectName name;
			name = new ObjectName("jap.mbeans:type=exemple1");
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			mbs.registerMBean(mbean, name);
		} catch (MalformedObjectNameException e1) {
			e1.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		}
		
		try { Thread.sleep(10000000); } catch(InterruptedException e) {}
	}
}
