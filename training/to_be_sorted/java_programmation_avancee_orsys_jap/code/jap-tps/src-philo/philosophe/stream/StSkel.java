package philosophe.stream;

import philosophe.api.IGestBaguettes;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class StSkel extends Thread {
	static StResponse ROK = new StResponse(true);
	static StResponse RNO = new StResponse(false);
	static StResponse RNULL = new StResponse(true);
	
	ServerSocket cnx;
	IGestBaguettes sync;

	public StSkel(IGestBaguettes s, String pStr) {
		sync = s;
		try {
			int port = Integer.parseInt(pStr, 10);
			cnx = new ServerSocket(port);
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void run() {
		try {
			ExecutorService srv = Executors.newCachedThreadPool();
			for (;;) {
				Socket sck = cnx.accept();
				System.out.println("connexion acceptee avec " + sck.getInetAddress() + " [port : " + sck.getPort() + "]");
				srv.execute(new SkeletonTask(sck));
			}
		} catch (Exception e) { e.printStackTrace(); }
	}

	private class SkeletonTask implements Runnable {
		private Socket sck;
		
		public SkeletonTask(Socket s) { sck = s; }

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
					System.out.println("requete " + req + " recue de " + sck.getInetAddress() + " [port : " + sck.getPort() + "]");
					try {
						if ("acqBaguettes".equals(req)) {
							if (sync.acqBaguettes(b1, b2)) outS.writeObject(ROK);
							else outS.writeObject(RNO);
						} else if ("libBaguettes".equals(req)) {
							sync.libBaguettes(b1, b2);
							outS.writeObject(RNULL);
						}						
					} catch (Exception e) {
						outS.writeObject(new StResponse(e));
					}
					System.out.println("reponse envoyee a " + sck.getInetAddress() + " [port : " + sck.getPort() + "]");
				}
			} catch (Exception e) {
				System.out.println("Erreur a l'execution du skeleton");
				e.printStackTrace();
			}
		}
	}
}
