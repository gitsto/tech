package philosophe.async.stream;

import philosophe.api.IGestBaguettes1;

import java.net.*;
import java.io.*;

public class StSkel {
	ServerSocket cnx;
	IGestBaguettes1 sync;

	public StSkel(IGestBaguettes1 s, String pStr) {
		sync = s;
		try {
			int port = Integer.parseInt(pStr, 10);
			cnx = new ServerSocket(port);
		} catch (Exception e) {
		}
	}

	public void service() {
		try {
			for (;;) {
				Socket sck = cnx.accept();
				System.out.println("connexion acceptee");
				new PhiloSynk(sck, sync).start();
			}
		} catch (Exception e) {
		}
	}
}

class PhiloSynk extends Thread {

	static StResponse rOK = new StResponse(true);
	static StResponse rNO = new StResponse(false);

	Socket sck;
	IGestBaguettes1 sync;

	PhiloSynk(Socket s, IGestBaguettes1 g) {
		sck = s;
		sync = g;
	}

	public void run() {
		try {
			OutputStream outS1 = sck.getOutputStream();
			InputStream inS1 = sck.getInputStream();
			ObjectOutputStream outS = new ObjectOutputStream(outS1);
			ObjectInputStream inS = new ObjectInputStream(inS1);

			for (;;) {
				StRequest requete = (StRequest) inS.readObject();
				String req = requete.getRequete();
				int b1 = requete.getBaguette1();
				int b2 = requete.getBaguette2();

				if ("acqBaguettes".equals(req)) {
					if (sync.acqBaguettes(b1, b2))outS.writeObject(rOK);
					else outS.writeObject(rNO);
				} else if ("libBaguettes".equals(req)) {
					sync.libBaguettes(b1, b2);
				} else if ("reqBaguettes".equals(req)) {
					sync.reqBaguettes(b1, b2, requete.getCallback());
				}
			}
		} catch (Exception e) {
		}
	}
}
