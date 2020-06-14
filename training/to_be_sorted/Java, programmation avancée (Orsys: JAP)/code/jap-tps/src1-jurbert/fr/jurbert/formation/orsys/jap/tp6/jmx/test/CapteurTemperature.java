package fr.jurbert.formation.orsys.jap.tp6.jmx.test;

import java.util.ArrayList;
import java.util.List;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import fr.jurbert.formation.orsys.jap.Utils;

public class CapteurTemperature extends NotificationBroadcasterSupport implements CapteurTemperatureMBean {

	private int temperature;

	private final int minTemperature;

	private final int maxTemperature;

	private final Thread evolutionThread;
	
	private boolean isIncreasing;
	
	private int historyDepth = 5;
	
	private boolean isTemperatureMoving;
	
	private List<Integer> history = new ArrayList<Integer>();

	public CapteurTemperature(int minTemperature, int maxTemperature) {
		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
		this.temperature = 20;

		evolutionThread = new Thread(new EvolutionTask());
	}
	
	public void start() {
		evolutionThread.start();
	}

	class EvolutionTask implements Runnable {

		public void run() {
			System.out.println("EvolutionTask started, initial temperature = " + temperature);
			
			while (true) {
				Utils.sleep(1000);
				
				if (isTemperatureMoving) {
					int maxDT = 10;
					int dT = (int) ((Math.random() - .5) * 2 * maxDT);
					setTemperature(temperature + dT);
					afficherTemperature();
					isIncreasing = (dT >= 0);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.simple.CapteurTemperatureMBean#getTemperature()
	 */
	public int getTemperature() {
		return temperature;
	}
	
	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.simple.CapteurTemperatureMBean#setTemperature(int)
	 */
	public void setTemperature(int temperature) {
		// Store previous temperature in history
		history.add(this.temperature);
		while(history.size() > historyDepth) {
			history.remove(0);
		}
		
		// Apply new temperature
		if (temperature > maxTemperature) {
			temperature = maxTemperature;
			sendNotification(new Notification("WARN - Temperature too high, forced to: " + temperature, this, 0));
		} else if (temperature < minTemperature) {
			temperature = minTemperature;
			sendNotification(new Notification("WARN - Temperature too low, forced to: " + temperature, this, 0));
		} else {
			this.temperature = temperature;
//			sendNotification(new Notification("Temperature has changed: " + temperature, this, 0));
		}
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.simple.CapteurTemperatureMBean#getMaxTemperature()
	 */
	public int getMaxTemperature() {
		return maxTemperature;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.simple.CapteurTemperatureMBean#getMinTemperature()
	 */
	public int getMinTemperature() {
		return minTemperature;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.simple.CapteurTemperatureMBean#isIncreasing()
	 */
	public boolean isIncreasing() {
		return isIncreasing;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.simple.CapteurTemperatureMBean#getHistory()
	 */
	public List<Integer> getHistory() {
		return history;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.simple.CapteurTemperatureMBean#getHistoryDepth()
	 */
	public int getHistoryDepth() {
		return historyDepth;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.simple.CapteurTemperatureMBean#setHistoryDepth(int)
	 */
	public void setHistoryDepth(int historyDepth) {
		this.historyDepth = historyDepth;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.simple.CapteurTemperatureMBean#isTemperatureMoving()
	 */
	public boolean isTemperatureMoving() {
		return isTemperatureMoving;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.simple.CapteurTemperatureMBean#setTemperatureMoving(boolean)
	 */
	public void setTemperatureMoving(boolean isTemperatureMoving) {
		this.isTemperatureMoving = isTemperatureMoving;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp6.simple.CapteurTemperatureMBean#afficherTemperature()
	 */
	public void afficherTemperature() {
		System.out.println("Temperature : " + temperature);
	}

}
