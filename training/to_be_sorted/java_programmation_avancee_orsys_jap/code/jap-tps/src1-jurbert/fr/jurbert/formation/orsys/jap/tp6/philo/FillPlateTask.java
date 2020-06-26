package fr.jurbert.formation.orsys.jap.tp6.philo;

import java.util.concurrent.Callable;

import fr.jurbert.formation.orsys.jap.Utils;
import fr.jurbert.formation.orsys.jap.tp6.resto.Plate;

public class FillPlateTask implements Callable<Plate> {
	
	private final Plate plate;
	
	private final String philosopheName;

	public FillPlateTask(Plate plate, String philosopheName) {
		if (plate == null) {
			throw new IllegalArgumentException("plate cannot be null");
		}
		if (philosopheName == null) {
			throw new IllegalArgumentException("philosopheName cannot be null");
		}
		
		this.plate = plate;
		this.philosopheName = philosopheName;
	}

	public Plate call() throws Exception {
		System.out.println("Server begins filling " + philosopheName + "'s plate.");
		
		// Simultate the filling of the plate
//		Utils.sleep(Utils.getRandomDelay(1000, 3000));
		Utils.sleep(2000);
		
		// Fill the plate
		plate.fill();
		
		System.out.println(philosopheName + "'s plate has been filled.");
		
		return plate;
	}

}
