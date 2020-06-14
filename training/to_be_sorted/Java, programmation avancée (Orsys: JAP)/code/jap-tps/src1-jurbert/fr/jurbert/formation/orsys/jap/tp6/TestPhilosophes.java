package fr.jurbert.formation.orsys.jap.tp6;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import fr.jurbert.formation.orsys.jap.Utils;
import fr.jurbert.formation.orsys.jap.tp6.baguette.BaguettesServiceFactory;
import fr.jurbert.formation.orsys.jap.tp6.baguette.IBaguettesService;
import fr.jurbert.formation.orsys.jap.tp6.gui.Gui;
import fr.jurbert.formation.orsys.jap.tp6.gui.ILeaveRestaurantListener;
import fr.jurbert.formation.orsys.jap.tp6.jmx.PhilosopheAdmin;
import fr.jurbert.formation.orsys.jap.tp6.jmx.PhilosopheAdminMBean;
import fr.jurbert.formation.orsys.jap.tp6.philo.IPhilosophe;
import fr.jurbert.formation.orsys.jap.tp6.philo.Philosophe;
import fr.jurbert.formation.orsys.jap.tp6.resto.Restaurant;

public class TestPhilosophes {

	private final static String[] nomsPhilosophes = {
		"Oscar",
		"Hugues",
		"Léonard",
		"Phillipe",
		"Louis", };

	private final static int NB_PHILOSOPHES = nomsPhilosophes.length;

	private List<Philosophe> philosophes;

	public void run() {
		// Initialisation des philosophes
		philosophes = new ArrayList<Philosophe>(NB_PHILOSOPHES);
		int position = 0;
		for (String nom : nomsPhilosophes) {
			philosophes.add(new Philosophe(nom, position));
			position++;
		}

		Gui.getInstance().preinit(NB_PHILOSOPHES, new Listener());

		// Initialisation du restaurant (3 serveurs)
		IBaguettesService baguettesService = BaguettesServiceFactory.getBaguettesService(NB_PHILOSOPHES);
		Restaurant restaurant = new Restaurant(3, baguettesService);
		restaurant.allocateTable(NB_PHILOSOPHES);

		// Initialisation de l'interface graphique
		Gui.getInstance().init(philosophes.toArray(philosophes.toArray(new Philosophe[philosophes.size()])),
				restaurant.getTable(), baguettesService.toString());
		
		// Initialisation de l'administration des philosophes
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		for (IPhilosophe philosophe : philosophes) {
			try {
				ObjectName name = new ObjectName("fr.jurbert.formation.orsys.jap.jmx:type=philosophe_" + philosophe.getPosition());
				PhilosopheAdminMBean philoAdmin = new PhilosopheAdmin(philosophe);
				mbs.registerMBean(philoAdmin, name);
			} catch (NotCompliantMBeanException e) {
				e.printStackTrace();
			} catch (MalformedObjectNameException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (InstanceAlreadyExistsException e) {
				e.printStackTrace();
			} catch (MBeanRegistrationException e) {
				e.printStackTrace();
			}
		}

		// Lancement des philosophes : ils entrent dans le restaurant
		for (IPhilosophe philosophe : philosophes) {
			philosophe.enterInRestaurant(restaurant);
		}
	}

	class Listener implements ILeaveRestaurantListener {

		public void leave() {
			for (Philosophe philosophe : philosophes) {
				philosophe.leaveRestaurant();
			}

			new Thread(new Runnable() {

				public void run() {
					for (Philosophe philosophe : philosophes) {
						philosophe.waitUntilLeft();
					}
					System.out.println("All philosophes have left");
					Utils.sleep(1000);
					System.exit(0);
				}
			}).start();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestPhilosophes().run();
	}

}
