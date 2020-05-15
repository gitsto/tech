package fr.paris.jva.exercice8;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class Etape1 {
	public static void main(String[] args) {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		try {
			ObjectName name = new ObjectName("fr.paris.jva.exercice8:type=TCPServer");
			TCPServer server = new TCPServer();
			mbs.registerMBean(server, name);
			server.listen();
			System.out.println("Waiting forever...");
			Thread.sleep(Long.MAX_VALUE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
