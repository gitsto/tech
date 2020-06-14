package fr.jurbert.formation.orsys.jap.tp6.jmx;

import fr.jurbert.formation.orsys.jap.tp6.philo.IPhilosophe;

public class PhilosopheAdmin implements PhilosopheAdminMBean {
	
	private final IPhilosophe philosophe;
	
	public PhilosopheAdmin(IPhilosophe philosophe) {
		if (philosophe == null) {
			throw new IllegalArgumentException("\'philosophe\' parameter cannot be null");
		}
		
		this.philosophe = philosophe;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.jmx.PhilosopheAdminMBean#getPhilosopheName()
	 */
	public String getPhilosopheName() {
		return philosophe.getPhilosopheName();
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.jmx.PhilosopheAdminMBean#getPosition()
	 */
	public int getPosition() {
		return philosophe.getPosition();
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.jmx.PhilosopheAdminMBean#isDiscussing()
	 */
	public boolean isDiscussing() {
		return philosophe.isDiscussing();
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.jmx.PhilosopheAdminMBean#isEating()
	 */
	public boolean isEating() {
		return philosophe.isEating();
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.jmx.PhilosopheAdminMBean#isHungry()
	 */
	public boolean isHungry() {
		return philosophe.isHungry();
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.jmx.PhilosopheAdminMBean#setHungry(boolean)
	 */
	public void setHungry(boolean isHungry) {
		philosophe.setHungry(isHungry);
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.jmx.PhilosopheAdminMBean#isAutist()
	 */
	public boolean isAutist() {
		return philosophe.isAutist();
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.jmx.PhilosopheAdminMBean#setAutist(boolean)
	 */
	public void setAutist(boolean isAutist) {
		philosophe.setAutist(isAutist);
	}
	
}
