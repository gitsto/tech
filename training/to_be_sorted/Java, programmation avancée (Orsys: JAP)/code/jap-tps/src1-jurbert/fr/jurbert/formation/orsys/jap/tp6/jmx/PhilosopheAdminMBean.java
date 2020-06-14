package fr.jurbert.formation.orsys.jap.tp6.jmx;

public interface PhilosopheAdminMBean {

	public String getPhilosopheName();

	public int getPosition();

	public boolean isDiscussing();

	public boolean isEating();

	public boolean isHungry();

	public void setHungry(boolean isHungry);

	public boolean isAutist();

	public void setAutist(boolean isAutist);

}