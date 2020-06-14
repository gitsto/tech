package compteurs.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import compteurs.base.ClientCompteur;
import compteurs.base.Compteur;
import compteurs.base.ICompteur;

public class LanceurClientCompteur {
	public static void main(String[] args) {
		try {
			ICompteur cpt = new Compteur();
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
