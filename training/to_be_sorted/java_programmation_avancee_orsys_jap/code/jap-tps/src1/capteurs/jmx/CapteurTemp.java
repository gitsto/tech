package capteurs.jmx;

import java.io.Serializable;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import capteurs.base.IMachine;

public class CapteurTemp extends NotificationBroadcasterSupport implements IMachine, CapteurTempMBean, Serializable {
	private static final long serialVersionUID = 7804024057686026214L;
	
	private double temperature;
	private double temperatureMin;
	private double temperatureMax;
	private long periode;
	private IMachine machine;
	private Thread thread;
	

	public CapteurTemp(IMachine m, double tMin, double tMax, long p) {
		machine = m;
		temperatureMin = tMin;
		temperatureMax = tMax;
		periode = p;
		thread = new Thread(new InnerLoop());
		thread.start();
	}
	
	public CapteurTemp(IMachine m, double tMin, double tMax) { this(m, tMin, tMax, 2000); }

	public double getTemperature() { return temperature; }
	public double getTemperatureMax() { return temperatureMax; }
	public double getTemperatureMin() { return temperatureMin;}
	public void setTemperatureMax(double v) { temperatureMax = v; }
	public void setTemperatureMin(double v) { temperatureMin = v; }
	
	public void afficherValeurs() {
		System.out.println("la temperature courante est : " + temperature);
		System.out.println("l'intervalle des temperatures normales est : " + temperatureMin + " ... " + temperatureMax);
	}
	
	private class InnerLoop extends Thread {
	
		public void run() {
			try {
				while (true) {
					temperature = machine.getTemperature();
					if (temperature < temperatureMin || temperature > temperatureMax) {
						sendNotification(new Notification("alerte", CapteurTemp.this, 0, "" + temperature));			
					}
					sleep(periode);
				}
			} catch(Exception e) {
				System.err.println("interruption du thread dans CapteurTemp");
				e.printStackTrace();
			}
		}
	}
}
