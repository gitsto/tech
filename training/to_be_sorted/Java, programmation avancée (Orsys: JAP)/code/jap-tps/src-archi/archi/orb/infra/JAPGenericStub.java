package archi.orb.infra;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import archi.common.JAPGenericRequest;
import archi.common.JAPGenericResponse;

public abstract class JAPGenericStub {
	protected JAPGenericRequest requete;
	protected Socket socket;
	protected ObjectInputStream inS;
	protected ObjectOutputStream outS;
	protected Hashtable<Long, BlockingQueue<JAPGenericResponse>> queueTable;
	
	public JAPGenericStub(java.net.InetAddress ad, int p) throws IOException {
		socket = new Socket(ad, p);
		outS = new ObjectOutputStream(socket.getOutputStream());
		inS = new ObjectInputStream(socket.getInputStream());
		queueTable = new Hashtable<Long, BlockingQueue<JAPGenericResponse>>();
		ReceptionThread recThread = new ReceptionThread();
		recThread.start();
	}
	
	class ReceptionThread extends Thread {
		public void run() {
			for (;;) {
				try {
					JAPGenericResponse rep = null;
					rep = (JAPGenericResponse) inS.readObject();
					Long key = new Long(rep.getIdent());
					BlockingQueue<JAPGenericResponse> bQ = queueTable.get(key);
					bQ.put(rep);
				} catch (Throwable ex) {
					System.err.println("erreur dans StubCltThread");
					ex.printStackTrace();
				}
			}
		}
	}

	protected JAPGenericResponse getResponse(long rID) throws InterruptedException {
		//return (JAPGenericResponse) inS.readObject();
		Long key = new Long(rID);
		BlockingQueue<JAPGenericResponse> bQ = queueTable.get(key);
		if (bQ == null) {
			bQ = new ArrayBlockingQueue<JAPGenericResponse>(20);
			queueTable.put(key, bQ);
		}
		return bQ.take();
	}
}
