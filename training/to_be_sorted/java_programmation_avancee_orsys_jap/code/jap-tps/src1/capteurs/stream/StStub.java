package capteurs.stream;

import java.net.*;
import java.io.*;

import capteurs.base.IMachine;

public class StStub implements IMachine {
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

	public double getTemperature() {
		try {
			outS.writeObject(new StRequest("getTemperature"));
			rep = (StResponse) inS.readObject();
			Object res = rep.getReponse();
			if (res instanceof Double) return (Double) res;
		} catch (Exception e) { throw new RuntimeException(e); }
		return 0;
	}
}
