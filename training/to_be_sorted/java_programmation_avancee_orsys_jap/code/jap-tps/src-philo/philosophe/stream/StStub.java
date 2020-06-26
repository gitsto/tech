package philosophe.stream;

import philosophe.api.IGestBaguettes;

import java.net.*;
import java.io.*;

public class StStub implements IGestBaguettes {
	StResponse rep;
	Socket sck;
	ObjectInputStream inS;
	ObjectOutputStream outS;

	public StStub(String aStr, String pStr) {
		try {
			InetAddress add = InetAddress.getByName(aStr);
			int port = Integer.parseInt(pStr, 10);
			sck = new Socket(add, port);

			outS = new ObjectOutputStream(sck.getOutputStream());
			inS = new ObjectInputStream(sck.getInputStream());
		} catch (Exception e) {
			System.err.println("erreur a la creation de la socket : " + e);
		}
	}

	public boolean acqBaguettes(int b1, int b2) throws IllegalArgumentException {
		try {
			outS.writeObject(new StRequest("acqBaguettes", b1, b2));
			rep = (StResponse) inS.readObject();
			Object res = rep.getReponse();
			if (res instanceof IllegalArgumentException) throw (IllegalArgumentException) res;
			if (res instanceof Boolean) return (Boolean) res;
		} catch (Exception e) { throw new RuntimeException(e); }
		return false;
	}

	public void libBaguettes(int b1, int b2)  throws IllegalArgumentException {
		try {
			outS.writeObject(new StRequest("libBaguettes", b1, b2));
			rep = (StResponse) inS.readObject();
			Object res = rep.getReponse();
			if (res instanceof IllegalArgumentException) throw (IllegalArgumentException) res;
		} catch (Exception e) { throw new RuntimeException(e); } 
	}
}
