package fr.jurbert.formation.orsys.jap.tp6.jmx.test;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class CapteurTemperatureTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CapteurTemperature capteur = new CapteurTemperature(-10, 35);
		try {
			ObjectName beanName = new ObjectName("fr.jurbert.formation.orsys.jap.jmx:type=capteurTemperature");
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			mbs.registerMBean(capteur, beanName);
			capteur.start();
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		}

	}

}
