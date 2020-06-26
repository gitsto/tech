package archi.jmx.infra;

public interface JAPGenericInterceptorCtlMBean {
	public void configureNotificationMask(String methNm, String sign, boolean pre, boolean post);
	public void configureNotificationMask(String methNm, boolean pre, boolean post);
	public void configureNotificationMask(boolean pre, boolean post);
	
	public void setNotificationMessage(String id);
	public String getNotificationMessage();
	
	public void activate();
	public void desactivate();
}