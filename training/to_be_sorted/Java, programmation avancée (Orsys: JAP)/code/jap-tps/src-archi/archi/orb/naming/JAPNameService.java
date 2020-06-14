package archi.orb.naming;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import archi.orb.infra.JAPGenericAnnotSkel;
import archi.orb.util.JAPStubGenerator;
import archi.orb.util.ORBHelper;


public class JAPNameService implements IJAPNameService {
	
	private InetAddress address;
	private int port;

	public JAPNameService(InetAddress add, int p) throws IOException {
		address = add;
		port = p;
	}

	public synchronized Object lookup(String sName) {
		try {
			Socket socket = new Socket(address, port);
			ObjectOutputStream oS = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream iS = new ObjectInputStream(socket.getInputStream());
			oS.writeInt(0);
			oS.writeObject(sName);
			oS.flush();
			JAPName jpn = (JAPName) iS.readObject();
			Class<?> clz = new JAPStubGenerator().generateStubClass(sName, jpn.getInterfaceNames());
			Constructor<?> ctor = clz.getConstructor(java.net.InetAddress.class, int.class);
			Object obj = ctor.newInstance(jpn.getInetAddress(), jpn.getPort());
			return obj;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public synchronized void bind(Object obj, String sName) {
		try {
			Socket socket = new Socket(address, port);
			ObjectOutputStream oS = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream iS = new ObjectInputStream(socket.getInputStream());
			oS.writeInt(1);
			JAPGenericAnnotSkel skel = new JAPGenericAnnotSkel(obj, new ServerSocket(0, 0, InetAddress.getLocalHost()));
			skel.start();
			String[] iNames = ORBHelper.getAnnotatedInterfaceNames(obj.getClass(), archi.orb.infra.JAPRemote.class);
			oS.writeObject(sName);
			oS.writeObject(new JAPName(iNames, skel.getInetAddress(), skel.getPort()));
			oS.flush();
			iS.readObject();
			socket.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public synchronized String[] listServices() {
		try {
			Socket socket = new Socket(address, port);
			ObjectOutputStream oS = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream iS = new ObjectInputStream(socket.getInputStream());
			oS.writeInt(2);
			oS.flush();
			try {
				return (String[]) iS.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			iS.readObject();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public synchronized String[] listInterfacesOfService(String srv) {
		try {
			Socket socket = new Socket(address, port);
			ObjectOutputStream oS = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream iS = new ObjectInputStream(socket.getInputStream());
			oS.writeInt(3);
			oS.flush();
			try {
				return (String[]) iS.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
