package capteurs.jmx;

public interface CapteurTempMBean {
	public double getTemperature();
	public double getTemperatureMin();
	public void setTemperatureMin(double v);
	public double getTemperatureMax();
	public void setTemperatureMax(double v);
	
	public void afficherValeurs();
}
