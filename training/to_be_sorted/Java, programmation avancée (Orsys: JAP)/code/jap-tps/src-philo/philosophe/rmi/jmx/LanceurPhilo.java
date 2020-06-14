package philosophe.rmi.jmx;

import java.lang.management.ManagementFactory;
import java.rmi.Naming;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import archi.jmx.infra.JAPGenericInterceptorCtl;

import philosophe.rmi.adapter.IRMIGestBaguettes;
import philosophe.rmi.adapter.RMIPhiloToStubAdapter;
import philosophe.thread2.actif.PhiloActif;

public class LanceurPhilo {
	public static void main(String args[]) {
		int nbPhil = 5;
		int nbServ = 3;
		
		if (args.length > 0) {
			nbPhil = Integer.parseInt(args[0], 10);
			nbServ = Integer.parseInt(args[1], 10);
		}
	
		String regURL = "rmi://" + args[2] + "/philo";
		try {

			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ExecutorService serveurs = Executors.newFixedThreadPool(nbServ, Executors.defaultThreadFactory());
			AcqBaguettesStatistics stat = new AcqBaguettesStatistics();
			stat.start();
		
			for (int i = 0; i < nbPhil; i++) {
				IRMIGestBaguettes sync = (IRMIGestBaguettes) Naming.lookup(regURL + 0);
				JMXStub stub = new JMXStub(new RMIPhiloToStubAdapter(sync));
				JAPGenericInterceptorCtl mbean = new JAPGenericInterceptorCtl(stub);
				PhiloActif philo = new PhiloActif(i, serveurs, stub, i, (i + 1) % nbPhil);
				PhiloAttributsCtl ctl = new PhiloAttributsCtl(philo);
				stub.activate();
				ObjectName nm1 = new ObjectName("philos.management:type=attributs" + i);
				mbs.registerMBean(ctl, nm1);
				ObjectName nm2 = new ObjectName("philos.management:type=interactions" + i);
				mbs.registerMBean(mbean, new ObjectName("philos.management:type=interactions" + i));
				mbs.addNotificationListener(nm2, stat, null, null);
				philo.start();
			}
		
			
			Object lock = new Object();;
			synchronized(lock) { 
				try {
					lock.wait();
				} catch (InterruptedException e) { e.printStackTrace(); }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
