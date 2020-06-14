package fr.jurbert.formation.orsys.jap.tp6.philo;

import fr.jurbert.formation.orsys.jap.Utils;

public class RepeatTachePhilosophe implements Runnable {
	
	private final TachePhilosophe repeatedTask;

	public RepeatTachePhilosophe(TachePhilosophe repeatedTask) {
		if (repeatedTask == null) {
			throw new IllegalArgumentException("repeatedTask is null");
		}
		
		this.repeatedTask = repeatedTask;
	}
	
	public void run() {
		while(true) {
			if (repeatedTask.canExecuteTask()) {
				repeatedTask.run();
			}

			if (repeatedTask.getPhilosophe().isLeaving()) {
				if (repeatedTask.canFinish()) {
					break;
				}
			}
			
			Utils.sleep(Utils.getRandomDelay(0, 3000));
		}
	}

}
