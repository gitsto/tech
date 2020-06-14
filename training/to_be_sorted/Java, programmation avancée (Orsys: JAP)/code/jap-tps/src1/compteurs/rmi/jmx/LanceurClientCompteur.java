package compteurs.rmi.jmx;

import java.lang.management.ManagementFactory;
import java.rmi.Naming;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import compteurs.base.ClientCompteur;
import compteurs.base.ICompteur;
import compteurs.jmx.CompteurStatistics;
import compteurs.rmi.IRMICompteur;

public class LanceurClientCompteur {
	public static void main(String[] args) {
		try {
			IRMICompteur cpt = (IRMICompteur) Naming.lookup("rmi://localhost/compteur1");
			ICompteur inter = new CompteurIntercept(cpt);
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ObjectName name = new ObjectName("jap.compteur:type=capteur_client");
			mbs.registerMBean(inter, name);
			CompteurStatistics stat1 = new CompteurStatistics();

			mbs.addNotificationListener(name, stat1, null, null);
			stat1.start();
			System.out.println("la valeur du compteur est : " + inter.getValeur());
			@SuppressWarnings("unused")
			ClientCompteur clt = new ClientCompteur(inter);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
