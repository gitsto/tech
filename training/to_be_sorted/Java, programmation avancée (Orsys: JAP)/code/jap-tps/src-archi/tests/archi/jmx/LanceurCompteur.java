package tests.archi.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import compteurs.base.ClientCompteur;
import compteurs.base.Compteur;
import compteurs.base.ICompteur;
import compteurs.jmx.CompteurStatistics;

import archi.jmx.infra.JAPGenericInterceptor;
import archi.jmx.infra.JAPGenericInterceptorCtl;
import archi.jmx.util.JAPInterceptorFactory;


public class LanceurCompteur {

	public static void main(String[] args) {
		try {
			ICompteur cpt = new Compteur();
			JAPInterceptorFactory fact = new JAPInterceptorFactory();
			JAPGenericInterceptor inter = (JAPGenericInterceptor) fact.generateInterceptor(new String[] { "compteurs.base.ICompteur"} , cpt);
			inter.configureNotificationMask("incrementer", true, false);
		    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		    ObjectName name = new ObjectName("jap.compteur:type=capteur_client");
			JAPGenericInterceptorCtl mbean = new JAPGenericInterceptorCtl(inter);
		    mbs.registerMBean(mbean, name);
		    CompteurStatistics stat1 = new CompteurStatistics();
		    mbs.addNotificationListener(name, stat1, null, null);
		    stat1.start();
			System.out.println("la valeur du compteur est : " + ((ICompteur) inter).getValeur());
			@SuppressWarnings("unused")
			ClientCompteur clt = new ClientCompteur((ICompteur) inter);
			System.out.println("le compteur est cree et demarre ");
		} catch(Throwable e) { e.printStackTrace(); }
	}
}
