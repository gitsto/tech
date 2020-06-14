package tests.archi.orb;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import archi.jmx.infra.JAPGenericInterceptor;
import archi.jmx.infra.JAPGenericInterceptorCtl;
import archi.jmx.util.JAPInterceptorFactory;
import archi.orb.naming.JAPNameService;

import philosophe.api.IGestBaguettes;
import philosophe.rmi.jmx.AcqBaguettesStatistics;
import philosophe.thread2.actif.PhiloActif;


public class LanceurJMXPhilo1 {
	public static void main(String args[]) {
		int nbPhil = 10;
		int nbServ = 3;
		if (args.length > 0) nbPhil = Integer.parseInt(args[0], 10);
		if (nbPhil > 10) nbPhil = 10;
		if (args.length > 1) nbServ = Integer.parseInt(args[1], 10);
		
		ExecutorService serveurs = Executors.newFixedThreadPool(nbServ, Executors.defaultThreadFactory());
		try {
			JAPNameService sce = new JAPNameService(InetAddress.getLocalHost(), 7777);
			IGestBaguettes s = (IGestBaguettes) sce.lookup("synchro_baguettes_0");
			JAPInterceptorFactory fact = new JAPInterceptorFactory();
		    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		    
			JAPGenericInterceptor inter = (JAPGenericInterceptor) fact.generateInterceptor(new String[] { "philosophe.api.IGestBaguettes"} , s);
			inter.configureNotificationMask("acqBaguettes", true, true);
			JAPGenericInterceptorCtl mbean = new JAPGenericInterceptorCtl(inter);
		    ObjectName name = new ObjectName("jap.philosophes:type=time-statistics");
		    mbs.registerMBean(mbean, name);
		    
		    AcqBaguettesStatistics stat1 = new AcqBaguettesStatistics();
		    stat1.start();
		    mbs.addNotificationListener(name, stat1, null, null);

			for (int i = 0; i < nbPhil; i++) {
				new PhiloActif(i, serveurs, (IGestBaguettes) inter, i, (i + 1) % nbPhil).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
