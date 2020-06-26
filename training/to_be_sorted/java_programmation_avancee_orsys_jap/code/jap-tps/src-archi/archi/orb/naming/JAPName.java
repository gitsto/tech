package archi.orb.naming;

import java.io.Serializable;
import java.net.InetAddress;

public class JAPName implements Serializable {
	private static final long serialVersionUID = 3068891986782778673L;

	private String[] interfaceName;
	private InetAddress  serviceAddress;
	private int port;
	
	public JAPName(String[] iN, InetAddress  sA, int p) {
		interfaceName = iN;
		serviceAddress = sA;
		port = p;
	}

	public String[] getInterfaceNames() { return interfaceName; }
	public InetAddress getInetAddress() { return serviceAddress; }
	public int getPort() { return port; }
}