package philosophe.async.stream;

import philosophe.api.IPhiloCallback;

import java.net.*;
import java.io.*;

public class StPhiloCallbackSkel {
	ServerSocket cnx;
	IPhiloCallback sync;

	public StPhiloCallbackSkel(IPhiloCallback s) {
		sync = s;
		try {
			cnx = new ServerSocket();
		} catch (Exception e) {
		}
	}

	public void service() {
		try {
			for (;;) {
				Socket sck = cnx.accept();
				new Service(sck, sync).start();
			}
		} catch (Exception e) {
		}
	}
	
	public int getPort() { return cnx.getLocalPort(); }
	public InetAddress getAddress() { return cnx.getInetAddress(); }

	class Service extends Thread {

		Socket sck;
		IPhiloCallback sync;

		Service(Socket s, IPhiloCallback g) {
			sck = s;
			sync = g;
		}

		public void run() {
			try {
				InputStream inS1 = sck.getInputStream();
				ObjectInputStream inS = new ObjectInputStream(inS1);

				for (;;) {
					StRequest requete = (StRequest) inS.readObject();
					String req = requete.getRequete();
					if ("baguettesLibres".equals(req)) {
						sync.baguettesAllouees();
					}
				}
			} catch (Exception e) {}
		}
	}
}
