package archi.orb.naming;

public interface IJAPNameService {
	public Object lookup(String sName);
	public void bind(Object obj, String sName);
	public String[] listServices();
	public String[] listInterfacesOfService(String srv);
}