package jmx.exemple0;
import java.lang.management.*;
import javax.management.*;

public class ExempleMain0 {
	public static void main(String[] args) {
		try {
			Exemple mbean = new Exemple();
		
			ObjectName name;
			name = new ObjectName("jap.mbeans:type=exemple1");
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			mbs.registerMBean(mbean, name);
		
			System.out.println("le champ rw de Exemple est " + mbs.getAttribute(name,"ReadWrite"));
			mbs.setAttribute(name,new Attribute("ReadWrite", "Zorgh"));
			mbs.invoke(name,"operation1",new Object[] { "mr Dupont" }, new String[ ] { "java.lang.String" });
		} catch (MalformedObjectNameException e1) {
			e1.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (MBeanException e) {
			e.printStackTrace();
		} catch (ReflectionException e) {
			e.printStackTrace();
		} catch (InvalidAttributeValueException e) {
			e.printStackTrace();
		}
	}
}
