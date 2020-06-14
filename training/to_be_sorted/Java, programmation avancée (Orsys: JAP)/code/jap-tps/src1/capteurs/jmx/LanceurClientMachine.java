package capteurs.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import capteurs.base.ClientMachine;
import capteurs.base.IMachine;
import capteurs.base.Machine;

public class LanceurClientMachine {

	public static void main(String[] args) {
	    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	    IMachine mac = new Machine(15, 10, 20, 2, 500);

		try {
			ObjectName name = new ObjectName("jap.capteurs:type=capteur-temperature");
		    CapteurTemp capt = new CapteurTemp(mac, 12, 18);
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
