package capteurs.stream;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

import capteurs.base.IMachine;

public class StSkel extends Thread {
	ServerSocket cnx;
	IMachine sync;

	public StSkel(IMachine s, String pStr) {
		sync = s;
		try {
			int port = Integer.parseInt(pStr, 10);
			cnx = new ServerSocket(port);
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void start() {
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
					String req = requete.getReq();
					System.out.println("requete " + req + " recue de " + sck.getInetAddress() + " [port : " + sck.getPort() + "]");
					try {
						if ("getTemperature".equals(req)) {
							double res = sync.getTemperature();
							outS.writeObject(new StResponse(res));
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
