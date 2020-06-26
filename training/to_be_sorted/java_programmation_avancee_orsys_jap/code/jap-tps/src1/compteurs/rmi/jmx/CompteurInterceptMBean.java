package compteurs.rmi.jmx;

public interface CompteurInterceptMBean {
	public void suspend();
	public void resume();
	public void raz();
}
