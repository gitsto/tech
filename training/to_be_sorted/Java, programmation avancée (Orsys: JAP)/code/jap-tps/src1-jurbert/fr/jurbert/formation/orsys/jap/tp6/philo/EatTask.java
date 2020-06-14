package fr.jurbert.formation.orsys.jap.tp6.philo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import fr.jurbert.formation.orsys.jap.tp6.resto.Plate;

public class EatTask extends TachePhilosophe {

	public EatTask(IPhilosophe philosophe) {
		super(philosophe, "manger");
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp1.q4.TachePhilosophe#beginTask()
	 */
	@Override
	protected void beginTask() {
		IPhilosophe philosophe = getPhilosophe();
		// Ensure plate is not empty
		Plate plate = philosophe.getPlate();
		if(plate.isEmpty()) {
			String philosopheName = getPhilosopheName();
			System.out.println(philosopheName + "'s plate needs to be filled.");
			// Need to wait until plate is filled
			try {
				ExecutorService servers = philosophe.getRestaurant().getServers();
				servers.submit(new FillPlateTask(plate, philosopheName)).get();
			} catch (InterruptedException e) {
			} catch (ExecutionException e) {
			}
		}
		
		// Get the baguettes
		philosophe.acquireBaguettes();
		
		// Eat
		philosophe.setEating(true);
		super.beginTask();
	}
	
	@Override
	protected void endTask() {
		IPhilosophe philosophe = getPhilosophe();
		
		// Stop eating
		philosophe.getPlate().eat();
		philosophe.setEating(false);
		super.endTask();

		// Release the baguettes
		philosophe.releaseBaguettes();
	}
	
	@Override
	protected boolean canExecuteTask() {
		// Only eat if philosophe is hungry
		return getPhilosophe().isHungry();
	}
	
	@Override
	protected boolean canFinish() {
		IPhilosophe philosophe = getPhilosophe();
		return philosophe.getPlate().isEmpty();
	}
}
