package fr.jurbert.formation.orsys.jap.tp6.resto;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import fr.jurbert.formation.orsys.jap.tp6.baguette.IBaguettesService;

public class Restaurant {
	
	/**
	 * Service that executes the plates filling (3 servers).
	 */
	private ExecutorService servers;
	
	private Table table;
	
	private final IBaguettesService baguettesService;

	public Restaurant(int nbServers, IBaguettesService baguettesService) {
		if (baguettesService == null) {
			throw new IllegalArgumentException("\'baguettesService\' parameter cannot be null");
		}
		
		this.baguettesService = baguettesService;
		
		servers = Executors.newFixedThreadPool(nbServers, new ThreadFactory() {
			public Thread newThread(Runnable r) {
				Thread newThread = new Thread(r);
				newThread.setDaemon(true);
				return newThread;
			}
		});
	}
	
	public void allocateTable(int nbPhilosophes) {
		// TODO : use a table factory
		table = new Table(nbPhilosophes, baguettesService);
	}

	public ExecutorService getServers() {
		return servers;
	}
	
	public ITable getTable() {
		return table;
	}
	
}
