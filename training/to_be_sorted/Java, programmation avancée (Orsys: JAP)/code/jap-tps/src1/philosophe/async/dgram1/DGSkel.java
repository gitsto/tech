package philosophe.async.dgram1;

import philosophe.api.IGestBaguettes1;
import philosophe.api.IPhiloCallback;

import java.net.*;
import java.util.Hashtable;

public class DGSkel {
	private IGestBaguettes1 sync;
	private DatagramSocket socket;
	private Hashtable<Long, IPhiloCallback> callbacks;
	
	public DGSkel(IGestBaguettes1 s, String pStr) {
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
		System.out.println("serveur Datagram demarre");
		byte[] reqBuf = new byte[11];
		DatagramPacket reqPacket = new DatagramPacket(reqBuf, 11);
		
		for (;;) {
			try {
				socket.receive(reqPacket);
				int b1 = reqBuf[9];
				int b2 = reqBuf[10];
				if (reqBuf[8] == SynchroCtes1.ACQ) {
					byte[] repBuf = new byte[9];
					preparePacket(repBuf, reqBuf);
					int snp = reqPacket.getPort();
					InetAddress add = reqPacket.getAddress();
					DatagramPacket repPacket = new DatagramPacket(repBuf, 9, add, snp);
					if (sync.acqBaguettes(b1, b2)) {
						repBuf[8] = (byte) SynchroCtes1.OK;
					} else {
						repBuf[8] = (byte) SynchroCtes1.NO;
					}
					socket.send(repPacket);
				} else if (reqBuf[8] == SynchroCtes1.REL) {
					sync.libBaguettes(b1, b2);
				} else if (reqBuf[8] == SynchroCtes1.REQ) {
					Long rID = getRID(reqBuf);
					IPhiloCallback cb = callbacks.get(rID);
					if (cb != null) {
						cb = new DGPhiloCallbackStub(rID.longValue(),reqPacket.getPort(),reqPacket.getAddress());
						callbacks.put(rID, cb);	
					}
					sync.reqBaguettes(b1, b2, cb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void preparePacket(byte[] buf2, byte[] buf1) {	
		for (int i =0; i < 8; i++) buf2[i] = buf1[i];
	}
	
	private Long getRID(byte[] buf) {
		long v = 0;
		for (int i = 0; i < 8; i++) { v = v << 8 + buf[i]; }
		return new Long(v);
	}
}
