package capteurs.base;

public class Machine implements IMachine {
	private double temperature;
	private double temperatureMin;
	private double temperatureMax;
	private double delta;
	private long periode;
	private Thread thread;
	
	public Machine(double t, double tMin, double tMax, double d, long p) {
		temperature = t;
		temperatureMin = tMin;
		temperatureMax = tMax;
		delta = d;
		periode = p;
		thread = new Thread(new Task());
		thread.start();
	}
	
	public Machine(double tMin, double tMax) { this ((tMin + tMax)/2, tMin, tMax, 1, 250); }

	public void afficherValeurs() {
		System.out.println("la valeur courante est : " + temperature);
		System.out.println("l'intervalle courant est : " + temperatureMin + " ... " + temperatureMax);
	}
	
	public double getTemperature() { return temperature; }
	
	private class Task implements Runnable {
		public void run() {
			try {
				while (!thread.isInterrupted()) {
					temperature = temperature + (delta * (Math.random() -0.5));
					if (temperature > temperatureMax) temperature = temperatureMax;
					if (temperature < temperatureMin) temperature = temperatureMin;
					Thread.sleep(periode);
				}
			} catch(Exception e) {
				System.err.println("arret du thread dans CapteurTemp");
				e.printStackTrace();
			}
		}
	}
}
