package philosophe.async.dgram1;

import philosophe.api.IPhiloCallback;

import java.net.*;
import java.util.Hashtable;

public class DGPhiloCallbackSkel {
	private IPhiloCallback sync;
	private DatagramSocket socket;
	private Hashtable<Long, IPhiloCallback> callbacks;
	
	public DGPhiloCallbackSkel(IPhiloCallback s, String pStr) {
		sync = s;
		callbacks = new Hashtable<Long, IPhiloCallback>();
		
		int port = Integer.parseInt(pStr, 10);
		try {
			socket = new DatagramSocket(port);
		} catch (Exception e) {
			System.out.println("erreur a la creation du serveur DG");
		}
	}

	public void service() {
		System.out.println("serveur Datagram (pour Callback) demarre");
		byte[] reqBuf = new byte[11];
		DatagramPacket reqPacket = new DatagramPacket(reqBuf, 11);
		
		for (;;) {
			try {
				socket.receive(reqPacket);
				if (reqBuf[8] == SynchroCtes1.NTF) {
					Long rID = getRID(reqBuf);
					IPhiloCallback cb = callbacks.get(rID);
					if (cb != null) {
						cb = new DGPhiloCallbackStub(rID.longValue(),reqPacket.getPort(),reqPacket.getAddress());
						callbacks.put(rID, cb);	
					}
					sync.baguettesAllouees();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private Long getRID(byte[] buf) {
		long v = 0;
		for (int i = 0; i < 8; i++) { v = v << 8 + buf[i]; }
		return new Long(v);
	}
}
