package archi.orb.naming;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class JAPNameServer {
	private ServerSocket connect;
	private HashMap<String, JAPName> registry;
	
	public JAPNameServer(int p) throws IOException {
		connect = new ServerSocket(p);
		registry = new HashMap<String, JAPName>();
	}
	
	public void start() throws IOException {
		System.out.println("demarrage JAPNamingServer");
		while(true) {
			Socket s = connect.accept();
			ObjectOutputStream oS = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream iS = new ObjectInputStream(s.getInputStream());
			try {
				int sel = iS.readInt();
				switch(sel) {
				case 0 : 
					oS.writeObject(lookup((String) iS.readObject()));
					break;
				case 1 : 
					bind((String) iS.readObject(),(JAPName) iS.readObject()); 
					oS.writeObject(""); 
					break;
				case 2 : 
					oS.writeObject(listServices()) ; 
					break;
				case 3 :
					oS.writeObject(listInterfacesOfService((String) iS.readObject())); 
					break;
				default : 
					System.err.println("reception d'une requete inconnu");
					oS.writeObject(""); 
					break; 	
				}

				oS.flush();
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close() { 
		try {
			connect.close();
		} catch (IOException e) { e.printStackTrace(); } 
	}

	public JAPName lookup(String sName) {
		//System.out.println("demande de lookup");
		return registry.get(sName);
	}

	public void bind(String sName, JAPName obj) {
		//System.out.println("demande de bind");
		registry.put(sName, (JAPName) obj);
	}

	public String[] listServices() {
		//System.out.println("demande de listServices");
		return registry.keySet().toArray(new String[0]);
	}
	
	public String[] listInterfacesOfService(String srv) {
		JAPName jn = registry.get(srv);
		return jn.getInterfaceNames();
	}
}
