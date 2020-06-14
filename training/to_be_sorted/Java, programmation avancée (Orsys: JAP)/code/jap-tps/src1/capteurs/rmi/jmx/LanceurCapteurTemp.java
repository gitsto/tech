package capteurs.rmi.jmx;

import java.lang.management.ManagementFactory;
import java.rmi.Naming;

import javax.management.MBeanServer;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import capteurs.base.ClientMachine;
import capteurs.base.IMachine;
import capteurs.rmi.IRMIMachine;

public class LanceurCapteurTemp {

	public static void main(String[] args) {
	    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		try {
			ObjectName name = new ObjectName("jap.capteurs:type=capteur-temperature");
			IRMIMachine mac = (IRMIMachine) Naming.lookup("rmi://localhost/capteurs/machine1");
		    IMachine capt = new CapteurTemp(mac, 12, 18);
		    mbs.registerMBean(capt, name);
		    NotificationListener listen = new NotificationListener() {
				public void handleNotification(Notification arg0, Object arg1) {
					if ("alerte".equals(arg0.getType()))
						System.err.println("alerte temperature :" + arg0.getMessage());
				}
			};
		    mbs.addNotificationListener(name, listen, null, null);
		    new ClientMachine(capt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
