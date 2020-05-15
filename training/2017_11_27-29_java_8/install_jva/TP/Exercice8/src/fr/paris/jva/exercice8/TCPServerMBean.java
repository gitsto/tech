package fr.paris.jva.exercice8;

public interface TCPServerMBean {
	String getLabel() ;
	void setLabel(String label) ;
	boolean isRunning();
	void setRunning(boolean running);
}