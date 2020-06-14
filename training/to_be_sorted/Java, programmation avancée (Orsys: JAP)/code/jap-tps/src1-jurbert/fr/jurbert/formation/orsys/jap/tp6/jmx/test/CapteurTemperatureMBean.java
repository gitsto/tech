package fr.jurbert.formation.orsys.jap.tp6.jmx.test;

import java.util.List;

public interface CapteurTemperatureMBean {

	public int getTemperature();

//	public void setTemperature(int temperature);

	public int getMaxTemperature();

	public int getMinTemperature();

	public void afficherTemperature();

	public boolean isIncreasing();

	public int getHistoryDepth();

	public void setHistoryDepth(int historyDepth);

	public List<Integer> getHistory();

	public boolean isTemperatureMoving();

	public void setTemperatureMoving(boolean isTemperatureMoving);

}