package fr.jurbert.formation.orsys.jap.tp6.philo;

import fr.jurbert.formation.orsys.jap.tp6.resto.Plate;
import fr.jurbert.formation.orsys.jap.tp6.resto.Restaurant;

public interface IPhilosophe {

	public abstract int getPosition();

	public abstract String getPhilosopheName();

	public Plate getPlate();

	public Restaurant getRestaurant();

	public void enterInRestaurant(Restaurant restaurant);

	public boolean isDiscussing();

	public void setDiscussing(boolean isDiscussing);

	public boolean isEating();

	public void setEating(boolean isEating);

	/**
	 * Warning : blocking until baguettes are gotten. 
	 */
	public void acquireBaguettes();

	public void releaseBaguettes();

	public boolean isLeaving();

	public boolean isHungry();

	public void setHungry(boolean isHungry);

	public boolean isAutist();

	public void setAutist(boolean isAutist);

}