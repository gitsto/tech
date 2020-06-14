package philosophe.async.stream;

import philosophe.api.IGestBaguettes1;
import philosophe.api.IPhiloCallback;

import java.net.*;
import java.util.Hashtable;
import java.io.*;

public class StStub implements IGestBaguettes1 {
	private StResponse rep;
	private Socket sck;
	private ObjectInputStream inS;
	private ObjectOutputStream outS;
	
	Hashtable<IPhiloCallback, IPhiloCallback> callbacks;
		
	public StStub(String aStr, String pStr) {
		try {
			InetAddress add = InetAddress.getByName(aStr);
			int port = Integer.parseInt(pStr, 10);
			sck = new Socket(add, port);
			
			outS = new ObjectOutputStream(sck.getOutputStream());
			inS = new ObjectInputStream(sck.getInputStream());

		} catch (UnknownHostException e) {
			System.err.println("adresse serveur inconnue");
			return;
		} catch (SocketException e) {
			System.err.println("erreur a la cration de la socket");
		} catch (IOException e) {
			System.err.println("erreur durant communication reseau");
		} catch (IllegalArgumentException e) {
			System.err.println("parametres packet illegaux");
		}
	}

	public boolean acqBaguettes(int b1, int b2) {
		try {
			outS.writeObject(new StRequest("acqBaguettes", b1, b2));
			rep = (StResponse) inS.readObject();
			Object res = rep.getReponse();
			if (res instanceof IllegalArgumentException) throw (IllegalArgumentException) res;
			if (res instanceof Boolean) return (Boolean) res;
		} catch (SocketException e) {
			System.err.println("erreur durant communication reseau");
		} catch (IOException e) {
			System.err.println("erreur durant I/O reseau");
		} catch (ClassNotFoundException e) {
			System.err.println("classe inconnue dans reponse serveur");
		}
		return false;
	}

	public void libBaguettes(int b1, int b2) {
		try {
			outS.writeObject(new StRequest("libBaguettes", b1, b2));
		} catch (SocketException e) {
			System.err.println("erreur durant communication reseau");
		} catch (IOException e) {
			System.err.println("erreur durant I/O reseau");
		}
	}

	public void reqBaguettes(int b1, int b2, IPhiloCallback p) throws IllegalArgumentException {
		try {
			IPhiloCallback cb = callbacks.get(p);
			if (cb == null) {
				cb = new StPhiloCallbackStub(new StPhiloCallbackSkel(p));
				callbacks.put(p, cb);
			}
			
			outS.writeObject(new StRequest("reqBaguettes", b1, b2, cb));
		} catch (SocketException e) {
			System.err.println("erreur durant communication reseau");
		} catch (IOException e) {
			System.err.println("erreur durant I/O reseau");
		}
	}
}
