package philosophe.async.dgram1;

import philosophe.api.IGestBaguettes1;
import philosophe.api.IPhiloCallback;

import java.net.*;
import java.io.*;
import java.util.Hashtable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DGStub implements IGestBaguettes1 {
	//Hashtable<Long, byte[]> reqTable = new Hashtable<Long, byte[]>();
	//Hashtable<Long, Semaphore> semTable  = new Hashtable<Long, Semaphore>();
		
	InetAddress add;
	int port;
	DatagramSocket sck;
	
	Hashtable<Long, BlockingQueue<byte[]>> queueTable = new Hashtable<Long, BlockingQueue<byte[]>>();
	ReceptionThread recThread;
	
	class Semaphore {
		private int value;
		public Semaphore(int n) { value = n; }
		public synchronized void P(int n) throws InterruptedException { while (value < n) wait(); value -= n; }
		public synchronized void V(int n) { value += n; if (value > 0) notifyAll(); }
	}
	
	class ReceptionThread extends Thread {
		public void run() {
			for (;;) {
				try {
					byte[] buf = new byte[9];
					sck.receive(new DatagramPacket(buf ,9));
					
					long rId = 0;
					for (int i = 0; i < 8; i++) rId = rId + buf[i] << (56 - 8 * i);

					BlockingQueue<byte[]> bQ = queueTable.get(new Long(rId));
					bQ.put(buf);
					
					//ALTERNATIVE : programmation via des s�maphores
					//Semaphore s = semTable.get(key);
					//reqTable.put(key, rep);
					//s.V(1);
				} catch (Exception ex) {
					System.out.println("erreur dans StubCltThread");
					ex.printStackTrace();
				}
			}
		}
	}
	
	public DGStub(String aStr, String pStr) {
		try {
			add = InetAddress.getByName(aStr);
			port = Integer.parseInt(pStr, 10);
			sck = new DatagramSocket();

			recThread = new ReceptionThread();
			recThread.start();
			System.err.println("lancement du thread user en reception");
		} catch (UnknownHostException e) {
			System.err.println("adresse serveur inconnue");
			return;
		} catch (SocketException e) {
			System.err.println("erreur a la creation de la socket");
			return;
		} catch (IllegalArgumentException e) {
			System.err.println("parametres packet illegaux : " + aStr + " " + pStr);
		}
	}

	public boolean acqBaguettes(int b1, int b2) {
		try {
			long rId = Thread.currentThread().getId();
			Long key = new Long(rId);
			
			BlockingQueue<byte[]> bQ = queueTable.get(key);
			if (bQ == null) {
				bQ = new ArrayBlockingQueue<byte[]>(5);
				queueTable.put(key, bQ);
			}
			
			byte[] buf = new byte[11];
			preparePacket(buf, rId);
			buf[8] = (byte) SynchroCtes1.ACQ;
			buf[9] = (byte) b1;
			buf[10] = (byte) b2;
			sck.send(new DatagramPacket(buf, 11, add, port));
			
			byte[] rep = bQ.take();
			
			//ALTERNATIVE : programmation via des s�maphores
			//Semaphore s = semTable.get(key);
			//if (s == null) {
			//	s = new Semaphore(0);
			//	semTable.put(key, s);
			//}
			//s.P(1);
			
			return (rep[8] == SynchroCtes1.OK);
		} catch (SocketException e) {
			System.err.println("erreur de communication avec le serveur");
		} catch (IOException e) {
			System.err.println("erreur I/O durant communication reseau");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void libBaguettes(int b1, int b2) {
		try {
			long rId = Thread.currentThread().getId();
			byte[] buf = new byte[11];
			preparePacket(buf, rId);
			buf[8] = (byte) SynchroCtes1.REL;
			buf[9] = (byte) b1;
			buf[10] = (byte) b2;
			sck.send(new DatagramPacket(buf, 11, add, port));
		} catch (SocketException e) {
			System.err.println("erreur de communication avec le serveur");
		} catch (IOException e) {
			System.err.println("erreur I/O durant communication reseau");
		}
	}
	
	private void preparePacket(byte[] buf, long rId) {
		buf[0] = (byte) ((rId >>> 56) & 0XFF);
		buf[1] = (byte) ((rId >>> 48) & 0XFF);
		buf[2] = (byte) ((rId >>> 40) & 0XFF);
		buf[2] = (byte) ((rId >>> 32) & 0XFF);		
		buf[4] = (byte) ((rId >>> 24) & 0XFF);
		buf[5] = (byte) ((rId >>> 16) & 0XFF);
		buf[6] = (byte) ((rId >>> 8) & 0XFF);
		buf[7] = (byte) (rId & 0XFF);
	}

	public void reqBaguettes(int b1, int b2, IPhiloCallback p) throws IllegalArgumentException {
		try {
			long rId = Thread.currentThread().getId();
			byte[] buf = new byte[11];
			preparePacket(buf, rId);
			buf[8] = (byte) SynchroCtes1.NTF;
			buf[9] = (byte) b1;
			buf[10] = (byte) b2;
			sck.send(new DatagramPacket(buf, 11, add, port));
		} catch (SocketException e) {
			System.err.println("erreur de communication avec le serveur");
		} catch (IOException e) {
			System.err.println("erreur I/O durant communication reseau");
		}
		
	}
}
